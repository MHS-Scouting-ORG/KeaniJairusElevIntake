package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase {

  private CANSparkMax elevMotor;
  private RelativeEncoder enc;
  private double speedCap;

  private PIDController pid;
  private double previousError;
  private double currentError;

  private double topEncLimit;
  private double bottomEncLimit;

  // private DigitalInput topLimitSwitch;
  // private DigitalInput bottomLimitSwitch;

  public ElevatorSubsystem() {
    elevMotor = new CANSparkMax(ElevatorConstants.ELEVATOR_PORT, MotorType.kBrushless);
    // topLimitSwitch = new DigitalInput(ElevatorConstants.TOP_LS_PORT);
    // bottomLimitSwitch = new DigitalInput(ElevatorConstants.BOTTOM_LS_PORT);
    enc = elevMotor.getEncoder();
    speedCap = ElevatorConstants.SPEED_CAP;
    elevMotor.setIdleMode(IdleMode.kBrake);
    topEncLimit = ElevatorConstants.TOP_ENC_LIMIT;
    bottomEncLimit = ElevatorConstants.BOTTOM_ENC_LIMIT;
    pid = new PIDController(0.0, 0.0, 0.0);
    previousError = 0;
    pid.setTolerance(1);
  }

  ////////////////////////
  //  Accessor Methods  //
  ////////////////////////

  public double getEnc() {
    return enc.getPosition();
  }

  // public boolean getTopLimitSwitch() {
  //   return topLimitSwitch.get();
  // }

  // public boolean getBottomLimitSwitch() {
  //   return bottomLimitSwitch.get();
  // }

  //////////////////////////////
  //  Basic Movement Methods  //
  //////////////////////////////

  public void elevStop(){
    elevMotor.stopMotor();
  }

  public void toBottom(){
    if (getEnc() > -50) {
      elevMotor.set(-0.5);
    }
    else {
      elevStop();
    }
  }

  public void toTop() {
    if (getEnc() < 50) {
      elevMotor.set(0.5);
    }
    else {
      elevStop();
    }
  }

  // Checks if limit switches are pressed to prevent movement in that direction
  public void ManualElevator(double speed) {
    // if (getTopLimitSwitch() && speed < -0.1) {
    //   elevMotor.set(deadzone(speed));
    // }
    // else if (getBottomLimitSwitch() && speed > 0.1){
    //   elevMotor.set(deadzone(speed));
    // }
    // else if (!getTopLimitSwitch() && !getBottomLimitSwitch()){
    //   elevMotor.set(deadzone(speed));
    // }
    // else{
    //   elevStop();
    // }

    // This line is in case of no limitswitches and just sets motor to joystick speed
    elevMotor.set(deadzone(speed)); 
  }


  // Deadzone includes a speedcap at 0.5 in either direction
  public double deadzone(double speed) {
    if (Math.abs(speed) < 0.1) {
      return 0;
    }
    else if (speed > speedCap) {
      return speedCap;
    }
    else if (speed < -speedCap) {
      return -speedCap;
    }
    else {
      return speed;
    }
  }

  //////////////////
  //  PID Methods //
  //////////////////
  
  public double calculateSpeed(double setpoint){
    double error = pid.calculate(getEnc(), setpoint);

    if (error > speedCap){
      return speedCap;
    }
    else if (error < -speedCap){
      return -speedCap;
    }
    else{
      return error;
    }
  }

  public void resetI(){
    currentError = pid.getPositionError();
    
    if (currentError > 0 && previousError < 0){
      pid.reset();
    }
    else if (currentError < 0 && previousError > 0){
      pid.reset();
    }
    
    previousError = currentError;
  }

  public void toTopPID(){
    elevMotor.set(calculateSpeed(topEncLimit));
  }

  public void toBottomPID(){
    elevMotor.set(calculateSpeed(bottomEncLimit));
  }

  @Override
  public void periodic() {

    resetI();

    // SmartDashboard
    SmartDashboard.putNumber("Elevator Encoder", getEnc());
    // SmartDashboard.putBoolean("Elevator Top LS", getTopLimitSwitch());
    // SmartDashboard.putBoolean("Elevator Bottom LS", getBottomLimitSwitch());
  }
}