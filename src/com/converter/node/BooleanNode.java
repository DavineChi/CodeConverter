package com.converter.node;

import com.converter.TokenInputStream;

public class BooleanNode extends ASTNode {
	
	private static final long serialVersionUID = 1L;
	
	private String nodeValue;
	
	public BooleanNode(String value) {
		
		this.nodeType = NodeType.BOOLEAN;
		this.lineNumber = TokenInputStream.getLineNumber();
		this.nodeValue = value;
	}
	
	public String getValue() {
		
		return nodeValue;
	}
	
	@Override
	public String toString() {
		
		return nodeType.toString() + ":[" + nodeValue + "] (line " + lineNumber + ")";
	}
}
