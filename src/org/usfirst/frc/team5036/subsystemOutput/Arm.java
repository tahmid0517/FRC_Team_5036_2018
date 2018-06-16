package org.usfirst.frc.team5036.subsystemOutput;

import org.usfirst.frc.team5036.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm 
{
	private static Arm instance;
	public static Arm getInstance()
	{
		if(instance == null)
			instance = new Arm();
		return instance;
	}
	
	public TalonSRX cim1, cim2;
	public Arm()
	{
		cim1 = new TalonSRX(RobotMap.ARM_1);
		cim2 = new TalonSRX(RobotMap.ARM_2);
		cim2.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);
	}
	
	public void driveArm(double output)
	{
		if(output < -0.5)
		{
			output = -0.5;
		}
		else if(output > 0.5)
		{
			output = 0.5;
		}
		cim1.set(ControlMode.PercentOutput,output);
		cim2.set(ControlMode.PercentOutput, -output);
		SmartDashboard.putNumber("Arm 1", cim1.getMotorOutputPercent());
		SmartDashboard.putNumber("Arm 2:", cim2.getMotorOutputPercent());
	}
}
