import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class BreadthFirstSearch  extends ASearch
{
	private LinkedList<ASearchNode> open;
	private LinkedList<ASearchNode> closed;
	
	@Override
	public String getSolverName() 
	{
		return "BFS";
	}

	@Override
	public ASearchNode createSearchRoot
	(
		IProblemState problemState
	) 
	{
        return new BlindSearchNode(problemState);
	}
	
	@Override
	public void initLists() 
	{
		open = new LinkedList<>();
		closed = new LinkedList<>();
	}

	@Override
	public ASearchNode getOpen
	(
		ASearchNode node
	) 
	{
        for (ASearchNode nextNode : open) if (nextNode.equals(node)) return nextNode;
		return null;
	}

	@Override
	public boolean isOpen
	(
		ASearchNode node
	) 
	{
        for (ASearchNode nextNode : open) if (nextNode.equals(node)) return true;
		return false;
	}
	
	@Override
	public boolean isClosed
	(
		ASearchNode node
	) 
	{
        for (ASearchNode nextNode : closed) if (nextNode.equals(node)) return true;
        return false;
	}

	@Override
	public void addToOpen
	(
		ASearchNode node
	) 
	{
        open.removeIf(nextNode -> nextNode.equals(node));
		open.add(node);
	}

	@Override
	public void addToClosed
	(
		ASearchNode node
	) 
	{
        closed.add(node);
	}

	@Override
	public int openSize() 
	{
		return open.size();
	}

	@Override
	public ASearchNode getBest() 
	{
		ASearchNode node = open.removeFirst();
//		addToClosed(node);
		return node;
	}
}
