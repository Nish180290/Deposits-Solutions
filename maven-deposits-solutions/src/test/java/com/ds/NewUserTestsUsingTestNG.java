package com.ds;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class NewUserTestsUsingTestNG {

	private WebDriver newUserPageDriver;
	static String username;
	String newUserPageURL = "http://85.93.17.135:9000";

	@BeforeSuite
	public void testSetUp() {

		System.out.println("*******************");
		System.setProperty("webdriver.chrome.driver",
				"D:/Deposits-solutions/maven-deposits-solutions/Driver/chromedriver_win32/chromedriver.exe");
		newUserPageDriver = new ChromeDriver();
		System.out.println("launching chrome browser");
		newUserPageDriver.manage().window().maximize();
	}

	@Test(priority=1)
	public void verifyNewUserPageTitle() {
		newUserPageDriver.navigate().to(newUserPageURL);
		newUserPageDriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		String getTitle = newUserPageDriver.getTitle();
		System.out.println("Page title: - " + getTitle);
		Assert.assertEquals(getTitle, "New User");
	}

	@Test(priority=2)
	public void createUser() {
		newUserPageDriver.navigate().to(newUserPageURL);
		username = randomUserNameGenerator();
		System.out.println("Username generated is : " + username);
		newUserPageDriver.findElement(By.name("user.name")).sendKeys(username);
		newUserPageDriver.findElement(By.name("user.email")).sendKeys(username + "@gmail.com");
		newUserPageDriver.findElement(By.name("user.password")).sendKeys("test11@");
		newUserPageDriver.findElement(By.name("confirmationPassword")).sendKeys("test11@");
		newUserPageDriver.findElement(By.xpath("//button[@type='submit']")).click();
		System.out.println("user created");
	}

	private String randomUserNameGenerator() {
		return RandomStringUtils.randomAlphabetic(10);
	}

	@Test(priority=3)
	public void checkUniqueUser() {
		newUserPageDriver.navigate().to(newUserPageURL);
		System.out.println("Username generated in checkuniqueuser " + username);
		newUserPageDriver.findElement(By.name("user.name")).sendKeys(username);
		newUserPageDriver.findElement(By.name("user.email")).sendKeys("deposits@gmail.com");
		newUserPageDriver.findElement(By.name("user.password")).sendKeys("test11@");
		newUserPageDriver.findElement(By.name("confirmationPassword")).sendKeys("test11@");
		newUserPageDriver.findElement(By.xpath("//button[@type='submit']")).click();
		String InvalidMessage = newUserPageDriver.findElement(By.xpath(".//*[@id='user.name.error']")).getText();
		Assert.assertEquals(InvalidMessage, "Must be unique");
	}

	@Test(priority=4)
	public void checkUniqueEmailId() {
		newUserPageDriver.navigate().to(newUserPageURL);
		System.out.println("Username generated in checkuniqueEmailid : " + username);
		newUserPageDriver.findElement(By.name("user.name")).sendKeys("Deposits");
		newUserPageDriver.findElement(By.name("user.email")).sendKeys(username + "@gmail.com");
		newUserPageDriver.findElement(By.name("user.password")).sendKeys("test11@");
		newUserPageDriver.findElement(By.name("confirmationPassword")).sendKeys("test11@");
		newUserPageDriver.findElement(By.xpath("//button[@type='submit']")).click();
		String InvalidMessage = newUserPageDriver.findElement(By.xpath(".//*[@id='user.email.error']")).getText();
		Assert.assertEquals(InvalidMessage, "Must be unique");
	}

	@Test(priority=5)
	public void emptyUserNameTextBoxValidation() {
		newUserPageDriver.navigate().to(newUserPageURL);
		newUserPageDriver.findElement(By.name("user.email")).sendKeys("testuser@gmail.com");
		newUserPageDriver.findElement(By.name("user.password")).sendKeys("test11@");
		newUserPageDriver.findElement(By.name("confirmationPassword")).sendKeys("test11@");
		newUserPageDriver.findElement(By.xpath("//button[@type='submit']")).click();
		String InvalidMessage = newUserPageDriver.findElement(By.xpath(".//*[@id='user.name.error']")).getText();

		if ("Required".equals(InvalidMessage)) {
			System.out.println(InvalidMessage);
		}
		Assert.assertEquals(InvalidMessage, "Required");

	}

	@Test(priority=6)
	public void emptyEmailIDTextBoxValidation() {
		newUserPageDriver.navigate().to(newUserPageURL);
		newUserPageDriver.findElement(By.name("user.name")).sendKeys("testuser");
		newUserPageDriver.findElement(By.name("user.password")).sendKeys("test11@");
		newUserPageDriver.findElement(By.name("confirmationPassword")).sendKeys("test11@");
		newUserPageDriver.findElement(By.xpath("//button[@type='submit']")).click();
		String InvalidMessage = newUserPageDriver.findElement(By.xpath(".//*[@id='user.email.error']")).getText();

		if ("Required".equals(InvalidMessage)) {
			System.out.println(InvalidMessage);
		}
		Assert.assertEquals(InvalidMessage, "Required");
	}

	@Test(priority=7)
	public void emptyPasswordTextBoxValidation() {
		newUserPageDriver.navigate().to(newUserPageURL);
		newUserPageDriver.findElement(By.name("user.name")).sendKeys("testuser");
		newUserPageDriver.findElement(By.name("confirmationPassword")).sendKeys("test11@");
		newUserPageDriver.findElement(By.xpath("//button[@type='submit']")).click();
		String InvalidMessage = newUserPageDriver.findElement(By.xpath(".//*[@id='user.password.error']")).getText();

		if ("Required".equals(InvalidMessage)) {
			System.out.println(InvalidMessage);
		}
		Assert.assertEquals(InvalidMessage, "Required");
	}

	@Test(priority=8)
	public void verifyPassword() {
		newUserPageDriver.navigate().to(newUserPageURL);
		newUserPageDriver.findElement(By.name("user.name")).sendKeys("test1");
		newUserPageDriver.findElement(By.name("user.email")).sendKeys("test11@gmail.com");
		newUserPageDriver.findElement(By.name("user.password")).sendKeys("test11@");
		newUserPageDriver.findElement(By.name("user.password")).getAttribute("value");
		newUserPageDriver.findElement(By.name("confirmationPassword")).sendKeys("test11@");
		String cpass = newUserPageDriver.findElement(By.name("confirmationPassword")).getAttribute("value");
		Assert.assertEquals(cpass, "test11@");
	}

	@AfterSuite
	public void tearDown() {
		newUserPageDriver.quit();
	}

}
