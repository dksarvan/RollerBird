package com.org.CIBC;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TableHTML {

	
	WebDriver driver;

		 @BeforeTest
		 public void setup() throws Exception {
			 
			 	driver = new HtmlUnitDriver();				
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		  driver.get("http://only-testing-blog.blogspot.in/2014/05/form.html");
		 }

		 @AfterTest
		 public void tearDown() throws Exception {
		  driver.quit();
		 }

		 @Test
		 public void Handle_Dynamic_Webtable() {
		 
		  //To locate table.
		  WebElement mytable = driver.findElement(By.xpath(".//*[@id='post-body-8228718889842861683']/div[1]/table/tbody"));
		  //To locate rows of table.
		  List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
		  //To calculate no of rows In table.
		  int rows_count = rows_table.size();
		  
		  //Loop will execute till the last row of table.
		  for (int row=0; row<rows_count; row++){
		   //To locate columns(cells) of that specific row.
		   List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
		   //To calculate no of columns(cells) In that specific row.
		   int columns_count = Columns_row.size();
		   System.out.println("Number of cells In Row "+row+" are "+columns_count);
		   
		   //Loop will execute till the last cell of that specific row.
		   for (int column=0; column<columns_count; column++){
		    //To retrieve text from that specific cell.
		    String celtext = Columns_row.get(column).getText();
		    System.out.println("Cell Value Of row number "+row+" and column number "+column+" Is "+celtext);
		   }
		   System.out.println("--------------------------------------------------");
		  }  
		 }
		}