package com.converter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.converter.node.ASTNode;
import com.converter.node.AssignmentNode;
import com.converter.node.AttributeNode;
import com.converter.node.BlankLineNode;
import com.converter.node.BodyNode;
import com.converter.node.BooleanNode;
import com.converter.node.CaseNode;
import com.converter.node.CommentNode;
import com.converter.node.ConstantNode;
import com.converter.node.DeclarationNode;
import com.converter.node.DoUntilNode;
import com.converter.node.EHBlockNode;
import com.converter.node.ElseIfResultNode;
import com.converter.node.ElseResultNode;
import com.converter.node.ExpressionNode;
import com.converter.node.INodeCollection;
import com.converter.node.IfNode;
import com.converter.node.MethodNode;
import com.converter.node.OnErrorNode;
import com.converter.node.ParameterNode;
import com.converter.node.ProgramNode;
import com.converter.node.PropertyCallNode;
import com.converter.node.PropertyNode;
import com.converter.node.SelectCaseNode;
import com.converter.node.SetNode;
import com.converter.node.StringNode;
import com.converter.node.TerminationNode;
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
	
	private boolean inMethodBody;
	
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
	private boolean isPropertyCall;
	private boolean isTermination;
	private boolean isOnErrorGoto;
	private boolean isBlankLine;
	private boolean isSelectCase;
	private boolean isCaseBlock;
	private boolean isErrorHandlerHandle;
	private boolean isDoUntil;
	
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
		this.inMethodBody = false;
		this.isAssignment = false;
		this.isDeclaration = false;
		this.isSetStatement = false;
		this.isWithBlock = false;
		this.isIfBlock = false;
		this.isPropertyCall = false;
		this.isTermination = false;
		this.isOnErrorGoto = false;
		this.isBlankLine = false;
		this.isSelectCase = false;
		this.isCaseBlock = false;
		this.isErrorHandlerHandle = false;
		this.isDoUntil = false;
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
		isPropertyCall = Util.isPropertyCall(currentLine);
		isTermination = Util.isTermination(currentLine);
		isOnErrorGoto = Util.isOnErrorGoto(currentLine);
		isBlankLine = Util.isBlankLine(currentLine);
		isSelectCase = Util.isSelectCase(currentLine);
		isCaseBlock = Util.isCaseBlock(currentLine);
		isErrorHandlerHandle = Util.isErrorHandlerHandle(currentLine);
		isDoUntil = Util.isDoUntil(currentLine);
	}
	
	private void process(INodeCollection node) {
		
		if (isTermination) {
			
			ASTNode termination = new TerminationNode(currentLine);
			
			node.addNode(termination);
			
			return;
		}
		
		if (isBlankLine) {
			
			node.addNode(new BlankLineNode());
			
			return;
		}
		
		if (isComment) {
			
			CommentNode comment = parseComment(currentLine);
			
			node.addNode(comment);
			
			return;
		}
		
		if (isDeclaration) {
			
			DeclarationNode declaration = parseDeclaration(currentLine);
			
			node.addNode(declaration);
			
			return;
		}
		
		if (isMethod) {
			
			MethodNode method = parseMethod(currentLine);
			
			node.addNode(method);
			
			return;
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
			
			return;
		}
		
		if (isWithBlock) {
			
			WithNode withNode = parseWithBlock();
			
			node.addNode(withNode);
			
			return;
		}
		
		if (isIfBlock) {
			
			ASTNode expression = getIfCondition(currentLine);
			
			IfNode ifNode = new IfNode(expression);
			
			parseIfBlock(ifNode);
			
			node.addNode(ifNode);
			
			return;
		}
		
		if (isPropertyCall) {
			
			PropertyCallNode propertyCall = new PropertyCallNode(currentLine);
			
			node.addNode(propertyCall);
			
			return;
		}
		
		if (isOnErrorGoto) {
			
			OnErrorNode error = new OnErrorNode(currentLine);
			
			node.addNode(error);
			
			return;
		}
		
		if (isSelectCase) {
			
			SelectCaseNode selectCase = parseSelectCase();
			
			node.addNode(selectCase);
			
			return;
		}
		
		if (isCaseBlock) {
			
			CaseNode caseBlock = parseCaseBlock();
			
			node.addNode(caseBlock);
			
			return;
		}
		
		if (isErrorHandlerHandle && inMethodBody) {
			
			EHBlockNode handle = parseEHBlock();
			
			node.addNode(handle);
			
			return;
		}
		
		if (isDoUntil) {
			
			DoUntilNode doUntil = parseDoUntil();
			
			node.addNode(doUntil);
			
			return;
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
	
	private String[] splitStatement(String value) {
		
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
	
	public BodyNode collectMethodBody(String name) {
		
		BodyNode result = new BodyNode(name);
		
		inMethodBody = true;
		
		while (true) {

			boolean stop = Util.isEndOfFunctionOrSub(currentLine);
			
			if (stop) {
				
				break;
			}
			
			currentLine = stream.nextLine();
			
			this.analyze();
			this.process(result);
			
			if (isTermination) {
				
				ASTNode node = new TerminationNode(currentLine);
				
				result.addNode(node);
			}
		}
		
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
		
		int lineNumber = TokenInputStream.getLineNumber();
		
		String[] tokens = value.split(" ");
		
		String accessModifier = tokens[0];
		String callType = tokens[1];
		String callName = tokens[2].split("\\(")[0];
		String returnType = null;
		
		if (callType.equals("Function")) {
			
			returnType = tokens[tokens.length - 1];
		}
		
		ParameterNode[] parameters = collectParameters(value);
		BodyNode methodBody = collectMethodBody(callName);
		
		inMethodBody = false;
		
		result = new MethodNode(accessModifier, callType, returnType, callName, parameters, methodBody, lineNumber);
		
		return result;
	}
	
	public ExpressionNode parseExpression(String value) {
		
		ExpressionNode result = null;
		
		// TODO: parseExpression implementation
		
		
		return result;
	}
	
	public CaseNode parseCaseBlock() {
		
		CaseNode result = null;
		
		String expression = currentLine.replace("Case ", "");
		
		result = new CaseNode(expression);
		
		while (true) {
			
			if (stream.eof()) {
				
				break;
			}
			
			String peekedValue = stream.peekLine();
			
			if (peekedValue.startsWith("Case ") || peekedValue.equals("End Select")) {
				
				break;
			}
			
			currentLine = stream.nextLine();
			
			boolean stop = Util.isEndOfCaseBlock(currentLine);
			
			if (stop) {
				
				break;
			}
			
			this.analyze();
			this.process(result);
		}
		
		return result;
	}
	
	public SelectCaseNode parseSelectCase() {
		
		SelectCaseNode result = null;
		
		String expression = currentLine.replace("Select Case ", "");
		
		result = new SelectCaseNode(expression);
		
		boolean endSelectCase = false;
		boolean isCaseBlock = false;
		
		while (true) {
			
			currentLine = stream.nextLine();
			
			endSelectCase = currentLine.equals("End Select");
			isCaseBlock = currentLine.startsWith("Case ");
			
			if (endSelectCase) {
				
				break;
			}
			
			if (isCaseBlock) {
				
				CaseNode caseNode = collectCaseBlock();
				
				result.addNode(caseNode);
			}
			
			this.analyze();
			this.process(result);
		}
		
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
	
	private ASTNode getIfCondition(String value) {
		
		ASTNode result = null;
		
		String ifCondition = Util.getIfCondition(currentLine);
		String[] parts = ifCondition.split(" ");
		
		StringNode left = null;
		StringNode right = null;
		
		String operator = null;
		boolean isRelOp = false;
		
		if (parts.length > 1) {
			
			operator = parts[1];
			isRelOp = Util.isRelationalOperator(operator);
		}
		
		if (isRelOp) {
			
			left = new StringNode(parts[0]);
			right = new StringNode(parts[2]);
			result = new ExpressionNode(operator, left, right);
		}
		
		else {
			
			result = new BooleanNode(ifCondition);
		}
		
		return result;
	}
	
	public void parseIfBlock(IfNode node) {
		
		String id = node.getCondition().toString();
		
		INodeCollection thenResult = new ThenResultNode(id);
		INodeCollection elseResult = new ElseResultNode();
		INodeCollection elseIfResult = null;
		
		while (true) {
			
			if (stream.eof()) {
				
				break;
			}
			
			boolean singleLineIfThen = Util.isSingleLineIfThen(currentLine);
			
			if (singleLineIfThen) {
				
				String parts[] = currentLine.split("Then");
				String condition = parts[1].trim();
				
				ASTNode expression = null;
				
				boolean isPropertyCall = Util.isPropertyCall(condition);
				
				if (isPropertyCall) {
					
					expression = new PropertyCallNode(condition);
				}
				
				else {
					
					expression = new StringNode(condition);
				}
				
				thenResult.addNode(expression);
				
				break;
			}
			
			currentLine = stream.nextLine();
			
			boolean stop = currentLine.equals("End If") ||
                           currentLine.startsWith("ElseIf") ||
                           currentLine.equals("Else");
			
			if (stop) {
				
				break;
			}
			
			this.analyze();
			this.process(thenResult);
		}
		
		node.addNode((ASTNode)(thenResult));
		
		while (true) {
			
			if (currentLine.startsWith("ElseIf")) {
				
				ASTNode expression = getIfCondition(currentLine);
				
				elseIfResult = new ElseIfResultNode(expression);
				
				while (true) {
					
					if (stream.eof()) {
						
						break;
					}
					
					currentLine = stream.nextLine();
					
					boolean stop = currentLine.equals("End If") || currentLine.equals("Else");
					
					if (stop) {
						
						break;
					}
					
					this.analyze();
					this.process(elseIfResult);
					
					String peek = stream.peekLine();
					
					if (peek.startsWith("ElseIf ")) {
						
						currentLine = stream.nextLine();
						
						break;
					}
				}
				
				node.addNode((ASTNode)(elseIfResult));
			}
			
			else {
				
				break;
			}
		}
		
		if (currentLine.equals("Else")) {
			
			while (true) {
				
				if (stream.eof()) {
					
					break;
				}
				
				currentLine = stream.nextLine();
				
				boolean stop = currentLine.equals("End If");
				
				if (stop) {
					
					break;
				}
				
				this.analyze();
				this.process(elseResult);
			}
			
			node.addNode((ASTNode)(elseResult));
		}
	}
	
	public SetNode parseSetStatement(String value) {
		
		SetNode result = null;
		
		String[] parts = value.split("=");
		String left = parts[0].replace("Set ", "").trim();
		String right = parts[1].trim();
		
		result = new SetNode(left, right);
		
		return result;
	}
	
	public ASTNode parseAssignment(String value) {
		
		ASTNode result = null;
		
		ASTNode leftNode = null;
		ASTNode rightNode = null;
		
		if (isSetStatement) {
			
			result = parseSetStatement(value);
			
			return result;
		}
		
		String name = "";
		String accessModifier = null;
		String dataType = null;
		String[] statement = splitStatement(value);
		String comment = null;
		
		if (containsComment) {
			
			String[] parts = value.split("'");
			
			comment = parts[1];
		}
		
		String leftSide = statement[0];
		String rightSide = statement[1];
		String[] leftSideParts = leftSide.split(" ");
		
		boolean isConstant = Util.isConstantDeclaration(leftSide);
		boolean isAttribute = false;
		
		if (leftSide.startsWith("Public ") || leftSide.startsWith("Private ")) {
			
			accessModifier = leftSideParts[0].trim();
		}
		
		if (leftSide.startsWith("Attribute ")) {
			
			name = leftSideParts[1].trim();
			isAttribute = true;
		}
		
		else {
			
			if ((accessModifier != null) && isConstant) {
				
				name = leftSideParts[2];
			}
			
			if ((accessModifier != null) && !isConstant) {
				
				name = leftSideParts[1];
			}
			
			if ((accessModifier == null) && isConstant) {
				
				name = leftSideParts[1];
			}
			
			if ((accessModifier == null) && !isConstant) {
				
				name = leftSideParts[0];
			}
			
			for (int i = 0; i < DataType.LIST.length; i++) {
				
				String arg = DataType.LIST[i];
				
				if (leftSide.contains("As " + arg)) {
					
					dataType = arg;
					break;
				}
			}
		}
		
		if (isAttribute) {
			
			leftNode = new AttributeNode(name);
		}
		
		else if (isConstant) {
			
			leftNode = new ConstantNode(accessModifier, dataType, name);
		}
		
		else {
			
			leftNode = new VariableNode(accessModifier, dataType, name);
		}
		
		rightNode = new StringNode(rightSide);
		
		result = new AssignmentNode(leftNode, rightNode, comment);
		
		return result;
	}
	
	public EHBlockNode parseEHBlock() {
		
		EHBlockNode result = null;
		
		boolean isErrRaise = false;
		boolean endOfMethod = false;
		
		String id = currentLine.replace(":", "");
		
		result = new EHBlockNode(id);
		
		while (true) {
			
			currentLine = stream.nextLine();
			
			isErrRaise = Util.isErrorHandlerErrRaise(currentLine);
			endOfMethod = Util.isEndOfFunctionOrSub(currentLine);
			
			if (endOfMethod) {
				
				break;
			}
			
			if (isErrRaise) {
				
				String errDescription = Util.getErrorHandlerErrDescription(currentLine);
				StringNode descriptionNode = new StringNode(errDescription);
				result.addNode(descriptionNode);
				
				continue;
			}
			
			this.analyze();
			this.process(result);
		}
		
		return result;
	}
	
	public DoUntilNode parseDoUntil() {
		
		DoUntilNode result = null;
		
		String[] parts = currentLine.split(" ");
		
		String condition = parts[2];
		
		result = new DoUntilNode(condition);
		
		boolean endDoUntil = false;
		
		while (true) {
			
			currentLine = stream.nextLine();
			
			endDoUntil = currentLine.equals("Loop");
			
			if (endDoUntil) {
				
				break;
			}
			
			this.analyze();
			this.process(result);
		}
		
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
		
		checkBypass();
		
		if (isTopLevelNode) {
			
			program = new ProgramNode(currentLine);
			
			return;
		}
		
		if (isBeginEndNode) {
			
			ProgramNode beginEnd = parseBeginEnd();
			
			program.addNode(beginEnd);
			
			isBeginEndNode = false;
			
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
