package id.ac.itb.logistik.ditlog.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "PENILAIAN_KINERJA")
public class PenilaianKinerja implements Serializable{

    @Id
    @Column(name = "ROWID")
    public String id;

    @Column(name = "ID_KONTRAK")
    private Long idKontrak = 0L;

    @Column(name = "ID_INDIKATOR")
    private Long idIndikator = 0L;

    @Column(name = "NILAI")
    private BigDecimal nilai = BigDecimal.ZERO;

    @Column(name = "TGL_BUAT")
    private Date dateCreated = new Date();

    @Column(name = "TGL_UPDATE")
    private Date dateUpdated = new Date();

    @Column(name = "ID_USER")
    private Long idUser;

    public PenilaianKinerja() {
    }

    public PenilaianKinerja(Long idKontrak, Long idIndikator, BigDecimal nilai) {
        this.idKontrak = idKontrak;
        this.idIndikator = idIndikator;
        this.nilai = nilai;
    }

    public Long getIdKontrak() {
        return idKontrak;
    }

    public void setIdKontrak(Long idKontrak) {
        this.idKontrak = idKontrak;
    }

    public Long getIdIndikator() {
        return idIndikator;
    }

    public void setIdIndikator(Long idIndikator) {
        this.idIndikator = idIndikator;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
