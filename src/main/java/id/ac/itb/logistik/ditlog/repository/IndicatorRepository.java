package id.ac.itb.logistik.ditlog.repository;

import id.ac.itb.logistik.ditlog.model.Indicator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicatorRepository extends CrudRepository<Indicator, Long> {
}
