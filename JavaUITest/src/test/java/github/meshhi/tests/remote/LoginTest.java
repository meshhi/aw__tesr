package github.meshhi.tests.remote;
import org.junit.Test;
import org.junit.Assert;

// comment
public class LoginTest extends BaseStartTestDecorator {
    @Test
    public void pass() throws InterruptedException {
        System.out.println(driver.getCurrentURL());
        Assert.assertTrue(driver.getCurrentURL().contains("/app/sources"));
    }
}
