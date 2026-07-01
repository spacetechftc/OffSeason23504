package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.Mecanismos.Pinpoint;

import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.core.units.Angle;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.hardware.driving.FieldCentric;
import dev.nextftc.hardware.driving.MecanumDriverControlled;
import dev.nextftc.hardware.impl.MotorEx;

public class MecanumDrive implements Subsystem {



    // Criação dos Motores
    private final MotorEx frontLeftMotor;
    private final MotorEx backLeftMotor;
    private final MotorEx frontRightMotor;
    private final MotorEx backRightMotor;
    private final Pinpoint pinpoint;

    // Construtor

    public MecanumDrive(Pinpoint pinpoint){
        this.pinpoint = pinpoint;
        frontLeftMotor = new MotorEx("fl").brakeMode();
        frontRightMotor = new MotorEx("fr").brakeMode();
        backLeftMotor = new MotorEx("rl").brakeMode();
        backRightMotor = new MotorEx("rr").brakeMode();
    }



    public void start(){
        new MecanumDriverControlled(
                frontLeftMotor,
                frontRightMotor,
                backLeftMotor,
                backRightMotor,
                Gamepads.gamepad1().leftStickY().negate(),
                Gamepads.gamepad1().leftStickX(),
                Gamepads.gamepad1().rightStickX().negate(),
                new FieldCentric(() ->
                    Angle.fromRad(Math.toRadians(pinpoint.getHeading()))
                )
        ).invoke();
    }
}
