// Name:Roberts Grants 
// Student number: 1611978
// Date 05.05.17
// Web Browser , CSC1022 Project
package browser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Homecfg {
// Method that reads configuration file , made to setup home page 
	public static String homecfg1() {
		String text = null;
	try (BufferedReader myReader = new BufferedReader(
			new FileReader("browser/home_cfg.cfg"))) {
		
		text = myReader.readLine(); // Read line 
			
	
	} catch (IOException exp) {
		exp.printStackTrace();
	}
	return text; // Returns string , link to home page. 

}
}
