package com.converter.utils;

import java.util.HashMap;
import java.util.Map;

import com.converter.node.ASTNode;
import com.converter.node.AssignmentNode;
import com.converter.node.BlankLineNode;
import com.converter.node.CommentNode;
import com.converter.node.DeclarationNode;
import com.converter.node.MethodNode;
import com.converter.node.SetNode;

public class GeneratorUtil {
	
	public static boolean isSetNode(ASTNode node) {
		
		boolean result = false;
		
		if (node instanceof SetNode) {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean isDeclarationNode(ASTNode node) {
		
		boolean result = false;
		
		if (node instanceof DeclarationNode) {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean isMethodNode(ASTNode node) {
		
		boolean result = false;
		
		if (node instanceof MethodNode) {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean isAssignmentNode(ASTNode node) {
		
		boolean result = false;
		
		if (node instanceof AssignmentNode) {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean isCommentNode(ASTNode node) {
		
		boolean result = false;
		
		if (node instanceof CommentNode) {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean isBlankLineNode(ASTNode node) {
		
		boolean result = false;
		
		if (node instanceof BlankLineNode) {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean containsStringConcatenation(String value) {
		
		boolean result = false;
		
		if (value.contains(" & ")) {
			
			result = true;
		}
		
		return result;
	}
	
	public static String convertToCSharpConcatenation(String value) {
		
		String result = "";
		
		result = value.replace(" & ", " + ");
		
		return result;
	}
	
	public static String convertToCSharpBooleanValue(String value) {
		
		String result = "";
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("True", "true");
		map.put("False", "false");
		
		result = map.get(value);
		
		return result;
	}
	
	public static String convertToCSharpDataType(String value) {
		
		String result = "";
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("Byte", "byte");
		map.put("Boolean", "bool");
		map.put("Integer", "int");
		map.put("Long", "long");
		map.put("Single", "single");
		map.put("Double", "double");
		map.put("Currency", "decimal");
		map.put("Decimal", "decimal");
		map.put("Date", "DateTime");
		map.put("Object", "object");
		map.put("String", "string");
		map.put("Variant", "var");
		map.put("Connection", "SqlConnection");
		map.put("Recordset", "DataSet");
		map.put("Nothing", "null");
		
		result = map.get(value);
		
		return result;
	}
}
