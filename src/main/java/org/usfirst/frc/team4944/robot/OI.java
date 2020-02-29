package org.usfirst.frc.team4944.robot;


import org.usfirst.frc.team4944.robot.commands.ArmsDown;
import org.usfirst.frc.team4944.robot.commands.ArmsFinished;
import org.usfirst.frc.team4944.robot.commands.ArmsUp;
import org.usfirst.frc.team4944.robot.commands.CPFinished;
import org.usfirst.frc.team4944.robot.commands.CPStart;
import org.usfirst.frc.team4944.robot.commands.ControllerMode;
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
import org.usfirst.frc.team4944.robot.commands.ControllerMode;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class OI extends Subsystem{
    XboxController driver, operator;
    boolean prevAButton, prevBButton, prevXButton, prevYButton, prevRBButton, prevLBButton, prevRTButton, prevLTButton;
    Command aCommandOn, aCommandOff, bCommandOn, bCommandOff, xCommandOn, xCommandOff, yCommandOn, yCommandOff;
    Command lbCommandOn, lbCommandOff, rbCommandOn, rbCommandOff, rtCommandOn, rtCommandOff, ltCommandOn, ltCommandOff;
    Command dpad0On, dpad0Off, dpad180On, dpad180Off, dpad270On, dpad270Off, dpad90On, dpad90Off, lmCommand;
    TurretSubsystem turret;
    ToggleCommand toggleA, toggleB, toggleX, toggleY, toggleRB, toggleLB, toggleRT, toggleLT, toggleLM;
    TriggerCommand leftTrigger, rightTrigger;
    ShooterSubsystem shooter;
    double shooterFPS, shooterPower;
    Boolean controlMode;

    public OI(){

        // Controllers
        this.driver = new XboxController(0);
        this.operator = new XboxController(0);

        // Controller Mode
        this.controlMode = true;

        // Button Toggles
        this.prevAButton = false;
        this.prevBButton = false;
        this.prevXButton = false;
        this.prevYButton = false;
        this.prevRBButton = false;
        this.prevLBButton = false;
        this.prevLTButton = false;
        
    }

    // Controller Mode 0
    // Drive/CP/Intake/etc...
    // Controller Mode 1
    // Climber
    // True = 0/False = 1
    public Boolean getControlMode() {
        return this.controlMode;
    }

    public void setControlMode(Boolean mode) {
        this.controlMode = mode;
        System.out.println("Setting ContrlMode " + mode);
    }


    public void setupControlModeOne(boolean mode) {
        // A
        this.aCommandOn = new CPStart(0.2, mode);
        this.aCommandOff = new CPFinished();
        this.driver.addWhenHeldToA(this.aCommandOn);
        this.driver.addWhenReleasedToA(this.aCommandOff);

        // B
        this.bCommandOn = new IntakeInit(0.9);
        this.bCommandOff = new IntakingFinished();
        this.driver.addWhenHeldToB(this.bCommandOn);
        this.driver.addWhenReleasedToB(this.bCommandOff);

        // X
        this.xCommandOn = new OutTakeInit(-0.9, -0.5, -0.5, -0.3);
        this.xCommandOff = new OutTakingFinished();
        this.driver.addWhenHeldToX(this.xCommandOn);
        this.driver.addWhenReleasedToX(this.xCommandOff);

        // Y
        this.yCommandOn = new FeederBegin(0.7, 0.7);
        this.yCommandOff = new FeederFinished();
        this.driver.addWhenHeldToY(this.yCommandOn);
        this.driver.addWhenReleasedToY(this.yCommandOff);

        // RB
        this.rbCommandOn = new ArmsUp(0.4);
        this.rbCommandOff = new ArmsFinished();
        this.driver.addWhenHeldToRightBumper(this.rbCommandOn);
        this.driver.addWhenReleasedToRightBumper(this.rbCommandOff);

        // LB
        this.lbCommandOn = new ArmsDown(0.2);
        this.lbCommandOff = new ArmsFinished();
        this.driver.addWhenHeldToLeftBumper(this.lbCommandOn);
        this.driver.addWhenReleasedToLeftBumper(this.lbCommandOff);

        // RT -- Only Active on Control Mode 2
        this.rtCommandOn = new ArmsUp(0.4);
        this.rtCommandOff = new ArmsFinished();
        this.driver.addWhenHeldToRightTrigger(this.rtCommandOn);
        this.driver.addWhenReleasedToRightTrigger(this.rtCommandOff);

        // LT -- Only Active on Control Mode 2
        this.ltCommandOn = new ArmsDown(0.2);
        this.ltCommandOff = new ArmsFinished();
        this.driver.addWhenHeldToLeftTrigger(this.ltCommandOn);
        this.driver.addWhenReleasedToLeftTrigger(this.ltCommandOff);

    }

    public void oiIinit(){
        // LM
        this.lmCommand = new ControllerMode();
        this.driver.addCommandToLeftMenu(this.lmCommand);
        this.setupControlModeOne(this.controlMode);
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }
}
