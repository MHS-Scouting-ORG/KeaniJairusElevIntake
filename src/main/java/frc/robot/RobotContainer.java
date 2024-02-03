package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.HangSubsystem;
import frc.robot.commands.ElevatorCommands.ElevatorToBottomCommand;
import frc.robot.commands.ElevatorCommands.ElevatorToBottomPIDCommand;
import frc.robot.commands.ElevatorCommands.ElevatorToTopCommand;
import frc.robot.commands.ElevatorCommands.ElevatorToTopPIDCommand;
import frc.robot.commands.ElevatorCommands.ManualElevatorCommand;
import frc.robot.commands.IntakeCommands.DeliverCmd;
import frc.robot.commands.IntakeCommands.IntakeCmd;
import frc.robot.commands.IntakeCommands.OuttakeCmd;
import frc.robot.subsystems.UnderIntakeSubsystem;

public class RobotContainer {
  private final HangSubsystem hangSub = new HangSubsystem();
  private final UnderIntakeSubsystem u_subsystem = new UnderIntakeSubsystem();

  //private final XboxController xboxController = new XboxController(0);
  private final Joystick joystick = new Joystick(1);

  public RobotContainer() {
    //i_subsystem.setDefaultCommand(new ManualIntakePivot(i_subsystem, () -> joystick.getY()));
    hangSub.setDefaultCommand(new ManualElevatorCommand(hangSub, () -> joystick.getY()));

    configureBindings();
  }

  private void configureBindings() {
    new JoystickButton(joystick, 1).onTrue(new IntakeCmd(u_subsystem));
    new JoystickButton(joystick, 6).toggleOnTrue(new OuttakeCmd(u_subsystem));
    new JoystickButton(joystick, 6).toggleOnFalse(new InstantCommand(() -> u_subsystem.stopIntake()));
    new JoystickButton(joystick, 2).whileTrue(new DeliverCmd(u_subsystem));

    new JoystickButton(joystick,   7).onTrue(new ElevatorToTopCommand(hangSub));
    new JoystickButton(joystick, 4).onTrue(new ElevatorToBottomCommand(hangSub));

    new JoystickButton(joystick, 5).onTrue(new ElevatorToTopPIDCommand(hangSub));
    new JoystickButton(joystick, 3).onTrue(new ElevatorToBottomPIDCommand(hangSub));
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
