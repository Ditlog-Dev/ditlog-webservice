package id.ac.itb.logistik.ditlog;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.ac.itb.logistik.ditlog.model.Indicator;
import id.ac.itb.logistik.ditlog.model.SPMKContract;
import id.ac.itb.logistik.ditlog.model.User;
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
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DitlogContractTests extends BaseTest {
    @Autowired
    SPMKContractRepository contractRepository;
    private static User testUser;
    private static String bearerAuth;
    private static SPMKContract testContract;
    private static String jsonIndicator;
    private static boolean setUpIsDone = false;

    @Before
    public void setUp() throws JsonProcessingException {
        if (setUpIsDone) {
            return;
        }
        testContract = new SPMKContract();
        testContract.setIdKontrak(1L);
        testContract.setNoKontrak("1234");
        testContract.setTahun(2018L);
        contractRepository.save(testContract);
        jsonIndicator = mapper.writeValueAsString(testContract);
        testUser = new User("john",422L);
        bearerAuth = TokenAuthenticationService.TOKEN_PREFIX + " " + TokenAuthenticationService.getJWT(testUser);
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

    @Test
    public void interactionLoads() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response =
                restTemplate.exchange(createURLWithPort("/contracts"), HttpMethod.GET, entity,  String.class);

        JSONObject result = new JSONObject(response.getBody());

        // Assertion
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        JSONAssert.assertEquals(
                jsonIndicator,
                result.getJSONArray("payload").getJSONObject(0),
                false);
    }
    @Test
    public void interactionLoadsWithParam() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response =
                restTemplate.exchange(createURLWithPort("/contracts?tahun=2018"), HttpMethod.GET, entity,  String.class);

        JSONObject result = new JSONObject(response.getBody());

        // Assertion
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        JSONAssert.assertEquals(
                jsonIndicator,
                result.getJSONArray("payload").getJSONObject(0),
                false);
    }
    @Test
    public void interactionLoadsWithParamWrong() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response =
                restTemplate.exchange(createURLWithPort("/contracts?tahun=2017"), HttpMethod.GET, entity,  String.class);

        JSONObject result = new JSONObject(response.getBody());

        // Assertion
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCodeValue());
    }
    @Test
    public void interactionLoadIndividual() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response =
                restTemplate.exchange(createURLWithPort("/contracts/1"), HttpMethod.GET, entity,  String.class);

        JSONObject result = new JSONObject(response.getBody());

        // Assertion

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        JSONAssert.assertEquals(
                jsonIndicator,
                result.getJSONObject("payload"),
                false);
    }
    @Test
    public void interactionLoadIndividualWrong() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response =
                restTemplate.exchange(createURLWithPort("/contracts/2"), HttpMethod.GET, entity,  String.class);

        JSONObject result = new JSONObject(response.getBody());

        // Assertion

        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCodeValue());
    }
}
