package com.refactify.arguments;

import java.util.ArrayList;
import java.util.List;

public class ConversionArgumentsParser {

    public ConversionArguments parseArguments(String[] arguments) {
        ConversionArguments conversionArguments = new ConversionArguments();
        try {
            for (int i = 0; i < arguments.length; i++) {
                String argument = arguments[i];
                if (argument.equals("-s") || argument.equals("--source")) {
                    String value = arguments[++i];
                    conversionArguments.setSource(value);
                }
                else if (argument.equals("-d") || argument.equals("--dest")) {
                    String value = arguments[++i];
                    conversionArguments.setDestination(value);
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException exc) {
            System.out.println("Invalid arguments.");
        }
        return conversionArguments;
    }
}
