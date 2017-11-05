package com.org.CIBC;

import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestngDemo {
  @Test(description = "test1 to print something", groups="Regression")
  public void test1() {
	  System.out.println("This is test1");
  }
  @Test(groups="Regression")
  public void test2() {
	  System.out.println("This is test2");
	  Assert.assertEquals(1, 2);
  }
  @Test(dependsOnGroups="Regression")
  public void test3() {
	  System.out.println("This is test3");
  }
  @Test(dependsOnMethods="test5")
  public void test4() {
	  System.out.println("This is test4");	  
  }
  @Test
  public void test5() {
	  System.out.println("This is test5");
	  Assert.assertEquals(1, 2);
  }
  @BeforeMethod(description="This method is to print something")
  public void beforeMethod() {
	  System.out.println("It runs before each Test");
  }

  @AfterMethod(groups="Regression")
  public void afterMethod() {
	  System.out.println("It runs after each Test");
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("It runs before class");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("It runs after class");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("It runs before Test");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("It runs after Test");
  }
/*
  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("It runs before Suite");
  }
*/
  @AfterSuite
  public void afterSuite() {
	  System.out.println("It runs after Suite");
  }
  
  @BeforeGroups
  public void beforeGroups() {
	  System.out.println("It runs before Suite");
  }

  @AfterGroups
  public void afterGroups() {
	  System.out.println("It runs after Suite");
  }

}
