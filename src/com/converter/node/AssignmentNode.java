package com.converter.node;

public class AssignmentNode extends ASTNode {
	
	private String nodeOperator = "=";
	private ASTNode nodeLeft;
	private ASTNode nodeRight;
	
	public AssignmentNode(ASTNode left, ASTNode right) {
		
		this.nodeType = NodeType.ASSIGNMENT;
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
	
	
	@Override
	public String toString() {
		
		return nodeType.toString() + ":" + nodeLeft + " " + nodeOperator + " " + nodeRight;
	}
}
