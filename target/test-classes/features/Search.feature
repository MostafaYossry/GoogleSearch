#Author: mos.yossry.cufe@gmail.com
#Task for SystemsLTD Egypty
#Google Search Scenario

Feature: Search for Systems Egypt

  Scenario: Search for System Egypt on Google
    Given I successfully connected to the Google Landing page using Google image
		
		When  I write "Search_Text"
		And   I Click Enter Key
		Then I assert go to the search page number "1"
		
		When  I click Next
		Then I assert go to the search page number "2"
		
		When I count the search reults in 2nd_page
		And  I click Next
		Then I assert go to the search page number "3"
		
		When I count the search reults in 3rd_page
		Then I assert number of results on 2nd_page is equal to 3rd_page
	
		Then I close the browser