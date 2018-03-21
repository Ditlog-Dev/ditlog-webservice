package id.ac.itb.logistik.ditlog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "PROGRES_JASA")
public class TugasPemeriksa implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID_TUGAS", unique = true, nullable = false)
    private Long idTugas;

    @Column(name="ID_KONTRAK")
    private Long idKontrak;

    @Column(name="ID_DETAIL")
    private Long idDetail;

    @Column(name="ID_PEMERIKSA")
    private Long idPemeriksa;

    @Column(name="APPROVER")
    private Long approver;

    @JsonIgnore
    @Column(name="TGL_BUAT")
    private Date tglBuat;

    @JsonIgnore
    @Column(name="TGL_UPDATE")
    private Date tglUpdate;

    @Column(name="ID_USER")
    private Long idUser;

    @Column(name="JENIS_PAKET")
    private String jenisPaket;

    public Long getIdDetail() {
        return idDetail;
    }

    public Long getIdUser() {
        return idUser;
    }

    public Date getTglUpdate() {
        return tglUpdate;
    }

    public Date getTglBuat() {
        return tglBuat;
    }

    public Long getApprover() {
        return approver;
    }

    public Long getIdKontrak() {
        return idKontrak;
    }

    public Long getIdPemeriksa() {
        return idPemeriksa;
    }

    public Long getIdTugas() {
        return idTugas;
    }

    public String getJenisPaket() {
        return jenisPaket;
    }

    public void setTglUpdate(Date tglUpdate) {
        this.tglUpdate = tglUpdate;
    }

    public void setTglBuat(Date tglBuat) {
        this.tglBuat = tglBuat;
    }

    public void setIdDetail(Long idDetail) {
        this.idDetail = idDetail;
    }

    public void setApprover(Long approver) {
        this.approver = approver;
    }

    public void setIdKontrak(Long idKontrak) {
        this.idKontrak = idKontrak;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public void setIdPemeriksa(Long idPemeriksa) {
        this.idPemeriksa = idPemeriksa;
    }

    public void setIdTugas(Long idTugas) {
        this.idTugas = idTugas;
    }

    public void setJenisPaket(String jenisPaket) {
        this.jenisPaket = jenisPaket;
    }
}
