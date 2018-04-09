package id.ac.itb.logistik.ditlog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "PENILAIAN_KINERJA")
public class PenilaianKinerja implements Serializable{

    @JsonIgnore
    @EmbeddedId
    public PenilaianIdentity penilaianIdentity;

    @Transient
    private Long idIndicator;

    @Column(name = "NILAI")
    private BigDecimal nilai = BigDecimal.ZERO;

    @JsonIgnore
    @Column(name = "TGL_BUAT")
    private Date dateCreated = new Date();

    @JsonIgnore
    @Column(name = "TGL_UPDATE")
    private Date dateUpdated = new Date();

    @JsonIgnore
    @Column(name = "ID_USER")
    private Long idUser;

    @Transient
    private String namaIndikator;

    public PenilaianKinerja() {
    }

    public PenilaianKinerja(PenilaianIdentity penilaianIdentity) {
        this.penilaianIdentity = penilaianIdentity;
        setIdIndicator();
    }

    public Long getIdIndicator() {
        return idIndicator;
    }

    public void setIdIndicator(Long idIndicator) {
        this.idIndicator = idIndicator;
    }
    public void setIdIndicator() {
        this.idIndicator = penilaianIdentity.getIdIndikator();
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

    public String getNamaIndikator() {
        return namaIndikator;
    }

    public void setNamaIndikator(String namaIndikator) {
        this.namaIndikator = namaIndikator;
    }
}
