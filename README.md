# Liquify

Liquify is a command-line tool to convert [Liquibase changelog files](http://www.liquibase.org/documentation/databasechangelog.html)
between the supported file formats(xml,yaml,json,sql).  The project comes with the
liquify bash shell script as the default way to launch the tool.  However, if you do not
have BASH on your machine, you can still launch the tool using the java command as well (java -jar liquify.jar [-options] <source>).

_Note: When using the sql change log file type, not all bundled change commands are supported for all the different
database types (i.e. createSequence, addAutoIncrement, etc).  If the tool fails to build when trying to,
convert to the sql type, it could be because of the desired database type not supporting a bundle change command
in the source change log file._

### Usage
#### Bash shell
```
liquify [-options] <source>
  Options:
  
    Required:
      -t,--type         Target changelog file type (xml,yaml,json,sql).
      
    Conditional:
      -db,--database    Database type to use when using the sql type (i.e oracle, h2, etc).
      
Example Usage:
  liquify -t sql -db oracle db.changelog.xml (db.changelog.xml => db.changelog.oracle.sql)
```

#### Java 
```
java -jar liquify.jar [-options] <source>
  Options:
  
    Required:
      -t,--type         Target changelog file type (xml,yaml,json,sql).
      
    Conditional:
      -db,--database    Database type to use when using the sql type (i.e oracle, h2, etc).
      
Example Usage:
  java -jar liquify.jar -t sql -db oracle db.changelog.xml (db.changelog.xml => db.changelog.oracle.sql)
```

### Building
The project requires JDK 1.6+ and Gradle as the build tool.  I've included a gradle wrapper command for this project.
For those who haven't used gradle, the gradle wrapper will download gradle to your machine and proxy gradle commands to the version of
gradle it downloads; so, you don't have to find gradle and install it yourself.  You can just run gradle wrapper command
```./gradlew dist``` and it will do the rest for you.
The __dist__ task will create a build/distributions directory with a liquify*.zip file containing all you need to use
the tool.