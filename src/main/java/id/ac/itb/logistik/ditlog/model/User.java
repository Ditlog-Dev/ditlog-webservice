package id.ac.itb.logistik.ditlog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SILOG_USER")
public class User {

  @Id
  @GeneratedValue
  @Column(name = "ID_USER")
  Long idUser;

  @Column(name = "USER_NAME")
  String username;

  @Column(name = "USER_PASSWORD")
  String password;

  @Column(name = "ID_EMPLOYEES")
  Long idEmployee;

  @Column(name = "ID_RESPONSIBILITY")
  Long idResponsibility;

  @Column(name = "VENDOR_ID")
  Long idVendor;

  public User() {
  }

  public User(String username, Long idUser) {
    this.username = username;
    this.idUser = idUser;
  }

  public String getUsername() {
    return username;
  }

  public Long getIdUser() {
    return idUser;
  }

  public String getPassword() {
    return password;
  }

  public Long getIdEmployee() {
    return idEmployee;
  }

  public Long getIdResponsibility() {
    return idResponsibility;
  }

  public Long getIdVendor() {
    return idVendor;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setIdUser(Long id_user) {
    this.idUser = id_user;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setIdEmployee(Long idEmployee) {
    this.idEmployee = idEmployee;
  }

  public void setIdResponsibility(Long idResponsibility) {
    this.idResponsibility = idResponsibility;
  }

  public void setIdVendor(Long idVendor) {
    this.idVendor = idVendor;
  }
}
