import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserLaunchPractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.gecko.driver","C:\\MyGitHub\\Selenium\\workspace\\ecfd-selenium\\lib\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.google.com");
		System.out.println(driver.getTitle());
		
		System.out.println(driver.getCurrentUrl());
		driver.get("http://www.yahoo.com");
		driver.navigate().back();
		
		driver.close();//it closes the current browser
		//driver.quit();//it closes all the browsers opened by the selenium script
	}

}
