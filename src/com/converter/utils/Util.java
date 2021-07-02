package com.converter.utils;

import com.converter.node.ASTNode;
import com.converter.node.NodeType;

public class Util {
	
	public static boolean isDoUntil(String value) {
		
		boolean result = false;
		
		if (value.startsWith("Do Until ")) {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean isSelectCase(String value) {
		
		boolean result = false;
		
		if (value.startsWith("Select Case ")) {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean isBalanced(String value, char character) {
		
		boolean result = false;
		
		char[] characters = value.toCharArray();
		int count = 0;
		int remainder = 0;
		
		for (int i = 0; i < characters.length; i++) {
			
			char item = characters[i];
			
			if (item == character) {
				
				count++;
			}
		}
		
		remainder = count % 2;
		
		if (remainder == 0) {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean isBlankLine(String value) {
		
		boolean result = false;
		
		if (value.equals("")) {
			
			result = true;
		}
		
		return result;
	}
	
	public static String getErrorHandlerErrorDescription(String value) {
		
		String result = null;
		
		if (value.startsWith("Err.Raise ")) {
			
			String[] parts = value.split(",");
			
			String first = parts[0];
			String second = parts[1];
			String third = parts[2];
			
			if (first.contains("Err.Number") && third.contains("Err.Description")) {
				
				result = second.trim();
			}
		}
		
		return result;
	}
	
	public static boolean isErrorHandler(String value) {
		
		boolean result = false;
		
		int length = value.length();
		String last = value.substring(length - 1);
		
		if (value.startsWith("EH_") && last.equals(":")) {
			
			result = true;
		}
		
		return result;
	}
	
	public static String getIfCondition(String value) {
		
		String result = "";
		
		int startIndex = 3;
		int endIndex = value.indexOf(" Then");
		
		// 'result' could be an ExpressionNode or a CallNode, or possibly something else
		result = value.substring(startIndex, endIndex);
		
		return result;
	}
	
	public static boolean isOnErrorGoto(String value) {
		
		boolean result = false;
		
		if (value.startsWith("On Error GoTo ")) {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean isReturnStatement(String value) {
		
		boolean result = false;
		
		if (value.startsWith("Exit ")) {
			
			result = true;
		}
		
		return result;
	}
	
	public static String removeInlineComment(String value) {
		
		String result = "";
		
		String[] parts = value.split("'");
		
		result = parts[0].trim();
		
		return result;
	}
	
	public static boolean isProperty(String value) {
		
		boolean result = false;
		
		if (value.startsWith(".")) {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean isIfBlock(String value) {
		
		boolean result = false;
		
		if (value.startsWith("If ")) {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean isWithBlock(String value) {
		
		boolean result = false;
		
		if (value.startsWith("With ")) {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean isPropertyCall(String value) {
		
		boolean result = false;
		
		if (value.contains(".Close") || value.contains(".Execute") || value.contains(".Write ")) {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean isBeginEndNode(String value) {
		
		boolean result = false;
		
		value = value.toUpperCase();
		
		if (value.equals("BEGIN")) {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean isTopLevelNode(String value) {
		
		boolean result = false;
		
		value = value.toUpperCase();
		
		if (value.contains("VERSION") && value.contains("CLASS")) {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean isValidDataType(String value) {
		
		boolean result = false;
		
		for (int i = 0; i < DataType.LIST.length; i++) {
			
			String type = DataType.LIST[i];
			
			if (value.equals(type)) {
				
				result = true;
				break;
			}
		}
		
		return result;
	}
	
	public static boolean isDeclaration(String value) {
		
		boolean result = false;
		
		if (value.startsWith("Dim ") && value.contains(" As ")) {
			
			result = true;
		}
		
		return result;
	}
	
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
	
	public static boolean isSetStatement(String value) {
		
		boolean result = false;
		
		if (Util.isAssignment(value) && value.startsWith("Set ")) {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean isAssignment(String value) {
		
		boolean result = false;
		
		if (value.startsWith("If ")) {
			
			return false;
		}
		
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
	
	public static boolean isBalancedParenthesis(String value) {
		
		boolean result = false;
		
		char[] characters = value.toCharArray();
		int countLeft = 0;
		int countRight = 0;
		
		for (int i = 0; i < characters.length; i++) {
			
			char candidate = characters[i];
			
			if (candidate == '(') {
				
				countLeft++;
			}
			
			if (candidate == ')') {
				
				countRight++;
			}
		}
		
		result = (countLeft == countRight);
		
		return result;
	}
	
	public static boolean isBalancedQuotationMarks(String value) {
		
		boolean result = false;
		
		if (value.contains("\"")) {
			
			result = Util.isBalanced(value, '"');
		}
		
		return result;
	}
	
	public static boolean isConstantDeclaration(String value) {
		
		boolean result = false;
		
		if (value.startsWith("Const ")) {
			
			result = true;
		}
		
		else if (value.startsWith("Private ")) {
			
			String[] parts = value.split(" ");
			
			value = parts[1];
			
			if (value.equals("Const")) {
				
				result = true;
			}
		}
		
		return result;
	}
	
	public static boolean isVariableDeclaration(String value) {
		
		boolean result = false;
		
		if (value.startsWith("Dim ")) {
			
			result = true;
		}
		
		return result;
	}
	
	public static boolean isFunctionOrSub(String value) {
		
		boolean result = false;
		
		String[] terms = new String[] { "Public Sub",
                                        "Private Sub",
                                        "Public Function",
                                        "Private Function" };
		
		for (int i = 0; i < terms.length; i++) { 
			
			String term = terms[i];
			
			if (value.startsWith(term)) {
				
				result = true;
				break;
			}
		}
		
		return result;
	}
	
	public static boolean isEndOfFunctionOrSub(String value) {
		
		boolean result = false;
		
		if (value.equals("End Function") || value.equals("End Sub")) {
			
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
		
		int index = -1;

		if (value.contains("'")) {
			
			index = value.indexOf("'");
			result = true;
			
			if (value.contains("\"")) {
				
				// If odd-numbered quotation-marks, not a valid inline comment.
				// If even-numbered, then it should be a valid inline comment.
				
				String check = value.substring(0, index);
				
				int count = 0;
				
				for (int i = 0; i < check.length(); i++) {
					
					String character = check.substring(i, (i + 1));
					
					if (character.equals("\"")) {
						
						count++;
					}
				}
				
				int modResult = count % 2;
				
				if (modResult > 0) {
					
					result = false;
				}
			}
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
