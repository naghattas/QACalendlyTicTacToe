package com.tictactoe.qa.pages;

import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import baseModel.TestBase;

public class CodepenioHomePage extends TestBase {
	
	
	@FindBy(xpath="//input[@id='number']")
	WebElement EnterNumberToPlayEditField;
	
	@FindBy(xpath="//*[@id='start']")
	WebElement PlayButton;
	
	@FindBy(xpath="//*[@id='table']/tr/td")
	WebElement TicTacToeTable;
	
	@FindBy(xpath="//*[@id='endgame']")
	WebElement EndGameTxt;

	
	
	public CodepenioHomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String validateHomePageTitle() {
		return driver.getTitle(); // 'CodePen - QA Interview Assignment'
	}

	public String enterNumberToPlay(String num) {
		// driver.switchTo().frame("result");
		EnterNumberToPlayEditField.sendKeys(num);
		return num;		 
	}
	
	public boolean clickPlayAndDisplayTable(){
		PlayButton.click();
		return TicTacToeTable.isDisplayed();
	}
	
	public boolean clickPlayAndfailWithTimeout(){
		PlayButton.click();
		try {
			WebDriverWait wait = new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("//*[@id='table']/tr/td")));
		}
		 catch (ElementNotVisibleException e) {
//			 Assert.fail(
		                e.printStackTrace();
		                return false; //+ ("No error message is displayed if table does not display")
	        }
		return TicTacToeTable.isDisplayed();
	}
	
	 public String clickTableToPlayTicTacToe() {	
		WebElement dropdown = driver.findElement(By.id("table"));
		List<WebElement> options = dropdown.findElements(By.tagName("td"));
		 Iterator<WebElement> it=options.iterator();
		   while(it.hasNext())
			   try{
				   it.next().click();
			   }
		   catch(Exception e){
	           return EndGameTxt.getText();
	        }
		return EndGameTxt.getText();        
		}
	
}
	

		    



