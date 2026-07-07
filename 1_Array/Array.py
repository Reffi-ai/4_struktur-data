MAX = 10  # kapasitas maksimum array (maksimal 10 data)
data = [[None, None] for _ in range(MAX)]  # array 2D: [NIM, Nama]
count = 0  # jumlah data yang sedang tersimpan

# fungsi untuk mengecek apakah NIM atau Nama sudah ada
def is_duplicate(nim, nama):
    for i in range(count):
        if data[i][0] == nim or data[i][1] == nama:
            return True
    return False

# fungsi validasi NIM (harus angka)
def is_valid_nim(nim):
    return nim.isdigit()

# fungsi validasi Nama (harus huruf/spasi)
def is_valid_nama(nama):
    return nama.replace(" ", "").isalpha()


def insert_beginning():
    global count
    if count == MAX:
        print("Array penuh!")
        return

    # geser data ke kanan
    for i in range(count, 0, -1):
        data[i][0] = data[i-1][0]
        data[i][1] = data[i-1][1]

    # input dengan validasi lengkap
    while True:
        nim = input("Masukkan NIM: ")
        nama = input("Masukkan Nama: ")

        if not is_valid_nim(nim):
            print("NIM harus berupa angka!")
        elif not is_valid_nama(nama):
            print("Nama harus berupa huruf!")
        elif is_duplicate(nim, nama):
            print("NIM atau Nama sudah ada!")
        else:
            break

    data[0][0] = nim
    data[0][1] = nama
    count += 1


def insert_position():
    global count
    if count == MAX:
        print("Array penuh!")
        return

    pos = int(input("Masukkan posisi: ")) - 1
    if pos < 0 or pos > count:
        print("Posisi tidak valid!")
        return

    # geser data ke kanan
    for i in range(count, pos, -1):
        data[i][0] = data[i-1][0]
        data[i][1] = data[i-1][1]

    # input dengan validasi
    while True:
        nim = input("Masukkan NIM: ")
        nama = input("Masukkan Nama: ")

        if not is_valid_nim(nim):
            print("NIM harus berupa angka!")
        elif not is_valid_nama(nama):
            print("Nama harus berupa huruf!")
        elif is_duplicate(nim, nama):
            print("NIM atau Nama sudah ada!")
        else:
            break

    data[pos][0] = nim
    data[pos][1] = nama
    count += 1


def insert_end():
    global count
    if count == MAX:
        print("Array penuh!")
        return

    # input dengan validasi
    while True:
        nim = input("Masukkan NIM: ")
        nama = input("Masukkan Nama: ")

        if not is_valid_nim(nim):
            print("NIM harus berupa angka!")
        elif not is_valid_nama(nama):
            print("Nama harus berupa huruf!")
        elif is_duplicate(nim, nama):
            print("NIM atau Nama sudah ada!")
        else:
            break

    data[count][0] = nim
    data[count][1] = nama
    count += 1


def delete_beginning():
    global count
    if count == 0:
        print("Data kosong!")
        return

    # geser ke kiri
    for i in range(count - 1):
        data[i][0] = data[i+1][0]
        data[i][1] = data[i+1][1]

    count -= 1


def delete_position():
    global count
    if count == 0:
        print("Data kosong!")
        return

    pos = int(input("Masukkan posisi: ")) - 1
    if pos < 0 or pos >= count:
        print("Posisi tidak valid!")
        return

    # geser ke kiri
    for i in range(pos, count - 1):
        data[i][0] = data[i+1][0]
        data[i][1] = data[i+1][1]

    count -= 1


def delete_end():
    global count
    if count == 0:
        print("Data kosong!")
        return

    count -= 1


def delete_first_occurrence():
    global count
    nim = input("Masukkan NIM yang dihapus: ")

    for i in range(count):
        if data[i][0] == nim:
            # geser ke kiri
            for j in range(i, count - 1):
                data[j][0] = data[j+1][0]
                data[j][1] = data[j+1][1]
            count -= 1
            return

    print("Data tidak ditemukan!")


def show_data():
    if count == 0:
        print("Data kosong!")
        return

    for i in range(count):
        print(f"nim mhs#{i+1} : {data[i][0]}")
        print(f"nama mhs#{i+1} : {data[i][1]}")
        print()


# program utama
while True:
    print("\nMENU")
    print("1. Insert at beginning")
    print("2. Insert at given position")
    print("3. Insert at end")
    print("4. Delete from beginning")
    print("5. Delete given position")
    print("6. Delete from end")
    print("7. Delete first occurrence")
    print("8. Show data")
    print("9. Exit")

    pilih = int(input("Pilih: "))

    if pilih == 1:
        insert_beginning()
    elif pilih == 2:
        insert_position()
    elif pilih == 3:
        insert_end()
    elif pilih == 4:
        delete_beginning()
    elif pilih == 5:
        delete_position()
    elif pilih == 6:
        delete_end()
    elif pilih == 7:
        delete_first_occurrence()
    elif pilih == 8:
        show_data()
    elif pilih == 9:
        break