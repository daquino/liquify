package com.refactify.arguments;

public class TargetFileNameBuilder {
    public String buildFilename(final ConversionArguments arguments) {
        String fileName;
        String source = arguments.getSource();
        String baseFileName = source.substring(0, source.lastIndexOf("."));
        if(arguments.getConversionType().equals(ConversionArguments.ConversionType.SQL)) {
            fileName = String.format("%s.%s.%s", baseFileName, arguments.getDatabase(),
                    arguments.getConversionType().getExtension());
        }
        else {
            fileName = String.format("%s.%s", baseFileName, arguments.getConversionType().getExtension());
        }
        return fileName;
    }
}
