package id.ac.itb.logistik.ditlog.controller;

import id.ac.itb.logistik.ditlog.model.BaseResponse;
import id.ac.itb.logistik.ditlog.model.ProgresJasa;
import id.ac.itb.logistik.ditlog.repository.ProgresJasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProgresJasaController {

    @Autowired
    ProgresJasaRepository progresJasaRepository;

    @GetMapping("/spmk/{id}")
    public ResponseEntity<BaseResponse> getById(@PathVariable("id") Long id){

        ProgresJasa result = progresJasaRepository.findProgresJasaById(id);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(true);
        baseResponse.setCode(HttpStatus.OK.value());
        baseResponse.setMessage(id.toString());
        baseResponse.setPayload(result);
        return ResponseEntity.ok(baseResponse);
    }

    @Transactional
    @PostMapping("/spmk")
    public ResponseEntity<BaseResponse> setApprove(@RequestParam(value = "idSpmk") Long idSpmk,
                                                   @RequestParam(value = "status") Long status) {
        BaseResponse baseResponse = new BaseResponse();

        Iterable<ProgresJasa> list = progresJasaRepository.findAllByIdSpmk(idSpmk);

        list.forEach(progresJasa -> progresJasa.setStatusRencana(status.toString()));

        progresJasaRepository.save(list);

//        list.forEach(progresJasa -> progresJasaRepository.updateStatusById(status, idSpmk););
//        int result = progresJasaRepository.updateStatusById(status, idSpmk);
//        progresJasaRepository.save(list);

        baseResponse.setStatus(true);
        baseResponse.setCode(HttpStatus.OK.value());
//        baseResponse.setMessage(id.toString());
        baseResponse.setPayload(list);
        System.out.println(baseResponse);
        return ResponseEntity.ok(baseResponse);
    }
}
