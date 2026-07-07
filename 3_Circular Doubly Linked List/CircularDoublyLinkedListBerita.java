import java.util.Scanner; // digunakan untuk input dari user

// Kelas Node untuk Circular Doubly Linked List
class Node {
    String berita; // menyimpan data berita
    Node next, prev; // pointer ke node berikutnya dan sebelumnya

    // Constructor untuk inisialisasi node
    Node(String berita) {
        this.berita = berita;
        this.next = this.prev = null; // awalnya pointer null
    }
}

public class CircularDoublyLinkedListBerita {
    static Node head = null; // menunjuk ke node pertama
    static int count = 0; // menyimpan jumlah node
    static Scanner input = new Scanner(System.in); // scanner untuk input

    // fungsi untuk menambah berita (insert di akhir)
    static void insertBerita() {
        System.out.print("Masukkan teks berita: ");
        input.nextLine(); // clear buffer (menghindari input kosong)
        String teks = input.nextLine();
        Node baru = new Node(teks); // membuat node baru

        // jika list masih kosong
        if (head == null) {
            head = baru;
            head.next = head; // circular: next ke dirinya sendiri
            head.prev = head; // prev ke dirinya sendiri
        } else {
            Node tail = head.prev; // ambil node terakhir
            tail.next = baru; // node terakhir menunjuk ke node baru
            baru.prev = tail; // node baru menunjuk ke node terakhir
            baru.next = head; // node baru menunjuk ke head
            head.prev = baru; // head menunjuk ke node baru sebagai prev
        }
        count++; // tambah jumlah data
    }

    // fungsi untuk menghapus berita berdasarkan posisi
    static void hapusBerita() {
        if (head == null) {
            System.out.println("Data kosong!");
            return;
        }

        System.out.print("Masukkan nomor berita: ");
        int pos = input.nextInt();

        // validasi posisi
        if (pos < 1 || pos > count) {
            System.out.println("Nomor tidak valid!");
            return;
        }

        // jika hanya ada satu node
        if (count == 1) {
            head = null;
        } else {
            Node bantu = head;
            // mencari node sesuai posisi
            for (int i = 1; i < pos; i++) {
                bantu = bantu.next;
            }

            // menghubungkan node sebelum dan sesudah node yang dihapus
            bantu.prev.next = bantu.next;
            bantu.next.prev = bantu.prev;

            // jika node yang dihapus adalah head
            if (bantu == head) {
                head = bantu.next; // head pindah ke node berikutnya
            }
        }
        count--; // kurangi jumlah data
    }

    static void tampilForward() throws InterruptedException {
        if (head == null) {
            System.out.println("Data kosong!");
            return;
        }

        Node bantu = head;
        int i = 1;
        boolean[] stop = {false}; // flag untuk menghentikan loop

        System.out.println("Tekan ENTER untuk kembali ke menu...\n");

        // thread untuk mendeteksi ENTER
        Thread stopThread = new Thread(() -> {
            new Scanner(System.in).nextLine();
            stop[0] = true; // ubah flag saat ENTER ditekan
        });
        stopThread.start();

        while (!stop[0]) {
            System.out.println(i + ". " + bantu.berita);
            Thread.sleep(3000); // jeda tiap berita

            bantu = bantu.next;
            i++;

            if (i > count) {
                System.out.println(); // spasi antar putaran
                Thread.sleep(3000);
                i = 1;
            }
        }

        System.out.println("\nKembali ke menu...");
    }

    static void tampilBackward() throws InterruptedException {
        if (head == null) {
            System.out.println("Data kosong!");
            return;
        }

        Node bantu = head.prev;
        int i = count;
        boolean[] stop = {false}; // flag untuk menghentikan loop

        System.out.println("Tekan ENTER untuk kembali ke menu...\n");

        Thread stopThread = new Thread(() -> {
            new Scanner(System.in).nextLine();
            stop[0] = true; // ubah flag saat ENTER ditekan
        });
        stopThread.start();

        while (!stop[0]) {
            System.out.println(i + ". " + bantu.berita);
            Thread.sleep(3000); // jeda tiap berita

            bantu = bantu.prev;
            i--;

            if (i < 1) {
                System.out.println(); // spasi antar putaran
                Thread.sleep(3000);
                i = count;
            }
        }

        System.out.println("\nKembali ke menu...");
    }

    // menampilkan satu data berdasarkan posisi
    static void tampilTertentu() {
        if (head == null) {
            System.out.println("Data kosong!");
            return;
        }

        System.out.print("Masukkan nomor berita: ");
        int pos = input.nextInt();

        // validasi posisi
        if (pos < 1 || pos > count) {
            System.out.println("Nomor tidak valid!");
            return;
        }

        Node bantu = head;
        // mencari node sesuai posisi
        for (int i = 1; i < pos; i++) {
            bantu = bantu.next;
        }

        // tampilkan data yang dipilih
        System.out.println(pos + ". " + bantu.berita);
    }

    // program utama
    public static void main(String[] args) throws InterruptedException {
        int pilih;
        do {
            System.out.println("\nMENU");
            System.out.println("1. Insert berita");
            System.out.println("2. Hapus berita");
            System.out.println("3. Tampilkan berita forward");
            System.out.println("4. Tampilkan berita backward");
            System.out.println("5. Tampil berita tertentu");
            System.out.println("6. Exit");
            System.out.print("Pilih: ");
            pilih = input.nextInt();

            // percabangan menu
            switch (pilih) {
                case 1 -> insertBerita(); // tambah data
                case 2 -> hapusBerita(); // hapus data
                case 3 -> tampilForward(); // tampil dari depan
                case 4 -> tampilBackward(); // tampil dari belakang
                case 5 -> tampilTertentu(); // tampil data tertentu
            }
        } while (pilih != 6); // ulang sampai pilih exit
    }
}
