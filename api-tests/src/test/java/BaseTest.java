import config.ServerConfig;
import model.User;
import org.aeonbits.owner.ConfigFactory;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected String accessToken;
    protected int productId;
    protected String registeredUsername;
    protected String registeredPassword;
    protected User user;

    @BeforeClass
    public void setup() {
        ServerConfig config = ConfigFactory.create(ServerConfig.class);
        RestAssured.baseURI = config.baseUrl();
    }
}
