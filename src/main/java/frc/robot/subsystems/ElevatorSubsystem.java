package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase {

  private CANSparkMax elevMotor;
  private RelativeEncoder enc;
  private double speedCap;

  private DigitalInput topLimitSwitch;
  private DigitalInput bottomLimitSwitch;

  public ElevatorSubsystem() {
    elevMotor = new CANSparkMax(ElevatorConstants.ELEVATOR_PORT, MotorType.kBrushless);
    topLimitSwitch = new DigitalInput(0);
    bottomLimitSwitch = new DigitalInput(1);
    enc = elevMotor.getEncoder();
    speedCap = 0.5;
    elevMotor.setIdleMode(IdleMode.kBrake);
  }

  ////////////////////////
  //  Accessor Methods  //
  ////////////////////////

  public double getEnc() {
    return enc.getPosition();
  }

  public boolean getTopLimitSwitch() {
    return topLimitSwitch.get();
  }

  public boolean getBottomLimitSwitch() {
    return bottomLimitSwitch.get();
  }

  ////////////////////////
  //  Movement Methods  //
  ////////////////////////

  public void elevStop(){
    elevMotor.stopMotor();
  }

  public void toBottom(){
    if (!getBottomLimitSwitch()) {
      elevMotor.set(-0.3);
    }
    else {
      elevStop();
    }
  }

  public void toTop() {
    if (!getTopLimitSwitch()) {
      elevMotor.set(0.3);
    }
    else {
      elevStop();
    }
  }

  // Checks if limit switches are pressed to prevent movement in that direction
  public void ManualElevator(double speed) {
    if (getTopLimitSwitch() && speed < -0.1) {
      elevMotor.set(deadzone(speed));
    }
    else if (getBottomLimitSwitch() && speed > 0.1){
      elevMotor.set(deadzone(speed));
    }
    else if (!getTopLimitSwitch() && !getBottomLimitSwitch()){
      elevMotor.set(deadzone(speed));
    }
    else{
      elevStop();
    }

    // This line is in case of no limitswitches and just sets motor to joystick speed
    // elevMotor.set(deadzone(speed)); 
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

  @Override
  public void periodic() {

    // SmartDashboard
    SmartDashboard.putNumber("Elevator Encoder", getEnc());
    SmartDashboard.putBoolean("Elevator Top LS", getTopLimitSwitch());
    SmartDashboard.putBoolean("Elevator Bottom LS", getBottomLimitSwitch());
  }
}