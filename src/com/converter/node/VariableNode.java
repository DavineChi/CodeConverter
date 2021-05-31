package com.converter.node;

public class VariableNode extends ASTNode {
	
	private String nodeAccessModifier;
	private String nodeDataType;
	private String nodeValue;
	
	public VariableNode(String accessModifier, String dataType, String value) {
		
		this.nodeType = NodeType.VARIABLE;
		this.nodeAccessModifier = accessModifier;
		this.nodeDataType = dataType;
		this.nodeValue = value;
	}
	
	public String getAccessModifier() {
		
		return nodeAccessModifier;
	}
	
	public String getDataType() {
		
		return nodeDataType;
	}
	
	public String getValue() {
		
		return nodeValue;
	}
}
