package Assignments;

import java.io.IOException;

import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
import org.jointheleague.ecolban.rpirobot.IRobotInterface;
import org.jointheleague.ecolban.rpirobot.SimpleIRobot;

public class RPISGoldRush extends IRobotAdapter {
	// Sonar sonar = new Sonar();
	boolean lightSensors = true;

	public RPISGoldRush(IRobotInterface iRobot) {
		super(iRobot);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Try event listner, rev Monday 2030");
		IRobotInterface base = new SimpleIRobot();
		RPISGoldRush rob = new RPISGoldRush(base);
		rob.setup();
		while (rob.loop()) {
		}
		rob.shutDown();

	}

	private void setup() throws Exception {
		// SETUP CODE GOES HERE!!!!!

	}

	private boolean loop() throws Exception {
		readSensors(100);
		if (lightSensors) {
			
			driveDirect(500, 500);

			int[] lightBumpReadings = getLightBumps();

			if (lightBumpReadings[0] > 0) {
				driveDirect(400, -300);
				sleep(150);

			} else if (lightBumpReadings[1] > 0) {
				driveDirect(400, -200);
				sleep(150);

			} else if (lightBumpReadings[2] > 0) {
				driveDirect(400, -300);
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
		}
		if (getInfraredByte() > 241) {
			System.out.println("infrared sensor read");
			lightSensors = false;
			driveDirect(500, 500);
			if (isBumpLeft()) {
				driveDirect(-400, -400);
				sleep(500);
				driveDirect(500, -200);
				sleep(200);
			}
			if (isBumpRight()) {
				driveDirect(-400, -400);
				sleep(500);
				driveDirect(-200, 500);
				sleep(200);
			}
		}
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
