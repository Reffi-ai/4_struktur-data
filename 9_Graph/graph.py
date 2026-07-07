from collections import deque

# Graph menggunakan adjacency list
graph = {}


# =========================
# FUNGSI GRAPH
# =========================

def tambah_vertex():
    vertex = input("Masukkan nama vertex: ")

    if vertex in graph:
        print("Vertex sudah ada!")
    else:
        graph[vertex] = []
        print(f"Vertex {vertex} berhasil ditambahkan.")


def hapus_vertex():
    vertex = input("Masukkan vertex yang ingin dihapus: ")

    if vertex not in graph:
        print("Vertex tidak ditemukan!")
        return

    # Hapus vertex dari graph
    graph.pop(vertex)

    # Hapus semua edge yang menuju vertex tersebut
    for v in graph:
        if vertex in graph[v]:
            graph[v].remove(vertex)

    print(f"Vertex {vertex} berhasil dihapus.")


def tambah_edge():
    v1 = input("Vertex asal : ")
    v2 = input("Vertex tujuan: ")

    if v1 not in graph or v2 not in graph:
        print("Salah satu vertex tidak ditemukan!")
        return

    # Undirected graph
    if v2 not in graph[v1]:
        graph[v1].append(v2)

    if v1 not in graph[v2]:
        graph[v2].append(v1)

    print(f"Edge {v1} -- {v2} berhasil ditambahkan.")


def hapus_edge():
    v1 = input("Vertex asal : ")
    v2 = input("Vertex tujuan: ")

    if v1 not in graph or v2 not in graph:
        print("Vertex tidak ditemukan!")
        return

    if v2 in graph[v1]:
        graph[v1].remove(v2)

    if v1 in graph[v2]:
        graph[v2].remove(v1)

    print(f"Edge {v1} -- {v2} berhasil dihapus.")


# =========================
# TAMPILKAN GRAPH
# =========================

def tampilkan_graph():
    if not graph:
        print("Graph kosong!")
        return

    print("\nAdjacency List:")
    for vertex in graph:
        print(f"{vertex} -> {graph[vertex]}")

    print("\nAdjacency Matrix:")

    vertices = list(graph.keys())
    width = 12  # lebar kolom

    # Header
    print("".center(width), end="")

    for v in vertices:
        print(f"{v:^{width}}", end="")

    print()

    # Isi matrix
    for i in vertices:
        print(f"{i:^{width}}", end="")

        for j in vertices:
            if j in graph[i]:
                print(f"{1:^{width}}", end="")
            else:
                print(f"{0:^{width}}", end="")

        print()

# =========================
# DFS
# =========================

def dfs(start):
    visited = set()

    def dfs_recursive(vertex):
        visited.add(vertex)
        print(vertex, end=" ")

        for neighbor in graph[vertex]:
            if neighbor not in visited:
                dfs_recursive(neighbor)

    dfs_recursive(start)
    print()


def traversal_dfs():
    start = input("Masukkan vertex awal DFS: ")

    if start not in graph:
        print("Vertex tidak ditemukan!")
        return

    print("Hasil DFS:")
    dfs(start)


# =========================
# BFS
# =========================

def bfs(start):
    visited = set()
    queue = deque()

    visited.add(start)
    queue.append(start)

    while queue:
        vertex = queue.popleft()
        print(vertex, end=" ")

        for neighbor in graph[vertex]:
            if neighbor not in visited:
                visited.add(neighbor)
                queue.append(neighbor)

    print()


def traversal_bfs():
    start = input("Masukkan vertex awal BFS: ")

    if start not in graph:
        print("Vertex tidak ditemukan!")
        return

    print("Hasil BFS:")
    bfs(start)


# =========================
# MENU
# =========================

while True:
    print("\n===== MENU GRAPH =====")
    print("1. Tambah Vertex")
    print("2. Hapus Vertex")
    print("3. Tambah Edge")
    print("4. Hapus Edge")
    print("5. Tampilkan Graph")
    print("6. Traversal DFS")
    print("7. Traversal BFS")
    print("8. Quit")

    pilihan = input("Pilih menu: ")

    if pilihan == "1":
        tambah_vertex()

    elif pilihan == "2":
        hapus_vertex()

    elif pilihan == "3":
        tambah_edge()

    elif pilihan == "4":
        hapus_edge()

    elif pilihan == "5":
        tampilkan_graph()

    elif pilihan == "6":
        traversal_dfs()

    elif pilihan == "7":
        traversal_bfs()

    elif pilihan == "8":
        print("Program selesai.")
        break

    else:
        print("Pilihan tidak valid!")