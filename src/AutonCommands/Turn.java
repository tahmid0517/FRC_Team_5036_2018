package AutonCommands;

import org.usfirst.frc.team5036.robot.RobotOutput;
import org.usfirst.frc.team5036.robot.Sensors;

import Loops.ArmLooper;
import Loops.GyroPID;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Turn extends RobotOutput
{
	public static void execute(double holdingArmAngle,double angleOfTurn,boolean runIntake,double intakeSpeed)
	{
		GyroPID turnPID = new GyroPID(angleOfTurn);
		int countingGoodPos = 0;
		ArmLooper.getInstance().setTarget(holdingArmAngle);
		while(countingGoodPos < 10 && DriverStation.getInstance().isAutonomous())
		{
			ArmLooper.getInstance().continueLoop();
			double output = turnPID.getOutput();
			drivetrain.arcadeDrive(0,output);
			SmartDashboard.putNumber("PID Output",output);
			SmartDashboard.putNumber("Angle", Sensors.getInstance().getAngle());
			if(Math.abs(turnPID.currentError) < 2)
				countingGoodPos++;
			if(runIntake)
				intake.runIntake(intakeSpeed);
			Timer.delay(0.02);
		}
		System.out.println("Just finished turn PID at " + angleOfTurn + " degrees.");
	}
}