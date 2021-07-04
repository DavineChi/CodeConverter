package com.converter;

import com.converter.node.ASTNode;
import com.converter.node.MethodNode;
import com.converter.node.ProgramNode;

public class Inspector {
	
	private ProgramNode program;
	private ASTNode[] nodes;
	private int methodNodeCount;
	
	public Inspector(ProgramNode program) {
		
		this.program = program;
		this.methodNodeCount = 0;
		
		initialize();
	}
	
	private void initialize() {
		
		this.nodes = program.getNodes();
		
		for (int i = 0; i < nodes.length; i++) {
			
			ASTNode child = nodes[i];
			
			if (child instanceof MethodNode) {
				
				methodNodeCount++;
			}
		}
	}
	
	public int getNumberOfMethods() {
		
		return methodNodeCount;
	}
	
	public MethodNode getMethodByName(String searchName) {
		
		MethodNode result = null;
		
		for (int i = 0; i < nodes.length; i++) {
			
			ASTNode child = nodes[i];
			
			if (child instanceof MethodNode) {
				
				MethodNode attempt = (MethodNode)(child);
				String name = attempt.getName();
				
				if (name.equals(searchName)) {
					
					result = attempt;
					
					break;
				}
			}
		}
		
		return result;
	}
}
