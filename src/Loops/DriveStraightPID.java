package Loops;

import org.usfirst.frc.team5036.robot.Sensors;
import org.usfirst.frc.team5036.subsystemOutput.Drivetrain;

public class DriveStraightPID 
{
	public static final double P_CONSTANT = 1.5;
	public static final double D_CONSTANT = 1.2;
	public double currentError,lastError,target,outputCap;
	public boolean firstCycle;
	public DriveStraightPID(double target,double maxSpeed)
	{
		lastError = 0;
		this.target = target;
		currentError = target - Sensors.getInstance().getEncoderAverageInInches();
		firstCycle = true;
		Sensors.getInstance().resetEncoders();
		Drivetrain.getInstance().shiftLow();
		outputCap = maxSpeed;
	}
	
	public double getOutput()
	{
		if(firstCycle)
		{
			firstCycle = false;
			currentError = target - Sensors.getInstance().getEncoderAverageInInches();
			return capOutput(getP());
		}
		else
		{
			updateError();
			return capOutput(getP() + getD());
		}
	}
	
	public double capOutput(double val)
	{
		if(val > outputCap)
			return outputCap;
		else if(val < -outputCap)
			return -outputCap;
		return val;
	}
	
	public double getP()
	{
		return P_CONSTANT * currentError;
	}
	
	public double getD()
	{
		return (currentError - lastError) * D_CONSTANT;
	}
	
	public void updateError()
	{
		lastError = currentError;
		currentError = target - Sensors.getInstance().getEncoderAverageInInches();
	}
}
