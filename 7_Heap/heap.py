import heapq
import csv

# ==========================================
# HEAP
# ==========================================

min_heap = []
max_heap = []

# ==========================================
# TAMBAH DATA
# ==========================================

def tambah_data(id_data, nama):

    # Min Heap
    heapq.heappush(min_heap, (id_data, nama))

    # Max Heap
    heapq.heappush(max_heap, (-id_data, nama))

# ==========================================
# CEK DATA DUPLIKAT
# ==========================================

def data_sudah_ada(id_data, nama):

    for data_id, data_nama in min_heap:

        # Cek ID
        if data_id == id_data:

            print("Error: ID sudah ada!")
            return True

        # Cek Nama
        if data_nama.lower() == nama.lower():

            print("Error: Nama sudah ada!")
            return True

    return False

# ==========================================
# TAMPIL ASCENDING
# ==========================================

def tampil_ascending():

    if not min_heap:
        print("Min-Heap kosong.")
        return

    print("\n=== DATA ASCENDING (MIN-HEAP) ===")

    temp = min_heap.copy()

    while temp:
        id_data, nama = heapq.heappop(temp)
        print(f"ID: {id_data} | Nama: {nama}")

# ==========================================
# TAMPIL DESCENDING
# ==========================================

def tampil_descending():

    if not max_heap:
        print("Max-Heap kosong.")
        return

    print("\n=== DATA DESCENDING (MAX-HEAP) ===")

    temp = max_heap.copy()

    while temp:
        id_data, nama = heapq.heappop(temp)
        print(f"ID: {-id_data} | Nama: {nama}")

# ==========================================
# HAPUS DATA MIN-HEAP BERDASARKAN ID
# ==========================================

def hapus_min_heap(id_hapus):

    global min_heap

    ditemukan = False
    temp = []

    while min_heap:

        id_data, nama = heapq.heappop(min_heap)

        if id_data == id_hapus:

            ditemukan = True
            print("\nData berhasil dihapus dari Min-Heap:")
            print(f"ID: {id_data} | Nama: {nama}")

        else:

            temp.append((id_data, nama))

    # Susun ulang heap
    min_heap = temp
    heapq.heapify(min_heap)

    if not ditemukan:
        print("Data tidak ditemukan di Min-Heap.")

# ==========================================
# HAPUS DATA MAX-HEAP BERDASARKAN ID
# ==========================================

def hapus_max_heap(id_hapus):

    global max_heap

    ditemukan = False
    temp = []

    while max_heap:

        id_data, nama = heapq.heappop(max_heap)

        if -id_data == id_hapus:

            ditemukan = True
            print("\nData berhasil dihapus dari Max-Heap:")
            print(f"ID: {-id_data} | Nama: {nama}")

        else:

            temp.append((id_data, nama))

    # Susun ulang heap
    max_heap = temp
    heapq.heapify(max_heap)

    if not ditemukan:
        print("Data tidak ditemukan di Max-Heap.")

# ==========================================
# LOAD CSV
# ==========================================

def load_csv(nama_file):

    with open(nama_file, mode='r', encoding='utf-8') as file:

        reader = csv.reader(file)

        next(reader)

        for row in reader:

            id_data = int(row[0])
            nama = row[1]

            tambah_data(id_data, nama)

    print(f"\nData dari {nama_file} berhasil dimasukkan.")

# ==========================================
# VALIDASI ID
# ==========================================

def input_id():

    while True:

        id_input = input("Masukkan ID: ")

        if id_input.isdigit():

            return int(id_input)

        else:

            print("ID harus berupa angka!")

# ==========================================
# VALIDASI NAMA
# ==========================================

def input_nama():

    while True:

        nama = input("Masukkan Nama: ")

        # Mengizinkan spasi
        if nama.replace(" ", "").isalpha():

            return nama

        else:

            print("Nama hanya boleh huruf!")

# ==========================================
# MENU
# ==========================================

def menu():

    # Load data awal
    # load_csv("data100.csv")
    load_csv("7. Heap - 2/data100.csv")

    while True:

        print("\n=== MENU HEAP ===")
        print("1. Tambah Data")
        print("2. Tampilkan Ascending Menggunakan Min-Heap")
        print("3. Tampilkan Descending Menggunakan Max-Heap")
        print("4. Hapus Data Min-Heap")
        print("5. Hapus Data Max-Heap")
        print("6. Keluar")

        pilihan = input("Pilih menu: ")

        # ==================================
        # TAMBAH DATA
        # ==================================

        if pilihan == '1':

            id_data = input_id()
            nama = input_nama()

            # Cek data duplikat
            if data_sudah_ada(id_data, nama):

                continue

            tambah_data(id_data, nama)

            print("Data berhasil ditambahkan.")

        # ==================================
        # TAMPIL ASCENDING
        # ==================================

        elif pilihan == '2':

            tampil_ascending()

        # ==================================
        # TAMPIL DESCENDING
        # ==================================

        elif pilihan == '3':

            tampil_descending()

        # ==================================
        # HAPUS MIN HEAP
        # ==================================

        elif pilihan == '4':

            id_hapus = input_id()

            hapus_min_heap(id_hapus)

        # ==================================
        # HAPUS MAX HEAP
        # ==================================

        elif pilihan == '5':

            id_hapus = input_id()

            hapus_max_heap(id_hapus)

        # ==================================
        # KELUAR
        # ==================================

        elif pilihan == '6':

            print("Program selesai.")
            break

        else:

            print("Pilihan tidak valid.")

# ==========================================
# MAIN PROGRAM
# ==========================================

menu()