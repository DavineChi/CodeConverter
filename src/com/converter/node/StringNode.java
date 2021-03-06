package com.converter.node;

public class StringNode extends ASTNode {
	
	private static final long serialVersionUID = 1L;
	
	private String nodeValue;
	
	public StringNode(String value) {
		
		this.nodeType = NodeType.STRING;
		this.nodeValue = value;
	}
	
	public String getValue() {
		
		return nodeValue;
	}
	
	@Override
	public String toString() {
		
		return nodeType.toString() + ":[" + nodeValue + "]";
	}
}
