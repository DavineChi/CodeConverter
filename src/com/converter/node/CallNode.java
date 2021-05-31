package com.converter.node;

public class CallNode extends ASTNode {
	
	private ASTNode nodeFunction;
	private ASTNode[] nodeArguments;
	
	public CallNode(ASTNode function, ASTNode[] arguments) {
		
		this.nodeType = NodeType.CALL;
		this.nodeFunction = function;
		this.nodeArguments = arguments;
	}
	
	public ASTNode getFunction() {
		
		return nodeFunction;
	}
	
	public ASTNode[] getArguments() {
		
		return nodeArguments;
	}
}
