package com.converter.node;

public class ExpressionNode extends ASTNode {
	
	private String nodeOperator;
	private ASTNode nodeLeft;
	private ASTNode nodeRight;
	
	public ExpressionNode(String operator, ASTNode left, ASTNode right) {
		
		this.nodeType = NodeType.EXPRESSION;
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
