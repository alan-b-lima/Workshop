@echo off

if "%~1"=="" (
    echo No argument provided. Usage: run.bat [option]
    exit /b 1
)

set "arg=%~1"
if /i "%arg%"=="start" (

    echo Starting the application...

) else if /i "%arg%"=="stop" (

    echo Stopping the application...

) else if /i "%arg%"=="status" (
    
    echo Checking the application status...

) else (

    echo Invalid option: %arg%
    echo Valid options are: start, stop, status
    exit /b 1
    
)

exit /b 0