package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {

  private CANSparkMax intakeMotor;
  private CANSparkMax intakeElevMotor;

  public IntakeSubsystem() {
    intakeMotor = new CANSparkMax(0, MotorType.kBrushless); 
    intakeElevMotor = new CANSparkMax(0, MotorType.kBrushless);
  }

  public void intake(){
    intakeMotor.set(0.5);
  }

  public void outake(){
    intakeMotor.set(-0.5);
  }

  public double deadZone(double speed){
    if(Math.abs(speed) < 0.1){
      return 0;
    }
    else{
      return speed;
    }
  }

  public void manualIntake(double speed){
    intakeElevMotor.set(deadZone(speed));
  }

  @Override
  public void periodic() {
  }
}
