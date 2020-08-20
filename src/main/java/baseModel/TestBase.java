package baseModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import utility.TestUtil;
import utility.WebEventListener;


public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop; 

	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	private static String chromeFilepath =System.getProperty("user.dir") + "\\src\\main\\java\\drivers\\chromedriver.exe";
	private static String ffFilepath =System.getProperty("user.dir") + "\\src\\main\\java\\drivers\\geckodriver.exe";
	private static String inputStreamFilePath =System.getProperty("user.dir") + "\\src\\main\\java\\config\\config.properties";
	
	
	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(inputStreamFilePath); 
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialization(){
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", chromeFilepath);
			ChromeOptions options = new ChromeOptions();
			// options.addArguments("--headless");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			options.merge(capabilities);
			driver = new ChromeDriver(options); 
		}
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", ffFilepath);
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette",true);
			driver = new FirefoxDriver(); 
		}
		
		
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		// driver.get(prop.getProperty("baseUrl"));
		
	}
	
	public void goToTicTacToeHomePage(){
		 driver.get(prop.getProperty("TicTacToeQAUrl"));
	}
	
	
//	protected String getWebAppURL(String urlPart) {
//			String url = driver.get(prop.getProperty("baseUrl");
//            if (url == null) {
//                throw new RuntimeException("url for thread " + Thread.currentThread().getId() + " not specified");
//            } else {
//                return url + urlPart;
//            }
//        }
//    }
	
	
	
 }
		
