package id.ac.itb.logistik.ditlog.repository;

import id.ac.itb.logistik.ditlog.model.ProgresJasa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgresJasaRepository extends CrudRepository<ProgresJasa, Long> {

    @Query(value = "SELECT * FROM PROGRES_JASA WHERE ID_PROGRES = ?1", nativeQuery = true)
    ProgresJasa findProgresJasaById(Long id);

    @Query(value = "SELECT * FROM PROGRES_JASA WHERE ID_SPMK = ?1", nativeQuery = true)
    Iterable<ProgresJasa> findAllByIdSpmk(Long idSpmk);

}
