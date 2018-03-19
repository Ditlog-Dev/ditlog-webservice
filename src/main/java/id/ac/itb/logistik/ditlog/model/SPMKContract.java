package id.ac.itb.logistik.ditlog.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "V_SPMK_ANDROID")
public class SPMKContract {
  @Column(name = "TAHUN")
  private Long tahun;

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

  @Column(name = "JABATAN_PIMPINAN")
  private String jabatanPimpinan;

  @Column(name = "PIMPINAN_PERUSAHAAN")
  private String pimpinanPerusahaan;

  @Column(name = "NILAI_NEGOSIASI")
  private Long nilaiNegosiasi;

  @Column(name = "JENIS")
  private String jenis;

  @Column(name = "ID_TTD")
  private Long idTTD;

  @Column(name = "PPK")
  private String ppk;

  @Column(name = "NIP")
  private String nip;

  @Column(name = "ID_JNS_PENGADAAN")
  private Long idJenisPengadaan;

  @Column(name = "METODE_PENGADAAN")
  private String metodePengadaan;

  @Column(name = "BERDASARKAN_WAKTU_PELAKSANAAN")
  private String berdasarkanWaktuPelaksanaan;

  @Column(name = "NO_PO")
  private String noPO;

  @Column(name = "MASA_GARANSI_PURNA_JUAL")
  private String masaGaransiPurnaJual;

  @Column(name = "NILAI_UANG_MUKA")
  private Long nilaiUangMuka;

  @Column(name = "SUMBER_DANA")
  private String sumberDana;

  @Column(name = "CARA_PEMBAYARAN")
  private String caraPembayaran;

  @Column(name = "JENIS_KONTRAK")
  private String jenisKontrak;

  @Column(name = "NAMA_BANK")
  private String namaBank;

  @Column(name = "NO_REKENING")
  private String noRekening;

  @Column(name = "REK_ATAS_NAMA")
  private String rekAtasNama;

  @Column(name = "VENDOR_ID")
  private Long vendorId;

  @Transient
  private BigDecimal nilaiRataRata;

  public SPMKContract() {
  }

  public Long getTahun() {
    return tahun;
  }

  public void setTahun(Long tahun) {
    this.tahun = tahun;
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

  public String getJabatanPimpinan() {
    return jabatanPimpinan;
  }

  public void setJabatanPimpinan(String jabatanPimpinan) {
    this.jabatanPimpinan = jabatanPimpinan;
  }

  public String getPimpinanPerusahaan() {
    return pimpinanPerusahaan;
  }

  public void setPimpinanPerusahaan(String pimpinanPerusahaan) {
    this.pimpinanPerusahaan = pimpinanPerusahaan;
  }

  public Long getNilaiNegosiasi() {
    return nilaiNegosiasi;
  }

  public void setNilaiNegosiasi(Long nilaiNegosiasi) {
    this.nilaiNegosiasi = nilaiNegosiasi;
  }

  public String getJenis() {
    return jenis;
  }

  public void setJenis(String jenis) {
    this.jenis = jenis;
  }

  public Long getIdTTD() {
    return idTTD;
  }

  public void setIdTTD(Long idTTD) {
    this.idTTD = idTTD;
  }

  public String getPpk() {
    return ppk;
  }

  public void setPpk(String ppk) {
    this.ppk = ppk;
  }

  public String getNip() {
    return nip;
  }

  public void setNip(String nip) {
    this.nip = nip;
  }

  public Long getIdJenisPengadaan() {
    return idJenisPengadaan;
  }

  public void setIdJenisPengadaan(Long idJenisPengadaan) {
    this.idJenisPengadaan = idJenisPengadaan;
  }

  public String getMetodePengadaan() {
    return metodePengadaan;
  }

  public void setMetodePengadaan(String metodePengadaan) {
    this.metodePengadaan = metodePengadaan;
  }

  public String getBerdasarkanWaktuPelaksanaan() {
    return berdasarkanWaktuPelaksanaan;
  }

  public void setBerdasarkanWaktuPelaksanaan(String berdasarkanWaktuPelaksanaan) {
    this.berdasarkanWaktuPelaksanaan = berdasarkanWaktuPelaksanaan;
  }

  public String getNoPO() {
    return noPO;
  }

  public void setNoPO(String noPO) {
    this.noPO = noPO;
  }

  public String getMasaGaransiPurnaJual() {
    return masaGaransiPurnaJual;
  }

  public void setMasaGaransiPurnaJual(String masaGaransiPurnaJual) {
    this.masaGaransiPurnaJual = masaGaransiPurnaJual;
  }

  public Long getNilaiUangMuka() {
    return nilaiUangMuka;
  }

  public void setNilaiUangMuka(Long nilaiUangMuka) {
    this.nilaiUangMuka = nilaiUangMuka;
  }

  public String getSumberDana() {
    return sumberDana;
  }

  public void setSumberDana(String sumberDana) {
    this.sumberDana = sumberDana;
  }

  public String getCaraPembayaran() {
    return caraPembayaran;
  }

  public void setCaraPembayaran(String caraPembayaran) {
    this.caraPembayaran = caraPembayaran;
  }

  public String getJenisKontrak() {
    return jenisKontrak;
  }

  public void setJenisKontrak(String jenisKontrak) {
    this.jenisKontrak = jenisKontrak;
  }

  public String getNamaBank() {
    return namaBank;
  }

  public void setNamaBank(String namaBank) {
    this.namaBank = namaBank;
  }

  public String getNoRekening() {
    return noRekening;
  }

  public void setNoRekening(String noRekening) {
    this.noRekening = noRekening;
  }

  public String getRekAtasNama() {
    return rekAtasNama;
  }

  public void setRekAtasNama(String rekAtasNama) {
    this.rekAtasNama = rekAtasNama;
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

  @Override
  public String toString() {
    return "SPMKContract{" +
            "tahun=" + tahun +
            ", idSPMK=" + idSPMK +
            ", noSPMK='" + noSPMK + '\'' +
            ", tanggalSPMK=" + tanggalSPMK +
            ", tanggalMulai=" + tanggalMulai +
            ", tanggalAkhir=" + tanggalAkhir +
            ", jumlahHariPekerjaan='" + jumlahHariPekerjaan + '\'' +
            ", jumlahHariInstalasi='" + jumlahHariInstalasi + '\'' +
            ", idSPPBJ=" + idSPPBJ +
            ", idKontrak=" + idKontrak +
            ", noKontrak='" + noKontrak + '\'' +
            ", tanggalKontrak=" + tanggalKontrak +
            ", idHPS=" + idHPS +
            ", nmHPS='" + nmHPS + '\'' +
            ", namaPenyedia='" + namaPenyedia + '\'' +
            ", alamat='" + alamat + '\'' +
            ", jabatanPimpinan='" + jabatanPimpinan + '\'' +
            ", pimpinanPerusahaan='" + pimpinanPerusahaan + '\'' +
            ", nilaiNegosiasi=" + nilaiNegosiasi +
            ", jenis='" + jenis + '\'' +
            ", idTTD=" + idTTD +
            ", ppk='" + ppk + '\'' +
            ", nip='" + nip + '\'' +
            ", idJenisPengadaan=" + idJenisPengadaan +
            ", metodePengadaan='" + metodePengadaan + '\'' +
            ", berdasarkanWaktuPelaksanaan='" + berdasarkanWaktuPelaksanaan + '\'' +
            ", noPO='" + noPO + '\'' +
            ", masaGaransiPurnaJual='" + masaGaransiPurnaJual + '\'' +
            ", nilaiUangMuka=" + nilaiUangMuka +
            ", sumberDana='" + sumberDana + '\'' +
            ", caraPembayaran='" + caraPembayaran + '\'' +
            ", jenisKontrak='" + jenisKontrak + '\'' +
            ", namaBank='" + namaBank + '\'' +
            ", noRekening='" + noRekening + '\'' +
            ", rekAtasNama='" + rekAtasNama + '\'' +
            ", vendorId=" + vendorId +
            ", nilaiRataRata=" + nilaiRataRata +
            '}';
  }
}