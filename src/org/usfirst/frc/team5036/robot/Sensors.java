package org.usfirst.frc.team5036.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Sensors 
{
	private static Sensors instance;
	public static Sensors getInstance()
	{
		if(instance == null)
			instance = new Sensors();
		return instance;
	}
	
	public ADXRS450_Gyro gyro;
	public AnalogInput ultrasonic;
	public Encoder leftEncoder, rightEncoder;
	public DigitalInput limitSwitch;
	public PowerDistributionPanel pdp;
	public UsbCamera camera;
	public CameraServer camServer;
	public AnalogPotentiometer pot;
	public Sensors()
	{
		gyro = new ADXRS450_Gyro();
		ultrasonic = new AnalogInput(1);
		leftEncoder = new Encoder(RobotMap.LEFT_ENC_IN,RobotMap.LEFT_ENC_OUT,false,Encoder.EncodingType.k4X);
		rightEncoder = new Encoder(RobotMap.RIGHT_ENC_IN,RobotMap.RIGHT_ENC_OUT,false,Encoder.EncodingType.k4X);
		leftEncoder.reset();
		rightEncoder.reset();
		limitSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH);
		pdp = new PowerDistributionPanel(1);
		camera = CameraServer.getInstance().startAutomaticCapture(0);
		camera.setResolution(320,240);
		camera.setFPS(30);
		pot = new AnalogPotentiometer(RobotMap.POTENTIOMETER);
	}
	
	public void displayAllValues()
	{
		SmartDashboard.putNumber("Gyro:",getAngle());
		SmartDashboard.putNumber("Ultrasonic Raw:",getUltrasonicRawVoltage());
		SmartDashboard.putNumber("Left Encoder, Ticks:",leftEncoder.getRaw());
		SmartDashboard.putNumber("Left Encoder, Inches:",getLeftEncoderInInches());
		SmartDashboard.putNumber("Right Encoder, Ticks:",rightEncoder.getRaw());
		SmartDashboard.putNumber("Right Encoder, Inches",getRightEncoderInInches());
		SmartDashboard.putNumber("Average Encoder, Ticks",getEncoderAverageInTicks());
		SmartDashboard.putNumber("Average Encoder, Inches",getEncoderAverageInInches());
		SmartDashboard.putBoolean("Limit Switch:", isArmAbleToGoDown());
		SmartDashboard.putNumber("Potentiometer Raw:", getPotentiometer());
		SmartDashboard.putNumber("Potentiometer Converted:", getPotentiometerConverted());
		SmartDashboard.putNumber("Potentiometer New:",pot.get());
	}
	
	public double getAngle()
	{
		return gyro.getAngle();
	}
	
	public void resetGyro()
	{
		gyro.reset();
	}
	
	public double getUltrasonicRawVoltage()
	{
		return ultrasonic.getVoltage();
	}
	
	public double getLeftEncoderInTicks()
	{
		return leftEncoder.getRaw();
	}
	
	public double getRightEncoderInTicks()
	{
		return rightEncoder.getRaw();
	}
	
	public double getEncoderAverageInTicks()
	{
		return getRightEncoderInTicks();
	}
	
	public double getLeftEncoderInInches()
	{
		return -leftEncoder.getRaw() / 388.34826762246;
	}
	
	public double getRightEncoderInInches()
	{
		return -rightEncoder.getRaw() / 388.34826762246;
	}
	
	public void resetEncoders()
	{
		leftEncoder.reset();
		rightEncoder.reset();
	}
	
	public double getEncoderAverageInInches()
	{
		return getRightEncoderInInches();
	}
	
	public boolean isArmAbleToGoDown()
	{
		return limitSwitch.get();
	}
	
	public double getVoltage()
	{
		return pdp.getVoltage();
	}
	
	public void clearStickyFaults()
	{
		pdp.clearStickyFaults();
	}
	
	public double getPotentiometer()
	{
		return pot.get();
	}
	
	public double getPotentiometerConverted()
	{
		double raw = getPotentiometer();
		double lowest = 0.3305;
		double atNinety = 0.409;
		double constDiff = lowest - atNinety;
		double rawDiff = lowest - raw;
		double conversionConst = 90 / constDiff;
		double angle = rawDiff * conversionConst;
		return angle;
	}
}
