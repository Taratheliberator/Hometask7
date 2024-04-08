
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import endpoints.Urls;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import model.*;
import org.testng.annotations.Test;
import service.RestApiBuilder;

import java.time.Instant;
import java.util.List;
import static org.testng.Assert.*;


public class ApiTests extends BaseTest {

    @Test(priority = 1)

    public void UserRegistrationTest() throws JsonProcessingException {
        registeredUsername = "user" + Instant.now().getEpochSecond();
        registeredPassword = "password";
        user = new User(registeredUsername, registeredPassword);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(user);

        RestApiBuilder.performRequest("POST", Urls.REGISTER, requestBody, null, 201);
    }


    @Test(priority = 2)
    public void authenticateUser() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(user);

        Response response = RestApiBuilder.performRequest("POST", Urls.LOGIN, requestBody, null, 200);

        accessToken = response.path("access_token");
        assertNotNull(accessToken, "Access token should not be null");
    }


    @Test(priority = 3)
    public void GetListOfProductsTestAndExtractProductId() {
        Response response = RestApiBuilder.performRequest("GET", Urls.PRODUCTS, null, null, 200);

        assertNotNull(response.getBody(), "Response body should not be null");

        List<Product> products = response.jsonPath().getList("", Product.class);

        assertFalse(products.isEmpty(), "Products list should not be empty");

        Product firstProduct = products.get(0);
        assertTrue(firstProduct.getId() > 0, "Product ID should be positive");

        productId = firstProduct.getId();
    }
}
