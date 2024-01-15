package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {

  private CANSparkMax elevMotor;
  private RelativeEncoder enc;

  public ElevatorSubsystem() {
    elevMotor = new CANSparkMax(0, MotorType.kBrushless);
    enc = elevMotor.getEncoder();
  }

  public void ManualElevator(double speed) {
    elevMotor.set(deadzone(speed));
  }

  public double deadzone(double speed) {
    if (Math.abs(speed) < 0.1) {
      return 0;
    }
    else {
      return speed;
    }
  }

  @Override
  public void periodic() {
  }
}
