package com.wen.sort;

import java.util.Hashtable;
import java.util.Map;

public class MyLRUCache3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//常规策略初始化
		LRUCache3<Integer,Boolean> map = new LRUCache3<Integer,Boolean>(5 ,LRUCache3.Policy.NORMAL ,0);
		for(int i = 0; i < 10; i++) {
			   map.set(i, true);
			   System.out.println(map.size());
			   System.out.println(map);
		}
		
		//提权策略初始化
		LRUCache3<Integer,Boolean> map1 = new LRUCache3<Integer,Boolean>(10 ,LRUCache3.Policy.WEIGHT ,5);
		for(int i = 0 ; i<= 5 ; i ++) {		//访问同一个Node多次可以提升其权重因子
			map1.set(0, true);
			System.out.println(map1.size());
			System.out.println(map1);
		}
		for(int i = 0; i < 11; i++) {
			   map1.set(i, true);
			   System.out.println(map1.size());
			   System.out.println(map1);
		}
	}
}

/**
 * 		此LRU的特点是用HahsTable + 双向链表实现
 * 		好处就是HahsTable是线程安全的，而且对于LRU的自定义策略可以很灵活
 * 
 * */
class LRUCache3<K,V>{
	
	private int capacity;  
    private Hashtable<K, Node<K,V>> nodes;	//利用Hashtable来缓存容器   
    private Node<K,V> head;	//链表头  
    private Node<K,V> end;	//链表尾
    Policy policy = null ;
    int promoted = 3 ;				//能被提权的权重因子的差值
    /*
     *  定义了两个LRU的策略
     *  第一个常规策略就是新添加，新设置的权重为最高，如果size不够则删除最低权重的
     * 	第二个策略是按照提权因子来判断是否有机会提权而不会被删除，权重计算按照查找，增加优先，如果权重因子到达一个基数就可以提权而不会被删除，提权成功则恢复权重因子
     * */
    public enum Policy {  
    	  NORMAL, WEIGHT
    	}
    
    public LRUCache3(int capacity,Policy p, int promoted) {
        this.capacity = capacity;
        nodes = new Hashtable<K, Node<K,V>>(capacity);//缓存容器
        policy = p ;
        this.promoted = promoted;
    }
    
    //每次get的时候先把node移走，然后再设置到头部，提升权重
    public V get(K key) {
	    	if(nodes.containsKey(key)){
	    		Node<K,V> n = nodes.get(key);
	    		remove(n);
	        setHead(n);
	        n.weight += 1;
	    		return n.value;
	    	}
	    	return null;
    }
    
    //node 的remove 调整双向链表的前后指针
    public void remove(Node<K,V> n){
    		if(n.pre != null) {
    			n.pre.next = n.next;
    		}else {
    			head = n.next;
    		}
    		if(n.next != null) 
    		{
    			n.next.pre = n.pre;
    		}else{
            end = n.pre;
        }
    }
    
    
    // 设置Node为头部
    public void setHead(Node<K,V> n)
    {
    		n.next = head ;
    		n.pre = null;
    		
    		 if(head!=null) {
    	            head.pre = n;
    		 }
    	     head = n;
    	 
        if(end == null) {
            end = head;
        }
    }
    
    //存储Node的时候，先检查是否有Key一样的，如果有就替换值，并且提高权重，没有就新增加并且把它插入到头部
    public void set(K key, V value) 
    {
    		if(nodes.containsKey(key)) {
    			
    			Node<K,V> n = nodes.get(key);
    			n.value = value;
    			n.weight += 1;
    			remove(n);
    			setHead(n);
    		}else {
    			Node<K,V> created = new Node<K,V>(key, value);
             if(nodes.size()>=capacity)
             {
            	 	policyRemove(created);
             }else{
                setHead(created);
             }
             nodes.put(key, created);
    		}
    }
    
    //判断是否可以提权而不会被删除
    public void policyRemove(Node<K,V> n) 
    {
    		if(policy == Policy.NORMAL) {
	    	 	nodes.remove(end.key);
	        remove(end);
	        setHead(n);
	 	}else {
	 		if(end != null) {
		 		Node<K,V> pre = end.pre;
		 		if(end.weight >= pre.weight + promoted) {		//提权成功
		 			end.weight = 0;
		 			nodes.remove(pre.key);
		 			remove(pre);
		 			setHead(n);
		 		}else {
		 			nodes.remove(end.key);
			        remove(end);
			        setHead(n);
		 		}
	 		}
	 	}
    }
    
    public int size() {
    		return nodes.size();
    }
    
    @Override
    public String toString() {
    	
    		StringBuilder sb = new StringBuilder(); 
    		for(Map.Entry<K,Node<K,V>> entry : nodes.entrySet()) 
    		{
    			sb.append(String.format("{%s:%s:%s} ", entry.getKey(), entry.getValue().value , entry.getValue().weight));
    		}
    		return sb.toString();
    }
}

/*
 * 	定义1个双向链表的节点
 * 
 * */
class Node<K,V>{
	K key ;
	V value ;
	int weight ;
	Node<K,V> pre ;
	Node<K,V> next ;
	
	public Node(K key , V value){
		this.key = key;
		this.value = value;
		this.weight = 0;
	}
}