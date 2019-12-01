import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static org.hamcrest.object.HasToString.hasToString;

class LoginSession {

   String s;
   private String username;
   private String domain;
   private String password;



    private int statusCode;
   String userNotFoundReason = "USER_NOT_FOUND";
   String nullArgumentReason = "ILLEGAL_ARGUMENT";
   String incorrectPasswordReason = "INCORRECT_PASSWORD";
   String wrongJsonReason = "WRONG_JSON";

    int getStatusCode() {
        return statusCode;
    }

    void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    void setUsername(String username) {
        this.username = username;
    }

    String getUsername() {
        return username;
    }

    void setDomain(String domain) {
        this.domain = domain;
    }

    String getDomain() {
        return domain;
    }

    void setPassword(String password){
        this.password = password;
    }
    String getPassword(){
        return password;
    }

    void baseLoginTest(String username, String domain, String password, int statusCode, String reason, String message) {
        RequestSpecification request = given();
        request.baseUri("https://vkplatform.speechpro.com/vksession/");
        request.header("Content-Type", "application/json");
        request.body("{\n" +
                "  \"username\": " + "\"" + username + "\",\n" +
                "  \"domain_id\":" + domain + ",\n" +
                "  \"password\":" + "\"" + password + "\"\n" +
                "}");
        request.expect().statusCode(statusCode);
        if (statusCode != 200) {
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


    }
