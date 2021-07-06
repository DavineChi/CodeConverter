package com.converter;

import com.converter.node.ASTNode;
import com.converter.node.MethodNode;
import com.converter.node.ProgramNode;

public class Inspector {
	
	private ProgramNode program;
	private ASTNode[] nodes;
	private int methodNodeCount;
	private int currentNodeIndex;
	
	
	public Inspector(ProgramNode program) {
		
		this.program = program;
		this.methodNodeCount = 0;
		this.currentNodeIndex = 0;
		
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
	
	public ProgramNode getProgram() {
		
		return program;
	}
	
	public ASTNode[] getNodes() {
		
		return nodes;
	}
	
	public int getNumberOfMethods() {
		
		return methodNodeCount;
	}
	
	public ASTNode nextNode() {
		
		ASTNode result = null;
		
		result = nodes[currentNodeIndex];
		
		currentNodeIndex++;
		
		return result;
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
	
	public boolean endOfNodes() {
		
		boolean result = false;
		
		if (currentNodeIndex >= nodes.length) {
			
			return true;
		}
		
		ASTNode check = nodes[currentNodeIndex];
		
		if (check == null) {
			
			result = true;
		}
		
		return result;
	}
}
