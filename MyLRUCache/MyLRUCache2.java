package com.wen.sort;

import java.util.*;

public class MyLRUCache2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LRUCache2<Integer,Boolean> map = new LRUCache2<Integer,Boolean>(10);
		for(int i = 0; i < 20; i++) {
			   map.put(i, true);
			   System.out.println(map.size());
			   System.out.println(map);
		}
	}
}

/*
 * 	此种方式在内部维护了一个做了防止扩容的LinkedHashMap
 *  利用此LinkedHashMap来做最后的CRUD 处理，外部对于一些基本的常用操作做了同步处理
 * 	使得其线程安全
 * */

class LRUCache2<K,V>
{
	private final int MAX_CACHE_SIZE;
    private final float DEFAULT_LOAD_FACTOR = 0.75f;
    LinkedHashMap<K, V> map;				//定义1个内部的Map来做内部的存储
    
    public LRUCache2(int cacheSize) {
    	
    		MAX_CACHE_SIZE = cacheSize;
    		int capacity = (int) Math.ceil(MAX_CACHE_SIZE / DEFAULT_LOAD_FACTOR) + 1;		//这里是为了使容器达到要求不自动扩容，所以加了个防止扩容的偏移
    		map = new LinkedHashMap<K,V>(capacity,DEFAULT_LOAD_FACTOR){
			@Override
    			protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {			//这里复写LinkedHashMap提供的removeEldestEntry方法来作为自定义LRUCcahe的策略
    		        return size() > MAX_CACHE_SIZE;
    		    }
    		};
    }
    
    public synchronized void put(K key, V value) {
        map.put(key, value);
    }
    
    public synchronized V get(K key) {
        return map.get(key);
    }
    
    public synchronized void remove(K key) {
        map.remove(key);
    }
    
    public synchronized int size() {
        return map.size();
    }

    public synchronized void clear() {
        map.clear();
    }
    
    public synchronized Set<Map.Entry<K, V>> getAll(){
    		return map.entrySet();
    }
    
    @Override
    public String toString() {
    	
    		StringBuilder sb = new StringBuilder(); 
    		for(Map.Entry<K, V> entry : map.entrySet()) 
    		{
    			sb.append(String.format("{%s:%s} ", entry.getKey(), entry.getValue()));
    		}
    		return sb.toString();
    }
}