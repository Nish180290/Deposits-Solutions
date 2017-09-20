package com.ds;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @author NISH Class NewUserPageUsingJunitChrome is use to test "New User Page"
 * 
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NewUserPageUsingJunitChrome {

	private static final Logger logger = Logger.getLogger(NewUserPageUsingJunitChrome.class.getName());
	private static WebDriver newUserPageDriver;
	static String username;
	String newUserPageURL = "http://85.93.17.135:9000";

	/**
	 * testSetUp Method to setup webdrivers for chrome
	 */
	@BeforeClass
	public static void testSetUp() {
		logger.info("Method: testSetUp() execution starts");
		System.setProperty("webdriver.chrome.driver",
				"D:/Deposits-solutions/maven-deposits-solutions/Driver/chromedriver_win32/chromedriver.exe");
		newUserPageDriver = new ChromeDriver();
		logger.info("launching chrome browser");
		newUserPageDriver.manage().window().maximize();
		logger.info("Method: testSetUp() execution starts");
	}

	/**
	 * navigateToPage Method is used navigate to url before every test method
	 * 
	 */
	@Before
	public void navigateToPage() {
		newUserPageDriver.navigate().to(newUserPageURL);
		newUserPageDriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	}

	/**
	 * verifyANewUserPageTitle Method is used verify newuser page is opening or
	 * not
	 */
	@Test
	public void verifyANewUserPageTitle() {
		logger.info("Method: verifyANewUserPageTitle() execution starts");
		String getTitle = newUserPageDriver.getTitle();
		logger.info("Page title: - " + getTitle);
		assertEquals(getTitle, "New User");
		logger.info("Method: verifyANewUserPageTitle() execution starts");

	}

	/**
	 * verifyBcreateUser Method is used verify whether user is getting created
	 * or not page is opening or not
	 */
	@Test
	public void verifyBCreateUser() {
		logger.info("Method: verifyBCreateUser() execution starts");
		username = randomUserNameGenerator();
		logger.info("Username generated is : " + username);
		newUserPageDriver.findElement(By.name("user.name")).sendKeys(username);
		newUserPageDriver.findElement(By.name("user.email")).sendKeys(username + "@gmail.com");
		newUserPageDriver.findElement(By.name("user.password")).sendKeys("test11@");
		newUserPageDriver.findElement(By.name("confirmationPassword")).sendKeys("test11@");
		newUserPageDriver.findElement(By.xpath("//button[@type='submit']")).click();
		ExpectedConditions.titleIs("All User");
		logger.info("Method: verifyBCreateUser() execution starts");
	}

	/**
	 * verifyCUniqueUser Method is used verify whether user name entered is
	 * unique or not
	 */
	@Test
	public void verifyCUniqueUser() {
		logger.info("Method: verifyCUniqueUser() execution starts");
		newUserPageDriver.findElement(By.name("user.name")).sendKeys(username);
		newUserPageDriver.findElement(By.name("user.email")).sendKeys("deposits@gmail.com");
		newUserPageDriver.findElement(By.name("user.password")).sendKeys("test11@");
		newUserPageDriver.findElement(By.name("confirmationPassword")).sendKeys("test11@");
		newUserPageDriver.findElement(By.xpath("//button[@type='submit']")).click();
		String InvalidMessage = newUserPageDriver.findElement(By.xpath(".//*[@id='user.name.error']")).getText();
		logger.info("InvalidMessage for username : " + InvalidMessage);
		assertEquals(InvalidMessage, "Must be unique");
		logger.info("Method: verifyCUniqueUser() execution Ends");
	}

	/**
	 * verifyDUniqueEmailId Method is used verify whether email id entered is
	 * unique or not
	 */
	@Test
	public void verifyDUniqueEmailId() {
		logger.info("Method : verifyDUniqueEmailId() execution starts");
		newUserPageDriver.findElement(By.name("user.name")).sendKeys("Deposits");
		newUserPageDriver.findElement(By.name("user.email")).sendKeys(username + "@gmail.com");
		newUserPageDriver.findElement(By.name("user.password")).sendKeys("test11@");
		newUserPageDriver.findElement(By.name("confirmationPassword")).sendKeys("test11@");
		newUserPageDriver.findElement(By.xpath("//button[@type='submit']")).click();
		String InvalidMessage = newUserPageDriver.findElement(By.xpath(".//*[@id='user.email.error']")).getText();
		logger.info("InvalidMessage for emailid : " + InvalidMessage);
		assertEquals(InvalidMessage, "Must be unique");
		logger.info("Method : verifyDUniqueEmailId() execution starts");
	}

	/**
	 * verifyEEmptyUserNameTextBox Method is used verify whether user name
	 * textbox is not empty
	 */
	@Test
	public void verifyEEmptyUserNameTextBox() {
		logger.info("Method : verifyEEmptyUserNameTextBox() execution starts");
		newUserPageDriver.findElement(By.name("user.email")).sendKeys("testuser@gmail.com");
		newUserPageDriver.findElement(By.name("user.password")).sendKeys("test11@");
		newUserPageDriver.findElement(By.name("confirmationPassword")).sendKeys("test11@");
		newUserPageDriver.findElement(By.xpath("//button[@type='submit']")).click();
		String InvalidMessage = newUserPageDriver.findElement(By.xpath(".//*[@id='user.name.error']")).getText();
		logger.info("InvalidMessage for empty username : " + InvalidMessage);
		assertEquals(InvalidMessage, "Required");
		logger.info("Method : verifyEEmptyUserNameTextBox() execution starts");

	}

	/**
	 * verifyFEmptyEmailIDTextBox Method is used verify whether email id textbox
	 * is empty or not
	 */
	@Test
	public void verifyFEmptyEmailIDTextBox() {
		logger.info("Method : verifyEEmptyUserNameTextBox() execution starts");
		newUserPageDriver.findElement(By.name("user.name")).sendKeys("testuser");
		newUserPageDriver.findElement(By.name("user.password")).sendKeys("test11@");
		newUserPageDriver.findElement(By.name("confirmationPassword")).sendKeys("test11@");
		newUserPageDriver.findElement(By.xpath("//button[@type='submit']")).click();
		String InvalidMessage = newUserPageDriver.findElement(By.xpath(".//*[@id='user.email.error']")).getText();
		logger.info("InvalidMessage for empty emailid : " + InvalidMessage);
		assertEquals(InvalidMessage, "Required");
		logger.info("Method : verifyEEmptyUserNameTextBox() execution ends");
	}

	/**
	 * verifyGEmptyPasswordTextBox Method is used verify whether password
	 * textbox is empty or not
	 */
	@Test
	public void verifyGEmptyPasswordTextBox() {
		logger.info("Method : verifyGEmptyPasswordTextBox() execution starts");
		newUserPageDriver.findElement(By.name("user.name")).sendKeys("testuser");
		newUserPageDriver.findElement(By.name("user.email")).sendKeys("testuser@gmail.com");
		newUserPageDriver.findElement(By.name("confirmationPassword")).sendKeys("test11@");
		newUserPageDriver.findElement(By.xpath("//button[@type='submit']")).click();
		String InvalidMessage = newUserPageDriver.findElement(By.xpath(".//*[@id='user.password.error']")).getText();
		logger.info("InvalidMessage for empty password : " + InvalidMessage);
		assertEquals(InvalidMessage, "Required");
		logger.info("Method : verifyGEmptyPasswordTextBox() execution ends");
	}

	/**
	 * verifyHPassAndConfirmPass Method is used verify whether the values in
	 * password & confirm password textbox is same or not
	 */
	@Test
	public void verifyHPassAndConfirmPass() {
		logger.info("Method : verifyHPassAndConfirmPass() execution starts");
		newUserPageDriver.findElement(By.name("user.name")).sendKeys("test1");
		newUserPageDriver.findElement(By.name("user.email")).sendKeys("test11@gmail.com");
		newUserPageDriver.findElement(By.name("user.password")).sendKeys("test11@");
		newUserPageDriver.findElement(By.name("confirmationPassword")).sendKeys("test11@");
		String cpass = newUserPageDriver.findElement(By.name("confirmationPassword")).getAttribute("value");
		newUserPageDriver.findElement(By.xpath("//button[@type='submit']")).click();
		logger.info("password and confirmpassword are same");
		assertEquals(cpass, "test11@");
		logger.info("Method : verifyHPassAndConfirmPass() execution starts");
	}

	/**
	 * verifyIFailScenarioPassAndConfirmPass Method is used to throw error if
	 * password and confirm password values are not same
	 */
	@Test
	public void verifyIFailScenarioPassAndConfirmPass() {
		logger.info("Method : verifyIFailScenarioPassAndConfirmPass() execution starts");
		newUserPageDriver.findElement(By.name("user.name")).sendKeys("test12");
		newUserPageDriver.findElement(By.name("user.email")).sendKeys("test112@gmail.com");
		newUserPageDriver.findElement(By.name("user.password")).sendKeys("test11@");
		newUserPageDriver.findElement(By.name("confirmationPassword")).sendKeys("test11");
		newUserPageDriver.findElement(By.xpath("//button[@type='submit']")).click();
		String InvalidMessage = newUserPageDriver.findElement(By.xpath(".//*[@id='user.confirmationPassword.error']"))
				.getText();
		String cpass = newUserPageDriver.findElement(By.name("confirmationPassword")).getAttribute("value");
		logger.info("password and confirmpassword are  not same");
		assertNotEquals(cpass, "test11@");
		logger.info("InvalidMessage for differnt passowrd : " + InvalidMessage);
		assertEquals(InvalidMessage, "passwords are not the same");
		logger.info("Method : verifyIFailScenarioPassAndConfirmPass() execution ends");
	}

	/**
	 * verifyJAllUsersLink Method is used to verify whether 'All Users'
	 * hyperlink is working or not
	 */
	@Test
	public void verifyJAllUsersLink() {
		logger.info("Method : verifyJAllUsersLink() execution starts");
		List<WebElement> anchors = newUserPageDriver.findElements(By.tagName("a"));
		Iterator<WebElement> i = anchors.iterator();

		while (i.hasNext()) {
			WebElement anchor = i.next();
			logger.info("anchor tag value : " + anchor.getAttribute("href"));
			if (anchor.getAttribute("href").contains("http://85.93.17.135:9000/users/all")) {
				anchor.click();
				break;
			}
		}
		newUserPageDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		ExpectedConditions.titleIs("All User");
		logger.info("Method : verifyJAllUsersLink() execution ends");
	}

	/**
	 * verifyJDeleteAllUser Method is used verify whether all users are getting
	 * deleted or not
	 * 
	 * @throws MalformedURLException,IOException
	 */

	@Test
	public void verifyKDeleteAllUser() throws MalformedURLException, IOException {
		logger.info("Method : verifyKDeleteAllUser() execution starts");
		URL url = new URL("http://85.93.17.135:9000/user/all");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("DELETE");
		int responseCode = connection.getResponseCode();
		assertEquals(responseCode, 200);
		logger.info("Method : verifyKDeleteAllUser() execution ends");
	}

	/**
	 * tearDown method is used to quit the chrome driver
	 */
	@AfterClass
	public static void tearDown() {
		logger.info("Method : tearDown() execution starts");
		newUserPageDriver.quit();
		logger.info("Method : tearDown() execution ends");
	}

	/**
	 * randomUserNameGenerator is util method to generate random 10 digit string
	 * 
	 * @return String randomString
	 * 
	 */
	private String randomUserNameGenerator() {
		return RandomStringUtils.randomAlphabetic(10);
	}

}
