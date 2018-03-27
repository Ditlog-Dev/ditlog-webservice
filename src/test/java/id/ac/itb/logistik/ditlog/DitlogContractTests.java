package id.ac.itb.logistik.ditlog;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.ac.itb.logistik.ditlog.model.SPMKContract;
import id.ac.itb.logistik.ditlog.model.User;
import id.ac.itb.logistik.ditlog.repository.SPMKContractRepository;
import id.ac.itb.logistik.ditlog.model.RoleConstant;
import id.ac.itb.logistik.ditlog.service.TokenAuthenticationService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
public class DitlogContractTests extends BaseTest {
    @Autowired
    SPMKContractRepository contractRepository;
    private static User userVendor;
    private static User userKasubdit;
    private static User userKasieBarang;
    private static User userKasieJasa;
    private static User userBarang;
    private static User userJasa;
    private static String bearerAuth;
    private static SPMKContract contractBarang;
    private static SPMKContract contractJasa;
    private static String jsonContractBarang,jsonContractJasa;
    private static boolean setUpIsDone = false;
    private Map<Long,String> ROLE = RoleConstant.ROLE;

    @Before
    public void setUp() throws JsonProcessingException {
        if (setUpIsDone) {
            return;
        }
        contractBarang = new SPMKContract();
        contractBarang.setIdKontrak(1L);
        contractBarang.setNoKontrak("1234");
        contractBarang.setTahun(2018L);
        contractBarang.setJenis("BARANG");
        contractRepository.save(contractBarang);
        contractJasa = new SPMKContract();
        contractJasa.setIdKontrak(2L);
        contractJasa.setNoKontrak("1234");
        contractJasa.setTahun(2017L);
        contractJasa.setJenis("JASA");
        contractRepository.save(contractJasa);
        jsonContractBarang = mapper.writeValueAsString(contractBarang);
        jsonContractJasa = mapper.writeValueAsString(contractJasa);
        userVendor = new User("john vendor", RoleConstant.VENDOR);
        userKasubdit = new User("john kasubdit", RoleConstant.KASUBDIT_PEMERIKSA);
        userBarang = new User("john barang", RoleConstant.PEMERIKSA_BARANG);
        userKasieBarang = new User("john kasie barang", RoleConstant.KASIE_PEMERIKSA_BARANG);
        userJasa = new User("john jasa", RoleConstant.PEMERIKSA_JASA);
        userKasieJasa = new User("john kasie jasa", RoleConstant.KASIE_PEMERIKSA_JASA);
        userRepo.save(userVendor);
        userRepo.save(userKasubdit);
        userRepo.save(userBarang);
        userRepo.save(userKasieBarang);
        userRepo.save(userJasa);
        userRepo.save(userKasieJasa);
        setUpIsDone = true;
    }

    public void setUpHeader(User user) {
        bearerAuth = TokenAuthenticationService.TOKEN_PREFIX + " " + TokenAuthenticationService.getJWT(user);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(
                TokenAuthenticationService.HEADER_STRING,
                bearerAuth
        );
    }

    @Test
    public void interactionLoads() throws JSONException {
        setUpHeader(userKasubdit);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response =
                restTemplate.exchange(createURLWithPort("/contracts"), HttpMethod.GET, entity,  String.class);

        setUpHeader(userVendor);
        entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response2 =
                restTemplate.exchange(createURLWithPort("/contracts"), HttpMethod.GET, entity,  String.class);

        JSONObject result = new JSONObject(response.getBody());
        JSONObject result2 = new JSONObject(response2.getBody());

        // Assertion
        Assert.assertEquals(response,response2);
        JSONAssert.assertEquals(result,result2,false);
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(2,result.getJSONArray("payload").length());
        JSONAssert.assertEquals(
                jsonContractBarang,
                result.getJSONArray("payload").getJSONObject(0),
                false);
        JSONAssert.assertEquals(
                jsonContractJasa,
                result.getJSONArray("payload").getJSONObject(1),
                false);
    }
    @Test
    public void interactionLoadsBarang() throws JSONException {
        setUpHeader(userBarang);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response =
                restTemplate.exchange(createURLWithPort("/contracts"), HttpMethod.GET, entity,  String.class);

        setUpHeader(userKasieBarang);
        entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response2 =
                restTemplate.exchange(createURLWithPort("/contracts"), HttpMethod.GET, entity,  String.class);

        JSONObject result = new JSONObject(response.getBody());
        JSONObject result2 = new JSONObject(response2.getBody());

        // Assertion
        Assert.assertEquals(response,response2);
        JSONAssert.assertEquals(result,result2,false);
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(1,result.getJSONArray("payload").length());
        JSONAssert.assertEquals(
                jsonContractBarang,
                result.getJSONArray("payload").getJSONObject(0),
                false);
    }
    @Test
    public void interactionLoadsJasa() throws JSONException {
        setUpHeader(userJasa);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response =
                restTemplate.exchange(createURLWithPort("/contracts"), HttpMethod.GET, entity,  String.class);

        setUpHeader(userKasieJasa);
        entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response2 =
                restTemplate.exchange(createURLWithPort("/contracts"), HttpMethod.GET, entity,  String.class);

        JSONObject result = new JSONObject(response.getBody());
        JSONObject result2 = new JSONObject(response2.getBody());

        // Assertion
        Assert.assertEquals(response,response2);
        JSONAssert.assertEquals(result,result2,false);
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(1,result.getJSONArray("payload").length());
        JSONAssert.assertEquals(
                jsonContractJasa,
                result.getJSONArray("payload").getJSONObject(0),
                false);
    }
    @Test
    public void interactionLoadsWithParam() throws JSONException {
        setUpHeader(userKasubdit);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response =
                restTemplate.exchange(createURLWithPort("/contracts?tahun=2018"), HttpMethod.GET, entity,  String.class);

        JSONObject result = new JSONObject(response.getBody());

        // Assertion
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(1,result.getJSONArray("payload").length());
        JSONAssert.assertEquals(
                jsonContractBarang,
                result.getJSONArray("payload").getJSONObject(0),
                false);
    }
    @Test
    public void interactionLoadsWithParamWrong() throws JSONException {
        setUpHeader(userKasubdit);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response =
                restTemplate.exchange(createURLWithPort("/contracts?tahun=2016"), HttpMethod.GET, entity,  String.class);

        JSONObject result = new JSONObject(response.getBody());

        // Assertion
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCodeValue());
    }
    @Test
    public void interactionLoadIndividual() throws JSONException {
        setUpHeader(userKasubdit);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response =
                restTemplate.exchange(createURLWithPort("/contracts/1"), HttpMethod.GET, entity,  String.class);

        JSONObject result = new JSONObject(response.getBody());

        // Assertion

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        JSONAssert.assertEquals(
                jsonContractBarang,
                result.getJSONObject("payload"),
                false);
    }
    @Test
    public void interactionLoadIndividualWrong() throws JSONException {
        setUpHeader(userKasubdit);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response =
                restTemplate.exchange(createURLWithPort("/contracts/3"), HttpMethod.GET, entity,  String.class);

        JSONObject result = new JSONObject(response.getBody());

        // Assertion

        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCodeValue());
    }
}
