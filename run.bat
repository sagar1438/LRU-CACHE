@echo off
echo ==============================
echo Building LRU Cache project...
echo ==============================
mvn clean package

if %errorlevel% neq 0 (
    echo Build failed!
    pause
    exit /b %errorlevel%
)

echo ==============================
echo Running Main class...
echo ==============================
java -cp target/classes com.sagar.cache.Main

echo ==============================
echo Running all tests...
echo ==============================
mvn test

pause
