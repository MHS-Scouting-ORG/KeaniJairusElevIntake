package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {

  private CANSparkMax intakeMotor;
  private CANSparkMax intakePivotMotor;
  private double maxSpeed;
  private RelativeEncoder rEnc;

  public IntakeSubsystem() {
    intakeMotor = new CANSparkMax(IntakeConstants.INTAKE_PORT, MotorType.kBrushless); 
    intakePivotMotor = new CANSparkMax(IntakeConstants.INTAKEPIVOT_PORT, MotorType.kBrushless);
    intakeMotor.setIdleMode(IdleMode.kCoast);
    intakePivotMotor.setIdleMode(IdleMode.kBrake);
    maxSpeed = 0.5;
    rEnc = intakePivotMotor.getEncoder();
  }

  public double getEnc(){
    return rEnc.getPosition();
  }

  //////////////////////////
  //   Movement Methods   //
  //////////////////////////

  public void intake(){
    intakeMotor.set(maxSpeed);
  }

  public void outtake(){
    intakeMotor.set(-maxSpeed);
  }

  //Deadzone for the Xbox Controller and sets a max speed of 0.5 in both directions
  public double deadZone(double speed){
    if(Math.abs(speed) < 0.1){
      return 0;
    }
    else if(speed > maxSpeed){
      return maxSpeed;
    }
    else if(speed < -maxSpeed){
      return -maxSpeed;   
    }
    else{
      return speed;
    }
  }

  
  public void manualIntake(double speed){
    intakePivotMotor.set(deadZone(speed));
  }

  public void setPos(double enc, double speed, double toll){
    if(enc > 0){
      if(getEnc() < enc - toll){
        intakePivotMotor.set(speed);
      }
      else{
        stopPivotIntake();
      }
    }
    else{
      if(getEnc() > enc - toll){
        intakePivotMotor.set(-speed);
      }
      else{
        stopPivotIntake();
      }
    }
  }

  public void stopIntake(){
    intakeMotor.stopMotor();
  }

  public void stopPivotIntake(){
    intakePivotMotor.stopMotor();
  }

  @Override
  public void periodic() {
  }
}