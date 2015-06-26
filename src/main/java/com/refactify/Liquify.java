package com.refactify;

import com.refactify.arguments.ConversionArguments;
import com.refactify.arguments.ConversionArgumentsParser;
import com.refactify.printer.UsagePrinter;
import liquibase.changelog.ChangeSet;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.exception.LiquibaseException;
import liquibase.parser.ChangeLogParser;
import liquibase.parser.ChangeLogParserFactory;
import liquibase.resource.FileSystemResourceAccessor;
import liquibase.resource.ResourceAccessor;
import liquibase.serializer.ChangeLogSerializer;
import liquibase.serializer.ChangeLogSerializerFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Liquify {
    private final static ConversionArgumentsParser parser = new ConversionArgumentsParser();
    private final static UsagePrinter usagePrinter = new UsagePrinter();
    public static void main(final String[] args) {
        ConversionArguments conversionArguments = parser.parseArguments(args);

        if(conversionArguments.areValid()) {
            convertDatabaseChangeLog(conversionArguments);
        }
        else {
            usagePrinter.printUsage();
        }
    }

    private static void convertDatabaseChangeLog(final ConversionArguments conversionArguments) {
        try {
            ResourceAccessor resourceAccessor = new FileSystemResourceAccessor(System.getProperty("user.dir"));
            ChangeLogParser parser = ChangeLogParserFactory.getInstance().getParser(conversionArguments.getSource(), resourceAccessor);
            DatabaseChangeLog changeLog = parser.parse(conversionArguments.getSource(), null, resourceAccessor);
            ChangeLogSerializer serializer = ChangeLogSerializerFactory.getInstance().getSerializer(conversionArguments.getDestination());
            for (ChangeSet set : changeLog.getChangeSets()) {
                set.setFilePath(conversionArguments.getDestination());
            }
            serializer.write(changeLog.getChangeSets(), new FileOutputStream(conversionArguments.getDestination()));
        }
        catch (LiquibaseException e) {
            e.printStackTrace();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
