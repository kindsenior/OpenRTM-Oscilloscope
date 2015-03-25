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

//描画をおこなうクラス
public class GraphicsComponent extends JComponent {

	// 定数
	public static final int ELEMENT_X = 500;
	public static final int ELEMENT_Y = 400;
	public static final int MARGIN_X = 0;
	public static final int MARGIN_Y = 0;
	public static final int AXISX_NUMBER = ELEMENT_Y / 50;
	public static final int AXISY_NUMBER = ELEMENT_X / 50;
	//バッファから描画座標変換のための一時変数
	private double graphicsTmpX;
	private double graphicsTmpY;
	// 描画変数
	private int[] graphicsX = new int[ELEMENT_X + 1]; //100秒分
	private int[] graphicsY = new int[ELEMENT_X + 1]; //100秒分
	public double shiftX;
	private double shiftY;
	// フラグ変数
	private boolean animationFlag = false;
	private boolean[] graphSelection = new boolean[OsiloscopeComp.dataNumber];
	private boolean drawGraphFlag = true;
	
	public static Color c[] = new Color[Data.MAXIMUM_DATAPORTS];
	Font f = new Font("Default" , Font.PLAIN, 12);

																																															BasicStroke wideStroke = new BasicStroke(3.0f);

	//staticイニシャライザ―
	static{
		// 色決め
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
	
	// コンストラクタ
	public GraphicsComponent() {

		// RTC起動時，全てのグラフを描画するように設定
		for (int i = 0; i < OsiloscopeComp.dataNumber; i++) {
			graphSelection[i] = true;
		}

		//x座標の初期化
		initGraphicsX();
		
		// y座標の初期化
		initGraphicsY();
		
		// フォントの設定
		setFont(f);

	}
	
	//shiftX, shiftYの初期化
	public void initShiftXY(){
		this.shiftX = 0;
		this.shiftY = 0;
		BasicFrame.jSlider1.setValue(0);
	}
	
	//shiftXの初期化
	public void initShiftX(){
		this.shiftX = 0;
		BasicFrame.jSlider1.setMaximum(0);
		BasicFrame.jSlider1.setValue(0);
		BasicFrame.jSlider1.setInverted(true);
	}
	
	//x座標の初期化
	public void initGraphicsX(){
		for (int i = 0; i <= ELEMENT_X; i++) {
			graphicsX[i] = ELEMENT_X;
		}
	}
	
	//y座標の初期化
	public void initGraphicsY(){
		for (int i = 0; i <= ELEMENT_Y; i++) {
			graphicsY[i] = ELEMENT_Y / 2;
		}
	}
	
	//セッター
	public void setShiftX(double shiftX){
		this.shiftX += shiftX;
		if(BasicFrame.jSlider1.getMaximum() < this.shiftX){
			this.shiftX = BasicFrame.jSlider1.getMaximum();
		}else if(this.shiftX < 0){
			this.shiftX = 0;
		}
		BasicFrame.jSlider1.setValue((int)this.shiftX);
	}
	
	//セッター
	public void setShiftY(double shiftY){
		this.shiftY += shiftY;
	}

	// ゲッター
	public boolean getAnimationFlag() {
		return animationFlag;
	}

	// セッター
	public void setAnimationFlag(boolean animationFlag) {
		this.animationFlag = animationFlag;
	}

	// ゲッター
	public boolean getGraphSelection(int i) {
		return graphSelection[i];
	}

	// セッター
	public void setGraphSelection(int i, boolean graphSelection) {
		this.graphSelection[i] = graphSelection;
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// 下地の描画
		drawBackground1(g);
		// 軸の描画
		drawAxis(g);
		//グラフの描画
		if(drawGraphFlag){
			for (int i = 0; i < OsiloscopeComp.dataNumber; i++) {
				drawGraph(i, g);
			}
		}
		
		//下地の描画
		drawBackground2(g);
		//グラフ目盛りの描画
		drawGraphScale(g);
	}

	// 下地の描画
	public void drawBackground1(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(MARGIN_X, MARGIN_Y, ELEMENT_X, ELEMENT_Y);
	}

	// 軸の描画
	public void drawAxis(Graphics g) {	
		int axisX = (int)(ELEMENT_X / 2 + 50 * 10 / ((Double)BasicFrame.spnAxisTime.getValue() * 1000) * BasicFrame.jSlider1.getValue());
		//int axisX = (int)(ELEMENT_X / 2 + 50 * 10 / 1 * 1);
		int axisY = (int)(ELEMENT_Y / 2 - shiftY * ELEMENT_Y / 8 / (Double)BasicFrame.spnValueAxis.getValue());
		//int axisY = (int)(ELEMENT_Y / 2 - shiftY * ELEMENT_Y / 8 / 1);
		g.setColor(Color.gray);
		// 縦軸
		for(axisX %= ELEMENT_X / 10;  axisX <= ELEMENT_X; axisX += ELEMENT_X / 10){
			g.drawLine(axisX, 0, axisX, ELEMENT_Y);
		}
		// 横軸
		for(axisY %= ELEMENT_Y / 8; axisY <= ELEMENT_Y; axisY += ELEMENT_Y / 8){
			g.drawLine(0, axisY, ELEMENT_X, axisY);
		}
	}

	// グラフ描画の設定
	public void drawGraph(int i, Graphics g){
		
		double bufPerPixel = (Double)BasicFrame.spnAxisTime.getValue() * 10 * 100 / 500; //1ピクセルあたりのバッファサイズ
		double bufPerDisplay = (Double)BasicFrame.spnAxisTime.getValue() * 10 * 100; //グラフ画面内のバッファサイズ
		
		//グラフの描画範囲が枠内全体に及ばない場合
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
		//グラフの描画領域が枠内全体に及ぶ場合
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
		//描画設定
		g.setColor(c[i]);
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(wideStroke);
		
		//描画を行う
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
	
	//下地の描画
	public void drawBackground2(Graphics g){
		g.setColor(new Color(240, 240, 240));
		g.fillRect(0, ELEMENT_Y, ELEMENT_X + 1, this.getHeight());
	}
	
	//グラフ目盛りの描画
	public void drawGraphScale(Graphics g){
		
		int scaleX = (int)(50 * 10 / ((Double)BasicFrame.spnAxisTime.getValue() * 1000) * BasicFrame.jSlider1.getValue());
		int scaleY = (int)(ELEMENT_Y / 2 - shiftY * ELEMENT_Y / 8 / (Double)BasicFrame.spnValueAxis.getValue());
		int quotientX;
		int quotientY;
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.black);
		
		// 縦軸の目盛り
		for(quotientX = scaleX / (ELEMENT_X / 10), scaleX = scaleX % (ELEMENT_X / 10)+ ELEMENT_X * 9 / 10; 0 <= scaleX; 
		scaleX -= ELEMENT_X / 10, quotientX++){
			g2.rotate( Math.PI / 4.0, scaleX, ELEMENT_Y + 10);
			g2.drawString((Double)BasicFrame.spnAxisTime.getValue() * (quotientX + 1) + "", scaleX, ELEMENT_Y + 10);
			g2.rotate( -Math.PI / 4.0, scaleX, ELEMENT_Y + 10);
		}
		// 横軸の目盛り
		for(quotientY = scaleY / (ELEMENT_Y / 8), scaleY = scaleY % (ELEMENT_Y / 8); scaleY <= ELEMENT_Y;
		scaleY += ELEMENT_Y / 8, quotientY--){
			g2.rotate( -Math.PI / 4.0, ELEMENT_X + 10, scaleY);
			g2.drawString((Double)BasicFrame.spnValueAxis.getValue() * quotientY + "", ELEMENT_X + 10, scaleY);
			g2.rotate(Math.PI / 4.0, ELEMENT_X + 10, scaleY);
		}
		return;

		
	}
	
	// アニメーションのクラス
	class AnimationComponent extends Thread {
		
		int counter = 0;

		@Override
		public void run() {
			//テストコード用変数
			long time = 0;
			//タイマー用変数
			long sleeptime;
			long nanoSeconds;
			long millSeconds;
			
			while (animationFlag) {
				//時間計測開始
				sleeptime = System.nanoTime();

				if(Data.MAXIMUM_FRAMERATE <= counter){
					repaint();
					counter = 0;
				}else{
					counter += (Integer)BasicFrame.spnFrameRate.getValue();					
				}
				
				sleeptime = System.nanoTime() - sleeptime; //sleeptimeはナノ秒
				nanoSeconds = sleeptime % 1000000; //nanoSecondsを求める
				millSeconds = sleeptime / 1000000; //millSecondsを求める
				
				//スリープ処理
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

	// アニメーションの開始
	public void StartAnimation() {
		InitAnimation();
		if (!animationFlag) {
			animationFlag = true;
			new AnimationComponent().start();
		}
	}

	// アニメーションの停止
	public void StopAnimation() {
		animationFlag = false;
	}

	// バッファの初期化・グラフの再描画
	public void InitAnimation() {
		
		//バッファの初期化
		Buffer.initBuf();

		for (int i = 0; i <= ELEMENT_X; i++) {
			graphicsY[i] = ELEMENT_Y / 2;
		}
		
		initShiftX();
	}
	
	//ファイル出力
	public boolean saveFile(String fileName){
		
		try {
			File file = new File(fileName);
			FileWriter filereader = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(filereader);
			
			System.out.println((Double)BasicFrame.spnAxisTime.getValue() * 100 * 10 + "");
			
			//データ名を吐き出す
			bw.write("時間[s]");
			for(int i = 0; i < OsiloscopeComp.dataNumber; i++){
				if(BasicFrame.cbIn[i].isSelected()){
					bw.write("," + OsiloscopeComp.dataName[i]);
				}
			}
			bw.write("\n");
			//数値を吐き出す
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
			BasicFrame.lblLog.setText(fileName + "に保存しました" );
			return true;
		} catch (Exception e) {
			BasicFrame.lblLog.setText(fileName + "に保存できませんでした");
			return false;
		}
	}
}
