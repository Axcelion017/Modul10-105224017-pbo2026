import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Map<String, Film> daftarFilm = new HashMap<>();
        Set<String> kursi  = new HashSet<>();
        List<String> prosesPesanan = new ArrayList<>();
        Set<RiwayatTransaksi> riwayatTransaksi = new HashSet<>();
        Map<String, Set<String>> kursiTerpesanPerFilm = new HashMap<>();

        daftarFilm.put("F01", new Film("Avengers: Endgame", 50000));
        daftarFilm.put("F02", new Film("The Lion King", 45000));
        daftarFilm.put("F03", new Film("Joker", 40000));
        kursiTerpesanPerFilm.put("F01", new HashSet<>());
        kursiTerpesanPerFilm.put("F02", new HashSet<>());
        kursiTerpesanPerFilm.put("F03", new HashSet<>());

        for (int i = 1; i <= 5; i++) {
            kursi.add("A" + i);
            kursi.add("B" + i);
            kursi.add("C" + i);
            kursi.add("D" + i);
            kursi.add("E" + i);
        }

        prosesPesanan.add( "John Doe#F01#A1");
        prosesPesanan.add( "Jane Smith#F01#A1"); 
        prosesPesanan.add( "Emily Davis#F02#C3");
        prosesPesanan.add( "Alice Johnson#F02#B2");
        prosesPesanan.add( "Bob Brown#F03#B2");
        prosesPesanan.add( "Charlie Wilson#F03#H5");
        prosesPesanan.add( "David Lee#F04#A1");


        for (String t : prosesPesanan) {
            String[] data = t.split("#");
            String namaPembeli = data[0];
            String kodeFilm = data[1];
            String nomorKursi = data[2];
            System.out.print("Memproses: " + namaPembeli + " | " + kodeFilm + " | " + nomorKursi + " -> ");
            if (!daftarFilm.containsKey(kodeFilm)) {
                System.out.println("Proeses pesanan GAGAL. Kode film tidak tersedia.");
                continue;
            }
            if (!kursi.contains(nomorKursi)) {
                System.out.println("Proses pesanan GAGAL. Nomor kursi tidak valid / tidak ada di studio.");
                continue;
            }
            if(kursiTerpesanPerFilm.containsKey(kodeFilm) && kursiTerpesanPerFilm.get(kodeFilm).contains(nomorKursi)){
                System.out.println("Proses Pesanan GAGAL. Kursi sudah dipesan pelanggan lain.");
                continue;
            }else{
                Film filmDipesan = daftarFilm.get(kodeFilm);
                RiwayatTransaksi transaksi = new RiwayatTransaksi(namaPembeli, filmDipesan, nomorKursi);
                riwayatTransaksi.add(transaksi);
                kursiTerpesanPerFilm.get(kodeFilm).add(nomorKursi);

                System.out.println("Proses Pesanan BERHASIL. Transaksi berhasil diproses.");
            }
            
        }
        
        System.out.println("\nRiwayat Transaksi");
        for (RiwayatTransaksi rt : riwayatTransaksi) {
            System.out.println("Nama Pemesan: " + rt.namaPemesan);
            System.out.println("Film Dipesan: " + rt.filmDipesan.namaFilm);
            System.out.println("Harga Film: " + rt.filmDipesan.hargaFilm);
            System.out.println("Nomor Kursi: " + rt.nomorKursi);
            System.out.println("-----------------------------");
        }
    }
}


