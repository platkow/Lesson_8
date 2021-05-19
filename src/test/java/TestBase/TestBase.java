package TestBase;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBase {
    private WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger("TestBase.class");

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeEach
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        logger.info(">>>>> Start test >>>>>");
    }

    @AfterEach
    public void cleanUp(){
        driver.quit();
        logger.info(">>>>> Driver quit. Finish test >>>>>");
    }
}
