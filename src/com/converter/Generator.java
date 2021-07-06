package com.converter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.converter.node.ASTNode;
import com.converter.node.AssignmentNode;
import com.converter.node.AttributeNode;
import com.converter.node.BodyNode;
import com.converter.node.CommentNode;
import com.converter.node.ConstantNode;
import com.converter.node.DeclarationNode;
import com.converter.node.MethodNode;
import com.converter.node.ParameterNode;
import com.converter.node.ProgramNode;
import com.converter.node.SetNode;
import com.converter.node.StringNode;
import com.converter.node.VariableNode;
import com.converter.utils.GeneratorUtil;

public class Generator {
	
	private Inspector inspector;
	private ASTNode currentNode;
	
	private String namespace;
	private String className;
	
	private boolean isBlankLineNode;
	private boolean isCommentNode;
	private boolean isAssignmentNode;
	private boolean isMethodNode;
	private boolean isDeclarationNode;
	private boolean isSetNode;
	
	private boolean inMethodBody;
	
	public Generator(ProgramNode program, String namespace, String className) {
		
		this.inspector = new Inspector(program);
		this.currentNode = null;
		
		this.namespace = namespace;
		this.className = className;
		
		this.isBlankLineNode = false;
		this.isCommentNode = false;
		this.isAssignmentNode = false;
		this.isMethodNode = false;
		this.isDeclarationNode = false;
		this.isSetNode = false;
		
		this.inMethodBody = false;
	}
	
	private void analyze() {
		
		isBlankLineNode = GeneratorUtil.isBlankLineNode(currentNode);
		isCommentNode = GeneratorUtil.isCommentNode(currentNode);
		isAssignmentNode = GeneratorUtil.isAssignmentNode(currentNode);
		isMethodNode = GeneratorUtil.isMethodNode(currentNode);
		isDeclarationNode = GeneratorUtil.isDeclarationNode(currentNode);
		isSetNode = GeneratorUtil.isSetNode(currentNode);
	}
	
	private void process() {
		
		if (isSetNode) {
			
			SetNode setNode = (SetNode)(currentNode);
			
			String line = generateSetStatement(setNode);
			
			StringManager.append(line);
			
			return;
		}
		
		if (isBlankLineNode) {
			
			StringManager.append("");
			
			return;
		}
		
		if (isCommentNode) {
			
			CommentNode comment = (CommentNode)(currentNode);
			
			String line = generateComment(comment);
			
			StringManager.append(line);
			
			return;
		}
		
		if (isAssignmentNode) {
			
			AssignmentNode assignment = (AssignmentNode)(currentNode);
			
			ASTNode left = assignment.getLeft();
			
			if (left instanceof AttributeNode) {
				
				return;
			}
			
			String line = generateAssignment(assignment);
			
			StringManager.append(line);
			
			return;
		}
		
		if (isMethodNode) {
			
			MethodNode method = (MethodNode)(currentNode);
			
			inMethodBody = true;
			
			generateMethod(method);
			
			inMethodBody = false;
			
			return;
		}
		
		if (isDeclarationNode) {
			
			DeclarationNode declaration = (DeclarationNode)(currentNode);
			
			String line = generateDeclaration(declaration);
			
			StringManager.append(line);
			
			return;
		}
	}
	
	public String generateComment(CommentNode node) {
		
		String result = "";
		
		String value = node.getComment();
		
		result = "// " + value;
		
		return result;
	}
	
	public String generateAssignment(AssignmentNode node) {
		
		String result = "";
		
//		ASTNode astNode = node.getLeft();
//		
//		if (astNode instanceof ConstantNode) {
//			
//			ConstantNode cNode = (ConstantNode)(astNode);
//			
//			String name = cNode.getName();
//			
//			if (name.equals("conNotFound")) {
//				
//				String stop = "";
//			}
//		}
		
		String accessModifier = null;
		String dataType = "";
		String operator = node.getOperator();
		ASTNode left = node.getLeft();
		ASTNode right = node.getRight();
		String inlineComment = node.getInlineComment();
		
		String leftName;
		String rightValue;
		
		if (left instanceof ConstantNode) {
			
			ConstantNode constantNode = (ConstantNode)(left);
			
			accessModifier = constantNode.getAccessModifier();
			dataType = constantNode.getDataType();
			leftName = constantNode.getName();
			
			result = "const";
		}
		
		else if (left instanceof VariableNode) {
			
			VariableNode variableNode = (VariableNode)(left);
			
			accessModifier = variableNode.getAccessModifier();
			dataType = variableNode.getDataType();
			leftName = variableNode.getName();
		}
		
		else {
			
			return result;
		}
		
		if (accessModifier != null) {
			
			accessModifier = accessModifier.toLowerCase();
		}
		
		dataType = GeneratorUtil.convertToCSharpDataType(dataType);
		
		if (dataType != null) {
			
			if (result.equals("const")) {
				
				if (accessModifier == null) {
					
					result = result + " " + dataType + " " + leftName;
				}
				
				else {
					
					result = accessModifier + " " + result + " " + dataType + " " + leftName;
				}
			}
			
			else if (accessModifier.equals("")) {
				
				result = dataType + " " + leftName;
			}
			
			else {
				
				result = accessModifier + " " + dataType + " " + leftName;
			}
		}
		
		else {
			
			if (result.equals("const")) {
				
				if (accessModifier == null) {
					
					result = result + " " + dataType + " " + leftName;
				}
				
				else {
					
					result = accessModifier + " " + result + " " + dataType + " " + leftName;
				}
			}
			
			else if (accessModifier == null || accessModifier.equals("")) {
				
				result = leftName;
			}
			
			else {
				
				result = accessModifier + " " + leftName;
			}
		}
		
		if (right instanceof StringNode) {
			
			StringNode stringNode = (StringNode)(right);
			
			rightValue = stringNode.getValue();
			
			boolean containsConcat = GeneratorUtil.containsStringConcatenation(rightValue);
			
			if (containsConcat) {
				
				rightValue = GeneratorUtil.convertToCSharpConcatenation(rightValue);
			}
			
			result = result + " " + operator + " " + rightValue;
		}
		
		if (inlineComment != null) {
			
			result = result + "; // " + inlineComment;
		}
		
		else {
			
			result = result + ";";
		}
		
		return result;
	}
	
	public void generateMethod(MethodNode node) {
		
		BodyNode body = (BodyNode)(node.getBody());
		
		String line = generateMethodHeader(node);
		
		StringManager.append(line);
		
		StringManager.append("{");
		StringManager.incrementIndents();
		
		generateMethodBody(body);
		
		StringManager.decrementIndents();
		StringManager.append("}");
	}
	
	private String generateMethodHeader(MethodNode node) {
		
		String result = "";
		
		ParameterNode[] params = node.getParameters();
		
		String accessModifier = node.getAccessModifier().toLowerCase();
		String methodName = node.getName();
		String returnType;
		
		if (node.getReturnType() != null) {
			
			returnType = node.getReturnType();
			returnType = GeneratorUtil.convertToCSharpDataType(returnType);
		}
		
		else {
			
			returnType = "void";
		}
		
		result = accessModifier + " " + returnType + " " + methodName;
		
		String list = "";
		
		if (params != null) {
			
			for (int i = 0; i < params.length; i++) {
				
				ParameterNode param = params[i];
				
				String paramDataType = param.getDataType();
				String paramName = param.getName();
				
				paramDataType = GeneratorUtil.convertToCSharpDataType(paramDataType);
				
				String paramItem = paramDataType + " " + paramName;
				
				if (list.equals("") && params.length > 1) {
					
					list = paramItem + ", ";
				}
				
				else if (list.equals("") && params.length == 1) {
					
					list = paramItem;
				}
				
				else if ((i + 1) == params.length) {
					
					list = list + paramItem;
				}
				
				else {
					
					list = list + paramItem + ", ";
				}
			}
		}
		
		result = result + "(" + list + ")";
		
		return result;
	}
	
	private void generateMethodBody(BodyNode node) {
		
		// TODO: implementation
		ASTNode[] nodes = node.getNodes();
		
		for (int i = 0; i < nodes.length; i++) {
			
			currentNode = nodes[i];
			
			this.analyze();
			this.process();
		}
	}
	
	public String generateDeclaration(DeclarationNode node) {
		
		String result = "";
		
		String dataType = node.getDataType();
		String name = node.getVariableName();
		
		dataType = GeneratorUtil.convertToCSharpDataType(dataType);
		
		result = dataType + " " + name + ";";
		
		return result;
	}
	
	public String generateSetStatement(SetNode node) {
		
		String result = "";
		
		String dataType = "";
		String name = node.getLeft();
		String right = node.getRight();
		String operator = node.getOperator();
		
		String[] rightParts = right.split(" ");
		
		if (rightParts.length > 1) {
			
			dataType = rightParts[1];
		}
		
		else {
			
			dataType = rightParts[0];
		}
		
		dataType = GeneratorUtil.convertToCSharpDataType(dataType);
		
		if (dataType == null) {
			
			dataType = right;
		}
		
		else if (dataType.equals("null")) {
			
			result = name + " " + operator + " " + dataType;
		}
		
		else {
			
			result = name + " " + operator + " new " + dataType+ "()";
		}
		
		result = result + ";";
		
		return result;
	}
	
	public void convertMethod(MethodNode node) {
		
		String nodeAccessModifier = node.getAccessModifier().toLowerCase();
		//String nodeCallType = node.getCallType();
		String nodeReturnType = node.getReturnType();
		String nodeName = node.getName();
		ParameterNode[] nodeParameters = node.getParameters();
		ASTNode nodeBody = node.getBody();
		
		if (nodeReturnType == null) {
			
			nodeReturnType = "void";
		}
		
		String methodHeader = nodeAccessModifier + " " + nodeReturnType + " " + nodeName;
	}
	
	public void print() {
		
		String value = StringManager.getOutput();
		
		System.out.println(value);
	}
	
	private void buildClassPrefix() {
		
		StringManager.append("namespace " + namespace);
		StringManager.append("{");
		StringManager.incrementIndents();
		
		StringManager.append("public class " + className);
		StringManager.append("{");
		StringManager.incrementIndents();
	}
	
	private void buildClassSuffix() {
		
		StringManager.decrementIndents();
		StringManager.append("}");
		
		StringManager.decrementIndents();
		StringManager.append("}");
	}
	
	public void start() {
		
		buildClassPrefix();
		
		while (!inspector.endOfNodes()) {
			
			currentNode = inspector.nextNode();
			
			this.analyze();
			this.process();
		}
		
		buildClassSuffix();
	}
	
	public void write(String path) {
		
		File file = new File(path);
		BufferedWriter writer = null;
		
		try {
			
			writer = new BufferedWriter(new FileWriter(file));
			
			String output = StringManager.getOutput();
			
			writer.write(output);
			
			writer.close();
		}
		
		catch (IOException ex) {
			
			ex.printStackTrace();
		}
	}
}
