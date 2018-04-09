package id.ac.itb.logistik.ditlog;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.ac.itb.logistik.ditlog.model.*;
import id.ac.itb.logistik.ditlog.repository.IndicatorRepository;
import id.ac.itb.logistik.ditlog.repository.PenilaianRepository;
import id.ac.itb.logistik.ditlog.repository.SPMKContractRepository;
import id.ac.itb.logistik.ditlog.service.TokenAuthenticationService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    private static Long testCount;
    private static List<Indicator> indicators;
    private static List<PenilaianKinerja> penilaianKinerjaList;
    private static SPMKContract contract;
    private static User userKasubdit;
    private static String bearerAuth;
    private static boolean setUpIsDone = false;

    @Before
    public void setUp() {
        if (setUpIsDone) {
            return;
        }
        contract = new SPMKContract();
        contract.setIdKontrak(1L);
        contract.setNoKontrak("1234");
        contract.setJenis("BARANG");
        spmkContractRepository.save(contract);

        indicators = new ArrayList<>();
        penilaianKinerjaList = new ArrayList<>();
        testCount = 100L;
        for(Long i = 0L; i<testCount; i++){
            Indicator indicator = new Indicator(i+1L, "test indicator " + Long.toString(i+1));
            penilaianKinerjaList.add(new PenilaianKinerja(new PenilaianIdentity(1L,i+1)));
            indicators.add(indicator);
            indicatorRepository.save(indicator);
        }
        penilaianRepository.save(penilaianKinerjaList.get(0));

        userKasubdit = new User("john kasubdit", RoleConstant.KASUBDIT_PEMERIKSA);
        userRepo.save(userKasubdit);

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

    // Get All
    // Add
    // Add update
    // Add update add
    // Add delete
    // Add delete delete
    // Add modify delete add
    // Update
    // Delete

    @Test
    public void getAllPenilaian() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response =
                restTemplate.exchange(createURLWithPort("/contracts/"+contract.getIdKontrak()+"/indicators"), HttpMethod.GET, entity, String.class);
        JSONObject result = new JSONObject(response.getBody());

        // Assertion
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(true,result.getJSONArray("payload").length() >= 1);
    }

    @Test
    public void addPenilaian() throws JSONException {
        List<Long> indicatorIdList = new ArrayList<>();
        indicatorIdList.add(penilaianKinerjaList.get(1).getIdIndicator());
        HttpEntity<List<Long>> entity = new HttpEntity<>(indicatorIdList, headers);
        ResponseEntity<String> response =
                restTemplate.postForEntity(createURLWithPort("/contracts/"+contract.getIdKontrak()+"/indicators"), entity, String.class);
        JSONObject result = new JSONObject(response.getBody());

        // Assertion
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(1,result.getJSONArray("payload").length());
        Assert.assertEquals(
                0,
                result.getJSONArray("payload").getJSONObject(0).getInt("nilai")
        );
        Assert.assertEquals(
                penilaianKinerjaList.get(1).getIdIndicator().intValue(),
                result.getJSONArray("payload").getJSONObject(0).getInt("idIndicator")
        );
    }

    @Test
    public void addAndUpdatePenilaian() throws JSONException {
        List<Long> indicatorIdList = new ArrayList<>();
        List<PenilaianSimplified> penilaianList = new ArrayList<>();
        indicatorIdList.add(penilaianKinerjaList.get(2).getIdIndicator());
        PenilaianSimplified penilaian = new PenilaianSimplified(penilaianKinerjaList.get(2).getIdIndicator());
        penilaian.setNilai(BigDecimal.valueOf(10));
        penilaianList.add(penilaian);
        HttpEntity<List<Long>> entity = new HttpEntity<>(indicatorIdList, headers);
        ResponseEntity<String> response =
                restTemplate.postForEntity(createURLWithPort("/contracts/"+contract.getIdKontrak()+"/indicators"), entity, String.class);
        JSONObject result = new JSONObject(response.getBody());

        // Adding penilaian
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(1,result.getJSONArray("payload").length());

        HttpEntity<List<PenilaianSimplified>> entity2 = new HttpEntity<>(penilaianList, headers);
        ResponseEntity<String> response2 =
                restTemplate.exchange(createURLWithPort("/contracts/"+contract.getIdKontrak()+"/indicators"), HttpMethod.PUT, entity2, String.class);
        JSONObject result2 = new JSONObject(response2.getBody());

        // Update penilaian
        Assert.assertEquals(HttpStatus.OK.value(), response2.getStatusCodeValue());
        Assert.assertEquals(1,result2.getJSONArray("payload").length());
        Assert.assertEquals(
                penilaianKinerjaList.get(2).getIdIndicator().intValue(),
                result2.getJSONArray("payload").getJSONObject(0).getInt("idIndicator")
        );
        Assert.assertEquals(
                10,
                result2.getJSONArray("payload").getJSONObject(0).getInt("nilai")
        );
    }

    @Test
    public void addUpdateAndAddPenilaian() throws JSONException {
        List<Long> indicatorIdList = new ArrayList<>();
        List<PenilaianSimplified> penilaianList = new ArrayList<>();
        indicatorIdList.add(penilaianKinerjaList.get(3).getIdIndicator());
        PenilaianSimplified penilaian = new PenilaianSimplified(penilaianKinerjaList.get(3).getIdIndicator());
        penilaian.setNilai(BigDecimal.valueOf(10));
        penilaianList.add(penilaian);
        HttpEntity<List<Long>> entity = new HttpEntity<>(indicatorIdList, headers);
        ResponseEntity<String> response =
                restTemplate.postForEntity(createURLWithPort("/contracts/"+contract.getIdKontrak()+"/indicators"), entity, String.class);
        JSONObject result = new JSONObject(response.getBody());

        // Adding penilaian
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(1,result.getJSONArray("payload").length());

        HttpEntity<List<PenilaianSimplified>> entity2 = new HttpEntity<>(penilaianList, headers);
        ResponseEntity<String> response2 =
                restTemplate.exchange(createURLWithPort("/contracts/"+contract.getIdKontrak()+"/indicators"), HttpMethod.PUT, entity2, String.class);
        JSONObject result2 = new JSONObject(response2.getBody());

        // Update penilaian
        Assert.assertEquals(HttpStatus.OK.value(), response2.getStatusCodeValue());
        Assert.assertEquals(1,result2.getJSONArray("payload").length());

        response = restTemplate.postForEntity(createURLWithPort("/contracts/"+contract.getIdKontrak()+"/indicators"), entity, String.class);
        result = new JSONObject(response.getBody());

        // Add penilaian
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(0,result.getJSONArray("payload").length());

        HttpEntity<String> entity3 = new HttpEntity<>(null, headers);
        ResponseEntity<String> response3 = restTemplate.exchange(createURLWithPort("/contracts/"+contract.getIdKontrak()+"/indicators"), HttpMethod.GET, entity3, String.class);
        JSONObject result3 = new JSONObject(response3.getBody());
        JSONArray payload = result3.getJSONArray("payload");
        boolean found = false;
        for(int i=0; i<payload.length(); i++){
            if(payload.getJSONObject(i).getInt("idIndicator") == penilaianKinerjaList.get(3).getIdIndicator()){
                found = true;
                Assert.assertEquals(
                        10,
                        payload.getJSONObject(i).getInt("nilai")
                );
                break;
            }
        }
        Assert.assertEquals(true,found);
    }

    @Test
    public void addAndDeletePenilaian() throws JSONException {
        List<Long> indicatorIdList = new ArrayList<>();
        indicatorIdList.add(penilaianKinerjaList.get(4).getIdIndicator());
        HttpEntity<List<Long>> entity = new HttpEntity<>(indicatorIdList, headers);
        ResponseEntity<String> response =
                restTemplate.postForEntity(createURLWithPort("/contracts/"+contract.getIdKontrak()+"/indicators"), entity, String.class);
        JSONObject result = new JSONObject(response.getBody());

        // Assertion
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(1,result.getJSONArray("payload").length());

        List<Long> deleteIdList = new ArrayList<>();
        deleteIdList.add(penilaianKinerjaList.get(4).getIdIndicator());
        HttpEntity<List<Long>> entity1 = new HttpEntity<>(deleteIdList, headers);
        response = restTemplate.exchange(createURLWithPort("/contracts/"+contract.getIdKontrak()+"/indicators"), HttpMethod.DELETE, entity1, String.class);
        result = new JSONObject(response.getBody());

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(1,result.getJSONArray("payload").length());
        Assert.assertEquals(
                penilaianKinerjaList.get(4).getIdIndicator().intValue(),
                result.getJSONArray("payload").getJSONObject(0).getInt("idIndicator")
        );
    }

    @Test
    public void addDeleteAndDeletePenilaian() throws JSONException {
        List<Long> indicatorIdList = new ArrayList<>();
        indicatorIdList.add(penilaianKinerjaList.get(5).getIdIndicator());
        HttpEntity<List<Long>> entity = new HttpEntity<>(indicatorIdList, headers);
        ResponseEntity<String> response =
                restTemplate.postForEntity(createURLWithPort("/contracts/"+contract.getIdKontrak()+"/indicators"), entity, String.class);
        JSONObject result = new JSONObject(response.getBody());

        // Assertion
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(1,result.getJSONArray("payload").length());

        List<Long> deleteIdList = new ArrayList<>();
        deleteIdList.add(penilaianKinerjaList.get(5).getIdIndicator());
        HttpEntity<List<Long>> entity1 = new HttpEntity<>(deleteIdList, headers);
        response = restTemplate.exchange(createURLWithPort("/contracts/"+contract.getIdKontrak()+"/indicators"), HttpMethod.DELETE, entity1, String.class);
        result = new JSONObject(response.getBody());

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(1,result.getJSONArray("payload").length());

        response = restTemplate.exchange(createURLWithPort("/contracts/"+contract.getIdKontrak()+"/indicators"), HttpMethod.DELETE, entity1, String.class);
        result = new JSONObject(response.getBody());

        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCodeValue());
        Assert.assertEquals(false,result.getBoolean("status"));
        Assert.assertEquals(404,result.getInt("code"));
    }

    @Test
    public void addDeleteAndAddPenilaian() throws JSONException {
        List<Long> indicatorIdList = new ArrayList<>();
        indicatorIdList.add(penilaianKinerjaList.get(6).getIdIndicator());
        HttpEntity<List<Long>> entity = new HttpEntity<>(indicatorIdList, headers);
        ResponseEntity<String> response =
                restTemplate.postForEntity(createURLWithPort("/contracts/"+contract.getIdKontrak()+"/indicators"), entity, String.class);
        JSONObject result = new JSONObject(response.getBody());

        // Assertion
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(1,result.getJSONArray("payload").length());

        List<Long> deleteIdList = new ArrayList<>();
        deleteIdList.add(penilaianKinerjaList.get(6).getIdIndicator());
        HttpEntity<List<Long>> entity1 = new HttpEntity<>(deleteIdList, headers);
        response = restTemplate.exchange(createURLWithPort("/contracts/"+contract.getIdKontrak()+"/indicators"), HttpMethod.DELETE, entity1, String.class);
        result = new JSONObject(response.getBody());

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(1,result.getJSONArray("payload").length());

        response = restTemplate.postForEntity(createURLWithPort("/contracts/"+contract.getIdKontrak()+"/indicators"), entity, String.class);
        result = new JSONObject(response.getBody());

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(1,result.getJSONArray("payload").length());
        Assert.assertEquals(
                0,
                result.getJSONArray("payload").getJSONObject(0).getInt("nilai")
        );
        Assert.assertEquals(
                penilaianKinerjaList.get(6).getIdIndicator().intValue(),
                result.getJSONArray("payload").getJSONObject(0).getInt("idIndicator")
        );
    }

    @Test
    public void addModifyDeleteAndAddPenilaian() throws JSONException {
        int idx = 7;
        List<Long> indicatorIdList = new ArrayList<>();
        List<PenilaianSimplified> penilaianList = new ArrayList<>();
        indicatorIdList.add(penilaianKinerjaList.get(idx).getIdIndicator());
        PenilaianSimplified penilaian = new PenilaianSimplified(penilaianKinerjaList.get(idx).getIdIndicator());
        penilaian.setNilai(BigDecimal.valueOf(10));
        penilaianList.add(penilaian);
        HttpEntity<List<Long>> entity = new HttpEntity<>(indicatorIdList, headers);
        ResponseEntity<String> response =
                restTemplate.postForEntity(createURLWithPort("/contracts/"+contract.getIdKontrak()+"/indicators"), entity, String.class);
        JSONObject result = new JSONObject(response.getBody());

        // Adding penilaian
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(1,result.getJSONArray("payload").length());

        HttpEntity<List<PenilaianSimplified>> entity2 = new HttpEntity<>(penilaianList, headers);
        ResponseEntity<String> response2 =
                restTemplate.exchange(createURLWithPort("/contracts/"+contract.getIdKontrak()+"/indicators"), HttpMethod.PUT, entity2, String.class);
        JSONObject result2 = new JSONObject(response2.getBody());

        // Update penilaian
        Assert.assertEquals(HttpStatus.OK.value(), response2.getStatusCodeValue());
        Assert.assertEquals(1,result2.getJSONArray("payload").length());

        List<Long> deleteIdList = new ArrayList<>();
        deleteIdList.add(penilaianKinerjaList.get(idx).getIdIndicator());
        HttpEntity<List<Long>> entity1 = new HttpEntity<>(deleteIdList, headers);
        response = restTemplate.exchange(createURLWithPort("/contracts/"+contract.getIdKontrak()+"/indicators"), HttpMethod.DELETE, entity1, String.class);
        result = new JSONObject(response.getBody());

        // Delete penilaian
        Assert.assertEquals(1, result.getJSONArray("payload").length());

        response = restTemplate.postForEntity(createURLWithPort("/contracts/"+contract.getIdKontrak()+"/indicators"), entity, String.class);
        result = new JSONObject(response.getBody());

        // Add penilaian
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(1,result.getJSONArray("payload").length());

        JSONArray payload = result.getJSONArray("payload");
        boolean found = false;
        for(int i=0; i<payload.length(); i++){
            if(payload.getJSONObject(i).getInt("idIndicator") == penilaianKinerjaList.get(idx).getIdIndicator()){
                found = true;
                Assert.assertEquals(
                        0,
                        payload.getJSONObject(i).getInt("nilai")
                );
                break;
            }
        }
        Assert.assertEquals(true,found);
    }

    @Test
    public void updatePenilaian() throws JSONException {
        int idx = 8;
        PenilaianSimplified penilaian = new PenilaianSimplified();
        penilaian.setIdIndikator(penilaianKinerjaList.get(idx).getIdIndicator());
        penilaian.setNilai(BigDecimal.valueOf(10));
        List<PenilaianSimplified> penilaianList = new ArrayList<>();
        penilaianList.add(penilaian);
        HttpEntity<List<PenilaianSimplified>> entity = new HttpEntity<>(penilaianList, headers);
        ResponseEntity<String> response =
                restTemplate.exchange(createURLWithPort("/contracts/"+contract.getIdKontrak()+"/indicators"), HttpMethod.PUT, entity, String.class);
        JSONObject result = new JSONObject(response.getBody());
        // Assertion
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCodeValue());
        Assert.assertEquals(404, result.getInt("code"));
        Assert.assertEquals(false, result.getBoolean("status"));
    }

    @Test
    public void deletePenilaian() throws JSONException {
        int idx = 9;
        List<Long> deleteIdList = new ArrayList<>();
        deleteIdList.add(penilaianKinerjaList.get(idx).getIdIndicator());
        HttpEntity<List<Long>> entity1 = new HttpEntity<>(deleteIdList, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/contracts/"+contract.getIdKontrak()+"/indicators"), HttpMethod.DELETE, entity1, String.class);
        JSONObject result = new JSONObject(response.getBody());

        // Assertion
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCodeValue());
        Assert.assertEquals(404, result.getInt("code"));
        Assert.assertEquals(false, result.getBoolean("status"));
    }
}
