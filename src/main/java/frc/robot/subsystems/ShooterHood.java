package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.hardware.TalonFXS;
import com.ctre.phoenix6.signals.InvertedValue;

import frc.robot.Constants;

public class ShooterHood {

    private final TalonFX  talon;

    public ShooterHood(){
        talon = new TalonFX(Constants.ShooterHoodConstants.kCanID);

        TalonFXConfiguration configs = new TalonFXConfiguration();
        //should be clockwise?
        configs.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        talon.getConfigurator().apply(configs);

        
    }    
}
