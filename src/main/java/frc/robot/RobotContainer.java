package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.HangSubsystem;
import frc.robot.commands.ElevatorCommands.ElevatorToBottomCmd;
import frc.robot.commands.ElevatorCommands.ElevatorToTopCmd;
import frc.robot.commands.ElevatorCommands.ManualElevatorCmd;
import frc.robot.commands.HangCommands.HangToBottomCmd;
import frc.robot.commands.HangCommands.HangToTopCmd;
import frc.robot.commands.IntakeCommands.DeliverCmd;
import frc.robot.commands.IntakeCommands.IntakeCmd;
import frc.robot.commands.IntakeCommands.OuttakeCmd;
import frc.robot.subsystems.UnderIntakeSubsystem;

public class RobotContainer {
  private final HangSubsystem hangSub = new HangSubsystem();
  private final UnderIntakeSubsystem u_subsystem = new UnderIntakeSubsystem();
  private final ElevatorSubsystem elevSub = new ElevatorSubsystem();

  private final Joystick joystick = new Joystick(1);

  public RobotContainer() {
    //i_subsystem.setDefaultCommand(new ManualIntakePivot(i_subsystem, () -> joystick.getY()));
    //hangSub.setDefaultCommand(new ManualHangCmd(hangSub, () -> joystick.getY()));
    elevSub.setDefaultCommand(new ManualElevatorCmd(elevSub, () -> joystick.getY()));

    configureBindings();
  }

  private void configureBindings() {
    // new JoystickButton(joystick, 1).onTrue(new IntakeCmd(u_subsystem));
    // new JoystickButton(joystick, 6).toggleOnTrue(new OuttakeCmd(u_subsystem));
    // new JoystickButton(joystick, 6).toggleOnFalse(new InstantCommand(() -> u_subsystem.stopIntake()));
    // new JoystickButton(joystick, 2).whileTrue(new DeliverCmd(u_subsystem));

    new JoystickButton(joystick, 5).onTrue(new HangToTopCmd(hangSub));
    new JoystickButton(joystick, 3).onTrue(new HangToBottomCmd(hangSub));

    new JoystickButton(joystick, 6).onTrue(new ElevatorToTopCmd(elevSub));
    new JoystickButton(joystick, 4).onTrue(new ElevatorToBottomCmd(elevSub));
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
