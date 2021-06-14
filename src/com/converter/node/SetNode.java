package com.converter.node;

public class SetNode extends ASTNode {
	
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
