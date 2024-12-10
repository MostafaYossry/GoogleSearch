 package pages;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import configuration.ReadFromExcel;

public class LandingPage {

	public WebDriver driver;

	ReadFromExcel readFromExcel = new ReadFromExcel();
	public String GoogleImage_Xpath, SearchTextBox_id;
	
	// Find all page elements using FindBy and useful locators
		@FindBy(xpath = "/html/body/div[1]/div[2]/div/img")
		WebElement GoogleImage;
		
		@FindBy(id = "APjFqb")
		WebElement SearchTextBox;

	// use same created driver over all pages
		public LandingPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
		}

		
	// Create methods to use the page elements
	    public void CheckGoogleImage() throws IOException {
	    	Assert.assertTrue(GoogleImage.isDisplayed());
			}

	    public void WriteText(String SearchText) throws IOException {
	    	SearchTextBox.sendKeys(SearchText);
	    	}

	    public void ClickEnter() throws IOException {
	    	SearchTextBox.sendKeys(Keys.ENTER);
	    	}
	    
}
