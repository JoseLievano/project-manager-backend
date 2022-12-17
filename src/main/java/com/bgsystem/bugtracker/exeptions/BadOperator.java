package com.bgsystem.bugtracker.exeptions;

public class BadOperator extends Exception{

    public BadOperator(String message){ super(message);}
    public BadOperator(){
        super("Bad operator");
    }

}
