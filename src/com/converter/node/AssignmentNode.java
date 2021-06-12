package com.converter.node;

public class AssignmentNode extends ASTNode {
	
	private String nodeOperator = "=";
	private ASTNode nodeLeft;
	private ASTNode nodeRight;
	private String nodeInlineComment;
	
	public AssignmentNode(ASTNode left, ASTNode right) {
		
		this(left, right, null);
	}
	
	public AssignmentNode(ASTNode left, ASTNode right, String inlineComment) {
		
		this.nodeType = NodeType.ASSIGNMENT;
		this.nodeLeft = left;
		this.nodeRight = right;
		this.nodeInlineComment = inlineComment;
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
	
	public String getInlineComment() {
		
		return nodeInlineComment;
	}
	
	@Override
	public String toString() {
		
		return nodeType.toString() + ":[" + nodeLeft + " " + nodeOperator + " " + nodeRight + "]";
	}
}
