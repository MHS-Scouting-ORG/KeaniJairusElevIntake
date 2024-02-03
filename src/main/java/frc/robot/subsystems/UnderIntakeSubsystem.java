package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants.IntakeConstants;

public class UnderIntakeSubsystem extends SubsystemBase {
  
  private CANSparkMax intakeMotor;

  public UnderIntakeSubsystem() {
    intakeMotor = new CANSparkMax(IntakeConstants.INTAKE_PORT2, MotorType.kBrushless);
  }

  public void intake(){
    intakeMotor.set(IntakeConstants.INTAKE_MAXSPEED);
  }

  public void outtake(){
    intakeMotor.set(-IntakeConstants.INTAKE_MAXSPEED);
  }

  public void stopIntake(){
    intakeMotor.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
