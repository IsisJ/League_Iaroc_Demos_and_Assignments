package Assignments;

import java.io.IOException;

import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
import org.jointheleague.ecolban.rpirobot.IRobotInterface;
import org.jointheleague.ecolban.rpirobot.SimpleIRobot;

public class AssignmentCode extends IRobotAdapter {
	//Sonar sonar = new Sonar();

	public AssignmentCode(IRobotInterface iRobot) {
		super(iRobot);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Try event listner, rev Monday 2030");
		IRobotInterface base = new SimpleIRobot();
		AssignmentCode rob = new AssignmentCode(base);
		rob.setup();
		while (rob.loop()) {
		}
		rob.shutDown();

	}

	private void setup() throws Exception {
		// SETUP CODE GOES HERE!!!!!
//		driveDirect(500,500);
//		sleep(3000);
//		//make the robot spin around the center
//		driveDirect(-100,100);
//		sleep(5500);
////		* Make the robot spin around one wheel.
//		driveDirect(0,500);
//		sleep(1000);
////		* Make the robot move in a figure 8 pattern.driveDirect
//		driveDirect(100,500);
//		sleep(2000);
//		driveDirect(500,100);
//		sleep(5000);
//		driveDirect(100,500);
//		sleep(5000);
//		driveDirect(0,0);
		
	}

	private boolean loop() throws Exception {
		// LOOP CODE GOES HERE!!!!!
		
		readSensors(100);
		
		driveDirect(200, 200);
		
		if(isBumpRight()&&isBumpLeft()){
			driveDirect(-400, -400);
			sleep(3000);
			driveDirect(500,500);
			sleep(1000);
			driveDirect(300,0);
			sleep(1000);
			driveDirect(500,500);
			sleep(1000);
			return false;
		}
		if(isBumpLeft()){
			
		}
		
		return true;
	}

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