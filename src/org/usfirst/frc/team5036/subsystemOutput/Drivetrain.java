package org.usfirst.frc.team5036.subsystemOutput;

import org.usfirst.frc.team5036.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain 
{
	private static Drivetrain instance;
	public static Drivetrain getInstance()
	{
		if(instance == null)
			instance = new Drivetrain();
		return instance;
	}
	
	public VictorSP left1,left2,right1,right2;
	public DoubleSolenoid shifters;
	public Drivetrain()
	{
		left1 = new VictorSP(RobotMap.DRIVE_LEFT_1);
		left2 = new VictorSP(RobotMap.DRIVE_LEFT_2);
		right1 = new VictorSP(RobotMap.DRIVE_RIGHT_1);
		right2 = new VictorSP(RobotMap.DRIVE_RIGHT_2);
		shifters = new DoubleSolenoid(RobotMap.SHIFTERS_IN,RobotMap.SHIFTERS_OUT);
	}
	
	public double capMotorSpeed(double speed)
	{
		double cappedSpeed = 0.6;
		if(speed < -cappedSpeed)
			return -cappedSpeed;
		if(speed > cappedSpeed)
			return cappedSpeed;
		return speed;
	}
	
	public void setLeftAndRightPWM(double left, double right)
	{
		double leftPWM = capMotorSpeed(left);
		double rightPWM = capMotorSpeed(right);
		left1.set(leftPWM);
		left2.set(leftPWM);
		right1.set(-rightPWM);
		right2.set(-rightPWM);
		SmartDashboard.putNumber("Victor 1",left1.get());
		SmartDashboard.putNumber("Victor 3",right1.get());
	}
	
	public void arcadeDrive(double forward,double rotate)
	{
		double left = forward - rotate;
		double right = forward + rotate;
		setLeftAndRightPWM(left,right);
	}
	
	public void shiftLow()
	{
		shifters.set(DoubleSolenoid.Value.kForward);
	}
	
	public void shiftHigh()
	{
		shifters.set(DoubleSolenoid.Value.kReverse);
	}
}
