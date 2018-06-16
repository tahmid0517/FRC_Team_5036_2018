package AutonRoutines;

import AutonCommands.DriveStraight;
import AutonCommands.Turn;

public class PartTwoToOtherSide 
{
	public static void execute(String mssg)
	{
		if(mssg.charAt(0) == 'L')
			Turn.execute(60,-90,false,0);
		else if(mssg.charAt(1) == 'R')
			Turn.execute(60,90,false,0);
		if(mssg.charAt(0) == mssg.charAt(1))
			DriveStraight.execute(45,50,false,0.5,0);
		else
			DriveStraight.execute(45,80,false,0.5,0);
		if(mssg.charAt(0) == 'L')
			Turn.execute(60,90,false,0);
		else if(mssg.charAt(1) == 'R')
			Turn.execute(60,-90,false,0);
	}
}
