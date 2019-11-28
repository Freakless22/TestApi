import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static org.hamcrest.object.HasToString.hasToString;

public class LoginSession {

   public String s;


    public void possitiveLoginTest(String username, String domain, String passwd, int statusCode, String reason, String message) {


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

    public void badRequestTest(int statusCode){
        RequestSpecification request = given();
        request.baseUri("https://vkplatform.speechpro.com/vksession/");
        request.header("Content-Type", "application/json");
        request.body("{\n" +
                "  \"username\": " + "\"" + "\",\n" +
                "  \"domain_id\":" + ",\n" +
                "  \"password\":" + "\"\n" +
                "}");
        request.expect().statusCode(statusCode);

        Response getToken = request.post("/rest/session");
    }
}