package AutonCommands;

import org.usfirst.frc.team5036.robot.RobotOutput;
import org.usfirst.frc.team5036.robot.Sensors;
//random commment for no reason to test something with Github and eclipse
import Loops.ArmLooper;
import Loops.DriveStraightPID;
import Loops.GyroPID;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class DriveStraight extends RobotOutput
{
	public static void execute(double holdingAngle,double distance,boolean runIntake,double maxSpeed,double intakeSpeed)
	{
		DriveStraightPID drivePID = new DriveStraightPID(distance,maxSpeed);
		GyroPID gyroPID = new GyroPID(0);
		Sensors.getInstance().resetEncoders();
		drivetrain.shiftLow();
		ArmLooper.getInstance().setTarget(holdingAngle);
		int countingGoodPos = 0;
		while(countingGoodPos < 10 && DriverStation.getInstance().isAutonomous())
		{
			ArmLooper.getInstance().continueLoop();
			double output = drivePID.getOutput();
			drivetrain.arcadeDrive(output,gyroPID.getOutput());
			SmartDashboard.putNumber("PID Output:",output);
			SmartDashboard.putNumber("Encoder: ",Sensors.getInstance().getEncoderAverageInInches());
			if(Math.abs(drivePID.currentError) < 2.5)
				countingGoodPos++;
			if(runIntake)
				intake.runIntake(intakeSpeed);
			Timer.delay(0.02);
		}
		SmartDashboard.putNumber("Final Encoder: ",Sensors.getInstance().getEncoderAverageInInches());
		drivetrain.setLeftAndRightPWM(0,0);
	}
	
	public static void executeTilStop(double holdingAngle,double distance,boolean runIntake,double maxSpeed,double intakeSpeed)
	{
		DriveStraightPID drivePID = new DriveStraightPID(distance,maxSpeed);
		GyroPID gyroPID = new GyroPID(0);
		Sensors.getInstance().resetEncoders();
		drivetrain.shiftLow();
		ArmLooper.getInstance().setTarget(holdingAngle);
		int countingGoodPos = 0;
		while(countingGoodPos < 10 && DriverStation.getInstance().isAutonomous())
		{
			ArmLooper.getInstance().continueLoop();
			double output = drivePID.getOutput();
			drivetrain.arcadeDrive(output,gyroPID.getOutput());
			SmartDashboard.putNumber("PID Output:",output);
			SmartDashboard.putNumber("Encoder: ",Sensors.getInstance().getEncoderAverageInInches());
			if(Math.abs(drivePID.currentError - drivePID.lastError) < 0.1 || Math.abs(drivePID.currentError) < 2.5)
				countingGoodPos++;
			if(runIntake)
				intake.runIntake(intakeSpeed);
			Timer.delay(0.02);
		}
		SmartDashboard.putNumber("Final Encoder: ",Sensors.getInstance().getEncoderAverageInInches());
		drivetrain.setLeftAndRightPWM(0,0);
	}
}
