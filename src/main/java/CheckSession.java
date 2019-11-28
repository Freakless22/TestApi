import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.object.HasToString.hasToString;

public class CheckSession {

    public void checkLogin(String token, String isActive) {
        Response checkLogin = given()
                .headers(
                        "X-Session-ID",
                        "" + token)
                .when()
                .get("https://vkplatform.speechpro.com/vksession/rest/session")
                .then()
                .statusCode(200)
                .and()
                .body("is_active", hasToString(isActive))
                .extract()
                .response();

    }
}