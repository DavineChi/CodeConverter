package com.converter.node;

public class AttributeNode extends ASTNode {
	
	private static final long serialVersionUID = 1L;
	
	private String nodeAccessModifier;
	private String nodeName;
	
	public AttributeNode(String name) {
		
		this.nodeType = NodeType.ATTRIBUTE;
		this.nodeName = name;
	}
	
	public String getAccessModifier() {
		
		return nodeAccessModifier;
	}
	
	public String getName() {
		
		return nodeName;
	}
	
	@Override
	public String toString() {
		
		return nodeType.toString() + ":[" + nodeName + "]";
	}
}
