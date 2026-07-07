import java.io.*;
import java.util.*;

public class HeapProgram {

    // ==========================================
    // MIN HEAP & MAX HEAP
    // ==========================================

    static PriorityQueue<Data> minHeap =
            new PriorityQueue<>(Comparator.comparingInt(d -> d.id));

    static PriorityQueue<Data> maxHeap =
            new PriorityQueue<>((a, b) -> b.id - a.id);

    static Scanner input = new Scanner(System.in);

    // ==========================================
    // CLASS DATA
    // ==========================================

    static class Data {

        int id;
        String nama;

        Data(int id, String nama) {
            this.id = id;
            this.nama = nama;
        }
    }

    // ==========================================
    // TAMBAH DATA
    // ==========================================

    static void tambahData(int id, String nama) {

        Data dataBaru = new Data(id, nama);

        minHeap.add(dataBaru);
        maxHeap.add(dataBaru);
    }

    // ==========================================
    // CEK DATA DUPLIKAT
    // ==========================================

    static boolean dataSudahAda(int id, String nama) {

        for (Data data : minHeap) {

            // Cek ID
            if (data.id == id) {

                System.out.println(
                        "Error: ID sudah ada!"
                );

                return true;
            }

            // Cek Nama
            if (data.nama.equalsIgnoreCase(nama)) {

                System.out.println(
                        "Error: Nama sudah ada!"
                );

                return true;
            }
        }

        return false;
    }

    // ==========================================
    // TAMPIL ASCENDING
    // ==========================================

    static void tampilAscending() {

        if (minHeap.isEmpty()) {

            System.out.println("Min-Heap kosong.");
            return;
        }

        System.out.println("\n=== DATA ASCENDING (MIN-HEAP) ===");

        PriorityQueue<Data> temp =
                new PriorityQueue<>(minHeap);

        while (!temp.isEmpty()) {

            Data data = temp.poll();

            System.out.println(
                    "ID: " + data.id +
                    " | Nama: " + data.nama
            );
        }
    }

    // ==========================================
    // TAMPIL DESCENDING
    // ==========================================

    static void tampilDescending() {

        if (maxHeap.isEmpty()) {

            System.out.println("Max-Heap kosong.");
            return;
        }

        System.out.println("\n=== DATA DESCENDING (MAX-HEAP) ===");

        PriorityQueue<Data> temp =
                new PriorityQueue<>(maxHeap);

        while (!temp.isEmpty()) {

            Data data = temp.poll();

            System.out.println(
                    "ID: " + data.id +
                    " | Nama: " + data.nama
            );
        }
    }

    // ==========================================
    // HAPUS MIN HEAP
    // ==========================================

    static void hapusMinHeap(int idHapus) {

        boolean ditemukan = false;

        PriorityQueue<Data> temp =
                new PriorityQueue<>(Comparator.comparingInt(d -> d.id));

        while (!minHeap.isEmpty()) {

            Data data = minHeap.poll();

            if (data.id == idHapus) {

                ditemukan = true;

                System.out.println("\nData berhasil dihapus dari Min-Heap:");
                System.out.println(
                        "ID: " + data.id +
                        " | Nama: " + data.nama
                );

            } else {

                temp.add(data);
            }
        }

        minHeap = temp;

        if (!ditemukan) {

            System.out.println("Data tidak ditemukan di Min-Heap.");
        }
    }

    // ==========================================
    // HAPUS MAX HEAP
    // ==========================================

    static void hapusMaxHeap(int idHapus) {

        boolean ditemukan = false;

        PriorityQueue<Data> temp =
                new PriorityQueue<>((a, b) -> b.id - a.id);

        while (!maxHeap.isEmpty()) {

            Data data = maxHeap.poll();

            if (data.id == idHapus) {

                ditemukan = true;

                System.out.println("\nData berhasil dihapus dari Max-Heap:");
                System.out.println(
                        "ID: " + data.id +
                        " | Nama: " + data.nama
                );

            } else {

                temp.add(data);
            }
        }

        maxHeap = temp;

        if (!ditemukan) {

            System.out.println("Data tidak ditemukan di Max-Heap.");
        }
    }

    // ==========================================
    // LOAD CSV
    // ==========================================

    static void loadCSV(String namaFile) {

        try {

            BufferedReader br =
                    new BufferedReader(new FileReader(namaFile));

            String baris;

            // Skip header
            br.readLine();

            while ((baris = br.readLine()) != null) {

                String[] data = baris.split(",");

                int id = Integer.parseInt(data[0]);
                String nama = data[1];

                tambahData(id, nama);
            }

            br.close();

            System.out.println(
                    "\nData dari " + namaFile +
                    " berhasil dimasukkan."
            );

        } catch (Exception e) {

            System.out.println(
                    "Terjadi kesalahan membaca file."
            );
        }
    }

    // ==========================================
    // VALIDASI ID
    // ==========================================

    static int inputID() {

        while (true) {

            System.out.print("Masukkan ID: ");

            String idInput = input.nextLine();

            if (idInput.matches("\\d+")) {

                return Integer.parseInt(idInput);

            } else {

                System.out.println(
                        "ID harus berupa angka!"
                );
            }
        }
    }

    // ==========================================
    // VALIDASI NAMA
    // ==========================================

    static String inputNama() {

        while (true) {

            System.out.print("Masukkan Nama: ");

            String nama = input.nextLine();

            if (nama.matches("[a-zA-Z ]+")) {

                return nama;

            } else {

                System.out.println(
                        "Nama hanya boleh huruf!"
                );
            }
        }
    }

    // ==========================================
    // MENU
    // ==========================================

    static void menu() {

        // Load data awal
        // loadCSV("data100.csv");
        loadCSV("7. Heap - 2/data100.csv");

        while (true) {

            System.out.println("\n=== MENU HEAP ===");
            System.out.println("1. Tambah Data");
            System.out.println("2. Tampilkan Ascending Menggunakan Min-Heap");
            System.out.println("3. Tampilkan Descending Menggunakan Max-Heap");
            System.out.println("4. Hapus Data Min-Heap");
            System.out.println("5. Hapus Data Max-Heap");
            System.out.println("6. Keluar");

            System.out.print("Pilih menu: ");
            String pilihan = input.nextLine();

            // ==================================
            // TAMBAH DATA
            // ==================================

            if (pilihan.equals("1")) {

                int id = inputID();
                String nama = inputNama();

                // Cek data duplikat
                if (dataSudahAda(id, nama)) {
                    
                    continue;
                }

                tambahData(id, nama);

                System.out.println(
                        "Data berhasil ditambahkan."
                );
            }

            // ==================================
            // ASCENDING
            // ==================================

            else if (pilihan.equals("2")) {

                tampilAscending();
            }

            // ==================================
            // DESCENDING
            // ==================================

            else if (pilihan.equals("3")) {

                tampilDescending();
            }

            // ==================================
            // HAPUS MIN HEAP
            // ==================================

            else if (pilihan.equals("4")) {

                int idHapus = inputID();

                hapusMinHeap(idHapus);
            }

            // ==================================
            // HAPUS MAX HEAP
            // ==================================

            else if (pilihan.equals("5")) {

                int idHapus = inputID();

                hapusMaxHeap(idHapus);
            }

            // ==================================
            // KELUAR
            // ==================================

            else if (pilihan.equals("6")) {

                System.out.println("Program selesai.");
                break;
            }

            else {

                System.out.println(
                        "Pilihan tidak valid."
                );
            }
        }
    }

    // ==========================================
    // MAIN
    // ==========================================

    public static void main(String[] args) {

        menu();
    }
}