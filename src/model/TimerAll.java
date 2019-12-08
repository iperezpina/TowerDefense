package model;

import java.util.Timer;
import java.util.TimerTask;

public class TimerAll {
	private static float timeInMill = 0;
	private static int seconds = 0;
	private static float deltaTime = .1f;
	
	static Timer timer;
	private static boolean check = false;
	
	public static void run() {
		timer = new Timer();
		timer.scheduleAtFixedRate(tt, 0, 100);
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public float getTime() {
		return timeInMill;
	}
	
	private static TimerTask tt = new TimerTask() {
		public void run() {
			timeInMill += deltaTime;
			seconds = (int) timeInMill;
    }
	};
	
	public static int getTimeInSeconds() {
		return seconds;
	}
	
	public static float getTimeInMilliseconds() {
		return timeInMill;
	}
	
	public static void pause() {
		deltaTime = 0f;
	}
	
	public static void play() {
		resetDeltaTime();
	}
	
	public static void timesTwo() {
		deltaTime = .2f;
	}
	
	private static void resetDeltaTime() {
		deltaTime = .1f;
	}
}
