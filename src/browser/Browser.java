// Name:Roberts Grants 
// Student number: 1611978
// Date 05.05.17
// Web Browser , CSC1022 Project
package browser;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

public class Browser extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// JTextField for address bar.
	private JTextField addressBar;
	// JEditorPane for displaying web sites. 
	private JEditorPane display;
	// Browser Buttons
	private JButton backButton, forwardButton ,reloadButton,homeButton,historyButton;
	// ArrayList to store history
	private ArrayList<String> webpage = new ArrayList<String>();
	// PrintWriter to save browser history
	private PrintWriter write = null;
	// Index for History
	
	private int currentIndex = 0; // Index displays current state of web page 0 is first page 1 is second
	// We create constructor 
	public Browser() {
		super("Lucky Browser");

		// Back button and  its ActionListener
		backButton = new JButton("<Back");
		backButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent event) {
				actionBack(); 
			}
		// Action that button will perform 
			private void actionBack() {
				if (webpage.size()<=1) {
					//backButton.setEnabled(false);
				}
				else {
					currentIndex--; // Performs back action by decreasing current index 
					loadHtml(webpage.get(currentIndex)); // Loads needed web page 
				}
			}
			
		});

		// Forward Button and Action Listener
		forwardButton = new JButton("Forward>");
		
		forwardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				actionForward();
			}
			// Action that forward button will perform 
			private void actionForward() {
				System.out.println(webpage.size());
			if (webpage.size()<=1) {
				//forwardButton.setEnabled(false);
			}
			
			else {
				currentIndex++; // Performs back action by increasing current index 
				loadHtml(webpage.get(currentIndex)); // Loads needed web page
			}
			}
		});
		
		// Reload button and Action Listener
		reloadButton = new JButton("Reload");
		// Action that reload button will perform 
		reloadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				actionReload();
			}
			// Reloads web page
			private void actionReload() {
				loadHtml(webpage.get(currentIndex));
			}
		});
		
		
		//History Button
		historyButton = new JButton("History");
		historyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				actionHistory();
			}

			private void actionHistory() {
				// Display history text file in separate window 
				try {
					JTextArea ta = new JTextArea(20, 60);
					ta.read(new FileReader("browser/history.cfg"), null);
					ta.setEditable(false);
					JOptionPane.showMessageDialog(historyButton, new JScrollPane(ta));
					}
					catch (IOException ioe) {
					ioe.printStackTrace();
					}
			}
		});
	// Writes history to text file
		 try {
			this.write = new PrintWriter(new FileOutputStream(new File("browser/history.cfg"), true));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		// Import home configuration method 
		String text = Homecfg.homecfg1();

		// Home Button and it's action listener 
		homeButton = new JButton("Home");
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
                actionHome();
			}
			// Loads home page
			private void actionHome() {
				loadHtml(text);
			}
		});
		
		// Address Bar action listener
		addressBar = new JTextField("http://");
		addressBar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				currentIndex++;
				loadHtml(event.getActionCommand());
			}
		});

		// Create JEditorPane for displaying webpages 
		display = new JEditorPane();
		display.setEditable(false);
		display.addHyperlinkListener(

				// Create event listener that activates only when link is
				// clicked
				new HyperlinkListener() {
					public void hyperlinkUpdate(HyperlinkEvent event) {
						if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
							loadHtml(event.getURL().toString());

						}

					}
				});

		// Create JPanel and add all buttons to it . 
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1, 3));
		buttonsPanel.add(backButton);
		buttonsPanel.add(forwardButton);
		buttonsPanel.add(reloadButton);
		buttonsPanel.add(homeButton);
		buttonsPanel.add(addressBar);
		buttonsPanel.add(historyButton);

	    addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.out.println("Closed");
                write.close();
            }
        });
	    // Add buttons panel to  JFrame and set its location 
		add(buttonsPanel, BorderLayout.NORTH);
		// Add browser display to JFrame and set its location 
		add(new JScrollPane(display), BorderLayout.CENTER);
		// Set size of JFrame window 
		setSize(800, 600);
		setVisible(true);
	}
	

	// Load HTML method and bad link exception . My method that loads websites 
	void loadHtml(String userText) {
		webpage.add(userText);
		for (String s: webpage){
			write.println(s);
		}

		System.out.println(webpage); // 
// Bad link exception 
		try {
			display.setPage(userText);
			addressBar.setText(userText);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(display, "Invalid URL");
		}

	}

}