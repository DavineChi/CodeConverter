package com.converter.node;

public class BooleanNode extends ASTNode {
	
	private static final long serialVersionUID = 1L;
	
	private String nodeValue;
	
	public BooleanNode(String value) {
		
		this.nodeType = NodeType.BOOLEAN;
		this.nodeValue = value;
	}
	
	public String getValue() {
		
		return nodeValue;
	}
}
