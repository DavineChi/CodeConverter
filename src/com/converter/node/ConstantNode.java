package com.converter.node;

public class ConstantNode extends ASTNode {
	
	private static final long serialVersionUID = 1L;
	
	private String nodeAccessModifier;
	private String nodeDataType;
	private String nodeName;
	
	public ConstantNode(String accessModifier, String dataType, String name) {
		
		this.nodeType = NodeType.CONSTANT;
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
		
		return nodeType.toString() + ":[" + nodeName + "]";
	}
}
