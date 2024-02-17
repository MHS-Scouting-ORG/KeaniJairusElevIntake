package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Constants.IntakeConstants;

public class UnderIntakeSubsystem extends SubsystemBase {
  
  private CANSparkMax intakeMotor;
  private CANSparkMax intakeMotor2;
  private DigitalInput opticalSensor;

  private RelativeEncoder enc;
 
  public UnderIntakeSubsystem() {
    intakeMotor = new CANSparkMax(IntakeConstants.INTAKE_PORT, MotorType.kBrushless);
    intakeMotor2 = new CANSparkMax(IntakeConstants.INTAKE_PORT2, MotorType.kBrushless);
    opticalSensor = new DigitalInput(IntakeConstants.INTAKE_OPTICAL_PORT);

    intakeMotor.setInverted(true);

    intakeMotor.setSmartCurrentLimit(IntakeConstants.SMART_CURRENT_LIMIT); 
    intakeMotor2.setSmartCurrentLimit(IntakeConstants.SMART_CURRENT_LIMIT); 

    enc = intakeMotor.getEncoder();
  }

  public double getEnc(){
    return enc.getPosition();
  }

  public boolean getOpticalSensor(){
    return opticalSensor.get();
  }

  public void intake(double xSpeed){
    intakeMotor.set(deadzone(Math.abs(xSpeed)*1.5));
    intakeMotor2.set(deadzone(Math.abs(xSpeed)*1.5));
  }

  public void outtake(double xSpeed){
    intakeMotor.set(Math.abs(xSpeed));
    intakeMotor2.set(Math.abs(xSpeed));
  }

  public void manualIntake(double speed){
    intakeMotor.set(-deadzone(speed));
    intakeMotor2.set(deadzone(speed));
  }

  public void toEncoder(double enc){
    double holdEnc = getEnc();

    while (getEnc() > holdEnc - enc){
      //FIXME Add double param
      //intake();
    }
    stopIntake();
  }

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
    intakeMotor2.stopMotor();
  }

  @Override
  public void periodic() {
    //SmartDashboard
    SmartDashboard.putBoolean("[I] Optical Sensor", getOpticalSensor());
    SmartDashboard.putNumber("[I] Encoder", getEnc());
  }
}
