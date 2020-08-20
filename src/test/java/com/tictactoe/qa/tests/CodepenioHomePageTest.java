package com.tictactoe.qa.tests;

import java.util.concurrent.TimeoutException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tictactoe.qa.pages.CodepenioHomePage;

import baseModel.TestBase;


public class CodepenioHomePageTest extends TestBase {
	
	CodepenioHomePage codepenioHomePage;

	public CodepenioHomePageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		goToTicTacToeHomePage();
		codepenioHomePage = new CodepenioHomePage();
		driver.switchTo().frame("result");
	}

	@Test(priority=1)
	public void ticTactoePageTitleShouldDisplay(){
		String title = codepenioHomePage.validateHomePageTitle();
		Assert.assertTrue(title.contains("QA Interview Assignment"));
		//Assert.assertEquals(title, "CodePen - QA Interview Assignment");
	}
	
		@Test(priority=2)
		public void tictacToeGameShouldAllowToPlayAndwin(){
			codepenioHomePage.enterNumberToPlay("2");
			codepenioHomePage.clickPlayAndDisplayTable();
			String displayEndGameText = codepenioHomePage.clickTableToPlayTicTacToe();
			Assert.assertTrue(displayEndGameText.contains("Congratulations player"));
		}
		
		@Test(priority=3)
		public void tictacToeGameDoesNotDisplayErrorMssg() throws TimeoutException{
			codepenioHomePage.enterNumberToPlay("3333333333333");
			boolean displayBugError = codepenioHomePage.clickPlayAndfailWithTimeout();
			Assert.assertFalse(displayBugError);
			// Thread.setDefaultUncaughtExceptionHandler(eh); 
		}
	
		@Test(priority=4)
		public void ticTacToeDoesNotDisplayWinner() throws TimeoutException{
			codepenioHomePage.enterNumberToPlay("5");
			codepenioHomePage.clickPlayAndDisplayTable();
			codepenioHomePage.enterNumberToPlay("4");
			codepenioHomePage.clickPlayAndDisplayTable();
			String displayEndGameText = codepenioHomePage.clickTableToPlayTicTacToe();
			Assert.assertFalse(displayEndGameText.contains("Congratulations player"));
		}

	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}
