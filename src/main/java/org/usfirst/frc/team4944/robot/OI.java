package org.usfirst.frc.team4944.robot;

import org.usfirst.frc.team4944.robot.commands.FeederBegin;
import org.usfirst.frc.team4944.robot.commands.FeederFinished;
import org.usfirst.frc.team4944.robot.commands.IntakeInit;
import org.usfirst.frc.team4944.robot.commands.IntakingFinished;
import org.usfirst.frc.team4944.robot.commands.ShooterSpinDown;
import org.usfirst.frc.team4944.robot.commands.ShooterSpinUpInit;
import org.usfirst.frc.team4944.robot.commands.ShootingCommandGroup;
import org.usfirst.frc.team4944.robot.commands.ShootingFinished;
import org.usfirst.frc.team4944.robot.commands.UnstickBall;
import org.usfirst.frc.team4944.robot.commands.UnstickBallFinish;
import org.usfirst.frc.team4944.robot.commands.UnstickBallInit;
import org.usfirst.frc.team4944.robot.custom.XboxController;

public class OI {
    XboxController driver;
    XboxController operator;
    boolean getDriverAButtonToggle = false;

    public OI() {
        this.driver = new XboxController(0);
        this.operator = new XboxController(1);
        
        //A Button Shooter Control
        //this.driver.addWhenHeldToA(new ShooterSpinUpInit(0.75));
        //this.driver.addWhenReleasedToA(new ShooterSpinDown());
        
        //B Button Intake Control
        //this.driver.addWhenHeldToB(new IntakeInit(0.9));
        //this.driver.addWhenReleasedToB(new IntakingFinished());
        
        //X Button Ustick Ball
        this.driver.addCommandToX(new UnstickBall());
        
        //Y Button Feeder Control
        this.driver.addWhenHeldToY(new FeederBegin(0.5, 0.5));
        this.driver.addWhenReleasedToY(new FeederFinished());

        /*
        driver.addCommandToA(new ShootingCommandGroup());
        driver.addWhenReleasedToA(new ShootingFinished());

        driver.addCommandToB(new IntakeInit(1));
        driver.addWhenReleasedToB(new IntakingFinished());
        */
    }
}
