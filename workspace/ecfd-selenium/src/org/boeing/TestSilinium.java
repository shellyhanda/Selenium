package org.boeing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestSilinium {
	public static void main(String r[]) throws InterruptedException{
		System.setProperty("webdriver.gecko.driver", "C:\\MyGitHub\\Selenium\\workspace\\ecfd-selenium\\lib\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.wikipedia.org/");
		
		WebElement link  = driver.findElement(By.linkText("English"));
		link.click();
		Thread.sleep(10000);
		WebElement searchBox = driver.findElement(By.id("searchInput"));
		searchBox.sendKeys("Software");
		Thread.sleep(5000);
		driver.quit();
		
	}
}
