package org.usfirst.frc.team4944.robot;

import org.usfirst.frc.team4944.robot.commands.ArmsDown;
import org.usfirst.frc.team4944.robot.commands.ArmsFinished;
import org.usfirst.frc.team4944.robot.commands.ArmsUp;
import org.usfirst.frc.team4944.robot.commands.CPFinished;
import org.usfirst.frc.team4944.robot.commands.CPInit;
import org.usfirst.frc.team4944.robot.commands.IntakeInit;
import org.usfirst.frc.team4944.robot.commands.IntakingFinished;
import org.usfirst.frc.team4944.robot.commands.OutTakeInit;
import org.usfirst.frc.team4944.robot.commands.OutTakingFinished;
import org.usfirst.frc.team4944.robot.commands.QueueFinished;
import org.usfirst.frc.team4944.robot.commands.QueueInit;
import org.usfirst.frc.team4944.robot.commands.WenchFinished;
import org.usfirst.frc.team4944.robot.commands.WenchInit;
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
    Command lmCommandOn, lmCommandOff;
    TurretSubsystem turret;
    ToggleCommand toggleA, toggleB, toggleX, toggleY, toggleRB, toggleLB, toggleRT, toggleLT, toggleLM;
    TriggerCommand leftTrigger, rightTrigger;
    ShooterSubsystem shooter;
    double shooterFPS, shooterPower;

    public OI() {

        // Controllers

        this.driver = new XboxController(0);
        this.operator = new XboxController(1);

        // Button Toggles

        this.prevAButton = false;
        this.prevBButton = false;
        this.prevXButton = false;
        this.prevYButton = false;
        this.prevRBButton = false;
        this.prevLBButton = false;
        this.prevLTButton = false;

        // Commands

        // A -- CONTROL PANEL SPIN (DRIVER)
        this.aCommandOn = new CPInit(0.2);
        this.aCommandOff = new CPFinished();
        this.driver.addWhenHeldToA(this.aCommandOn);
        this.driver.addWhenReleasedToA(this.aCommandOff);

        // B -- INTAKE (DRIVER)
        this.bCommandOn = new IntakeInit(-0.9);
        this.bCommandOff = new IntakingFinished();
        this.driver.addWhenHeldToB(this.bCommandOn);
        this.driver.addWhenReleasedToB(this.bCommandOff);

        // X -- OUTAKE/HOPPER/BELT/SHOOTER (DRIVER)
        // Comp
        // Practice Negative/positive/Negative/Negative
        this.xCommandOn = new OutTakeInit(0.7, -0.5, -0.5, 0);
        this.xCommandOff = new OutTakingFinished();
        this.driver.addWhenHeldToX(this.xCommandOn);
        this.driver.addWhenReleasedToX(this.xCommandOff);

        // Y -- QUEUER (DRIVER)
        // PRactice positive/negative
        this.yCommandOn = new QueueInit(0.7, 0.7);
        this.yCommandOff = new QueueFinished();
        this.driver.addWhenHeldToY(this.yCommandOn);
        this.driver.addWhenReleasedToY(this.yCommandOff);

        // RB -- CONTROL PANEL HEIGHT (DRIVER)
        this.rbCommandOn = new ArmsUp(0.4);
        this.rbCommandOff = new ArmsFinished();
        this.driver.addWhenHeldToRightBumper(this.rbCommandOn);
        this.driver.addWhenReleasedToRightBumper(this.rbCommandOff);

        // LB -- INTAKE HEIGHT (DRIVER)
        this.lbCommandOn = new ArmsDown(0.2);
        this.lbCommandOff = new ArmsFinished();
        this.driver.addWhenHeldToLeftBumper(this.lbCommandOn);
        this.driver.addWhenReleasedToLeftBumper(this.lbCommandOff);

        // RT -- CLIMB UP (OPERATOR)
        this.rtCommandOn = new ArmsUp(0.2);
        this.rtCommandOff = new ArmsFinished();
        this.operator.addWhenHeldRightTrigger(this.rtCommandOn);
        this.operator.addWhenReleasedToRightTrigger(this.rtCommandOff);

        // LT -- CLIMB DOWN (OPERATOR)
        this.ltCommandOn = new ArmsDown(0.2);
        this.ltCommandOff = new ArmsFinished();
        this.operator.addWhenHeldToLeftTrigger(this.ltCommandOn);
        this.operator.addWhenReleasedToRightTrigger(this.rtCommandOff);

        // Y -- WENCH FAST (OPERATOR)
        this.yCommandOn = new WenchInit(0.9);
        this.yCommandOff = new WenchFinished();
        this.operator.addWhenHeldToY(this.yCommandOn);
        this.operator.addWhenReleasedToY(this.yCommandOff);

        // X -- WENCH SLOW (OPERATOR)
        this.xCommandOn = new WenchInit(0.6);
        this.xCommandOff = new WenchFinished();
        this.operator.addWhenHeldToX(this.xCommandOn);
        this.operator.addWhenReleasedToX(this.xCommandOff);

        // LM -- WENCH REVERSE (OPERATOR)
        this.lmCommandOn = new WenchInit(-0.5);
        this.lmCommandOff = new WenchFinished();
        this.operator.addWhenHeldToLeftMenu(this.lmCommandOn);
        this.operator.addWhenReleasedToLeftMenu(this.lmCommandOff);
    }
}
// Trains Are Cool
