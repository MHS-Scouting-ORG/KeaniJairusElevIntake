package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.HangConstants;
 
public class HangSubsystem extends SubsystemBase {

  private CANSparkMax hangMotor1;
  private CANSparkMax hangMotor2;
  private RelativeEncoder enc;

  private PIDController pid;
  private double previousError;
  private double currentError;

  private DigitalInput topMRS;
  private DigitalInput bottomMRS;

  public HangSubsystem() {
    hangMotor1 = new CANSparkMax(HangConstants.HANG_MOTOR_PORT1, MotorType.kBrushless);
    hangMotor2 = new CANSparkMax(HangConstants.HANG_MOTOR_PORT2, MotorType.kBrushless);

    hangMotor1.setSmartCurrentLimit(HangConstants.SMART_CURRENT_LIMIT);
    hangMotor2.setSmartCurrentLimit(HangConstants.SMART_CURRENT_LIMIT);

    hangMotor1.setIdleMode(IdleMode.kBrake);
    hangMotor2.setIdleMode(IdleMode.kBrake);

    topMRS = new DigitalInput(HangConstants.TOP_MRS_PORT);
    bottomMRS = new DigitalInput(HangConstants.BOTTOM_MRS_PORT);

    enc = hangMotor1.getEncoder();

    pid = new PIDController(HangConstants.HANG_KP, HangConstants.HANG_KI, HangConstants.HANG_KD);
    pid.setTolerance(HangConstants.PID_TOLERANCE);
    previousError = 0;
  }

  //////////////////////
  // Accessor Methods //
  //////////////////////

  public double getEnc() {
    return enc.getPosition();
  }

  public boolean getTopMRS() {
    return topMRS.get();
  }

  public boolean getBottomMRS(){
    return bottomMRS.get();
  }

  //////////////////////////////
  //  Basic Movement Methods  //
  //////////////////////////////

  public void toBottom() {
   hangMotor1.set(-HangConstants.SPEED_CAP);
   hangMotor2.set(-HangConstants.SPEED_CAP);
  }

  public void toTop() {
    hangMotor1.set(HangConstants.SPEED_CAP);
    hangMotor2.set(HangConstants.SPEED_CAP);
  }

  // Checks if limit switches are pressed to prevent movement in that direction
  public void ManualHang(double speed) {
    hangMotor1.set(deadzone(speed));
    hangMotor2.set(deadzone(speed));
  }

  // Deadzone includes a speedcap at 0.5 in either direction
  public double deadzone(double speed) {
    if (Math.abs(speed) < 0.1) {
      return 0;
    } 
    else if (speed > HangConstants.SPEED_CAP) {
      return HangConstants.SPEED_CAP;
    } 
    else if (speed < -HangConstants.SPEED_CAP) {
      return -HangConstants.SPEED_CAP;
    } 
    else {
      return speed;
    }
  }

  public void stopHang() {
    hangMotor1.stopMotor();
    hangMotor2.stopMotor();
  }

  //////////////////
  // PID Methods //
  //////////////////

  public double calculateSpeed(double setpoint) {
    double output = pid.calculate(getEnc(), setpoint);

    if (output > HangConstants.SPEED_CAP) {
      return HangConstants.SPEED_CAP;
    } 
    else if (output < -HangConstants.SPEED_CAP) {
      return -HangConstants.SPEED_CAP;
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

  public void toTopPID() {
    hangMotor1.set(calculateSpeed(HangConstants.TOP_ENC_LIMIT));
    hangMotor2.set(calculateSpeed(HangConstants.TOP_ENC_LIMIT));
  }

  public void toBottomPID() {
    hangMotor1.set(calculateSpeed(HangConstants.BOTTOM_ENC_LIMIT));
    hangMotor2.set(calculateSpeed(HangConstants.BOTTOM_ENC_LIMIT));
  }

  public boolean isAtSetpoint() {
    return Math.abs(getEnc() - pid.getSetpoint()) <= 3;
  }

  @Override
  public void periodic() {

    resetI();

    // SmartDashboard
    SmartDashboard.putNumber("[H] Enc #1", getEnc());
    SmartDashboard.putBoolean("[H] Top MRS", getTopMRS());
    SmartDashboard.putBoolean("[H] Bottom MRS", getBottomMRS());
    SmartDashboard.putBoolean("[H] isAtSetpoint", isAtSetpoint());
  }
}