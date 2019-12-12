package model;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer class that keeps a internal clcok inside the game
 * 
 * @author Ivan, Marisa, Laura, Albert
 * 
 */
public class TimerAll {
	private static float timeInMill = 0;
	private static int seconds = 0;
	private static float deltaTime = .1f;

	static Timer timer;
	private static boolean check = false;
	private static boolean updating = false;

	/**
	 * run Timer class that starts the Timer
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public static void runTimer() {
		resetDeltaTime();
		timer = new Timer();
		TimerTask tt = new TimerTask() {
			public void run() {
				timeInMill += deltaTime;
				seconds = (int) timeInMill;
			}
		};

		if (!updating) {
			updating = true;
			timer.scheduleAtFixedRate(tt, 0, 100);
		}

	}

	// Getter
	public boolean getCheck() {
		return check;
	}

	// Getter
	public float getTime() {
		return timeInMill;
	}

	// Getter
	public static int getTimeInSeconds() {
		return seconds;
	}

	// Getter
	public static float getTimeInMilliseconds() {
		return timeInMill;
	}

	/**
	 * Pauses the timer
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public static void pause() {
		deltaTime = 0f;
	}

	/**
	 * resets the time
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public static void play() {
		resetDeltaTime();
	}

	/**
	 * makes the timer run faster
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public static void timesTwo() {
		deltaTime = .2f;
	}

	/**
	 * resets the time
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	private static void resetDeltaTime() {
		deltaTime = .1f;
		timeInMill = 0;
		seconds = 0;
	}

	/**
	 * stops the timer
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public static void cancel() {
		timer.cancel();
	}
}
