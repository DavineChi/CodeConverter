package com.converter.node;

public class ElseIfResultNode extends BaseResultNode {
	
	private static final long serialVersionUID = 1L;
	
	private ASTNode nodeCondition;
	
	public ElseIfResultNode(ASTNode condition) {
		
		super();
		
		this.nodeType = NodeType.ELSEIF;
		this.nodeCondition = condition;
	}
	
	public ASTNode getCondition() {
		
		return nodeCondition;
	}
	
	@Override
	public String toString() {
		
		return nodeType.toString() + ":[...(" + size + " items)]";
	}
}
