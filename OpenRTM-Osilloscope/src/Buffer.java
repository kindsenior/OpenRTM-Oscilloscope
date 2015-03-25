import java.util.ArrayList;


public class Buffer {
	// �o�b�t�@
	private static ArrayList<Long> longBuf[] = new ArrayList[OsiloscopeComp.dataNumber];
	private static ArrayList<Short> shortBuf[] = new ArrayList[OsiloscopeComp.dataNumber];
	private static ArrayList<Double> doubleBuf[] = new ArrayList[OsiloscopeComp.dataNumber];
	private static ArrayList<Float> floatBuf[] = new ArrayList[OsiloscopeComp.dataNumber];
	// �ꎞ�ϐ�(�f�[�^�|�[�g�̒l���o�b�t�@�ɕۑ�����ۂ̈ꎞ�ϐ�
	private static long[] longTmp = new long[OsiloscopeComp.dataNumber];
	private static short[] shortTmp = new short[OsiloscopeComp.dataNumber];
	private static double[] doubleTmp = new double[OsiloscopeComp.dataNumber];
	private static float[] floatTmp = new float[OsiloscopeComp.dataNumber];
	//�萔
	public static final int MAX_BUF = 10000;
	
	static{
		// �o�b�t�@�ϐ��̏�����
		for (int i = 0; i < OsiloscopeComp.dataNumber; i++) {
			longBuf[i] = new ArrayList<Long>();
			shortBuf[i] = new ArrayList<Short>();
			doubleBuf[i] = new ArrayList<Double>();
			floatBuf[i] = new ArrayList<Float>();
		}
		// �ꎞ�ϐ��̏�����
		for (int i = 0; i < OsiloscopeComp.dataNumber; i++) {
			longTmp[i] = 0;
			shortTmp[i] = 0;
			doubleTmp[i] = 0;
			floatTmp[i] = 0;
		}
	}
	
	// �Q�b�^�[
	public static int getSize() {
		switch (OsiloscopeComp.dataType[0]) {
		case 0:
			return longBuf[0].size();
		case 1:
			return shortBuf[0].size();
		case 2:
			return doubleBuf[0].size();
		case 3:
			return floatBuf[0].size();
		default:
			return -1;
		}
	}
	
	// �Z�b�^�[
	public static void setLongBuf(int i, long longTmpY) {
		if (MAX_BUF < longBuf[i].size()) {
			longBuf[i].remove(0);
		}
		longBuf[i].add(longTmpY);
	}

	// �Z�b�^�[
	public static void setShortBuf(int i, short shortTmpY) {
		if (MAX_BUF < shortBuf[i].size()) {
			shortBuf[i].remove(0);
		}
		shortBuf[i].add(shortTmpY);
	}

	// �Z�b�^�[
	public static void setDoubleBuf(int i, double doubleTmpY) {
		if (MAX_BUF < doubleBuf[i].size()) {
			doubleBuf[i].remove(0);
		}
		doubleBuf[i].add(doubleTmpY);
	}

	// �Z�b�^�[
	public static void setFloatBuf(int i, float floatTmpY) {
		if (MAX_BUF < floatBuf[i].size()) {
			floatBuf[i].remove(0);
		}
		floatBuf[i].add(floatTmpY);
	}
	
	//�Q�b�^�[
	public static long getLongBuf(int dataNumber, int bufNumber){
		return longBuf[dataNumber].get(bufNumber);
	}
	
	//�Q�b�^�[
	public static short getShortBuf(int dataNumber, int bufNumber){
		return shortBuf[dataNumber].get(bufNumber);
	}
	
	//�Q�b�^�[
	public static double getDoubleBuf(int dataNumber, int bufNumber){
		return doubleBuf[dataNumber].get(bufNumber);
	}
	
	//�Q�b�^�[
	public static float getFloatBuf(int dataNumber, int bufNumber){
		return floatBuf[dataNumber].get(bufNumber);
	}
	
	// �Z�b�^�[
	public static void setLongTmp(int i, long _longTmpY) {
		longTmp[i] = _longTmpY;
	}

	// �Z�b�^�[
	public static void setShortTmp(int i, short _shortTmpY) {
		shortTmp[i] = _shortTmpY;
	}

	// �Z�b�^�[
	public static void setDoubleTmp(int i, double _doubleTmpY) {
		doubleTmp[i] = _doubleTmpY;
	}

	// �Z�b�^�[
	public static void setFloatTmp(int i, float _floatTmpY) {
		floatTmp[i] = _floatTmpY;
	}
	
	//�o�b�t�@�̍X�V
	public static void updateBuf(){
		for (int i = 0; i < OsiloscopeComp.dataNumber; i++) {
			switch (OsiloscopeComp.dataType[i]) {
			case 0:
				Buffer.setLongBuf(i, longTmp[i]);
				break;
			case 1:
				Buffer.setShortBuf(i, shortTmp[i]);
				break;
			case 2:
				Buffer.setDoubleBuf(i, doubleTmp[i]);
				break;
			case 3:
				Buffer.setFloatBuf(i, floatTmp[i]);
				break;
			default:
			}
		}
	}
	
	//�o�b�t�@�̏�����
	public static void initBuf(){
		for (int i = 0; i < OsiloscopeComp.dataNumber; i++) {
			longTmp[i] = 0;
			shortTmp[i] = 0;
			doubleTmp[i] = 0;
			floatTmp[i] = 0;
		}
		
		
		initTmp();
		
	}
	
	//�ꎞ�ϐ��̏�����
	public static void initTmp(){
		for (int i = 0; i < OsiloscopeComp.dataNumber; i++) {
			switch (OsiloscopeComp.dataType[i]) {
			case 0:
				longBuf[i].clear();
				break;
			case 1:
				shortBuf[i].clear();
				break;
			case 2:
				doubleBuf[i].clear();
				break;
			case 3:
				floatBuf[i].clear();
				break;
			default:
			}
		}
	}
}
