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
	
	public String getNodePassedBy() {
		
		return nodePassedBy;
	}
	
	public String getNodeName() {
		
		return nodeName;
	}
	
	public String getNodeDataType() {
		
		return nodeDataType;
	}
	
	@Override
	public String toString() {
		
		String pass = nodePassedBy;
		
		if (nodePassedBy.equals("")) {
			
			pass = "none";
		}
		
		return "Passed By: " + pass + ", Name: " + nodeName + ", Data Type: " + nodeDataType;
	}
}
