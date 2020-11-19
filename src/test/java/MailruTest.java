import ServiceMail.MailManager;
import ServiceMail.MailManagerImpl;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;


public class MailruTest {
    private InitCustomDriver driver = InitCustomDriver.create_driver();
    private File uploadFile = new File("src/test/resources/fa.txt");
    private File contentMail = new File("src/test/resources/testText.docx");
    private File contentImage = new File("src/test/resources/41.png");
    private ImageSelection imagesl;
    private MailManager mailManagerImpl = new MailManagerImpl();

    private String login = System.getProperty("login");
    private String pass = System.getProperty("pass");
    private String receiverEmail = System.getProperty("receiverEmail");
    private String titleLetter = System.getProperty("titleLetter");
    private final String fromStr = System.getProperty("fromStr");


    @Before
    public void setUp() {
        imagesl = ImageSelection.Initimagesl(contentImage);
        imagesl.ImageToClipboard();
        WebDriverRunner.setWebDriver(driver.getDriver());
        mailManagerImpl.authMail(login, pass);
    }

    @Test
    public void testMailRu() {
        mailManagerImpl.openLetter();
        mailManagerImpl.addTitleToletter(receiverEmail, titleLetter, uploadFile);
        mailManagerImpl.addContentToLetter(contentMail);
        mailManagerImpl.addSignatureToLetter(fromStr);
        mailManagerImpl.sendLetter();
    }

    @After
    public void closeDriver() {
        driver.closeDriver();
    }

}
