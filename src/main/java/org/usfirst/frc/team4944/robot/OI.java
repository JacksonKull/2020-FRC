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

        if(!(this.getDriverAButtonToggle)){
            this.driver.addCommandToA(new ShooterSpinUpInit(0.75));
        }else if(this.getDriverAButtonToggle){
            this.driver.addWhenReleasedToA(new ShooterSpinDown());
        }

        this.driver.addCommandToB(new IntakeInit(0.9));
        this.driver.addWhenReleasedToB(new IntakingFinished());

        this.driver.addCommandToX(new UnstickBall());

        this.driver.addCommandToY(new FeederBegin(0.5, 0.5));
        this.driver.addWhenReleasedToY(new FeederFinished());

        /*
        driver.addCommandToA(new ShootingCommandGroup());
        driver.addWhenReleasedToA(new ShootingFinished());

        driver.addCommandToB(new IntakeInit(1));
        driver.addWhenReleasedToB(new IntakingFinished());
        */
    }

    public void setDriverAButton(boolean position){
        this.getDriverAButtonToggle = position;
    }

    public boolean getDriverAButtonToggle(){
        return this.getDriverAButtonToggle;
    }

}
