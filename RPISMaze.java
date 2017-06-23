package Assignments;

import java.io.IOException;

import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
import org.jointheleague.ecolban.rpirobot.IRobotInterface;
import org.jointheleague.ecolban.rpirobot.SimpleIRobot;

public class RPISMaze extends IRobotAdapter {
	// Sonar sonar = new Sonar();
	Camera cam;

	int red = 80;
	// int i = 0;
	double redPercentage;
	

	public RPISMaze(IRobotInterface iRobot) {
		super(iRobot);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Try event listner, rev Monday 2030");
		IRobotInterface base = new SimpleIRobot();
		RPISMaze rob = new RPISMaze(base);
		rob.setup();
		while (rob.loop()) {
		}
		rob.shutDown();

	}

	private void setup() throws Exception {
		// SETUP CODE GOES HERE!!!!!
		cam = new Camera(150, 50);
		cam.enableBurst();
		// cam.turnOffPreview();
		cam.setTimeout(250);
		Thread idk = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					System.out.println("Picture taken");

					cam.takeRGBPicture();

					redPercentage = cam.getRedPercentage(80, false);
					System.out.println("Red Percentage:" + redPercentage);
					////System.out.println(r + "-red");
				//	System.out.println(g + "-green");
//					if (g > red) {
//						try {
//							driveDirect(500, 500);
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						sleep(300);
//					}
//					if (r > g) {
//						try {
//							driveDirect(-500, 500);
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						sleep(900);
//						try {
//							driveDirect(500, 500);
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						System.out.println("turn around roomba");
//
//					}
				}
			}
		});
		idk.start();
	}

	private boolean loop() throws Exception {
		// LOOP CODE GOES
		driveDirect(500, 160);
		readSensors(100);

		int[] lightBumpReadings = getLightBumps();

		if (lightBumpReadings[0] > 0) {
			driveDirect(-200, 400);
			sleep(150);

		} else if (lightBumpReadings[1] > 0) {
			driveDirect(-300, 400);
			sleep(150);

		} else if (lightBumpReadings[2] > 0) {
			driveDirect(-400, 400);
			sleep(150);

		} else if (lightBumpReadings[3] > 0) {
			driveDirect(-400, 400);
			sleep(150);

		} else if (lightBumpReadings[4] > 0) {
			driveDirect(-300, 400);
			sleep(150);

		} else if (lightBumpReadings[5] > 0) {
			driveDirect(-200, 400);
			sleep(150);

		}

		/*
		 * if (isBumpLeft() || isBumpRight()) { driveDirect(-100, -100);
		 * sleep(500); driveDirect(-500, 500); sleep(3000); driveDirect(0, 0);
		 * 
		 * return false; }
		 */

		return true;
	}

	// private void readSensors(int i) {
	// TODO Auto-generated method stub

	// }

	private void sleep(int amt) {
		try {
			Thread.sleep(amt);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void shutDown() throws IOException {
		reset();
		stop();
		closeConnection();
	}
}