package com.bgsystem.bugtracker.exeptions;

public class ElementAlreadyExist extends Exception{

    public ElementAlreadyExist(String message){ super(message);}
    public ElementAlreadyExist(){
        super("Element already exist");
    }

}
