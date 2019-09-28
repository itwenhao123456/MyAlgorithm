package com.itwenhao123.myalgorithm.heap;

public class MyIndexMaxHeap1<Item extends Comparable> {

	private Item[] data;
    private int count;
    private int capacity;
    private int indexes[];		// 最大索引堆中的索引, indexes[x] = i 表示索引i在x的位置
    protected int[] reverse;    // 最大索引堆中的反向索引, reverse[i] = x 表示索引i在x的位置
	
	public MyIndexMaxHeap1(int capacity) {
		data = (Item[])new Comparable[capacity+1];
        count = 0;
        this.capacity = capacity;
        indexes = new int[capacity+1];
        reverse = new int[capacity+1];
        reverse[0] = 0;
        for( int i = 1 ; i <= capacity ; i ++ )
            reverse[i] = i;
	}
	
    public void insert(int i , Item item) {
    	
    		i += 1;
        data[i] = item;
        indexes[count+1] = i;
        //reverse[count+1] = i;
        count ++;
        shiftUp(count);
    }
    
    private void shiftUp(int k) {
        while( k > 1 && data[indexes[k/2]].compareTo(data[indexes[k]]) < 0 ){
            swapIndexes(k, k/2);
            //swapRevs(reverse[k],reverse[k/2]);
            reverse[indexes[k]] = k;
            reverse[indexes[k/2]] = k/2;
            k /= 2;
        }
    }
    
 // 从最大堆中取出堆顶元素, 即堆中所存储的最大数据
    public Item extractMax(){

        Item ret = data[indexes[1]];
        swapIndexes( 1 , count );
        reverse[indexes[count]] = 0;
        count --;
        shiftDown(1);

        return ret;
    }
    
    private void shiftDown(int k){
    		
        while( 2*k <= count ){
            int j = 2*k;
            if( j+1 <= count && data[indexes[j+1]].compareTo(data[indexes[j]]) > 0 )
                j ++;

            if( data[indexes[k]].compareTo(data[indexes[j]]) >= 0 )
                break;

            swapIndexes(k, j);
            swapRevs(k,j);
            k = j;
        }
    }
    
    // 交换索引堆中的索引i和j
    private void swapIndexes(int i, int j){
        int t = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = t;
        
//        reverse[indexes[i]] = i;
//        reverse[indexes[j]] = j;
    }
    
    private void swapRevs(int i, int j){
    	
    		int t = reverse[i];
    		reverse[i] = reverse[j];
    		reverse[j] = t;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

//        int N = 10; // 堆中元素个数
//        int M = 10; // 堆中元素取值范围[0, M)
//        for( int i = 0 ; i < N ; i ++ )
//            maxHeap.insert(i,new Integer((int)(Math.random() * M)));

        int a[] = {15,17,19,13,22,16,28};
		MyIndexMaxHeap1<Integer> maxHeap = new MyIndexMaxHeap1<Integer>(a.length);
        for(int i = 0 ; i<a.length ; i++) {
        		maxHeap.insert(i,a[i]);
        }
        
        Integer[] arr = new Integer[a.length];
        // 将maxheap中的数据逐渐使用extractMax取出来
        // 取出来的顺序应该是按照从大到小的顺序取出来的
        for( int i = 0 ; i < a.length ; i ++ ){
            arr[i] = maxHeap.extractMax();
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // 确保arr数组是从大到小排列的
//        for( int i = 1 ; i < N ; i ++ )
//            assert arr[i-1] >= arr[i];
	}

}
