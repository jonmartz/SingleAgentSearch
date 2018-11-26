import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class BreadthFirstSearch  extends ASearch
{
	private LinkedHashMap<String, ASearchNode> open;
	private HashSet<String> closed;
	
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
		open = new LinkedHashMap<>();
		closed = new HashSet<>();
	}

	@Override
	public ASearchNode getOpen
	(
		ASearchNode node
	) 
	{
		return open.get(node._currentProblemState.toString());
	}

	@Override
	public boolean isOpen
	(
		ASearchNode node
	) 
	{
        return open.containsKey(node._currentProblemState.toString());
	}
	
	@Override
	public boolean isClosed
	(
		ASearchNode node
	) 
	{
        return closed.contains(node._currentProblemState.toString());
	}

	@Override
	public void addToOpen
	(
		ASearchNode node
	) 
	{
        open.put(node._currentProblemState.toString(), node);
	}

	@Override
	public void addToClosed
	(
		ASearchNode node
	) 
	{
        closed.add(node._currentProblemState.toString());
	}

	@Override
	public int openSize() 
	{
		return open.size();
	}

	@Override
	public ASearchNode getBest() 
	{
		Iterator<ASearchNode> iterator = open.values().iterator();
		ASearchNode node = iterator.next();
		iterator.remove();
		return node;
	}
}
