package com.converter.node;

import java.util.Arrays;

public class ProgramNode extends ASTNode {
	
	private static final int INITIAL_CAPACITY = 10;
	
	private String nodeId;
	private ASTNode[] nodeNodes;
	private int capacity;
	private int size;
	
	public ProgramNode(String id) {
		
		this.nodeId = id;
		this.nodeType = NodeType.PROGRAM;
		this.capacity = INITIAL_CAPACITY;
		this.nodeNodes = new ASTNode[INITIAL_CAPACITY];
		this.size = 0;
	}
	
	public ProgramNode(String id, ASTNode node) {
		
		this(id);
		this.addNode(node);
	}
	
	public String getId() {
		
		return nodeId;
	}
	
	public ASTNode[] getNodes() {
		
		return nodeNodes;
	}
	
	public void addNode(ASTNode node) {
		
		ASTNode[] nodesCopy;
		
		// Do not add if already in nodeNodes
		for (int i = 0; i < nodeNodes.length; i++) {
			
			if (nodeNodes[i] == node) {
				
				return;
			}
		}
		
		// Grow nodeNodes as needed
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
		
		return nodeType.toString() + ":[" + nodeId + "]";
	}
}
