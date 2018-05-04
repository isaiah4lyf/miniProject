package graph.doublyLinkedList;

public class DLLNode<E>{
	protected DLLNode<E> next, previous;
	private E data;
	
	
	protected DLLNode(E data, DLLNode<E> previous, DLLNode<E> next){
		this.data = data;
		this.previous = previous;
		this.next = next;
	}


	protected DLLNode(E data){
		this(data,null,null);
	}

	public boolean hasNext(){
		return next != null;
	}
	

	public boolean hasPrevious(){
		return previous != null;
	}
	

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}
	

	public DLLNode<E> next(){
		return next;
	}
	

	public DLLNode<E> previous(){
		return previous;
	}
	

	protected void destroy(){
		data = null;
		next = null;
		previous = null;
	}

	public String toString(){
		return String.format("%s", data.toString());
	}
}