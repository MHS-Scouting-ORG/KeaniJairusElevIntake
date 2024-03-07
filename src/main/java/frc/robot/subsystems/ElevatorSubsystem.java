package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase {

  private TalonFX elevMotor;

  private PIDController pid;
  private double previousError;
  private double currentError;
  private double setpoint;

  private DigitalInput topLS;
  private DigitalInput bottomLS;

  public ElevatorSubsystem() {
    elevMotor = new TalonFX(ElevatorConstants.ELEVATOR_MOTOR_PORT);

    elevMotor.getConfigurator().apply(new CurrentLimitsConfigs().withSupplyCurrentLimitEnable(true));
    elevMotor.getConfigurator().apply(new CurrentLimitsConfigs().withSupplyCurrentLimit(ElevatorConstants.SMART_CURRENT_LIMIT));

    elevMotor.setNeutralMode(NeutralModeValue.Brake);

    topLS = new DigitalInput(ElevatorConstants.TOP_LS_PORT);
    bottomLS = new DigitalInput(ElevatorConstants.BOTTOM_LS_PORT);

    elevMotor.setInverted(true);
 
    pid = new PIDController(ElevatorConstants.ELEV_KP, ElevatorConstants.ELEV_KI, ElevatorConstants.ELEV_KD);
    pid.setTolerance(ElevatorConstants.PID_TOLERANCE);
    previousError = 0;

    setpoint = 0;
  }

  ////////////////////////
  // Accessor Methods //
  ////////////////////////

  public double getEnc() {
    return elevMotor.getPosition().getValue();
  }

  public void resetEnc(){
    elevMotor.setPosition(0);
  }

  public boolean getTopLimitSwitch() {
    return !topLS.get();
  }

  public boolean getBottomLimitSwitch() {
    return !bottomLS.get();
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

  public void setSetpoint(double setpoint) {
    this.setpoint = setpoint;
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

  public boolean isAtSetpoint() {
    return pid.atSetpoint();
  }

  @Override
  public void periodic() {
    if(getBottomLimitSwitch()){
      resetEnc();
    }

    resetI();

    double output = pid.calculate(getEnc(), setpoint);

    // if (getBottomLimitSwitch() && output < 0){
    //   elevStop();
    // }

    // else if (getTopLimitSwitch() && output > 0){
    //   elevStop();
    // }

    // else if (isAtSetpoint()){
    //   elevStop();
    // }

    // else if (output > ElevatorConstants.SPEED_CAP) {
    //   elevMotor.set(ElevatorConstants.SPEED_CAP);
    // } 
    // else if (output < -ElevatorConstants.SPEED_CAP) {
    //   elevMotor.set(-ElevatorConstants.SPEED_CAP);
    // }
    // else {
    //   elevMotor.set(output);
    // }

    // SmartDashboard
    SmartDashboard.putNumber("[E] Enc", getEnc());
    SmartDashboard.putNumber("[E] Output", output);
    SmartDashboard.putNumber("[E] Setpoint",setpoint);
    SmartDashboard.putBoolean("[E] isAtSetpoint", pid.atSetpoint());
    SmartDashboard.putBoolean("[E] Top LS", getTopLimitSwitch());
    SmartDashboard.putBoolean("[E] Bottom LS", getBottomLimitSwitch());
  }
}