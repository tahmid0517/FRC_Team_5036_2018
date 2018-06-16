package Loops;

import org.usfirst.frc.team5036.robot.Sensors;
import org.usfirst.frc.team5036.subsystemOutput.Arm;

public class ArmLooper 
{
	private static ArmLooper instance;
	public static ArmLooper getInstance()
	{
		if(instance == null)
			instance = new ArmLooper();
		return instance;
	}
	
	public static final double P_CONSTANT = 0.05;
	public static final double GRAVITY_CONST = 0.098;
	public double currentError,target; 
	public ArmLooper()
	{
		currentError = 0;
		target = Sensors.getInstance().getPotentiometerConverted();
	}
	
	public void setTarget(double target)
	{
		this.target = target;
	}
	
	public double getGravityCompensation()
	{
		return GRAVITY_CONST * Math.cos(getCurrentPosition());
	}
	public void continueLoop()
	{
		updateError();
		Arm.getInstance().driveArm((getP() + getGravityCompensation()) * getVoltageCompensationConst());
	}
	
	public double getP()
	{
		return P_CONSTANT * currentError;
	}
	
	public double getCurrentPosition()
	{
		return Sensors.getInstance().getPotentiometerConverted();
	}
	
	public double getVoltageCompensationConst()
	{
		return 12 / Sensors.getInstance().getVoltage();
	}
	
	public void updateError()
	{
		currentError = target - getCurrentPosition();
	}
	
}
