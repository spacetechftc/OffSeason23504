package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.impl.MotorEx;

public class TestMotors extends NextFTCOpMode{
    private MotorEx frontLeftMotor = new MotorEx("fl");
    private MotorEx frontRightMotor = new MotorEx("fr");
    private MotorEx backLeftMotor = new MotorEx("rl");
    private MotorEx backRightMotor = new MotorEx("rr");

    private MotorGroup motores = new MotorGroup(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor);

    public TestMotors(){
        addComponents(BulkReadComponent.INSTANCE);
    }

    @Override
    public void onUpdate() {


        if (gamepad1.aWasPressed()){
            motores.setPower(1);
        }else{
            motores.setPower(0);
        }

        telemetry.addData("Front Left Power", frontLeftMotor.getPower());
        telemetry.addLine("----------------------------------------------------------");
        telemetry.addData("Front Right Power", frontRightMotor.getPower());
        telemetry.addLine("----------------------------------------------------------");
        telemetry.addData("Back Left Power", backLeftMotor.getPower());
        telemetry.addLine("----------------------------------------------------------");
        telemetry.addData("Back Right Power", backRightMotor.getPower());
        telemetry.update();
    }
}
