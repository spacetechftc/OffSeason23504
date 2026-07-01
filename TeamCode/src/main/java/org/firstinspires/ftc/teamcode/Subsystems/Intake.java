package org.firstinspires.ftc.teamcode.Subsystems;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.controllable.RunToVelocity;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.powerable.SetPower;

public class Intake implements Subsystem {
    public static final Intake INSTANCE = new Intake();
    private Intake() {}

    private final ControlSystem controlSystem = ControlSystem.builder()
            .basicFF(0, 0, 0)
            .velPid(0, 0, 0)
            .build();

    // Instances
    private final MotorEx rightMotor = new MotorEx("intakeD");
    private final MotorEx leftMotor = new MotorEx("intakeE");
    private final MotorGroup motors = new MotorGroup(rightMotor, leftMotor);

    // Commands

    public Command colect = new RunToVelocity(controlSystem,  1.0).requires(this);
    public Command stopMotors = new RunToVelocity(controlSystem,  0.0).requires(this);
    public Command eject = new RunToVelocity(controlSystem, -1.0).requires(this);

}
