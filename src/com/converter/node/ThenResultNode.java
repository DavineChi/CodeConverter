package com.converter.node;

public class ThenResultNode extends BaseResultNode {
	
	private static final long serialVersionUID = 1L;
	
	private String nodeId;
	
	public ThenResultNode(String id) {
		
		super();
		
		this.nodeType = NodeType.THEN;
		this.nodeId = id;
	}
	
	public String getId() {
		
		return nodeId;
	}
	
	@Override
	public String toString() {
		
		return nodeType.toString() + ":[...(" + size + " items) " + nodeId + "]";
	}
}
