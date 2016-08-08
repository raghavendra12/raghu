package ERP;

import java.awt.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class StockCatogires 
	{
		public static String expval;
	public static void main(String[] args)
	{
		WebDriver driver=new FirefoxDriver();
		
		driver.get("http://webapp.qedgetech.com");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath(".//*[@id='btnreset']")).click();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("master");
		
		driver.findElement(By.id("btnsubmit")).click();
		Object catname;
	    boolean flag=false;
		int count=0;
		WebElement stock= driver.findElement(By.linkText("stock items"));
		Actions act= new Actions(driver);
		act.moveToElement(stock).build().perform();
		driver.findElement(By.linkText("stock Categories")).click();
		driver.findElement(By.xpath(".//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a")).click();
		driver.findElement(By.id("x_Category_Name")).sendKeys("Raj");
		driver.findElement(By.id("btnAction")).click();
		driver.findElement(By.xpath("html/body/div[19]/div[2]/div/div[4]/div[2]/button[1]")).click();
		driver.findElement(By.xpath("html/body/div[17]/div[2]/div/div[4]/div[2]/button")).click();
		
		String pCnt=driver.findElement(By.xpath(".//*[@id='ewContentColumn']/div[3]/div[1]/form/div[2]/span[2]")).getText();
		String[] split = pCnt.split(" ");
		String cnt = split[2];
		int pc = Integer.parseInt(cnt);
		do
		{
		
			List<WebElement> rows = (List<WebElement>) driver.findElement(By.xpath("//*[@id='tbl_a_stock_categorieslist']/tbody/tr"));
			for(int i = 0; i<rows.size(); i++)
			{
				List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
				String actval=cols.get(3).getText();
				if (expval.equalsIgnoreCase(actval))
				{
					flag = true;
					break;
				}
				
			}
			if(flag==false)
			{
				driver.findElement(By.xpath(".//*[@id='ewContentColumn']/div[3]/div[1]/form/div[2]/div/div/div[2]/a[1]")).click();
				
			}
			count++;
		}
		while(count<=pc);
		if(flag)
		{
			return;
		}
		else
		{
			return;
	        	}
	     }

}
