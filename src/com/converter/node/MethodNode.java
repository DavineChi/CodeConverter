package com.converter.node;

import com.converter.TokenInputStream;

public class MethodNode extends ASTNode {
	
	private static final long serialVersionUID = 1L;
	
	private String nodeAccessModifier;
	private String nodeCallType;
	private String nodeReturnType;
	private String nodeName;
	private ParameterNode[] nodeParameters;
	private ASTNode nodeBody;
	
	public MethodNode(String accessModifier,
                      String callType,
                      String returnType,
                      String name,
                      ParameterNode[] parameters,
                      ASTNode body) {
		
		this.nodeType = NodeType.METHOD;
		this.lineNumber = TokenInputStream.getLineNumber();
		this.nodeAccessModifier = accessModifier;
		this.nodeCallType = callType;
		this.nodeReturnType = returnType;
		this.nodeName = name;
		this.nodeParameters = parameters;
		this.nodeBody = body;
	}
	
	public String getAccessModifier() {
		
		return nodeAccessModifier;
	}
	
	public String getCallType() {
		
		return nodeCallType;
	}
	
	public String getReturnType() {
		
		return nodeReturnType;
	}
	
	public String getName() {
		
		return nodeName;
	}
	
	public ParameterNode[] getParameters() {
		
		return nodeParameters;
	}
	
	public ASTNode getBody() {
		
		return nodeBody;
	}
	
	@Override
	public String toString() {
		
		return nodeType.toString() + ":[" + nodeName + "] (line " + lineNumber + ")";
	}
}
