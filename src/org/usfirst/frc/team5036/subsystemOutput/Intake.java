package org.usfirst.frc.team5036.subsystemOutput;

import org.usfirst.frc.team5036.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

public class Intake 
{
	private static final double STALL_SPEED = 0.2;
	private static Intake instance;
	public static Intake getInstance()
	{
		if(instance == null)
			instance = new Intake();
		return instance;
	}
	
	public TalonSRX intake1, intake2;
	Solenoid ledLight;
	DoubleSolenoid poker, pinchers;
	public Intake()
	{
		intake1 = new TalonSRX(RobotMap.INTAKE_1);
		intake2 = new TalonSRX(RobotMap.INTAKE_2);
		ledLight = new Solenoid(RobotMap.LED);
		poker = new DoubleSolenoid(RobotMap.POKER_IN,RobotMap.POKER_OUT);
		pinchers = new DoubleSolenoid(RobotMap.PINCH_IN,RobotMap.PINCH_OUT);
	}
	
	public void runIntake(double power)
	{
		power *= 0.5;
		intake1.set(ControlMode.PercentOutput,power);
		intake2.set(ControlMode.PercentOutput,power);
	}
	
	public void stallIntake()
	{
		runIntake(STALL_SPEED);
	}
	
	public void lightLED()
	{
		ledLight.set(true);
	}
	
	public void turnOffLED()
	{
		ledLight.set(false);
	}
	
	public void pokeCube()
	{
		poker.set(DoubleSolenoid.Value.kForward);
	}
	
	public void retractPoker()
	{
		poker.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void pinchCube()
	{
		pinchers.set(DoubleSolenoid.Value.kForward);
	}
	
	public void retractPinchers()
	{
		pinchers.set(DoubleSolenoid.Value.kReverse);
	}
}
