package com.itwenhao123.myalgorithm.bst;
import java.util.LinkedList;
import java.util.Queue;

	//二分搜索树
	//由于Key需要能够进行比较，所以需要extends Comparable<Key>
public class BST<Key extends Comparable<Key>, Value> {
	
	 // 树中的节点为私有的类, 外界不需要了解二分搜索树节点的具体实现
	public class Node {
	     private Key key;
	     private Value value;
	     private Node left, right;
	
	    public Node(Key key, Value value) {
	         this.key = key;
	         this.value = value;
	         left = right = null;
	     }
	     
        public Node(Node node){
            this.key = node.key;
            this.value = node.value;
            this.left = node.left;
            this.right = node.right;
        }
	 }
	
	 private Node root;  // 根节点
	 private int count;  // 树种的节点个数
	
	 // 构造函数, 默认构造一棵空二分搜索树
	 public BST() {
	     root = null;
	     count = 0;
	 }
	
	 // 返回二分搜索树的节点个数
	 public int size() {
	     return count;
	 }
	
	 // 返回二分搜索树是否为空
	 public boolean isEmpty() {
	     return count == 0;
	 }
	 
	// 向二分搜索树中插入一个新的(key, value)数据对
    public void insert(Key key, Value value){
        root = insert(root, key, value);
    }
	
    // 查看二分搜索树中是否存在键key
    public boolean contain(Key key){
        return contain(root, key);
    }

    // 在二分搜索树中搜索键key所对应的值。如果这个值不存在, 则返回null
    public Value search(Key key){
        return search( root , key );
    }
    
    // 二分搜索树的前序遍历
    public void preOrder(){
        preOrder(root);
    }

    // 二分搜索树的中序遍历
    public void inOrder(){
        inOrder(root);
    }

    // 二分搜索树的后序遍历
    public void postOrder(){
        postOrder(root);
    }
    
    // 寻找二分搜索树的最小的键值
    public Key minimum(){
        assert count != 0;
        Node minNode = minimum( root );
        return minNode.key;
    }
    
    // 寻找二分搜索树的最大的键值
    public Key maximum(){
        assert count != 0;
        Node maxNode = maximum(root);
        return maxNode.key;
    }
    
    // 对以node为根的二叉搜索树进行前序遍历, 递归算法
    private void preOrder(Node node){

        if( node != null ){
            System.out.println(node.key);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // 对以node为根的二叉搜索树进行中序遍历, 递归算法
    private void inOrder(Node node){

        if( node != null ){
            inOrder(node.left);
            System.out.println(node.key);
            inOrder(node.right);
        }
    }

    // 对以node为根的二叉搜索树进行后序遍历, 递归算法
    private void postOrder(Node node){

        if( node != null ){
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.key);
        }
    }
    
    // 从二分搜索树中删除最小值所在节点
    public void removeMin(){
        if( root != null )
            root = removeMin( root );
    }
    
    // 从二分搜索树中删除最大值所在节点
    public void removeMax(){
        if( root != null )
            root = removeMax( root );
    }
    
    // 从二分搜索树中删除键值为key的节点
    public void remove(Key key){
        root = remove(root, key);
    }
    
    // 向以node为根的二分搜索树中, 插入节点(key, value), 使用递归算法
    // 返回插入新节点后的二分搜索树的根
    public Node insert(Node node, Key key, Value value){

        if( node == null ){
            count ++;
            return new Node(key, value);
        }

        if( key.compareTo(node.key) == 0 )
            node.value = value;
        else if( key.compareTo(node.key) < 0 )
            node.left = insert( node.left , key, value);
        else    // key > node->key
            node.right = insert( node.right, key, value);

        return node;
    }
    
    // 查看顺序查找表中是否包含键值为key的节点
    public boolean contain(Node node ,Key key){
    		while(node != null) {
    			if( key.compareTo(node.key) == 0 ) {
    				return true;
    			}
    			else if( key.compareTo(node.key) < 0) {
    				node = node.left;
    			}else {
    				node = node.right;
    			}
    		}
    		return false;
    }
    
 // 查看顺序查找表中是否包含键值为key的节点
    public Value search(Node node ,Key key){
		while(node != null) {
			if( key.compareTo(node.key) == 0 ) {
				return node.value;
			}
			else if( key.compareTo(node.key) < 0) {
				node = node.left;
			}else {
				node = node.right;
			}
		}
		return null;
    }
    
    public void levelOrder(){

        // 我们使用LinkedList来作为我们的队列
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        while( !q.isEmpty() ){

            Node node = q.remove();

            System.out.println(node.key);

            if( node.left != null )
                q.add( node.left );
            if( node.right != null )
                q.add( node.right );
        }
    }
    
    // 返回以node为根的二分搜索树的最小键值所在的节点
    private Node minimum(Node node){
        if( node.left == null )
            return node;

        return minimum(node.left);
    }
    
    // 返回以node为根的二分搜索树的最大键值所在的节点
    private Node maximum(Node node){
        if( node.right == null )
            return node;

        return maximum(node.right);
    }
    
    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node){

        if( node.left == null ){

            Node rightNode = node.right;
            node.right = null;
            count --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }
    
    // 删除掉以node为根的二分搜索树中的最大节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node){

        if( node.right == null ){

            Node leftNode = node.left;
            node.left = null;
            count --;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    
    // 删除掉以node为根的二分搜索树中键值为key的节点, 递归算法
    // 返回删除节点后新的二分搜索树的根
    Node remove(Node node, Key key){

        if( node == null )
            return null;

        if( key.compareTo(node.key) < 0 ){
            node.left = remove( node.left , key );
            return node;
        }
        else if( key.compareTo(node.key) > 0 ){
            node.right = remove( node.right, key );
            return node;
        }
        else{   // key == node->key

            // 待删除节点左子树为空的情况
            if( node.left == null ){
                Node rightNode = node.right;
                node.right = null;
                count --;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if( node.right == null ){
                Node leftNode = node.left;
                node.left = null;
                count--;
                return leftNode;
            }

            // 待删除节点左右子树均不为空的情况

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = new Node(minimum(node.right));
            count ++;

            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;
            count --;

            return successor;
        }
    }
    
    public static void main(String[] args) {
//    		int N = 100;
        BST<Integer, String> bst = new BST<Integer,String>();
//        for(int i = 1 ; i < N ; i ++) {
//        		bst.insert(i, (i)+"");
//        }
//
//        System.out.println(" bst.root: " + bst.root.key + " , ");
//        bst.preOrder();
//        System.out.print("min: " + bst.minimum() + " , ");
//        bst.removeMin();
//        System.out.print("min: " + bst.minimum() + " , ");

        int a[] = {15,11,16,8,12,17,9} ;
        for(int i = 0 ; i <a.length ; i++) {
        		bst.insert(a[i], (a[i])+"");
        }
        bst.preOrder();
        System.out.println("bst.root: " + bst.root.key + " , ");
        System.out.println("min: " + bst.minimum() + " , ");
        bst.removeMin();
        System.out.println("min: " + bst.minimum() + " , ");
    }
}
