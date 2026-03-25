package utils;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class TokenManager {
    public static String token;

    public static String getToken() {
        if (token == null) {

            String body = """
                    {
                      "email": "kishorekumar.b+admin@spritle.com",
                      "password": "@Kichu010104"
                    }
                    """;

            Response response = given()
                    .header("Content-Type", "application/json")
                    .body(body)
                    .when()
                    .post("https://api.development.insighttwin.com/api/v1/auth/login");

            if (response.getStatusCode() == 200 || response.getStatusCode() == 201) {
                token = response.jsonPath().getString("access");
            } else {
                throw new RuntimeException(
                        "Failed to get token: STATUS " + response.getStatusCode() + " - " + response.asString());
            }
        }

        return token;
    }
}