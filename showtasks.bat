call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openbrowser
echo Cannot compile the script.
goto fail

:openbrowser
start chrome http://localhost:8080/crud/v1/task/getTasks
goto end

:fail
echo.
echo There were errors.

:end
echo.
echo The SHOWTASKS script is completed.
