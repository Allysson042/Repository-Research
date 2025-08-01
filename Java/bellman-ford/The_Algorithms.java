import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class The_Algorithms {


    int vertex;
    int edge;
    private Edge[] edges;
    private int index = 0;

    public The_Algorithms(int v, int e) {
        vertex = v;
        edge = e;
        edges = new Edge[e];
    }

    class Edge {

        int u;
        int v;
        int w;

        /**
         * @param u Source Vertex
         * @param v End vertex
         * @param c Weight
         */
        Edge(int a, int b, int c) {
            u = a;
            v = b;
            w = c;
        }
    }

    /**
     * @param p[] Parent array which shows updates in edges
     * @param i   Current vertex under consideration
     */
    /*void printPath(int[] p, int i) {
        if (p[i] == -1) { // Found the path back to parent
            return;
        }
        printPath(p, p[i]);
        System.out.print(i + " ");
    }*/


    public void go() {
        // shows distance to all vertices
        // Interactive run for understanding the
        // class first time. Assumes source vertex is 0 and
        try (Scanner sc = new Scanner(System.in)) {
            int i;
            int v;
            int e;
            int u;
            int ve;
            int w;
            int j;
            int neg = 0;
            //System.out.println("Enter no. of vertices and edges please");
            v = sc.nextInt();
            e = sc.nextInt();
            Edge[] arr = new Edge[e]; // Array of edges
            //System.out.println("Input edges");
            for (i = 0; i < e; i++) {
                u = sc.nextInt();
                ve = sc.nextInt();
                w = sc.nextInt();
                arr[i] = new Edge(u, ve, w);
            }
            int[] dist = new int[v]; // Distance array for holding the finalized shortest path distance
                                     // between source
            // and all vertices
            int[] p = new int[v]; // Parent array for holding the paths
            for (i = 0; i < v; i++) {
                dist[i] = Integer.MAX_VALUE; // Initializing distance values
            }
            dist[0] = 0;
            p[0] = -1;
            for (i = 0; i < v - 1; i++) {
                for (j = 0; j < e; j++) {
                    if (dist[arr[j].u] != Integer.MAX_VALUE && dist[arr[j].v] > dist[arr[j].u] + arr[j].w) {
                        dist[arr[j].v] = dist[arr[j].u] + arr[j].w; // Update
                        p[arr[j].v] = arr[j].u;
                    }
                }
            }
            // Final cycle for negative checking
            for (j = 0; j < e; j++) {
                if (dist[arr[j].u] != Integer.MAX_VALUE && dist[arr[j].v] > dist[arr[j].u] + arr[j].w) {
                    neg = 1;
                    //System.out.println("Negative cycle");
                    break;
                }
            }
            if (neg == 0) { // Go ahead and show results of computation
                //System.out.println("Distances are: ");
                for (i = 0; i < v; i++) {
                    System.out.println(i + " " + dist[i]);
                }
                //System.out.println("Path followed:");
                for (i = 0; i < v; i++) {
                    //System.out.print("0 ");
                    //printPath(p, i);
                    //System.out.println();
                }
            }
        }
    }

    /**
     * @param source Starting vertex
     * @param end    Ending vertex
     * @param Edge   Array of edges
     */
    public void show(int source, int end,
        Edge[] arr) { // be created by using addEdge() method and passed by calling getEdgeArray()
                      // method // Just shows results of computation, if graph is passed to it. The
                      // graph should
        int i;
        int j;
        int v = vertex;
        int e = edge;
        int neg = 0;
        double[] dist = new double[v]; // Distance array for holding the finalized shortest path
                                       // distance between source
        // and all vertices
        int[] p = new int[v]; // Parent array for holding the paths
        for (i = 0; i < v; i++) {
            dist[i] = Integer.MAX_VALUE; // Initializing distance values
        }
        dist[source] = 0;
        p[source] = -1;
        for (i = 0; i < v - 1; i++) {
            for (j = 0; j < e; j++) {
                if ((int) dist[arr[j].u] != Integer.MAX_VALUE && dist[arr[j].v] > dist[arr[j].u] + arr[j].w) {
                    dist[arr[j].v] = dist[arr[j].u] + arr[j].w; // Update
                    p[arr[j].v] = arr[j].u;
                }
            }
        }
        // Final cycle for negative checking
        for (j = 0; j < e; j++) {
            if ((int) dist[arr[j].u] != Integer.MAX_VALUE && dist[arr[j].v] > dist[arr[j].u] + arr[j].w) {
                neg = 1;
                //System.out.println("Negative cycle");
                break;
            }
        }
        if (neg == 0) { // Go ahead and show results of computaion
            //System.out.println("Distance is: " + dist[end]);
            //System.out.println("Path followed:");
            //System.out.print(source + " ");
            //printPath(p, end);
            //System.out.println();
        }
    }

    /**
     * @param x Source Vertex
     * @param y End vertex
     * @param z Weight
     */
    public void addEdge(int x, int y, int z) { // Adds unidirectional edge
        edges[index++] = new Edge(x, y, z);
    }

    public Edge[] getEdgeArray() {
        return edges;
    }

    public static void main(String[] args) {
        final int V = 1000; // Número total de vértices
        int edgeCount = 0;
        List<String> lines = new ArrayList<>();

        // Lê as arestas do arquivo e armazena em uma lista temporária
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("../../graph_input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length != 3) continue;
                lines.add(line);
                edgeCount++;
            }
        } catch (java.io.IOException e) {
            return;
        }

        The_Algorithms algo = new The_Algorithms(V, edgeCount);

        // Adiciona as arestas lidas no objeto The_Algorithms
        for (String l : lines) {
            String[] parts = l.trim().split("\\s+");
            int src = Integer.parseInt(parts[0]);
            int dest = Integer.parseInt(parts[1]);
            int weight = Integer.parseInt(parts[2]);
            algo.addEdge(src, dest, weight);
        }

        // Executa o algoritmo a partir do vértice 0 até todos os outros
        algo.show(0, 10, algo.getEdgeArray()); // Exemplo: mostra caminho de 0 até 10
    }

}
