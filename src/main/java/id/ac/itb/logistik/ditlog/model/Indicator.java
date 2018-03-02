package id.ac.itb.logistik.ditlog.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "INDIKATOR_PENILAIAN")
public class Indicator {

    @Id
    @GeneratedValue
    @Column(name = "ID_INDIKATOR")
    Long id;

    @Column(name = "NAMA_INDIKATOR")
    String name;

    @Column(name = "TGL_BUAT")
    Date dateCreated;

    @Column(name = "TGL_UPDATE")
    Date dateModified;

    @Column(name = "ID_USER")
    Long idUser;


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
}
