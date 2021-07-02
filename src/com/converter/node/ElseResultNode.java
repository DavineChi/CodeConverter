package com.converter.node;

public class ElseResultNode extends BaseResultNode {
	
	private static final long serialVersionUID = 1L;
	
	public ElseResultNode() {
		
		super();
		
		this.nodeType = NodeType.ELSE;
	}
	
	@Override
	public String toString() {
		
		return nodeType.toString() + ":[...(" + size + " items)]";
	}
}
