package id.ac.itb.logistik.ditlog;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.ac.itb.logistik.ditlog.model.*;
import id.ac.itb.logistik.ditlog.repository.IndicatorRepository;
import id.ac.itb.logistik.ditlog.repository.PenilaianRepository;
import id.ac.itb.logistik.ditlog.repository.SPMKContractRepository;
import id.ac.itb.logistik.ditlog.service.TokenAuthenticationService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
public class DitlogPenilaianTests extends BaseTest{

    @Autowired
    IndicatorRepository indicatorRepository;
    @Autowired
    SPMKContractRepository spmkContractRepository;
    @Autowired
    PenilaianRepository penilaianRepository;
    private static Indicator indicator1, indicator2;
    private static PenilaianKinerja penilaianKinerja1, penilaianKinerja2;
    private static SPMKContract contract;
    private static User userKasubdit;
    private static String bearerAuth;
    private static boolean setUpIsDone = false;

    @Before
    public void setUp() {
        if (setUpIsDone) {
            return;
        }
        String dateString;
        Date date = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        contract = new SPMKContract();
        contract.setIdKontrak(5L);
        contract.setNoKontrak("1234");
        dateString = "2018-09-09";
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        contract.setTanggalKontrak(date);
        contract.setJenis("BARANG");
        spmkContractRepository.save(contract);

        indicator1 = new Indicator();
        indicator1.setId(1L);
        indicator1.setName("test indicator");
        indicator2 = new Indicator();
        indicator2.setId(2L);
        indicator2.setName("test indicator 2");
        indicatorRepository.save(indicator1);
        indicatorRepository.save(indicator2);

        userKasubdit = new User("john kasubdit", RoleConstant.KASUBDIT_PEMERIKSA);
        userRepo.save(userKasubdit);

        penilaianKinerja1 = new PenilaianKinerja(new PenilaianIdentity(5L,1L));
        penilaianKinerja2 = new PenilaianKinerja(new PenilaianIdentity(5L,2L));

        bearerAuth = TokenAuthenticationService.TOKEN_PREFIX + " " + TokenAuthenticationService.getJWT(userKasubdit);
        setUpIsDone = true;
    }

    @Before
    public void setUpHeader() {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(
                TokenAuthenticationService.HEADER_STRING,
                bearerAuth
        );
    }

    // Add
    // Add update
    // Add update add
    // Add delete
    // Add delete delete
    // Add modify delete add
    // Update
    // Delete


    @Test
    public void addPenilaian() throws JSONException {
        List<Long> indicatorIdList = new ArrayList<>();
        indicatorIdList.add(1L);
        indicatorIdList.add(2L);
        HttpEntity<List<Long>> entity = new HttpEntity<>(indicatorIdList, headers);
        ResponseEntity<String> response =
                restTemplate.postForEntity(createURLWithPort("/contracts/"+contract.getIdKontrak()+"/indicators"), entity, String.class);
        JSONObject result = new JSONObject(response.getBody());

        // Assertion
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(2,result.getJSONArray("payload").length());
//        JSONAssert.assertEquals(jsonIndicator2, result.getJSONObject("payload"), false);
    }
}
