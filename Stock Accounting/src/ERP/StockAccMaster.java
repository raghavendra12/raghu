package ERP;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class StockAccMaster 

{
	//Global Variables
	public static String strUrl="http://webapp.qedgetech.com";
	public static String expval,actval;
	public WebDriver driver;
	public String strUsername="admin",strPassword="master";
	public String prPath="F:\\OJT3\\StockAccounting\\src\\com\\stockAcc\\properties\\stockAcc.properties";
	public FileInputStream fi;
	public Properties pr;
	
	
	/*Domain Name:ERP
	Project Name :Stock Accounting
	Function Name: stockAcc_Launch()
	Arguments      : url
	Description    : It will launch Stock Accounting Application
	Return Type    : String
	Author         :xxxxxxxxxxx
	Creation Date  :19/7/2016*/
	
	
	public String stockAcc_Launch(String strUrl) throws IOException
	{
		fi=new FileInputStream(prPath);
		pr=new Properties();
		pr.load(fi);
		expval="Login";
		driver=new FirefoxDriver();
		driver.get(strUrl);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		actval=driver.findElement(By.id(pr.getProperty("login"))).getText();
		if (expval.equalsIgnoreCase(actval)) 
		{
			return "Pass";
			
		}
		else
		{
			return "Fail";
		}
		
	}
	
	/*Domain Name:ERP
	Project Name :Stock Accounting
	Function Name: stockAcc_Login()
	Arguments      : strUsername,strPassword
	Description    : It will login  Stock Accounting Application
	Return Type    : String
	Author         :xxxxxxxxxxx
	Creation Date  :19/7/2016*/
	
	public String stockAcc_Login(String strUsername, String strPassword)

	{
		expval="administrator";
		driver.findElement(By.id(pr.getProperty("username"))).clear();
		driver.findElement(By.id(pr.getProperty("username"))).sendKeys(strUsername);
		
		driver.findElement(By.id(pr.getProperty("password"))).clear();
		driver.findElement(By.id(pr.getProperty("password"))).sendKeys(strPassword);
		
		driver.findElement(By.id(pr.getProperty("login"))).click();
		
		actval=driver.findElement(By.xpath(".//*[@id='msUserName']/font/strong")).getText();
		if (expval.equalsIgnoreCase(actval))
		{
			return "Pass";
		}
		else
		{
			return "Fail";
		}
	}
	
	
	public String stockAcc_Catcreation(String catname)
	{
		expval=catname;
		boolean flag=false;
		int count=0;
		WebElement stock=driver.findElement(By.linkText("Stock Items"));
		
		Actions act=new Actions(driver);
		act.moveToElement(stock).build().perform();
		
		driver.findElement(By.linkText("Stock Categories")).click();
		
		driver.findElement(By.xpath(".//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a")).click();
		
		driver.findElement(By.id("x_Category_Name")).sendKeys(catname);
		
		driver.findElement(By.id("btnAction")).click();
		
		driver.findElement(By.xpath("html/body/div[17]/div[2]/div/div[4]/div[2]/button[1]")).click();
		
		driver.findElement(By.xpath("html/body/div[17]/div[2]/div/div[4]/div[2]/button")).click();
		
		
		
		String pCnt=driver.findElement(By.xpath(".//*[@id='ewContentColumn']/div[3]/div[1]/form/div[2]/span[2]")).getText();
		
		String[] split=pCnt.split(" ");
		
		String cnt=split[2];
		
		int pC=Integer.parseInt(cnt);
		
		do
		{
			List<WebElement> rows=driver.findElements(By.xpath("//table[@id='tbl_a_stock_categorieslist']/tbody/tr"));
			for (int i = 0; i < rows.size(); i++) 
			{
				List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
				String actval=cols.get(3).getText();
				if (expval.equalsIgnoreCase(actval))
				{
					flag=true;
					break;
				}
			}
			if (flag)
			{
				break;
			}
			else
			{			
				driver.findElement(By.xpath(".//*[@id='ewContentColumn']/div[3]/div[1]/form/div[2]/div/div/div[2]/a[1]")).click();
			}
			count++;
		} 
		while (count <= pC);
		if (flag) 
		{
			return "Pass";
		}
		else
		{
			return "Fail";
		}
		
	

	}
}



