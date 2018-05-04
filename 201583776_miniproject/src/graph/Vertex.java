package graph;

import graph.doublyLinkedList.DLLNode;
import graph.doublyLinkedList.DoublyLinkedList;
import graph.doublyLinkedList.NodeIterator;


public class Vertex <E,T> implements Comparable<Vertex<E,T>>{
	

	private E data;
	private DoublyLinkedList<Edge<E,T>> inEdges,outEdges;
	private DLLNode<Vertex<E,T>> position;
	private int status;
	private int color;
	private final int id;
	
	private Vertex<E,T> dijkstra_parent;
	private double dijkstra_value;
	private Edge<E,T> dijkstra_edge;
	
	public static final int UNVISITED = 0;
	public static final int VISITING = 1;
	public static final int VISITED = 2;
	
	protected static final int UNCOLORED = 0;

	protected Vertex(E data, int id) {
		this.data = data;
		this.status = UNVISITED;
		this.color = 0;
		this.id = id;
		inEdges = new DoublyLinkedList<Edge<E,T>>();
		outEdges = new DoublyLinkedList<Edge<E,T>>();
	}
	
	protected Vertex(E data) {
		this(data,0);
	}

	public Vertex<E,T>[] getNeighbors(){
		Vertex<E,T>[] neighbors = new Vertex[outEdges.size()];
		NodeIterator<Edge<E,T>> iter = outEdges.iterator();
		int index = 0;
		Edge<E,T> current = null;
		while(iter.hasNext()){
			current = iter.next();
			neighbors[index++] = current.getOpposite(this);
		}
		return neighbors;
	}

	public Vertex<E,T>[] getNeighbors_in(){
		Vertex<E,T>[] neighbors = new Vertex[inEdges.size()];
		NodeIterator<Edge<E,T>> iter = inEdges.iterator();
		int index = 0;
		Edge<E,T> current = null;
		while(iter.hasNext()){
			current = iter.next();
			neighbors[index++] = current.getOpposite(this);
		}
		return neighbors;
	}

	public NodeIterator<Edge<E,T>> getOutEdges(){
		return outEdges.iterator();
	}
	

	public NodeIterator<Edge<E,T>> getInEdges(){
		return inEdges.iterator();
	}

	protected DLLNode<Edge<E,T>> addOutEdge(Edge<E,T> e){
		return outEdges.add(e);
	}
	

	protected DLLNode<Edge<E,T>> addInEdge(Edge<E,T> e){
		return inEdges.add(e);
	}
	

	protected void removeInEdge(DLLNode <Edge<E,T>> node){
		inEdges.remove(node);
	}

	protected void removeOutEdge(DLLNode <Edge<E,T>> node){
		outEdges.remove(node);
	}

	public E getData() {
		return data;
	}

	protected DLLNode<Vertex<E,T>> getPosition() {
		return position;
	}

	protected void setPosition(DLLNode<Vertex<E,T>> position) {
		this.position = position;
	}


	public void setData(E data) {
		this.data = data;
	}
	
	public int getStatus() {
		return status;
	}

	protected void setStatus(int status) {
		this.status = status;
	}


	public int getColor() {
		return color;
	}

	protected void setColor(int color) {
		this.color = color;
	}
	
	public Vertex<E,T> getDijkstra_parent() {
		return dijkstra_parent;
	}

	protected void setDijkstra_parent(Vertex<E,T> dijkstra_parent) {
		this.dijkstra_parent = dijkstra_parent;
	}

	public double getDijkstra_value() {
		return dijkstra_value;
	}


	protected void setDijkstra_value(double dijkstra_value) {
		this.dijkstra_value = dijkstra_value;
	}
	

	public Edge<E,T> getDijkstra_edge() {
		return dijkstra_edge;
	}

	protected void setDijkstra_edge(Edge<E,T> dijkstra_edge) {
		this.dijkstra_edge = dijkstra_edge;
	}
	

	public int getID(){
		return id;
	}
	

	public int compareTo(Vertex<E,T> v) {
		if(v.getDijkstra_value() == getDijkstra_value())
			return 0;
		else if(v.getDijkstra_value() < getDijkstra_value())
			return 1;
		else
			return -1;
	}	
	

	public String toString(){
		return String.format("<%s>", data.toString());
	}
}
