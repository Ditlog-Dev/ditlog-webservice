package id.ac.itb.logistik.ditlog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PROGRES_JASA")
public class Milestone {

    @Id
    @Column(name = "ID_PROGRES")
    private Long idProgres;

    @Column(name = "ID_SPMK")
    private Long idSPMK;

    @Column(name = "TGL_RENCANA")
    private Date tglRencana;

    @Column(name = "TGL_REALISASI")
    private Date tglRealisasi;

    @Column(name = "PROS_RENCANA")
    private int persentaseRencana;

    @Column(name = "PROS_REALISASI")
    private int persentaseRealisasi;

    @Column(name = "KET_RENCANA")
    private String keteranganRencana;

    @Column(name = "KET_REALISASI")
    private String keteranganRealisasi;

    @Column(name = "ALASAN_REJECT_RENCANA")
    private String alasanReject;

    @Column(name = "ID_USER")
    private Long idUser;

    @Column(name = "STATUS_RENCANA")
    private String statusRencana;

    @Column(name = "STATUS_REALISASI")
    private String statusRealisasi;

    public String getAlasanReject() {
        return alasanReject;
    }

    public Date getTglRencana() {
        return tglRencana;
    }

    public Date getTglRealisasi() {
        return tglRealisasi;
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

    public int getPersentaseRealisasi() {
        return persentaseRealisasi;
    }

    public int getPersentaseRencana() {
        return persentaseRencana;
    }

    public String getStatusRealisasi() {
        return statusRealisasi;
    }

    public String getStatusRencana() {
        return statusRencana;
    }

    public void setAlasanReject(String alasanReject) {
        this.alasanReject = alasanReject;
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

    public void setPersentaseRealisasi(int persentaseRealisasi) {
        this.persentaseRealisasi = persentaseRealisasi;
    }

    public void setPersentaseRencana(int persentaseRencana) {
        this.persentaseRencana = persentaseRencana;
    }

    public void setTglRealisasi(Date tglRealisasi) {
        this.tglRealisasi = tglRealisasi;
    }

    public void setTglRencana(Date tglRencana) {
        this.tglRencana = tglRencana;
    }

    public void setStatusRealisasi(String statusRealisasi) {
        this.statusRealisasi = statusRealisasi;
    }

    public void setStatusRencana(String statusRencana) {
        this.statusRencana = statusRencana;
    }

    @Override
    public String toString() {
        return "Milestone{" +
                "idProgres=" + idProgres +
                ", idSPMK=" + idSPMK +
                ", tanggalRencana=" + tglRencana +
                ", tanggalRealisasi=" + tglRealisasi +
                ", prosRencana=" + persentaseRencana +
                ", prosRealisasi=" + persentaseRealisasi +
                ", ketRencana=" + keteranganRencana +
                ", ketRealisasi=" + keteranganRealisasi +
                ", statusRencana=" + statusRencana +
                ", statusRealisasi=" + statusRealisasi +
                ", alasanReject=" + alasanReject +
                ", idUser=" + idUser +
                '}';
    }
}
