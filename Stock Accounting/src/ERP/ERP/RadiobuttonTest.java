package ERP.ERP;

import java.security.KeyStore.Entry.Attribute;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import ERP.webelement;

public class RadiobuttonTest {
  @Test
  public void f() {
  FirefoxDriver driver = new FirefoxDriver();
  driver.navigate().to("http://echoecho.com/htmlforms10.html");
  driver.manage().window().maximize();
  WebElement block = driver.findElement(By.xpath("html/body/div[2]/table[7]/tbody/tr/td[4]/table/tbody/tr/td[1]/table[2]/tbody/tr[1]/td[1]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td"));
  List<webelement> radio= block.findElements(By.name("group1"));
  System.out.println(radio.size());
  for(int i=0;i<radio.size();i++)
  {
	  System.out.println(radio.get(i).getAttribute("value")+"--------"+radio.get(i).getAttribute("checked"));
	  
  }
  }
}

  

