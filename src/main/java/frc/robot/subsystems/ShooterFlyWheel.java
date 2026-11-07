package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterFlyWheel extends SubsystemBase{

    //left is follower
    private final TalonFX leftTalon;
    private final TalonFX  rightTalon;

    public ShooterFlyWheel(){
        //Motor controlers for left and right sides of shooter
        leftTalon = new TalonFX(Constants.ShooterFlyWheelConstants.kLeftCanID);
        rightTalon = new TalonFX(Constants.ShooterFlyWheelConstants.kRightCanID);

        TalonFXConfiguration configs = new TalonFXConfiguration();
        //right motor is clockwise
        configs.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        rightTalon.getConfigurator().apply(configs);

        //Ensure left is following right
        leftTalon.setControl(new Follower(Constants.ShooterFlyWheelConstants.kRightCanID, true));
    }
}
