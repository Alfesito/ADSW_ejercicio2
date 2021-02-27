package es.upm.dit.adsw.ej2;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Graph= Nodes + Links 
 * @author Daniel Gomez Campo
 * @author Mateo Sarria Franco de Sarabia 
 * @author Andres Alfaro Fernandez
 * @version 07/02/2019
 */
public class Graph {
	private List <Node> n = new ArrayList <> ();
	private List <Link> l = new ArrayList <> ();
	private Map<String, Node> nodesMap = new HashMap <> ();
	private Map<Node, List<Link>> linksMap = new HashMap <> ();
	
	/**Array
	 * Constructor 
	 */
	public Graph() { }
	/**
	 * Add a node
	 * @param node
	 */
	public void addNode (Node node) {			
		n.add(node);
		String nombreNode=node.getName();
		
		nodesMap.put(nombreNode, node);
		linksMap.put(node, new ArrayList<>());
	}
	/**
	 * Add a link
	 * @param link
	 */
	public void addLink(Link link) {	
		l.add(link);
		
		Node src = this.getNode(link.getSrc());
		List <Link> links =linksMap.get(src);
		links.add(link);	
	}
	/**
	 * Add a bidirectional link. A to B, and B to A
	 * @param a node
	 * @param b another node
	 * @param w link weight
	 */
	public void addLink2D (java.lang.String a, java.lang.String b, int w) {
		Link l_ab = new Link(a,b,w);
		Link l_ba = new Link(b,a,w);
		l.add(l_ab);
		l.add(l_ba);
		
		this.addLink(l_ab);
		this.addLink(l_ba);
	}
	/**
	 * Getter
	 * @return list of nodes. The empty list if there are none
	 */
	public java.util.List <Node> getNodes(){
		return n;						
	}
	
	/**
	 * Get a node by name
	 * @param name
	 * @return node . Null if not such a node 
	 */
	public Node getNode(java.lang.String name) {	////
		if(nodesMap.containsKey(name)) {
			return nodesMap.get(name);
		}else {
			return null;
		}
	}
	
	/**
	 * Get links in the graph
	 * @return list of links.The empty list if there is none
	 */
	public java.util.List <Link> getLinks(){	//igual a getNodes, pero con la clase Link
		return l;
	}
	
	/**
	 * Get links from a given node
	 * @param node - source node
	 * @return list of links starting at the given node. The empty list if there is none
	 */
	public java.util.List <Link> getLinks(Node node){	////	
		List <Link>listaVacia=new ArrayList<>();
		if(linksMap.containsKey(node)) {
			return linksMap.get(node);	
		}else {
			return listaVacia;
		}
	}
	
	/**
	 * Get link from a source to a destination node
	 * @param src - source node
	 * @param dst - destination node
	 * @return link from src to dst. Null if no link 
	 */
	public Link getLink(Node src, Node dst) {					
		for(int i=0; i<=l.size()-1; i++) {				
			if((src.getName().equals(l.get(i).getSrc())) && (dst.getName().equals(l.get(i).getDst()))) {				
				return l.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Aggregated weight of a list of nodes				//usar getLink 
	 * @param path - list of nodes
	 * @return total weight. -1 is some link is missing
	 */
	
	public int getWeight(java.util.List<Node> path) {
		int tweight=0;
		if(path.size()==0) {
			return -1;
		}
		for (int i=1; i<path.size();i++) {
			Node nsrc = path.get(i-1);  					
			Node ndst = path.get(i);						
			if (this.getLink(nsrc,ndst) == null) {
				return -1;
			}
			tweight += this.getLink(nsrc,ndst).getWeight();
		}
		return tweight;
	}
}
