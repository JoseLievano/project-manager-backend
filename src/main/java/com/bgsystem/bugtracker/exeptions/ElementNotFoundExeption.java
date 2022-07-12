package com.bgsystem.bugtracker.exeptions;

public class ElementNotFoundExeption extends Exception{

    public ElementNotFoundExeption(String message){super(message);}
    public ElementNotFoundExeption(){
        super("This element doesn't exist");
    }

}
