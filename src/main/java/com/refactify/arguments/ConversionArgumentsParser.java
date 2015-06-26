package com.refactify.arguments;

public class ConversionArgumentsParser {

    public ConversionArguments parseArguments(String[] arguments) {
        ConversionArguments conversionArguments = new ConversionArguments();
        try {
            conversionArguments.setSource(arguments[arguments.length - 1]);
            for (int i = 0; i < arguments.length - 1; i++) {
                String argument = arguments[i];
                if (argument.equals("-t") || argument.equals("--type")) {
                    String value = arguments[++i];
                    conversionArguments.setConversionType(ConversionArguments.ConversionType.fromString(value));
                }
                else if (argument.equals("-db") || argument.equals("--database")) {
                    String value = arguments[++i];
                    conversionArguments.setDatabase(value);
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException exc) {
            System.out.println("Invalid arguments.");
        }
        return conversionArguments;
    }
}
