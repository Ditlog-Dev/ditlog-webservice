package id.ac.itb.logistik.ditlog.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class PenilaianIdentity implements Serializable {
//    @NotNull
    @Column(name = "ID_KONTRAK")
    private Long idKontrak;

//    @NotNull
    @Column(name = "ID_INDIKATOR")
    private Long idIndikator;

    public PenilaianIdentity() {
    }

    public PenilaianIdentity(Long idKontrak, Long idIndikator) {
        this.idKontrak = idKontrak;
        this.idIndikator = idIndikator;
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

    @Override
    public String toString() {
        return "PenilaianIdentity{" +
                "idKontrak=" + idKontrak +
                ", idIndikator=" + idIndikator +
                '}';
    }
}
