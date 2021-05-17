package Tests;

import TestBase.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FormTest extends TestBase {
    @Test
    public void shouldFillFormWithSuccess(){
        Random random = new Random();

        getDriver().get("https://seleniumui.moderntester.pl/form.php");

        getDriver().findElement(By.id("inputFirstName3")).sendKeys("Bill");

        getDriver().findElement(By.cssSelector("#inputLastName3")).sendKeys("Gates");

        getDriver().findElement(By.cssSelector("#inputEmail3")).sendKeys("billgates@gmail.com");

        List<WebElement> gridRadiosSex = getDriver().findElements(By.cssSelector("[name='gridRadiosSex']"));
        gridRadiosSex.get(0).click();

        getDriver().findElement(By.id("inputAge3")).sendKeys("65");

        List<WebElement> gridRadiosExperience = getDriver().findElements(By.cssSelector("[name='gridRadiosExperience']"));
            gridRadiosExperience.get(random.nextInt(7) + 1).click();

        WebElement otherCheckbox = getDriver().findElement(By.cssSelector("#gridCheckOther"));
        if (!otherCheckbox.isSelected()){
            otherCheckbox.click();
        }

        Select continents = new Select(getDriver().findElement(By.id("selectContinents")));
        continents.selectByVisibleText("North America");

        Select seleniumCommands = new Select(getDriver().findElement(By.cssSelector("#selectSeleniumCommands")));
        seleniumCommands.selectByValue("navigation-commands");

        getDriver().findElement(By.cssSelector("#chooseFile")).sendKeys(System.getProperty("user.dir") + "/src/main/resources/Files/file.txt");

        getDriver().findElement(By.cssSelector("#additionalInformations")).sendKeys("Form fill test.");

        getDriver().findElement(By.cssSelector(".btn.btn-secondary.btn-lg.active")).click();

        getDriver().findElement(By.cssSelector(".btn.btn-primary")).click();

        WebElement msg = getDriver().findElement(By.id("validator-message"));
        assertThat(msg.getText(), equalTo("Form send with success"));
    }
}
