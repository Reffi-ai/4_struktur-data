import java.util.Scanner; // digunakan untuk mengambil input dari pengguna (keyboard) di program Java

public class ArrayMahasiswa {
    static final int MAX = 10; // kapasitas maksimum array
    static String[][] data = new String[MAX][2]; // [NIM, Nama]
    static int count = 0; // jumlah data aktif

    static Scanner input = new Scanner(System.in);

    // fungsi cek duplikasi NIM atau Nama
    static boolean isDuplicate(String nim, String nama) {
        for (int i = 0; i < count; i++) {
            if (data[i][0].equals(nim) || data[i][1].equals(nama)) {
                return true;
            }
        }
        return false;
    }

    // validasi NIM harus angka
    static boolean isValidNim(String nim) {
        return nim.matches("\\d+"); // hanya angka
    }

    // validasi Nama harus huruf
    static boolean isValidNama(String nama) {
        return nama.matches("[a-zA-Z ]+"); // huruf dan spasi
    }

    static void insertBeginning() {
        if (count == MAX) {
            System.out.println("Array penuh!");
            return;
        }

        // geser data ke kanan
        for (int i = count; i > 0; i--) {
            data[i][0] = data[i - 1][0];
            data[i][1] = data[i - 1][1];
        }

        String nim, nama;

        // input + validasi
        while (true) {
            System.out.print("Masukkan NIM: ");
            nim = input.next();
            System.out.print("Masukkan Nama: ");
            nama = input.next();

            if (!isValidNim(nim)) {
                System.out.println("NIM harus angka!");
            } else if (!isValidNama(nama)) {
                System.out.println("Nama harus huruf!");
            } else if (isDuplicate(nim, nama)) {
                System.out.println("NIM atau Nama sudah ada!");
            } else {
                break;
            }
        }

        data[0][0] = nim;
        data[0][1] = nama;
        count++;
    }

    static void insertPosition() {
        if (count == MAX) {
            System.out.println("Array penuh!");
            return;
        }

        System.out.print("Masukkan posisi (1 - " + (count + 1) + "): ");
        int pos = input.nextInt() - 1;

        if (pos < 0 || pos > count) {
            System.out.println("Posisi tidak valid!");
            return;
        }

        // geser data ke kanan
        for (int i = count; i > pos; i--) {
            data[i][0] = data[i - 1][0];
            data[i][1] = data[i - 1][1];
        }

        String nim, nama;

        // input + validasi
        while (true) {
            System.out.print("Masukkan NIM: ");
            nim = input.next();
            System.out.print("Masukkan Nama: ");
            nama = input.next();

            if (!isValidNim(nim)) {
                System.out.println("NIM harus angka!");
            } else if (!isValidNama(nama)) {
                System.out.println("Nama harus huruf!");
            } else if (isDuplicate(nim, nama)) {
                System.out.println("NIM atau Nama sudah ada!");
            } else {
                break;
            }
        }

        data[pos][0] = nim;
        data[pos][1] = nama;
        count++;
    }

    static void insertEnd() {
        if (count == MAX) {
            System.out.println("Array penuh!");
            return;
        }

        String nim, nama;

        // input + validasi
        while (true) {
            System.out.print("Masukkan NIM: ");
            nim = input.next();
            System.out.print("Masukkan Nama: ");
            nama = input.next();

            if (!isValidNim(nim)) {
                System.out.println("NIM harus angka!");
            } else if (!isValidNama(nama)) {
                System.out.println("Nama harus huruf!");
            } else if (isDuplicate(nim, nama)) {
                System.out.println("NIM atau Nama sudah ada!");
            } else {
                break;
            }
        }

        data[count][0] = nim;
        data[count][1] = nama;
        count++;
    }

    static void deleteBeginning() {
        if (count == 0) {
            System.out.println("Data kosong!");
            return;
        }

        // geser ke kiri
        for (int i = 0; i < count - 1; i++) {
            data[i][0] = data[i + 1][0];
            data[i][1] = data[i + 1][1];
        }

        count--;
    }

    static void deletePosition() {
        if (count == 0) {
            System.out.println("Data kosong!");
            return;
        }

        System.out.print("Masukkan posisi (1 - " + count + "): ");
        int pos = input.nextInt() - 1;

        if (pos < 0 || pos >= count) {
            System.out.println("Posisi tidak valid!");
            return;
        }

        // geser ke kiri
        for (int i = pos; i < count - 1; i++) {
            data[i][0] = data[i + 1][0];
            data[i][1] = data[i + 1][1];
        }

        count--;
    }

    static void deleteEnd() {
        if (count == 0) {
            System.out.println("Data kosong!");
            return;
        }
        count--;
    }

    static void deleteFirstOccurrence() {
        if (count == 0) {
            System.out.println("Data kosong!");
            return;
        }

        System.out.print("Masukkan NIM yang dihapus: ");
        String nim = input.next();

        for (int i = 0; i < count; i++) {
            if (data[i][0].equals(nim)) {
                // geser ke kiri
                for (int j = i; j < count - 1; j++) {
                    data[j][0] = data[j + 1][0];
                    data[j][1] = data[j + 1][1];
                }
                count--;
                return;
            }
        }

        System.out.println("Data tidak ditemukan!");
    }

    static void showData() {
        if (count == 0) {
            System.out.println("Data kosong!");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println("nim mhs#" + (i + 1) + " : " + data[i][0]);
            System.out.println("nama mhs#" + (i + 1) + " : " + data[i][1]);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int pilih;

        do {
            System.out.println("\nMENU");
            System.out.println("1. Insert at beginning");
            System.out.println("2. Insert at given position");
            System.out.println("3. Insert at end");
            System.out.println("4. Delete from beginning");
            System.out.println("5. Delete given position");
            System.out.println("6. Delete from end");
            System.out.println("7. Delete first occurrence");
            System.out.println("8. Show data");
            System.out.println("9. Exit");
            System.out.print("Pilih: ");
            pilih = input.nextInt();

            switch (pilih) {
                case 1 -> insertBeginning();
                case 2 -> insertPosition();
                case 3 -> insertEnd();
                case 4 -> deleteBeginning();
                case 5 -> deletePosition();
                case 6 -> deleteEnd();
                case 7 -> deleteFirstOccurrence();
                case 8 -> showData();
            }
        } while (pilih != 9);
    }
}