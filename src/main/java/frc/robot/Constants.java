package frc.robot;

public final class Constants {
  public static class IntakeConstants {
    public static final int INTAKE_PORT = 1;
    public static final int INTAKE_PORT2 = 15;
    public static final int INTAKEPIVOT_PORT = 5;
    public static final int INTAKE_RESTING_LS_PORT = 1;
    public static final int INTAKE_INTAKING_LS_PORT = 8;
    public static final int INTAKE_OPTICAL_PORT = 9;
    public static final double INTAKEPIVOT_KP = 0.01;
    public static final double INTAKEPIVOT_KI = 0;
    public static final double INTAKEPIVOT_KD = 0;
    public static final double INTAKE_MAXSPEED = 0.5;
    public static final double INTAKE_ENCTOLERANCE = 2;
  }
  public static class HangConstants {
    public static final int HANG_MOTOR_PORT1 = 3;
    public static final int HANG_MOTOR_PORT2 = 4;
    public static final int TOP_LS_PORT = 6;
    public static final int BOTTOM_LS_PORT = 2;
    public static final double SPEED_CAP = 0.5;
    public static final double TOP_ENC_LIMIT = 50;
    public static final double BOTTOM_ENC_LIMIT = -50;
    public static final double HANG_KP = 0.01;
    public static final double HANG_KI = 0;
    public static final double HANG_KD = 0;
  }

  public static class ElevatorConstants {
    public static final int ELEVATOR_MOTOR_PORT = 5;
    public static final int TOP_LS_PORT = 8;
    public static final int BOTTOM_LS_PORT = 11;
    public static final double SPEED_CAP = 0.5;
    public static final double ELEV_KP = 0.01;
    public static final double ELEV_KI = 0;
    public static final double ELEV_KD = 0;
  }
}