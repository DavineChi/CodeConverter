package com.converter.node;

public class BlankLineNode extends ASTNode {
	
	private static final long serialVersionUID = 1L;
	
	private String nodeLine = "";
	
	public BlankLineNode() {
		
		this.nodeType = NodeType.BLANKLINE;
	}
	
	public String getLine() {
		
		return nodeLine;
	}
	
	@Override
	public String toString() {
		
		return nodeType.toString();
	}
}
