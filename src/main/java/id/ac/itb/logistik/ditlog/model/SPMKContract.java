package id.ac.itb.logistik.ditlog.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "V_SPMK_ANDROID")
public class SPMKContract {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID_SPMK")
  private Long idSPMK;

  @Column(name = "NO_SPMK")
  private String noSPMK;

  @Column(name = "TGL_SPMK")
  private Date tanggalSPMK;

  @Column(name = "TGL_MULAI_PEKERJAAN")
  private Date tanggalMulai;

  @Column(name = "TGL_AKHIR_PEKERJAAN")
  private Date tanggalAkhir;

  @Column(name = "JUMLAH_HARI_PEKERJAAN")
  private String jumlahHariPekerjaan;

  @Column(name = "JUMLAH_HARI_INSTALASI")
  private String jumlahHariInstalasi;

  @Column(name = "ID_SPPBJ_HEADER")
  private Long idSPPBJ;

  @Column(name = "ID_KONTRAK", unique = true)
  private Long idKontrak;

  @Column(name = "NO_KONTRAK")
  private String noKontrak;

  @Column(name = "TGL_KONTRAK")
  private Date tanggalKontrak;

  @Column(name = "ID_HPS")
  private Long idHPS;

  @Column(name = "NM_HPS")
  private String nmHPS;

  @Column(name = "NAMA_PENYEDIA")
  private String namaPenyedia;

  @Column(name = "ALAMAT")
  private String alamat;

  @Column(name = "JENIS")
  private String jenis;

  @Column(name = "VENDOR_ID")
  private Long vendorId;

  @Transient
  private BigDecimal nilaiRataRata = BigDecimal.ZERO;

  public SPMKContract() {
  }

  public Long getIdSPMK() {
    return idSPMK;
  }

  public void setIdSPMK(Long idSPMK) {
    this.idSPMK = idSPMK;
  }

  public String getNoSPMK() {
    return noSPMK;
  }

  public void setNoSPMK(String noSPMK) {
    this.noSPMK = noSPMK;
  }

  public Date getTanggalSPMK() {
    return tanggalSPMK;
  }

  public void setTanggalSPMK(Date tanggalSPMK) {
    this.tanggalSPMK = tanggalSPMK;
  }

  public Date getTanggalMulai() {
    return tanggalMulai;
  }

  public void setTanggalMulai(Date tanggalMulai) {
    this.tanggalMulai = tanggalMulai;
  }

  public Date getTanggalAkhir() {
    return tanggalAkhir;
  }

  public void setTanggalAkhir(Date tanggalAkhir) {
    this.tanggalAkhir = tanggalAkhir;
  }

  public String getJumlahHariPekerjaan() {
    return jumlahHariPekerjaan;
  }

  public void setJumlahHariPekerjaan(String jumlahHariPekerjaan) {
    this.jumlahHariPekerjaan = jumlahHariPekerjaan;
  }

  public String getJumlahHariInstalasi() {
    return jumlahHariInstalasi;
  }

  public void setJumlahHariInstalasi(String jumlahHariInstalasi) {
    this.jumlahHariInstalasi = jumlahHariInstalasi;
  }

  public Long getIdSPPBJ() {
    return idSPPBJ;
  }

  public void setIdSPPBJ(Long idSPPBJ) {
    this.idSPPBJ = idSPPBJ;
  }

  public Long getIdKontrak() {
    return idKontrak;
  }

  public void setIdKontrak(Long idKontrak) {
    this.idKontrak = idKontrak;
  }

  public String getNoKontrak() {
    return noKontrak;
  }

  public void setNoKontrak(String noKontrak) {
    this.noKontrak = noKontrak;
  }

  public Date getTanggalKontrak() {
    return tanggalKontrak;
  }

  public void setTanggalKontrak(Date tanggalKontrak) {
    this.tanggalKontrak = tanggalKontrak;
  }

  public Long getIdHPS() {
    return idHPS;
  }

  public void setIdHPS(Long idHPS) {
    this.idHPS = idHPS;
  }

  public String getNmHPS() {
    return nmHPS;
  }

  public void setNmHPS(String nmHPS) {
    this.nmHPS = nmHPS;
  }

  public String getNamaPenyedia() {
    return namaPenyedia;
  }

  public void setNamaPenyedia(String namaPenyedia) {
    this.namaPenyedia = namaPenyedia;
  }

  public String getAlamat() {
    return alamat;
  }

  public void setAlamat(String alamat) {
    this.alamat = alamat;
  }

  public String getJenis() {
    return jenis;
  }

  public void setJenis(String jenis) {
    this.jenis = jenis;
  }

  public Long getVendorId() {
    return vendorId;
  }

  public void setVendorId(Long vendorId) {
    this.vendorId = vendorId;
  }

  public BigDecimal getNilaiRataRata() {
    return nilaiRataRata;
  }

  public void setNilaiRataRata(BigDecimal nilaiRataRata) {
    this.nilaiRataRata = nilaiRataRata;
  }
}