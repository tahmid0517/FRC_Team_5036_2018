package AutonRoutines;

public class ScoreCubeInSwitch 
{
	public static void execute(char c)
	{
		if(c == 'L')
			DriveAndTurnTwiceAndScoreCube.execute();
		else if(c == 'R')
			DriveStraightAndScoreCube.execute();
	}
}
