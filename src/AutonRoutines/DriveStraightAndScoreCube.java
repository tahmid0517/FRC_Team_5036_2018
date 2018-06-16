package AutonRoutines;

import AutonCommands.DriveStraight;
import AutonCommands.MoveArmToNewAngle;
import AutonCommands.ScoreCube;

public class DriveStraightAndScoreCube 
{
	public static final double FIRST_ANGLE = 80;
	public static void execute()
	{
		DriveStraight.execute(FIRST_ANGLE,107,true,0.8,0.25);
		MoveArmToNewAngle.execute(45,0);
		ScoreCube.execute(1,0.4,true);
		MoveArmToNewAngle.execute(60,0);
		DriveStraight.execute(60,-30,false,0.5,0);
	}
}
