package com.itwenhao123.myalgorithm.graph;

public class MyComponents {

    Graph G;                    // 图的引用
    private boolean[] visited;  // 记录dfs的过程中节点是否被访问
    private int ccount;         // 记录联通分量个数
    private int[] id;           // 每个节点所对应的联通分量标记
	
    // 构造函数, 求出无权图的联通分量
    public MyComponents(Graph graph){

        // 算法初始化
        G = graph;
        visited = new boolean[G.V()];
        id = new int[G.V()];
        ccount = 0;
        for( int i = 0 ; i < G.V() ; i ++ ){
            visited[i] = false;
            id[i] = -1;
        }

        // 求图的联通分量
        for( int i = 0 ; i < G.V() ; i ++ )
            if( !visited[i] ){
                dfs(i);
                ccount ++;
            }
    }
    
 // 图的深度优先遍历
    void dfs( int v ){

        visited[v] = true;
        id[v] = ccount;

        for( int i: G.adj(v) ){
            if( !visited[i] )
                dfs(i);
        }
    }
    
 // 返回图的联通分量个数
    int count(){
        return ccount;
    }

    // 查询点v和点w是否联通
    boolean isConnected( int v , int w ){
        assert v >= 0 && v < G.V();
        assert w >= 0 && w < G.V();
        return id[v] == id[w];
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// TestG1.txt
        String filename1 = "/Users/hle2/eclipse-workspace/TestMySort/src/com/itwenhao123/myalgorithm/graph/testG1.txt";
        MySparseGraph g1 = new MySparseGraph(13, false);
        ReadGraph readGraph1 = new ReadGraph(g1, filename1);
        MyComponents component1 = new MyComponents(g1);
        System.out.println("TestG1.txt, Component Count: " + component1.count());
        System.out.println();

        // TestG2.txt
        String filename2 = "/Users/hle2/eclipse-workspace/TestMySort/src/com/itwenhao123/myalgorithm/graph/testG2.txt";
        MySparseGraph g2 = new MySparseGraph(6, false);
        ReadGraph readGraph2 = new ReadGraph(g2, filename2);
        MyComponents component2 = new MyComponents(g2);
        System.out.println("TestG2.txt, Component Count: " + component2.count());
	}

}
