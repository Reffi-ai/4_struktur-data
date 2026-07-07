import time  # digunakan untuk memberikan delay (jeda waktu)

# Kelas Node untuk Circular Doubly Linked List
class Node:
    def __init__(self, berita):
        self.berita = berita   # menyimpan isi data (teks berita)
        self.next = None       # pointer ke node berikutnya
        self.prev = None       # pointer ke node sebelumnya

# variabel global
head = None   # menunjuk ke node pertama
count = 0     # jumlah node/data

# fungsi untuk menambah berita (insert di akhir)
def insert_berita():
    global head, count
    teks = input("Masukkan teks berita: ")
    baru = Node(teks)  # membuat node baru

    # jika list masih kosong
    if head is None:
        head = baru
        head.next = head   # menunjuk ke dirinya sendiri (circular)
        head.prev = head
    else:
        tail = head.prev   # mengambil node terakhir
        tail.next = baru   # node terakhir menunjuk ke node baru
        baru.prev = tail   # node baru menunjuk ke node terakhir
        baru.next = head   # node baru menunjuk ke head
        head.prev = baru   # head menunjuk ke node baru sebagai prev
    count += 1  # menambah jumlah data

# fungsi untuk menghapus berita berdasarkan posisi
def hapus_berita():
    global head, count
    if head is None:
        print("Data kosong!")
        return

    pos = int(input("Masukkan nomor berita: "))
    # validasi posisi
    if pos < 1 or pos > count:
        print("Nomor tidak valid!")
        return

    # jika hanya ada satu data
    if count == 1:
        head = None
    else:
        bantu = head
        # mencari node sesuai posisi
        for _ in range(pos - 1):
            bantu = bantu.next

        # menghubungkan node sebelum dan sesudah node yang dihapus
        bantu.prev.next = bantu.next
        bantu.next.prev = bantu.prev

        # jika yang dihapus adalah head
        if bantu == head:
            head = bantu.next  # head pindah ke node berikutnya
    count -= 1  # mengurangi jumlah data

import threading

def tampil_forward():
    if head is None:
        print("Data kosong!")
        return

    bantu = head
    i = 1
    stop = False  # flag

    print("Tekan ENTER untuk kembali ke menu...\n")

    # thread untuk mendeteksi ENTER
    def tunggu_enter():
        nonlocal stop
        input()
        stop = True

    threading.Thread(target=tunggu_enter, daemon=True).start()

    while not stop:
        print(f"{i}. {bantu.berita}")
        time.sleep(3)

        bantu = bantu.next
        i += 1

        if i > count:
            print()
            time.sleep(3)
            i = 1

    print("\nKembali ke menu...")

import threading

def tampil_backward():
    if head is None:
        print("Data kosong!")
        return

    bantu = head.prev
    i = count
    stop = False  # flag

    print("Tekan ENTER untuk kembali ke menu...\n")

    def tunggu_enter():
        nonlocal stop
        input()
        stop = True

    threading.Thread(target=tunggu_enter, daemon=True).start()

    while not stop:
        print(f"{i}. {bantu.berita}")
        time.sleep(3)

        bantu = bantu.prev
        i -= 1

        if i < 1:
            print()
            time.sleep(3)
            i = count

    print("\nKembali ke menu...")

# menampilkan satu data berdasarkan posisi
def tampil_tertentu():
    if head is None:
        print("Data kosong!")
        return

    pos = int(input("Masukkan nomor berita: "))
    # validasi posisi
    if pos < 1 or pos > count:
        print("Nomor tidak valid!")
        return

    bantu = head
    # mencari node sesuai posisi
    for _ in range(pos - 1):
        bantu = bantu.next

    # menampilkan data yang dipilih
    print(f"{pos}. {bantu.berita}")

# program utama (menu)
while True:
    print("\nMENU")
    print("1. Insert berita")
    print("2. Hapus berita")
    print("3. Tampilkan berita forward")
    print("4. Tampilkan berita backward")
    print("5. Tampil berita tertentu")
    print("6. Exit")

    pilih = int(input("Pilih: "))

    # percabangan menu
    if pilih == 1:
        insert_berita()
    elif pilih == 2:
        hapus_berita()
    elif pilih == 3:
        tampil_forward()
    elif pilih == 4:
        tampil_backward()
    elif pilih == 5:
        tampil_tertentu()
    elif pilih == 6:
        break  # keluar dari program
