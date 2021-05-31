package com.converter.node;

public class ProgramNode extends ASTNode {
	
	private ASTNode nodeProgram;
	
	public ProgramNode(ASTNode program) {
		
		this.nodeType = NodeType.PROGRAM;
		this.nodeProgram = program;
	}
	
	public ASTNode getProgram() {
		
		return nodeProgram;
	}
}
