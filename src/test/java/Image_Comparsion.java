import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

public class Image_Comparsion
{
    public static void main(String args[]) throws Exception
    {
        System.setProperty("webdriver.chrome.driver","C://PC//Projects//drivers//chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.google.com");

        WebElement logoImage = driver.findElement(By.xpath("//img[@id='hplogo']"));
        BufferedImage expectedImage = ImageIO.read(new File("C://PC//Projects//ScreenShootHyperion//testScreenshots//FullPageScreenshotNew.png"));
        Screenshot logoImageScreenshot = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, logoImage);
        BufferedImage actualImage = logoImageScreenshot.getImage();
        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
        Assert.assertFalse(diff.hasDiff(),"Result of Image comparsion");
        System.out.println("Images Compared Sucesfully");
    }
}