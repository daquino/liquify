package com.refactify.printer;

public class UsagePrinter {
    public void printUsage() {
        System.out.println();
        System.out.println("Usage: ");
        System.out.println("  liquify [-options] <source>");
        System.out.println("");
        System.out.println("  Options:");
        System.out.println("    Required:");
        System.out.println("      -t,--type         Target changelog file type(xml,yaml,json,sql).");
        System.out.println("    Conditional:");
        System.out.println("      -db,--database    Database type to use when using the sql type (i.e oracle, h2, etc).");
        System.out.println("\n" +
                "Example Usage:\n" +
                "liquify -t sql -db oracle db.changelog.xml (creates db.changelog.oracle.sql with the oracle dialect).");
        System.out.println();
    }
}
