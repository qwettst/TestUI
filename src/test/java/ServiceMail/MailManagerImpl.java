package ServiceMail;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class MailManagerImpl implements MailManager {

    @Override
    public void authMail(String login, String pass) {
        Selenide.open("https://mail.ru/");
        Selenide.$(By.name("login")).val(login).pressEnter();
        Selenide.$(By.name("password")).should(Condition.appear).val(pass).pressEnter();

    }

    @Override
    public void openLetter() {
        Selenide.$(By.className("compose-button__txt")).should(Condition.appear).click();
    }

    @Override
    public void sendLetter() {
        Selenide.$(By.xpath("//span[@data-title-shortcut='Ctrl+Enter']")).click();
    }

    @Override
    public void addTitleToletter(String receiverEmail, String titleLetter, File uploadFile) {
        Selenide.$(By.className("compose-button__txt")).should(Condition.appear).click();
        Selenide.$("input[class='container--H9L5q size_s--3_M-_']").should(Condition.appear).val(receiverEmail);
        Selenide.$(By.name("Subject")).sendKeys(titleLetter);
        Selenide.$("input[class='desktopInput--3cWPE'").uploadFile(uploadFile);
    }

    @Override
    public void addContentToLetter(File contentMail) {
        try (FileInputStream fis = new FileInputStream(contentMail.getAbsolutePath());
             XWPFDocument document = new XWPFDocument(fis)) {
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            for (XWPFParagraph para : paragraphs) {
                Selenide.$(By.xpath("//div[@tabindex='505'][@role='textbox']")).sendKeys(para.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addSignatureToLetter(String fromStr) {
        Selenide.$(By.xpath("//div[@tabindex='505'][@role='textbox']")).sendKeys(Keys.ENTER);
        Selenide.$(By.xpath("//div[@tabindex='505'][@role='textbox']")).sendKeys(Keys.CONTROL + "v");
        Selenide.$(By.xpath("//div[@tabindex='505'][@role='textbox']")).sendKeys(Keys.ENTER);
        Selenide.$(By.xpath("//div[@tabindex='505'][@role='textbox']")).sendKeys(Keys.CONTROL + "k");
        Selenide.$(By.xpath("//input[@data-test-id='link']")).should(Condition.appear).val("https://mail.ru/");
        Selenide.$(By.xpath("//input[@data-test-id='text']")).val("From Mail.ru").pressEnter();
        Selenide.$(By.xpath("//div[@tabindex='505'][@role='textbox']")).sendKeys(Keys.ENTER);
        Selenide.$(By.xpath("//div[@tabindex='505'][@role='textbox']")).sendKeys(fromStr);
    }
}
