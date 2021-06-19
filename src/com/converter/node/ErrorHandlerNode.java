package com.converter.node;

public class ErrorHandlerNode extends ASTNode {
	
	private static final long serialVersionUID = 1L;
	
	private String nodeErrorDescription;
	
	public ErrorHandlerNode(String errorDescription) {
		
		this.nodeType = NodeType.ERRORHANDLER;
		this.nodeErrorDescription = errorDescription;
	}
	
	public String getErrorDescription() {
		
		return nodeErrorDescription;
	}
	
	@Override
	public String toString() {
		
		return nodeType.toString() + ":[" + nodeErrorDescription + "]";
	}
}
