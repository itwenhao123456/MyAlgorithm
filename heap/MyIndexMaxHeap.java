package com.itwenhao123.myalgorithm.heap;

public class MyIndexMaxHeap<Item extends Comparable> {

	private Item[] data;
    private int count;
    private int capacity;
    private int indexes[];
	
	public MyIndexMaxHeap(int capacity) {
		data = (Item[])new Comparable[capacity+1];
        count = 0;
        this.capacity = capacity;
        indexes = new int[capacity+1];
	}
	
	// 返回堆中的元素个数
    public int size(){
        return count;
    }

    // 返回一个布尔值, 表示堆中是否为空
    public boolean isEmpty(){
        return count == 0;
    }
	
    public void insert(int i , Item item) {
    		assert count + 1 <= capacity;
    		assert i + 1 >= 1 && i + 1 <= capacity;
    		i += 1;
    		data[count + 1] = item;
    		//indexs[i+1] = i + 1;
    		indexes[count+1] = i;
    		count ++;
    		shiftUp(count);
    }
    
    private void shiftUp(int k) {
    	while( k > 1 && data[indexes[k/2]].compareTo(data[indexes[k]]) < 0 ){
            swapIndexes(k, k/2);
            k /= 2;
        }
    }
    
 // 获取最大堆中的堆顶元素
    public Item getMax(){
        assert( count > 0 );
        return data[1];
    }
    
 // 从最大堆中取出堆顶元素, 即堆中所存储的最大数据
    public Item extractMax(){
        assert count > 0;
        Item ret = data[indexes[1]];
        swapIndexes( 1 , count );
        count --;
        shiftDown(1);

        return ret;
    }
    
    // 从最大索引堆中取出堆顶元素的索引
    public Item extractMaxIndex(){
        assert count > 0;

        Item ret = data[indexes[1]];
        swapIndexes( 1 , count );
        count --;
        shiftDown(1);

        return ret;
    }
    
    private void shiftDown(int k){
    		
        while( 2*k <= count ){
            int j = 2*k; // 在此轮循环中,data[k]和data[j]交换位置
            if( j+1 <= count && data[indexes[j+1]].compareTo(data[indexes[j]]) > 0 )
                j ++;
            // data[j] 是 data[2*k]和data[2*k+1]中的最大值

            if( data[indexes[k]].compareTo(data[indexes[j]]) >= 0 ) break;
            swapIndexes(indexes[k], indexes[j]);
            k = j;
        }
    }
    
    // 交换索引堆中的索引i和j
    private void swapIndexes(int i, int j){
        int t = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = t;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyIndexMaxHeap<Integer> maxHeap = new MyIndexMaxHeap<Integer>(100);
        int N = 100; // 堆中元素个数
        int M = 100; // 堆中元素取值范围[0, M)
        for( int i = 0 ; i < N ; i ++ )
            maxHeap.insert(i,new Integer((int)(Math.random() * M)));

        Integer[] arr = new Integer[N];
        // 将maxheap中的数据逐渐使用extractMax取出来
        // 取出来的顺序应该是按照从大到小的顺序取出来的
        for( int i = 0 ; i < N ; i ++ ){
            arr[i] = maxHeap.extractMax();
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // 确保arr数组是从大到小排列的
        for( int i = 1 ; i < N ; i ++ )
            assert arr[i-1] >= arr[i];
	}

}
