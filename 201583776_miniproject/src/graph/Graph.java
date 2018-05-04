package graph;

import graph.doublyLinkedList.DLLNode;
import graph.doublyLinkedList.DoublyLinkedList;
import graph.doublyLinkedList.NodeIterator;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import java.util.Stack;


public class Graph <E,T> {
	

	private DoublyLinkedList<Vertex <E,T>> vertexList;
	private DoublyLinkedList<Edge<E,T>> edgeList;
	

	private boolean directed;	
	private int unique_id = 0;

	public Graph(boolean directed) {
		vertexList = new DoublyLinkedList<Vertex<E,T>>();
		edgeList = new DoublyLinkedList<Edge<E,T>>();
		this.directed = directed;
	}
	
	public Vertex<E,T> add_Vertex_To_Graph(E data){
		return add_Vertex_To_Graph(data, unique_id++);
	}
	

	public Edge<E,T>[] add_Edge(Vertex<E,T> v1, Vertex<E,T> v2, T label, double weight){
		Edge<E,T> edges[] = new Edge[directed ? 1 : 2];

		edges[0] = new Edge<E,T>(v1, v2);
		edges[0].setPosition(edgeList.add(edges[0]));
		edges[0].setLabel(label);
		edges[0].setWeight(weight);
		
		if(!directed){
			edges[1] = new Edge<E,T>(v2, v1);
			edges[1].setLabel(label);
			edges[1].setWeight(weight);
			edges[1].setPosition(edgeList.add(edges[1]));
		}
		return edges;
	}
	
	public Edge<E,T>[] add_Edge(Vertex<E,T> v1, Vertex<E,T> v2){
		return add_Edge(v1, v2, null, 0.0);
	}

	public void remove_Vertex(Vertex<E,T> vertex){

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

	public void remove_Edge(Edge<E,T> edge){
		edge.getV1().removeOutEdge(edge.getIncidentPositionV1());
		edge.getV2().removeInEdge(edge.getIncidentPositionV2());
		edgeList.remove(edge.getPosition());
	}

	public NodeIterator<Vertex<E,T>> vertices_Iterator() {
		return vertexList.iterator();
	}

	public NodeIterator<Edge<E,T>> edges_Iterator() {
		return edgeList.iterator();
	}
	
	public Vertex<E,T>[] return_Vertices_Array(){
		Vertex<E,T>[] tmp = new Vertex[vertexList.size()];
		NodeIterator<Vertex<E,T>> iter = vertices_Iterator();
		int index = 0;
		while(iter.hasNext())
			tmp[index++] = iter.next();
		return tmp;
	}
	
	public Edge<E,T>[] return_Edges_Array(){
		Edge<E,T>[] tmp = new Edge[edgeList.size()];
		NodeIterator<Edge<E,T>> iter = edges_Iterator();
		int index = 0;
		while(iter.hasNext())
			tmp[index++] = iter.next();
		return tmp;
	}


	public Graph<E,T> clone(){
		Graph<E,T> graph = new Graph<E,T>(true);
		NodeIterator<Vertex<E,T>> iterV = vertexList.iterator();
		while(iterV.hasNext()){
			Vertex<E,T> vertex = iterV.next();
			graph.add_Vertex_To_Graph(vertex.getData(), vertex.getID());
		}
		Vertex<E,T> vertices[] = graph.return_Vertices_Array();
		NodeIterator<Edge<E,T>> iterE = edgeList.iterator();
		while(iterE.hasNext()){
			Edge<E,T> currentE = iterE.next();
			
			Vertex<E,T> v1 = vertices[get_Index_Of_Vertex_By_ID(vertices, currentE.getV1().getID())];
			Vertex<E,T> v2 = vertices[get_Index_Of_Vertex_By_ID(vertices, currentE.getV2().getID())];
			graph.add_Edge(v1, v2, currentE.getLabel(), currentE.getWeight());
		}
		graph.directed = directed;
		graph.unique_id = unique_id;
		return graph;
	}

	public String toString(){
		String output = "Vertices:\n";
		for(Vertex<E,T> v : return_Vertices_Array())
			output += String.format("%s ", v.toString());		
		output += "\n\nEdges:\n";		
		for(Edge<E,T> e : return_Edges_Array()){
			output += String.format("%s\n", e.toString());
		}
		return output;
	}
	
	
	private Vertex<E,T> add_Vertex_To_Graph(E data, int id){
		Vertex<E,T> vertex = new Vertex<E,T>(data, id);
		DLLNode<Vertex<E,T>> node = vertexList.add(vertex);
		vertex.setPosition(node);
		return vertex;
	}

	public int get_Index_Of_Vertex_By_ID(Vertex<E,T>[] vertices, int id){
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
	
	
	
	public Edge<E,T>[] dijkstra_Shortest_Path(Vertex<E,T> vFrom, Vertex<E,T> vTo){
		this.dijkstra_Shortest_Path(vFrom);
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
	
	public void dijkstra_Shortest_Path(Vertex<E,T> v){
		
		NodeIterator<Vertex<E,T>> iterV = vertices_Iterator();
		while(iterV.hasNext()){
			Vertex<E,T> currentV = iterV.next();
			currentV.setStatus(Vertex.UNVISITED);
			currentV.setDijkstra_value(Double.MAX_VALUE);
			currentV.setDijkstra_parent(null);
		}	

		NodeIterator<Edge<E,T>> iterE = edges_Iterator();
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

}
