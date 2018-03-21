package id.ac.itb.logistik.ditlog.repository;

import id.ac.itb.logistik.ditlog.model.Milestone;
import id.ac.itb.logistik.ditlog.model.SPMKContract;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SPMKContractRepository extends CrudRepository<SPMKContract, Long> {

    @Query(value = "SELECT * FROM V_SPMK_ANDROID WHERE ID_KONTRAK = ?1", nativeQuery = true)
    SPMKContract findContractById(Long id);
    @Query(value = "SELECT * FROM V_SPMK_ANDROID WHERE TAHUN = ?1", nativeQuery = true)
    Iterable<SPMKContract> findAllByYear(Long year);
    @Query(value = "SELECT * FROM V_SPMK_ANDROID WHERE JENIS = ?1", nativeQuery = true)
    Iterable<SPMKContract> findAllByTag(String tag);
    @Query(value = "SELECT * FROM V_SPMK_ANDROID WHERE JENIS = ?1 AND TAHUN = ?2", nativeQuery = true)
    Iterable<SPMKContract> findAllByTagAndYear(String tag, Long year);
    @Query(value = "SELECT * FROM V_SPMK_ANDROID WHERE VENDOR_ID = ?1", nativeQuery = true)
    Iterable<SPMKContract> findByIdVendor(Long idVendor);
}
