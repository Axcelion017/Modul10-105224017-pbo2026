import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Map<String, Film> daftarFilm = new HashMap<>();// Map untuk menyimpan daftar film dengan kode film sebagai key
        Set<String> kursi  = new HashSet<>(); // Set untuk menyimpan nomor kursi yang valid di studio
        List<String> prosesPesanan = new ArrayList<>(); // List untuk menyimpan proses pesanan dalam format "NamaPembeli#KodeFilm#NomorKursi sebagai buffer sebelum diproses"
        Set<RiwayatTransaksi> riwayatTransaksi = new HashSet<>(); // Set untuk menyimpan riwayat transaksi agar tidak ada duplikasi
        Map<String, Set<String>> kursiTerpesanPerFilm = new HashMap<>(); // Map untuk melacak kursi yang sudah dipesan per film

        daftarFilm.put("F01", new Film("Avengers: Endgame", 50000));
        daftarFilm.put("F02", new Film("The Lion King", 45000));
        daftarFilm.put("F03", new Film("Joker", 40000));
        kursiTerpesanPerFilm.put("F01", new HashSet<>()); // Inisialisasi set untuk setiap film agar bisa melacak kursi yang sudah dipesan
        kursiTerpesanPerFilm.put("F02", new HashSet<>());
        kursiTerpesanPerFilm.put("F03", new HashSet<>());

        for (int i = 1; i <= 5; i++) {
            kursi.add("A" + i);
            kursi.add("B" + i);
            kursi.add("C" + i);
            kursi.add("D" + i);
            kursi.add("E" + i);
        }// Menambahkan nomor kursi A1-A5, B1-B5, C1-C5, D1-D5, E1-E5 ke dalam set kursi

        // Menambahkan proses pesanan ke dalam list prosesPesanan dengan format "NamaPembeli#KodeFilm#NomorKursi"
        prosesPesanan.add( "John Doe#F01#A1");
        prosesPesanan.add( "Jane Smith#F01#A1"); 
        prosesPesanan.add( "Emily Davis#F02#C3");
        prosesPesanan.add( "Alice Johnson#F02#B2");
        prosesPesanan.add( "Bob Brown#F03#B2");
        prosesPesanan.add( "Charlie Wilson#F03#H5");
        prosesPesanan.add( "David Lee#F04#A1");

        // Memproses setiap pesanan dalam list prosesPesanan
        for (String t : prosesPesanan) {
            String[] data = t.split("#");
            String namaPembeli = data[0];
            String kodeFilm = data[1];
            String nomorKursi = data[2];
            System.out.print("Memproses: " + namaPembeli + " | " + kodeFilm + " | " + nomorKursi + " -> ");
            if (!daftarFilm.containsKey(kodeFilm)) { //Cek ada film atau gknya
                System.out.println("Proeses pesanan GAGAL. Kode film tidak tersedia.");
                continue;
            }
            if (!kursi.contains(nomorKursi)) { //Cek nomor kursinya ada gk di bioskop
                System.out.println("Proses pesanan GAGAL. Nomor kursi tidak valid / tidak ada di studio.");
                continue;
            }
            if(kursiTerpesanPerFilm.containsKey(kodeFilm) && kursiTerpesanPerFilm.get(kodeFilm).contains(nomorKursi)){ //Cek apakah kursi sudah dipesan untuk film tersebut
                System.out.println("Proses Pesanan GAGAL. Kursi sudah dipesan pelanggan lain.");
                continue;
            }else{//Kalo bener semua pengecekan validnya baru di proses pesanannya berhasil
                Film filmDipesan = daftarFilm.get(kodeFilm);
                RiwayatTransaksi transaksi = new RiwayatTransaksi(namaPembeli, filmDipesan, nomorKursi);
                riwayatTransaksi.add(transaksi);
                kursiTerpesanPerFilm.get(kodeFilm).add(nomorKursi);
                System.out.println("Proses Pesanan BERHASIL. Transaksi berhasil diproses.");
            }
            
        }
        //Cek riwayat biar aman
        System.out.println("\nRiwayat Transaksi");
        for (RiwayatTransaksi rt : riwayatTransaksi) {
            rt.tampilkanTransaksi();
        }
    }
}


