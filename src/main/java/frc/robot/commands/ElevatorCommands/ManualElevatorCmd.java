package frc.robot.commands.ElevatorCommands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

public class ManualElevatorCmd extends Command {

  private ElevatorSubsystem elevSub;
  private DoubleSupplier speed;

  public ManualElevatorCmd(ElevatorSubsystem newElevSub, DoubleSupplier doubleSupp) {

    elevSub = newElevSub;
    speed = doubleSupp;

    addRequirements(elevSub);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    elevSub.ManualHang(speed.getAsDouble());
    SmartDashboard.putNumber("Joystick", speed.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
