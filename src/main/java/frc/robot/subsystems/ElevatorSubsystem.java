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

  private PIDController pid;
  private double previousError;
  private double currentError;

  private DigitalInput topLS;
  private DigitalInput bottomLS;

  public ElevatorSubsystem() {
    elevMotor = new CANSparkMax(ElevatorConstants.ELEVATOR_MOTOR_PORT, MotorType.kBrushless);

    elevMotor.setSmartCurrentLimit(ElevatorConstants.SMART_CURRENT_LIMIT);

    elevMotor.setIdleMode(IdleMode.kBrake);

    topLS = new DigitalInput(ElevatorConstants.TOP_LS_PORT);
    bottomLS = new DigitalInput(ElevatorConstants.BOTTOM_LS_PORT);

    enc = elevMotor.getEncoder();

    pid = new PIDController(ElevatorConstants.ELEV_KP, ElevatorConstants.ELEV_KI, ElevatorConstants.ELEV_KD);
    pid.setTolerance(ElevatorConstants.PID_TOLERANCE);
    previousError = 0;
  }

  ////////////////////////
  // Accessor Methods //
  ////////////////////////

  public double getEnc() {
    return enc.getPosition();
  }

  public void resetEnc(){
    enc.setPosition(0);
  }

  public boolean getTopLimitSwitch() {
    return topLS.get();
  }

  public boolean getBottomLimitSwitch() {
    return bottomLS.get();
  }

  //////////////////////////////
  // Basic Movement Methods //
  //////////////////////////////

  public void elevStop() {
    elevMotor.stopMotor();
  }

  public void toBottom() {
    if (getEnc() > -50) {
      elevMotor.set(-ElevatorConstants.SPEED_CAP);
    } else {
      elevStop();
    }
  }

  public void toTop() {
    if (getEnc() < 50) {
      elevMotor.set(ElevatorConstants.SPEED_CAP);
    } else {
      elevStop();
    }
  }

  // Checks if limit switches are pressed to prevent movement in that direction
  public void ManualHang(double speed) {
    if (getTopLimitSwitch() && speed > 0) {
      elevStop();
    }
    else if (getBottomLimitSwitch() && speed < 0){
      elevStop();
    }
    // else if (!getTopLimitSwitch() && !getBottomLimitSwitch()){
    // elevMotor.set(deadzone(speed));
    // }
    else {
      elevMotor.set(deadzone(speed));
    }

    // This line is in case of no limitswitches and just sets motor to joystick
    // speed
    // elevMotor.set(deadzone(speed));
  }

  // Deadzone includes a speedcap at 0.5 in either direction
  public double deadzone(double speed) {
    if (Math.abs(speed) < 0.1) {
      return 0;
    } 
    else if (speed > ElevatorConstants.SPEED_CAP) {
      return ElevatorConstants.SPEED_CAP;
    } 
    else if (speed < -ElevatorConstants.SPEED_CAP) {
      return -ElevatorConstants.SPEED_CAP;
    } 
    else {
      return speed;
    }
  }

  //////////////////
  // PID Methods //
  //////////////////

  public double calculateSpeed(double setpoint) {
    double output = pid.calculate(getEnc(), setpoint);

    if (output > ElevatorConstants.SPEED_CAP) {
      return ElevatorConstants.SPEED_CAP;
    } 
    else if (output < -ElevatorConstants.SPEED_CAP) {
      return -ElevatorConstants.SPEED_CAP;
    } 
    else {
      return output;
    }
  }

  public void resetI() {
    currentError = pid.getPositionError();

    if (currentError > 0 && previousError < 0) {
      pid.reset();
    } 
    else if (currentError < 0 && previousError > 0) {
      pid.reset();
    }

    previousError = currentError;
  }

  public void toSetpoint(double setpoint) {
    elevMotor.set(calculateSpeed(setpoint));
  }

  public void holdAtPoint() {
    elevMotor.set(calculateSpeed(getEnc()));
  }

  public boolean isAtSetpoint() {
    return Math.abs(getEnc() - pid.getSetpoint()) <= 3;
  }

  @Override
  public void periodic() {

    resetI();

    // SmartDashboard
    SmartDashboard.putNumber("[E] Enc", getEnc());
    SmartDashboard.putBoolean("[E] Top LS", getTopLimitSwitch());
    SmartDashboard.putBoolean("[E] isAtSetpoint", isAtSetpoint());
    SmartDashboard.putBoolean("[E] Bottom LS", getBottomLimitSwitch());
  }
}