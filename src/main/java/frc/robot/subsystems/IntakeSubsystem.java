package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {

  private CANSparkMax intakeMotor;
  private RelativeEncoder enc;

  public IntakeSubsystem() {
    intakeMotor = new CANSparkMax(0, MotorType.kBrushless); 
    enc = intakeMotor.getEncoder();
    
  }

  @Override
  public void periodic() {
  }
}
