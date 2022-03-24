package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class EnterPage extends Form {

    private IButton singInButton = getElementFactory().getButton(By.id("index_login_button"), "Sing in Button");
    private ITextBox loginTextBox = getElementFactory().getTextBox(By.id("index_email"), "Login Text Box");
    private ITextBox passwordTextBox = getElementFactory().getTextBox(By.id("index_pass"), "Password Text Box");
    private static final String PAGE_LOC = "index_login";

    public EnterPage() {
        super(By.id(PAGE_LOC), "Enter Page");
    }

    public void clickOnSingInButton() {
        singInButton.clickAndWait();
    }

    public void sendLogin(String emailOrPhone) {
        loginTextBox.clearAndType(emailOrPhone);
    }

    public void sendPassword(String password) {
        passwordTextBox.clearAndTypeSecret(password);
    }
}
