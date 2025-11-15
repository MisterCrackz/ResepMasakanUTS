# 🍲 Aplikasi Resep Masakan

Sebuah aplikasi GUI desktop sederhana untuk mengelola resep masakan. Dibuat menggunakan Java Swing (JFrame) dan menerapkan konsep OOP dengan arsitektur Model-View-Controller (MVC).

Proyek ini dibuat untuk memenuhi Ujian Tengah Semester (UTS) mata kuliah Pemrograman Berorientasi Objek 2.

## 📸 Tangkapan Layar (Screenshot)

<img width="948" height="723" alt="image" src="https://github.com/user-attachments/assets/89eae4b8-5201-4c90-8415-b521812c9987" />

## ✨ Fitur Utama

Aplikasi ini mencakup fungsionalitas penuh untuk manajemen data (CRUD) serta fitur tantangan (Impor/Ekspor).

* **Fungsionalitas CRUD:**
    * ✅ **Create:** Menambah resep baru (judul, bahan, instruksi).
    * ✅ **Read:** Membaca resep dengan memilih dari daftar.
    * ✅ **Update:** Memperbarui resep yang sudah ada.
    * ✅ **Delete:** Menghapus resep dari daftar dengan pesan konfirmasi.
* **Fitur Tantangan (Nilai Tambah 20 Poin):**
    * ⭐ **Ekspor:** Mengekspor seluruh daftar resep ke dalam format file `.json` yang rapi.
    * ⭐ **Impor:** Mengimpor data resep dari file `.json` untuk memuat data kembali ke aplikasi.
* **Desain & UX Modern:**
    * 🎨 Tampilan antarmuka yang bersih dan modern menggunakan method `setupModernUI` kustom, bukan tampilan standar Swing.
    * 👆 Tombol memiliki *hover effects* dan ikon untuk *user experience* yang lebih baik.
    * 🛡️ Tombol "Update" dan "Hapus" dinonaktifkan secara otomatis jika tidak ada resep yang dipilih untuk mencegah error.

---

## 🏛️ Arsitektur & Konsep OOP

Proyek ini dibangun dengan mematuhi prinsip Pemrograman Berorientasi Objek, khususnya pemisahan tanggung jawab (Separation of Concerns) menggunakan pola arsitektur **Model-View-Controller (MVC)**.

* **Model:** `Resep.java`
    * Bertanggung jawab untuk menyimpan data (POJO - Plain Old Java Object). Menerapkan **Enkapsulasi** dengan atribut `private` dan method `public` (getters/setters).
* **View:** `AplikasiResepFrame.java`
    * Bertanggung jawab untuk semua tampilan (GUI) dan *Event Handling*. Ini adalah `JFrame` utama yang dilihat oleh pengguna.
* **Controller:** `ManajemenResep.java`
    * Bertanggung jawab untuk semua logika bisnis. Bertindak sebagai jembatan antara View dan Model. Semua proses (tambah, hapus, impor, ekspor) diatur di sini. Ini menerapkan **Abstraksi**.

---

## 💻 Teknologi yang Digunakan

* **Bahasa:** Java
* **Framework/Library:**
    * Java Swing (untuk GUI)
    * `org.json` (Library eksternal untuk menangani data JSON)
* **IDE:** Apache NetBeans

---

## 🚀 Cara Menjalankan Proyek

1.  *Clone* repositori ini.
2.  Buka proyek melalui NetBeans IDE.
3.  Pastikan Anda telah menambahkan library eksternal `java-json.jar` ke dalam folder `Libraries` proyek di NetBeans.
4.  Klik kanan pada file `AplikasiResepFrame.java` di dalam *Source Packages*.
5.  Pilih **"Run File"**.

---

## 👨‍🎓 Informasi Pembuat

Proyek ini dibuat dan dikumpulkan untuk Ujian Tengah Semester (UTS):

* **Nama:** **[Nama Lengkap Anda]**
* **NPM:** **[NPM Anda]**
* **Mata Kuliah:** Pemrograman Berorientasi Objek 2
* **Dosen:** **[Nama Dosen Anda]**
* **Universitas:** **[Nama Universitas Anda]**
