package AutonCommands;

import org.usfirst.frc.team5036.subsystemOutput.Intake;

import Loops.ArmLooper;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class MoveArmToNewAngle 
{
	public static void execute(double newAngle,double intakeSpeed)
	{
		ArmLooper.getInstance().setTarget(newAngle);
		int countingGoodPos = 0;
		Intake.getInstance().runIntake(intakeSpeed);
		while(countingGoodPos < 10 && DriverStation.getInstance().isAutonomous())
		{
			ArmLooper.getInstance().continueLoop();
			if(ArmLooper.getInstance().currentError < 5)
				countingGoodPos++;
			Timer.delay(0.02);
		}
		Intake.getInstance().runIntake(0);
	}
}
