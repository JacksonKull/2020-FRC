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

    public OI() {
        //Controllers
        this.driver = new XboxController(0);
        this.operator = new XboxController(1);
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
        this.xCommandOn = null;
        this.xCommandOff = null;
        //this.toggleX = new ToggleCommand(this.xCommandOn, this.xCommandOff);
        //this.driver.addCommandToX(this.toggleX);

        //Toggle Y
        this.yCommandOn = new OutTakeInit(-0.9);
        this.yCommandOff = new OutTakingFinished();
        this.toggleY = new ToggleCommand(this.yCommandOn, this.yCommandOff);
        this.driver.addCommandToY(this.toggleY);
        
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
        this.rtCommandOn = new FeederBegin(0.5, 0.5);
        this.rtCommandOff = new FeederFinished();
        this.toggleRT = new ToggleCommand(this.rtCommandOn, this.rtCommandOff);
        this.driver.addCommandToRightTrigger(this.toggleRT);

        //Toggle LT
        this.ltCommandOn = new ShooterSpinUpInit(1.0);
        this.ltCommandOff = new ShooterSpinDown();        
        this.toggleLT = new ToggleCommand(this.ltCommandOn, this.ltCommandOff);
        this.driver.addCommandToLeftTrigger(this.toggleLT);
    }

    public void updateCommands(){
        // //Toggles A Button
		// if(this.driver.getAButton() && !this.prevAButton){this.driver.toggleAButton();this.prevAButton = true;}
        // else if(!this.driver.getAButton() && this.prevAButton){this.prevAButton = false;}
        // //Toggles B Button
		// if(this.driver.getBButton() && !this.prevBButton){this.driver.toggleBButton();this.prevBButton = true;}
        // else if(!this.driver.getBButton() && this.prevBButton){this.prevBButton = false;this.driver.toggleBButton();}
        // //Toggles X Button 
        // if(this.driver.getXButton() && !this.prevXButton){this.driver.toggleXButton();this.prevXButton = true;}
        // else if(!this.driver.getXButton() && this.prevXButton){this.prevXButton = false;}
        // //Toggles Y Button
        // if(this.driver.getYButton() && !this.prevYButton){this.driver.toggleYButton();this.prevYButton = true;}
        // else if(!this.driver.getYButton() && this.prevYButton){this.prevYButton = false;}
        // //Toggles RB Button
        // if(this.driver.getRightBumper() && !this.prevRBButton){this.driver.toggleRBButton();this.prevRBButton = true;}
        // else if(!this.driver.getRightBumper() && this.prevRBButton){this.prevRBButton = false;}
        // //Toggles LB Button
        // if(this.driver.getLeftBumper() && !this.prevLBButton){this.driver.toggleLBButton();this.prevLBButton = true;}
        // else if(!this.driver.getLeftBumper() && this.prevLBButton){this.prevLBButton = false;}
        // //Toggles RT Button
        // // if(this.driver.getRightTriggerDown() && !this.prevRTButton){this.driver.toggleRTButton();this.prevRTButton = true;}
        // // else if(!this.driver.getRightTriggerDown() && !this.prevRTButton){this.prevRTButton = false;}
        // // //Toggles LT Button
        // // if(this.driver.getLeftTriggerDown() && !this.prevLTButton){this.driver.toggleLTButton();this.prevLTButton = true;}
        // // else if(!this.driver.getLeftTriggerDown() && !this.prevLTButton){this.prevLTButton = false;}
        
        // //Toggle A Commands
        // if(!(this.driver.getAToggle())){
        //     this.driver.addWhenHeldToA(this.aCommandOn);
        // }else if(!this.driver.getAToggle()){
        //     this.driver.addWhenReleasedToA(this.aCommandOff);
		// }
        // //Toggle B Commands
        // // if(this.driver.getBToggle() && this.prevBButton){
        // //     this.driver.addCommandToB(this.bCommandOn);
        // // }else if(this.driver.getBToggle() && !this.prevBButton){
        // //     this.driver.addWhenReleasedToB(this.bCommandOff);
        // // }
        // this.driver.addWhenPressed(new ToggleCommand(this.bCommandOn, this.bCommandOff));
        // //Toggle X Commands
        // if(!(this.driver.getXToggle())){
        //     this.driver.addWhenHeldToX(this.xCommandOn);
        // }else if(!this.driver.getXToggle()){
        //     this.driver.addWhenReleasedToX(this.xCommandOff);
		// }
        // //Toggle Y Commands
        // if(!(this.driver.getYToggle())){
        //     this.driver.addWhenHeldToY(this.yCommandOn);
        // }else if(!this.driver.getYToggle()){
        //     this.driver.addWhenReleasedToY(this.yCommandOff);
        // }
        // //Toggle RB Commands
        // if(!(this.driver.getRBToggle())){
        //     this.driver.addWhenHeldToRightBumper(this.rbCommandOn);
        // }else if(!this.driver.getRBToggle()){
        //     this.driver.addWhenReleasedToRightBumper(this.rbCommandOff);
        // }
        // //Toggle LB Commands
        // if(!(this.driver.getLBToggle())){
        //     this.driver.addWhenHeldToLeftBumper(this.lbCommandOn);
        // }else if(!this.driver.getLBToggle()){
        //     this.driver.addWhenReleasedToLeftBumper(this.lbCommandOff);
        // }
        // //Toggle RT Commands
        // if(this.driver.getRightTriggerDown() && !prevRTButton){
        //     this.rtCommandOn.start();
        //     this.turret.followLimelightNoEncoder();
        // }else if(!this.driver.getRightTriggerDown() && prevRTButton){
        //     this.rtCommandOff.start();
        //     if(this.driver.getRightBumper()){
        //         this.turret.setTurretMotorPower(0.5);
        //     }else if(this.driver.getLeftBumper()){
        //         this.turret.setTurretMotorPower(-0.5);
        //     }else{
        //         this.turret.setTurretMotorPower(0.0);
        //     }
        // }
        // //Toggle LT Commands
        // if(this.driver.getLeftTriggerDown() && !prevLTButton){
        //     this.ltCommandOn.start();
        // }else if(!this.driver.getLeftTriggerDown() && prevLTButton){
        //     this.ltCommandOff.start();
        // }
         
    }
}
