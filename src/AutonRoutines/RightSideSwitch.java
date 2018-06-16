package AutonRoutines;

import AutonCommands.DriveStraight;
import AutonCommands.Turn;

public class RightSideSwitch 
{
	public static void execute(char c)
	{
		DriveStraight.execute(80,120,true,0.5,0.25);
		if(c == 'R')
		{
			//Turn.execute(80,-90,, intakeSpeed);
		}
	}
}
