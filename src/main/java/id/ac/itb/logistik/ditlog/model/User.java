package id.ac.itb.logistik.ditlog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "SILOG_USER")
public class User {
  @Column(name = "ID_USULAN_USER")
  private Long idUsulanUser;

  @Column(name = "STATUS")
  private char status;

  @Id
  @GeneratedValue
  @Column(name = "ID_USER")
  private Long idUser;

  @Column(name = "USER_NAME")
  private String username;

  @Column(name = "USER_PASSWORD")
  private String password;

  @Column(name = "ID_EMPLOYEES")
  private Long idEmployee;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "USER_ID_ORACLE")
  private String userIdOracle;

  @Column(name = "TGL_CREATE")
  private Date tanggalCreate;

  @Column(name = "TGL_UPDATE")
  private Date tanggalUpdate;

  @Column(name = "ID_RESPONSIBILITY")
  private Long idResponsibility;

  @Column(name = "ID_UNIT")
  private Long idUnit;

  @Column(name = "VENDOR_ID")
  private Long vendorId;

  @Column(name = "NM_USER")
  private String namaUser;

  public User() {
  }

  public User(String username, Long idResponsibility) {
    this.username = username;
    this.idResponsibility = idResponsibility;
  }

  public char getStatus() {
    return status;
  }

  public void setStatus(char status) {
    this.status = status;
  }

  public Long getIdUser() {
    return idUser;
  }

  public void setIdUser(Long idUser) {
    this.idUser = idUser;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Long getIdEmployee() {
    return idEmployee;
  }

  public void setIdEmployee(Long idEmployee) {
    this.idEmployee = idEmployee;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUserIdOracle() {
    return userIdOracle;
  }

  public void setUserIdOracle(String userIdOracle) {
    this.userIdOracle = userIdOracle;
  }

  public Date getTanggalCreate() {
    return tanggalCreate;
  }

  public void setTanggalCreate(Date tanggalCreate) {
    this.tanggalCreate = tanggalCreate;
  }

  public Date getTanggalUpdate() {
    return tanggalUpdate;
  }

  public void setTanggalUpdate(Date tanggalUpdate) {
    this.tanggalUpdate = tanggalUpdate;
  }

  public Long getIdResponsibility() {
    return idResponsibility;
  }

  public void setIdResponsibility(Long idResponsibility) {
    this.idResponsibility = idResponsibility;
  }

  public Long getIdUnit() {
    return idUnit;
  }

  public void setIdUnit(Long idUnit) {
    this.idUnit = idUnit;
  }

  public Long getVendorId() {
    return vendorId;
  }

  public void setVendorId(Long vendorId) {
    this.vendorId = vendorId;
  }

  public String getNamaUser() {
    return namaUser;
  }

  public void setNamaUser(String namaUser) {
    this.namaUser = namaUser;
  }

  public Long getIdUsulanUser() {
    return idUsulanUser;
  }

  public void setIdUsulanUser(Long idUsulanUser) {
    this.idUsulanUser = idUsulanUser;
  }

  @Override
  public String toString() {
    return "User{" +
            "idUsulanUser=" + idUsulanUser +
            ", status=" + status +
            ", idUser=" + idUser +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", idEmployee=" + idEmployee +
            ", email='" + email + '\'' +
            ", userIdOracle='" + userIdOracle + '\'' +
            ", tanggalCreate=" + tanggalCreate +
            ", tanggalUpdate=" + tanggalUpdate +
            ", idResponsibility=" + idResponsibility +
            ", idUnit=" + idUnit +
            ", vendorId=" + vendorId +
            ", namaUser='" + namaUser + '\'' +
            '}';
  }
}