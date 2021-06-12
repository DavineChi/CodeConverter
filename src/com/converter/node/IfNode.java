package com.converter.node;

public class IfNode extends ASTNode {
	
	private ASTNode nodeCondition;
	private ASTNode nodeThenResult;
	private ASTNode nodeElseResult;
	
	public IfNode(ASTNode condition, ASTNode thenResult) {
		
		this(condition, thenResult, null);
	}
	
	public IfNode(ASTNode condition, ASTNode thenResult, ASTNode elseResult) {
		
		this.nodeType = NodeType.IF;
		this.nodeCondition = condition;
		this.nodeThenResult = thenResult;
		this.nodeElseResult = elseResult;
	}
	
	public ASTNode getCondition() {
		
		return nodeCondition;
	}
	
	public ASTNode getThenResult() {
		
		return nodeThenResult;
	}
	
	public ASTNode getElseResult() {
		
		return nodeElseResult;
	}
}
