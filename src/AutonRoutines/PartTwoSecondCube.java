package AutonRoutines;

import org.usfirst.frc.team5036.subsystemOutput.Intake;

import AutonCommands.DriveStraight;
import AutonCommands.IntakeDelay;
import AutonCommands.MoveArmToNewAngle;
import AutonCommands.ScoreCube;
import AutonCommands.Turn;

public class PartTwoSecondCube 
{
	public static void execute(char c)
	{
		if(c == 'L')
		{
			double ARM_ANGLE_1 = 45;
			double TURN_1 = 54;
			double DISTANCE_1 = -69;
			//double ARM_ANGLE_2 = 45;
			double DISTANCE_2 = 20;
			double TURN_2 = 0;
			double DISTANCE_3 = -35;
			double ARM_ANGLE_3 = 45;
			double DISTANCE_4 = 90.5;
			Turn.execute(ARM_ANGLE_1,TURN_1,false,0);
			MoveArmToNewAngle.execute(35,0);
			MoveArmToNewAngle.execute(0,0);
			Intake.getInstance().retractPinchers();
			DriveStraight.execute(0,29,true,0.8,0.5);
			IntakeDelay.execute(0.4,1);
			Intake.getInstance().pinchCube();
			MoveArmToNewAngle.execute(45,0.2);
			Turn.execute(45,-76,true,0.2);
			DriveStraight.executeTilStop(45,75,true,0.7,0.2);
			ScoreCube.execute(1,0.4,true);
			//Turn.execute(ARM_ANGLE_1,TURN_2,false,0);
			//Intake.getInstance().retractPinchers();
			//DriveStraight.execute(0,DISTANCE_2,true,0.9,0.8);
			//IntakeDelay.execute(0.5,1);
			//Intake.getInstance().pinchCube();
			//DriveStraight.execute(0,DISTANCE_3,true,0.9,0.2);
			/*Turn.execute(0,-35,true,0.2);
			DriveStraight.execute(ARM_ANGLE_3,DISTANCE_4,true,0.5,0.2);
			ScoreCube.execute(1,0.3,true);*/
		}
			
		else if(c == 'R')
		{
			double ARM_ANGLE_1 = 60;
			double TURN_1 = 45;
			double DISTANCE_1 = -74;
			double TURN_2 = -45;
			double ARM_MOVE_1 = 30;
			double ARM_MOVE_2 = 0;
			double DISTANCE_2 = 35;
			double DISTANCE_3 = -35;
			double TURN_3 = 30;
			double ARM_MOVE_3 = 45;
			double DISTANCE_4 = 95;
			Turn.execute(ARM_ANGLE_1,TURN_1,false,0);
			DriveStraight.execute(ARM_ANGLE_1,DISTANCE_1,false,0.7,0);
			Turn.execute(60,TURN_2,false,0);
			MoveArmToNewAngle.execute(ARM_MOVE_1,0);
			MoveArmToNewAngle.execute(ARM_MOVE_2,0);
			Intake.getInstance().retractPinchers();
			DriveStraight.execute(0,DISTANCE_2,true,0.9,0.8);
			IntakeDelay.execute(0.8,0.5);
			Intake.getInstance().pinchCube();
			DriveStraight.execute(0,DISTANCE_3,true,0.9,0.2);
			MoveArmToNewAngle.execute(ARM_MOVE_3,0.2);
			Turn.execute(45,TURN_3,true,0.2);
			DriveStraight.executeTilStop(45,DISTANCE_4,true,0.5,0.2);
			ScoreCube.execute(1,0.4,true);
			DriveStraight.execute(45,-30,false,0.7,0);
			MoveArmToNewAngle.execute(0,0);
		}
	}
}
