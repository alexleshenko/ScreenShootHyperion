import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class FullPageScreenshot
{
    public static void main(String args[]) throws Exception{
        //инициализация драйвера + конфги
        System.setProperty("webdriver.chrome.driver","C://PC//Projects//drivers//chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("http://demo.automationtesting.in/Register.html");
        Thread.sleep(2000);
        Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        ImageIO.write(fpScreenshot.getImage(),"PNG",new File("C://PC//Projects//ScreenShootHyperion//testScreenshots//expected//FullPageScreenshot1.png"));
        driver.quit();
    }
}