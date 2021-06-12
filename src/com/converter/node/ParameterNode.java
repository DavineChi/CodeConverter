package com.converter.node;

public class ParameterNode extends ASTNode {
	
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
		
		String pass = nodePassedBy;
		
		if (nodePassedBy.equals("")) {
			
			// VB6 default is to pass arguments by reference (ByRef)
			pass = "default";
		}
		
		return "Passed: " + pass + ", Name: " + nodeName + ", Data Type: " + nodeDataType;
	}
}
