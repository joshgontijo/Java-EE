package com.josue.cdi.lookup;

import javax.inject.Named;

/**
 * @author Josue Gontijo .
 */
@Named("controlB")
public class ControlB implements Control{
    @Override
    public String getMessage() {
        return "Control B message";
    }
}
