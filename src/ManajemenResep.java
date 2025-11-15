import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import org.json.JSONArray; // Dari library eksternal yg kita tambah
import org.json.JSONObject; // Dari library eksternal yg kita tambah

/**
 * CONTROLLER (Bagian dari MVC)
 * File: ManajemenResep.java
 * * Ini adalah 'Controller'. Kelas ini berisi SEMUA logika bisnis.
 * - Mengelola daftar resep (pakai ArrayList).
 * - Menangani fungsi Tambah, Ubah, Hapus.
 * - Menangani fungsi Impor dan Ekspor (Fitur Tantangan).
 * * Konsep OOP[cite: 15]:
 * - Abstraction: 'View' (JFrame) tidak perlu tahu BAGAIMANA resep
 * disimpan (apakah pakai ArrayList, database, dll). 'View' hanya
 * tahu "Manajer, tolong tambahkan resep ini". Detailnya disembunyikan.
 */
public class ManajemenResep {

    // Database sementara kita. Menyimpan daftar Objek Resep
    private ArrayList<Resep> daftarResep;

    /**
     * Constructor
     */
    public ManajemenResep() {
        this.daftarResep = new ArrayList<>();
    }

    // --- Logika CRUD (Create, Read, Update, Delete) ---
    
    /**
     * CREATE: Menambah resep baru ke daftar.
     */
    public void tambahResep(Resep resep) {
        this.daftarResep.add(resep);
    }

    /**
     * READ: Mengambil seluruh daftar resep.
     */
    public ArrayList<Resep> getDaftarResep() {
        return this.daftarResep;
    }

    /**
     * UPDATE: Mengganti resep di posisi (index) tertentu.
     */
    public void updateResep(int index, Resep resepBaru) {
        if (index >= 0 && index < daftarResep.size()) {
            this.daftarResep.set(index, resepBaru);
        }
    }

    /**
     * DELETE: Menghapus resep di posisi (index) tertentu.
     */
    public void hapusResep(int index) {
        if (index >= 0 && index < daftarResep.size()) {
            this.daftarResep.remove(index);
        }
    }

    // --- Logika Fitur Tantangan (Impor/Ekspor)  ---

    /**
     * EKSPOR: Menyimpan seluruh 'daftarResep' ke file JSON.
     * @param file File tujuan (dipilih via JFileChooser di View)
     * @return true jika sukses, false jika gagal
     */
    public boolean eksporKeJSON(File file) {
        JSONArray jsonArray = new JSONArray();
        for (Resep resep : daftarResep) {
            JSONObject jsonResep = new JSONObject();
            jsonResep.put("judul", resep.getJudul());
            jsonResep.put("bahan", resep.getBahan());
            jsonResep.put("instruksi", resep.getInstruksi());
            jsonArray.put(jsonResep);
        }

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(jsonArray.toString(4)); // 4 = indentasi agar rapi
            return true;
        } catch (IOException e) {
            System.err.println("Error saat ekspor: " + e.getMessage());
            return false;
        }
    }

    /**
     * IMPOR: Membaca file JSON dan memuatnya ke 'daftarResep'.
     * @param file File sumber (dipilih via JFileChooser di View)
     * @return true jika sukses, false jika gagal
     */
    public boolean imporDariJSON(File file) {
        try {
            String content = new String(Files.readAllBytes(file.toPath()));
            JSONArray jsonArray = new JSONArray(content);

            daftarResep.clear(); // Kosongkan daftar lama

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonResep = jsonArray.getJSONObject(i);
                Resep resep = new Resep(
                    jsonResep.getString("judul"),
                    jsonResep.getString("bahan"),
                    jsonResep.getString("instruksi")
                );
                daftarResep.add(resep);
            }
            return true;
        } catch (Exception e) {
            System.err.println("Error saat impor: " + e.getMessage());
            return false;
        }
    }
}