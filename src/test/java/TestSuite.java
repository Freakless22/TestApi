import org.junit.runner.RunWith;
import org.junit.runners.Suite;



@Suite.SuiteClasses( {


        TestLogin.class,
        TestCheckSession.class,
        TestDeleteSession.class

})
@RunWith(Suite.class)
public class TestSuite {


}