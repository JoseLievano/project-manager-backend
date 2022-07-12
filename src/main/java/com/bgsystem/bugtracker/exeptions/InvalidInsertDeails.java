package com.bgsystem.bugtracker.exeptions;

public class InvalidInsertDeails extends Exception{

    public InvalidInsertDeails(String message){
        super(message);
    }
    public InvalidInsertDeails(){super("The details are not valid, is not possible to create this element");}

}
