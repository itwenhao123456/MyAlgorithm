package com.itwenhao123.myalgorithm.minimunSpanTree;

import java.util.Vector;

public class LazyPrimMainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        String filename = "/Users/hle2/eclipse-workspace/TestMySort/src/com/itwenhao123/myalgorithm/minimunSpanTree/testG1.txt";
        int V = 8;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<Double>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        // Test Lazy Prim MST
        System.out.println("Test Lazy Prim MST:");
        LazyPrimMST<Double> lazyPrimMST = new LazyPrimMST<Double>(g);
        Vector<Edge<Double>> mst = lazyPrimMST.mstEdges();
        for( int i = 0 ; i < mst.size() ; i ++ )
            System.out.println(mst.elementAt(i));
        System.out.println("The MST weight is: " + lazyPrimMST.result());

        System.out.println();
	}

}
