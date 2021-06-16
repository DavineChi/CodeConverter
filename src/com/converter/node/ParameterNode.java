package com.converter.node;

import java.io.Serializable;

public class ParameterNode extends ASTNode implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nodePassedBy;
	private String nodeName;
	private String nodeDataType;
	
	public ParameterNode(String passedBy, String name, String dataType) {
		
		this.nodeType = NodeType.PARAMETER;
		this.nodePassedBy = passedBy;
		this.nodeName = name;
		this.nodeDataType = dataType;
	}
	
	public String getPassedBy() {
		
		// VB6 default is to pass arguments by reference (ByRef)
		
		return nodePassedBy;
	}
	
	public String getName() {
		
		return nodeName;
	}
	
	public String getDataType() {
		
		return nodeDataType;
	}
	
	@Override
	public String toString() {
		
		return nodeType.toString() + ":[" + nodeName + ":" + nodeDataType + "]";
	}
}
