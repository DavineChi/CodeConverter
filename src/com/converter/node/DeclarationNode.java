package com.converter.node;

import java.io.Serializable;

public class DeclarationNode extends ASTNode implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nodeVariableName;
	private String nodeDataType;
	
	public DeclarationNode(String variableName, String dataType) {
		
		this.nodeType = NodeType.DECLARATION;
		this.nodeVariableName = variableName;
		this.nodeDataType = dataType;
	}
	
	public String getVariableName() {
		
		return nodeVariableName;
	}
	
	public String getDataType() {
		
		return nodeDataType;
	}
	
	@Override
	public String toString() {
		
		return nodeType.toString() + ":[" + nodeVariableName + ":" + nodeDataType + "]";
	}
}
