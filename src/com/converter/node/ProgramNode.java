package com.converter.node;

public class ProgramNode extends ASTNode {
	
	private static final int INITIAL_CAPACITY = 10;
	
	private ASTNode[] nodeNodes;
	
	public ProgramNode() {
		
		this.nodeType = NodeType.PROGRAM;
		this.nodeProgram = program;
		this.nodeNodes = new ASTNode[INITIAL_CAPACITY];
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
