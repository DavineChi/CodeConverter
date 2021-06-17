package com.converter.node;

public class BooleanNode extends ASTNode {
	
	private static final long serialVersionUID = 1L;
	
	private boolean nodeValue;
	
	public BooleanNode(boolean value) {
		
		this.nodeType = NodeType.BOOLEAN;
		this.nodeValue = value;
	}
	
	public boolean getValue() {
		
		return nodeValue;
	}
}
