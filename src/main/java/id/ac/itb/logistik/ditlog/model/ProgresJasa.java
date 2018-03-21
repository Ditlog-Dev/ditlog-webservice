package id.ac.itb.logistik.ditlog.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PROGRES_JASA")
public class ProgresJasa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_PROGRES")
    private Long idProgres;

    @Column(name = "ID_DETAIL")
    private Long idDetail;

    @Column(name = "ID_SPMK")
    private Long idSpmk;

    @Column(name = "TGL_RENCANA")
    private Date tglRencana;

    @Column(name = "TGL_REALISASI")
    private Date tglRealisasi;

    @Column(name = "PROS_RENCANA")
    private Integer prosRencana;

    @Column(name = "PROS_REALISASI")
    private Integer prosRealisasi;

    @Column(name = "KET_RENCANA")
    private String ketRencana;

    @Column(name = "KET_REALISASI")
    private String ketRealisasi;

    @Column(name = "TGL_BUAT")
    private Date tglBuat;

    @Column(name = "TGL_UPDATE")
    private Date tglUpdate;

    @Column(name = "ID_USER")
    private Long idUser;

    @Column(name = "STATUS_RENCANA")
    private String statusRencana;

    @Column(name = "STATUS_REALISASI")
    private String statusRealisasi;

    public Long getIdProgres() {
        return idProgres;
    }

    public void setIdProgres(Long idProgres) {
        this.idProgres = idProgres;
    }

    public Long getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(Long idDetail) {
        this.idDetail = idDetail;
    }

    public Long getIdSpmk() {
        return idSpmk;
    }

    public void setIdSpmk(Long idSpmk) {
        this.idSpmk = idSpmk;
    }

    public Date getTglRencana() {
        return tglRencana;
    }

    public void setTglRencana(Date tglRencana) {
        this.tglRencana = tglRencana;
    }

    public Date getTglRealisasi() {
        return tglRealisasi;
    }

    public void setTglRealisasi(Date tglRealisasi) {
        this.tglRealisasi = tglRealisasi;
    }

    public Integer getProsRencana() {
        return prosRencana;
    }

    public void setProsRencana(Integer prosRencana) {
        this.prosRencana = prosRencana;
    }

    public Integer getProsRealisasi() {
        return prosRealisasi;
    }

    public void setProsRealisasi(Integer prosRealisasi) {
        this.prosRealisasi = prosRealisasi;
    }

    public String getKetRencana() {
        return ketRencana;
    }

    public void setKetRencana(String ketRencana) {
        this.ketRencana = ketRencana;
    }

    public String getKetRealisasi() {
        return ketRealisasi;
    }

    public void setKetRealisasi(String ketRealisasi) {
        this.ketRealisasi = ketRealisasi;
    }

    public Date getTglBuat() {
        return tglBuat;
    }

    public void setTglBuat(Date tglBuat) {
        this.tglBuat = tglBuat;
    }

    public Date getTglUpdate() {
        return tglUpdate;
    }

    public void setTglUpdate(Date tglUpdate) {
        this.tglUpdate = tglUpdate;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getStatusRencana() {
        return statusRencana;
    }

    public void setStatusRencana(String statusRencana) {
        this.statusRencana = statusRencana;
    }

    public String getStatusRealisasi() {
        return statusRealisasi;
    }

    public void setStatusRealisasi(String statusRealisasi) {
        this.statusRealisasi = statusRealisasi;
    }
}
