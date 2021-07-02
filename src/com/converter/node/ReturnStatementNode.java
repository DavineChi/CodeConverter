package com.converter.node;

public class ReturnStatementNode extends ASTNode {
	
	private static final long serialVersionUID = 1L;
	
	private String nodeStatement;
	
	public ReturnStatementNode(String statement) {
		
		this.nodeType = NodeType.RETURN;
		this.nodeStatement = statement;
	}
	
	public String getStatement() {
		
		return nodeStatement;
	}
	
	@Override
	public String toString() {
		
		return nodeType.toString() + ":[" + nodeStatement + "]";
	}
}
