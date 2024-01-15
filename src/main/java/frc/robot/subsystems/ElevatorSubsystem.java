package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {

  private CANSparkMax elevMotor;
  private RelativeEncoder enc;
  private double speedCap;

  private DigitalInput topLimitSwitch;
  private DigitalInput bottomLimitSwitch;

  public ElevatorSubsystem() {
    elevMotor = new CANSparkMax(0, MotorType.kBrushless);
    topLimitSwitch = new DigitalInput(0);
    bottomLimitSwitch = new DigitalInput(1);
    enc = elevMotor.getEncoder();
    speedCap = 0.5;
  }

  public double getEnc() {
    return enc.getPosition();
  }

  public boolean getTopLimitSwitch() {
    return topLimitSwitch.get();
  }

  public boolean getBottomLimitSwitch() {
    return bottomLimitSwitch.get();
  }

  public void elevStop(){
    elevMotor.stopMotor();
  }

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
  }

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
    SmartDashboard.putNumber("Elevator Encoder", getEnc());
  }
}
