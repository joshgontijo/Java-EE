package com.josue.cdi.lookup;

import javax.inject.Named;

/**
 * @author Josue Gontijo .
 */
@Named("controlA")
public class ControlA implements Control{
    @Override
    public String getMessage() {
        return "Control A message";

    }
}
