import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class BasicFrame extends javax.swing.JFrame {

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				BasicFrame inst = new BasicFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
				inst.setSize(309, 260);
			}
		});
	}

	public BasicFrame() {
		super();
		initGUI();
		BoxLayout thisLayout1 = new BoxLayout(getContentPane(),
				javax.swing.BoxLayout.Y_AXIS);
		getContentPane().setLayout(thisLayout1);
		{
			jPanel2 = new JPanel();
			getContentPane().add(jPanel2);
			FlowLayout jPanel2Layout = new FlowLayout();
			jPanel2.setLayout(jPanel2Layout);
			{
				jLabel4 = new JLabel();
				jPanel2.add(jLabel4);
				jLabel4.setText("                             ");
				jLabel4.setPreferredSize(new java.awt.Dimension(146, 19));
			}
			{
				jLabel1 = new JLabel();
				jPanel2.add(jLabel1);
				jLabel1.setText("\u632f\u5e45");
			}
			{
				jLabel3 = new JLabel();
				jPanel2.add(jLabel3);
				jLabel3.setPreferredSize(new java.awt.Dimension(27, 15));
			}
			{
				jLabel2 = new JLabel();
				jPanel2.add(jLabel2);
				jLabel2.setText("\u5468\u6ce2\u6570(Hz)");
			}
		}
		PanelOutport[] panelOupport = new PanelOutport[OscillatorComp.dataNumber];
		System.out.println(OscillatorComp.dataNumber);
		SpinnerNumberModel model1;
		SpinnerNumberModel model2;
		for (int i = 0; i < OscillatorComp.dataNumber; i++) {
			panel[i] = new Panel();
			panel[i].setLayout(new FlowLayout());
			switch (OscillatorComp.dataType[i]) {
			case 0:
				panel[i].jLabel1.setText("データポート" + i + "(long)	");
				model1 = new SpinnerNumberModel(10, 0, null, 10);
				panel[i].jSpinner1.setModel(model1);
				model2 = new SpinnerNumberModel(0.5d, 0.1d, null, 0.1d);
				panel[i].jSpinner2.setModel(model2);
				break;
			case 1:
				panel[i].jLabel1.setText("データポート" + i + "(short)	");
				model1 = new SpinnerNumberModel(5, 0, null, 1);
				panel[i].jSpinner1.setModel(model1);
				model2 = new SpinnerNumberModel(0.5d, 0.1d, null, 0.1d);
				panel[i].jSpinner2.setModel(model2);
				break;
			case 2:
				panel[i].jLabel1.setText("データポート" + i + "(double)	");
				model1 = new SpinnerNumberModel(0.05d, 0.0d, null, 0.01d);
				panel[i].jSpinner1.setModel(model1);
				model2 = new SpinnerNumberModel(1.0d, 0.1d, null, 0.1d);
				panel[i].jSpinner2.setModel(model2);
				break;
			case 3:
				panel[i].jLabel1.setText("データポート" + i + "(float)	");
				model1 = new SpinnerNumberModel(0.5d, 0.0d, null, 0.1d);
				panel[i].jSpinner1.setModel(model1);
				model2 = new SpinnerNumberModel(1.0d, 0.1d, null, 0.1d);
				panel[i].jSpinner2.setModel(model2);
				break;
			default:
			}
			panel[i].jSpinner1.setSize(60, 23);
			panel[i].jSpinner2.setSize(60, 23);
			add(panel[i]);
		}
		setTitle("OpenRTM-Osillator");
		pack();
	}

	private void initGUI() {
		try {
			FlowLayout thisLayout = new FlowLayout();
			getContentPane().setLayout(thisLayout);
			this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			this.setResizable(false);
			pack();
			this.setSize(436, 546);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	public static Panel[] panel = new Panel[OscillatorComp.dataNumber];
	private JLabel jLabel4;
	private JLabel jLabel3;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JPanel jPanel2;

}
