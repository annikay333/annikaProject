package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
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

        //CANcoder on hood axle
        encoder = new CANcoder(Constants.ShooterHoodConstants.kCANcoderID);

        //Stator Current Limit to prevent stripping hood gears or other damage if hood runs into something
        configs = new TalonFXConfiguration();
        configs.CurrentLimits.StatorCurrentLimit = Constants.ShooterHoodConstants.kCurrentLimitHood;
        talon.getConfigurator().apply(configs);
    }    

    public void setPosition(double setPoint){
        //calculate pid value and use it to drive motor to correct angle
        talon.set(pid.calculate(encoder.getPosition().getValueAsDouble(),setPoint));
    }

}
