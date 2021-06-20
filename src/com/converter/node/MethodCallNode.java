package com.converter.node;

public class MethodCallNode extends ASTNode {
	
	private static final long serialVersionUID = 1L;
	
	private String nodeMethodName;
	private String[] nodeArguments;
	
	public MethodCallNode(String methodName) {
		
		this(methodName, null);
	}
	
	public MethodCallNode(String methodName, String[] arguments) {
		
		this.nodeType = NodeType.METHODCALL;
		this.nodeMethodName = methodName;
		this.nodeArguments = arguments;
	}
	
	public String getMethodName() {
		
		return nodeMethodName;
	}
	
	public String[] getArguments() {
		
		return nodeArguments;
	}
	
	@Override
	public String toString() {
		
		return nodeType.toString() + ":[" + nodeMethodName + "]";
	}
}
