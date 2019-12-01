import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

class DeleteSession {
    private String token;


    String getToken() {
        return token;
    }

    void setToken(String token) {
        this.token = token;
    }

    void deleteSession(String token, int statusCode){
        Response deleteSession = given()
                .headers(
                        "X-Session-ID",
                        "" + token)
                .when()
                .delete("https://vkplatform.speechpro.com/vksession/rest/session")
                .then()
                .statusCode(statusCode)
                .extract()
                .response();

    }
}
