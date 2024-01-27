package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
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
  private double pidSpeed;
  private DigitalInput restingLimitSwitch;
  private DigitalInput intakingLimitSwitch;

  public IntakeSubsystem() {
    intakeMotor = new CANSparkMax(IntakeConstants.INTAKE_PORT, MotorType.kBrushless); 
    intakePivotMotor = new CANSparkMax(IntakeConstants.INTAKEPIVOT_PORT, MotorType.kBrushless);
    intakeMotor.setIdleMode(IdleMode.kCoast);
    intakePivotMotor.setIdleMode(IdleMode.kBrake);
    maxSpeed = 0.5;
    pidSpeed = 0;
    rEnc = intakePivotMotor.getEncoder();
    pid = new PIDController(IntakeConstants.INTAKEPIVOT_KP, IntakeConstants.INTAKEPIVOT_KI, IntakeConstants.INTAKEPIVOT_KD);
    restingLimitSwitch = new DigitalInput(IntakeConstants.INTAKE_RESTING_LS_PORT);
    intakingLimitSwitch = new DigitalInput(IntakeConstants.INTAKE_INTAKING_LS_PORT);
    pid.setTolerance(encoderVal);
  }

  //////////////////////////
  //   Accessor Methods   //
  //////////////////////////

  public double getEnc(){
    return rEnc.getPosition();
  }

  public boolean getRestingLS(){
    return restingLimitSwitch.get();
  }

  public boolean getIntakingLS(){
    return intakingLimitSwitch.get();
  }

  //////////////////////////
  //   Intaking Methods   //
  //////////////////////////

  public void intake(){
    intakeMotor.set(maxSpeed);
  }

  public void outtake(){
    intakeMotor.set(-maxSpeed);
  }
  
  public void stopIntake(){
    intakeMotor.stopMotor();
  }
  ////////////////////////
  //  Pivoting Methods  //
  ////////////////////////

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

    if (getRestingLS() && speed > 0){
      stopPivotIntake();
    }
    // else if (getIntakingLS() && speed < 0){
    //   stopPivotIntake();
    // }
    else {
      intakePivotMotor.set(deadZone(speed));
    }
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

  public boolean isAtSetpoint(){
    double error = getEnc() - setpoint;
    return Math.abs(error) < 2;
  }

  public boolean isPIDOn(){
    return pidOn;
  }

  public void newSetpoint(double setpoint){
    this.setpoint = setpoint;
  }
  
  @Override
  public void periodic() {

    if(pidOn){
      pidSpeed = pid.calculate(getEnc(), setpoint);

      if(pidSpeed > maxSpeed){
        pidSpeed = maxSpeed;
      }
      else if(pidSpeed < -maxSpeed){
        pidSpeed = -maxSpeed;
      }
      intakePivotMotor.set(pidSpeed);
    }

    SmartDashboard.putNumber("Intake Pivot Encoder", getEnc());
    SmartDashboard.putNumber("Intake Pivot Setpoint", setpoint);
    SmartDashboard.putBoolean("Intake Pivot PID", isPIDOn());
    SmartDashboard.putNumber("Intake PID Current Error", pid.getPositionError());
    SmartDashboard.putBoolean("Resting LimitSwitch", restingLimitSwitch.get());
  }
}