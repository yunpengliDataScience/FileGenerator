package org.dragon.yunpeng;

public class Column {

	private String fieldName;
	private String methodName;
	private String javaType;
	private boolean primaryKey;
	private String tableColumnName;

	public Column(String fieldName, String methodName, String javaType, String tableColumnName) {
		this.fieldName = fieldName;
		this.methodName = methodName;
		this.javaType = javaType;
		this.tableColumnName = tableColumnName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public boolean isPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getTableColumnName() {
		return tableColumnName;
	}

	public void setTableColumnName(String tableColumnName) {
		this.tableColumnName = tableColumnName;
	}
}
