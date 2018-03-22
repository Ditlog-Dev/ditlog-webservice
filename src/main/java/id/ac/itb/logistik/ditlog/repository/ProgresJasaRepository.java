package id.ac.itb.logistik.ditlog.repository;

import id.ac.itb.logistik.ditlog.model.ProgresJasa;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProgresJasaRepository extends CrudRepository<ProgresJasa, Long> {

    @Query(value = "SELECT * FROM PROGRES_JASA WHERE ID_PROGRES = ?1", nativeQuery = true)
    ProgresJasa findProgresJasaById(Long id);

    @Query(value = "SELECT * FROM PROGRES_JASA WHERE ID_SPMK = ?1", nativeQuery = true)
    Iterable<ProgresJasa> findAllByIdSpmk(Long idSpmk);

    @Modifying
    @Transactional
    @Query(value = "UPDATE PROGRES_JASA SET STATUS_RENCANA = ?1 WHERE ID_SPMK = ?2", nativeQuery = true)
    int updateStatusById(Long status, Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ProgresJasa p SET p.statusRencana = ?1 WHERE p.idSpmk = ?2", nativeQuery = true)
    int updateStatusById2(Long status, Long id);
}
