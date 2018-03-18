package id.ac.itb.logistik.ditlog.repository;

import id.ac.itb.logistik.ditlog.model.SPMKContract;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SPMKContractRepository extends PagingAndSortingRepository<SPMKContract, Long> {

}
