package org.apache.commons.dbutils;

import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.dbutils.BeanProcessor;

public class HumpPropertyBeanProcessor extends BeanProcessor {

	private final Map<Class<?>, SpecialTypeHandle<?>> specialClassMap;
	
    public HumpPropertyBeanProcessor() {
        this(new HashMap<Class<?>, SpecialTypeHandle<?>>());
    }
	
	public HumpPropertyBeanProcessor(Map<Class<?>, SpecialTypeHandle<?>> specialClassMap) {
		super();
        if (specialClassMap == null) {
            throw new IllegalArgumentException("The SpecialClassMap map cannot be null");
        }
        this.specialClassMap = specialClassMap;
	}
	
	@Override
	protected int[] mapColumnsToProperties(ResultSetMetaData rsmd,
			PropertyDescriptor[] props) throws SQLException {
        int cols = rsmd.getColumnCount(); 
        int columnToProperty[] = new int[cols + 1];
        Arrays.fill(columnToProperty, PROPERTY_NOT_FOUND);
        
		Map<String, Integer> properMap = new HashMap<String, Integer>();
		for (int i = 0; i < props.length; i++) {
			properMap.put(props[i].getName(), i);
		}

		for (int col = 1; col <= cols; col++) {
			String columnName = rsmd.getColumnLabel(col);
			if (null == columnName || 0 == columnName.length()) {
				columnName = rsmd.getColumnName(col);
			}
			columnName = convert(columnName);
			Integer descriptorIndex = properMap.get(columnName);
			if (descriptorIndex != null) {
				columnToProperty[col] = descriptorIndex.intValue();
			}
		}
        return columnToProperty;
	}
	
	/**
	 * 替换成驼峰表达式  
	 * @param columnName
	 * @return
	 */
	private String convert(String columnName) {
		columnName = columnName.toLowerCase();
		/*XmlBeanInfo columnToPerporty = this.tableColumnMap.get(columnName);		
		if (columnToPerporty != null) {
			return columnToPerporty.getProperty();
		}*/
		String regexValue = "(_\\w)\\w?";
		Pattern pattern = Pattern.compile(regexValue);
		Matcher matcher = pattern.matcher(columnName);
		while (matcher.find()) {
			String value = matcher.group(1);
			String upCaseValue = value.replaceAll("_", "").toUpperCase();
			columnName = columnName.replaceFirst(value, upCaseValue);
		}
		return columnName;
	}
	

	@Override
	protected Object processColumn(ResultSet rs, int index, Class<?> propType) throws SQLException {
		Set<Class<?>> classSet = specialClassMap.keySet();
		boolean containsInterface = classSet.contains(propType);
		Class<?> containsClass = null;
		if (!containsInterface) {
			Class<?>[] allInterfaceArr = propType.getInterfaces();
			for (Class<?> inte : allInterfaceArr) {
				if (classSet.contains(inte)) {
					containsInterface = true;
					containsClass = inte;
				}
			}
		} else {
			containsClass = propType;
		}

		if (containsInterface) {
			SpecialTypeHandle<?> handle = specialClassMap.get(containsClass);
			if (handle == null) {
				throw new IllegalArgumentException("The SpecialTypeHandle Cannot Be Null");
			}
			if (!propType.isPrimitive() && rs.getObject(index) == null) {
				return null;
			}
			Object o = rs.getObject(index);
			Object result = handle.getResultObject(propType,o);
			return result;
		}
		return super.processColumn(rs, index, propType);
	}
}
