package com.converter.node;

public class OnErrorNode extends ASTNode {
	
	private static final long serialVersionUID = 1L;
	
	private String nodeFullStatement;
	private String nodeGotoCall;
	
	public OnErrorNode(String fullStatement) {
		
		this.nodeType = NodeType.ONERRORGOTO;
		this.nodeFullStatement = fullStatement;
		
		String[] parts = fullStatement.split(" ");
		
		this.nodeGotoCall = parts[3];
	}
	
	public String getFullStatement() {
		
		return nodeFullStatement;
	}
	
	public String getGotoCall() {
		
		return nodeGotoCall;
	}
	
	@Override
	public String toString() {
		
		return nodeType.toString() + ":[" + nodeGotoCall + "]";
	}
}
