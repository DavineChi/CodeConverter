package com.converter.node;

import java.io.Serializable;

public class IfNode extends ASTNode implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ASTNode nodeCondition;
	private ASTNode nodeThenResult;
	private ASTNode nodeElseResult;
	
	public IfNode(ASTNode condition) {
		
		this(condition, null, null);
	}
	
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
	
	public void setThenResult(ThenResultNode thenResult) {
		
		this.nodeThenResult = thenResult;
	}
	
	public ASTNode getElseResult() {
		
		return nodeElseResult;
	}
	
	public void setElseResult(ElseResultNode elseResult) {
		
		this.nodeElseResult = elseResult;
	}
	
	@Override
	public String toString() {
		
		return nodeType.toString() + ":[" + nodeCondition + "]";
	}
}
