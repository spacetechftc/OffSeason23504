package org.firstinspires.ftc.teamcode.Subsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.powerable.SetPower;

public class Intake implements Subsystem {
    public static final Intake INSTANCE = new Intake();
    private Intake() {}

    // Instances
    private MotorEx rightMotor = new MotorEx("motor_intake_right");
    private MotorEx leftMotor = new MotorEx("motor_intake_left");
    private MotorGroup motors = new MotorGroup(rightMotor, leftMotor);

    // Commands
    public Command colect = new SetPower(motors,  1.0).requires(this);
    public Command stopMotors = new SetPower(motors,  0.0).requires(this);
    public Command eject = new SetPower(motors, -1.0).requires(this);

}
