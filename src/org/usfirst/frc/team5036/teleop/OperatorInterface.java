package org.usfirst.frc.team5036.teleop;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OperatorInterface 
{
	private static OperatorInterface instance;
	public static OperatorInterface getInstance()
	{
		if(instance == null)
			instance = new OperatorInterface();
		return instance;
	}
	
	//Controller Ports
	public static final int DRIVER_PORT = 0;
	public static final int OPERATOR_PORT = 1;
    
	public Joystick driverController, operatorController;
	public OperatorInterface()
	{
		driverController = new Joystick(DRIVER_PORT);
		operatorController = new Joystick(OPERATOR_PORT);
	}
	
	// Driver Controller Methods
	public double getDriveForward()
	{
		double forward = -driverController.getRawAxis(1);
		SmartDashboard.putNumber("Forward: ", forward);
		return forward;
	}
	
	public double getDriveRotate()
	{
		double rotate = driverController.getRawAxis(4);
		SmartDashboard.putNumber("Rotate: ", rotate);
		return rotate;
	}
	
	public boolean isHighGearBtnPressed()
	{	
		return driverController.getRawButton(6);
	}
	
	public boolean getPokePistonBtn()
	{
		return driverController.getRawButton(5);
	}
	
	public double getOuttakeTrigger()
	{
		return driverController.getRawAxis(2);
	}
	
	// Operator Controller Methods
	public boolean holdCube()
	{
		return operatorController.getRawButton(5);
	}
	
	public double getIntakeTrigger()
	{
		return operatorController.getRawAxis(2);
	}
	
	public double getIntakeStick()
	{
		return operatorController.getRawAxis(1);
	}
	
	public boolean isClosedLoopArm()
	{
		return operatorController.getRawAxis(3) > 0.2;
	}
	
	public boolean getSwitchHeightBtn()
	{
		return operatorController.getRawButton(4);
	}
	
	public boolean getExchangeHeightBtn()
	{
		return operatorController.getRawButton(2);
	}
	
	public boolean getFlipHeightBtn()
	{
		return operatorController.getRawButton(3);
	}
	
	public boolean getIntakeHeightBtn()
	{
		return operatorController.getRawButton(1);
	}
	
	public boolean isOpenLoopArm()
	{
		return operatorController.getRawButton(6);
	}
	
	public double getManualArmStick()
	{
		return -operatorController.getRawAxis(5);
	}
	
	public boolean turnOnCompressorBtn()
	{
		return operatorController.getRawButton(9);
	}

	public boolean turnOffCompressorBtn()
	{
		return operatorController.getRawButton(10);
	}
}
