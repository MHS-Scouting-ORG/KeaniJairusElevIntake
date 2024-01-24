package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {

  private CANSparkMax intakeMotor;
  private CANSparkMax intakePivotMotor;
  private double maxSpeed;
  private RelativeEncoder rEnc;
  private PIDController pid;  
  private boolean pidOn = false;
  private double encoderVal;
  private double setpoint;

  public IntakeSubsystem() {
    intakeMotor = new CANSparkMax(IntakeConstants.INTAKE_PORT, MotorType.kBrushless); 
    intakePivotMotor = new CANSparkMax(IntakeConstants.INTAKEPIVOT_PORT, MotorType.kBrushless);
    intakeMotor.setIdleMode(IdleMode.kCoast);
    intakePivotMotor.setIdleMode(IdleMode.kBrake);
    maxSpeed = 0.5;
    rEnc = intakePivotMotor.getEncoder();
    pid = new PIDController(IntakeConstants.INTAKEPIVOT_KP, IntakeConstants.INTAKEPIVOT_KI, IntakeConstants.INTAKEPIVOT_KD);
  }

  //////////////////////////
  //   Accessor Methods   //
  //////////////////////////

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

  public void stopIntake(){
    intakeMotor.stopMotor();
  }

  public void stopPivotIntake(){
    intakePivotMotor.stopMotor();
  }

  /////////////////////
  //   PID Methods   //
  /////////////////////

  public void turnPIDOn(){
    pidOn = true;
  }

  public void turnPIDOff(){
    pidOn = false;
  }

  public boolean isPIDOn(){
    return pidOn;
  }

  public boolean isAtSetpoint(){
    double error = setpoint - getEnc();
    return Math.abs(error) <= 2;
  }

  public void newSetpoint(double setpoint){
    this.setpoint = setpoint;
  }
  
  @Override
  public void periodic() {
    encoderVal = getEnc();
    double pidSpeed = 0;
    double currentError = pid.getPositionError();
    double prevError = pid.getPositionError();

    if(currentError > 0 && prevError < 0){
      pid.reset();
    }
    else if(currentError < 0 && prevError > 0){
      pid.reset();
    }

    if(pidOn){
      pidSpeed = pid.calculate(encoderVal, setpoint);
    }
    else{
      pidSpeed = maxSpeed;
    }
    if(pidSpeed > 0.5){
      pidSpeed = 0.5;
    }
    else if(pidSpeed < -0.5){
      pidSpeed = -0.5;
    }
    intakePivotMotor.set(pidSpeed);

    SmartDashboard.putNumber("Intake Pivot Encoder", getEnc());
    SmartDashboard.putNumber("Intake Pivot Setpoint", setpoint);
    SmartDashboard.putBoolean("Intake Pivot PID", isPIDOn());
    SmartDashboard.putNumber("Intake Pivot Position Error", pid.getPositionError());
  }
}