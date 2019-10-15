package com.itwenhao123.myalgorithm.graph;

public class TestGraphMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 使用两种图的存储方式读取testG1.txt文件
        String filename = "/Users/hle2/eclipse-workspace/TestMySort/src/com/itwenhao123/myalgorithm/graph/testG1.txt";
        MySparseGraph g1 = new MySparseGraph(13, false);
        ReadGraph readGraph1 = new ReadGraph(g1, filename);
        System.out.println("test G1 in Sparse Graph:");
        g1.show();

        System.out.println();

        MyDenseGraph g2 = new MyDenseGraph(13, false);
        ReadGraph readGraph2 = new ReadGraph(g2 , filename );
        System.out.println("test G1 in Dense Graph:");
        g2.show();

        System.out.println();

        // 使用两种图的存储方式读取testG2.txt文件
        filename = "/Users/hle2/eclipse-workspace/TestMySort/src/com/itwenhao123/myalgorithm/graph/testG2.txt";
        MySparseGraph g3 = new MySparseGraph(6, false);
        ReadGraph readGraph3 = new ReadGraph(g3, filename);
        System.out.println("test G2 in Sparse Graph:");
        g3.show();

        System.out.println();

        MyDenseGraph g4 = new MyDenseGraph(6, false);
        ReadGraph readGraph4 = new ReadGraph(g4, filename);
        System.out.println("test G2 in Dense Graph:");
        g4.show();
	}

}
