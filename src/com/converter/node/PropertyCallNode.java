package com.converter.node;

public class PropertyCallNode extends ASTNode {
	
	private static final long serialVersionUID = 1L;
	
	private String nodeOperator = ".";
	private String nodeSourceObjectName;
	private String nodePropertyName;
	
	public PropertyCallNode(String sourceObjectName, String propertyName) {
		
		this.nodeType = NodeType.PROPERTYCALL;
		this.nodeSourceObjectName = sourceObjectName;
		this.nodePropertyName = propertyName;
	}
	
	public String getOperator() {
		
		return nodeOperator;
	}
	
	public String getSourceObjectName() {
		
		return nodeSourceObjectName;
	}
	
	public String getPropertyName() {
		
		return nodePropertyName;
	}
	
	@Override
	public String toString() {
		
		return nodeType.toString() + ":[" + nodeSourceObjectName + nodeOperator + nodePropertyName + "]";
	}
}
