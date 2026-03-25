package base;

import io.restassured.RestAssured;

public class BaseTest {
    public static String BASE_URL = "https://api.development.insighttwin.com/";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }
}
