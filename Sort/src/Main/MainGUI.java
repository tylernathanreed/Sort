//* Description *//
// Title: Main GUI
// Author: Tyler Reed
// Runs the Program from a GUI

//* Package *//
package Main;

//* Libraries *//
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

//* Main GUI Class *//
@SuppressWarnings ("serial")
public class MainGUI extends JFrame
{
	//* Constructor *//
	// Creates an Instance of the Main GUI Class
	public MainGUI()
	{
		init();
	}

	// Initializes the GUI
	private void init()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Merge Sort");

		// Create the Frame Components
		JPanel panel_generate = initGenerate();

		// Add the Frame Components
		add(panel_generate);

		pack();
		setMinimumSize(new Dimension(400, 50));
	}

	private JPanel initGenerate()
	{
		// Initialize the Panel
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));

		// Create the Panel Components
		JCheckBox cbtn_generate = new JCheckBox("Generate");
		JLabel lbl_amount = new JLabel("Amount: ");
		JTextField txt_amount = new JTextField();
		JLabel lbl_range = new JLabel("Range: ");
		JTextField txt_min = new JTextField();
		JLabel lbl_delimiter = new JLabel("/");
		JTextField txt_max = new JTextField();

		// Configure the Panel Components
		cbtn_generate.setToolTipText("Whether or not to use Generated Data versus User Input Data");
		txt_amount.setToolTipText("Number of Elements to Generate");
		txt_min.setToolTipText("Lower Bound on Random Value");
		txt_max.setToolTipText("Upper Bound on Random Value");

		// Add the Panel Components
		panel.add(cbtn_generate);
		panel.add(Box.createRigidArea(new Dimension(20, 0)));
		panel.add(lbl_amount);
		panel.add(txt_amount);
		panel.add(Box.createRigidArea(new Dimension(20, 0)));
		panel.add(lbl_range);
		panel.add(txt_min);
		panel.add(Box.createRigidArea(new Dimension(5, 0)));
		panel.add(lbl_delimiter);
		panel.add(Box.createRigidArea(new Dimension(5, 0)));
		panel.add(txt_max);
		
		return panel;
	}
}
