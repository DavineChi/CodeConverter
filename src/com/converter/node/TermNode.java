package com.converter.node;

public class TermNode extends ASTNode {
	
	private static final long serialVersionUID = 1L;
	
	private String nodeAccessModifier;
	private String nodeDataType;
	private String nodeValue;
	
	public TermNode(String accessModifier, String dataType, String value) {
		
		this.nodeType = NodeType.TERM;
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
