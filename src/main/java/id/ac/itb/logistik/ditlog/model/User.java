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

  @Column(name = "EMAIL")
  String email;

  public User() {
  }

  public User(String username, Long idEmployee) {
    this.username = username;
    this.idEmployee = idEmployee;
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

  public String getEmail() {
    return email;
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

  public void setEmail(String email) {
    this.email = email;
  }

  public void setIdEmployee(Long id_employee) {
    this.idEmployee = id_employee;
  }
}
