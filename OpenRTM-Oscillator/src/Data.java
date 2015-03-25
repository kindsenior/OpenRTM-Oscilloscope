import java.util.ArrayList;

import jp.go.aist.rtm.RTC.port.InPort;
import RTC.TimedLong;

public class Data {
	public static final int MAXIMUM_DATAPORTS = 10;
	public static final int MAXIMUM_FRAMERATE = 100;
	public static final String[] fileType = { ".csv"};
	public static final String[] dataType = { "RTC::TimedLong", "RTC::TimedShort", "RTC::TimedDouble", "RTC::TimedFloat" };
	public static final Double[] valueAxisData = new Double[] { 0.000001, 0.000002, 0.000005, 
		0.00001, 0.00002, 0.00005, 0.0001, 0.0002, 0.0005, 0.001, 0.002, 0.005, 0.01, 0.02, 0.05, 0.1, 0.2, 0.5, 1.0, 2.0, 5.0,
	10.0, 20.0, 50.0, 100.0, 200.0, 500.0, 1000.0, 2000.0, 5000.0, 10000.0, 20000.0, 50000.0, 100000.0, 200000.0, 500000.0, 1000000.0, 2000000.0, 5000000.0, 
	10000000.0, 20000000.0, 50000000.0,};
	public static final Double[] timeAxisData = new Double[] { 0.1, 0.2, 0.5, 1.0, 2.0, 5.0, 10.0};
	public static final Integer[] frameRate = new Integer[] {1, 5, 10, 20, 30, 60, 100};
}
