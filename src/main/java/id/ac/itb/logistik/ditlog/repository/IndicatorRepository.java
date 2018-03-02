package id.ac.itb.logistik.ditlog.repository;

import id.ac.itb.logistik.ditlog.model.Indicator;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Just dummy
 */
@Repository
public class IndicatorRepository {
    private List<Indicator> indicators;

    public IndicatorRepository() {
        this.indicators = new ArrayList<>();
        for(int i=1; i<10; i++){
            Indicator indicator = new Indicator();
            indicator.setId((long) i);
            indicator.setName("Indicator "+String.valueOf(i));
            this.indicators.add(indicator);
        }
    }


    public List<Indicator> getIndicators() {
        return indicators;
    }
}
