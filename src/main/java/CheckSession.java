import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.object.HasToString.hasToString;

class CheckSession {

    void checkLogin(String token, String isActive, int statusCode) {
        Response checkLogin = given()
                .headers(
                        "X-Session-ID",
                        "" + token)
                .when()
                .get("https://vkplatform.speechpro.com/vksession/rest/session")
                .then()
                .statusCode(statusCode)
                .and()
                .body("is_active", hasToString(isActive))
                .extract()
                .response();
    }
}