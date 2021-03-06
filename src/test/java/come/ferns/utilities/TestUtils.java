package come.ferns.utilities;



import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.util.Date;

//import java.io.IOException;
import org.apache.commons.io.FileUtils;

import com.ferns.base.TestBase;

public class TestUtils extends TestBase {
	public static String fileName;
	public static void captureScreenshot(String name) {
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		fileName = System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+name;
		Date date = new Date();
		fileName = fileName+date.toString().replace(":", " ").replace(" ","_")+".png";
		System.out.println(fileName);
		try {
			//FileHandler.copy(src, new File(fileName));
			FileUtils.copyFile(src,new File(fileName));
			log.error("Screen shot taken");
		} catch (Exception e) {
			log.warn("No screenshot taken. "+e.getMessage());
			e.printStackTrace();
		}
		
	}
}
