# liquifier

Liquifier is a command-line tool to convert Liquibase changelog files
between the supported file formats(xml,yaml,json,sql).

Usage:

liquify [-options] <source>

Options:
  Required:
    -t, --type       Change log file type to convert to (xml,yaml,json,sql).

  Optional:
    -db, --database  Database type to use when using the sql type (i.e oracle, h2, etc).

Example Usage:
liquify -t sql -db oracle db.changelog.xml (creates db.changelog.oracle.sql with the oracle dialect).
