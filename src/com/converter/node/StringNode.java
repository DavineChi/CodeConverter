package com.converter.node;

import java.io.Serializable;

public class StringNode extends ASTNode implements Serializable {
	
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
