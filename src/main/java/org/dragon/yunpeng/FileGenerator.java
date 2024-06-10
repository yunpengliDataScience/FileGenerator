package org.dragon.yunpeng;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileGenerator {

	public static void main(String[] args) throws IOException {

		// Create folders if they don't exist.
		createFolders();

		// Retrieve entity names.
		List<String> entityNameList = getEntityNames();

		// Generate XML binding files.
		for (String entityName : entityNameList) {
			generateXMLBindingFile(entityName);
		}

		// Generate JPA Repository Java files.
		for (String entityName : entityNameList) {
			generateJPARepositoryJavaFile(entityName);
		}

		// Generate batch script that uses inst2xd to create XSDs.
		generateInst2xdScript();

		// Generate batch script that uses JAXB Xjc to create Java beans that map
		// corresponding XML.
		generateXjcScript();

	}

	private static void createFolderIfNotExist(String folderPath) {
		
		File folder = new File(folderPath);

        // Check if the folder does not exist
        if (!folder.exists()) {
            // Create the folder and its parent directories if they do not exist
            boolean created = folder.mkdirs();
        }
	}
	
	private static void createFolders() {
		String workingDirectory = System.getProperty("user.dir");
		
		String jpaRepositoryFileDirectory = workingDirectory + File.separator + "generatedJPARepositoryJavaFiles" + File.separator;
		createFolderIfNotExist(jpaRepositoryFileDirectory);
		
		String xsdFileDirectory = workingDirectory + File.separator + "generatedXSDFiles" + File.separator;
		createFolderIfNotExist(xsdFileDirectory);
		
		String sourceXMLDirectoryPath = workingDirectory + File.separator + "sourceXMLFiles" + File.separator;
		createFolderIfNotExist(sourceXMLDirectoryPath);
		
		String scriptFileDirectory = workingDirectory + File.separator + "generatedBatchScriptFiles" + File.separator;
		createFolderIfNotExist(scriptFileDirectory);
		
		String generatedXMLBindingDirectoryPath = workingDirectory + File.separator + "generatedXMLBindingFiles";
		createFolderIfNotExist(generatedXMLBindingDirectoryPath);
		
		String xmlJavaBeanDirectoryPath = workingDirectory + File.separator + "generatedXMLJavabeans";
		createFolderIfNotExist(xmlJavaBeanDirectoryPath);
		
		String generatedJPAEntityDirectoryPath = workingDirectory + File.separator + "generatedJPAEntityFiles";
		createFolderIfNotExist(generatedJPAEntityDirectoryPath);
	}
	
	private static void generateJPARepositoryJavaFile(String entityName) throws IOException {
		
		String template = "package navy.ssp.ma.tfrUtility.dataImport.repositories;\r\n"
				+ "\r\n"
				+ "import org.springframework.data.repository.CrudRepository;\r\n"
				+ "import org.springframework.stereotype.Repository;\r\n"
				+ "\r\n"
				+ "import navy.ssp.ma.tfrUtility.dataImport.entities.%s;\r\n"
				+ "\r\n"
				+ "@Repository\r\n"
				+ "public interface %s_Repository extends CrudRepository<%s, Long> {\r\n"
				+ "\r\n"
				+ "}\r\n"
				+ "";
		
		String finalString = String.format(template, entityName, entityName, entityName);
		System.out.println(finalString);
		
		String workingDirectory = System.getProperty("user.dir");
		System.out.println(workingDirectory);
		String jpaRepositoryFileDirectory = workingDirectory + File.separator + "generatedJPARepositoryJavaFiles" + File.separator;
		
		String fileName = jpaRepositoryFileDirectory + entityName + "_Repository.java";
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		writer.write(finalString);

		writer.close();
	}
	
	private static void generateXMLBindingFile(String entityName) throws IOException {
		
		String workingDirectory = System.getProperty("user.dir");
		System.out.println(workingDirectory);
		String xsdFileDirectory = workingDirectory + File.separator + "generatedXSDFiles" + File.separator;
		
		String template = "<jxb:bindings xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns:jxb=\"http://java.sun.com/xml/ns/jaxb\" version=\"2.1\">\r\n"
				+ "	\r\n"
				+ "	<jxb:globalBindings underscoreBinding=\"asCharInWord\" enableJavaNamingConventions = \"true\"/>\r\n"
				+ "	\r\n"
				+ "	<jxb:bindings schemaLocation=\"file:///%s%s0.xsd\">\r\n"
				+ "            <jxb:bindings node=\"//xs:complexType[@name='NewDataSetType']\">\r\n"
				+ "                <jxb:class name=\"NewDataSet\"/>\r\n"
				+ "            </jxb:bindings>\r\n"
				+ "			<jxb:bindings node=\"//xs:complexType[@name='%sType']\">\r\n"
				+ "                <jxb:class name=\"%s\"/>\r\n"
				+ "            </jxb:bindings>\r\n"
				+ "    </jxb:bindings>\r\n"
				+ "\r\n"
				+ "</jxb:bindings>";
		
		
		
		String finalString = String.format(template, xsdFileDirectory, entityName, entityName, entityName);
		System.out.println(finalString);


		String bindingFileDirectory = workingDirectory + File.separator + "generatedXMLBindingFiles" + File.separator;
		
		String fileName = bindingFileDirectory + entityName + "_Bindings.xml";
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		writer.write(finalString);

		writer.close();
	}
	
	private static List<String> getEntityNames() {

		String workingDirectory = System.getProperty("user.dir");
		System.out.println(workingDirectory);
		String directoryPath = workingDirectory + File.separator + "sourceXMLFiles" + File.separator;

		List<String> entityList = new ArrayList<String>();

		File folder = new File(directoryPath);
		File[] listOfFiles = folder.listFiles();

		if (listOfFiles != null) {
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {

					String fileName = listOfFiles[i].getName();

					int index = fileName.lastIndexOf('.');

					String entityName = fileName.substring(0, index);
					entityList.add(entityName);

					System.out.println(entityName);
				} else if (listOfFiles[i].isDirectory()) {
					System.out.println("Directory " + listOfFiles[i].getName());
				}
			}
		}

		return entityList;
	}

	private static void generateInst2xdScript() throws IOException {
		
		String workingDirectory = System.getProperty("user.dir");
		System.out.println(workingDirectory);
		String sourceXMLDirectoryPath = workingDirectory + File.separator + "sourceXMLFiles";
		String generatedXSDDirectoryPath = workingDirectory + File.separator + "generatedXSDFiles";
		String inst2xdDirectoryPath = workingDirectory + File.separator + "Tool" + File.separator + "xmlbeans-5.0.3" + File.separator + "bin" + File.separator;
		
		String template = "@echo off\r\n"
				+ "setlocal enabledelayedexpansion\r\n"
				+ "\r\n"
				+ "REM Directory containing XML files\r\n"
				+ "set XML_DIR=%s\r\n"
				+ "\r\n"
				+ "REM Output directory for generated XSD files\r\n"
				+ "set OUTPUT_DIR=%s\r\n"
				+ "\r\n"
				+ "REM Iterate over XML files in the directory\r\n"
				+ "for %%%%f in (\"%%XML_DIR%%\\*.xml\") do (\r\n"
				+ "  REM Extract filename without extension\r\n"
				+ "  set filename=%%%%~nf\r\n"
				+ "  \r\n"
				+ "  REM echo !filename!\r\n"
				+ "\r\n"
				+ "  REM Run inst2xsd to generate XSD file\r\n"
				+ "  %sinst2xsd %%%%f -outDir %%OUTPUT_DIR%% -outPrefix %%%%~nf\r\n"
				+ ")";
		
		String finalString = String.format(template, sourceXMLDirectoryPath, generatedXSDDirectoryPath, inst2xdDirectoryPath);
		System.out.println(finalString);
		
		String scriptFileDirectory = workingDirectory + File.separator + "generatedBatchScriptFiles" + File.separator;
		
		String fileName = scriptFileDirectory + "batchGenerateXSDs.bat";
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		writer.write(finalString);

		writer.close();
	}
	
private static void generateXjcScript() throws IOException {
		
		String workingDirectory = System.getProperty("user.dir");
		System.out.println(workingDirectory);
		
		String sourceXMLDirectoryPath = workingDirectory + File.separator + "sourceXMLFiles";
		String generatedXSDDirectoryPath = workingDirectory + File.separator + "generatedXSDFiles";
		String generatedXMLBindingDirectoryPath = workingDirectory + File.separator + "generatedXMLBindingFiles";
		String outputDirectoryPath = workingDirectory + File.separator + "generatedXMLJavabeans";
		
		String template = "@echo off\r\n"
				+ "setlocal enabledelayedexpansion\r\n"
				+ "\r\n"
				+ "REM Directory containing XML files\r\n"
				+ "set XML_DIR=%s\r\n"
				+ "\r\n"
				+ "REM Directory containing XSD files\r\n"
				+ "set XSD_DIR=%s\r\n"
				+ "\r\n"
				+ "REM Directory containing XML Binding files\r\n"
				+ "set XML_BINDING_DIR=%s\r\n"
				+ "\r\n"
				+ "REM Output directory for generated XSD files\r\n"
				+ "set OUTPUT_DIR=%s\r\n"
				+ "\r\n"
				+ "REM Iterate over XML files in the directory\r\n"
				+ "for %%%%f in (\"%%XML_DIR%%\\*.xml\") do (\r\n"
				+ "  REM Extract filename without extension\r\n"
				+ "  set filename=%%%%~nf\r\n"
				+ "  \r\n"
				+ "  REM echo !filename!\r\n"
				+ "\r\n"
				+ "  REM Run inst2xsd to generate XSD file\r\n"
				+ "xjc -d %%OUTPUT_DIR%% -p org.dragon.yunpeng.project.entities -b \"%%XML_BINDING_DIR%%\\%%%%~nf_Bindings.xml\" \"%%XSD_DIR%%\\%%%%~nf0.xsd\"\r\n"
				+ ")";
		
		String finalString = String.format(template, sourceXMLDirectoryPath, generatedXSDDirectoryPath, generatedXMLBindingDirectoryPath, outputDirectoryPath);
		System.out.println(finalString);
		
		String scriptFileDirectory = workingDirectory + File.separator + "generatedBatchScriptFiles" + File.separator;
		
		String fileName = scriptFileDirectory + "batchGenerateXMLJavaBeans.bat";
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		writer.write(finalString);

		writer.close();
	}
}
