
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static org.hamcrest.object.HasToString.hasToString;

public class TestLoginApi {

    private String s;


    private void token(){

        RequestSpecification request = given();
        request.baseUri("https://vkplatform.speechpro.com/vksession/");
        request.header("Content-Type", "application/json");
        request.body("{\n" +
                "  \"username\": \"Vk_user\",\n" +
                "  \"domain_id\": 201,\n" +
                "  \"password\": \"123\"\n" +
                "}");
        request.expect().statusCode(200);

        Response response = request.post("/rest/session");

        String regex = "\"session_id\":+ \"(.+?)\"";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(response.getBody().prettyPrint());

        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                s = matcher.group(i);
            }
        }

    }

    @Test
    public void loginCheckSession(){

        token();
        Response response = given()
                .headers(
                        "X-Session-ID",
                        "" + s)
                .when()
                .get("https://vkplatform.speechpro.com/vksession/rest/session")
                .then()
                .statusCode(200)
                .and()
                .body("is_active", hasToString("true"))
                .extract()
                .response();
    }

    @Test
    public void logoutDeleteSession(){
        token();
        Response response = given()
                .headers(
                        "X-Session-ID",
                        "" + s)
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
