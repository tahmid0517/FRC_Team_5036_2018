package org.usfirst.frc.team5036.teleop;

import org.usfirst.frc.team5036.robot.RobotOutput;
import org.usfirst.frc.team5036.robot.Sensors;

import Loops.ArmLooper;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Teleop extends RobotOutput
{
	public static OperatorInterface oi;
	
	public static void init()
	{
		oi = OperatorInterface.getInstance();
	}
	
	public static void run()
	{
		drive();
		intake();
		arm();
	}
	
	public static void drive()
	{
		if(oi.isHighGearBtnPressed())
			drivetrain.shiftHigh();
		else
			drivetrain.shiftLow();
		drivetrain.arcadeDrive(oi.getDriveForward(), oi.getDriveRotate());
	}
	
	public static void intake()
	{
		
		if(oi.getOuttakeTrigger() > 0.05)
		{
			intake.runIntake(-oi.getOuttakeTrigger());
			intake.retractPinchers();
		}
		else if(oi.getIntakeTrigger() > 0.05)
		{
			intake.runIntake(0.55);
			intake.turnOffLED();
		}
		else if(oi.holdCube())
		{
			intake.stallIntake();
			intake.pinchCube();
			intake.lightLED();
			SmartDashboard.putString("Stalling Cube?","YES");
		}
		else
		{
			intake.runIntake(oi.getIntakeStick());
			intake.retractPinchers();
			intake.turnOffLED();
			SmartDashboard.putString("Stalling Cube?","NO");

		}
		if(oi.getPokePistonBtn())
			intake.pokeCube();
		else
			intake.retractPoker();
		if(Sensors.getInstance().getUltrasonicRawVoltage() <= 0.267)
		{
			SmartDashboard.putString("Do we have cube?:", "YEEEEEEEEEEEEEEEEEEEEEEES");
		}
		else
		{
			SmartDashboard.putString("Do we have cube?:", "NO");
		}
	}
	
	public static void arm()
	{
		if(oi.isOpenLoopArm())
			arm.driveArm(oi.getManualArmStick());
		else if(oi.isClosedLoopArm())
		{
			if(oi.operatorController.getPOV(0) == 0)
				ArmLooper.getInstance().setTarget(70);
			else if(oi.getSwitchHeightBtn())
				ArmLooper.getInstance().setTarget(45);
			else if(oi.getFlipHeightBtn())
				ArmLooper.getInstance().setTarget(23);
			else if(oi.getExchangeHeightBtn())
				ArmLooper.getInstance().setTarget(10);
			else if(oi.getIntakeHeightBtn())
				ArmLooper.getInstance().setTarget(1);
			else if(oi.operatorController.getPOV(0) == 90)
				ArmLooper.getInstance().setTarget(85);
			else if(oi.operatorController.getPOV(0) == 270)
				ArmLooper.getInstance().setTarget(35);
			else if(oi.operatorController.getPOV(0) == 180)
				ArmLooper.getInstance().setTarget(120);
			ArmLooper.getInstance().continueLoop();
		}
		else
			arm.driveArm(0);
	}
}
