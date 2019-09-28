package com.itwenhao123.myalgorithm.bst;

public class MyBinarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int N = 1000;
        Integer[] arr = new Integer[N];
        for(int i = 0 ; i < N ; i ++)
            arr[i] = new Integer(i);

        Comparable target =  MyBinarySearch.MyFind(arr,55);
        System.out.println(target);
	}

	// 二分查找法,在有序数组arr中,查找target
    // 如果找到target,返回相应的索引index
    // 如果没有找到target,返回-1
	public static Comparable MyFind (Comparable[] arr, Comparable target) {
		
		// 在arr[l...r]之中查找target
        int l = 0, r = arr.length-1;
        
        while(l<=r) {
        		int mid = (l+r) / 2 ;
        		if(arr[mid].compareTo(target) == 0) {
        			return arr[mid];
        		}
        		 if( arr[mid].compareTo(target) > 0 )
                     r = mid - 1;
                 else
                     l = mid + 1;
        }
        return -1;
	}
}
