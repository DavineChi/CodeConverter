package com.converter.node;

public class VariableNode extends ASTNode {
	
	private String nodeAccessModifier;
	private String nodeDataType;
	private String nodeName;
	private boolean nodeIsConstant;
	
	public VariableNode(String accessModifier, String dataType, String name) {
		
		this(accessModifier, dataType, name, false);
	}
	
	public VariableNode(String accessModifier, String dataType, String name, boolean isConstant) {
		
		this.nodeType = NodeType.VARIABLE;
		this.nodeAccessModifier = accessModifier;
		this.nodeDataType = dataType;
		this.nodeName = name;
		this.nodeIsConstant = isConstant;
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
	
	public boolean isConstant() {
		
		return nodeIsConstant;
	}
	
	@Override
	public String toString() {
		
		return nodeType.toString() + ":[" + nodeName + "]";
	}
}
