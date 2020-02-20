package frc.robot;

/*
 * DO NOT EDIT.   See RobotConstants.java to change the mappings between buttons and robot engines.
 * 
 * This class maps Joystick buttons to values that the Robot program understands.
 * See http://controls.coderedrobotics.com/programminglessons/4.html for more details.
 */

public class Button{
    /*
     *
     * A button is a Joystick control that is read as either being pushed or
     * not being pushed.  If it is being pushed, the program is sent a True value.
     * If it is not being pushed, the program is sent a False value.
     *
     * These constants give a name to the numbers that make sense to the Robot program.
     * Example: 
     *   Button A on the joystick must be referred to as button 1 in the program.
     *   The Top Left Trigger on the Joystick must be referred to as button 5 in the program.
     *
     */

    public static int A = 1;               // raw btn 1 -> A
    public static int B = 2;               // raw btn 2 -> B
    public static int X = 3;               // raw btn 3 -> X 
    public static int Y = 4;               // raw btn 4 -> Y 
    public static int topLeftTrigger = 5;  // raw btn 5 -> top left trigger
    public static int topRightTrigger = 6; // raw btn 6 -> top right trigger
    public static int back = 7;            // raw btn 7 -> back
    public static int start = 8;           // raw btn 8 -> start
    public static int leftStick = 9;       // raw btn 9 -> Push left stick
    public static int rightStick = 10;     // raw btn 10 -> Push right stick
}
