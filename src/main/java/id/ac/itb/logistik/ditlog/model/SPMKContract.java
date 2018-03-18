package id.ac.itb.logistik.ditlog.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "V_SPMK_ANDROID")
public class SPMKContract {
  @Column(name = "TAHUN")
  Long tahun;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID_SPMK")
  Long idSPMK;

  @Column(name = "NO_SPMK")
  String noSPMK;

  @Column(name = "TGL_SPMK")
  Date tanggalSPMK;

  @Column(name = "TGL_MULAI_PEKERJAAN")
  Date tanggalMulai;

  @Column(name = "TGL_AKHIR_PEKERJAAN")
  Date tanggalAkhir;

  @Column(name = "JUMLAH_HARI_PEKERJAAN")
  String jumlahHariPekerjaan;

  @Column(name = "JUMLAH_HARI_INSTALASI")
  String jumlahHariInstalasi;

  @Column(name = "ID_SPPBJ_HEADER")
  Long idSPPBJ;

  @Column(name = "ID_KONTRAK")
  Long idKontrak;

  @Column(name = "NO_KONTRAK")
  String noKontrak;

  @Column(name = "TGL_KONTRAK")
  Date tanggalKontrak;

  @Column(name = "ID_HPS")
  Long idHPS;

  @Column(name = "NM_HPS")
  String nmHPS;

  @Column(name = "NAMA_PENYEDIA")
  String namaPenyedia;

  @Column(name = "ALAMAT")
  String alamat;

  @Column(name = "JABATAN_PIMPINAN")
  String jabatanPimpinan;

  @Column(name = "PIMPINAN_PERUSAHAAN")
  String pimpinanPerusahaan;

  @Column(name = "NILAI_NEGOSIASI")
  Long nilaiNegosiasi;

  @Column(name = "JENIS")
  String jenis;

  @Column(name = "ID_TTD")
  Long idTTD;

  @Column(name = "PPK")
  String ppk;

  @Column(name = "NIP")
  String nip;

  @Column(name = "ID_JNS_PENGADAAN")
  Long idJenisPengadaan;

  @Column(name = "METODE_PENGADAAN")
  String metodePengadaan;

  @Column(name = "BERDASARKAN_WAKTU_PELAKSANAAN")
  String berdasarkanWaktuPelaksanaan;

  @Column(name = "NO_PO")
  String noPO;

  @Column(name = "MASA_GARANSI_PURNA_JUAL")
  String masaGaransiPurnaJual;

  @Column(name = "NILAI_UANG_MUKA")
  Long nilaiUangMuka;

  @Column(name = "SUMBER_DANA")
  String sumberDana;

  @Column(name = "CARA_PEMBAYARAN")
  String caraPembayaran;

  @Column(name = "JENIS_KONTRAK")
  String jenisKontrak;

  @Column(name = "NAMA_BANK")
  String namaBank;

  @Column(name = "NO_REKENING")
  String noRekening;

  @Column(name = "REK_ATAS_NAMA")
  String rekAtasNama;

  @Column(name = "VENDOR_ID")
  Long vendorId;
}