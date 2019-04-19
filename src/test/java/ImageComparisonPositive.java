import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ImageComparisonPositive
{
    WebDriver driver;

    @Test
    public void imageComaparision() throws IOException
    {
        System.setProperty("webdriver.chrome.driver","C://PC//Projects//drivers//chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://demo.automationtesting.in/Register.html");

        Screenshot actualImage = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        ImageIO.write(actualImage.getImage(),"PNG",new File("C://PC//Projects//ScreenShootHyperion//testScreenshots//actual//FullPageScreenshot1.png"));

        BufferedImage expectedImage = ImageIO.read(new File("C://PC//Projects//ScreenShootHyperion//testScreenshots//expected//FullPageScreenshot1.png"));
        BufferedImage actual = actualImage.getImage();
//        Screenshot logoImageScreenshot = new AShot().takeScreenshot(driver, logoImage);
//        BufferedImage actualImage = logoImageScreenshot.getImage();

        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actual, expectedImage);
        Assert.assertFalse(diff.hasDiff(),"Images are Same");

        driver.quit();
    }
}