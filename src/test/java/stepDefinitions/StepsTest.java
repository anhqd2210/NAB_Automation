package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageObjects.HomePage;

public class StepsTest extends CommonSteps {

	@Before
	public void beforeScenario() {
		openBrowser();
	}

	@Given("Open the browser")
	public void open_the_browser() {
		openBrowser();
	}

	@When("^User navigates to (.*)$")
	public void user_navigates_to(String url) {
		driver.get(url);
		homepage = new HomePage(driver);
		waitForElementInvisibility(15, homepage.lblLoading);
	}

	@When("User navigates to Homepage")
	public void user_navigates_to_Homepage() {
		driver.get(homePageURL);
		homepage = new HomePage(driver);
		waitForElementInvisibility(15, homepage.lblLoading);
	}

	@Then("Verify that Search box is display")
	public void Verify_that_Search_box_is_display() {
		homepage = new HomePage(driver);
		waitForElement(10, homepage.txtHeaderSearch);
		assertEquals("Search box is display", true, homepage.txtHeaderSearch.isDisplayed());
	}

	@When("^User searches for keyword (.*)$")
	public void User_searches_for_keyword(String keyword) {
		homepage = new HomePage(driver);
		homepage.enterSearchValue(keyword);
		sendENTERKey();
	}

	@Then("^Verify that user should see (.*)$")
	public void Verify_that_user_should_see(String expected) {
		String cityNameXpath = "//div[@id='forecast-list']//td//a[contains(@href, 'city')]";
		waitForElement(10, cityNameXpath);
		boolean status = false;
		List<WebElement> lstElem = driver.findElements(By.xpath(cityNameXpath));

		if (lstElem.size() > 0) {
			for (WebElement elem : lstElem) {
				String actual = elem.getText().trim();
				if (actual.equals(expected)) {
					status = true;
					break;
				}
			}
			if (!status) {
				assertEquals("Cannot find value '" + expected + "' in list", true, status);
			}
		} else {
			assertEquals("Cannot find value '" + expected + "' in list", true, status);
		}
	}

	@When("User close the browser")
	public void User_close_the_browser() {
		driver.close();
	}

	@After
	public void afterScenario() {
		driver.quit();
	}

}
