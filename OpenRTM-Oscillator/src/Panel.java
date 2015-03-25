

import java.awt.Dimension;

import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class Panel extends javax.swing.JPanel {
	public JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	public JSpinner jSpinner2;
	public JSpinner jSpinner1;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new Panel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public Panel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(372, 34));
			{
				jLabel1 = new JLabel();
				this.add(jLabel1);
				jLabel1.setText("jLabel1");
				jLabel1.setPreferredSize(new java.awt.Dimension(104, 19));
			}
			{
				jLabel3 = new JLabel();
				this.add(jLabel3);
				jLabel3.setText("     ");
			}
			{
				SpinnerListModel jSpinner1Model = 
					new SpinnerListModel(
							new String[] { "Sun", "Mon" , "Tue" , "Wed" , "Thu" , "Fri" , "Sat" });
				jSpinner1 = new JSpinner();
				this.add(jSpinner1);
				jSpinner1.setModel(jSpinner1Model);
				jSpinner1.setPreferredSize(new java.awt.Dimension(60, 26));
			}
			{
				jLabel2 = new JLabel();
				this.add(jLabel2);
				jLabel2.setPreferredSize(new java.awt.Dimension(26, 20));
			}
			{
				SpinnerListModel jSpinner2Model = 
					new SpinnerListModel(
							new String[] { "Sun", "Mon" , "Tue" , "Wed" , "Thu" , "Fri" , "Sat" });
				jSpinner2 = new JSpinner();
				this.add(jSpinner2);
				jSpinner2.setModel(jSpinner2Model);
				jSpinner2.setPreferredSize(new java.awt.Dimension(60, 26));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
