
import org.junit.Test;



public class TestLoginApi extends LoginSession {

 private LoginSession login = new LoginSession();
 private CheckSession check = new CheckSession();
 private DeleteSession delete = new DeleteSession();

    @Test
    public void loginCheckDeletePositiveTest(){
        login.possitiveLoginTest("Vk_User","201", "123", 200, null, null);
        check.checkLogin(login.s, "true", 200);
        delete.deleteSession(login.s, 204);
        check.checkLogin(login.s, "false", 200);
    } //Авторизуемся с корректными данными, проверяем что сессия жива, удаляем сессию, проверяем что сессия неактивна

    @Test
    public void incorrectLoginTest(){

        login.possitiveLoginTest("Vk_User1","201", "123", 401, "USER_NOT_FOUND", "User 'Vk_User1' is not registered in security database.");
    } //Авторизуемся с некорректным именем пользователя.

    @Test
    public void incorrectPasswordTest(){

        login.possitiveLoginTest("Vk_User","201", "1234", 401, "INCORRECT_PASSWORD", "Incorrect password.");
    } //Авторизуемся с некорректным паролем.

    @Test
    public void incorrectDomainTest(){

        login.possitiveLoginTest("Vk_User","2011", "123", 401, "USER_NOT_FOUND", "User 'Vk_User' is not registered in security database.");
    } //Авторизуемся с некорректным доменом

    @Test
    public void badRequestTest(){

        login.badRequestTest(400,"WRONG_JSON");
    } //отправляем некорректный запрос

    @Test
    public void deleteNotActiveSession(){

        delete.deleteSession(login.s, 400);
    } //удаляем сессию которой нет

}




