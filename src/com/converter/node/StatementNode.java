package com.converter.node;

public class StatementNode extends ASTNode {
	
	private String nodeOperator = "=";
	private ASTNode nodeLeft;
	private ASTNode nodeRight;
	
	public StatementNode(VariableNode left, ExpressionNode right) {
		
		this.nodeType = NodeType.STATEMENT;
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
