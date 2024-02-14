package frc.robot.commands.IntakeCommands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.UnderIntakeSubsystem;

public class ManualIntakeCmd extends Command {

  private UnderIntakeSubsystem intakeSub;
  private DoubleSupplier doubleSupplier;

  // Command allows manual control of the intake using a joystick

  public ManualIntakeCmd(UnderIntakeSubsystem newIntakeSub, DoubleSupplier newDoubleSupplier) {

    intakeSub = newIntakeSub;
    doubleSupplier = newDoubleSupplier;
    
    addRequirements(intakeSub);
  }
  
  @Override
  public void initialize() {}

  @Override
  public void execute() {
    intakeSub.manualIntake(doubleSupplier.getAsDouble());
    SmartDashboard.putNumber("Manual Intake Speed", doubleSupplier.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
