package com.converter.node;

import java.util.Arrays;

public class WithNode extends ASTNode implements INodeCollection {
	
	private String nodeExpression;
	private ASTNode[] nodeNodes;
	private int capacity;
	private int size;
	
	public WithNode(String expression) {
		
		this.nodeType = NodeType.WITH;
		this.nodeExpression = expression;
		this.capacity = INITIAL_CAPACITY;
		this.nodeNodes = new ASTNode[INITIAL_CAPACITY];
		this.size = 0;
	}
	
	public String getExpression() {
		
		return nodeExpression;
	}
	
	@Override
	public void addNode(ASTNode node) {
		
		ASTNode[] nodesCopy;
		
		// Do not add if already in nodeStatements
		for (int i = 0; i < nodeNodes.length; i++) {
			
			if (nodeNodes[i] == node) {
				
				return;
			}
		}
		
		// Grow nodeStatements as needed
		if ((size + 1) > capacity) {
			
			capacity = nodeNodes.length + 10;
			nodesCopy = Arrays.copyOfRange(nodeNodes, 0, capacity);
			nodeNodes = nodesCopy;
		}
		
		nodeNodes[size] = node;
		
		size++;
	}
	
	@Override
	public String toString() {
		
		return nodeType.toString() + ":[" + nodeExpression + " (" + size + " lines)]";
	}
}