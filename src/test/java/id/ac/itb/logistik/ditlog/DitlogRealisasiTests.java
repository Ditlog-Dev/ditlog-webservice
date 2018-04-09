package id.ac.itb.logistik.ditlog;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.ac.itb.logistik.ditlog.controller.MilestoneController;
import id.ac.itb.logistik.ditlog.model.Milestone;
import id.ac.itb.logistik.ditlog.model.RoleConstant;
import id.ac.itb.logistik.ditlog.model.User;
import id.ac.itb.logistik.ditlog.repository.MilestoneRepository;
import id.ac.itb.logistik.ditlog.repository.RealisasiRepository;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
public class DitlogRealisasiTests extends BaseTest {
    @Autowired
    RealisasiRepository realisasiRepository;
    private static User userVendor;
    private static User userPemeriksaJasa;
    private static String bearerAuth;
    private static Milestone realisasiJasa1, realisasiJasa2,
            realisasiJasa3, realisasiJasa4;
    private static String jsonRealisasiJasa1, jsonRealisasiJasa2,
            jsonRealisasiJasa3, jsonRealisasiJasa4;
    private static boolean setUpIsDone = false;
    private Map<Long, String> ROLE = RoleConstant.ROLE;

    @Before
    public void setUp() throws JsonProcessingException {
        if (setUpIsDone) {
            return;
        }
        realisasiJasa1 = new Milestone();
        realisasiJasa1.setIdProgres(10L);
        realisasiJasa1.setStatusRencana("1");
        realisasiJasa1.setStatusRealisasi(null);
        realisasiJasa1.setIdSPMK(400L);
        realisasiJasa1.setIdUser(100L);
        realisasiJasa1.setTglRencana(new Date());
        realisasiRepository.save(realisasiJasa1);

        realisasiJasa2 = new Milestone();
        realisasiJasa2.setIdProgres(11L);
        realisasiJasa2.setStatusRencana("1");
        realisasiJasa2.setStatusRealisasi(null);
        realisasiJasa2.setIdSPMK(400L);
        realisasiJasa2.setIdUser(100L);
        realisasiJasa2.setTglRencana(new Date());
        realisasiRepository.save(realisasiJasa2);

        realisasiJasa3 = new Milestone();
        realisasiJasa3.setStatusRencana("1");
        realisasiJasa3.setPersentaseRencana(40);
        realisasiJasa3.setIdSPMK(401L);
        realisasiJasa3.setIdUser(101L);
        realisasiJasa3.setKeteranganRencana("Bikin apa");
        realisasiJasa3.setTglRencana(new Date());

        realisasiJasa4 = new Milestone();
        realisasiJasa4.setStatusRencana("1");
        realisasiJasa4.setPersentaseRencana(100);
        realisasiJasa4.setIdSPMK(401L);
        realisasiJasa4.setIdUser(101L);
        realisasiJasa4.setKeteranganRencana("Waduh");
        realisasiJasa4.setTglRencana(new Date());

        jsonRealisasiJasa1 = mapper.writeValueAsString(realisasiJasa1);
        jsonRealisasiJasa2 = mapper.writeValueAsString(realisasiJasa2);
        jsonRealisasiJasa3 = mapper.writeValueAsString(realisasiJasa3);
        jsonRealisasiJasa4 = mapper.writeValueAsString(realisasiJasa4);

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
    public void getRealisasiTrue() throws JSONException {
        setUpHeader(userVendor);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response =
                restTemplate.exchange(createURLWithPort("/realisasi/400"), HttpMethod.GET, entity, String.class);

        JSONObject result = new JSONObject(response.getBody());

        // Assertion
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(2, result.getJSONArray("payload").length());
        JSONAssert.assertEquals(
                jsonRealisasiJasa1,
                result.getJSONArray("payload").getJSONObject(0),
                false);
        JSONAssert.assertEquals(
                jsonRealisasiJasa2,
                result.getJSONArray("payload").getJSONObject(1),
                false);
    }

    @Test
    public void getRealisasiNoResult() throws JSONException {
        Milestone realisasiJasa = new Milestone();
        realisasiJasa = new Milestone();
        realisasiJasa.setIdProgres(11L);
        realisasiJasa.setStatusRencana("0");
        realisasiJasa.setStatusRealisasi(null);
        realisasiJasa.setIdSPMK(402L);
        realisasiRepository.save(realisasiJasa);

        setUpHeader(userPemeriksaJasa);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response =
                restTemplate.exchange(createURLWithPort("/realisasi/402"),
                        HttpMethod.GET, entity, String.class);

        JSONObject result = new JSONObject(response.getBody());

        // Assertion
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(0, result.getJSONArray("payload").length());
    }

    @Test
    public void insertRealisasi() throws JSONException, JsonProcessingException {
        setUpHeader(userVendor);
        List<Milestone> listOfRealisasi = new ArrayList<>();
        listOfRealisasi.add(realisasiJasa3);
        listOfRealisasi.add(realisasiJasa4);

        HttpEntity<List<Milestone>> entity = new HttpEntity<>(listOfRealisasi, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(createURLWithPort("/realisasi/401"),
                        entity, String.class);

        Iterable<Milestone> results = realisasiRepository.findByIdSPMK(401L);
        ArrayList<String> temp = new ArrayList<>();
        for (Milestone result : results) {
            result.setIdProgres(null);
            String tempe = mapper.writeValueAsString(result);
            temp.add(tempe);
        }

        // Assertion
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(temp.get(0), jsonRealisasiJasa3);
        Assert.assertEquals(temp.get(1), jsonRealisasiJasa4);
    }

}