package pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SearchResutsPage {

	public WebDriver driver;

	// Find all page elements using FindBy and useful locators
		@FindBy(xpath = "/html/body/div[3]/div/div[13]/div/div[2]/div[2]/div/div/div")
		List<WebElement> search_results_contianers;
		
		@FindBy(xpath = "//*[@id=\"pnnext\"]/span[2]")
		WebElement Next_Button;

		@FindBy(xpath = "//td[@class='YyVfkd NKTSme']")
		WebElement Selected_Page_button;

			
		// use same created driver over all pages
		public SearchResutsPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

		
	// Create methods to use the page elements
	    public void CheckSearchResults() throws IOException {
	    	Assert.assertNotNull(search_results_contianers);

	    	System.out.println(" search_results_contianers size is >> " + search_results_contianers.size());
	    }

	    public int CountSearchResults() throws IOException {
	    	return search_results_contianers.size();
	    	}

	    public void GotoNextPage()  throws IOException {
	    Next_Button.click();	
	    }
	    
	    public void Check_Page(String PageNumber)  throws IOException {
    	Assert.assertEquals(Selected_Page_button.getText(),PageNumber);	
    	System.out.println(" Current page number is >> " + Selected_Page_button.getText());
	    }
	    	    
}
