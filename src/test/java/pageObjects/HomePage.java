package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage {

	@FindBy(xpath = "//div[@class='owm-loader' and @aria-label='Loading']")
	@CacheLookup
	public WebElement lblLoading;

	@FindBy(xpath = "//div[@id='desktop-menu']/form[@role='search']/input[@placeholder='Weather in your city']")
	@CacheLookup
	public WebElement txtHeaderSearch;

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;

		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(factory, this);
	}

	public void enterSearchValue(String value) {
		txtHeaderSearch.sendKeys(value);
	}

}
