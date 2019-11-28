import io.restassured.response.Response;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.given;

public class DeleteSession {

    public void deleteSession(String token){
        Response deleteSession = given()
                .headers(
                        "X-Session-ID",
                        "" + token)
                .when()
                .delete("https://vkplatform.speechpro.com/vksession/rest/session")
                .then()
                .statusCode(204)
                .and()
                .body(Matchers.anything())
                .extract()
                .response();
    }
}
