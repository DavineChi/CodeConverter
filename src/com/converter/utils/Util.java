package com.converter.utils;

import com.converter.Token;
import com.converter.node.ASTNode;
import com.converter.node.NodeType;

public class Util {
	
	public static boolean hasLineContinuation(String value) {
		
		boolean result = false;
		
		if (value.equals("")) {
			
			return result;
		}
		
		char[] characters = value.toCharArray();
		char ch = characters[characters.length - 1];
		
		if (ch == '_') {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean isAssignment(String value) {
		
		boolean result = false;
		
		if (value.contains("=")) {
			
			result = true;
		}
		
		if (value.contains("\"")) {
			
			int indexEqualSign = value.indexOf("=");
			int indexQuotation = value.indexOf("\"");
			
			if (indexEqualSign > indexQuotation) {
				
				result = false;
			}
		}
		
		return result;
	}
	
	public static boolean isBalancedQuotationMarks(String value) {
		
		boolean result = false;
		
		if (value.contains("\"")) {
			
			char[] characters = value.toCharArray();
			int count = 0;
			int remainder = 0;
			
			for (int i = 0; i < characters.length; i++) {
				
				char item = characters[i];
				
				if (item == '"') {
					
					count++;
				}
			}
			
			remainder = count % 2;
			
			if (remainder == 0) {
				
				result = true;
			}
		}
		
		return result;
	}
	
	public static boolean isVariableDeclaration(String value) {
		
		boolean result = false;
		
		String line = value.trim().toUpperCase();
		
		if (line.startsWith("DIM ")) {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean isFunctionOrSub(String value) {
		
		boolean result = false;
		
		String request = value.toUpperCase();		
		String[] terms = new String[] { "PUBLIC SUB",
                                        "PRIVATE SUB",
                                        "PUBLIC FUNCTION",
                                        "PRIVATE FUNCTION" };
		
		for (String term : terms) {
			
			if (request.contains(term)) {
				
				result = true;
				break;
			}
		}
		
		return result;
	}
	
	public static boolean isEndOfFunctionOrSub(String value) {
		
		boolean result = false;
		
		String line = value.toUpperCase();
		
		if (line.contains("END FUNCTION") ||
			line.contains("END SUB")) {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean isString(String value) {
		
		boolean result = false;
		
		if (value.startsWith("\"")) {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean isString(Token token) {
		
		boolean result = false;
		
		String type = token.getTokenType().toUpperCase();
		
		if (type.equals("COMMENT")) {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean isString(ASTNode node) {
		
		boolean result = false;
		
		NodeType type = node.getType();
		
		if (type.equals(NodeType.STRING)) {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean containsString(String value) {
		
		boolean result = false;
		
		if (value.contains("\"")) {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean isComment(String value) {
		
		boolean result = false;
		
		String line = value.trim();
		
		if (line.startsWith("'")) {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean containsComment(String value) {
		
		boolean result = false;
		
		if (value.contains("'")) {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean isKeyword(String value) {
		
		boolean result = false;
		
		for (int i = 0; i < Keyword.LIST.length; i++) {
			
			String keyword = Keyword.LIST[i];
			
			if (value.toUpperCase().equals(keyword)) {
				
				result = true;
				break;
			}
		}
		
		return result;
	}
	
	public static boolean isDigit(String value) {
		
		boolean result = false;
		String symbols = "1234567890";
		
		if (value.length() == 0 || value.length() > 1) {
			
			return false;
		}
		
		result = symbols.indexOf(value) >= 0;
		
		return result;
	}
	
	public static boolean isNumber(String value) {
		
		boolean result = false;
		
		if (value.length() == 0) {
			
			return false;
		}
		
		if (Double.valueOf(value) instanceof Double) {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean isArithmeticOperator(String value) {
		
		boolean result = false;
		String symbols = "+-*/%";
		
		if (value.length() == 0 || value.length() > 1) {
			
			return false;
		}
		
		result = symbols.indexOf(value) >= 0;
		
		return result;
	}
	
	public static boolean isRelationalOperator(String value) {
		
		boolean result = false;
		String[] symbols = { "<", ">", "<=", ">=", "=", "<>" };
		
		for (int i = 0; i < symbols.length; i++) {
			
			String symbol = symbols[i];
			
			if (value.equals(symbol)) {
				
				result = true;
				break;
			}
		}
		
		return result;
	}
	
	public static boolean isPunctuation(String value) {
		
		boolean result = false;
		String symbols = ",()&_";
		
		if (value.length() == 0 || value.length() > 1) {
			
			return false;
		}
		
		result = symbols.indexOf(value) >= 0;
		
		return result;
	}
}
