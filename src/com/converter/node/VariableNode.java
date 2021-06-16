package com.converter.node;

import java.io.Serializable;

public class VariableNode extends ASTNode implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
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
