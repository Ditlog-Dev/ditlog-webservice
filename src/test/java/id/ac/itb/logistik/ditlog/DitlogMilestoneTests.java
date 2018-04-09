package id.ac.itb.logistik.ditlog;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.ac.itb.logistik.ditlog.controller.MilestoneController;
import id.ac.itb.logistik.ditlog.model.Milestone;
import id.ac.itb.logistik.ditlog.model.User;
import id.ac.itb.logistik.ditlog.repository.MilestoneRepository;
import id.ac.itb.logistik.ditlog.model.RoleConstant;
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
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
public class DitlogMilestoneTests extends BaseTest {
    @Autowired
    MilestoneRepository rencanaRepository;
    private static User userVendor;
    private static User userPemeriksaJasa;
    private static String bearerAuth;
    private static Milestone rencanaJasa1, rencanaJasa2,
            rencanaJasa3, rencanaJasa4, rencanaJasa5;
    private static String jsonRencanaJasa1, jsonRencanaJasa2,
            jsonRencanaJasa3, jsonRencanaJasa4, jsonRencanaJasa5;
    private static boolean setUpIsDone = false;
    private Map<Long, String> ROLE = RoleConstant.ROLE;

    @Before
    public void setUp() throws JsonProcessingException {
        if (setUpIsDone) {
            return;
        }
        rencanaJasa1 = new Milestone();
        rencanaJasa1.setIdProgres(10L);
        rencanaJasa1.setStatusRencana(null);
        rencanaJasa1.setStatusRealisasi(null);
        rencanaJasa1.setIdSPMK(400L);
        rencanaJasa1.setIdUser(100L);
        rencanaJasa1.setTglRencana(new Date());
        rencanaRepository.save(rencanaJasa1);

        rencanaJasa2 = new Milestone();
        rencanaJasa2.setIdProgres(11L);
        rencanaJasa2.setStatusRencana(null);
        rencanaJasa2.setStatusRealisasi(null);
        rencanaJasa2.setIdSPMK(400L);
        rencanaJasa2.setIdUser(100L);
        rencanaJasa2.setTglRencana(new Date());
        rencanaRepository.save(rencanaJasa2);

        rencanaJasa3 = new Milestone();
        rencanaJasa3.setStatusRencana("0");
        rencanaJasa3.setPersentaseRencana(40);
        rencanaJasa3.setIdSPMK(401L);
        rencanaJasa3.setIdUser(101L);
        rencanaJasa3.setKeteranganRencana("Bikin apa");
        rencanaJasa3.setTglRencana(new Date());

        rencanaJasa4 = new Milestone();
        rencanaJasa4.setStatusRencana("0");
        rencanaJasa4.setPersentaseRencana(100);
        rencanaJasa4.setIdSPMK(401L);
        rencanaJasa4.setIdUser(101L);
        rencanaJasa4.setKeteranganRencana("Waduh");
        rencanaJasa4.setTglRencana(new Date());

        rencanaJasa5 = new Milestone();
        rencanaJasa5.setIdProgres(14L);
        rencanaJasa5.setStatusRencana("1");
        rencanaJasa5.setStatusRealisasi("1");
        rencanaJasa5.setIdSPMK(402L);
        rencanaJasa5.setIdUser(102L);
        rencanaJasa5.setTglRencana(new Date());
        rencanaRepository.save(rencanaJasa5);

        jsonRencanaJasa1 = mapper.writeValueAsString(rencanaJasa1);
        jsonRencanaJasa2 = mapper.writeValueAsString(rencanaJasa2);
        jsonRencanaJasa3 = mapper.writeValueAsString(rencanaJasa3);
        jsonRencanaJasa4 = mapper.writeValueAsString(rencanaJasa4);
        jsonRencanaJasa5 = mapper.writeValueAsString(rencanaJasa5);

        userVendor = new User("siVendor", RoleConstant.VENDOR);
        userPemeriksaJasa = new User("siJasa", RoleConstant.PEMERIKSA_JASA);

        userRepo.save(userVendor);
        userRepo.save(userPemeriksaJasa);
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
    public void getRencanaTrue() throws JSONException {
        setUpHeader(userVendor);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response =
                restTemplate.exchange(createURLWithPort("/rencana/400"), HttpMethod.GET, entity, String.class);

        JSONObject result = new JSONObject(response.getBody());

        // Assertion
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(2, result.getJSONArray("payload").length());
        JSONAssert.assertEquals(
                jsonRencanaJasa1,
                result.getJSONArray("payload").getJSONObject(0),
                false);
        JSONAssert.assertEquals(
                jsonRencanaJasa2,
                result.getJSONArray("payload").getJSONObject(1),
                false);
    }

    @Test
    public void getRencanaNoResult() throws JSONException {
        setUpHeader(userPemeriksaJasa);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response =
                restTemplate.exchange(createURLWithPort("/rencana/402"),
                        HttpMethod.GET, entity, String.class);

        JSONObject result = new JSONObject(response.getBody());

        // Assertion
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(0, result.getJSONArray("payload").length());
    }

    @Test
    public void updateRencanaReject() throws JSONException {
        Milestone rencanaJasa = new Milestone();

        rencanaJasa.setIdProgres(200L);
        rencanaJasa.setStatusRencana(null);
        rencanaJasa.setStatusRencana("null");
        rencanaJasa.setIdSPMK(404L);
        rencanaRepository.save(rencanaJasa);

        rencanaJasa.setIdProgres(201L);
        rencanaJasa.setStatusRencana(null);
        rencanaJasa.setStatusRencana("null");
        rencanaJasa.setIdSPMK(404L);
        rencanaRepository.save(rencanaJasa);

        setUpHeader(userPemeriksaJasa);
        MilestoneController.Keterangan tempKet = new MilestoneController.Keterangan();
        tempKet.setKet("gagal bro");
        HttpEntity<MilestoneController.Keterangan> entity =
                new HttpEntity<>(tempKet, headers);

        ResponseEntity<String> response =
                restTemplate.exchange(createURLWithPort("/rencana/404/0"),
                        HttpMethod.PUT, entity, String.class);

        Iterable<Milestone> results = rencanaRepository.findByIdSPMK(404L);

        // Assertion
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());

        for (Milestone result : results) {
            Assert.assertEquals(result.getStatusRencana(), "0");
            Assert.assertEquals(result.getAlasanReject(), tempKet.getKet());
        }
    }

    @Test
    public void insertRencana() throws JSONException, JsonProcessingException {
        setUpHeader(userVendor);
        List<Milestone> listOfRencana = new ArrayList<>();
        listOfRencana.add(rencanaJasa3);
        listOfRencana.add(rencanaJasa4);

        HttpEntity<List<Milestone>> entity = new HttpEntity<>(listOfRencana, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(createURLWithPort("/rencana/401"),
                        entity, String.class);

        Iterable<Milestone> results = rencanaRepository.findByIdSPMK(401L);
        ArrayList<String> temp = new ArrayList<>();
        for (Milestone result : results) {
            result.setIdProgres(null);
            String tempe = mapper.writeValueAsString(result);
            temp.add(tempe);
        }

        // Assertion
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(temp.get(0), jsonRencanaJasa3);
        Assert.assertEquals(temp.get(1), jsonRencanaJasa4);
    }

}