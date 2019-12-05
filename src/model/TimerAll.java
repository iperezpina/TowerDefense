package model;

import java.util.Timer;
import java.util.TimerTask;

public class TimerAll {
	private static float timeInMill = 0;
	private static int seconds = 0;
	
	static Timer timer;
	private static boolean check = false;
	
	public static void run() {
		timer = new Timer();
		timer.scheduleAtFixedRate(tt, 0, 1000);
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public float getTime() {
		return timeInMill;
	}
	
	static class Switch extends TimerTask {
		public void run() {
			timeInMill+= .1f;
			seconds++;
			System.out.println(seconds);
			check = true;
			timer.cancel();
			
		}
	}
	
	private static TimerTask tt = new TimerTask() {
		public void run() {
			seconds++;
		System.out.println(seconds);
		}
	};
	
	public static int getTimeInSeconds() {
		return seconds;
	}
	

}
