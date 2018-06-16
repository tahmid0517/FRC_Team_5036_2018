package Loops;

import org.usfirst.frc.team5036.robot.Sensors;
import org.usfirst.frc.team5036.subsystemOutput.Drivetrain;

public class GyroPID 
{
	public static final double P_CONSTANT = 0.07;
	public static final double D_CONSTANT = 0.18;
	public double currentError,lastError,target;
	public boolean firstCycle;
	public GyroPID(double target)
	{
		lastError = 0;
		this.target = target;
		currentError = target;
		Sensors.getInstance().resetGyro();
		firstCycle = true;
		Drivetrain.getInstance().shiftLow();
	}
	
	public double getOutput()
	{
		if(firstCycle)
		{
			firstCycle = false;
			currentError = target - Sensors.getInstance().getAngle();
			return getP();
		}
		else
		{
			updateError();
			return getP() + getD();
		}
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
		currentError = target - Sensors.getInstance().getAngle();
	}
}
