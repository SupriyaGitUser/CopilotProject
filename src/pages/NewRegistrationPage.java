package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import abstractComponents.AbstractComponents;

public class NewRegistrationPage extends AbstractComponents {

    WebDriver driver;

    public NewRegistrationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstName")
    WebElement firstNameInput;

    @FindBy(id = "lastName")
    WebElement lastNameInput;

    @FindBy(id = "userEmail")
    WebElement emailInput;

    @FindBy(id = "userMobile")
    WebElement mobileInput;

    @FindBy(css = "select[formcontrolname='occupation']")
    WebElement occupationDropdown;

    @FindBy(css = "input[formcontrolname='gender'][value='Male']")
    WebElement maleRadio;

    @FindBy(css = "input[formcontrolname='gender'][value='Female']")
    WebElement femaleRadio;

    @FindBy(id = "userPassword")
    WebElement passwordInput;

    @FindBy(id = "confirmPassword")
    WebElement confirmPasswordInput;

    @FindBy(css = "input[formcontrolname='required']")
    WebElement ageCheckbox;

    @FindBy(id = "login")
    WebElement registerBtn;

    @FindBy(css = ".toast-message")
    WebElement toastMessage;

    public void register(String fName, String lName, String email, String mobile, String occupation, String gender, String password, String confirmPwd) {
        firstNameInput.sendKeys(fName);
        lastNameInput.sendKeys(lName);
        emailInput.sendKeys(email);
        mobileInput.sendKeys(mobile);
        occupationDropdown.sendKeys(occupation);
        if (gender.equalsIgnoreCase("Male")) {
            maleRadio.click();
        } else if (gender.equalsIgnoreCase("Female")) {
            femaleRadio.click();
        }
        passwordInput.sendKeys(password);
        confirmPasswordInput.sendKeys(confirmPwd);
        if (!ageCheckbox.isSelected()) {
            ageCheckbox.click();
        }
        registerBtn.click();
    }

    public String getToastMessage() {
        waitForElementVisibility(toastMessage);
        return toastMessage.getText();
    }
}
