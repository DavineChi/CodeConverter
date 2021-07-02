package com.converter.node;

public class PropertyCallNode extends ASTNode {
	
	private static final long serialVersionUID = 1L;
	
	private String nodeStatement;
	private String nodeSourceObject;
	private String nodePropertyCall;
	
	public PropertyCallNode(String statement) {
		
		this.nodeType = NodeType.PROPERTYCALL;
		this.nodeStatement = statement;
		
		String[] parts = statement.split("\\.");
		
		this.nodeSourceObject = parts[0];
		this.nodePropertyCall = parts[1];
	}
	
	public String getStatement() {
		
		return nodeStatement;
	}
	
	public String getSourceObject() {
		
		return nodeSourceObject;
	}
	
	public String getPropertyCall() {
		
		return nodePropertyCall;
	}
	
	@Override
	public String toString() {
		
		return nodeType.toString() + ":[" + nodePropertyCall + "]";
	}
}
