package org.usfirst.frc.team4944.robot;

import org.usfirst.frc.team4944.robot.commands.ArmsDown;
import org.usfirst.frc.team4944.robot.commands.ArmsFinished;
import org.usfirst.frc.team4944.robot.commands.ArmsUp;
import org.usfirst.frc.team4944.robot.commands.CPFinished;
import org.usfirst.frc.team4944.robot.commands.CPStart;
import org.usfirst.frc.team4944.robot.commands.FeederBegin;
import org.usfirst.frc.team4944.robot.commands.FeederFinished;
import org.usfirst.frc.team4944.robot.commands.IntakeInit;
import org.usfirst.frc.team4944.robot.commands.IntakingFinished;
import org.usfirst.frc.team4944.robot.commands.OutTakeInit;
import org.usfirst.frc.team4944.robot.commands.OutTakingFinished;
import org.usfirst.frc.team4944.robot.commands.ShooterSpinDown;
import org.usfirst.frc.team4944.robot.commands.ShooterSpinUpInit;
import org.usfirst.frc.team4944.robot.commands.ShootingCommandGroup;
import org.usfirst.frc.team4944.robot.commands.ShootingFinished;
import org.usfirst.frc.team4944.robot.custom.ToggleCommand;
import org.usfirst.frc.team4944.robot.custom.TriggerCommand;
import org.usfirst.frc.team4944.robot.custom.XboxController;
import org.usfirst.frc.team4944.robot.subsystems.ShooterSubsystem;
import org.usfirst.frc.team4944.robot.subsystems.TurretSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class OI {
    XboxController driver, operator;
    boolean prevAButton, prevBButton, prevXButton, prevYButton, prevRBButton, prevLBButton, prevRTButton, prevLTButton;
    Command aCommandOn, aCommandOff, bCommandOn, bCommandOff, xCommandOn, xCommandOff, yCommandOn, yCommandOff;
    Command lbCommandOn, lbCommandOff, rbCommandOn, rbCommandOff, rtCommandOn, rtCommandOff, ltCommandOn, ltCommandOff;
    Command dpad0On, dpad0Off, dpad180On, dpad180Off, dpad270On, dpad270Off, dpad90On, dpad90Off;
    TurretSubsystem turret;
    ToggleCommand toggleA, toggleB, toggleX, toggleY, toggleRB, toggleLB, toggleRT, toggleLT;
    TriggerCommand leftTrigger, rightTrigger;
    ShooterSubsystem shooter;
    double shooterFPS, shooterPower;

    public OI() {

        // Controllers
        this.driver = new XboxController(0);
        this.operator = new XboxController(1);

        // Subsystem
        this.shooter = new ShooterSubsystem();

        // ShooterSpeed
        this.shooterPower = 0.5;

        // Button Toggles
        this.prevAButton = false;
        this.prevBButton = false;
        this.prevXButton = false;
        this.prevYButton = false;
        this.prevRBButton = false;
        this.prevLBButton = false;
        this.prevLTButton = false;
        // Commands

        // A
        this.aCommandOn = new CPStart(0.2);
        this.aCommandOff = new CPFinished();
        this.driver.addWhenHeldToA(this.aCommandOn);
        this.driver.addWhenReleasedToA(this.aCommandOff);

        // B
        this.bCommandOn = new IntakeInit(0.9);
        this.bCommandOff = new IntakingFinished();
        this.driver.addWhenHeldToB(this.bCommandOn);
        this.driver.addWhenReleasedToB(this.bCommandOff);

        // X
        this.xCommandOn = new OutTakeInit(-0.9, -0.5, -0.5);
        this.xCommandOff = new OutTakingFinished();
        this.driver.addWhenHeldToX(this.xCommandOn);
        this.driver.addWhenReleasedToX(this.xCommandOff);

        // Y
        this.yCommandOn = new FeederBegin(0.7, 0.7);
        this.yCommandOff = new FeederFinished();
        this.driver.addWhenHeldToY(this.yCommandOn);
        this.driver.addWhenReleasedToY(this.yCommandOff);

        // RB
        this.rbCommandOn = new ArmsUp(0.2);
        this.rbCommandOff = new ArmsFinished();
        this.driver.addWhenHeldToRightBumper(this.rbCommandOn);
        this.driver.addWhenReleasedToRightBumper(this.rbCommandOff);

        // LB
        this.lbCommandOn = new ArmsDown(0.1);
        this.lbCommandOff = new ArmsFinished();
        this.driver.addWhenHeldToLeftBumper(this.lbCommandOn);
        this.driver.addWhenReleasedToLeftBumper(this.lbCommandOff);
        ;
        // RT
        this.rtCommandOn = null;
        this.rtCommandOff = null;
        // this.driver.addWhenHeldToRightTrigger(this.rtCommandOn);
        // this.driver.addWhenReleasedToRightTrigger(this.rtCommandOff);

        // LT
        this.ltCommandOn = null;
        this.ltCommandOff = null;
        // this.driver.addWhenHeldToLeftTrigger(this.ltCommandOn);
        // this.driver.addWhenReleasedToRightTrigger(this.rtCommandOff);

        // Dpad Up
        this.dpad0On = null;
        this.dpad0Off = null;
        // this.driver.addWhenHeldToDpad0(this.dpad0On);
        // this.driver.addWhenReleasedToDpad0(this.dpad0Off);

        // Dpad Down
        this.dpad180On = null;
        this.dpad180Off = null;
        // this.driver.addWhenHeldToDpad180(this.dpad180On);
        // this.driver.addWhenReleasedToDpad0(this.dpad180Off);

        // Dpad Left
        this.dpad270On = null;
        this.dpad270Off = null;
        // this.driver.addWhenHeldToDpad270(this.dpad270On);
        // this.driver.addWhenReleasedToDpad270(this.dpad270Off);

        // Dpad Right
    }
}
