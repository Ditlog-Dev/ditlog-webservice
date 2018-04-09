package id.ac.itb.logistik.ditlog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.HashMap;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INDIKATOR_PENILAIAN")
public class Indicator {

  @Id
  @Column(name = "ID_INDIKATOR", unique = true, nullable = false)
  private Long id = 0L;

  @Column(name = "NAMA_INDIKATOR")
  private String name;

  @JsonIgnore
  @Column(name = "TGL_BUAT")
  private Date dateCreated = new Date();

  @JsonIgnore
  @Column(name = "TGL_UPDATE")
  private Date dateModified = new Date();

  @Column(name = "ID_USER")
  private Long idUser;

  public Indicator() {
  }

  public Indicator(Long id) {
    this.id = id;
  }

  public Indicator(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  public Date getDateModified() {
    return dateModified;
  }

  public void setDateModified(Date dateModified) {
    this.dateModified = dateModified;
  }

  public Long getIdUser() {
    return idUser;
  }

  public void setIdUser(Long idUser) {
    this.idUser = idUser;
  }

  public HashMap<String, String> toMap() {
    HashMap<String, String> map = new HashMap<>();

    map.put("id", id.toString());
    map.put("name", name);
    map.put("dateCreated", dateCreated.toString());
    map.put("dateModified", dateModified.toString());
    map.put("idUser", idUser.toString());

    return map;
  }

  @Override
  public String toString() {
    return "Indicator [id="
        + id
        + ", name="
        + name
        + ", idUser="
        + idUser
        + ", dateCreated="
        + dateCreated
        + ", dateModified="
        + dateModified
        + "]";
  }
}
