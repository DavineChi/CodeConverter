package com.converter.node;

public class NumberNode extends ASTNode {
	
	private String nodeValue;
	
	public NumberNode(String value) {
		
		this.nodeType = NodeType.NUMBER;
		this.nodeValue = value;
	}
	
	public String getValue() {
		
		return nodeValue;
	}
}
