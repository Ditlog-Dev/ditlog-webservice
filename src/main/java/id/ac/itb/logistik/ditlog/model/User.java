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
  @Id
  @GeneratedValue
  @Column(name = "ID_USER")
  private Long idUser;

  @Column(name = "USER_NAME")
  private String username;

  @Column(name = "USER_PASSWORD")
  private String password;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "ID_RESPONSIBILITY")
  private Long idResponsibility;

  @Column(name = "VENDOR_ID")
  private Long vendorId;

  public User() {
  }

  public User(String username, Long idResponsibility) {
    this.username = username;
    this.idResponsibility = idResponsibility;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Long getIdResponsibility() {
    return idResponsibility;
  }

  public void setIdResponsibility(Long idResponsibility) {
    this.idResponsibility = idResponsibility;
  }

  public Long getVendorId() {
    return vendorId;
  }

  public void setVendorId(Long vendorId) {
    this.vendorId = vendorId;
  }


  @Override
  public String toString() {
    return "User{" +
            ", idUser=" + idUser +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", email='" + email + '\'' +
            ", idResponsibility=" + idResponsibility +
            ", vendorId=" + vendorId +
            '}';
  }
}
