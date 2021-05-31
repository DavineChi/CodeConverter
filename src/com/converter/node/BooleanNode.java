package com.converter.node;

public class BooleanNode extends ASTNode {
	
	private boolean nodeValue;
	
	public BooleanNode(boolean value) {
		
		this.nodeType = NodeType.BOOLEAN;
		this.nodeValue = value;
	}
	
	public boolean getValue() {
		
		return nodeValue;
	}
}
