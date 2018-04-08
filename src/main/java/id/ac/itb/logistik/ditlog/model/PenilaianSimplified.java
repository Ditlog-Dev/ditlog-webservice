package id.ac.itb.logistik.ditlog.model;

import java.math.BigDecimal;

public class PenilaianSimplified {
    private Long idIndikator;
    private BigDecimal nilai;

    public PenilaianSimplified() {
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
}
