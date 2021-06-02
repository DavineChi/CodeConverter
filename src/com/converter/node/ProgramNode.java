package com.converter.node;

public class ProgramNode extends ASTNode {
	
	private static final int INITIAL_CAPACITY = 10;
	
	private ASTNode[] nodeNodes;
	private int capacity;
	private int size;
	
	public ProgramNode() {
		
		this.nodeType = NodeType.PROGRAM;
		this.capacity = INITIAL_CAPACITY;
		this.nodeNodes = new ASTNode[INITIAL_CAPACITY];
		this.size = 0;
	}
	
	public ProgramNode(ASTNode node) {
		
		this();
		this.addNode(node);
	}
	
	public ASTNode[] getNodes() {
		
		return nodeNodes;
	}
	}
}
