@echo off
setlocal enabledelayedexpansion

REM Directory containing XML files
set XML_DIR=C:\Projects\FileGenerator\sourceXMLFiles

REM Directory containing XSD files
set XSD_DIR=C:\Projects\FileGenerator\generatedXSDFiles

REM Directory containing XML Binding files
set XML_BINDING_DIR=C:\Projects\FileGenerator\generatedXMLBindingFiles

REM Output directory for generated XSD files
set OUTPUT_DIR=C:\Projects\FileGenerator\generatedXMLJavabeans

REM Iterate over XML files in the directory
for %%f in ("%XML_DIR%\*.xml") do (
  REM Extract filename without extension
  set filename=%%~nf
  
  REM echo !filename!

  REM Run inst2xsd to generate XSD file
xjc -d %OUTPUT_DIR% -p org.dragon.yunpeng.project.entities -b "%XML_BINDING_DIR%\%%~nf_Bindings.xml" "%XSD_DIR%\%%~nf0.xsd"
)