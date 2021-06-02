package com.converter.node;

public class MethodNode extends ASTNode {
	
	private String nodeName;
	private ParameterNode[] nodeParameters;
	private ASTNode nodeBody;
	
	public MethodNode(String name, String[] variables, ASTNode body) {
                      ParameterNode[] parameters,
		
		this.nodeType = NodeType.METHOD;
		this.nodeName = name;
		this.nodeParameters = parameters;
		this.nodeBody = body;
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
}
