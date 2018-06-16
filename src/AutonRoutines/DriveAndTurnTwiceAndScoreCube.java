package AutonRoutines;

import org.usfirst.frc.team5036.subsystemOutput.Intake;

import AutonCommands.DriveStraight;
import AutonCommands.MoveArmToNewAngle;
import AutonCommands.ScoreCube;
import AutonCommands.Turn;

public class DriveAndTurnTwiceAndScoreCube 
{
	public static final double FIRST_ANGLE = 80;
	public static void execute()
	{
		DriveStraight.execute(FIRST_ANGLE,53,true,0.5,0.25);
		Turn.execute(FIRST_ANGLE,-90,true,0.25);
		DriveStraight.execute(FIRST_ANGLE,110,true,0.5,0.25);
		Turn.execute(FIRST_ANGLE,90,true,0.25);
		DriveStraight.executeTilStop(FIRST_ANGLE,65,true,0.5,0.25);
		MoveArmToNewAngle.execute(45,0);
		ScoreCube.execute(1,0.4,true);
		Intake.getInstance().runIntake(0);
		DriveStraight.execute(60,-50,false,0.9,0);
	}
}
