package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import org.firstinspires.ftc.teamcode.Mecanismos.Pinpoint;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDrive;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

public class TeleOpNextFTC extends NextFTCOpMode {
    private MecanumDrive mecanumDrive;
    private Pinpoint pinpoint = new Pinpoint();

    public TeleOpNextFTC(){
        addComponents(new SubsystemComponent(Intake.INSTANCE), BulkReadComponent.INSTANCE, BindingsComponent.INSTANCE);
    }

    @Override
    public void onInit() {
        pinpoint.init(hardwareMap);
        pinpoint.start();
        mecanumDrive = new MecanumDrive(pinpoint);
    }

    @Override
    public void onStartButtonPressed() {

        mecanumDrive.start();
        Gamepads.gamepad1().rightTrigger().greaterThan(0.2)
                .whenBecomesTrue(Intake.INSTANCE.colect)
                .whenBecomesFalse(Intake.INSTANCE.stopMotors);

        Gamepads.gamepad1().leftTrigger().greaterThan(0.2)
                .whenBecomesTrue(Intake.INSTANCE.eject)
                .whenBecomesFalse(Intake.INSTANCE.stopMotors);

    }

    @Override
    public void onUpdate() {
        pinpoint.updatePinpoint();
    }
}
