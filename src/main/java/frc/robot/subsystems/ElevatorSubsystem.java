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

  private CANSparkMax elevMotor1;
  private CANSparkMax elevMotor2;
  private RelativeEncoder enc;
  private double speedCap;

  private PIDController pid;
  private double previousError;
  private double currentError;

  private double topEncLimit;
  private double bottomEncLimit;

  private DigitalInput topLS;
  private DigitalInput bottomLS;

  public ElevatorSubsystem() {
    elevMotor1 = new CANSparkMax(ElevatorConstants.ELEVATOR_MOTOR_PORT1, MotorType.kBrushless);
    elevMotor2 = new CANSparkMax(ElevatorConstants.ELEVATOR_MOTOR_PORT2, MotorType.kBrushless);

    topLS = new DigitalInput(ElevatorConstants.TOP_LS_PORT);
    bottomLS = new DigitalInput(ElevatorConstants.BOTTOM_LS_PORT);

    enc = elevMotor1.getEncoder();

    speedCap = ElevatorConstants.SPEED_CAP;

    elevMotor1.setIdleMode(IdleMode.kBrake);
    elevMotor2.setIdleMode(IdleMode.kBrake);

    topEncLimit = ElevatorConstants.TOP_ENC_LIMIT;
    bottomEncLimit = ElevatorConstants.BOTTOM_ENC_LIMIT;

    pid = new PIDController(ElevatorConstants.ELEVATOR_KP, ElevatorConstants.ELEVATOR_KI, ElevatorConstants.ELEVATOR_KD);
    previousError = 0;
    pid.setTolerance(1);
  }

  ////////////////////////
  //  Accessor Methods  //
  ////////////////////////

  public double getEnc() {
    return enc.getPosition();
  }

  public boolean getTopLimitSwitch() {
    return topLS.get();
  }

  public boolean getBottomLimitSwitch() {
    return bottomLS.get();
  }

  //////////////////////////////
  //  Basic Movement Methods  //
  //////////////////////////////

  public void elevStop(){
    elevMotor1.stopMotor();
    elevMotor2.stopMotor();
  }

  public void toBottom(){
    if (getEnc() > -50) {
      elevMotor1.set(-speedCap);
      elevMotor2.set(-speedCap);
    }
    else {
      elevStop();
    }
  }

  public void toTop() {
    if (getEnc() < 50) {
      elevMotor1.set(speedCap);
      elevMotor2.set(speedCap);
    }
    else {
      elevStop();
    }
  }

  // Checks if limit switches are pressed to prevent movement in that direction
  public void ManualElevator(double speed) {
    if (getTopLimitSwitch() && speed > 0) {
      elevStop();
    }
    // else if (getBottomLimitSwitch() && speed > 0.1){
    //   elevMotor1.set(deadzone(speed));
    // }
    // else if (!getTopLimitSwitch() && !getBottomLimitSwitch()){
    //   elevMotor1.set(deadzone(speed));
    // }
    else{
      elevMotor1.set(deadzone(speed));
      elevMotor2.set(deadzone(speed));
    }

    // This line is in case of no limitswitches and just sets motor to joystick speed
    // elevMotor1.set(deadzone(speed)); 
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
    elevMotor1.set(calculateSpeed(topEncLimit));
    elevMotor2.set(calculateSpeed(topEncLimit));
  }

  public void toBottomPID(){
    elevMotor1.set(calculateSpeed(bottomEncLimit));
    elevMotor2.set(calculateSpeed(bottomEncLimit));
  }

  @Override
  public void periodic() {

    resetI();

    // SmartDashboard
    SmartDashboard.putNumber("Elevator Enc 1", getEnc());
    SmartDashboard.putNumber("Elevator Enc 2", elevMotor2.getEncoder().getPosition());
    SmartDashboard.putBoolean("Elevator Top LS", getTopLimitSwitch());
    // SmartDashboard.putBoolean("Elevator Bottom LS", getBottomLimitSwitch());
  }
}