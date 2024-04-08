package service;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestApiBuilder {

    public static Response performRequest(String method, String url, String body, String token, int expectedStatusCode) {
        // Initial request configuration
        RequestSpecification requestSpec = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when();

        // Adding request body if provided
        if (body != null && !body.isEmpty()) {
            requestSpec.body(body);
        }

        // Adding authorization token if provided
        if (token != null && !token.isEmpty()) {
            requestSpec.header("Authorization", "Bearer " + token);
        }

        // Choosing HTTP method and sending request
        Response response;
        switch (method.toUpperCase()) {
            case "GET":
                response = requestSpec.get();
                break;
            case "POST":
                response = requestSpec.post();
                break;
            case "PUT":
                response = requestSpec.put();
                break;
            case "DELETE":
                response = requestSpec.delete();
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }

        // Checking response status code
        response.then().statusCode(expectedStatusCode);

        return response;
    }
}
