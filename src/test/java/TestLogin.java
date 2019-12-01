
import org.junit.Test;


public class TestLogin {

 private LoginSession login = new LoginSession();


    @Test
    public void correctUsernameDomainPasswordLoginTest(){
        login.setUsername("vk_User");
        login.setDomain("201");
        login.setPassword("123");
        login.setStatusCode(200);
        login.baseLoginTest(login.getUsername(),login.getDomain(), login.getPassword(), login.getStatusCode(), null, null);
    } //Авторизуемся с корректными данными

    @Test
    public void registerUsernameLoginTest(){
        login.setUsername("Vk_user");
        login.setDomain("201");
        login.setPassword("123");
        login.setStatusCode(200);
        login.baseLoginTest(login.getUsername(),login.getDomain(), login.getPassword(), login.getStatusCode(), null, null);
    } //Проверяем что регистр в поле username не учитывается

    @Test
    public void incorrectUsernameLoginTest(){
        login.setUsername("Vk_user1");
        login.setDomain("201");
        login.setPassword("123");
        login.setStatusCode(401);
        login.baseLoginTest(login.getUsername(),login.getDomain(), login.getPassword(), login.getStatusCode(), login.userNotFoundReason, "User " +"\'"+ login.getUsername()+ "\'"+" is not registered in security database.");
    } //Авторизуемся с некорректным именем пользователя.

    @Test
    public void nullUsernameLoginTest(){
        login.setUsername("");
        login.setDomain("201");
        login.setPassword("123");
        login.setStatusCode(400);
        login.baseLoginTest(login.getUsername(),login.getDomain(), login.getPassword(), login.getStatusCode(), login.nullArgumentReason, "Argument username cannot be null.");
    } //Авторизуемся с пустым именем пользователя.

    @Test
    public void incorrectPasswordLoginTest(){
        login.setUsername("Vk_User");
        login.setDomain("201");
        login.setPassword("321");
        login.setStatusCode(401);
        login.baseLoginTest(login.getUsername(),login.getDomain(), login.getPassword(), login.getStatusCode(), login.incorrectPasswordReason, "Incorrect password.");
    } //Авторизуемся с некорректным паролем.

    @Test
    public void nullPasswordLoginTest(){
        login.setUsername("Vk_User");
        login.setDomain("201");
        login.setPassword("");
        login.setStatusCode(400);
        login.baseLoginTest(login.getUsername(),login.getDomain(), login.getPassword(), login.getStatusCode(), login.nullArgumentReason, "Argument password cannot be null.");
    } //Авторизуемся с пустым паролем.

    @Test //TODO сделать проверку сообщения
    public void incorrectDomainLoginTest(){
        login.setUsername("Vk_User");
        login.setDomain("qq");
        login.setPassword("123");
        login.setStatusCode(400);
        login.baseLoginTest(login.getUsername(),login.getDomain(), login.getPassword(),login.getStatusCode(), login.wrongJsonReason, "");
    } //Авторизуемся с некорректным доменом.

    @Test
    public void nullDomainLoginTest(){
        login.setUsername("Vk_User");
        login.setDomain(null);
        login.setPassword("123");
        login.setStatusCode(400);
        login.baseLoginTest(login.getUsername(),login.getDomain(), login.getPassword(), login.getStatusCode(), login.nullArgumentReason, "Argument domain_id cannot be null.");
    } //Авторизуемся с пустым доменом.

    @Test
    public void wrongDomainLoginTest(){
        login.setUsername("Vk_User");
        login.setDomain("200");
        login.setPassword("123");
        login.setStatusCode(401);
        login.baseLoginTest(login.getUsername(),login.getDomain(), login.getPassword(), login.getStatusCode(), login.userNotFoundReason, "User " +"\'"+ login.getUsername()+ "\'"+" is not registered in security database.");
    } //Авторизуемся не в тот домен


}







