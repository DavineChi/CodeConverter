package com.converter.node;

public interface INodeCollection {
	
	static final int INITIAL_CAPACITY = 5;
	
	abstract void addNode(ASTNode node);
}
