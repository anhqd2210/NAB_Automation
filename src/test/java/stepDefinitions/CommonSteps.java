package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.HomePage;

public class CommonSteps {
	WebDriver driver;
	String homePageURL = "https://openweathermap.org/";
	HomePage homepage;

	enum BrowserName {
		chrome, firefox
	}

	public void openBrowser() {
		String projectPath = System.getProperty("user.dir");
		System.out.print(projectPath);

//		System.setProperty("webdriver.chrome.driver", projectPath + "/drivers/chromedriver.exe");
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();

		configWebdriver(BrowserName.chrome);
	}

	private void configWebdriver(BrowserName browserName) {
		switch (browserName) {
		case chrome:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case firefox:
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}

	public void waitForElement(int seconds, String locator) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
	}

	public void waitForElement(int seconds, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementInvisibility(int seconds, String locator) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
	}

	public void waitForElementInvisibility(int seconds, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void sendENTERKey() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
	}
}
