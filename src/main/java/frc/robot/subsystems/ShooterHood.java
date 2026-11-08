package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.hardware.CANcoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterHood extends SubsystemBase{

    private final TalonFX  talon;
    private final TalonFXConfiguration configs;

    private final PIDController pid;
    private final CANcoder encoder;

    public ShooterHood(){
        talon = new TalonFX(Constants.ShooterHoodConstants.kCanID);

        pid = new PIDController(Constants.ShooterHoodConstants.kkP, Constants.ShooterHoodConstants.kkI, Constants.ShooterHoodConstants.kkD);

        //CANcoder
        encoder = new CANcoder(Constants.ShooterHoodConstants.kCANcoderID);

        configs = new TalonFXConfiguration();
        talon.getConfigurator().apply(configs);
    }    

    public void moveHood(double setPoint, boolean movingUp){
        if (movingUp){
            //should be clockwise?
            configs.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        }else{
            //should be ccw?
            configs.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        }

        talon.getConfigurator().apply(configs);

        talon.set(pid.calculate(encoder.getPosition().getValueAsDouble(),setPoint));
    }

    public void stop(){
        talon.set(0);
    }
}
