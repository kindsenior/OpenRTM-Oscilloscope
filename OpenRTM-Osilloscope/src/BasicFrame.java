import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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

	GraphicsComponent gc;

	{
		// Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			//javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JPanel panelWidget;
	private JTextField txtFilename;
	private JComboBox cmbFiletype;
	private JLabel jLabel4;
	public static JSpinner spnValueAxis;
	private JButton btnReturn;
	public static JSlider jSlider1;
	private JPanel jPanel4;
	private JPanel panelCanvas;
	private JPanel jPanel2;
	private JScrollPane jScrollPane1;
	public JButton btnStopAnimation;
	private JButton btnNext;
	private JButton btnBottom;
	private JButton btnTop;
	private JButton btnBack;
	private JPanel graphOne;
	private JTabbedPane jTabbedPane1;
	public static JCheckBox cbIn[] = new JCheckBox[OsiloscopeComp.dataNumber];
	private JPanel panelSetup2;
	private JLabel jLabel8;
	private JLabel jLabel7;
	private JLabel jLabel6;
	public static JSpinner spnAxisTime;
	public static JSpinner spnFrameRate;
	private JLabel jLabel5;
	private JLabel jLabel3;
	private JPanel panelSetup1;
	private JPanel panelSetup3;
	private JLabel jLabel2;
	private JLabel jLabel1;
	public JButton btnStartAnimation;
	private JButton btnWrite;
	private JScrollPane scrollSetup2;
	public static JLabel lblLog;
	private JPanel jPanel1;

	private int clickX;
	private int clickY;
	private int moveX;
	private int moveY;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				BasicFrame inst = new BasicFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public BasicFrame() {
		super();
		initGUI();
		panelCanvas.setLayout(new BorderLayout());
		gc = new GraphicsComponent();
		panelCanvas.add(gc, BorderLayout.CENTER);
		setTitle("OpenRTM-Oscilloscope");
	}

	private void initGUI() {
		try {
			this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			this.setResizable(false);
			this.setPreferredSize(new java.awt.Dimension(808, 596));
			{
				panelWidget = new JPanel();
				FlowLayout panelWidgetLayout = new FlowLayout();
				getContentPane().add(panelWidget, BorderLayout.WEST);
				panelWidget.setLayout(panelWidgetLayout);
				panelWidget.setPreferredSize(new java.awt.Dimension(216, 453));
				panelWidget.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
				{
					btnStartAnimation = new JButton();
					panelWidget.add(btnStartAnimation);
					btnStartAnimation.setPreferredSize(new java.awt.Dimension(197, 36));
					btnStartAnimation.setText("アニメーションの開始");
					btnStartAnimation.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnStartAnimationActionPerformed(evt);
						}
					});
				}
				{
					btnStopAnimation = new JButton();
					panelWidget.add(btnStopAnimation);
					btnStopAnimation.setText("\u30a2\u30cb\u30e1\u30fc\u30b7\u30e7\u30f3\u505c\u6b62");
					btnStopAnimation.setPreferredSize(new java.awt.Dimension(197, 36));
					btnStopAnimation.setEnabled(false);
					btnStopAnimation.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							btnStopAnimationActionPerformed(evt);
						}
					});
				}
				{
					jLabel7 = new JLabel();
					panelWidget.add(jLabel7);
					jLabel7.setText("\u63cf\u753b\u30b9\u30b1\u30fc\u30eb                                  ");
				}
				{
					panelSetup1 = new JPanel();
					panelWidget.add(panelSetup1);
					panelSetup1.setPreferredSize(new java.awt.Dimension(199, 202));
					panelSetup1.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
					{
						jPanel4 = new JPanel();
						panelSetup1.add(jPanel4);
						jPanel4.setPreferredSize(new java.awt.Dimension(186, 97));
						jPanel4.setLayout(null);
						{
							btnTop = new JButton();
							jPanel4.add(btnTop);
							btnTop.setText("\u2191");
							btnTop.setBounds(67, 0, 47, 32);
							btnTop.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									btnTopActionPerformed(evt);
								}
							});
						}
						{
							btnNext = new JButton();
							jPanel4.add(btnNext);
							btnNext.setText("\u2193");
							btnNext.setBounds(67, 65, 47, 32);
							btnNext.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									btnNextActionPerformed(evt);
								}
							});
						}
						{
							btnBack = new JButton();
							jPanel4.add(btnBack);
							btnBack.setText("\u2192");
							btnBack.setBounds(114, 33, 47, 32);
							btnBack.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									btnBackActionPerformed(evt);
								}
							});
						}
						{
							btnBottom = new JButton();
							jPanel4.add(btnBottom);
							btnBottom.setText("\u2190");
							btnBottom.setBounds(20, 33, 47, 32);
							btnBottom.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									btnBottomActionPerformed(evt);
								}
							});
						}
						{
							btnReturn = new JButton();
							jPanel4.add(btnReturn);
							btnReturn.setBounds(67, 33, 47, 32);
							btnReturn.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									btnReturnActionPerformed(evt);
								}
							});
						}
					}
					{
						jLabel3 = new JLabel();
						panelSetup1.add(jLabel3);
						jLabel3.setText("\u6a2a\u8ef8(s/div) :");
						jLabel3.setPreferredSize(new java.awt.Dimension(67, 13));
					}
					{
						SpinnerListModel spnAxisXModel = new SpinnerListModel
						(Data.timeAxisData);
						spnAxisTime = new JSpinner();
						panelSetup1.add(spnAxisTime);
						spnAxisTime.setModel(spnAxisXModel);
						spnAxisTime.setValue(1.0);
						spnAxisTime.setPreferredSize(new java.awt.Dimension(97, 20));
						spnAxisTime.addChangeListener(new ChangeListener() {
							public void stateChanged(ChangeEvent evt) {
								spnAxisTimeStateChanged(evt);
							}
						});
					}
					{
						jLabel4 = new JLabel();
						panelSetup1.add(jLabel4);
						jLabel4.setText("\u7e26\u8ef8(1/div) :");
						jLabel4.setPreferredSize(new java.awt.Dimension(67, 13));
					}
					{

						SpinnerListModel spnAxisYModel = new SpinnerListModel
						(Data.valueAxisData);
						spnValueAxis = new JSpinner();
						panelSetup1.add(spnValueAxis);
						spnValueAxis.setModel(spnAxisYModel);
						spnValueAxis.setValue(100.0);
						spnValueAxis.setPreferredSize(new java.awt.Dimension(98, 20));
						spnValueAxis.addChangeListener(new ChangeListener() {
							public void stateChanged(ChangeEvent evt) {
								spnAxisValueStateChanged(evt);
							}
						});
					}
					{
						jLabel5 = new JLabel();
						panelSetup1.add(jLabel5);
						jLabel5.setText("\u30d5\u30ec\u30fc\u30e0\u30ec\u30fc\u30c8 :");
					}
					{
						SpinnerListModel spnFrameRateModel = new SpinnerListModel
						(Data.frameRate);
						spnFrameRate = new JSpinner();
						panelSetup1.add(spnFrameRate);
						spnFrameRate.setModel(spnFrameRateModel);
						spnFrameRate.setValue(100);
						spnFrameRate.setPreferredSize(new java.awt.Dimension(98, 20));
					}
				}
				{
					jLabel8 = new JLabel();
					panelWidget.add(jLabel8);
					jLabel8.setText("\u30c7\u30fc\u30bf\u9078\u629e                                     ");
				}
				{
					scrollSetup2 = new JScrollPane();
					panelWidget.add(scrollSetup2);
					scrollSetup2.setPreferredSize(new java.awt.Dimension(201, 77));
					scrollSetup2.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
					{
						panelSetup2 = new JPanel();
						BoxLayout panelSetup2Layout = new BoxLayout(panelSetup2, javax.swing.BoxLayout.Y_AXIS);
						panelSetup2.setLayout(panelSetup2Layout);
						scrollSetup2.setViewportView(panelSetup2);
						{
							for (int i = 0; i < OsiloscopeComp.dataNumber; i++) {
								final int j = i;
								cbIn[i] = new JCheckBox();
								cbIn[i].setForeground(gc.c[i]);
								cbIn[i].setText(OsiloscopeComp.dataName[i]);
								cbIn[i].setSelected(true);
								cbIn[i].addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										cbInActionPerformed(j, evt);
									}
								});
								panelSetup2.add(cbIn[i]);
							}
						}

					}
				}
				{
					jLabel6 = new JLabel();
					panelWidget.add(jLabel6);
					jLabel6.setText("\u30d5\u30a1\u30a4\u30eb\u51fa\u529b                                   ");
				}
				{
					panelSetup3 = new JPanel();
					panelWidget.add(panelSetup3);
					FlowLayout panelSetup1Layout = new FlowLayout();
					panelSetup3.setLayout(panelSetup1Layout);
					panelSetup3.setPreferredSize(new java.awt.Dimension(204, 102));
					panelSetup3.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
					{
						jLabel1 = new JLabel();
						panelSetup3.add(jLabel1);
						jLabel1.setText("\u30d5\u30a1\u30a4\u30eb\u540d :");
						jLabel1.setPreferredSize(new java.awt.Dimension(68, 19));
						jLabel1.setFont(new java.awt.Font("MS UI Gothic", 0, 12));
						jLabel1.setBounds(0, 0, 0, 0);
					}
					{
						txtFilename = new JTextField();
						panelSetup3.add(txtFilename);
						txtFilename.setPreferredSize(new java.awt.Dimension(117, 25));
						txtFilename.setBounds(0, 0, 0, 0);
					}
					{
						jLabel2 = new JLabel();
						panelSetup3.add(jLabel2);
						jLabel2.setText("\u30d5\u30a1\u30a4\u30eb\u5f62\u5f0f :");
						jLabel2.setPreferredSize(new java.awt.Dimension(92, 13));
						jLabel2.setFont(new java.awt.Font("MS UI Gothic", 0, 12));
						jLabel2.setBounds(0, 0, 0, 0);
					}
					{
						ComboBoxModel cmbFiletypeModel = new DefaultComboBoxModel
						(Data.fileType);
								
						cmbFiletype = new JComboBox();
						panelSetup3.add(cmbFiletype);
						cmbFiletype.setModel(cmbFiletypeModel);
						cmbFiletype.setPreferredSize(new java.awt.Dimension(92, 25));
						cmbFiletype.setBounds(0, 0, 0, 0);
						cmbFiletype.setSize(92, 25);
					}
					{
						btnWrite = new JButton();
						panelSetup3.add(btnWrite);
						btnWrite.setText("\u4fdd\u5b58");
						btnWrite.setPreferredSize(new java.awt.Dimension(191, 30));
						btnWrite.setBounds(0, 0, 0, 0);
						btnWrite.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								btnWriteActionPerformed(evt);
							}
						});
					}
				}
			}
			{
				jTabbedPane1 = new JTabbedPane();
				getContentPane().add(jTabbedPane1, BorderLayout.CENTER);
				jTabbedPane1.setPreferredSize(new java.awt.Dimension(576, 454));
				{
					graphOne = new JPanel();
					BorderLayout graphOneLayout = new BorderLayout();
					jTabbedPane1.addTab("2次元プロット", null, graphOne, null);
					graphOne.setLayout(graphOneLayout);
					graphOne.setPreferredSize(new java.awt.Dimension(571, 452));
					{
						jPanel2 = new JPanel();
						graphOne.add(jPanel2, BorderLayout.CENTER);
						jPanel2.setBounds(528, 50, 10, 10);
						jPanel2.setLayout(null);
						jPanel2.setPreferredSize(new java.awt.Dimension(558, 443));
						{
							panelCanvas = new JPanel();
							jPanel2.add(panelCanvas);
							panelCanvas.setBounds(30, 30, 550, 450);
							panelCanvas.addMouseWheelListener(new MouseWheelListener() {
								public void mouseWheelMoved(MouseWheelEvent evt) {
									panelCanvasMouseWheelMoved(evt);
								}
							});
							panelCanvas.addMouseMotionListener(new MouseMotionAdapter() {
								public void mouseDragged(MouseEvent evt) {
									panelCanvasMouseDragged(evt);
								}
							});
							panelCanvas.addMouseListener(new MouseAdapter() {
								public void mouseReleased(MouseEvent evt) {
									panelCanvasMouseReleased(evt);
								}

								public void mousePressed(MouseEvent evt) {
									panelCanvasMousePressed(evt);
								}
							});
						}
					}
					{
						jPanel1 = new JPanel();
						graphOne.add(jPanel1, BorderLayout.SOUTH);
						{
							jSlider1 = new JSlider();
							jPanel1.add(jSlider1);
							jSlider1.setBounds(29, 447, 501, 22);
							jSlider1.setPreferredSize(new java.awt.Dimension(538, 30));
							jSlider1.setInverted(true);
							jSlider1.setMaximum(0);
							jSlider1.addMouseMotionListener(new MouseMotionAdapter() {
								public void mouseDragged(MouseEvent evt) {
									jSlider1MouseDragged(evt);
								}
							});
							jSlider1.addChangeListener(new ChangeListener() {
								public void stateChanged(ChangeEvent evt) {
									jSlider1StateChanged(evt);
								}
							});
						}
					}
				}
			}
			{
				lblLog = new JLabel();
				getContentPane().add(lblLog, BorderLayout.SOUTH);
				lblLog.setPreferredSize(new java.awt.Dimension(808, 28));
				lblLog.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
			}
			pack();
			this.setSize(808, 596);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	// 保存ボタンを押した時
	private void btnWriteActionPerformed(ActionEvent evt) {

		String fileName;

		if ((fileName = txtFilename.getText()).isEmpty()) {
			JOptionPane.showMessageDialog(gc, "ファイル名を入力してください");
			return;
		}

		fileName = fileName + Data.fileType[cmbFiletype.getSelectedIndex()];
		gc.saveFile(fileName);

	}

	// アニメーションの開始ボタンを押した際の処理
	private void btnStartAnimationActionPerformed(ActionEvent evt) {
		if (!gc.getAnimationFlag()) {
			clickX = 0;
			clickY = 0;
			moveX = 0;
			moveY = 0;
			gc.InitAnimation();
			btnStartAnimation.setEnabled(false);
			btnStopAnimation.setEnabled(true);
			gc.InitAnimation();
			gc.StartAnimation();
		} else {
			lblLog.setText("すでにアニメーションは開始されています");
		}
	}

	// アニメーション停止ボタン
	private void btnStopAnimationActionPerformed(ActionEvent evt) {
		if (gc.getAnimationFlag()) {
			gc.StopAnimation();
			btnStopAnimation.setEnabled(false);
			btnStartAnimation.setEnabled(true);

		} else {
			lblLog.setText("アニメーションは停止しています");
		}
	}



	// スライダーを動かした際の処理
	private void jSlider1StateChanged(ChangeEvent evt) {
		gc.shiftX = jSlider1.getValue();
		if (!gc.getAnimationFlag()) {
			repaint();
		}
	}

	// 横軸スケールの変更
	private void spnAxisTimeStateChanged(ChangeEvent evt) {
		gc.initGraphicsX();
		gc.repaint();
	}

	// 縦軸スケールの変更
	private void spnAxisValueStateChanged(ChangeEvent evt) {
		gc.repaint();
	}

	private void cbInActionPerformed(int j, ActionEvent evt) {
		if (cbIn[j].isSelected()) {
			gc.setGraphSelection(j, true);
			lblLog.setText(OsiloscopeComp.dataName[j] + "のチェックが入りました\n");
		} else {
			gc.setGraphSelection(j, false);

			lblLog.setText(OsiloscopeComp.dataName[j] + "のチェックが外されました\n");
		}
	}

	// ↑ボタンを押した時
	private void btnTopActionPerformed(ActionEvent evt) {
		gc.setShiftY((Double) BasicFrame.spnValueAxis.getValue());
		if (!gc.getAnimationFlag()) {
			repaint();
		}
	}

	// ↓ボタンを押した時
	private void btnNextActionPerformed(ActionEvent evt) {
		gc.setShiftY(-(Double) BasicFrame.spnValueAxis.getValue());
		if (!gc.getAnimationFlag()) {
			repaint();
		}
	}

	// 空白ボタンを押した時
	private void btnReturnActionPerformed(ActionEvent evt) {
		gc.initShiftXY();
		if (!gc.getAnimationFlag()) {
			repaint();
		}
	}

	// →ボタンを押した時
	private void btnBackActionPerformed(ActionEvent evt) {
		gc.setShiftX((Double) spnAxisTime.getValue() * 50 * 2);
		if (!gc.getAnimationFlag()) {
			repaint();
		}
	}

	// ←ボタンを押した時
	private void btnBottomActionPerformed(ActionEvent evt) {
		gc.setShiftX(-(Double) spnAxisTime.getValue() * 100);
		if (!gc.getAnimationFlag()) {
			repaint();
		}
	}

	// マウスを押した時
	private void panelCanvasMousePressed(MouseEvent evt) {
		clickX = evt.getX();
		clickY = evt.getY();
	}

	// マウスを離した時
	private void panelCanvasMouseReleased(MouseEvent evt) {
	}

	// ドラッグ時
	private void panelCanvasMouseDragged(MouseEvent evt) {
		// マウス移動量を計算
		moveX = clickX - evt.getX();
		moveY = clickY - evt.getY();
		gc.setShiftX(-(Double) spnAxisTime.getValue() * 100 * moveX / 50);
		gc.setShiftY((Double) spnValueAxis.getValue() / 50 * moveY);
		clickX = evt.getX();
		clickY = evt.getY();
		if (!gc.getAnimationFlag()) {
			repaint();
		}
	}

	// ホイールを回した時
	private void panelCanvasMouseWheelMoved(MouseWheelEvent evt) {
		// ctrlが押されている場合
		if (evt.getModifiers() == Event.CTRL_MASK) {

			if (0 < evt.getWheelRotation()) {
				for (int i = 0; i < evt.getWheelRotation(); i++) {
					if (spnAxisTime.getPreviousValue() != null) {
						spnAxisTime.setValue(spnAxisTime.getPreviousValue());
					}
				}
			} else {
				for (int i = 0; evt.getWheelRotation() < i; i--) {

					if (spnAxisTime.getNextValue() != null) {
						spnAxisTime.setValue(spnAxisTime.getNextValue());
					}
				}
			}
			return;
		}

		// ctrlが押されていない場合
		if (0 < evt.getWheelRotation()) {
			for (int i = 0; i < evt.getWheelRotation(); i++) {
				if (spnValueAxis.getPreviousValue() != null) {
					spnValueAxis.setValue(spnValueAxis.getPreviousValue());
				}
			}
		} else {
			for (int i = 0; evt.getWheelRotation() < i; i--) {

				if (spnValueAxis.getNextValue() != null) {
					spnValueAxis.setValue(spnValueAxis.getNextValue());
				}
			}
		}

		if (!gc.getAnimationFlag()) {
			repaint();
		}

	}
	
	
	//スライダーをドラッグした場合
	private void jSlider1MouseDragged(MouseEvent evt) {

	}

}
