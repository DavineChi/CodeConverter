package com.converter.node;

import java.util.Arrays;

public class EHBlockNode extends ASTNode implements INodeCollection {
	
	private static final long serialVersionUID = 1L;
	
	private String nodeId;
	private ASTNode[] nodeNodes;
	private int capacity;
	private int size;
	
	public EHBlockNode(String id) {
		
		this.nodeType = NodeType.EHBLOCK;
		this.nodeId = id;
		this.capacity = INITIAL_CAPACITY;
		this.nodeNodes = new ASTNode[INITIAL_CAPACITY];
		this.size = 0;
	}
	
	public String getId() {
		
		return nodeId;
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
