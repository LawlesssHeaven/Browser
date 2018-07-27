// Name:Roberts Grants 
// Student number: 1611978
// Date 05.05.17
// Web Browser , CSC1022 Project
package browser;
import javax.swing.JFrame;
// Main method off application 
public class mainMethod {
	public static void main(String[] args) {
		Browser first = new Browser(); // Creates browser object
		String text = Homecfg.homecfg1(); // Call home page method 
		first.loadHtml(text); // Loads home page on start 
		first.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Default close operation
		
	}
	

}
