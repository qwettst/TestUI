package ServiceMail;

import java.io.File;

public interface MailManager {
    void authMail(String login, String pass);

    void openLetter();

    void sendLetter();

    void addTitleToletter(String receiverEmail, String titleLetter, File uploadFile);

    void addContentToLetter(File contentMail);

    void addSignatureToLetter(String fromStr);
}
