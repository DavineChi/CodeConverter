package com.converter.node;

public class StatementNode extends ASTNode {
	
	private static final long serialVersionUID = 1L;
	
	private String nodeOperator = "=";
	private ASTNode nodeLeft;
	private ASTNode nodeRight;
	
	public StatementNode(VariableNode left, ExpressionNode right) {
		
		this.nodeType = NodeType.STATEMENT;
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
