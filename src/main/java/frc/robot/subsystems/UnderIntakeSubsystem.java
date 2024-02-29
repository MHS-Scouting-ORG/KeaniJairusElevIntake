package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Constants.IntakeConstants;

public class UnderIntakeSubsystem extends SubsystemBase {
  
  private CANSparkMax intakeMotor;
  private DigitalInput opticalSensor;

  private RelativeEncoder enc;
  
  public UnderIntakeSubsystem() {
    intakeMotor = new CANSparkMax(IntakeConstants.INTAKE_PORT, MotorType.kBrushless);
    opticalSensor = new DigitalInput(IntakeConstants.INTAKE_OPTICAL_PORT);

    intakeMotor.setIdleMode(IdleMode.kBrake);
    intakeMotor.setInverted(false);
    intakeMotor.setSmartCurrentLimit(IntakeConstants.SMART_CURRENT_LIMIT); 

    enc = intakeMotor.getEncoder();
  }

  public double getEnc(){
    return enc.getPosition();
  }

  public boolean getOpticalSensor(){
    return opticalSensor.get();
  }

  public void intake(double xSpeed){
    // Multiplies given joystick value by 1.2 then deadzones value
    intakeMotor.set(xSpeed);
  }

  public void outtake(){
    intakeMotor.set(-IntakeConstants.INTAKE_MAXSPEED);
  }

  public void manualIntake(double speed){
    intakeMotor.set(deadzone(speed));
  }

  // public void toEncoder(double enc){
  //   double holdEnc = getEnc();

  //   while (getEnc() > holdEnc - enc){
  //     intake();
  //   }
  //   stopIntake();
  // }

  //Deadzone for the intake that sets a max speed of 0.2 in both directions
  public double deadzone(double speed) {
    if (Math.abs(speed) < 0.1) {
      return 0;
    }
    else if (speed > IntakeConstants.INTAKE_MAXSPEED) {
      return IntakeConstants.INTAKE_MAXSPEED;
    }
    else if (speed < -IntakeConstants.INTAKE_MAXSPEED) {
      return -IntakeConstants.INTAKE_MAXSPEED;
    }
    else {
      return speed;
    }
  }

  public void stopIntake(){
    intakeMotor.stopMotor();
  }

  @Override
  public void periodic() {
    //SmartDashboard
    SmartDashboard.putBoolean("[I] Optical Sensor", getOpticalSensor());
    SmartDashboard.putNumber("[I] Encoder", getEnc());
  }
}
