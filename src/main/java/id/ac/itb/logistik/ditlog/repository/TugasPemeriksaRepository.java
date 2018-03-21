package id.ac.itb.logistik.ditlog.repository;

import id.ac.itb.logistik.ditlog.model.Milestone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TugasPemeriksaRepository extends CrudRepository<Milestone, Long> {

    @Query(value = "SELECT * FROM TUGAS_PEMERIKSA WHERE ID_PEMERIKSA = ?1", nativeQuery = true)
    Milestone findByIdPemeriksa(Long idPemeriksa);
}
