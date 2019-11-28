import io.restassured.response.Response;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.given;

class DeleteSession {

    void deleteSession(String token, int statusCode){
        Response deleteSession = given()
                .headers(
                        "X-Session-ID",
                        "" + token)
                .when()
                .delete("https://vkplatform.speechpro.com/vksession/rest/session")
                .then()
                .statusCode(statusCode)
                .and()
                .body(Matchers.anything())
                .extract()
                .response();
    }
}
