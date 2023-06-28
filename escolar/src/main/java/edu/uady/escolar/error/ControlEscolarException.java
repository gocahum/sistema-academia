package edu.uady.escolar.error;

public class ControlEscolarException extends Exception{
    private int code;
    private String message;

    public ControlEscolarException (String message){
        super(message);
    }
}
