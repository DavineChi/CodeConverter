package com.converter.node;

import java.io.Serializable;
import java.util.Arrays;

public class ElseResultNode extends ASTNode implements INodeCollection, Serializable {

	private static final long serialVersionUID = 1L;
	
	private ASTNode[] nodeNodes;
	private int capacity;
	private int size;
	
	public ElseResultNode() {
		
		this.nodeType = NodeType.ELSE;
		this.capacity = INITIAL_CAPACITY;
		this.nodeNodes = new ASTNode[INITIAL_CAPACITY];
		this.size = 0;
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
		
		return nodeType.toString() + ":[...(" + size + " lines)]";
	}
}
