package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import frc.robot.Constants;
import edu.wpi.first.math.controller.BangBangController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterFlyWheel extends SubsystemBase{

    //left is follower
    private final TalonFX leftTalon;
    private final TalonFX  rightTalon;

    //private final CANcoder encoder;

    public ShooterFlyWheel(){
        //Motor controlers for left and right sides of shooter
        leftTalon = new TalonFX(Constants.ShooterFlyWheelConstants.kLeftCanID);
        rightTalon = new TalonFX(Constants.ShooterFlyWheelConstants.kRightCanID);

        //CANcoder
        //encoder = new CANcoder(Constants.ShooterFlyWheelConstants.kCANcoderID);

        //set mode to coast to prevent damage n stuff
        leftTalon.setNeutralMode(NeutralModeValue.Coast);
        rightTalon.setNeutralMode(NeutralModeValue.Coast);

        TalonFXConfiguration configs = new TalonFXConfiguration();
        //right motor is clockwise
        configs.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        rightTalon.getConfigurator().apply(configs);

        //Ensure left is following right
        leftTalon.setControl(new Follower(Constants.ShooterFlyWheelConstants.kRightCanID, true));
    }

    public void shoot(double setPoint){
        //creates a new Bang Bang controller
        final BangBangController controller = new BangBangController();
        // Controls a motor with the output of the BangBang controller
        leftTalon.set(controller.calculate(leftTalon.getVelocity().getValueAsDouble(), setPoint));
    }
}
