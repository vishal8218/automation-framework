package coach_management;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Practice {
	Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();

	
	void addEdge(int u, int v, int weight) {
	    graph.computeIfAbsent(u, k -> new HashMap<>()).put(v, weight);
	    graph.computeIfAbsent(v, k -> new HashMap<>()).put(u, weight); // reverse edge for undirected
	}
	
	public static void main(String[]args)
	{
		Practice prac=new Practice();
		prac.addEdge(1, 2, 5);
		prac.addEdge(1, 3, 5);
		
		
	}

}
