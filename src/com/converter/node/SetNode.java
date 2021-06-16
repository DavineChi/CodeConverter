package com.converter.node;

import java.io.Serializable;

public class SetNode extends ASTNode implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nodeOperator = "=";
	private String nodeLeft;
	private String nodeRight;
	
	public SetNode(String left, String right) {
		
		this.nodeType = NodeType.SET;
		this.nodeLeft = left;
		this.nodeRight = right;
	}
	
	public String getOperator() {
		
		return nodeOperator;
	}
	
	public String getLeft() {
		
		return nodeLeft;
	}
	
	public String getRight() {
		
		return nodeRight;
	}
	
	@Override
	public String toString() {
		
		return nodeType.toString() + ":[" + nodeLeft + nodeOperator + nodeRight + "]";
	}
}
