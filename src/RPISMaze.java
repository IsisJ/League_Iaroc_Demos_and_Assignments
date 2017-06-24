
import java.io.IOException;

import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
import org.jointheleague.ecolban.rpirobot.IRobotInterface;
import org.jointheleague.ecolban.rpirobot.SimpleIRobot;

import Assignments.Camera;

public class RPISMaze extends IRobotAdapter {
	// Sonar sonar = new Sonar();
	Camera cam;
	double redPercentage;
	double greenPercentage;

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
		driveDirect(500, 500);
		sleep(500);
		cam = new Camera(150, 50);
		cam.enableBurst();
		cam.turnOffPreview();
		cam.setTimeout(2);
		Thread idk = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					System.out.println("Picture taken");
					cam.takeRGBPicture();
					redPercentage = cam.getRedPercentage(80, false);
					greenPercentage = cam.getGreenPercentage(20, false);
					System.out.println("Red Percentage:" + redPercentage);
					System.out.println("Green Percentage:" + greenPercentage);
				}
			}

		});

		idk.start();
	}

	private boolean loop() throws Exception {
		// LOOP CODE GOES
		readSensors(100);
		if (greenPercentage > redPercentage) {
			driveDirect(500, 500);
			sleep(1000);
		}
		if (redPercentage > greenPercentage) {
			System.out.println("turn around roomba");
			driveDirect(-500, 500);
			sleep(300);
		}
		
		int[] lightBumpReadings = getLightBumps();

		if (lightBumpReadings[0] > 0) {
			driveDirect(-200, 400);
			sleep(150);

		} else if (lightBumpReadings[1] > 0) {
			driveDirect(-200, 400);
			sleep(150);

		} else if (lightBumpReadings[2] > 0) {
			driveDirect(100, 400);
			sleep(150);

		} else if (lightBumpReadings[3] > 0) {
			driveDirect(100, 400);
			sleep(150);

		} else if (lightBumpReadings[4] > 0) {
			driveDirect(-200, 400);
			sleep(150);

		} else if (lightBumpReadings[5] > 0) {
			driveDirect(-200, 400);
			sleep(150);

		}

		if (isBumpLeft()) {
			driveDirect(250, -250);
			sleep(300);
		}
		if (isBumpRight()) {
			driveDirect(-250, 250);
			sleep(300);
		}

		return true;
	}


	private void sleep(int amt) {
		try {
			Thread.sleep(amt);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void shutDown() throws IOException {
		reset();
		stop();
		closeConnection();
	}
}