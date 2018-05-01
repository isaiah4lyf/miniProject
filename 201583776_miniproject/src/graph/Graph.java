package graph;

import graph.doublyLinkedList.DLLNode;
import graph.doublyLinkedList.DoublyLinkedList;
import graph.doublyLinkedList.NodeIterator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Graph <E,T> {
	

	private DoublyLinkedList<Vertex <E,T>> vertexList;
	private DoublyLinkedList<Edge<E,T>> edgeList;
	

	private boolean directed;
	private boolean isCyclic;
	private boolean isConnected;
	private int connectedComponents;
	
	private int unique_id = 0;

	public Graph(boolean directed) {
		vertexList = new DoublyLinkedList<Vertex<E,T>>();
		edgeList = new DoublyLinkedList<Edge<E,T>>();
		this.directed = directed;
	}
	
	public Vertex<E,T> addVertex(E data){
		return addVertex(data, unique_id++);
	}
	

	public Edge<E,T>[] addEdge(Vertex<E,T> v1, Vertex<E,T> v2, T label, double weight){
		Edge<E,T> edges[] = new Edge[directed ? 1 : 2];

		edges[0] = new Edge<E,T>(v1, v2);
		edges[0].setLabel(label);
		edges[0].setWeight(weight);
		edges[0].setPosition(edgeList.add(edges[0]));

		if(!directed){
			edges[1] = new Edge<E,T>(v2, v1);
			edges[1].setLabel(label);
			edges[1].setWeight(weight);
			edges[1].setPosition(edgeList.add(edges[1]));
		}
		return edges;
	}
	
	public Edge<E,T>[] addEdge(Vertex<E,T> v1, Vertex<E,T> v2){
		return addEdge(v1, v2, null, 0.0);
	}

	public void removeVertex(Vertex<E,T> vertex){

		NodeIterator<Edge<E,T>> iterOutEdges = vertex.getOutEdges();
		while(iterOutEdges.hasNext()){
			Edge<E,T> currentE = iterOutEdges.next();
			Vertex<E,T> vTo = currentE.getV2();
			vTo.removeInEdge(currentE.getIncidentPositionV2());
			edgeList.remove(currentE.getPosition());
		}

		NodeIterator<Edge<E,T>> iterInEdges = vertex.getInEdges();
		while(iterInEdges.hasNext()){
			Edge<E,T> currentE = iterInEdges.next();
			Vertex<E,T> vFrom = currentE.getV1();
			vFrom.removeOutEdge(currentE.getIncidentPositionV1());
			edgeList.remove(currentE.getPosition());
		}
		
		vertexList.remove(vertex.getPosition());
	}

	public void removeEdge(Edge<E,T> edge){
		edge.getV1().removeOutEdge(edge.getIncidentPositionV1());
		edge.getV2().removeInEdge(edge.getIncidentPositionV2());
		edgeList.remove(edge.getPosition());
	}

	public NodeIterator<Vertex<E,T>> vertices() {
		return vertexList.iterator();
	}

	public NodeIterator<Edge<E,T>> edges() {
		return edgeList.iterator();
	}
	
	public Vertex<E,T>[] vertices_array(){
		Vertex<E,T>[] tmp = new Vertex[vertexList.size()];
		NodeIterator<Vertex<E,T>> iter = vertices();
		int index = 0;
		while(iter.hasNext())
			tmp[index++] = iter.next();
		return tmp;
	}
	
	public Edge<E,T>[] edges_array(){
		Edge<E,T>[] tmp = new Edge[edgeList.size()];
		NodeIterator<Edge<E,T>> iter = edges();
		int index = 0;
		while(iter.hasNext())
			tmp[index++] = iter.next();
		return tmp;
	}

	public boolean isDirected() {
		return directed;
	}
	

	public boolean areAdjacent(Vertex<E,T> v1, Vertex<E,T> v2){		
		Vertex<E,T> v = directed || (v1.getOutEdges().size() < v2.getOutEdges().size()) ? v1 : v2;
		NodeIterator<Edge<E,T>> iterOutE = v.getOutEdges();
		while(iterOutE.hasNext())
			if( (v == v1 && iterOutE.next().getV2() == v2) || (v == v2 && iterOutE.next().getV2() == v1) )
				return true;
		return false;
	}

	public void transitiveClosure(){
		Vertex<E,T> vertices[] = this.vertices_array();
		for(int k = 0; k < vertices.length; k++){
			for(int i = 0; i < vertices.length; i++){
				if(i == k) continue;
				if(areAdjacent(vertices[i], vertices[k])){
					for(int j = 0; j < vertices.length; j++){
						if(j == i || j == k) continue;
						if(areAdjacent(vertices[k], vertices[j]) && !areAdjacent(vertices[i], vertices[j]))
							this.addEdge(vertices[i],vertices[j], null, 0.0);
					}
				}
			}
		}
	}

	public Graph<E,T> clone(){
		Graph<E,T> graph = new Graph<E,T>(true);
		NodeIterator<Vertex<E,T>> iterV = vertexList.iterator();
		while(iterV.hasNext()){
			Vertex<E,T> vertex = iterV.next();
			graph.addVertex(vertex.getData(), vertex.getID());
		}
		Vertex<E,T> vertices[] = graph.vertices_array();
		NodeIterator<Edge<E,T>> iterE = edgeList.iterator();
		while(iterE.hasNext()){
			Edge<E,T> currentE = iterE.next();
			
			Vertex<E,T> v1 = vertices[getIndexOfVertexByID(vertices, currentE.getV1().getID())];
			Vertex<E,T> v2 = vertices[getIndexOfVertexByID(vertices, currentE.getV2().getID())];
			graph.addEdge(v1, v2, currentE.getLabel(), currentE.getWeight());
		}
		graph.directed = directed;
		graph.unique_id = unique_id;
		return graph;
	}

	public String toString(){
		String output = "Vertices:\n";
		for(Vertex<E,T> v : vertices_array())
			output += String.format("%s ", v.toString());		
		output += "\n\nEdges:\n";		
		for(Edge<E,T> e : edges_array()){
			output += String.format("%s\n", e.toString());
		}
		return output;
	}
	
	
	private Vertex<E,T> addVertex(E data, int id){
		Vertex<E,T> vertex = new Vertex<E,T>(data, id);
		DLLNode<Vertex<E,T>> node = vertexList.add(vertex);
		vertex.setPosition(node);
		return vertex;
	}

	public int getIndexOfVertexByID(Vertex<E,T>[] vertices, int id){
		int left = 0;
		int right = vertices.length-1;
		int mid;
		while(left <= right){
			mid = (left + right) / 2;
			if(vertices[mid].getID() == id)
				return mid;
			if(vertices[mid].getID() < id)
				left = mid + 1;
			else
				right = mid - 1;
		}
		return -1;
	}
	
	
	
	public Edge<E,T>[] dijkstra(Vertex<E,T> vFrom, Vertex<E,T> vTo){
		this.dijkstra(vFrom);
		Stack<Edge<E,T>> path = new Stack<>();
		Vertex<E,T> current = vTo;
		
		while(current.getDijkstra_edge() != null){
			path.push(current.getDijkstra_edge());
			current = current.getDijkstra_parent();
		}
		Edge<E,T>[] edges = new Edge[path.size()];
		int index =  0;
		while(!path.isEmpty())
			edges[index++] = path.pop();
		return edges;
	}
	
public void dijkstra(Vertex<E,T> v){
		
		NodeIterator<Vertex<E,T>> iterV = vertices();
		while(iterV.hasNext()){
			Vertex<E,T> currentV = iterV.next();
			currentV.setStatus(Vertex.UNVISITED);
			currentV.setDijkstra_value(Double.MAX_VALUE);
			currentV.setDijkstra_parent(null);
		}	

		NodeIterator<Edge<E,T>> iterE = edges();
		while(iterE.hasNext())
			iterE.next().setStatus(Edge.UNDISCOVERED);		
		v.setDijkstra_value(0);		
		PriorityQueue<Vertex<E,T>> pq = new PriorityQueue<>();
		pq.offer(v);
		v.setStatus(Vertex.VISITING);
		v.setDijkstra_parent(v);
		while(!pq.isEmpty()){
			
			Vertex<E,T> polled = pq.poll();
			v.setStatus(Vertex.VISITED);
			NodeIterator<Edge<E,T>> incidentEdges = polled.getOutEdges();
			
			while(incidentEdges.hasNext()){
				Edge<E,T> edge = incidentEdges.next();
				Vertex<E,T> oppositeVertex = edge.getV2();
				double pathCost = edge.getWeight() + polled.getDijkstra_value();
				
				if(oppositeVertex.getStatus() == Vertex.UNVISITED){
					oppositeVertex.setDijkstra_value(pathCost);
					oppositeVertex.setDijkstra_edge(edge);
					edge.setStatus(Edge.DISCOVERED);
					oppositeVertex.setStatus(Vertex.VISITING);
					oppositeVertex.setDijkstra_parent(polled);
					pq.offer(oppositeVertex);
				
				}else if(oppositeVertex.getStatus() == Vertex.VISITING){
					
					if(oppositeVertex.getDijkstra_value() > pathCost){
						oppositeVertex.setDijkstra_value(pathCost);
						edge.setStatus(Edge.DISCOVERED);
						oppositeVertex.setDijkstra_parent(polled);
						oppositeVertex.getDijkstra_edge().setStatus(Edge.FORWARD); 
						oppositeVertex.setDijkstra_edge(edge);
					}
				}
			}
		}
	}


	
	
	public Vertex<E,T>[] BFS(Vertex<E,T> vertex){
		
		NodeIterator<Vertex<E,T>> iterV = vertices();
		while(iterV.hasNext())
			iterV.next().setStatus(Vertex.UNVISITED);
		NodeIterator<Edge<E,T>> iterE = edges();
		while(iterE.hasNext())
			iterE.next().setStatus(Edge.UNDISCOVERED);
		DoublyLinkedList<Vertex<E,T>> BFS_list = new DoublyLinkedList<>();
		Queue<Vertex<E,T>> q = new LinkedList<Vertex<E,T>>();
		q.add(vertex);
		vertex.setStatus(Vertex.VISITING);
		while(!q.isEmpty()){
			Vertex<E,T> polled = q.poll();
			BFS_list.add(polled);
			polled.setStatus(Vertex.VISITED);
			NodeIterator<Edge<E,T>> incidentEdges = polled.getOutEdges();
			while(incidentEdges.hasNext()){
				Edge<E,T> edge = incidentEdges.next();
				Vertex<E,T> oppositeVertex = edge.getV2();
				if(oppositeVertex.getStatus() == Vertex.UNVISITED){
					edge.setStatus(Edge.DISCOVERED);
					oppositeVertex.setStatus(Vertex.VISITING);
					q.offer(oppositeVertex);
				}else{
					if(edge.getStatus() == Edge.UNDISCOVERED)
						edge.setStatus(Edge.CROSS);
				}
			}
		}
		
		NodeIterator<Vertex<E,T>> BFS_iter = BFS_list.iterator();
		Vertex<E,T> BFS[] = new Vertex[BFS_iter.size()];
		int index = 0;
		while(BFS_iter.hasNext())
			BFS[index++] = BFS_iter.next();
		return BFS;
	}
}
