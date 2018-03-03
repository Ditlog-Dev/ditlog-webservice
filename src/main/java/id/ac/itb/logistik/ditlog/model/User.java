package id.ac.itb.logistik.ditlog.model;


import javax.persistence.*;

@Entity
@Table(name = "SILOG_USER")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID_USER")
    Long id_user;

    @Column(name = "USER_NAME")
    String username;

    @Column(name = "USER_PASSWORD")
    String password;

    @Column(name = "ID_EMPLOYEES")
    Long id_employee;

    @Column(name = "EMAIL")
    String email;

    public String getUsername() {
        return username;
    }

    public Long getId_user() {
        return id_user;
    }

    public String getPassword() {
        return password;
    }

    public Long getId_employee() {
        return id_employee;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId_employee(Long id_employee) {
        this.id_employee = id_employee;
    }
}
