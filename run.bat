@echo off

if /i "%~1"=="doc" (
    javadoc -d "%~dp0doc" -sourcepath src/main/java -subpackages main -classpath "%~dp0lib/gson-2.13.1.jar"

    if /i "%~2"=="open" (
        start "" "%~dp0doc\index.html"
    )

    exit
)

if /i "%~1"=="run" (
    java -XX:+ShowCodeDetailsInExceptionMessages -cp target/classes;lib/gson-2.13.1.jar visual.tui.Main

    exit
)

if /i "%~1"=="" ( exit )

java -XX:+ShowCodeDetailsInExceptionMessages -cp target/classes;lib/gson-2.13.1.jar "%~1"