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
	
	public ASTNode getNodeCondition() {
		
		return nodeCondition;
	}
	
	public ASTNode getNodeThenResult() {
		
		return nodeThenResult;
	}
	
	public ASTNode getNodeElseResult() {
		
		return nodeElseResult;
	}
}
