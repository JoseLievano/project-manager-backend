package com.bgsystem.bugtracker.exeptions;

public class ElementNotFoundException extends Exception{

    public ElementNotFoundException(String message){super(message);}
    public ElementNotFoundException(){
        super("This element doesn't exist");
    }

}
