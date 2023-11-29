/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

/**
 *
 * @author HP
 */
 
import java.util.ArrayList;
import java.util.Scanner;

public class LibrarySystem {
    static ArrayList<Buku> daftarBuku = new ArrayList<>();
    static ArrayList<Member> daftarAnggota = new ArrayList<>();
    static ArrayList<Peminjaman> daftarPeminjaman = new ArrayList<>();
    static int idTransaksi = 1;

    static String adminUsername = "admin";
    static String adminPassword = "admin";

    static class Buku {
        String judul;
        String Pengarang;
        String ISBN;
        boolean Ketersediaan;
    }

    static class Member {
        String IDMember;
        String Nama;
        String Alamat;
        ArrayList<String> RiwayatPeminjaman = new ArrayList<>();
    }

    static class Peminjaman {
        String IDTransaksi;
        String IDMember;
        String ISBN;
        String WaktuPeminjaman;
    }

    public static void main(String[] args) {
        // Menambahkan 5 buku awal
        tambahBuku("Harry Potter", "J.K. Rowling", "ISBN001", true);
        tambahBuku("Lord of the Rings", "J.R.R. Tolkien", "ISBN002", true);
        tambahBuku("To Kill a Mockingbird", "Harper Lee", "ISBN003", true);
        tambahBuku("1984", "George Orwell", "ISBN004", true);
        tambahBuku("The Great Gatsby", "F. Scott Fitzgerald", "ISBN005", true);
        tambahAnggota("M001", "John Doe", "Alamat John Doe");
    tambahAnggota("M002", "Jane Smith", "Alamat Jane Smith");
    tambahAnggota("M003", "Michael Johnson", "Alamat Michael Johnson");
    tambahAnggota("M004", "Emily Davis", "Alamat Emily Davis");
    tambahAnggota("M005", "Robert Wilson", "Alamat Robert Wilson");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Admin");
            System.out.println("2. Member");
            System.out.println("3. Keluar");

            System.out.print("Pilih opsi: ");
            int opsi = scanner.nextInt();

            if (opsi == 1) {
                System.out.print("Masukkan username admin: ");
                String username = scanner.next();
                System.out.print("Masukkan password admin: ");
                String password = scanner.next();

                if (loginAdmin(username, password)) {
                    adminMenu();
                } else {
                    System.out.println("Username atau password admin salah.");
                }
            } else if (opsi == 2) {
                memberMenu();
            } else if (opsi == 3) {
                System.out.println("Program berhenti.");
                break;
            } else {
                System.out.println("Opsi tidak valid. Silakan pilih lagi.");
            }
        }

        scanner.close();
    }

    static boolean loginAdmin(String username, String password) {
        return username.equals(adminUsername) && password.equals(adminPassword);
    }

    static void adminMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu Admin:");
            System.out.println("1. Tampilkan Buku");
            System.out.println("2. Tambah Buku");
            System.out.println("3. Hapus Buku");
            System.out.println("4. Tampilkan Anggota");
            System.out.println("5. Tambah Anggota");
            System.out.println("6. Hapus Anggota");
            System.out.println("7. Kembali ke Menu Utama");

            System.out.print("Pilih opsi: ");
            int opsiAdmin = scanner.nextInt();

            if (opsiAdmin == 1) {
                tampilkanBuku();
            } else if (opsiAdmin == 2) {
                tambahBukuAdmin();
            } else if (opsiAdmin == 3) {
                hapusBukuAdmin();
            } else if (opsiAdmin == 4) {
                tampilkanAnggota();
            } else if (opsiAdmin == 5) {
                tambahAnggota();
            } else if (opsiAdmin == 6) {
                hapusAnggota();
            } else if (opsiAdmin == 7) {
                break;
            } else {
                System.out.println("Opsi tidak valid. Silakan pilih lagi.");
            }
        }
    }

    static void tampilkanBuku() {
        System.out.println("\nDaftar Buku:");
        for (int i = 0; i < daftarBuku.size(); i++) {
            Buku buku = daftarBuku.get(i);
            System.out.println((i + 1) + ". " + buku.judul + " - " + buku.Pengarang + " (ISBN: " + buku.ISBN + ")");
        }
    }

    static void tambahBukuAdmin() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan judul buku: ");
        String judul = scanner.nextLine();
        System.out.print("Masukkan pengarang: ");
        String pengarang = scanner.nextLine();
        System.out.print("Masukkan ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Apakah buku tersedia? (true/false): ");
        boolean ketersediaan = scanner.nextBoolean();

        tambahBuku(judul, pengarang, isbn, ketersediaan);

        System.out.println("Buku berhasil ditambahkan!");
    }
    
    static void tambahAnggota(String idAnggota, String namaAnggota, String alamatAnggota) {
    Member anggotaBaru = new Member();
    anggotaBaru.IDMember = idAnggota;
    anggotaBaru.Nama = namaAnggota;
    anggotaBaru.Alamat = alamatAnggota;

    daftarAnggota.add(anggotaBaru);

    System.out.println("Anggota berhasil ditambahkan!");
    }

    
    static void hapusBukuAdmin() {
        Scanner scanner = new Scanner(System.in);

        tampilkanBuku();

        System.out.print("Masukkan nomor buku yang akan dihapus: ");
        int nomorBuku = scanner.nextInt();

        if (nomorBuku >= 1 && nomorBuku <= daftarBuku.size()) {
            daftarBuku.remove(nomorBuku - 1);
            System.out.println("Buku berhasil dihapus!");
        } else {
            System.out.println("Nomor buku tidak valid.");
        }
    }

    static void tampilkanAnggota() {
        System.out.println("\nDaftar Anggota:");
        for (Member anggota : daftarAnggota) {
            System.out.println("ID: " + anggota.IDMember + ", Nama: " + anggota.Nama + ", Alamat: " + anggota.Alamat);
        }
    }

    static void tambahAnggota() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan ID Anggota: ");
        String idAnggota = scanner.nextLine();
        System.out.print("Masukkan Nama Anggota: ");
        String namaAnggota = scanner.nextLine();
        System.out.print("Masukkan Alamat Anggota: ");
        String alamatAnggota = scanner.nextLine();

        Member anggotaBaru = new Member();
        anggotaBaru.IDMember = idAnggota;
        anggotaBaru.Nama = namaAnggota;
        anggotaBaru.Alamat = alamatAnggota;

        daftarAnggota.add(anggotaBaru);

        System.out.println("Anggota berhasil ditambahkan!");
    }

    static void hapusAnggota() {
        Scanner scanner = new Scanner(System.in);

        tampilkanAnggota();

        System.out.print("Masukkan ID Anggota yang akan dihapus: ");
               String idAnggota = scanner.nextLine();

        for (Member anggota : daftarAnggota) {
            if (anggota.IDMember.equals(idAnggota)) {
                daftarAnggota.remove(anggota);
                System.out.println("Anggota berhasil dihapus!");
                return;
            }
        }

        System.out.println("ID Anggota tidak ditemukan.");
    }

    static void memberMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu Member:");
            System.out.println("1. Tampilkan Buku");
            System.out.println("2. Pinjam Buku");
            System.out.println("3. Kembalikan Buku");
            System.out.println("4. Kembali ke Menu Utama");

            System.out.print("Pilih opsi: ");
            int opsiMember = scanner.nextInt();

            if (opsiMember == 1) {
                tampilkanBuku();
            } else if (opsiMember == 2) {
                pinjamBuku();
            } else if (opsiMember == 3) {
                kembalikanBuku();
            } else if (opsiMember == 4) {
                break;
            } else {
                System.out.println("Opsi tidak valid. Silakan pilih lagi.");
            }
        }
    }

    static void pinjamBuku() {
        Scanner scanner = new Scanner(System.in);

        tampilkanBuku();

        System.out.println("Pilih cara peminjaman:");
        System.out.println("1. Berdasarkan nomor buku");
        System.out.println("2. Berdasarkan ISBN");
        System.out.print("Pilih opsi: ");
        int opsiPeminjaman = scanner.nextInt();

        if (opsiPeminjaman == 1) {
            System.out.print("Masukkan nomor buku yang akan dipinjam: ");
            int nomorBuku = scanner.nextInt();
            prosesPeminjaman(nomorBuku);
        } else if (opsiPeminjaman == 2) {
            System.out.print("Masukkan ISBN buku yang akan dipinjam: ");
            String isbn = scanner.next();
            prosesPeminjaman(isbn);
        } else {
            System.out.println("Opsi tidak valid.");
        }
    }

    static void prosesPeminjaman(int input) {
        if (input >= 1 && input <= daftarBuku.size()) {
            Buku bukuDipinjam = daftarBuku.get(input - 1);

            if (bukuDipinjam.Ketersediaan) {
                Peminjaman transaksiPeminjaman = new Peminjaman();
                transaksiPeminjaman.IDTransaksi = "T" + idTransaksi++;
                transaksiPeminjaman.IDMember = "M001"; // ID Member sementara
                transaksiPeminjaman.ISBN = bukuDipinjam.ISBN;
                transaksiPeminjaman.WaktuPeminjaman = "Timestamp"; // Timestamp sementara

                daftarPeminjaman.add(transaksiPeminjaman);

                bukuDipinjam.Ketersediaan = false;

                System.out.println("Buku berhasil dipinjam! Transaksi ID: " + transaksiPeminjaman.IDTransaksi);
            } else {
                System.out.println("Buku tidak tersedia.");
            }
        } else {
            System.out.println("Nomor buku tidak valid.");
        }
    }

    static void prosesPeminjaman(String isbn) {
        Buku bukuDipinjam = cariBukuByISBN(isbn);

        if (bukuDipinjam != null && bukuDipinjam.Ketersediaan) {
            Peminjaman transaksiPeminjaman = new Peminjaman();
            transaksiPeminjaman.IDTransaksi = "T" + idTransaksi++;
            transaksiPeminjaman.IDMember = "M001"; // ID Member sementara
            transaksiPeminjaman.ISBN = bukuDipinjam.ISBN;
            transaksiPeminjaman.WaktuPeminjaman = "Timestamp"; // Timestamp sementara

            daftarPeminjaman.add(transaksiPeminjaman);

            bukuDipinjam.Ketersediaan = false;

            System.out.println("Buku berhasil dipinjam! Transaksi ID: " + transaksiPeminjaman.IDTransaksi);
        } else {
            System.out.println("Buku tidak tersedia atau ISBN tidak valid.");
        }
    }
    static void tambahBuku(String judul, String pengarang, String isbn, boolean ketersediaan) {
    Buku bukuBaru = new Buku();
    bukuBaru.judul = judul;
    bukuBaru.Pengarang = pengarang;
    bukuBaru.ISBN = isbn;
    bukuBaru.Ketersediaan = ketersediaan;

    daftarBuku.add(bukuBaru);
}

    static void kembalikanBuku() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan ID Transaksi: ");
        String idTransaksi = scanner.nextLine();

        for (Peminjaman peminjaman : daftarPeminjaman) {
            if (peminjaman.IDTransaksi.equals(idTransaksi)) {
                Buku bukuDikembalikan = cariBukuByISBN(peminjaman.ISBN);
                bukuDikembalikan.Ketersediaan = true;

                daftarPeminjaman.remove(peminjaman);

                System.out.println("Buku berhasil dikembalikan!");
                return;
            }
        }

        System.out.println("ID Transaksi tidak ditemukan.");
    }
    
    
    static Buku cariBukuByISBN(String isbn) {
        for (Buku buku : daftarBuku) {
            if (buku.ISBN.equals(isbn)) {
                return buku;
            }
        }
        return null;
    }
}