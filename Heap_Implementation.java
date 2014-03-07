	import java.io.*;

class Node
{
	private int iData;
	public Node(int key)
	{
		iData = key;
	}
	
	public int getKey()
	{
		return iData;
	}
	
	public void setKey(int key)
	{
		iData = key;
	}
}

class Heap_Implementation 
{
	private Node[] heapArray;
	private int maxSize;
	private int currentSize;
	
	public Heap_Implementation(int max)
	{
		maxSize = max;
		currentSize = 0;
		heapArray = new Node[maxSize];
	}
	
	public boolean isEmpty()
	{
		return currentSize == 0;
	}
	
	public boolean insert(int key)
	{
		if(currentSize == maxSize)
		{
			return false;
		}
		
		Node newNode = new Node(key);
		heapArray[currentSize] = newNode;
		trickleUp(currentSize++);
		return true;
	}
	
	public void trickleUp(int index)
	{
		int parent = (index - 1)/2;
		Node bottom = heapArray[index];
		
		while(index>0 && heapArray[parent].getKey() < bottom.getKey())
		{
			heapArray[index] = heapArray[parent];
			index = parent;
			parent = (parent - 1) / 2;
		}
		
		heapArray[index] = bottom;
	}
	
	public Node delete()
	{
		Node root = heapArray[0];
		heapArray[0] = heapArray[currentSize--];
		trickleDown(0);
		return root;
	}
	
	public void trickleDown(int index)
	{
		int largestChild;
		Node top = heapArray[index];
		while(index < currentSize / 2)
		{
			int leftChild = 2*index + 1;
			int rightChild = leftChild + 1;
				
			if(rightChild < currentSize && heapArray[leftChild].getKey() < heapArray[rightChild].getKey())
			{
				largestChild = rightChild;
			}
			else
				largestChild = leftChild;
			
			if(top.getKey() > heapArray[largestChild].getKey()) break;
			
			heapArray[index] = heapArray[largestChild];
			index = largestChild;
		}
		heapArray[index] = top;
	}
	
	public boolean change(int index, int newValue)
	{
		if(index < 0 || index > maxSize)
		{
			return false;
		}
		
		int old = heapArray[index].getKey();
		heapArray[index].setKey(newValue);
		
		if(old < newValue)
		{
			trickleUp(index);
		}
		else
		{
			trickleDown(index);
		}
		return true;
	}

	public void displayHeap()
	{
		System.out.println("Heap Array: ");
		for(int m=0; m<currentSize; m++)
		{
			if(heapArray[m] != null)
			{
				System.out.println(" "+heapArray[m].getKey());
			}
			else
			{
				System.out.println("---");
			}
		}
		
		System.out.println();
		
		int nBlanks = 32;
		int itemsPerRow = 1;
		int column = 0;
		int j = 0;
		String dots = "---------------------------";
		System.out.println(dots+dots);
		
		while(currentSize > 0)
		{
			if(column == 0)
			{
				for(int k = 0; k<nBlanks; k++)
				{
					System.out.print(' ');
				}
			}
			System.out.print(heapArray[j].getKey());
			
			if(++j == currentSize)
				break;
			
			if(++column == itemsPerRow)
			{
				nBlanks /= 2;
				itemsPerRow *= 2;
				column = 0;
				System.out.println();
			}
			else
			{
				for(int k = 0; k<nBlanks*2 - 2; k++)
					System.out.print(' ');
			}
		}
		
		System.out.println("\n"+dots+dots);
	}
}

class HeapApp
{
	public static void main(String[] args) throws IOException
	{
		int value, value2;
		Heap_Implementation theHeap = new Heap_Implementation(31);
		boolean success;
		
		theHeap.insert(70);
		theHeap.insert(40);
		theHeap.insert(50);
		theHeap.insert(20);
		theHeap.insert(60);
		theHeap.insert(100);
		theHeap.insert(80);
		theHeap.insert(30);
		theHeap.insert(10);
		theHeap.insert(90);
		
		while(true)
		{
			System.out.println("Enter first letter of ");
			System.out.print("show, insert, remove, change: ");
			int choice = getChar();
	
			switch(choice)
			{
			case 's':
				theHeap.displayHeap();
				break;
			case 'i':
				System.out.println("Insert the value you want to insert: ");
				value = getInt();
				success = theHeap.insert(value);
				if(!success)
				{
					System.out.println("Can not insert! Heap is full!!!");
				}
				break;
			case 'r':
				if(!theHeap.isEmpty())
					theHeap.delete();
				else
					System.out.println("Can not remove! Heap is Empty!!");
				break;
			case 'c':
				System.out.println("Enter current index of item: ");
				value = getInt();
				System.out.println("Enter new key: ");
				value2 = getInt();
				
				success = theHeap.change(value, value2);
				if(!success)
				{
					System.out.println("Invalid Index!!");
				}
				break;
			default:
				System.out.println("Invalid Entry: ");
			}
		}
	}
	
	public static String getString() throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
	
	public static char getChar() throws IOException
	{
		String s = getString();
		return s.charAt(0);
	}
	
	public static int getInt() throws IOException
	{
		String s = getString();
		return Integer.parseInt(s);
	}
}
