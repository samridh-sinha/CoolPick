package com.infy.CoolPick.Exceptions;

public class ProductNotFoundException extends Exception{

    private static final long serialVersionUID = 1L;



    public ProductNotFoundException(String errors){
        super(errors);
    }

    public ProductNotFoundException(){
        super();
    }


}
