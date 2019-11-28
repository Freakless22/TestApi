import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

public class LoginSession {

   private String s;


    public void possitiveLoginTest(String username, String domain, String passwd, int statusCode) {


        RequestSpecification request = given();
        request.baseUri("https://vkplatform.speechpro.com/vksession/");
        request.header("Content-Type", "application/json");
        request.body("{\n" +
                "  \"username\": " + "\"" + username + "\",\n" +
                "  \"domain_id\":" + domain + ",\n" +
                "  \"password\":" + "\"" + passwd + "\"\n" +
                "}");
        request.expect().statusCode(statusCode);

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
}