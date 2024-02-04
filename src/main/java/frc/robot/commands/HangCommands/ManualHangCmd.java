package frc.robot.commands.HangCommands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.HangSubsystem;

public class ManualHangCmd extends Command {

  private HangSubsystem hangSub;
  private DoubleSupplier doubleSupplier;

  // Command allows manual control of elevator using a joystick

  public ManualHangCmd(HangSubsystem newHangSub, DoubleSupplier newDoubleSupplier) {

    hangSub = newHangSub;
    doubleSupplier = newDoubleSupplier;
    
    addRequirements(hangSub);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    hangSub.ManualHang(doubleSupplier.getAsDouble());
    SmartDashboard.putNumber("Manual Elevator Speed", doubleSupplier.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
