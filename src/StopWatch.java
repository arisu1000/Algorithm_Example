import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class StopWatch {

	private static long StartTime;
	private static List<Long> RecordTime = new ArrayList();
	private static long EndTime;
	
	public static long getStartTime(){
		return StartTime;
	}
	
	public static long getEndTime(){
		return EndTime;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static long start() {
		
		StartTime = System.currentTimeMillis();
		return StartTime;
	}
	
	public static Date startTimeToDate(){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(StartTime);
		return cal.getTime();
	}

	public static long recordTime() {
		RecordTime.add(new Long(System.currentTimeMillis()));
		return RecordTime.get(RecordTime.size() - 1);
	}

	public static long stop() {
		EndTime = System.currentTimeMillis();
		return EndTime;
	}
	public static Date stopTimeToDate(){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(EndTime);
		return cal.getTime();
	}

	
	public static long TotalTime(){
		long TotalTime = EndTime - StartTime;
		return TotalTime;
	}

	public static double TotalTimeInSec(){
		double TotalTime = (double)EndTime - (double)StartTime;
		return TotalTime/1000;
	}
}
