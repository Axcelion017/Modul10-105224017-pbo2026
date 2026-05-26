public class RiwayatTransaksi {
    
    String namaPemesan;
    Film filmDipesan;
    String nomorKursi;

    public RiwayatTransaksi(String namaPemesan, Film filmDipesan, String nomorKursi) {
        this.namaPemesan = namaPemesan;
        this.filmDipesan = filmDipesan;
        this.nomorKursi = nomorKursi;
    }

    public void tampilkanTransaksi() {
        System.out.println("Nama Pemesan: " + namaPemesan);
        System.out.println("Film Dipesan: " + filmDipesan.namaFilm);
        System.out.println("Harga Film: " + filmDipesan.hargaFilm);
        System.out.println("Nomor Kursi: " + nomorKursi);
        System.out.println("-----------------------------");
    }
    
}
