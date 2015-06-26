package com.refactify;

import com.refactify.arguments.ConversionArguments;
import com.refactify.arguments.ConversionArgumentsParser;
import com.refactify.arguments.TargetFileNameBuilder;
import com.refactify.printer.UsagePrinter;
import liquibase.changelog.ChangeLogParameters;
import liquibase.changelog.ChangeSet;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.exception.LiquibaseException;
import liquibase.parser.ChangeLogParser;
import liquibase.parser.ChangeLogParserFactory;
import liquibase.resource.FileSystemResourceAccessor;
import liquibase.resource.ResourceAccessor;
import liquibase.serializer.ChangeLogSerializer;
import liquibase.serializer.ChangeLogSerializerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Liquify {
    private final static ConversionArgumentsParser parser = new ConversionArgumentsParser();
    private final static UsagePrinter usagePrinter = new UsagePrinter();
    private final static TargetFileNameBuilder targetFileNameBuilder = new TargetFileNameBuilder();

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
        String targetFileName = targetFileNameBuilder.buildFilename(conversionArguments);
        try {
            ResourceAccessor resourceAccessor = new FileSystemResourceAccessor(System.getProperty("user.dir"));
            ChangeLogParser parser = ChangeLogParserFactory.getInstance().getParser(conversionArguments.getSource(), resourceAccessor);
            DatabaseChangeLog changeLog = parser.parse(conversionArguments.getSource(), new ChangeLogParameters(), resourceAccessor);
            ChangeLogSerializer serializer = ChangeLogSerializerFactory.getInstance().getSerializer(targetFileName);
            for (ChangeSet set : changeLog.getChangeSets()) {
                set.setFilePath(targetFileName);
            }
            serializer.write(changeLog.getChangeSets(), new FileOutputStream(targetFileName));
        }
        catch (LiquibaseException e) {
            System.out.println("There was a problem parsing the source file.");
            deleteTargetFile(targetFileName);
        }
        catch (IOException e) {
            System.out.println("There was a problem serializing the source file.");
            deleteTargetFile(targetFileName);
        }
        catch(IllegalStateException e) {
            System.out.println(String.format("Database generator for type '%s' was not found.",
                    conversionArguments.getDatabase()));
            deleteTargetFile(targetFileName);
        }
    }

    private static void deleteTargetFile(final String targetFileName) {
        try {
            Files.deleteIfExists(Paths.get(targetFileName));
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
