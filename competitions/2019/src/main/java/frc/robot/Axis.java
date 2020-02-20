package frc.robot;

public class Axis{
    /*
     * DO NOT EDIT.   See RobotConstants.java to change the mappings between buttons and robot engines.
     * 
     * These constants map the Joystick sticks and triggers to numbers that make sense to the Robot program.
     * 
     * An axis is a control that registers a range of movements on a joystick. While a button can either
     * be on or off, Axises will pick up how far a stick was moved, or how hard a trigger was pressed.
     *  
     * See http://controls.coderedrobotics.com/programminglessons/4.html for more details.
     *
     * An Axis will have have a different value depending on how far the user
     * is pushing it.  
     * If it is not being pushed at all, the program is sent a 0.0 value.
     * If it is being pushed forward, the program is sent a value between 0.0 and 1.0,
     * depending on how far it's being pushed.
     * For instance, if the axis was being pushed half-way, the program would be sent a value of 0.5.
     * If it is being pushed backwards, the program is sent a value between 0.0 and -1.0.
     * For instance, if the axis was being pushed 1/3 of the way towards you, 
     * the program would be sent a value of -0.33.
     *
     */

public static int bottomLeftTrigger = 2;  // axis 2 -> bottom left trigger.  
                                          // All the way pushed in = 1.0
public static int bottomRightTrigger = 3; // axis 3 -> bottom right trigger. 
                                          // All the way pushed in = 1.0
public static int leftStickSideways = 0;  // axis 0 -> Left Stick  X - Left to right. 
                                          // All the way left = -1.0. All the way right = 1.0
public static int leftStickFrontBack = 1; // axis 1 -> Left Stick  Y - forwards/backwards.
                                          // All the way forwards = 1.0. All the way backwards = -1.0.
public static int rightStickSideways = 4; // axis 4 -> Right Stick X - Left to right.
                                          // All the way left = -1.0. All the way right = 1.0
public static int rightStickFrontBack = 5; // axis 5 -> Right Stick Y - forwards/backwards.
                                          // All the way forwards = 1.0. All the way backwards = -1.0.
} 