@echo off

setlocal
set "LIB=lib\gson-2.13.1.jar"

if /i "%~1"=="doc" (
    
    javadoc -d "%~dp0doc" -sourcepath src/main/java -subpackages model -classpath "%~dp0%LIB%"

    if /i "%~2"=="open" (
        start "" "%~dp0doc\index.html"
    )

) else if /i "%~1"=="run" (

    java -XX:+ShowCodeDetailsInExceptionMessages -cp "target/classes;%LIB%" visual.tui.Main

) else if /i "%~1"=="test" (

    if /i "%~2"=="" (
        java -XX:+ShowCodeDetailsInExceptionMessages -cp "target/test-classes;%LIB%" Test
    ) else (
        java -XX:+ShowCodeDetailsInExceptionMessages -cp "target/test-classes;%LIB%" "%~2"
    )

) else (

    java -XX:+ShowCodeDetailsInExceptionMessages -cp "target/classes;%LIB%" "%~1"
    
)