package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {

  private CANSparkMax elevMotor;

  public ElevatorSubsystem() {
    elevMotor = new CANSparkMax(0, MotorType.kBrushless);
  }

  @Override
  public void periodic() {
  }
}
