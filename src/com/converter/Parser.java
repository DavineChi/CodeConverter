package com.converter;

import com.converter.node.ASTNode;
import com.converter.node.MethodNode;
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
	private String currentLine;
	private boolean lineHasContinuation;
	
	public Parser(String filename) {
		
		this.stream = new TokenInputStream(filename);
		this.currentLine = null;
		this.lineHasContinuation = false;
	}
	
	public ASTNode parseProgram() {
		
		ASTNode program = new ProgramNode(null);
		
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
	
	public MethodNode parseMethod(String value) {
		
		MethodNode result = null;
		
		String accessModifier = "";
		String returnType = "";
		
		String line = value.toUpperCase();
		
		if (line.contains("PUBLIC")) {
			
			accessModifier = "public";
		}
		
		if (line.contains("PRIVATE")) {
			
			accessModifier = "private";
		}
		
		if (line.contains("SUB")) {
			
			returnType = "void";
		}
		
		if (line.contains("FUNCTION")) {
			
			// TOD: logical branches to find return type
			returnType = "";
		}
		
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
	
	private String collectContinuationLines() {
		
		String result = "";
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(currentLine);
		
		while (lineHasContinuation) {
			
			currentLine = stream.nextLine();
			currentLine = currentLine.trim();
			
			sb.append(currentLine);
			
			lineHasContinuation = Util.hasLineContinuation(currentLine);
		}
		
		result = sb.toString();
		result = result.replace(" _", " ");
		
		return result;
	}
	
	public void start() {
		
		ProgramNode prog;
		
		while (!stream.eof()) {
			
			currentLine = stream.nextLine();
			
			lineHasContinuation = Util.hasLineContinuation(currentLine);
			
			boolean isComment = Util.isComment(currentLine);
			boolean containsComment = Util.containsComment(currentLine);
			boolean isMethod = Util.isFunctionOrSub(currentLine);
			
			if (lineHasContinuation) {
				
				currentLine = collectContinuationLines();
			}
			
			if (containsComment) {
				
				Token tokenPreserved = stream.readComment(true);
				Token tokenDisacarded = stream.readComment(false);
			}
			
			if (isComment) {
				
				
			}
			
			if (isMethod) {
				
				parseMethod(currentLine);
			}
		}
	}
	
	public static void main(String[] args) {
		
		Parser parser = new Parser("main/resources/CEto.cls");
		
		parser.start();
	}
}





















