package com.converter.node;

import java.util.Arrays;

public class IfNode extends ASTNode implements INodeCollection {
	
	private ASTNode nodeCondition;
	private ASTNode nodeThenResult;
	private ASTNode nodeElseResult;
	private ASTNode[] nodeNodes;
	private int capacity;
	private int size;
	
	public IfNode(ASTNode condition) {
		
		this(condition, null, null);
	}
	
	public IfNode(ASTNode condition, ASTNode thenResult) {
		
		this(condition, thenResult, null);
	}
	
	public IfNode(ASTNode condition, ASTNode thenResult, ASTNode elseResult) {
		
		this.nodeType = NodeType.IF;
		this.nodeCondition = condition;
		this.nodeThenResult = thenResult;
		this.nodeElseResult = elseResult;
		this.capacity = INITIAL_CAPACITY;
		this.nodeNodes = new ASTNode[INITIAL_CAPACITY];
		this.size = 0;
	}
	
	public ASTNode getCondition() {
		
		return nodeCondition;
	}
	
	public ASTNode getThenResult() {
		
		return nodeThenResult;
	}
	
	public ASTNode getElseResult() {
		
		return nodeElseResult;
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
		
		return nodeType.toString() + ":[" + nodeCondition + " (" + size + " lines)]";
	}
}
