import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, Buku> katalogBuku = new HashMap<>();
        Set<Anggota> anggotaSet = new HashSet<>();
        LinkedList<String> daftarPeminjaman = new LinkedList<>();
        Map<String, String> statusBuku = new HashMap<>();

        katalogBuku.put("123", new Buku("123", "Pemrograman Java"));
        katalogBuku.put("321", new Buku("321", "Struktur Data dan Algoritma"));
        katalogBuku.put("456", new Buku("456", "Basis Data"));
        katalogBuku.put("654", new Buku("654", "Jaringan Komputer"));
        
        anggotaSet.add(new Anggota("D001", "Alice", "Dosen"));
        anggotaSet.add(new Anggota("M001", "Bob", "Mahasiswa"));
        anggotaSet.add(new Anggota("M001", "Charlie", "Mahasiswa")); // Duplicate, will not be added
        anggotaSet.add(new Anggota("D002", "David", "Dosen"));
        anggotaSet.add(new Anggota("M002", "Eve", "Mahasiswa"));

        for(Anggota anggota : anggotaSet) {
            System.out.println("ID: " + anggota.idAnggota + ", Nama: " + anggota.nama + ", Tipe: " + anggota.tipe);
        }

        System.out.println("Jumlah Anggota: " + anggotaSet.size());


        for(Anggota anggota : anggotaSet) {
            if(anggota.tipe.equals("Dosen")){
                daftarPeminjaman.addFirst(anggota.idAnggota + "#"+ katalogBuku.get("123").isbn);
            } else {
                daftarPeminjaman.addLast(anggota.idAnggota + "#"+ katalogBuku.get("456").isbn);
            }
        }

        daftarPeminjaman.add("M005#321");// Anggota tidak terdaftar
        daftarPeminjaman.add("M002#999");// Buku tidak terdaftar
        
        for(String peminjaman : daftarPeminjaman) {
            System.out.println("Peminjaman: " + peminjaman);
        }
        
        for(String peminjaman : daftarPeminjaman) {
            String[] parts = peminjaman.split("#");
            String idAnggota = parts[0];
            boolean anggotaTerdaftar = false;
            for(Anggota anggota : anggotaSet) {
                if(anggota.idAnggota.equals(idAnggota)){
                    anggotaTerdaftar = true;
                }
            }
            if(!anggotaTerdaftar){
                System.out.println("Anggota dengan ID: " + idAnggota + " tidak ditemukan.");
                continue;
            }
            String isbnBuku = parts[1];
            if(katalogBuku.get(isbnBuku) == null){
                System.out.println("Buku dengan ISBN: " + isbnBuku + " tidak ditemukan.");
                continue;
            }
            if(statusBuku.get(isbnBuku) != null && statusBuku.get(isbnBuku).equals("Dipinjam")){
                System.out.println(isbnBuku + " sudah dipinjam oleh anggota lain.");
            }else{
                statusBuku.put(isbnBuku, "Dipinjam");
                System.out.println(idAnggota + " berhasil meminjam buku dengan ISBN: " + isbnBuku);
            }
        }

        System.out.println("Status Buku: " + katalogBuku.get("123").judul + " - " + statusBuku.get("123"));
        System.out.println("Status Buku: " + katalogBuku.get("321").judul + " - " + statusBuku.get("321"));
        System.out.println("Status Buku: " + katalogBuku.get("456").judul + " - " + statusBuku.get("456"));
        System.out.println("Status Buku: " + katalogBuku.get("654").judul + " - " + statusBuku.get("654"));

    }
}
