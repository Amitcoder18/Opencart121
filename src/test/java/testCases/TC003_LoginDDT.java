package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
 * data is valid    login successful  test pass
 *                   login is fail      test fail
 * data is invalid   login is successful   test fail
 * data is invalid   login is fail       test is pass 
 */

public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass = DataProviders.class ,groups="Datadriven") // getting dataprovider from different class
	public void verify_loginDDT(String email,String pwd, String exp) throws InterruptedException
	{
		logger.info("********* starting TC003_loginDDT test  **********");
		try {
		// HomePage
		HomePage hp = new HomePage(driver);
		
		hp.clickMyAccount();
		hp.clickLogin();
		
		
		// LoginPage
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		// MyAccountPage
		MyAccountPage macc = new MyAccountPage(driver);
		
		boolean targetpage = macc.isMyAccountPageExists();
		
		if(exp.equalsIgnoreCase("valid"))
		{
			if(targetpage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(true);
				
			}
			else
			{
				Assert.assertTrue(false);
				}
		}
		if(exp.equalsIgnoreCase("invalid"))
		{
			if(targetpage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
			    Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		Thread.sleep(5000);
		logger.info("********* finished TC003_loginDDT test  **********");
	}

}
