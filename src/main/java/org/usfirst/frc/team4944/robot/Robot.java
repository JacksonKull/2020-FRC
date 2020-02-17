package org.usfirst.frc.team4944.robot;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4944.robot.commands.IntakeInit;
import org.usfirst.frc.team4944.robot.commands.IntakingFinished;
import org.usfirst.frc.team4944.robot.commands.ShooterSpinDown;
import org.usfirst.frc.team4944.robot.commands.ShooterSpinUpInit;
import org.usfirst.frc.team4944.robot.custom.XboxController;
import org.usfirst.frc.team4944.robot.subsystems.DriveSystem;
import org.usfirst.frc.team4944.robot.subsystems.HoodSubsystem;
import org.usfirst.frc.team4944.robot.subsystems.HopperSubsystem;
import org.usfirst.frc.team4944.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team4944.robot.subsystems.ShooterSubsystem;
import org.usfirst.frc.team4944.robot.subsystems.TurretSubsystem;


public class Robot extends TimedRobot {
	// CONTROLLERS
	XboxController driver;
	XboxController operator;
	// SUBSYSTEMS
	OI oi;
	DriveSystem driveSystem;
	TurretSubsystem turret;
	ShooterSubsystem shooter;
	IntakeSubsystem intake;
	HopperSubsystem hopper;
	HoodSubsystem hood;
	//SmartDashboard Values
	double turretEncoder;

	@Override
	public void robotInit() {
		// CONTROLLERS INIT
		this.driver = new XboxController(0);
		this.operator = new XboxController(1);
		
		// SUBSYSTEMS INIT
		this.oi = new OI();
		this.turret = new TurretSubsystem();
		this.shooter = new ShooterSubsystem();
		this.driveSystem = new DriveSystem();
		this.hopper = new HopperSubsystem();
		this.intake = new IntakeSubsystem();
		this.hood = new HoodSubsystem();
		
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

		// Drive Code
		double Y = -driver.getLeftStickY();
		double X = driver.getRightStickX();
		this.driveSystem.setPower(X + Y, X - Y);

		// this.hood.setHoodMotorPower(this.operator.getLeftStickY());

		// //A Button Shooter Control
		// if(!(this.driver.getAToggle())){
        //     this.driver.addWhenHeldToA(new ShooterSpinUpInit(1.0));
        // }else if(!this.driver.getAToggle()){
        //     this.driver.addWhenReleasedToA(new ShooterSpinDown());
		// }
		// //B Button Intake Control
		// if(!(this.driver.getBToggle())){
        //     this.driver.addCommandToB(new IntakeInit(0.9));
        // }else if(!this.driver.getBToggle()){
        //     this.driver.addWhenReleasedToB(new IntakingFinished());
		// }

		// Update Values
		this.updateValues();
		this.oi.updateCommands();
	}

	@Override
	public void testPeriodic() {
	}

	public void updateValues(){
		//System.out.println(this.hood.getHoodEncoderValue());
		// if(this.driver.getRightBumper()){
		// 	this.turret.setTurretAngle(25);
		// }else if(this.driver.getLeftBumper()){
		// 	this.turret.setTurretAngle(45);
		// }

		// if(this.operator.getAButton()){
		// 	this.turret.followLimelightNoEncoder();
		// }else if(!this.operator.getAButton()){
		// 	this.turret.setTurretMotorPower(0);
		// }

		// this.turret.followLimelightNoEncoder();

		// // System.out.println(this.hood.getVx() + " VX");
		// // System.out.println(this.hood.getVy() + " VY");
		// // System.out.println(this.hood.getRequiredAngle());
		// // //turret.followLimelight(); // Uses the limelight to change the set points on the turret
		// //turret.driveTurretPID(); // Sets the turrets Power using a PID loop
		// this.shooter.updateValues();
		// this.hood.updateValues();
		// this.hood.setAngleByLM();
		// this.hood.driveHoodPID();
		this.SmartDashboardDisplay(); // Displays all Smartdashboard Values
	}

	public void SmartDashboardDisplay(){
		// Turret
		// SmartDashboard.putNumber("Turret SetPoint", this.turret.getTurretSetPoint());
		//SmartDashboard.putNumber("Turret Encoder", this.turret.getTurretEncoderValue());
		// SmartDashboard.putNumber("Turret Power", this.turret.getTurretPower());
		// SmartDashboard.putNumber("Hood Angle", this.turret.getHoodAngle());
		SmartDashboard.putNumber("Hood Encoder", this.hood.getHoodEncoderValue());
		SmartDashboard.putNumber("Hood SetPoint", this.hood.getHoodSetPoint());
		SmartDashboard.putNumber("Hood Power", this.hood.getHoodMotorPower());
		SmartDashboard.putNumber("Set Hood Angle", this.hood.getRequiredAngle());
		SmartDashboard.putNumber("Vx", this.hood.getVx());
		SmartDashboard.putNumber("Vy", this.hood.getVy());
		// Limelight
		SmartDashboard.putNumber("Limelight Y Offset", turret.lm.getYOffset());
		SmartDashboard.putNumber("Limelight X Offset", turret.lm.getXOffset());
		SmartDashboard.putNumber("Distance From Target", turret.lm.getDistInFeet());
		// SmartDashboard.putBoolean("Limelight Connection:", turret.lm.getLimeLightConnected());
	}
}
