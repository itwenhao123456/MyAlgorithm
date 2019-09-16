package com.wen.sort;

import java.util.*;

public class MyLRUCacheTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LRUCache<Integer , Boolean> map = new LRUCache<Integer , Boolean>(10);
		for(int i = 0; i < 20; i++) {
			   map.put(i, true);
			   System.out.println(map.size());
			   System.out.println(map);
		}
	}

}

/*
 * 	这种方式最为简单，最为直接，缺点是效率一般，
 *  而且没有做synchronized 处理，LinkedHashMap本身就是线程不安全的
 * 
 * */

class LRUCache<K,V> extends LinkedHashMap<K,V>{
	
	private static final long serialVersionUID = 1L;
	private final int MAX_CACHE_SIZE;	//这里是自定义最大的容量
	
	public LRUCache(int cacheSize){
		super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);	//这里是为了使容器达到要求不自动扩容，所以加了个防止扩容的偏移
		MAX_CACHE_SIZE = cacheSize;
	}
	
	@Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {			//这里复写LinkedHashMap提供的removeEldestEntry方法来作为自定义LRUCcahe的策略
        return size() > MAX_CACHE_SIZE;
    }
}
