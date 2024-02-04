package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import frc.robot.Constants.IntakeConstants;

public class UnderIntakeSubsystem extends SubsystemBase {
  
  private CANSparkMax intakeMotor;
  private DigitalInput opticalSensor;

  public UnderIntakeSubsystem() {
    intakeMotor = new CANSparkMax(IntakeConstants.INTAKE_PORT2, MotorType.kBrushless);
    opticalSensor = new DigitalInput(IntakeConstants.INTAKE_OPTICAL_PORT);
  }

  public boolean getOpticalSensor(){
    return !opticalSensor.get();
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
    SmartDashboard.putBoolean("[I] Optical Sensor", getOpticalSensor());
    SmartDashboard.putNumber("[I] Speed", intakeMotor.get());
  }
}
