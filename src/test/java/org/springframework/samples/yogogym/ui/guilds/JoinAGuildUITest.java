package org.springframework.samples.yogogym.ui.guilds;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JoinAGuildUITest {

	@LocalServerPort
	private int port;

	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeEach
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testJoinAGuild() throws Exception {
	
		as("client9");
		joinGuild();
		checkYouJoined();
	}

	@AfterEach
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
	
	private void as(String username) {
		
		driver.get("http://localhost:"+port);
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("client1999");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	private void joinGuild() {
		
		driver.findElement(By.linkText("Client")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul")).click();
		driver.findElement(By.linkText("Client")).click();
		driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul/li[2]/ul/li[6]/a/span[2]")).click();
		driver.findElement(By.linkText("Calisthenics")).click();
		driver.findElement(By.linkText("Join the Guild")).click();
	}
	
	private void checkYouJoined() {
		
		driver.findElement(By.linkText("See your Guild")).click();
	}
}
