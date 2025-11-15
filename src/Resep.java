/**
 * MODEL (Bagian dari MVC)
 * File: Resep.java
 * * Ini adalah 'Model' atau POJO (Plain Old Java Object).
 * Tugasnya adalah merepresentasikan dan menyimpan data untuk SATU resep masakan.
 * * Konsep OOP[cite: 15]:
 * - Class & Object: Ini adalah 'class' (cetak biru) resep.
 * - Attribute: 'judul', 'bahan', 'instruksi' adalah atribut.
 * - Method: 'getJudul()', 'setJudul()', dll adalah method.
 * - Encapsulation: Atribut dibuat 'private', hanya bisa diakses
 * melalui method 'public' (getters/setters).
 */
public class Resep {

    // --- Atribut (Encapsulated) ---
    private String judul;
    private String bahan;
    private String instruksi;

    /**
     * Constructor: Method khusus yang dipanggil saat sebuah 'Object' Resep dibuat.
     */
    public Resep(String judul, String bahan, String instruksi) {
        this.judul = judul;
        this.bahan = bahan;
        this.instruksi = instruksi;
    }

    // --- Methods (Getters & Setters) ---
    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getBahan() {
        return bahan;
    }

    public void setBahan(String bahan) {
        this.bahan = bahan;
    }

    public String getInstruksi() {
        return instruksi;
    }

    public void setInstruksi(String instruksi) {
        this.instruksi = instruksi;
    }

    /**
     * PENTING: Method ini di-override agar JList  tahu apa yang
     * harus ditampilkan. Jika tidak, JList akan menampilkan
     * sesuatu seperti 'Resep@1a2b3c4d'. Kita ingin JList menampilkan judulnya.
     */
    @Override
    public String toString() {
        // Mengembalikan judul untuk ditampilkan di JList
        return this.judul;
    }
}