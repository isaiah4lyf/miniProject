package graph;

import graph.doublyLinkedList.DLLNode;


public class Edge <E,T> {
	private Vertex<E,T> v1, v2;
	private DLLNode<Edge<E,T>> incidentPositionV1, incidentPositionV2;
	private T label;
	private double weight;
	private DLLNode<Edge<E,T>> position;
	private int status;
	public static final int UNDISCOVERED = 0;
	public static final int DISCOVERED = 1;
	public static final int BACK = 2;
	public static final int FORWARD = 3;
	public static final int CROSS = 4;
	
	protected Edge(Vertex<E,T> v1, Vertex<E,T> v2){
		this.v1 = v1;
		this.v2 = v2;
		this.incidentPositionV1 = this.v1.addOutEdge(this);
		this.incidentPositionV2 = this.v2.addInEdge(this);
	}
	
	public Vertex<E,T> getOpposite(Vertex<E,T> v){
		if(v != v1 && v != v2)
			return null;
		return v1 == v ? v2 : v1;
	}
	

	public Vertex<E,T> getV1() {
		return v1;
	}

	public Vertex<E,T> getV2() {
		return v2;
	}


	public T getLabel() {
		return label;
	}


	public void setLabel(T label) {
		this.label = label;
	}


	public double getWeight() {
		return weight;
	}


	public void setWeight(double weight) {
		this.weight = weight;
	}

	protected DLLNode<Edge<E,T>> getPosition() {
		return position;
	}

	protected void setPosition(DLLNode<Edge<E,T>> position) {
		this.position = position;
	}

	public int getStatus() {
		return status;
	}
	
	public String getStatusString() {
		String statusString[] = {"Undiscovered","Discovered", "Back", "Forward","Cross"};
		return statusString[status];
	}
	
	protected void setStatus(int status) {
		this.status = status;
	}

	protected DLLNode<Edge<E,T>> getIncidentPositionV1() {
		return incidentPositionV1;
	}

	protected DLLNode<Edge<E,T>> getIncidentPositionV2() {
		return incidentPositionV2;
	}

	public String toString(){
		return label == null ? String.format("(%s, %s)", v1.toString(),v2.toString()) : String.format("(%s)", label);
	}
}
