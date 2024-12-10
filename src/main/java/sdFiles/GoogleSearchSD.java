package sdFiles;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import configuration.ReadFromExcel;
import pages.LandingPage;
import pages.SearchResutsPage;

public class GoogleSearchSD {

	// declare the used variable over this class 
		public static WebDriver driver;
	    				int	Res_count_1, Res_count_2 ;

	// create objects from other used classes
		ReadFromExcel 			readFromExcel 	= new ReadFromExcel();
	    private LandingPage 		landingPage 		= new LandingPage(driver);
	    private SearchResutsPage 	searchResutsPage	= new SearchResutsPage(driver);

	    				
		@Before
	    public void setUp() throws IOException {
			String Drivertype = readFromExcel.GetValueBasedonOtherColText("NeededDriver");
			System.out.println(" Driver is >> " + Drivertype);

			if (Drivertype.contains("Chrome")){
		        WebDriverManager.chromedriver().setup();
		        driver = new ChromeDriver();
		        }
			
			else if (Drivertype.contains("FireFox")){
				WebDriverManager.firefoxdriver().setup();
		        driver = new FirefoxDriver();
		        }
			
			else if (Drivertype.contains("Edge")){
				WebDriverManager.edgedriver().setup();
		        driver = new EdgeDriver();
		        }

			driver.manage().window().maximize();
	        // Set implicit wait
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    }

	// method to return the driver to be use same driver in each application page
		public static WebDriver getDriver() {
	        return driver;
	    }

	    
	@Given("I successfully connected to the Google Landing page using Google image")
	public void i_successfully_connected_to_the_google_landing_page_using_google_image() throws IOException {
		String URL =readFromExcel.GetValueBasedonOtherColText("URL");
		driver.get(URL);
		
//pass the used driver to the following application page
        landingPage = new LandingPage(getDriver());
        landingPage.CheckGoogleImage(); ;

        System.out.println(" URL is >> " + URL);
	}

	@When("I write {string}")
	public void i_write(String string) throws IOException {
		String Search_Text =readFromExcel.GetValueBasedonOtherColText(string);
		landingPage.WriteText(Search_Text);

		System.out.println(" Search Text is >> " + Search_Text);
	}

	@When("I Click Enter Key")
	public void i_click_enter_key() throws IOException {
		landingPage.ClickEnter();
	}

	@Then("I assert go to the search page number {string}")
	public void i_assert_go_to_the_search_page_number(String PageNumber)  throws IOException  {
		//pass the used driver to the following application page
		searchResutsPage = new SearchResutsPage(getDriver() );
		searchResutsPage.Check_Page(PageNumber);
	}

	@When("I click Next")
	public void i_click_next() throws IOException {
		searchResutsPage.GotoNextPage();
	}

	@When("I count the search reults in 2nd_page")
	public void i_count_the_search_reults_in_2nd_page() throws IOException {
		Res_count_1 = searchResutsPage.CountSearchResults();
		System.out.println(" Res_count_1 is " + Res_count_1);
	}

	
	@When("I count the search reults in 3rd_page")
	public void i_count_the_search_reults_in_3rd_page() throws IOException {
		Res_count_2 = searchResutsPage.CountSearchResults();
		System.out.println(" Res_count_2 is " + Res_count_2);

}
	
	@Then("I assert number of results on 2nd_page is equal to 3rd_page")
	public void i_assert_number_of_results_on_2nd_page_is_equal_to_3rd_page() {
		Assert.assertEquals(Res_count_1, Res_count_2);
	}

	@Then("I close the browser")
	public void i_close_the_browser() throws InterruptedException {
        if (driver != null) {
            driver.quit();
            }
        }
	}