package com.converter.node;

public class ExpressionNode extends ASTNode {
	
	private static final long serialVersionUID = 1L;
	
	private String nodeOperator;
	private ASTNode nodeLeft;
	private ASTNode nodeRight;
	
	public ExpressionNode(String operator, ASTNode left, ASTNode right) {
		
		this.nodeType = NodeType.EXPRESSION;
		this.nodeOperator = operator;
		this.nodeLeft = left;
		this.nodeRight = right;
	}
	
	public String getOperator() {
		
		return nodeOperator;
	}
	
	public ASTNode getLeft() {
		
		return nodeLeft;
	}
	
	public ASTNode getRight() {
		
		return nodeRight;
	}
}
