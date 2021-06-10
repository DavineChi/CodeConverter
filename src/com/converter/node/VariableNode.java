package com.converter.node;

public class VariableNode extends ASTNode {
	
	private String nodeAccessModifier;
	private String nodeDataType;
	private String nodeName;
	
	public VariableNode(String accessModifier, String dataType, String value) {
		
		this.nodeType = NodeType.VARIABLE;
		this.nodeAccessModifier = accessModifier;
		this.nodeDataType = dataType;
		this.nodeName = name;
	}
	
	public String getAccessModifier() {
		
		return nodeAccessModifier;
	}
	
	public String getDataType() {
		
		return nodeDataType;
	}
	
	public String getName() {
		
		return nodeName;
	}
	
	
	@Override
	public String toString() {
		
		return nodeType.toString() + ":" + nodeName;
	}
}
