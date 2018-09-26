package SeleniumWebDriverTests.SeleniumWebDriverTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * @author Marco Moro
 *  
 */

public class SeleniumWebDriverTestsTest {			
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	


	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	
		

	}

	@Test
	public void StaticDropdown() throws Exception {
		driver.get("https://www.fantasyfootballscout.co.uk/register/");

		Select s = new Select(driver.findElement(By.id("country_of_residence")));
		s.selectByValue("ITA");
		System.out.println(driver.findElement(By.id("country_of_residence")).getText());

	}


	@Test
	public void DynamicDropdown() throws Exception {
		driver.get("https://spicejet.com/");

		driver.findElement(By.xpath("//input[@id='ctl00_mainContent_ddl_originStation1_CTXT']")).click();

		driver.findElement(By.xpath("//a[@value='BKK']")).click();

		driver.findElement(By.xpath("(//a[@value='DXB'])[2]")).click();




	}



	@Test
	public void CheckBox() throws Exception {
		driver.get("https://www.fantasyfootballscout.co.uk/register/");

		assertFalse("false", driver.findElement(By.xpath("//p//input[@value='yes']")).isSelected());

		driver.findElement(By.xpath("//p//input[@value='yes']")).click();


		assertTrue("true", driver.findElement(By.xpath("//p//input[@value='yes']")).isSelected());


	}


	@Test
	public void RadioButton() throws Exception {
		driver.get("https://www.fantasyfootballscout.co.uk/register/");



		driver.findElement(By.xpath("//input[@value='yes']")).click();

		// count Radio button in the page with same attribute 

		System.out.println(driver.findElements(By.xpath("//input[@name='email_accept']")).size());



		// the for loop increments until finds second button and click it (2 buttons index 1)


		int count =driver.findElements(By.xpath("//input[@name='email_accept']")).size();

		for (int i=0;i<count;i++) {

			driver.findElements(By.xpath("//input[@name='email_accept']")).get(1).click();

		}


		/*
 in this scenario foor look click every radio button in the page as the variable (i) incemenet through the loop 

 int count =driver.findElements(By.xpath("//input[@name='email_accept']")).size();

for (int i=0;i<count;i++) {

	driver.findElements(By.xpath("//input[@name='email_accept']")).get(i).click();

  }


		 */

		// get the attributes for the radio buttons in the page and print them out the loop is 

		for (int i=0;i<count;i++) {

			System.out.println(driver.findElements(By.xpath("//input[@name='email_accept']")).get(i).getAttribute("value"));

		}

		// now we want to click on radio button with value yes 

		for (int i=0;i<count;i++) {

			String text = driver.findElements(By.xpath("//input[@name='email_accept']")).get(i).getAttribute("value");

			if (text.equals("yes")) {

				driver.findElements(By.xpath("//input[@name='email_accept']")).get(i).click();

			}

		}


	}

	@Test
	public void SeleniumMethods() throws Exception {
		driver.get("https://www.fantasyfootballscout.co.uk/");

		// check if an element is in hidden mode
		System.out.println(driver.findElement(By.xpath("//div[@class='login-here alive']")).isDisplayed()); 

		// extract text from the specific location for validation 
		System.out.println(driver.findElement(By.xpath("//div[@class='login-here alive']")).getText());

		// check if an element exists in a page and how many  
		System.out.println(driver.findElements( By.xpath("//div[@class='login-here alive']")).size()); 

		// check whetjer the element is enabled or disabled 
		System.out.println(driver.findElement(By.xpath("//div[@class='login-here alive']")).isEnabled());

		// special commands like tab or enter can be given by using sendkeys and special options 
		driver.findElement(By.xpath("//div[@class='login-here alive']")).sendKeys(Keys.TAB); 
		driver.findElement(By.xpath("//div[@class='login-here alive']")).sendKeys(Keys.ENTER); 

		// implicit wait 

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);  

		// explicit wait, the webdriver wait d is created and the time passed to my driver object

		WebDriverWait d=new WebDriverWait(driver, 5);  

		// penty of options are available as Expected conditions to apply the wait 
		d.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='login-here alive']")));  

		// Thread sleep example for 5 seconds 

		Thread.sleep(5000L); 



	}



	@Test
	public void SeleniumActions() throws Exception {
		driver.get("https://www.amazon.co.uk/");

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);  

		Actions a= new Actions(driver);



		// move to element and write in capital letter (see combined/concatenated actions) 

		a.moveToElement(driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"))).click().keyDown(Keys.SHIFT).sendKeys("hello").build().perform();
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).clear();
		// select the text i.e double click (just add the action double click

		a.moveToElement(driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"))).click().keyDown(Keys.SHIFT).sendKeys("hello").doubleClick().build().perform();



		// move mouse to location 
		a.moveToElement(driver.findElement(By.xpath("//a[@id='nav-link-yourAccount']//span[@class='nav-line-2']"))).build().perform(); 
		// click the web element it should be on the screen now
		driver.findElement(By.xpath("//span[@class='nav-text'][contains(text(),'Your Orders')]")).click(); 


		// right click is done by contextclick 

		a.moveToElement(driver.findElement(By.xpath("//span[@class='nav-logo-base nav-sprite']"))).contextClick().build().perform();

	}







	@Test
	public void Frames() throws Exception {
		driver.get("https://jqueryui.com/droppable/");

		// switch to frame by using element id 
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe.demo-frame")));

		// now the element in the frame is visible and clikkable 
		driver.findElement(By.id("draggable")).click();;

		// switch back to main page content gets out fromt he frame 
		driver.switchTo().defaultContent();

	}



	@Test
	public void DragDrop() throws Exception {
		driver.get("https://jqueryui.com/droppable/");

		// switch to frame by using element id 
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe.demo-frame")));

		// create new action
		Actions a = new Actions(driver);


		// identify element source and target
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement target = driver.findElement(By.id("droppable"));

		// pass element to the action and perform 
		a.dragAndDrop(source, target).perform();

	}



	@Test
	public void LinksInPage() throws Exception {
		driver.get("https://www.ebay.co.uk/");

		// count the link for the entire page (link are defined in ancher tag "a" 

		System.out.println("total links in page");
		System.out.println(driver.findElements(By.tagName("a")).size());

		/* count the link in the footer section 1) spy the entire section and take the footer id in this example "gbltfooter"
		 * then create a webelement variable that contains the entire footer 
		 */

		WebElement footer = driver.findElement(By.xpath(".//*[@id='glbfooter']"));

		// now I create a find elements for footer section as footer variable will act as a driver for the footer section (like a container) 
		System.out.println("total links in footer");
		System.out.println(footer.findElements(By.tagName("a")).size());

		// same concept for all the links in a colum 

		WebElement col = driver.findElement(By.xpath("//div[@id='gf-BIG']//tr//td[2]"));


		System.out.println("total links in column");
		System.out.println(col.findElements(By.tagName("a")).size());

		// get the colum links names by using a loop 

		for(int i=0; i<col.findElements(By.tagName("a")).size(); i++)

		{
			// print text for all the indexes 
			System.out.println(col.findElements(By.tagName("a")).get(i).getText()); 

		}	

	}



	@Test
	public void DynamicLink() throws Exception {
		driver.get("https://www.ebay.co.uk/");

		// click on the Dynamic link for the column and get title of the page and check text on new page

		WebElement col = driver.findElement(By.xpath("//div[@id='gf-BIG']//tr//td[2]"));

		for(int i=0; i<col.findElements(By.tagName("a")).size(); i++)

		{
			// print text for all the indexes 
			System.out.println(col.findElements(By.tagName("a")).get(i).getText()); 

			// going through the look if the text contains what we are looking for click it remeber lik is dynamic it can change its position
			if (col.findElements(By.tagName("a")).get(i).getText().contains("Sell for Charity"))

			{
				col.findElements(By.tagName("a")).get(i).click(); 
				// break the look you do not need to complete once you found link 
				break; 
			}	

		}	

		// print title for the new page
		System.out.println(driver.getTitle());

		//verify text is present on the page 
		assertEquals("eBay for Charity", driver.findElement(By.xpath("//h1[contains(text(),'eBay for Charity')]")).getText());


	}


	@Test
	public void CalendarUI() throws Exception {
		driver.get("https://www.firstchoice.co.uk/");

		// open the calendar
		driver.findElement(By.xpath("//input[@id='when']")).click();

		// now the goal is to select a specific month and date ( example 4th October 2019)

		// first step select month 

		//To identify the month  find unique identifier it could be the parent class as months for different years can have same class

		/* write a while loop (remember while loop expect an element to be true or false if true will go in the block of code)
		 * while look will keep executing ( click on next button to navigate to next month
		 * we need to put ! negation so the loop sees it as true and keep navigating until gets to October and it stops    
		 */
		while (!driver.findElement(By.xpath("//option[contains(@data-select-value,'October 2019')]")).getText().contains("October 2019"))
		{
			// until loop is true keeps clicking on next month navigation button 
			driver.findElement(By.xpath("//i[contains(@class,'icon-v2 icon-right')]")).click();
		}

		// once out of the loop we can now select date we want for the specific month 

		// Second step select date now     

		/* select a date better to use CSS Selector as usually calendar dates share the same class
		 * the class include all the dates and if the class name changes in the future it will be easy 
		 * to maintain the test*/
		// driver.findElement(By.cssSelector("b.4")).click(); (this is if you want to click on a day straight away)

		// i want to click on a specific date (i.e 4 )first i create a list 

		// List<WebElement> dates = driver.findElements(By.className("b")); (list class contains all the available date)

		// get the size for the calendar for the loop identified by the unique class

		int count =driver.findElements(By.className("b")).size(); // (gives you the size it can be use in the loop instead of list)

		// for loop will go to every instance of the calendar to get the text and put it into a sting
		for (int i=0; i<count; i++)

		{

			String text = driver.findElements(By.className("b")).get(i).getText();
			// if loop will identify my expected condition and click it ( date 4 is selected now 
			if(text.equalsIgnoreCase("4"))
			{
				driver.findElements(By.className("b")).get(i).click();
				break;
			}

		}

	}


	@Test
	public void SwitchFrame() throws Exception {
		driver.get("http://www.mediationbucks.org.uk/contact-us/");

		int number = FindFrameNumber(driver, By.xpath("//*[@class='recaptcha-checkbox-checkmark']"));  // integer number is the frame number to find it i use the method FindFrameNumber passing driver and xpath of the element I want to click
		driver.switchTo().frame(number);   // once the frame number is found by method i  pass the frame number to switch 
		driver.findElement(By.xpath("//*[@class='recaptcha-checkbox-checkmark']")).click();  // once I am in the frame now I can click the element 
		driver.switchTo().defaultContent();
	}
	public int FindFrameNumber (WebDriver driver, By by)

	{
		int i ; 
		int framecount = driver.findElements(By.tagName("iframe")).size(); 
		// remember tag name for frame is iframe so framecount variable will contain all the frame in the page thanks to size method
		for ( i=0; i<framecount; i++)
		{
			driver.switchTo().frame(i); 
			// check if the element is present in the page now we are in the frame ( remember for visible invisible you can use isDisplayed())
			// if is present size will be set to 1 otherwise loop will continue and check in the next frame until it find it 
			// put that into count variable and wirtie if loop 
			int count = driver.findElements(by).size();   // now you can go and get the checkbox element as you are into frame (spy on it get xpath)
			if (count > 0 )

			{

				break;
			}

			else {
				System.out.println("continue execution");
			}

		} 
		return i;

	}




	@Test
	public void AutoSuggestion() throws Exception {
		driver.get("https://www.yahoo.com/");


		driver.findElement(By.xpath("//input[@id='uh-search-box']")).sendKeys("cia");  // sending keys "cia"  autosuggestions pops up

		WebDriverWait wd = new WebDriverWait(driver,5);   // explicit wait wait until find the element in the given time not the whole time 

		wd.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='yui_3_18_0_3_1537867614440_1636']")));  // now we use the wait wd for a condition  in this case for the whole auto suggest box to appear

		driver.findElement(By.xpath("//span[@id='yui_3_18_0_3_1537543308362_1423']")).click(); // now the second choice can be selected identified by xpath

	}


	@Test
	public void GridTables() throws Exception {
		driver.get("https://www.premierleague.com/tables");

		// the aim for this test is to find out goals scored each team 

		// create a webElement that contains all the table content
		WebElement table = driver.findElement(By.cssSelector("div[Class='mainTableTab active']"));

		// count the row for the entire table for the for loop
		int count = table.findElements(By.xpath("//tr[contains(@class,'table')]")).size();
		int sum = 0;

		for (int i=0; i<count-10; i++)
		{   
			// for each table line get the text for column identified by inex 2 for class hidesmall and we put the value in a string named value
			String value = table.findElements(By.xpath("//tr[contains(@class,'table')]//td[@class='hideSmall'][1]")).get(i).getText();

			// need to convert string to integer before making any maths 
			int valueInteger = Integer.parseInt(value);

			// sum all the values 
			sum=sum + valueInteger;   

		}

		System.out.println("total goals for the top 5 in the premeier league is:" + sum);

	}	

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}


}

