package id.ac.itb.logistik.ditlog.repository;

import id.ac.itb.logistik.ditlog.model.PenilaianIdentity;
import id.ac.itb.logistik.ditlog.model.PenilaianKinerja;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

public interface PenilaianRepository extends CrudRepository<PenilaianKinerja, PenilaianIdentity> {
    @Query(value = "SELECT PENILAIAN_KINERJA.* FROM PENILAIAN_KINERJA WHERE ID_KONTRAK=?1", nativeQuery = true)
    Iterable<PenilaianKinerja> findAllByIdContract(Long id);
}
