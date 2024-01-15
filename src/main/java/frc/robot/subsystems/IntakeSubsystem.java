package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {

  private CANSparkMax intakeMotor;

  public IntakeSubsystem() {
    intakeMotor = new CANSparkMax(0, MotorType.kBrushless); 
  }

  @Override
  public void periodic() {
  }
}
