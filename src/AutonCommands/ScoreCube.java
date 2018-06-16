package AutonCommands;

import org.usfirst.frc.team5036.robot.RobotOutput;

import Loops.ArmLooper;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class ScoreCube extends RobotOutput
{
	public static void execute(int seconds,double intakeSpeed,boolean pokeCube)
	{
		int loops = (int) (seconds / 0.02);
		if(pokeCube)
			intake.pokeCube();
		intake.runIntake(-intakeSpeed);
		for(int i = 0;i < loops && DriverStation.getInstance().isAutonomous();++i)
		{
			ArmLooper.getInstance().continueLoop();
			Timer.delay(0.02);
		}
		intake.runIntake(0);
		intake.retractPoker();
	}
}
