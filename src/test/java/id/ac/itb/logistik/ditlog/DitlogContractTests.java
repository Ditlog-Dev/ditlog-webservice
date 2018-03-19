package id.ac.itb.logistik.ditlog;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.ac.itb.logistik.ditlog.model.Indicator;
import id.ac.itb.logistik.ditlog.model.SPMKContract;
import id.ac.itb.logistik.ditlog.model.User;
import id.ac.itb.logistik.ditlog.service.TokenAuthenticationService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DitlogContractTests extends BaseTest {
    private User testUser;
    private String bearerAuth;
    private SPMKContract testContract;
    private String jsonIndicator;
    private static boolean setUpIsDone = false;

    @Before
    public void setUp() throws JsonProcessingException {
        testContract = new SPMKContract();
        testContract.setIdKontrak(1L);
        testContract.setNoKontrak("1234");
        jsonIndicator = mapper.writeValueAsString(testContract);
        testUser = new User("john",422L);
        bearerAuth = TokenAuthenticationService.TOKEN_PREFIX + " " + TokenAuthenticationService.getJWT(testUser);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(
                TokenAuthenticationService.HEADER_STRING,
                bearerAuth
        );
    }
}
