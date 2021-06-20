package com.converter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.converter.node.ASTNode;
import com.converter.node.AssignmentNode;
import com.converter.node.BlankLineNode;
import com.converter.node.BodyNode;
import com.converter.node.CommentNode;
import com.converter.node.DeclarationNode;
import com.converter.node.ElseResultNode;
import com.converter.node.ExpressionNode;
import com.converter.node.INodeCollection;
import com.converter.node.IfNode;
import com.converter.node.MethodNode;
import com.converter.node.OnErrorNode;
import com.converter.node.ParameterNode;
import com.converter.node.ProgramNode;
import com.converter.node.PropertyNode;
import com.converter.node.SetNode;
import com.converter.node.StringNode;
import com.converter.node.ThenResultNode;
import com.converter.node.VariableNode;
import com.converter.node.WithNode;
import com.converter.utils.DataType;
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
public class Parser implements Serializable {
	
	private static final String SERIALIZED_FILE = "ParseTree.ser";
	private static final long serialVersionUID = 1L;
	
	private TokenInputStream stream;
	private ProgramNode program;
	private String currentLine;
	
	private boolean[] bypassFuse;
	private boolean bypassTopLevel;
	
	private boolean isTopLevelNode;
	private boolean isBeginEndNode;
	private boolean isComment;
	private boolean containsComment;
	private boolean isMethod;
	private boolean isAssignment;
	private boolean isDeclaration;
	private boolean isSetStatement;
	private boolean isWithBlock;
	private boolean isIfBlock;
	private boolean isMethodCall;
	private boolean isPropertyCall;
	private boolean isProperty;
	private boolean isReturnStatement;
	private boolean isOnErrorGoto;
	private boolean isEndOfFunctionOrSub;
	private boolean isBlankLine;
	
	public Parser(String filename) {
		
		this.stream = new TokenInputStream(filename);
		this.currentLine = null;
		
		this.bypassFuse = new boolean[] { false, false };
		this.bypassTopLevel = false;
		
		this.isTopLevelNode = false;
		this.isBeginEndNode = false;
		this.isComment = false;
		this.containsComment = false;
		this.isMethod = false;
		this.isAssignment = false;
		this.isDeclaration = false;
		this.isSetStatement = false;
		this.isWithBlock = false;
		this.isIfBlock = false;
		this.isMethodCall = false;
		this.isPropertyCall = false;
		this.isProperty = false;
		this.isReturnStatement = false;
		this.isOnErrorGoto = false;
		this.isBlankLine = false;
	}
	
	private void analyze() {
		
		isComment = Util.isComment(currentLine);
		containsComment = Util.containsComment(currentLine);
		isMethod = Util.isFunctionOrSub(currentLine);
		isAssignment = Util.isAssignment(currentLine);
		isDeclaration = Util.isDeclaration(currentLine);
		isSetStatement = Util.isSetStatement(currentLine);
		isWithBlock = Util.isWithBlock(currentLine);
		isIfBlock = Util.isIfBlock(currentLine);
		isMethodCall = Util.isMethodCall(currentLine);
		isPropertyCall = Util.isPropertyCall(currentLine);
		isProperty = Util.isProperty(currentLine);
		isReturnStatement = Util.isReturnStatement(currentLine);
		isOnErrorGoto = Util.isOnErrorGoto(currentLine);
		isEndOfFunctionOrSub = Util.isEndOfFunctionOrSub(currentLine);
		isBlankLine = Util.isBlankLine(currentLine);
	}
	
	private void process(INodeCollection node) {
		
		if (isComment) {
			
			CommentNode comment = parseComment(currentLine);
			
			node.addNode(comment);
		}
		
		if (isDeclaration) {
			
			DeclarationNode declaration = parseDeclaration(currentLine);
			
			node.addNode(declaration);
		}
		
		if (isMethod) {
			
			MethodNode method = parseMethod(currentLine);
			
			node.addNode(method);
		}
		
		if (isAssignment) {
			
			ASTNode result = parseAssignment(currentLine);
			
			if (result instanceof SetNode) {
				
				result = (SetNode)(result);
			}
			
			else {
				
				result = (AssignmentNode)(result);
			}
			
			node.addNode(result);
		}
		
		if (isWithBlock) {
			
			WithNode withNode = parseWithBlock();
			
			node.addNode(withNode);
		}
		
		if (isIfBlock) {
			
			IfNode ifNode = parseIfBlock();
			
			node.addNode(ifNode);
		}
		
		if (isMethodCall) {
			
			// TODO: implementation
			
		}
		
		if (isPropertyCall) {
			
			// TODO: implementation
			
		}
		
		if (isOnErrorGoto) {
			
			OnErrorNode error = new OnErrorNode(currentLine);
			
			node.addNode(error);
		}
		
		if (isBlankLine) {
			
			node.addNode(new BlankLineNode());
		}
	}
	
	@SuppressWarnings("unused")
	private void load() {
		
        try {
        	
            FileInputStream file = new FileInputStream(SERIALIZED_FILE);
            ObjectInputStream input = new ObjectInputStream(file);
            
            program = (ProgramNode)(input.readObject());
            
            input.close();
        }
        
        catch (IOException ioEx) {
        	
            ioEx.printStackTrace();
        }
        
        catch (ClassNotFoundException cnfEx) {
        	
            cnfEx.printStackTrace();
        }
	}
	
	@SuppressWarnings("unused")
	private void save() {
		
        try {
        	
            FileOutputStream file = new FileOutputStream(SERIALIZED_FILE);
            ObjectOutputStream output = new ObjectOutputStream(file);
            
            output.writeObject(program);
            file.close();
        }
        
        catch (IOException ex) {
        	
            ex.printStackTrace();
        }
	}
	
	public ProgramNode parseBeginEnd() {
		
		ProgramNode result = new ProgramNode("BEGIN...END");
		
		do {
			
			currentLine = stream.nextLine();
			
			this.analyze();
			this.process(result);
			
		} while (!(currentLine.equals("END")));
		
		return result;
	}
	
	public CommentNode parseComment(String value) {
		
		CommentNode result = null;
		
		value = value.replace("'", "");
		
		result = new CommentNode(value);
		
		return result;
	}
	
	public ParameterNode[] collectParameters(String value) {
		
		ParameterNode[] result = null;
		
		String[] firstSplit = value.split("\\)");
		String[] secondSplit = firstSplit[0].split("\\(");
		
		if (secondSplit.length > 1) {
			
			String parameters = secondSplit[1];
			
			String[] parametersList = parameters.split(",");
			
			result = new ParameterNode[parametersList.length];
			
			for (int i = 0; i < parametersList.length; i++) {
				
				String item = parametersList[i].trim();
				String[] parts = item.split(" ");
				
				String first = parts[0];
				String second = "";
				String third = "";
				String fourth = "";
				
				if (parts.length > 1) {
					
					second = parts[1];
				}
				
				if (parts.length > 2) {
					
					third = parts[2];
					fourth = "";
				}
				
				if (parts.length == 4) {
					
					fourth = parts[3];
				}
				
				String passedBy = "";
				String parameterName = "";
				String dataType = "";
				
				if (first.equals("ByRef") || first.equals("ByVal") || first.equals("Optional")) {
					
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
		}
		
		return result;
	}
	
	public BodyNode collectBody(String name) {
		
		BodyNode result = new BodyNode(name);
		
		do {
			
			currentLine = stream.nextLine();
			
			this.analyze();
			this.process(result);
			
			if (isReturnStatement) {
				
				result.setReturnStatement(currentLine);
			}
			
		} while (!isEndOfFunctionOrSub);
		
		return result;
	}
	
	public DeclarationNode parseDeclaration(String value) {
		
		DeclarationNode result = null;
		
		String[] parts = value.split(" ");
		
		String variableName = parts[1];
		String dataType = parts[3];
		
		boolean validType = Util.isValidDataType(dataType);
		
		if (validType) {
			
			result = new DeclarationNode(variableName, dataType);
		}
		
		return result;
	}
	
	public MethodNode parseMethod(String value) {
		
		MethodNode result = null;
		
		String[] tokens = value.split(" ");
		
		String accessModifier = tokens[0];
		String callType = tokens[1];
		String callName = tokens[2].split("\\(")[0];
		String returnType = null;
		
		if (callType.equals("Function")) {
			
			returnType = tokens[tokens.length - 1];
		}
		
		ParameterNode[] parameters = collectParameters(value);
		BodyNode methodBody = collectBody(callName);
		
		result = new MethodNode(accessModifier, callType, returnType, callName, parameters, methodBody);
		
		return result;
	}
	
	public ExpressionNode parseExpression(String value) {
		
		ExpressionNode result = null;
		
		// TODO: implementation
		
		
		return result;
	}
	
	public WithNode parseWithBlock() {
		
		WithNode result = null;
		
		String[] parts = currentLine.split(" ");
		String expression = parts[1];
		
		result = new WithNode(expression);
		
		do {
			
			currentLine = stream.nextLine();
			
			this.analyze();
			
			if (isProperty) {
				
				PropertyNode property = new PropertyNode(currentLine);
				
				result.addNode(property);
			}
			
		} while (!(currentLine.equals("End With")));
		
		return result;
	}
	
	public IfNode parseIfBlock() {
		
		IfNode result = null;
		
		ThenResultNode thenResult = new ThenResultNode();
		ElseResultNode elseResult = new ElseResultNode();
		
		String condition = Util.getIfCondition(currentLine);
		String[] parts = condition.split(" ");
		
		ExpressionNode expression = null;
		StringNode left = null;
		StringNode right = null;
		
		String relationalOperator = parts[1];
		
		boolean isRelOp = Util.isRelationalOperator(relationalOperator);
		
		if (isRelOp) {
			
			left = new StringNode(parts[0]);
			right = new StringNode(parts[2]);
			expression = new ExpressionNode(relationalOperator, left, right);
			
			result = new IfNode(expression, null, null);
		}
		
		
		while (!(currentLine.equals("Else"))) {
			
			currentLine = stream.nextLine();
			
			this.analyze();
			this.process(thenResult);
		}
		
		if (currentLine.equals("Else")) {
			
			while (!(currentLine.equals("End If"))) {
				
				currentLine = stream.nextLine();
				
				this.analyze();
				this.process(elseResult);
			}
		}
		
		result.setThenResult(thenResult);
		result.setElseResult(elseResult);
		
		return result;
	}
	
	public SetNode parseSetStatement(String value) {
		
		SetNode result = null;
		
		String[] parts = value.split("=");
		String left = parts[0].replace("Set ", "").trim();
		String right = parts[1].trim();
		
		result = new SetNode(left, right);
		
		return result;
	}
	
	public String[] splitStatement(String value) {
		
		String[] result = null;
		
		int length = value.length();
		int index = value.indexOf("=");
		
		String left = value.substring(0, index).trim();
		String right = value.substring(index + 2, length);
		
		if (containsComment) {
			
			right = Util.removeInlineComment(right);
		}
		
		result = new String[] { left, right };
		
		return result;
	}
	
	public ASTNode parseAssignment(String value) {
		
		ASTNode result = null;
		
		if (isSetStatement) {
			
			result = parseSetStatement(value);
			
			return result;
		}
		
		String variableName = "";
		String accessModifier = "";
		String dataType = "";
		String[] statement = splitStatement(value);
		String comment = null;
		
		if (containsComment) {
			
			String[] parts = value.split("'");
			
			comment = parts[1];
		}
		
		String leftSide = statement[0];
		String rightSide = statement[1];
		String[] leftSideParts = leftSide.split(" ");
		
		boolean isConstant = false;
		
		if (leftSide.startsWith("Public ") || leftSide.startsWith("Private ")) {
			
			accessModifier = leftSideParts[0].trim();
		}
		
		if (leftSide.toUpperCase().contains("CONST")) {
			
			isConstant = true;
		}
		
		if (!accessModifier.equals("") && isConstant) {
			
			variableName = leftSideParts[2];
		}
		
		if (!accessModifier.equals("") && !isConstant) {
			
			variableName = leftSideParts[1];
		}
		
		if (accessModifier.equals("")) {
			
			variableName = leftSideParts[0];
		}
		
		for (int i = 0; i < DataType.LIST.length; i++) {
			
			String arg = DataType.LIST[i];
			
			if (leftSide.contains("As " + arg)) {
				
				dataType = arg;
				break;
			}
		}
		
		// TODO: Declaration vs Variable?
		//DeclarationNode declaration = new DeclarationNode();
		VariableNode variable = new VariableNode(accessModifier, dataType, variableName, isConstant);
		StringNode expression = new StringNode(rightSide);
		
		result = new AssignmentNode(variable, expression, comment);
		
		return result;
	}
	
	private void checkBypass() {
		
		if (isTopLevelNode) {
			
			bypassFuse[0] = true;
		}
		
		if (isBeginEndNode) {
			
			bypassFuse[1] = true;
		}
		
		bypassTopLevel = (bypassFuse[0] && bypassFuse[1]);
	}
	
	private void processTopLevel() {
		
		isTopLevelNode = Util.isTopLevelNode(currentLine);
		isBeginEndNode = Util.isBeginEndNode(currentLine);
		
		if (isTopLevelNode) {
			
			program = new ProgramNode(currentLine);
			
			checkBypass();
			
			return;
		}
		
		if (isBeginEndNode) {
			
			ProgramNode beginEnd = parseBeginEnd();
			
			program.addNode(beginEnd);
			
			checkBypass();
			
			return;
		}
	}
	
	public void start() {
		
		while (!stream.eof()) {
			
			currentLine = stream.nextLine();
			
			if (!bypassTopLevel) {
				
				processTopLevel();
			}
			
			this.analyze();
			this.process(program);
		}
		
		@SuppressWarnings("unused")
		int stop = 0;
	}
	
	public void exit() {
		
		stream.close();
	}
	
	public static void main(String[] args) {
		
		Parser parser = new Parser("main/resources/CEto.cls");
		
		parser.start();
		//parser.save();
		parser.exit();
		
		//parser.load();
	}
}
