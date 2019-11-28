import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static org.hamcrest.object.HasToString.hasToString;

public class TestLoginApi2 extends LoginSession {
  //  private String s;
LoginSession login = new LoginSession();

    @Test
    public void test(){
        login.possitiveLoginTest("Vk_User","201", "123", 200);
    }

/*
    @Test
    public void possitiveLoginTest() {

        RequestSpecification request = given();
        request.baseUri("https://vkplatform.speechpro.com/vksession/");
        request.header("Content-Type", "application/json");
        request.body("{\n" +
                "  \"username\": \"Vk_user\",\n" +
                "  \"domain_id\": 201,\n" +
                "  \"password\": \"123\"\n" +
                "}");
        request.expect().statusCode(200);

        Response getToken = request.post("/rest/session");

        String regex = "\"session_id\":+ \"(.+?)\"";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(getToken.getBody().prettyPrint());

        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                s = matcher.group(i);
            }
        }


        Response checkLogin = given()
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


        Response deleteSession = given()
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

    @Test
    public void wrongPassLoginTest() {

        RequestSpecification request = given();
        request.baseUri("https://vkplatform.speechpro.com/vksession/");
        request.header("Content-Type", "application/json");
        request.body("{\n" +
                "  \"username\": \"Vk_user\",\n" +
                "  \"domain_id\": 201,\n" +
                "  \"password\": \"123 \"\n" +
                "}");
        request.expect().statusCode(401);
        request.expect().body("reason", hasToString("INCORRECT_PASSWORD"));
        request.expect().body("message", hasToString("Incorrect password."));


        Response getToken = request.post("/rest/session");
        getToken.andReturn();

            }

    @Test
    public void badRequestLoginTest(){
        RequestSpecification request = given();
        request.baseUri("https://vkplatform.speechpro.com/vksession/");
        request.header("Content-Type", "application/json");
        request.body("{\n" +
                "  \"sername\": \"Vk_user\",\n" +
                "  \"domain_id\": 201,\n" +
                "  \"password\": \"123\"\n" +
                "}");
        request.expect().statusCode(400);
        request.expect().body("reason", hasToString("WRONG_JSON"));


        Response getToken = request.post("/rest/session");
        getToken.andReturn();
    }

    @Test
    public void wrongDomainId(){

    }*/
        }




