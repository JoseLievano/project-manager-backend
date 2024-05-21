package com.bgsystem.bugtracker.exeptions;

public class InvalidDeleteOperation extends Exception{

    public InvalidDeleteOperation(String message){
        super(message);
    }

    public InvalidDeleteOperation(){
        super("Can't be deleted");
    }

}
