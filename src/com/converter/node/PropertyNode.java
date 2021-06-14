package com.converter.node;

public class PropertyNode extends ASTNode {
	
	private String nodeProperty;
	
	public PropertyNode(String property) {
		
		this.nodeType = NodeType.PROPERTY;
		this.nodeProperty = property;
	}
	
	public String getProperty() {
		
		return nodeProperty;
	}
	
	@Override
	public String toString() {
		
		String[] parts = nodeProperty.split(" ");
		
		return nodeType.toString() + ":[" + parts[0] + "]";
	}
}
