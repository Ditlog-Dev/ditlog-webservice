package id.ac.itb.logistik.ditlog.controller;

import id.ac.itb.logistik.ditlog.model.*;
import id.ac.itb.logistik.ditlog.repository.RealisasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

@RestController
public class RealisasiController {
    @Autowired
    RealisasiRepository realisasiRepository;

    Map<Long,String> ROLE = RoleConstant.ROLE;

    @GetMapping("/realisasi/{idSpmk}")
    public ResponseEntity<BaseResponse> getByIdSpmk(HttpServletRequest request,
                                                    @PathVariable("idSpmk") Long idSpmk
    ) {
        BaseResponse baseResponse = new BaseResponse();
        User user = (User) request.getAttribute("user");
        ArrayList<Milestone> results = new ArrayList<Milestone>();
        //System.out.println(user);
        Iterable<Milestone> resultsMilestone = new Iterable<Milestone>() {
            @Override
            public Iterator<Milestone> iterator() { return null; }
        };
        resultsMilestone = realisasiRepository.findByIdSPMK(idSpmk);

        for (Milestone resultMilestone : resultsMilestone) {
            if (resultMilestone.getStatusRencana() == null &&
                    resultMilestone.getStatusRealisasi() == null)
                results.add(resultMilestone);
        }

        if(results.spliterator().getExactSizeIfKnown() == 0){
            throw new EntityNotFoundException(Milestone.class.getSimpleName());
        }

        baseResponse.setStatus(true);
        baseResponse.setCode(HttpStatus.OK.value());
        baseResponse.setPayload(results);

        return ResponseEntity.ok(baseResponse);
    }
}
