package org.usfirst.frc.team4944.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4944.robot.commands.ExampleCommand;
import org.usfirst.frc.team4944.robot.custom.XboxController;
import org.usfirst.frc.team4944.robot.subsystems.DriveSystem;
import org.usfirst.frc.team4944.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team4944.robot.subsystems.TurretSubsystem;


public class Robot extends TimedRobot {
	// SERIAL PORT
	SerialPort serial;
	// CONTROLLERS
	XboxController driver;
	XboxController operator;
	// SUBSYSTEMS
	//DriveSystem driveSystem;
	TurretSubsystem turret;
	//SmartDashboard Values
	double turretEncoder;

	@Override
	public void robotInit() {
		// CONTROLLERS INIT
		driver = new XboxController(0);
		operator = new XboxController(1);
		
		// SUBSYSTEMS INIT
		turret = new TurretSubsystem();
		//driveSystem = new DriveSystem();
		
		//SmartDashboard
		this.SmartDashboardDisplay();
	}

	@Override
	public void disabledInit() {
		this.SmartDashboardDisplay();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run(); // KEEP HERE TO RUN COMMANDS
		this.SmartDashboardDisplay();
	}

	@Override
	public void autonomousInit() {
		this.SmartDashboardDisplay();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run(); // KEEP HERE TO RUN COMMANDS
		this.updateValues();
	}

	@Override
	public void teleopInit() {
		this.SmartDashboardDisplay();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run(); // KEEP HERE TO RUN COMMANDS
		
		double Y = driver.getLeftStickY();
		double X = driver.getRightStickX();
		
		if(driver.getDpadPressed()){
			turret.setAngle(driver.getDpadAngle());
		}
		this.updateValues();
	}

	@Override
	public void testPeriodic() {
	}

	public void updateValues(){
		turret.driveTurretPID();	
		this.SmartDashboardDisplay();
	}

	public void SmartDashboardDisplay(){
		SmartDashboard.putNumber("Turret Encoder", turret.getTurretEncoderValue());
		SmartDashboard.putNumber("Limelight Y Offset", turret.lm.getYOffset());
		SmartDashboard.putNumber("Distance From Target", turret.lm.getDistInFeet());
	}
}
