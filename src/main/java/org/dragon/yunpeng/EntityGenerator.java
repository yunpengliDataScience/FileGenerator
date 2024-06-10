package org.dragon.yunpeng;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class EntityGenerator {
    
    // Map SQL types to Java types
    private static String getJavaType(String sqlType) {
        switch (sqlType) {
            case "VARCHAR":
            case "VARCHAR2":
            case "CHAR":
            case "TEXT":
                return "String";
            case "INT":
            case "INTEGER":
                return "int";
            case "BIGINT":
            case "NUMBER":
                return "long";
            case "DOUBLE":
                return "double";
            case "FLOAT":
                return "float";
            case "DATE":
                return "java.sql.Date";
            case "TIMESTAMP":
                return "java.sql.Timestamp";
            // Add more mappings as needed
            default:
                return "Object";
        }
    }
    
    public static boolean isColumnPrimaryKey(Connection conn, String tableName, String columnName) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();
        try (ResultSet pkResultSet = metaData.getPrimaryKeys(null, null, tableName)) {
            while (pkResultSet.next()) {
                String pkColumnName = pkResultSet.getString("COLUMN_NAME");
                if (pkColumnName.equalsIgnoreCase(columnName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Database connection details
    	String jdbcUrl = "jdbc:h2:file:./HR_Database;MODE=Oracle"; // Adjust the URL as per your setup
        String username = "sa";
        String password = "sa";
        String tableName = "COUNTRIES"; // Replace with your table name
        String packageName = "com.example"; // Replace with your package name
        
        //String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe"; // Adjust the URL as per your setup
        //String username = "hr";
        //String password = "hr";
        //String tableName = "COUNTRIES"; // Replace with your table name
        //String packageName = "com.example"; // Replace with your package name


        try {
            // Load the H2 driver
        	Class.forName("org.h2.Driver");
        	
        	//Class.forName("oracle.jdbc.driver.OracleDriver");

            // Establish connection
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Retrieve metadata
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getColumns(null, null, tableName, null);

            // Collect column information
            List<Column> columns = new ArrayList<>();
            while (resultSet.next()) {
                String columnName = resultSet.getString("COLUMN_NAME");
                String columnType = resultSet.getString("TYPE_NAME");
                String javaType = getJavaType(columnType);
                String fieldName = columnName; //TODO
                String methodName = columnName; //TODO

                Column column = new Column(fieldName, methodName, javaType, columnName);
                
                System.out.println("Column: " + columnName + ", Field: " + fieldName + ", Method: " + methodName + ", Type: " + javaType);
                
                boolean isPrimaryKey = isColumnPrimaryKey(connection, tableName, columnName);
                
                if(isPrimaryKey) {
                	column.setPrimaryKey(isPrimaryKey);
                }
                
                columns.add(column);
            }

            // Initialize Velocity
            Velocity.init();

            // Prepare context
            VelocityContext context = new VelocityContext();
            context.put("packageName", packageName);
            context.put("tableName", tableName);
            context.put("className", tableName.substring(0, 1).toUpperCase() + tableName.substring(1).toLowerCase());
            context.put("columns", columns);

            // Load template
            Template template = Velocity.getTemplate("src/main/resources/EntityTemplate.vm");

            // Merge template
            StringWriter writer = new StringWriter();
            
            Velocity.mergeTemplate("src/main/resources/EntityTemplate.vm", null, context, writer);
            //template.merge(context, writer);

            // Write to file
            String className = tableName;
            
            String workingDirectory = System.getProperty("user.dir");
    		System.out.println(workingDirectory);
    		String fileDirectory = workingDirectory + File.separator + "generatedJPAEntityFiles" + File.separator;
    		
            // Write class content to a file
            try (FileWriter fileWriter = new FileWriter(fileDirectory + className + ".java")) {
                fileWriter.write(writer.toString());
            }
           

            System.out.println("Java entity class generated successfully!");

            // Close resources
            resultSet.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
