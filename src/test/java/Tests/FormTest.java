package Tests;

import TestBase.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FormTest extends TestBase {
    private Logger logger = LoggerFactory.getLogger("FormTest.class");
    String url;
    String firstName;
    String lastName;
    String email;
    String age;
    String continent;
    String navigationCommand;
    String msg;
    String filePath;

    @Test
    public void shouldFillFormWithSuccess() {
        Random random = new Random();
        url = "https://seleniumui.moderntester.pl/form.php";
        firstName = "Bill";
        lastName = "Gates";
        email = "billgates@gmail.com";
        age = "65";
        continent = "North America";
        navigationCommand = "Navigation Commands";
        msg = "Fill form test.";
        filePath = System.getProperty("user.dir") + "/src/main/resources/Files/file.txt";

        getDriver().get(url);
        logger.info("Webpage url: {}", url);

        getDriver().findElement(By.id("inputFirstName3")).sendKeys(firstName);
        getDriver().findElement(By.cssSelector("#inputLastName3")).sendKeys(lastName);
        logger.debug("First name is: {}, last name is: {}", firstName, lastName);

        getDriver().findElement(By.cssSelector("#inputEmail3")).sendKeys(email);
        logger.debug("Email address is: {}", email);

        List<WebElement> gridRadiosSex = getDriver().findElements(By.cssSelector("[name='gridRadiosSex']"));
        gridRadiosSex.get(0).click();
        logger.debug("Radio Sex properly clicked.");

        getDriver().findElement(By.id("inputAge3")).sendKeys(age);
        logger.debug("Age is: {}", age);

        List<WebElement> gridRadiosExperience = getDriver().findElements(By.cssSelector("[name='gridRadiosExperience']"));
        logger.debug("Number of radio buttons is: {}", gridRadiosExperience.size());

        int index = random.nextInt(gridRadiosExperience.size());
        gridRadiosExperience.get(index).click();
        logger.debug("Chosen random index of radio is: {}", index);

        WebElement otherCheckbox = getDriver().findElement(By.cssSelector("#gridCheckOther"));
        if (!otherCheckbox.isSelected()) {
            otherCheckbox.click();
        }
        logger.debug("'Other' Checkbox properly clicked.");

        Select continents = new Select(getDriver().findElement(By.id("selectContinents")));
        continents.selectByVisibleText(continent);
        logger.debug("Chosen continent is: {}", continent);

        Select seleniumCommands = new Select(getDriver().findElement(By.cssSelector("#selectSeleniumCommands")));
        seleniumCommands.selectByVisibleText(navigationCommand);
        logger.debug("Chosen selenium command is: {}", navigationCommand);

        getDriver().findElement(By.cssSelector("#chooseFile")).sendKeys(filePath);
        logger.debug("File path: " + filePath);

        getDriver().findElement(By.cssSelector("#additionalInformations")).sendKeys(msg);
        logger.debug("Additional message: {}", msg);

        WebElement button1 = getDriver().findElement(By.cssSelector(".btn.btn-secondary.btn-lg.active"));
        button1.click();
        logger.debug("Button '" + button1.getText() + "' correctly clicked.");

        WebElement button2 = getDriver().findElement(By.cssSelector(".btn.btn-primary"));
        button2.click();
        logger.debug("Button '" + button2.getText() + "' correctly clicked.");

        WebElement msg = getDriver().findElement(By.id("validator-message"));
        assertThat(msg.getText(), equalTo("Form send with success"));
    }
}
