call runcrud.bat
if "%ERRORLEVEL%" == "0" goto open
echo.
echo Cannot run runcurd
goto fail

:open
start "Pulpit\chrome.exe" "http://localhost:8080/crud/v1/task/getTasks"

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.