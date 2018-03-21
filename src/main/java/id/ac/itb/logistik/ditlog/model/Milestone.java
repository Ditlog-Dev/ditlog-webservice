package id.ac.itb.logistik.ditlog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "PROGRES_JASA")
public class Milestone {

    @Id
    @Column(name = "ID_PROGRES", unique = true, nullable = false)
    private Long idProgres = 0L;

    @Column(name = "ID_DETAIL")
    private Long idDetail;

    @Column(name = "ID_SPMK")
    private Long idSPMK;

    @Column(name = "TGL_RENCANA")
    private Date tglRencana = new Date();

    @Column(name = "TGL_REALISASI")
    private Date tglRealisasi;

    @Column(name = "PROS_RENCANA")
    private String persentaseRencana;

    @Column(name = "PROS_REALISASI")
    private String persentaseRealisasi;

    @Column(name = "KET_RENCANA")
    private String keteranganRencana;

    @Column(name = "KET_REALISASI")
    private String keteranganRealisasi;

    @JsonIgnore
    @Column(name = "TGL_BUAT")
    private Date tglBuat;

    @JsonIgnore
    @Column(name = "TGL_UPDATE")
    private Date tglUpdate;

    @Column(name = "ID_USER")
    private Long idUser;

    @Column(name = "STATUS_RENCANA")
    private String statusRencana;

    @Column(name = "STATUS_REALISASI")
    private String statusRealisasi;

    public Date getTglRencana() {
        return tglRencana;
    }

    public Date getTglBuat() {
        return tglBuat;
    }

    public Date getTglRealisasi() {
        return tglRealisasi;
    }

    public Date getTglUpdate() {
        return tglUpdate;
    }

    public Long getIdDetail() {
        return idDetail;
    }

    public Long getIdProgres() {
        return idProgres;
    }

    public Long getIdSPMK() {
        return idSPMK;
    }

    public Long getIdUser() {
        return idUser;
    }

    public String getKeteranganRealisasi() {
        return keteranganRealisasi;
    }

    public String getKeteranganRencana() {
        return keteranganRencana;
    }

    public String getPersentaseRealisasi() {
        return persentaseRealisasi;
    }

    public String getPersentaseRencana() {
        return persentaseRencana;
    }

    public String getStatusRealisasi() {
        return statusRealisasi;
    }

    public String getStatusRencana() {
        return statusRencana;
    }

    public void setIdDetail(Long idDetail) {
        this.idDetail = idDetail;
    }

    public void setIdProgres(Long idProgres) {
        this.idProgres = idProgres;
    }

    public void setIdSPMK(Long idSPMK) {
        this.idSPMK = idSPMK;
    }

    public void setKeteranganRealisasi(String keteranganRealisasi) {
        this.keteranganRealisasi = keteranganRealisasi;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public void setKeteranganRencana(String keteranganRencana) {
        this.keteranganRencana = keteranganRencana;
    }

    public void setPersentaseRealisasi(String persentaseRealisasi) {
        this.persentaseRealisasi = persentaseRealisasi;
    }

    public void setPersentaseRencana(String persentaseRencana) {
        this.persentaseRencana = persentaseRencana;
    }

    public void setTglRealisasi(Date tglRealisasi) {
        this.tglRealisasi = tglRealisasi;
    }

    public void setTglRencana(Date tglRencana) {
        this.tglRencana = tglRencana;
    }

    public void setTglBuat(Date tglBuat) {
        this.tglBuat = tglBuat;
    }

    public void setStatusRealisasi(String statusRealisasi) {
        this.statusRealisasi = statusRealisasi;
    }

    public void setStatusRencana(String statusRencana) {
        this.statusRencana = statusRencana;
    }

    public void setTglUpdate(Date tglUpdate) {
        this.tglUpdate = tglUpdate;
    }

    @Override
    public String toString() {
        return "Milestone{" +
                "idProgres=" + idProgres +
                ", idSPMK=" + idSPMK +
                '}';
    }
}
