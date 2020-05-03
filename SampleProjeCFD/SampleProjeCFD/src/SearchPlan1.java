

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class SearchPlan1 {

	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver","C:\\MyGitHub\\Selenium\\workspace\\ecfd-selenium\\lib\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver,15);
		Actions action = new Actions(driver);
	
		//launch the application in browser
		driver.get("http://ecfd-dev.web.boeing.com:85/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//wait for the browser to load Home page
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		
		String pageTitle = driver.getTitle();
		//to bypass the WSSO page opens for the first time
		if (pageTitle.equals("WSSO - Redirector"))
		{
			WebElement autoSSOBtn = driver.findElement(By.xpath("//input[@value='Auto']"));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Auto']")));
			action.moveToElement(autoSSOBtn).click().perform();
		}
		//log the result
		System.out.println("Application launch with page title: "+pageTitle);
	//-----------------------------------------------------------------------------------------------------------------	
		//selecting user role from drop-down
		WebElement userRole = driver.findElement(By.cssSelector("button.btn-default:nth-child(2)"));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn-default:nth-child(2)")));
		action.moveToElement(userRole).click().perform();
		
		//WebElement userRoleName = driver.findElement((By.cssSelector("ul.dropdown-menu:nth-child(3) > li:nth-child(1) > a:nth-child(1)")));
		WebElement userRoleName = driver.findElement(By.xpath("//*[@ng-model='logingUser' and @value='ROLE_ECFD_LEAD_ENGINEER']"));
		js.executeScript("arguments[0].click();", userRoleName);
			
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@id='createPlans']")));
		String yourCFDpage = driver.getCurrentUrl();
		if (yourCFDpage.contains("myCFD"))
		{
			System.out.println("Navigated to home Page: "+yourCFDpage);
		}
		else
		{
			System.out.println("Failed to navigate to Your CFD Page: "+yourCFDpage);
			//driver.close();
		}
		
		//steps for search plans starts
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchplanspage")));
		//click on Search tab
		WebElement searchTab = driver.findElement(By.id("searchplanspage"));
		js.executeScript("arguments[0].click();", searchTab);
		//wait for the browser to load search page
		
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		String searchPage = driver.getCurrentUrl();
		if (searchPage.contains("searchplans"))
		{
			System.out.println("Navigated to search Page: "+searchPage);
		}
		else
		{
			System.out.println("Failed to navigate to search Page: "+searchPage);
			driver.close();
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("DataAnalysis")));
		//move the mouse control to Search plans section by clicking on one field-Owner 
		WebElement searchPlansOwner = driver.findElement(By.cssSelector("#selectedUser"));
		js.executeScript("arguments[0].scrollIntoView();", searchPlansOwner);
		js.executeScript("arguments[0].click();", searchPlansOwner);
	
		//search plans with the name as Baindoorkar
		searchPlansOwner.sendKeys("Baindoorkar");
		WebElement ownerNameHover = driver.findElement(By.cssSelector("div.form-group:nth-child(5) > div:nth-child(2) > ul:nth-child(2)"));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.form-group:nth-child(5) > div:nth-child(2) > ul:nth-child(2)")));
		action.moveToElement(ownerNameHover).click().perform();
		//click on search plan button
		WebElement searchPlanBtn = driver.findElement(By.id("searchecfdSearchID"));
		searchPlanBtn.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.searchplanTable:nth-child(5)")));
		
		WebElement searchPlanTable = driver.findElement(By.cssSelector("div.searchplanTable:nth-child(5)"));
		if(searchPlanTable.isDisplayed())
		{
			System.out.println("Search results table is displayed");
		}
		else
		{
			System.out.println("Search results table is NOT displayed");
			driver.close();
		}
	
		WebElement userName = driver.findElement(By.xpath("//*[@id='HPCdataRecords']/div/div[1]/div[3]/div[2]/div/div[1]/div/div[3]/div"));
		//WebElement recordCount = driver.findElement(By.xpath("//abbr[@title='through']"))
		
		String tableValue = userName.getText();
		if(tableValue.contains("Baindoorkar"))
		{
			System.out.println("Search results table is displaying the right data for owner: "+tableValue);
		}
		else
		{
			System.out.println("Search results table is NOT displaying the right data for owner: "+tableValue);
		}
		driver.close();
	}

}

