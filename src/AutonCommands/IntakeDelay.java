package AutonCommands;

import org.usfirst.frc.team5036.subsystemOutput.Intake;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class IntakeDelay 
{
	public static void execute(double speed,double seconds)
	{
		double loops = seconds / 0.02;
		for(int i = 0;i < loops && DriverStation.getInstance().isAutonomous();++i)
		{
			Intake.getInstance().runIntake(speed);
			Timer.delay(0.02);
		}
	}
}
