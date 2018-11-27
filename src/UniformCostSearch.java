import java.util.*;

public class UniformCostSearch   extends ASearch
{
	private PriorityQueue<ASearchNode> openPriorityQueue;
	private HashMap<String, ASearchNode> openHashMap;
	private HashSet<String> closed;

	private class ASearchNodeComparator implements Comparator<ASearchNode>
	{
		@Override
		public int compare(ASearchNode o1, ASearchNode o2) {
			if (o1.getG() < o2.getG()) return -1;
			if (o1.getG() > o2.getG()) return 1;
			return 0;
		}
	}

	@Override
	public String getSolverName()
	{
		return "UCS";
	}

	@Override
	public ASearchNode createSearchRoot
	(
		IProblemState problemState
	)
	{
		ASearchNode newNode = new BlindSearchNode(problemState);
		return newNode;
	}

	@Override
	public void initLists()
	{
		openPriorityQueue = new PriorityQueue<>(new ASearchNodeComparator());
		openHashMap = new HashMap<>();
		closed = new HashSet<>();
	}

	@Override
	public ASearchNode getOpen
	(
		ASearchNode node
	)
	{
		return openHashMap.get(node._currentProblemState.toString());
	}

	@Override
	public boolean isOpen
	(
		ASearchNode node
	)
	{
		return openHashMap.containsKey(node._currentProblemState.toString());
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
		String state = node._currentProblemState.toString();
		ASearchNode nodeInOpen = openHashMap.get(state);
		if (nodeInOpen != null)
			openPriorityQueue.remove(nodeInOpen);
		openPriorityQueue.add(node);
		openHashMap.put(state, node);
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
		return openPriorityQueue.size();
	}

	@Override
	public ASearchNode getBest() 
	{
		ASearchNode node = openPriorityQueue.remove();
		openHashMap.remove(node._currentProblemState.toString());
		return node;
	}

}
