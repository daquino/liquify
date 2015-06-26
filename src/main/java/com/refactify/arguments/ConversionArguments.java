package com.refactify.arguments;

public class ConversionArguments {
    private String source;
    private String destination;
    private static final String[] validFileExtensions = new String[] {"xml", "yaml", "json", "sql"};

    public String getSource() {
        return source;
    }

    public void setSource(final String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(final String destination) {
        this.destination = destination;
    }

    public boolean areValid() {
        return isValidPath(source) && isValidPath(destination);
    }

    private boolean isValidPath(final String path) {
        return path != null && hasValidExtension(path);
    }

    private boolean hasValidExtension(final String path) {
        boolean validExtension = false;
        for(String fileExtension: validFileExtensions) {
            if(path.endsWith(fileExtension)) {
                validExtension = true;
                break;
            }
        }
        return validExtension;
    }
}
