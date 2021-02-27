package es.upm.dit.adsw.ej2;

import java.util.Random;

/**
 * Medidor de tiempos para el algoritmo de Dijkstra
 * @author Daniel Gomez Campo
 * @author Mateo Sarria Franco de Sarabia
 * @author Andres Alfaro Fernandez
 * @version 19/02/2019
 */
public class DijkstraMeter {
	
	public DijkstraMeter() {}

/**
 * 	Mide el tiempo de ejecucioon para un graph de N nodos
 * @param graph
 * @return tiempo de ejecucion de un graph de N nodos
 */
	public static long doit(Graph graph) {
		if(graph==null) {
			throw new NullPointerException();
		}else {
		Node n0=graph.getNode("0");
		long t0=System.currentTimeMillis();
		new Dijkstra(graph, n0);
		long t2=System.currentTimeMillis();
		return t2-t0;
		}
	}

	/**
	 * Metodo principal
	 * @param args
	 */
	public static void main(java.lang.String[] args) {
		for(int n=1000;n<=40000; n+=2000) {
			Graph graph=new Graph();
			load(graph, n);					//generar grafo
			long t = doit(graph);			//medir tiempos
			System.out.printf("%s %d%n", n, t);
		}
		
	}
	
	
	/**
	 * Genera N nodos, cada uno genera un nº fijo X, de enlaces a nodos aleatoriamente
	 * @param graph
	 * @param n
	 * @return genera un graph de tamaño n
	 */
	public static void load(Graph graph, int n){
		if(graph!=null) {			
			Random r=new Random(); 
			
			for(int i=0; i<n; i++) {		//genera n nodos
				graph.addNode(new Node(Integer.toString(i),0,0));
			}
		
			for (int i=0; i<n; i++) {
				int t=0;
				for(int j=0; j<5; j++) {
					t++;
					if(t<5) {
						int w=1;
						int rDst=r.nextInt(n);	
						String src=Integer.toString(i);
						String dst=Integer.toString(rDst);
						Link link=new Link (src,dst, w);
						graph.addLink(link);
					}
				}
			}
				
/*			int t=0;
 			for (int i=0; i<n; i++) {
				while(t<5){
					int w=1;
					int rDst=r.nextInt(n);	
					String src=Integer.toString(t);
					String dst=Integer.toString(rDst);
					Link link=new Link (src,dst, w);
					graph.addLink(link);	
					t++;
				}
 			}*/
 			
		}else if(graph==null) {
			throw new NullPointerException();
		}
	}
		
	
}
