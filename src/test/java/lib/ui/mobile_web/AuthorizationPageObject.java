package lib.ui.mobile_web;

import lib.ui.MainPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {

    private final static String
            LOGIN_BUTTON = "xpath://body/div/a[text()='Log in']",
            LOGIN_INPUT = "css:input[name='wpName']",
            PASSWORD_INPUT = "css:input[name='wpPassword']",
            SUBMIT_BUTTON = "css:button#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickAuthButton() {
        this.waitForElementToBePresent(LOGIN_BUTTON, "Unable to locate auth button", 5);
        while (this.isElementPresent(LOGIN_BUTTON)) {
            this.waitForElementAndClick(LOGIN_BUTTON, "Unable to locate and click auth button", 5);
        }
    }

    public void enterLoginData(String  login, String password) {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Unable to locate and put data into login field", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Unable to locate and put data into password field", 5);
    }

    public void submitForm() {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Unable to locate and click submit button", 5);
    }
}
