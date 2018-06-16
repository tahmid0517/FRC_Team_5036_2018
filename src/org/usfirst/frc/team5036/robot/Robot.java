/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5036.robot;

import org.usfirst.frc.team5036.subsystemOutput.Intake;
import org.usfirst.frc.team5036.teleop.Teleop;
import AutonCommands.DriveStraight;
import AutonRoutines.PartTwoSecondCube;
import AutonRoutines.PartTwoToOtherSide;
import AutonRoutines.ScoreCubeInSwitch;
import Loops.ArmLooper;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot 
{
	public static final int NO_PART_TWO = 2;
	public static final int PART_TWO_OTHER_SIDE = 1;
	public static final int PART_TWO_2ND_CUBE = 0;
	public static final int DRIVE_STRAIGHT = 3;
	public SendableChooser<Integer> autonChooser;
	public Compressor compressor;
	@Override
	public void robotInit() 
	{
		// Keep initializing of sensors and outputs in this order. It's faster.
		Sensors.getInstance().clearStickyFaults();;
		RobotOutput.init();
		ArmLooper.getInstance();
		compressor = new Compressor(0);
		autonChooser = new SendableChooser<Integer>();
		autonChooser.addDefault("Score Cube and 2nd Cube", PART_TWO_2ND_CUBE);
		autonChooser.addObject("Score Cube and No Part Two",NO_PART_TWO);
		autonChooser.addObject("Score Cube and Part Two Other Side", PART_TWO_OTHER_SIDE);
		autonChooser.addObject("Drive Straight cuz these haters don't trust us", DRIVE_STRAIGHT);
		SmartDashboard.putData("Autons", autonChooser);
	}

	@Override
	public void autonomousInit() 
	{
		String mssg = DriverStation.getInstance().getGameSpecificMessage();
		System.out.println(mssg);
		System.out.println("Auton selected: " + autonChooser.getSelected());
		if((int)autonChooser.getSelected() == PART_TWO_2ND_CUBE)
		{
			ScoreCubeInSwitch.execute(mssg.charAt(0));
			PartTwoSecondCube.execute(mssg.charAt(0));
		}
		else if((int)autonChooser.getSelected() == PART_TWO_OTHER_SIDE)
		{
			ScoreCubeInSwitch.execute(mssg.charAt(0));
			PartTwoToOtherSide.execute(mssg);
		}
		else if((int)autonChooser.getSelected() == NO_PART_TWO)
		{
			ScoreCubeInSwitch.execute(mssg.charAt(0));
		}
		else if((int)autonChooser.getSelected() == DRIVE_STRAIGHT)
		{
			DriveStraight.execute(80,120,true,0.5,0.25);
		}
		//DriveStraight.execute(80,120,true,0.5,0.25);
	}

	@Override
	public void autonomousPeriodic() 
	{
		ArmLooper.getInstance().continueLoop();
		Intake.getInstance().runIntake(0);
		Sensors.getInstance().displayAllValues();
	}

	@Override
	public void teleopInit()
	{
		Teleop.init();
		compressor.start();
	}
	
	@Override
	public void teleopPeriodic() 
	{
		Teleop.run();
		Sensors.getInstance().displayAllValues();
	}
	
	@Override
	public void disabledInit(){}
	
	@Override
	public void disabledPeriodic()
	{
		Sensors.getInstance().displayAllValues();
	}
	
	@Override
	public void testPeriodic() 
	{
	}
}
