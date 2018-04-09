package id.ac.itb.logistik.ditlog.repository;

import id.ac.itb.logistik.ditlog.model.Milestone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealisasiRepository extends PagingAndSortingRepository<Milestone, Long> {
    @Query(value = "SELECT * FROM PROGRES_JASA WHERE STATUS_RENCANA=1 AND ID_PROGRES = ?1", nativeQuery = true)
    Milestone findById(Long idProgres);

    @Query(value = "SELECT * FROM PROGRES_JASA WHERE STATUS_RENCANA=1 AND ID_USER = ?1", nativeQuery = true)
    Iterable<Milestone> findByIdUser(Long idUser);

    @Query(value = "SELECT * FROM PROGRES_JASA WHERE STATUS_RENCANA=1 AND ID_SPMK = ?1", nativeQuery = true)
    Iterable<Milestone> findByIdSPMK(Long idSPMK);
}