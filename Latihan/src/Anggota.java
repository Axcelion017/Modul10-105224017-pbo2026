import java.util.*;

public class Anggota {
    String idAnggota, nama, tipe;

    public Anggota(String idAnggota, String nama, String tipe) {
        this.idAnggota = idAnggota;
        this.nama = nama;
        this.tipe = tipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Anggota mhs = (Anggota) o;
        return Objects.equals(idAnggota, mhs.idAnggota);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnggota);
    }
}
