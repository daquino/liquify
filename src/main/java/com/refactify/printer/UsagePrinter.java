package com.refactify.printer;

public class UsagePrinter {
    public void printUsage() {
        System.out.println("Liquify can be use to convert Liquibase changelog " +
                "files between the supported file formats: xml,yaml,json,sql.");
        System.out.println("====================================");
        System.out.println("Usage: ");
        System.out.println("  liquify -s <source> -d <dest>");
        System.out.println("");
        System.out.println("  Options:");
        System.out.println("    -s,--source          Database changelog source file.");
        System.out.println("    -d,--dest            Database changelog destination file.");
    }
}
