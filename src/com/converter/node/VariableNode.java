package com.converter.node;

public class VariableNode extends ASTNode {
	
	private static final long serialVersionUID = 1L;
	
	private String nodeAccessModifier;
	private String nodeDataType;
	private String nodeName;
	
	public VariableNode(String accessModifier, String dataType, String name) {
		
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
		
		String output;
		
		if (nodeDataType.equals("")) {
			
			output = nodeType.toString() + ":[" + nodeName + "]";
		}
		
		else {
			
			output = nodeType.toString() + ":[" + nodeName + ":" + nodeDataType + "]";
		}
		
		return output;
	}
}
