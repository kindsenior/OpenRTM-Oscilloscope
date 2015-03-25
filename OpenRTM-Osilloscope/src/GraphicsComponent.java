import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JComponent;

//�`��������Ȃ��N���X
public class GraphicsComponent extends JComponent {

	// �萔
	public static final int ELEMENT_X = 500;
	public static final int ELEMENT_Y = 400;
	public static final int MARGIN_X = 0;
	public static final int MARGIN_Y = 0;
	public static final int AXISX_NUMBER = ELEMENT_Y / 50;
	public static final int AXISY_NUMBER = ELEMENT_X / 50;
	//�o�b�t�@����`����W�ϊ��̂��߂̈ꎞ�ϐ�
	private double graphicsTmpX;
	private double graphicsTmpY;
	// �`��ϐ�
	private int[] graphicsX = new int[ELEMENT_X + 1]; //100�b��
	private int[] graphicsY = new int[ELEMENT_X + 1]; //100�b��
	public double shiftX;
	private double shiftY;
	// �t���O�ϐ�
	private boolean animationFlag = false;
	private boolean[] graphSelection = new boolean[OsiloscopeComp.dataNumber];
	private boolean drawGraphFlag = true;
	
	public static Color c[] = new Color[Data.MAXIMUM_DATAPORTS];
	Font f = new Font("Default" , Font.PLAIN, 12);

																																															BasicStroke wideStroke = new BasicStroke(3.0f);

	//static�C�j�V�����C�U�\
	static{
		// �F����
		c[0] = new Color(65, 111, 166);
		c[1] = new Color(168, 66, 63);
		c[2] = new Color(134, 164, 74);
		c[3] = new Color(110, 84, 141);
		c[4] = new Color(61, 150, 174);
		c[5] = new Color(218, 129, 55);
		c[6] = new Color(142, 165, 203);
		c[7] = new Color(206, 142, 141);
		c[8] = new Color(181, 202, 146);
		c[9] = new Color(165, 151, 185);
	}
	
	// �R���X�g���N�^
	public GraphicsComponent() {

		// RTC�N�����C�S�ẴO���t��`�悷��悤�ɐݒ�
		for (int i = 0; i < OsiloscopeComp.dataNumber; i++) {
			graphSelection[i] = true;
		}

		//x���W�̏�����
		initGraphicsX();
		
		// y���W�̏�����
		initGraphicsY();
		
		// �t�H���g�̐ݒ�
		setFont(f);

	}
	
	//shiftX, shiftY�̏�����
	public void initShiftXY(){
		this.shiftX = 0;
		this.shiftY = 0;
		BasicFrame.jSlider1.setValue(0);
	}
	
	//shiftX�̏�����
	public void initShiftX(){
		this.shiftX = 0;
		BasicFrame.jSlider1.setMaximum(0);
		BasicFrame.jSlider1.setValue(0);
		BasicFrame.jSlider1.setInverted(true);
	}
	
	//x���W�̏�����
	public void initGraphicsX(){
		for (int i = 0; i <= ELEMENT_X; i++) {
			graphicsX[i] = ELEMENT_X;
		}
	}
	
	//y���W�̏�����
	public void initGraphicsY(){
		for (int i = 0; i <= ELEMENT_Y; i++) {
			graphicsY[i] = ELEMENT_Y / 2;
		}
	}
	
	//�Z�b�^�[
	public void setShiftX(double shiftX){
		this.shiftX += shiftX;
		if(BasicFrame.jSlider1.getMaximum() < this.shiftX){
			this.shiftX = BasicFrame.jSlider1.getMaximum();
		}else if(this.shiftX < 0){
			this.shiftX = 0;
		}
		BasicFrame.jSlider1.setValue((int)this.shiftX);
	}
	
	//�Z�b�^�[
	public void setShiftY(double shiftY){
		this.shiftY += shiftY;
	}

	// �Q�b�^�[
	public boolean getAnimationFlag() {
		return animationFlag;
	}

	// �Z�b�^�[
	public void setAnimationFlag(boolean animationFlag) {
		this.animationFlag = animationFlag;
	}

	// �Q�b�^�[
	public boolean getGraphSelection(int i) {
		return graphSelection[i];
	}

	// �Z�b�^�[
	public void setGraphSelection(int i, boolean graphSelection) {
		this.graphSelection[i] = graphSelection;
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// ���n�̕`��
		drawBackground1(g);
		// ���̕`��
		drawAxis(g);
		//�O���t�̕`��
		if(drawGraphFlag){
			for (int i = 0; i < OsiloscopeComp.dataNumber; i++) {
				drawGraph(i, g);
			}
		}
		
		//���n�̕`��
		drawBackground2(g);
		//�O���t�ڐ���̕`��
		drawGraphScale(g);
	}

	// ���n�̕`��
	public void drawBackground1(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(MARGIN_X, MARGIN_Y, ELEMENT_X, ELEMENT_Y);
	}

	// ���̕`��
	public void drawAxis(Graphics g) {	
		int axisX = (int)(ELEMENT_X / 2 + 50 * 10 / ((Double)BasicFrame.spnAxisTime.getValue() * 1000) * BasicFrame.jSlider1.getValue());
		//int axisX = (int)(ELEMENT_X / 2 + 50 * 10 / 1 * 1);
		int axisY = (int)(ELEMENT_Y / 2 - shiftY * ELEMENT_Y / 8 / (Double)BasicFrame.spnValueAxis.getValue());
		//int axisY = (int)(ELEMENT_Y / 2 - shiftY * ELEMENT_Y / 8 / 1);
		g.setColor(Color.gray);
		// �c��
		for(axisX %= ELEMENT_X / 10;  axisX <= ELEMENT_X; axisX += ELEMENT_X / 10){
			g.drawLine(axisX, 0, axisX, ELEMENT_Y);
		}
		// ����
		for(axisY %= ELEMENT_Y / 8; axisY <= ELEMENT_Y; axisY += ELEMENT_Y / 8){
			g.drawLine(0, axisY, ELEMENT_X, axisY);
		}
	}

	// �O���t�`��̐ݒ�
	public void drawGraph(int i, Graphics g){
		
		double bufPerPixel = (Double)BasicFrame.spnAxisTime.getValue() * 10 * 100 / 500; //1�s�N�Z��������̃o�b�t�@�T�C�Y
		double bufPerDisplay = (Double)BasicFrame.spnAxisTime.getValue() * 10 * 100; //�O���t��ʓ��̃o�b�t�@�T�C�Y
		
		//�O���t�̕`��͈͂��g���S�̂ɋy�΂Ȃ��ꍇ
		if(Buffer.getSize() - BasicFrame.jSlider1.getValue() < bufPerDisplay){
			switch(OsiloscopeComp.dataType[i]){
			case 0:
				for(int j = 0; j < ( Buffer.getSize() - BasicFrame.jSlider1.getValue()) / bufPerPixel; j++){
					graphicsX[j] = ELEMENT_X - ((int)((Buffer.getSize() - BasicFrame.jSlider1.getValue()) / bufPerPixel)  - j);
					graphicsTmpY = Buffer.getLongBuf(i, (int)(j * bufPerPixel));
					graphicsTmpY += (double)shiftY;
					graphicsTmpY *= ELEMENT_Y / 8  / (Double)BasicFrame.spnValueAxis.getValue();
					graphicsTmpY = -graphicsTmpY;
					graphicsY[j] = (int)graphicsTmpY + ELEMENT_Y / 2;
				}
				break;
			case 1:
				for(int j = 0; j < ( Buffer.getSize() - BasicFrame.jSlider1.getValue()) / bufPerPixel; j++){
					graphicsX[j] = ELEMENT_X - ((int)((Buffer.getSize() - BasicFrame.jSlider1.getValue()) / bufPerPixel)  - j);
					graphicsTmpY = Buffer.getShortBuf(i, (int)(j * bufPerPixel));
					graphicsTmpY += (double)shiftY;
					graphicsTmpY *= ELEMENT_Y / 8  / (Double)BasicFrame.spnValueAxis.getValue();
					graphicsTmpY = -graphicsTmpY;
					graphicsY[j] = (int)graphicsTmpY + ELEMENT_Y / 2;
				}
				break;
			case 2:
				for(int j = 0; j < ( Buffer.getSize() - BasicFrame.jSlider1.getValue()) / bufPerPixel; j++){
					graphicsX[j] = ELEMENT_X - ((int)((Buffer.getSize() - BasicFrame.jSlider1.getValue()) / bufPerPixel)  - j);
					graphicsTmpY = Buffer.getDoubleBuf(i, (int)(j * bufPerPixel));
					graphicsTmpY += (double)shiftY;
					graphicsTmpY *= ELEMENT_Y / 8  / (Double)BasicFrame.spnValueAxis.getValue();
					graphicsTmpY = -graphicsTmpY;
					graphicsY[j] = (int)graphicsTmpY + ELEMENT_Y / 2;
				}
				break;
			case 3:
				for(int j = 0; j < ( Buffer.getSize() - BasicFrame.jSlider1.getValue()) / bufPerPixel; j++){
					graphicsX[j] = ELEMENT_X - ((int)((Buffer.getSize() - BasicFrame.jSlider1.getValue()) / bufPerPixel)  - j);
					graphicsTmpY = Buffer.getFloatBuf(i, (int)(j * bufPerPixel));
					graphicsTmpY += (double)shiftY;
					graphicsTmpY *= ELEMENT_Y / 8  / (Double)BasicFrame.spnValueAxis.getValue();
					graphicsTmpY = -graphicsTmpY;
					graphicsY[j] = (int)graphicsTmpY + ELEMENT_Y / 2;
				}
				break;
			default:
			}
		}
		//�O���t�̕`��̈悪�g���S�̂ɋy�ԏꍇ
		else{
			switch(OsiloscopeComp.dataType[i]){
			case 0:
				for(int j = 0; j < ELEMENT_X; j++){
					graphicsX[j] = j;
					graphicsTmpY = Buffer.getLongBuf(i, (int)( Buffer.getSize() - bufPerDisplay - BasicFrame.jSlider1.getValue()  + bufPerPixel * j) );
					graphicsTmpY += (double)shiftY;
					graphicsTmpY *= ELEMENT_Y / 8  / (Double)BasicFrame.spnValueAxis.getValue();
					graphicsTmpY = -graphicsTmpY;
					graphicsY[j] = (int)graphicsTmpY + ELEMENT_Y / 2;
				}
				break;
			case 1:
				for(int j = 0; j < ELEMENT_X; j++){
					graphicsX[j] = j;
					graphicsTmpY = Buffer.getShortBuf(i, (int)( Buffer.getSize() - bufPerDisplay - BasicFrame.jSlider1.getValue() + bufPerPixel * j) );
					graphicsTmpY += (double)shiftY;
					graphicsTmpY *= ELEMENT_Y / 8  / (Double)BasicFrame.spnValueAxis.getValue();
					graphicsTmpY = -graphicsTmpY;
					graphicsY[j] = (int)graphicsTmpY + ELEMENT_Y / 2;
				}
				break;
			case 2:
				for(int j = 0; j < ELEMENT_X; j++){
					graphicsX[j] = j;
					graphicsTmpY = Buffer.getDoubleBuf(i, (int)( Buffer.getSize() - bufPerDisplay - BasicFrame.jSlider1.getValue() + bufPerPixel * j) );
					graphicsTmpY += (double)shiftY;
					graphicsTmpY *= ELEMENT_Y / 8  / (Double)BasicFrame.spnValueAxis.getValue();
					graphicsTmpY = -graphicsTmpY;
					graphicsY[j] = (int)graphicsTmpY + ELEMENT_Y / 2;
				}
				break;
			case 3:
				for(int j = 0; j < ELEMENT_X; j++){
					graphicsX[j] = j;
					graphicsTmpY = Buffer.getFloatBuf(i, (int)( Buffer.getSize() - bufPerDisplay - BasicFrame.jSlider1.getValue() + bufPerPixel * j) );
					graphicsTmpY += (double)shiftY;
					graphicsTmpY *= ELEMENT_Y / 8  / (Double)BasicFrame.spnValueAxis.getValue();
					graphicsTmpY = -graphicsTmpY;
					graphicsY[j] = (int)graphicsTmpY + ELEMENT_Y / 2;
				}
				break;
			default:
			}
		}
		//�`��ݒ�
		g.setColor(c[i]);
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(wideStroke);
		
		//�`����s��
		if(graphSelection[i]){
			if(Buffer.getSize() - BasicFrame.jSlider1.getValue() < bufPerDisplay){
				for(int j = 0; j < (int)((Buffer.getSize() - BasicFrame.jSlider1.getValue()) / bufPerPixel) - 1; j++){
					g.drawLine(graphicsX[j], graphicsY[j], graphicsX[j + 1], graphicsY[j + 1]);
				}
			}else{
				for(int j = 0; j < ELEMENT_X - 1; j++){
					g.drawLine(graphicsX[j], graphicsY[j], graphicsX[j + 1], graphicsY[j + 1]);
				}
			}
			
		}

	}
	
	//���n�̕`��
	public void drawBackground2(Graphics g){
		g.setColor(new Color(240, 240, 240));
		g.fillRect(0, ELEMENT_Y, ELEMENT_X + 1, this.getHeight());
	}
	
	//�O���t�ڐ���̕`��
	public void drawGraphScale(Graphics g){
		
		int scaleX = (int)(50 * 10 / ((Double)BasicFrame.spnAxisTime.getValue() * 1000) * BasicFrame.jSlider1.getValue());
		int scaleY = (int)(ELEMENT_Y / 2 - shiftY * ELEMENT_Y / 8 / (Double)BasicFrame.spnValueAxis.getValue());
		int quotientX;
		int quotientY;
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.black);
		
		// �c���̖ڐ���
		for(quotientX = scaleX / (ELEMENT_X / 10), scaleX = scaleX % (ELEMENT_X / 10)+ ELEMENT_X * 9 / 10; 0 <= scaleX; 
		scaleX -= ELEMENT_X / 10, quotientX++){
			g2.rotate( Math.PI / 4.0, scaleX, ELEMENT_Y + 10);
			g2.drawString((Double)BasicFrame.spnAxisTime.getValue() * (quotientX + 1) + "", scaleX, ELEMENT_Y + 10);
			g2.rotate( -Math.PI / 4.0, scaleX, ELEMENT_Y + 10);
		}
		// �����̖ڐ���
		for(quotientY = scaleY / (ELEMENT_Y / 8), scaleY = scaleY % (ELEMENT_Y / 8); scaleY <= ELEMENT_Y;
		scaleY += ELEMENT_Y / 8, quotientY--){
			g2.rotate( -Math.PI / 4.0, ELEMENT_X + 10, scaleY);
			g2.drawString((Double)BasicFrame.spnValueAxis.getValue() * quotientY + "", ELEMENT_X + 10, scaleY);
			g2.rotate(Math.PI / 4.0, ELEMENT_X + 10, scaleY);
		}
		return;

		
	}
	
	// �A�j���[�V�����̃N���X
	class AnimationComponent extends Thread {
		
		int counter = 0;

		@Override
		public void run() {
			//�e�X�g�R�[�h�p�ϐ�
			long time = 0;
			//�^�C�}�[�p�ϐ�
			long sleeptime;
			long nanoSeconds;
			long millSeconds;
			
			while (animationFlag) {
				//���Ԍv���J�n
				sleeptime = System.nanoTime();

				if(Data.MAXIMUM_FRAMERATE <= counter){
					repaint();
					counter = 0;
				}else{
					counter += (Integer)BasicFrame.spnFrameRate.getValue();					
				}
				
				sleeptime = System.nanoTime() - sleeptime; //sleeptime�̓i�m�b
				nanoSeconds = sleeptime % 1000000; //nanoSeconds�����߂�
				millSeconds = sleeptime / 1000000; //millSeconds�����߂�
				
				//�X���[�v����
				try {
					if(millSeconds < 10){
						if(nanoSeconds == 0){
							Thread.sleep(millSeconds);
						}else{
							Thread.sleep((long)(9 - millSeconds), (int)(1000000 - nanoSeconds));
						}
					}
				} catch (Exception e) {

				}
			}

		}

	}

	// �A�j���[�V�����̊J�n
	public void StartAnimation() {
		InitAnimation();
		if (!animationFlag) {
			animationFlag = true;
			new AnimationComponent().start();
		}
	}

	// �A�j���[�V�����̒�~
	public void StopAnimation() {
		animationFlag = false;
	}

	// �o�b�t�@�̏������E�O���t�̍ĕ`��
	public void InitAnimation() {
		
		//�o�b�t�@�̏�����
		Buffer.initBuf();

		for (int i = 0; i <= ELEMENT_X; i++) {
			graphicsY[i] = ELEMENT_Y / 2;
		}
		
		initShiftX();
	}
	
	//�t�@�C���o��
	public boolean saveFile(String fileName){
		
		try {
			File file = new File(fileName);
			FileWriter filereader = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(filereader);
			
			System.out.println((Double)BasicFrame.spnAxisTime.getValue() * 100 * 10 + "");
			
			//�f�[�^����f���o��
			bw.write("����[s]");
			for(int i = 0; i < OsiloscopeComp.dataNumber; i++){
				if(BasicFrame.cbIn[i].isSelected()){
					bw.write("," + OsiloscopeComp.dataName[i]);
				}
			}
			bw.write("\n");
			//���l��f���o��
			double time = 0.0;
			for(int j = 0; j < (Buffer.getSize() - BasicFrame.jSlider1.getValue()) && j < (Double)BasicFrame.spnAxisTime.getValue() * 100 * 10; j++){
				System.out.println(j + "");
				bw.write(time + "");
				for(int i = 0; i < OsiloscopeComp.dataNumber; i++){
					switch (OsiloscopeComp.dataType[i]) {
					case 0:
						if(BasicFrame.cbIn[i].isSelected()){
							bw.write("," + Buffer.getLongBuf(i, Buffer.getSize() - 1 - j - BasicFrame.jSlider1.getValue()));
						}
						break;
					case 1:
						if(BasicFrame.cbIn[i].isSelected()){
							bw.write("," + Buffer.getShortBuf(i, Buffer.getSize() - 1 - j - BasicFrame.jSlider1.getValue()));
						}
						break;
					case 2:
						if(BasicFrame.cbIn[i].isSelected()){
							bw.write("," + Buffer.getDoubleBuf(i, Buffer.getSize() - 1 - j - BasicFrame.jSlider1.getValue()));							
						}
						break;
					case 3:
						if(BasicFrame.cbIn[i].isSelected()){
							bw.write("," + Buffer.getFloatBuf(i, Buffer.getSize() - 1 - j - BasicFrame.jSlider1.getValue()));
						}						
						break;
					default:
					}
					
					
				}
				bw.write("\n");
				time += 0.01;
			}
	
			bw.close();
			BasicFrame.lblLog.setText(fileName + "�ɕۑ����܂���" );
			return true;
		} catch (Exception e) {
			BasicFrame.lblLog.setText(fileName + "�ɕۑ��ł��܂���ł���");
			return false;
		}
	}
}
