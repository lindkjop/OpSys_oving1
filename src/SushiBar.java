import java.io.*;

import java.util.Timer;

public class SushiBar {
	// SushiBar settings:
	// These parameters have to be changed to check that the program works in
	// all situations.
	private static int capacity = 2; // capacity of Sushi Bar
	private static int duration = 20; // Simulation time
	public static int maxOrder = 10; // Maximum number of orders for each
										// customer
	public static int customerWait = 3000; // coefficient of eating time for
											// customers
	public static int doorWait = 500 ; // coefficient of waiting time for door
										// for creating next customer
	public static Door door;
	public static Clock clock;
	public static Timer timer;
	public static ServingArea servingArea;
	public static Timer customerLeave;

	public static boolean isOpen = true;

	// Creating the log file
	private static File log;
	private static String path = "./";

	public static void main(String[] args) throws InterruptedException {
		log = new File(path + "log.txt");
		clock = new Clock(duration);
		timer = new Timer();
		customerLeave = new Timer();
		servingArea = new ServingArea(capacity);
		door = new Door();
		door.start();
		
		
		//System.exit(0);
	}

	// Writes actions in the log file and console
	public static void write(String str) {
		try {
			FileWriter fw = new FileWriter(log.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(Clock.getTime() + ", " + str + "\n");
			bw.close();
			System.out.println(Clock.getTime() + ", " + str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
