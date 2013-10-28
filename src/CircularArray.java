import java.util.Iterator;
import java.lang.*;
import java.nio.channels.UnsupportedAddressTypeException;

import com.sun.tools.internal.ws.processor.model.java.JavaException;
import com.sun.tools.javac.jvm.Items;


public class CircularArray<T> implements Iterable<T>{
	
	private T[] items;
	private int head = 0;
	
	public CircularArray(int size){
		items = (T[]) new Object[size];
	}
	
	public void rotate(int shiftRight){
		
		if(shiftRight < 0){
			head = (head+shiftRight)%items.length;
		}
		else{
			head = head + shiftRight%items.length;
		}
	}
	
	public T get(int i){
		if(i<0||i>items.length)
			throw new IndexOutOfBoundsException("out of bound");
		else 
			return items[(head+i)%items.length];
	}
	
	@Override
	public Iterator<T> iterator() {
		
		return new CircularArrayIterator<T>(this);
	}	
	
	private class CircularArrayIterator<T> implements Iterator<T>{
		
		private int _current = 0;
		private T[] _items;
		
		public CircularArrayIterator(CircularArray<T> array){
			_items = array.items;
		}
		
		@Override
		public boolean hasNext(){
			return _current < items.length - 1;
		}
		
		@Override
		public T next(){
			
			T item = (T) _items[_current];
			_current++;
			return item;
		}
        
		
		public void remove(){
			throw new UnsupportedOperationException("unsupported operations");
		}	
	}
	
	public static void main(String[] argv){
		
		CircularArray<Integer> array = new CircularArray<Integer>(10);
		Iterator<Integer> iterator = array.iterator();
		
		while(iterator.hasNext())
			System.out.println(iterator.next());
	}

	
	
}
