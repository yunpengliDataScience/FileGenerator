@echo off
setlocal enabledelayedexpansion

REM Directory containing XML files
set XML_DIR=C:\Projects\FileGenerator\sourceXMLFiles

REM Output directory for generated XSD files
set OUTPUT_DIR=C:\Projects\FileGenerator\generatedXSDFiles

REM Iterate over XML files in the directory
for %%f in ("%XML_DIR%\*.xml") do (
  REM Extract filename without extension
  set filename=%%~nf
  
  REM echo !filename!

  REM Run inst2xsd to generate XSD file
  C:\Projects\FileGenerator\Tool\xmlbeans-5.0.3\bin\inst2xsd %%f -outDir %OUTPUT_DIR% -outPrefix %%~nf
)