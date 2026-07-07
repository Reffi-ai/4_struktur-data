import java.util.*;

public class GraphProgram {

    // Graph menggunakan Adjacency List
    static HashMap<String, ArrayList<String>> graph = new HashMap<>();

    // =========================
    // TAMBAH VERTEX
    // =========================
    static void tambahVertex(Scanner input) {
        System.out.print("Masukkan nama vertex: ");
        String vertex = input.nextLine();

        if (graph.containsKey(vertex)) {
            System.out.println("Vertex sudah ada!");
        } else {
            graph.put(vertex, new ArrayList<>());
            System.out.println("Vertex " + vertex + " berhasil ditambahkan.");
        }
    }

    // =========================
    // HAPUS VERTEX
    // =========================
    static void hapusVertex(Scanner input) {
        System.out.print("Masukkan vertex yang ingin dihapus: ");
        String vertex = input.nextLine();

        if (!graph.containsKey(vertex)) {
            System.out.println("Vertex tidak ditemukan!");
            return;
        }

        // Hapus vertex
        graph.remove(vertex);

        // Hapus edge yang menuju vertex tersebut
        for (String v : graph.keySet()) {
            graph.get(v).remove(vertex);
        }

        System.out.println("Vertex " + vertex + " berhasil dihapus.");
    }

    // =========================
    // TAMBAH EDGE
    // =========================
    static void tambahEdge(Scanner input) {
        System.out.print("Vertex asal   : ");
        String v1 = input.nextLine();

        System.out.print("Vertex tujuan : ");
        String v2 = input.nextLine();

        if (!graph.containsKey(v1) || !graph.containsKey(v2)) {
            System.out.println("Salah satu vertex tidak ditemukan!");
            return;
        }

        // Undirected Graph
        if (!graph.get(v1).contains(v2)) {
            graph.get(v1).add(v2);
        }

        if (!graph.get(v2).contains(v1)) {
            graph.get(v2).add(v1);
        }

        System.out.println("Edge " + v1 + " -- " + v2 + " berhasil ditambahkan.");
    }

    // =========================
    // HAPUS EDGE
    // =========================
    static void hapusEdge(Scanner input) {
        System.out.print("Vertex asal   : ");
        String v1 = input.nextLine();

        System.out.print("Vertex tujuan : ");
        String v2 = input.nextLine();

        if (!graph.containsKey(v1) || !graph.containsKey(v2)) {
            System.out.println("Vertex tidak ditemukan!");
            return;
        }

        graph.get(v1).remove(v2);
        graph.get(v2).remove(v1);

        System.out.println("Edge " + v1 + " -- " + v2 + " berhasil dihapus.");
    }

    // =========================
    // TAMPILKAN GRAPH
    // =========================
    static void tampilkanGraph() {

        if (graph.isEmpty()) {
            System.out.println("Graph kosong!");
            return;
        }

        // Adjacency List
        System.out.println("\nAdjacency List:");

        for (String vertex : graph.keySet()) {
            System.out.println(vertex + " -> " + graph.get(vertex));
        }

        // Adjacency Matrix
        System.out.println("\nAdjacency Matrix:");

        ArrayList<String> vertices = new ArrayList<>(graph.keySet());

        int width = 12;

        // Header kosong kiri atas
        System.out.printf("%" + width + "s", "");

        // Header nama vertex
        for (String v : vertices) {
            System.out.printf("%" + width + "s", v);
        }

        System.out.println();

        // Isi matrix
        for (String i : vertices) {

            System.out.printf("%" + width + "s", i);

            for (String j : vertices) {

                if (graph.get(i).contains(j)) {
                    System.out.printf("%" + width + "d", 1);
                } else {
                    System.out.printf("%" + width + "d", 0);
                }
            }

            System.out.println();
        }
    }

    // =========================
    // DFS
    // =========================
    static void dfs(String start, HashSet<String> visited) {

        visited.add(start);

        System.out.print(start + " ");

        for (String neighbor : graph.get(start)) {

            if (!visited.contains(neighbor)) {
                dfs(neighbor, visited);
            }
        }
    }

    static void traversalDFS(Scanner input) {

        System.out.print("Masukkan vertex awal DFS: ");
        String start = input.nextLine();

        if (!graph.containsKey(start)) {
            System.out.println("Vertex tidak ditemukan!");
            return;
        }

        System.out.println("Hasil DFS:");

        HashSet<String> visited = new HashSet<>();

        dfs(start, visited);

        System.out.println();
    }

    // =========================
    // BFS
    // =========================
    static void traversalBFS(Scanner input) {

        System.out.print("Masukkan vertex awal BFS: ");
        String start = input.nextLine();

        if (!graph.containsKey(start)) {
            System.out.println("Vertex tidak ditemukan!");
            return;
        }

        System.out.println("Hasil BFS:");

        HashSet<String> visited = new HashSet<>();

        Queue<String> queue = new LinkedList<>();

        visited.add(start);

        queue.add(start);

        while (!queue.isEmpty()) {

            String vertex = queue.poll();

            System.out.print(vertex + " ");

            for (String neighbor : graph.get(vertex)) {

                if (!visited.contains(neighbor)) {

                    visited.add(neighbor);

                    queue.add(neighbor);
                }
            }
        }

        System.out.println();
    }

    // =========================
    // MAIN PROGRAM
    // =========================
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== MENU GRAPH =====");
            System.out.println("1. Tambah Vertex");
            System.out.println("2. Hapus Vertex");
            System.out.println("3. Tambah Edge");
            System.out.println("4. Hapus Edge");
            System.out.println("5. Tampilkan Graph");
            System.out.println("6. Traversal DFS (mendalam)");
            System.out.println("7. Traversal BFS (melebar)");
            System.out.println("8. Quit");

            System.out.print("Pilih menu: ");
            String pilihan = input.nextLine();

            switch (pilihan) {

                case "1":
                    tambahVertex(input);
                    break;

                case "2":
                    hapusVertex(input);
                    break;

                case "3":
                    tambahEdge(input);
                    break;

                case "4":
                    hapusEdge(input);
                    break;

                case "5":
                    tampilkanGraph();
                    break;

                case "6":
                    traversalDFS(input);
                    break;

                case "7":
                    traversalBFS(input);
                    break;

                case "8":
                    System.out.println("Program selesai.");
                    input.close();
                    return;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
}