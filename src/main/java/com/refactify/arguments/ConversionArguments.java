package com.refactify.arguments;

public class ConversionArguments {
    private String source;
    private ConversionType type;
    private String database;
    private static final String[] validFileExtensions = new String[] {".xml", ".yaml", ".json", ".sql"};
    private static final String[] validDatabases =
            new String[] {"db2", "derby", "firebird", "h2", "hsql", "informix", "mssql", "mariadb", "mysql", "oracle",
                    "postgresql", "sqlite", "asany", "sybase"};

    public String getSource() {
        return source;
    }

    public void setSource(final String source) {
        this.source = source;
    }

    public ConversionType getConversionType() {
        return type;
    }

    public void setConversionType(final ConversionType type) {
        this.type = type;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(final String database) {
        this.database = database;
    }

    public boolean areValid() {
        return isValidPath(source) && hasValidType();
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

    public boolean hasValidType() {
        if(type == null) {
            return false;
        }
        else if(!type.equals(ConversionType.SQL)) {
            return true;
        }
        else if(type.equals(ConversionType.SQL) && databaseIsSupported()) {
            return true;
        }
        else {
            return false;
        }

    }

    public boolean databaseIsSupported() {
        boolean validDatabase = false;
        if(database != null) {
            for (String db : validDatabases) {
                if (database.equals(db)) {
                    validDatabase = true;
                    break;
                }
            }
        }
        return validDatabase;
    }

    public enum ConversionType {
        XML("xml"), YAML("yaml"), JSON("json"), SQL("sql");

        ConversionType(final String extension) {
            this.extension = extension;
        }
        private String extension;

        public String getExtension() {
            return extension;
        }

        public static ConversionType fromString(final String value) {
            if("xml".equals(value)) {
                return XML;
            }
            else if("yaml".equals(value)) {
                return YAML;
            }
            else if("json".equals(value)) {
                return JSON;
            }
            else if ("sql".equals(value)) {
                return SQL;
            }
            else
                return null;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ConversionArguments{");
        sb.append("source='").append(source).append('\'');
        sb.append(", type=").append(type);
        sb.append(", database='").append(database).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
