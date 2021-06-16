package com.converter.node;

import java.io.Serializable;
import java.util.Arrays;

public class ProgramNode extends ASTNode implements INodeCollection, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nodeId;
	private ASTNode[] nodeNodes;
	private int capacity;
	private int size;
	
	public ProgramNode(String id) {
		
		this(id, null);
	}
	
	public ProgramNode(String id, ASTNode node) {
		
		this.nodeType = NodeType.PROGRAM;
		this.nodeId = id;
		this.capacity = INITIAL_CAPACITY;
		this.nodeNodes = new ASTNode[INITIAL_CAPACITY];
		this.size = 0;
		
		this.addNode(node);
	}
	
	public String getId() {
		
		return nodeId;
	}
	
	public ASTNode[] getNodes() {
		
		return nodeNodes;
	}
	
	@Override
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
