package com.converter;

import com.converter.node.ASTNode;
import com.converter.node.MethodNode;
import com.converter.node.ParameterNode;
import com.converter.node.ProgramNode;
import com.converter.utils.Util;

/************************************************************************************************
 * Main driver class used for parsing VB6 source files.</br>
 * 05/16/2021
 * <p>
 * 
 * Reference:</br>
 * http://lisperator.net/pltut/parser/
 * 
 */
public class Parser {
	
	private TokenInputStream stream;
	private ProgramNode program;
	private String currentLine;
	
	private boolean isComment;
	private boolean containsComment;
	private boolean isMethod;
	
	public Parser(String filename) {
		
		this.stream = new TokenInputStream(filename);
		this.program = new ProgramNode();
		this.currentLine = null;
		
		this.isComment = false;
		this.containsComment = false;
		this.isMethod = false;
	}
	
	public ASTNode parseProgram() {
		
		// TODO: implementation
		
		
		return program;
	}
	
	public ASTNode parseTopLevel() {
		
		ASTNode program = null;
		
		while (!stream.eof()) {
			
			
		}
		
		return program;
	}
	
	public void parseExpression() {
		
		// TODO: implementation
		
	}
	
	public void parseSub() {
		
		// TODO: implementation
		
	}
	
	public void parseFunction() {
		
		// TODO: implementation
		
	}
	
	public ParameterNode[] collectParameters(String value) {
		
		ParameterNode[] result = null;
		
		value = value.replace(")", "");
		
		String[] splitValues = value.split("\\(");
		String parameters = splitValues[1];
		
		String[] parametersList = parameters.split(",");
		
		result = new ParameterNode[parametersList.length];
		
		for (int i = 0; i < parametersList.length; i++) {
			
			String item = parametersList[i].trim();
			String[] parts = item.split(" ");
			
			String first = parts[0];
			String second = parts[1];
			String third = parts[2];
			String fourth = "";
			
			if (parts.length == 4) {
				
				fourth = parts[3];
			}
			
			String passedBy = "";
			String parameterName = "";
			String dataType = "";
			
			if (first.toUpperCase().equals("BYREF") ||
				first.toUpperCase().equals("BYVAL")) {
				
				passedBy = first;
				parameterName = second;
				dataType = fourth;
			}
			
			else {
				
				parameterName = first;
				dataType = third;
			}
			
			result[i] = new ParameterNode(passedBy, parameterName, dataType);
		}
		
		return result;
	}
	
	public MethodNode parseMethod(String value) {
		
		MethodNode result = null;
		
		boolean isFunction = false;
		boolean isSubRoutine = false;
		
		String[] tokens = stream.split(value);
		
		String accessModifier = tokens[0];
		String callType = tokens[1];
		String callName = tokens[2].split("\\(")[0];
		String returnType = "";
		
		ParameterNode[] parameters = collectParameters(value);
		
		if (callType.equals("FUNCTION")) {
			
			returnType = tokens[tokens.length - 1];
		}
		
		result = new MethodNode(accessModifier, callType, returnType, callName, parameters, null);
		
		return result;
	}
	
	public String[] delimited(String start, String end, String separator, String parser) {
		
		String[] result = new String[0];
		boolean first = true;
		
		// TODO: implementation
		
		
		return result;
	}
	
	public void maybeCall() {
		
		// TODO: implementation
		
	}
	
	public void start() {
		
		while (!stream.eof()) {
			
			currentLine = stream.nextLine();
			
			isComment = Util.isComment(currentLine);
			containsComment = Util.containsComment(currentLine);
			isMethod = Util.isFunctionOrSub(currentLine);
			
			if (containsComment) {
				
				Token tokenPreserved = stream.readComment(true);
				Token tokenDisacarded = stream.readComment(false);
			}
			
			if (isComment) {
				
				
			}
			
			if (isMethod) {
				
				MethodNode method = parseMethod(currentLine);
				
				program.addNode(method);
			}
			
			int stop = 0;
		}
	
	public void exit() {
		
		stream.close();
	}
	
	public static void main(String[] args) {
		
		Parser parser = new Parser("main/resources/CEto.cls");
		
		parser.start();
		
		parser.exit();
	}
}





















