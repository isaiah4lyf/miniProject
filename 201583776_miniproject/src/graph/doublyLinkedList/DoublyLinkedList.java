package graph.doublyLinkedList;

import graph.doublyLinkedList.DLLNode;


public class DoublyLinkedList <E> {
	

	private DLLNode <E> head,tail;
	private int size;
	

	public DoublyLinkedList() {
		size = 0;
		head = null;
		tail = null;
	}
	
	public DLLNode<E> add(E data){
		DLLNode<E> node = new DLLNode<E>(data);
		
		if(size == 0){
			head = node;
		
		}else{
			tail.next = node;
			node.previous = tail;
		}

		tail = node;
		size++;
		return node;
	}
	
	
	
	public DLLNode<E> getHead() {
		return head;
	}

	public void setHead(DLLNode<E> head) {
		this.head = head;
	}

	public DLLNode<E> getTail() {
		return tail;
	}

	public void setTail(DLLNode<E> tail) {
		this.tail = tail;
	}


	public DLLNode<E> addFirst(E data){
		DLLNode<E> node = new DLLNode<E>(data);

		if(size > 0)
			head.previous = node;
		node.next = head;
		head = node;

		size++;
		return node;
	}
	

	public DLLNode<E> search(E elem) {
		
		if(size() != 0)
		{
			if(elem == tail.getData())
			{
				return tail;
			}
			else if(elem == head.getData())
			{
				return head;
			}
			else
			{
				if(head != null && head.hasNext())
				{
					DLLNode<E>  currentNode = head.next();
					while (currentNode != tail) {
						if (currentNode.getData().equals(elem)) {
							return currentNode;
						}
						currentNode = currentNode.next();
					}
				}

			}	
		}

		return null;
	}
	

	public void remove(DLLNode<E> node){
		
		if(head == node){
			
		
			if(size == 1){
				head = null;
			
			}else{
				node.next.previous = null;
				head = node.next;
			}

		}else if(tail == node){
			node.previous.next = null;
			tail = node.previous;

		}else{
			node.previous.next = node.next;
			node.next.previous =  node.previous;
		}

		node.destroy();
		size--;
	}

	public int size(){
		return size;
	}

	public DLLNode<E> first(){
		return head;
	}

	public String toString(){
		String output = "[";
		DLLNode<E> tmp = head;
		
		while(tmp != null){
			output += tmp.toString();
			if(tmp.next != null)
				output += ", ";
			tmp = tmp.next;
		}
		output += "]";
		return output;
	}
	
	public NodeIterator<E> iterator(){
		
		return new NodeIterator<E>() {
			private DLLNode<E> position = head;
			

			public E next(){
				DLLNode<E> node = position;
				position = position.next;
				return node.getData();
			}
			
			public boolean hasNext(){
				return position != null;
			}
			public NodeIterator<E> concatenate(NodeIterator<E> secondIter){
				DoublyLinkedList<E> newList = new DoublyLinkedList<E>();
				while(this.hasNext())
					newList.add(this.next());
				while(secondIter.hasNext())
					newList.add(secondIter.next());
				return newList.iterator();
			}
			
			public int size(){
				return DoublyLinkedList.this.size();
			}

			public String toString(){
				return DoublyLinkedList.this.toString();
			}
		};
	}
}
