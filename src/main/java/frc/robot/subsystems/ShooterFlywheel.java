package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import frc.robot.Constants;
import edu.wpi.first.math.controller.BangBangController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterFlywheel extends SubsystemBase{

    //left is follower
    private final TalonFX leftTalon;
    private final TalonFX  rightTalon;
    private final BangBangController controller;


    public ShooterFlywheel(){

        leftTalon = new TalonFX(Constants.ShooterFlywheelConstants.kLeftCanID);
        rightTalon = new TalonFX(Constants.ShooterFlywheelConstants.kRightCanID);
        controller = new BangBangController();


        //set mode to coast to prevent damage by allowing wheels to speed up and slow naturally
        leftTalon.setNeutralMode(NeutralModeValue.Coast);
        rightTalon.setNeutralMode(NeutralModeValue.Coast);

        TalonFXConfiguration configs = new TalonFXConfiguration();

        //set current limit to prevent brownout/tipping braker
        configs.CurrentLimits.StatorCurrentLimit = Constants.ShooterFlywheelConstants.kCurrentLimitFlywheel;

        rightTalon.getConfigurator().apply(configs);
        leftTalon.getConfigurator().apply(configs);

        //Ensure left motor is following right and inverted so they spin in opposite directions
        leftTalon.setControl(new Follower(Constants.ShooterFlywheelConstants.kRightCanID, true));
    }

    public void shoot(double setPoint){
        // Motor to 100% power until it reaches target rotations per second
        rightTalon.set(controller.calculate(rightTalon.getVelocity().getValueAsDouble(), setPoint));
    }

    public void stop(){
        rightTalon.set(0);
    }
}
