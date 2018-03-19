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
  public Long tahun;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID_SPMK")
  public Long idSPMK;

  @Column(name = "NO_SPMK")
  public String noSPMK;

  @Column(name = "TGL_SPMK")
  public Date tanggalSPMK;

  @Column(name = "TGL_MULAI_PEKERJAAN")
  public Date tanggalMulai;

  @Column(name = "TGL_AKHIR_PEKERJAAN")
  public Date tanggalAkhir;

  @Column(name = "JUMLAH_HARI_PEKERJAAN")
  public String jumlahHariPekerjaan;

  @Column(name = "JUMLAH_HARI_INSTALASI")
  public String jumlahHariInstalasi;

  @Column(name = "ID_SPPBJ_HEADER")
  public Long idSPPBJ;

  @Column(name = "ID_KONTRAK")
  public Long idKontrak;

  @Column(name = "NO_KONTRAK")
  public String noKontrak;

  @Column(name = "TGL_KONTRAK")
  public Date tanggalKontrak;

  @Column(name = "ID_HPS")
  public Long idHPS;

  @Column(name = "NM_HPS")
  public String nmHPS;

  @Column(name = "NAMA_PENYEDIA")
  public String namaPenyedia;

  @Column(name = "ALAMAT")
  public String alamat;

  @Column(name = "JABATAN_PIMPINAN")
  public String jabatanPimpinan;

  @Column(name = "PIMPINAN_PERUSAHAAN")
  public String pimpinanPerusahaan;

  @Column(name = "NILAI_NEGOSIASI")
  public Long nilaiNegosiasi;

  @Column(name = "JENIS")
  public String jenis;

  @Column(name = "ID_TTD")
  public Long idTTD;

  @Column(name = "PPK")
  public String ppk;

  @Column(name = "NIP")
  public String nip;

  @Column(name = "ID_JNS_PENGADAAN")
  public Long idJenisPengadaan;

  @Column(name = "METODE_PENGADAAN")
  public String metodePengadaan;

  @Column(name = "BERDASARKAN_WAKTU_PELAKSANAAN")
  public String berdasarkanWaktuPelaksanaan;

  @Column(name = "NO_PO")
  public String noPO;

  @Column(name = "MASA_GARANSI_PURNA_JUAL")
  public String masaGaransiPurnaJual;

  @Column(name = "NILAI_UANG_MUKA")
  public Long nilaiUangMuka;

  @Column(name = "SUMBER_DANA")
  public String sumberDana;

  @Column(name = "CARA_PEMBAYARAN")
  public String caraPembayaran;

  @Column(name = "JENIS_KONTRAK")
  public String jenisKontrak;

  @Column(name = "NAMA_BANK")
  public String namaBank;

  @Column(name = "NO_REKENING")
  public String noRekening;

  @Column(name = "REK_ATAS_NAMA")
  public String rekAtasNama;

  @Column(name = "VENDOR_ID")
  public Long vendorId;
}