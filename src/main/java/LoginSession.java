import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static org.hamcrest.object.HasToString.hasToString;

class LoginSession {

   String s;

    void possitiveLoginTest(String username, String domain, String passwd, int statusCode, String reason, String message) {


        RequestSpecification request = given();
        request.baseUri("https://vkplatform.speechpro.com/vksession/");
        request.header("Content-Type", "application/json");
        request.body("{\n" +
                "  \"username\": " + "\"" + username + "\",\n" +
                "  \"domain_id\":" + domain + ",\n" +
                "  \"password\":" + "\"" + passwd + "\"\n" +
                "}");
        request.expect().statusCode(statusCode);

        if(statusCode == 401) {
            request.expect().body("reason", hasToString(reason));
            request.expect().body("message", hasToString(message));
        }

        Response getToken = request.post("/rest/session");

        String regex = "\"session_id\":+ \"(.+?)\"";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(getToken.getBody().prettyPrint());

        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                s = matcher.group(i);
            }
        }
    }

    void badRequestTest(int statusCode, String reason){
        RequestSpecification request = given();
        request.baseUri("https://vkplatform.speechpro.com/vksession/");
        request.header("Content-Type", "application/json");
        request.body("{\n" +
                "  \"NOTusername\": \"Vk_user\",\n" +
                "  \"domain_id\": 201,\n" +
                "  \"password\": \"123\"\n" +
                "}");

        request.expect().statusCode(statusCode);
        request.expect().body("reason", hasToString(reason));


       // Response auth = request.post("/rest/session");

    }
}