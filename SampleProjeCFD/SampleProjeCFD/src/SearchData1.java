import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchData1 {

	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver", "C:\\MyGitHub\\Selenium\\workspace\\ecfd-selenium\\lib\\geckodriver.exe");
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
		//choosing role as Engineer
		WebElement userRoleName = driver.findElement((By.cssSelector("ul.dropdown-menu:nth-child(3) > li:nth-child(1) > a:nth-child(1)")));
		//WebElement userRoleName = driver.findElement((By.xpath("")));
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

	}

}
