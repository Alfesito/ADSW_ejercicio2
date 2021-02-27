package es.upm.dit.adsw.ej2;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
* @author Andres Alfaro Fernandez
* @author Daniel Gómez Campo
* @author Mateo Sarria Franco de Sarabia
* @version 07/02/2019
*/	
public class Dijkstra {
	private Map<Node, Node> nAnterior;
	private Map<Node, Integer> dist;
	private Graph graph;
	private Node src;
	private static final int INF = Integer.MAX_VALUE;
		
	/**
	 * Constructor. Calculates the minimal path to every other node in the graph	
	 * @param graph_
	 * @param src_
	 */
	public Dijkstra(Graph graph, Node src) {				/// O(TOTAL_DIJKSTRA) = O(n^2)
		this.nAnterior=new HashMap<>();
		this.dist=new HashMap<>();
		this.graph = graph;
		this.src = src;
		
		if(graph!=null) {
		Set<Node>noVisit = getAcces(src); 							// private: O(n)
		
		for(Node node: noVisit) {									// O(n)
			this.dist.put(node, INF);
		}
		int t=0;
		this.dist.put(src, t);
		
		while (!noVisit.isEmpty()){									// O(n) * O(n) = O(n^2)
			Node node = getMCerc(noVisit);								// private: O(n)
			noVisit.remove(node);										// O(1)
			int d = dist.get(node);										// O(1)
			List<Link> links = graph.getLinks(node);					// O(1)
			
			for(Link link: links) {										// O(n)
				Node next = graph.getNode(link.getDst());					// O(1)
				int dn = d + link.getWeight();								// O(1)
		
				if(dn < this.dist.get(next)) {							// O(1)
					this.dist.put(next,dn);
					this.nAnterior.put(next,node);
				}
			}
		}
		}else if (graph == null) {									// O(1)
			throw new NullPointerException();			
		}	
	}
	
	/**
	 * Shortest path (minimal weight)
	 * @param dst
	 * @return
	 */
	public java.util.List<Node> getPath(Node dst){
		List<Node> path = new ArrayList<>();
		if(dst.getName()!=null||dst.getName().length()!=0) {
		path.add(dst);
		}
		Node n = dst;
		int t=0;

		while (src!=n) {				
			n =nAnterior.get(n);
			if(n == null) {
				return null;
			}
			path.add(t, n);
		}
		return path;
	}
	
	
	private Node getMCerc(Set<Node> noVisit) {		/// O(getMCerc) = O(n)
		int distMin = Integer.MAX_VALUE;
		Node MCerc=null;
		
		for ( Node n : noVisit) {						// O(n) * O(1) = O(n)
			int d = dist.get(n);
			if (distMin>d) {								//O(1)
				distMin = d;
				MCerc = n;			
			}
		}
		return MCerc;
	}
	
	private Set<Node> getAcces(Node node) {			/// O(getAcces) = O(n)
		List<Node> cola = new ArrayList<>();
		Set<Node> conj = new HashSet<>();
		cola.add(node);
		
		while(true) {									// O(1) 
			Node prox;
			do {
				if (cola.isEmpty()) {
					return conj;
				}
				prox = cola.remove(0);
			} while (conj.contains(prox));					// O(1)
			conj.add(prox);
			
			for (Link link : graph.getLinks(prox)) {		// O(n) * O(1) = O(n)
				cola.add(graph.getNode(link.getDst()));
			}
		}	
	}
	
}
