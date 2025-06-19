package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class RegistrationPage extends AbstractComponents {

    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstName")
    WebElement firstName;

    @FindBy(id = "lastName")
    WebElement lastName;

    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userMobile")
    WebElement userMobile;

    @FindBy(css = "select[formcontrolname='occupation']")
    WebElement occupation;

    @FindBy(css = "input[formcontrolname='gender'][value='Male']")
    WebElement genderMale;

    @FindBy(css = "input[formcontrolname='gender'][value='Female']")
    WebElement genderFemale;

    @FindBy(id = "userPassword")
    WebElement userPassword;

    @FindBy(id = "confirmPassword")
    WebElement confirmPassword;

    @FindBy(css = "input[formcontrolname='required']")
    WebElement ageCheckbox;

    @FindBy(id = "login")
    WebElement registerButton;

    @FindBy(css = ".toast-message")
    WebElement successMessage;

    public void completeRegistration(String fName, String lName, String email, String mobile, String occupationValue, String gender, String password, String confirmPassword) {
        enterFirstName(fName);
        enterLastName(lName);
        enterEmail(email);
        enterMobile(mobile);
        selectOccupation(occupationValue);
        selectGender(gender);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        checkAgeCheckbox();
        clickRegister();
    }

    public void enterFirstName(String fName) {
        firstName.sendKeys(fName);
    }

    public void enterLastName(String lName) {
        lastName.sendKeys(lName);
    }

    public void enterEmail(String email) {
        userEmail.sendKeys(email);
    }

    public void enterMobile(String mobile) {
        userMobile.sendKeys(mobile);
    }

    public void selectOccupation(String occupationValue) {
        occupation.sendKeys(occupationValue);
    }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("Male")) {
            genderMale.click();
        } else if (gender.equalsIgnoreCase("Female")) {
            genderFemale.click();
        }
    }

    public void enterPassword(String password) {
        userPassword.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        this.confirmPassword.sendKeys(confirmPassword);
    }

    public void checkAgeCheckbox() {
        if (!ageCheckbox.isSelected()) {
            ageCheckbox.click();
        }
    }

    public void clickRegister() {
        registerButton.click();
    }

    public String getSuccessMessage() {
        waitForElementVisibility(successMessage);
        return successMessage.getText();
    }
}