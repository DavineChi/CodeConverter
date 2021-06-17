package com.converter.node;

public class NumberNode extends ASTNode {
	
	private static final long serialVersionUID = 1L;
	
	private String nodeValue;
	
	public NumberNode(String value) {
		
		this.nodeType = NodeType.NUMBER;
		this.nodeValue = value;
	}
	
	public String getValue() {
		
		return nodeValue;
	}
}
