This project generates Java source code, XSD files, and batch scripts to eventually XML unmarshall beans and JPA beans that can carry XML data and insert into database tables.


-----------------------------------------------------------------------------------------------------
Create Java Entity Classes from XML files to be used as XML unmarshalling beans and JPA entity beans: 

1. Run FileGenerator.java to generate files.
2. Run batchGenerateXSDs.bat to generate XSDs.
3. Run batchGenerateXMLJavaBeans.bat to generate Java Beans.
4. In the generated java class, remove content of @XmlType and add @XmlRootElement(name="ENTITY_NAME").
5. In the generated java class, add @Entity in class level and add @Id to id field.