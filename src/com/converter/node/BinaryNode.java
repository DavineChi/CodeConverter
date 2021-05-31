package com.converter.node;

public class BinaryNode extends ASTNode {
	
	private String nodeOperator;
	private ASTNode nodeLeft;
	private ASTNode nodeRight;
	
	public BinaryNode(String operator, ASTNode left, ASTNode right) {
		
		this.nodeType = NodeType.BINARY;
		this.nodeOperator = operator;
		this.nodeLeft = left;
		this.nodeRight = right;
	}
	
	public String getNodeOperator() {
		
		return nodeOperator;
	}
	
	public ASTNode getNodeLeft() {
		
		return nodeLeft;
	}
	
	public ASTNode getNodeRight() {
		
		return nodeRight;
	}
}
