package dsa_assignment1;

public class OrderedMruList<E extends Comparable<E>> implements OrderedMruListInterface<E>
{
	//this will be list 1.. it will not have a next2 or prev2 set.
	MLNodeInterface<E>	headOrdered	= new MLNode<E>(null);

	//this will be list 2 - Most Recently Used.. it will not have a next1 or prev1 set.
	MLNodeInterface<E>	headMRU		= new MLNode<E>(null);

	public OrderedMruList()
	{
	}
	
	public boolean isEmptyOrdered()
	{
		if (this.headOrdered.getNext1().equals(this.headOrdered))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean isEmptyMru()
	{
		if (this.headMRU.getNext2().equals(this.headMRU))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public OrderedMruListInterface<E> touch(MLNodeInterface<E> target)
	{
		target.remove2();
		target.addAfter2(this.headMRU);
		return this;
	}
	
	public MLNodeInterface<E> getFirstMru()
	{
		if (this.isEmptyMru())
		{
			return null;
		}
		else
		{
			return this.headMRU.getNext2();
		}
	}
	
	public MLNodeInterface<E> getFirstOrdered()
	{
		if (this.isEmptyOrdered())
		{
			return null;
		}
		else
		{
			return this.headOrdered.getNext1();
		}
	}
	
	public MLNodeInterface<E> getNextOrdered(MLNodeInterface<E> current)
	{
		MLNodeInterface<E> nextNode = current.getNext1();
		if (nextNode.equals(this.headOrdered))
		{
			return null;
		}
		else
		{
			return nextNode;
		}
	}

	public MLNodeInterface<E> getNextMru(MLNodeInterface<E> current)
	{
		MLNodeInterface<E> nextNode = current.getNext2();
		if (nextNode.equals(this.headMRU))
		{
			return null;
		}
		else
		{
			return nextNode;
		}
	}

	public OrderedMruListInterface<E> remove(MLNodeInterface<E> target)
	{
		target.remove1();
		target.remove2();
		return this;
	}
	
	public OrderedMruListInterface<E> add(E element)
	{
		MLNodeInterface<E> newNode = new MLNode<E>(element);
		
		MLNodeInterface<E> nextNode = new MLNode<E>(element);
		//add to ordered (list 1) - in correct position.
		MLNodeInterface<E> currentNode = this.headOrdered;
		Boolean foundPlace = false;
		while (foundPlace == false)
		{
			nextNode = currentNode.getNext1();
			if (nextNode == null || nextNode.equals(this.headOrdered))
			{
				foundPlace = true;
			}
			else
			{
				if (newNode.getElement().compareTo(nextNode.getElement()) < 0)
				{
					foundPlace = true;
				}
				else
				{
					currentNode = nextNode;
				}
			}
			
		}
		newNode.addAfter1(currentNode);
		
		//add to MRU (list 2)
		newNode.addAfter2(this.headMRU);
		
		return this;
	}
}
