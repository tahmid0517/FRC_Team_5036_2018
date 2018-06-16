package org.usfirst.frc.team5036.robot;

import org.usfirst.frc.team5036.subsystemOutput.Arm;
import org.usfirst.frc.team5036.subsystemOutput.Drivetrain;
import org.usfirst.frc.team5036.subsystemOutput.Intake;

public class RobotOutput 
{
	public static Drivetrain drivetrain;
	public static Intake intake;
	public static Arm arm;
	public static void init()
	{
		drivetrain = Drivetrain.getInstance();
		intake = Intake.getInstance();
		arm = Arm.getInstance();
	}
}
