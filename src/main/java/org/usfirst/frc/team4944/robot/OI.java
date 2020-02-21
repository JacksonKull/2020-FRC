package org.usfirst.frc.team4944.robot;

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
import org.usfirst.frc.team4944.robot.commands.UnstickBall;
import org.usfirst.frc.team4944.robot.commands.UnstickBallFinish;
import org.usfirst.frc.team4944.robot.commands.UnstickBallInit;
import org.usfirst.frc.team4944.robot.custom.ToggleCommand;
import org.usfirst.frc.team4944.robot.custom.TriggerCommand;
import org.usfirst.frc.team4944.robot.custom.XboxController;
import org.usfirst.frc.team4944.robot.subsystems.ShooterSubsystem;
import org.usfirst.frc.team4944.robot.subsystems.TurretSubsystem;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;

public class OI {
    XboxController driver, operator;
    boolean prevAButton, prevBButton, prevXButton, prevYButton, prevRBButton, prevLBButton, prevRTButton, prevLTButton;
    Command aCommandOn, aCommandOff, bCommandOn, bCommandOff, xCommandOn, xCommandOff, yCommandOn, yCommandOff, lbCommandOn, lbCommandOff, rbCommandOn, rbCommandOff, rtCommandOn, rtCommandOff, ltCommandOn, ltCommandOff;
    TurretSubsystem turret;
    ToggleCommand toggleA, toggleB, toggleX, toggleY, toggleRB, toggleLB, toggleRT, toggleLT;
    TriggerCommand leftTrigger, rightTrigger;
    ShooterSubsystem shooter;
    double shooterFPS, shooterPower;

    public OI() {
        //Controllers
        this.driver = new XboxController(0);
        this.operator = new XboxController(1);
        //Subsystem
        this.shooter = new ShooterSubsystem();
        //ShooterSpeed
        this.shooterPower = 0.5;
        //Button Toggles
        this.prevAButton = false;
        this.prevBButton = false;
        this.prevXButton = false;
        this.prevYButton = false;
        this.prevRBButton = false;
        this.prevLBButton = false;
        this.prevLTButton = false;
        //Toggle Commands
        //Toggle A
        this.aCommandOn = null;
        this.aCommandOff = null;
        //this.toggleA = new ToggleCommand(this.aCommandOn, this.aCommandOff);
        
        //Toggle B
        this.bCommandOn = new IntakeInit(0.9);
        this.bCommandOff = new IntakingFinished();
        this.toggleB = new ToggleCommand(this.bCommandOn, this.bCommandOff);
        this.driver.addCommandToB(this.toggleB);
        
        //Toggle X
        this.xCommandOn = new OutTakeInit(-0.9);
        this.xCommandOff = new OutTakingFinished();
        this.toggleX = new ToggleCommand(this.xCommandOn, this.xCommandOff);
        this.driver.addCommandToX(this.toggleX);

        //Toggle Y
        this.yCommandOn = new FeederBegin(0.7, 0.7);
        this.yCommandOff = new FeederFinished();
        this.driver.addWhenHeldToY(this.yCommandOn);
        this.driver.addWhenReleasedToY(this.yCommandOff);
        // this.toggleY = new ToggleCommand(this.yCommandOn, this.yCommandOff);
        // this.driver.addCommandToY(this.toggleY);
        
        //Toggle RB
        this.rbCommandOn = null;
        this.rbCommandOff = null;
        //this.toggleRB = new ToggleCommand(this.rbCommandOn, this.rbCommandOff);
        //this.driver.addCommandToRightBumper(this.toggleRB);
        
        //Toggle LB
        this.lbCommandOn = null;
        this.lbCommandOff = null;
        //this.toggleLB = new ToggleCommand(this.lbCommandOn, this.lbCommandOff);
        //this.driver.addCommandToLeftBumper(this.toggleLB);
        
        //Toggle RT
        this.rtCommandOn = null;
        this.rtCommandOff = null;
        //this.toggleRT = new ToggleCommand(this.rtCommandOn, this.rtCommandOff);
        //this.driver.addCommandToRightTrigger(this.toggleRT);

        //Toggle LT
        //this.ltCommandOn = new ShooterSpinUpInit(this.shooter.getRequiredVelocity());
        //this.ltCommandOn = new ShooterSpinUpInit(this.shooterPower);
        //this.ltCommandOff = new ShooterSpinDown();        
        //this.toggleLT = new ToggleCommand(this.ltCommandOn, this.ltCommandOff);
        //this.driver.addCommandToLeftTrigger(this.toggleLT);
    }

    public void updateCommands(){
    
    }
}
