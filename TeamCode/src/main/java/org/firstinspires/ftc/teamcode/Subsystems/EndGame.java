package org.firstinspires.ftc.teamcode.Subsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.positionable.ServoGroup;
import dev.nextftc.hardware.positionable.SetPosition;

public class EndGame implements Subsystem {
    public static final EndGame INSTANCE = new EndGame();
    private EndGame(){}


    // Instância dos Servos
    private ServoEx servoLeft = new ServoEx("servo_left_endgame");
    private ServoEx servoRight = new ServoEx("servo_right_endgame");
    private ServoGroup servos = new ServoGroup(servoLeft, servoRight);

    // Comandos
    public Command subir = new SetPosition(servos, 1);
    public Command descer = new SetPosition(servos, 0);

    
    @Override
    public void initialize() {
        descer.invoke();
    }
}
