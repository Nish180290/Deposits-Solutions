package com.ds;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


	public class NewUserTests {
		

	    
		private WebDriver newUserPageDriver,allUsersPageDriver; 
		String newUserPageURL = "http://85.93.17.135:9000";
//		String allUsersPageUrl = "http://85.93.17.135:9000/users/all";

		@BeforeSuite
		public void testSetUp() {
			
			System.out.println("*******************");
			System.setProperty("webdriver.chrome.driver", "D:/Deposits-solutions/maven-deposits-solutions/Driver/chromedriver_win32/chromedriver.exe");
			newUserPageDriver = new ChromeDriver();
//			allUsersPageDriver= new ChromeDriver();
			System.out.println("launching chrome browser");
			newUserPageDriver.manage().window().maximize();
		}
		
		@Test
		public void verifyNewUserPageTitle() {
			newUserPageDriver.navigate().to(newUserPageURL);
			newUserPageDriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			String getTitle = newUserPageDriver.getTitle();
			System.out.println("Page title: - "+getTitle);
			Assert.assertEquals(getTitle, "New User");
		}
		
		@Test
		public void createUser() {
			newUserPageDriver.navigate().to(newUserPageURL);
			String username =randomUserNameGenerator();
			System.out.println("Username generated is : " + username);
	  		newUserPageDriver.findElement(By.name("user.name")).sendKeys(username);
			newUserPageDriver.findElement(By.name("user.email")).sendKeys(username+"@gmail.com");
			newUserPageDriver.findElement(By.name("user.password")).sendKeys("test11@");
			newUserPageDriver.findElement(By.name("confirmationPassword")).sendKeys("test11@");
			newUserPageDriver.findElement(By.xpath("//button[@type='submit']")).click();
			String getAllUserPageTitle = allUsersPageDriver.getTitle();
			System.out.println("Page title: - "+getAllUserPageTitle);
/*			newUserPageDriver.findElement(By.)
			Assert.assertEquals(allUsersPageUrl., "All User");*/
			System.out.println("user created");
		}

		private String randomUserNameGenerator() {
			byte[] array = new byte[7]; // length is bounded by 7
			    new Random().nextBytes(array);
			    return new String(array, Charset.forName("UTF-8"));
		}
		
		@Test
		public void emptyUserNameTextBoxValidation() {
			newUserPageDriver.navigate().to(newUserPageURL);
			newUserPageDriver.findElement(By.name("user.email")).sendKeys("testuser@gmail.com");
			newUserPageDriver.findElement(By.name("user.password")).sendKeys("test11@");
			newUserPageDriver.findElement(By.name("confirmationPassword")).sendKeys("test11@");			
			newUserPageDriver.findElement(By.xpath("//button[@type='submit']")).click();
			String InvalidMessage =newUserPageDriver.findElement(By.xpath(".//*[@id='user.name.error']")).getText();
			
			if ("Required".equals(InvalidMessage))
	        {
	            System.out.println(InvalidMessage);
	        }
			Assert.assertEquals(InvalidMessage, "Required");
//			user.name.error

		}
		
		@Test
		public void emptyEmailIDTextBoxValidation() {		
			newUserPageDriver.navigate().to(newUserPageURL);
			newUserPageDriver.findElement(By.name("user.name")).sendKeys("testuser");
			newUserPageDriver.findElement(By.name("user.password")).sendKeys("test11@");
			newUserPageDriver.findElement(By.name("confirmationPassword")).sendKeys("test11@");			
			newUserPageDriver.findElement(By.xpath("//button[@type='submit']")).click();
			String InvalidMessage =newUserPageDriver.findElement(By.xpath(".//*[@id='user.email.error']")).getText();
			
			if ("Required".equals(InvalidMessage))
	        {
	            System.out.println(InvalidMessage);
	        }
			Assert.assertEquals(InvalidMessage, "Required");
		}

		@Test
		public void emptyPasswordTextBoxValidation() {		
			newUserPageDriver.navigate().to(newUserPageURL);
			newUserPageDriver.findElement(By.name("user.name")).sendKeys("testuser");
			newUserPageDriver.findElement(By.name("confirmationPassword")).sendKeys("test11@");			
			newUserPageDriver.findElement(By.xpath("//button[@type='submit']")).click();
			String InvalidMessage =newUserPageDriver.findElement(By.xpath(".//*[@id='user.password.error']")).getText();
			
			if ("Required".equals(InvalidMessage))
	        {
	            System.out.println(InvalidMessage);
	        }
			Assert.assertEquals(InvalidMessage, "Required");
		}
		
		@Test
		public void verifyPassword() {
			newUserPageDriver.navigate().to(newUserPageURL);
	  		newUserPageDriver.findElement(By.name("user.name")).sendKeys("test1");
			newUserPageDriver.findElement(By.name("user.email")).sendKeys("test11@gmail.com");
			newUserPageDriver.findElement(By.name("user.password")).sendKeys("test11@");
			newUserPageDriver.findElement(By.name("user.password")).getAttribute("value");
			newUserPageDriver.findElement(By.name("confirmationPassword")).sendKeys("test11@");
			String cpass =newUserPageDriver.findElement(By.name("confirmationPassword")).getAttribute("value");
			Assert.assertEquals(cpass, "test11@");
		}
		
		@AfterSuite
		public void tearDown() {
			newUserPageDriver.quit();
		}
		

	}

