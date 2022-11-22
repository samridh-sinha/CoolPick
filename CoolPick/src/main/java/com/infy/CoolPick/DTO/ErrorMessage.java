package com.infy.CoolPick.DTO;

import lombok.Getter;
import lombok.Setter;


public class ErrorMessage {

    @Getter @Setter
    private Integer errorCode;
    @Getter @Setter
    private String errorMessage;

}
