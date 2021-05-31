package com.converter.node;

public class MethodNode extends ASTNode {
	
	private String nodeName;
	private String[] nodeVariables;
	private ASTNode nodeBody;
	
	public MethodNode(String name, String[] variables, ASTNode body) {
		
		this.nodeType = NodeType.METHOD;
		this.nodeName = name;
		this.nodeVariables = variables;
		this.nodeBody = body;
	}
	
	public String getName() {
		
		return nodeName;
	}
	
	public String[] getVariables() {
		
		return nodeVariables;
	}
	
	public ASTNode getBody() {
		
		return nodeBody;
	}
}
