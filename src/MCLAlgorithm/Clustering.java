/**
 * 
 */
package MCLAlgorithm;

/**
 * @author Sagar Shinde
 *
 */
import java.util.*;
public class Clustering {
	static HashMap<String, Integer> nodes;
	static HashMap<Integer, String> nodes1;
	int[][] getAdjacencyMatrix(ArrayList<String> nodeRelation){
		nodes=new HashMap<String, Integer>();
		nodes1=new HashMap<Integer, String>();
		int count=0;
		for(String x: nodeRelation){
			int index=x.indexOf(" ");
			String[] words;
			if(index==-1){	// if two nodes are not separated by space and are separated by tab
				words = x.split("\\t+");
			}else{
				words = x.split("\\s+");
			}
			if(!nodes.containsKey(words[0])){
				nodes.put(words[0], count);
				nodes1.put(count, words[0]);
				count++;
			}
			if(!nodes.containsKey(words[1])){
				nodes.put(words[1], count);
				nodes1.put(count, words[1]);
				count++;
			}
		}
		int[][] adjacencyMatrix=new int[nodes.size()][nodes.size()];
		adjacencyMatrix=AdjacencyMatrix.generateAdjacencyMatrix(nodeRelation, nodes);
		return adjacencyMatrix;
	}
	ArrayList<ArrayList<String>> applyMCLAlgorithm(int exp, double inf,int[][] adjacencyMatrix){
		double[][] transitionMatrix=new double[adjacencyMatrix.length][adjacencyMatrix.length];
		transitionMatrix=TransitionMatrix.generateTransitionMatrix(adjacencyMatrix);
		double[][] matrixForClusters=Algo.applyMCL(exp, inf, transitionMatrix);
		
		//get rows with dulicate clusters
		HashSet<Integer> duplicateRows=new HashSet<Integer>();
		for(int i=0;i<matrixForClusters[0].length;i++){
			boolean marked=false;
			for(int j=0;j<matrixForClusters.length;j++){
				if(matrixForClusters[j][i]!=0.0) {
					if(marked==true){
						duplicateRows.add(j);
					}else marked=true;
				}
			}
		}
		
		// make clusters
		ArrayList<ArrayList<String>> nameClusters=new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<Integer>> numberClusters=new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<matrixForClusters.length;i++){
			if(!duplicateRows.contains(i)){
				ArrayList<String> name=new ArrayList<String>();
				ArrayList<Integer> number=new ArrayList<Integer>();
				for(int j=0;j<matrixForClusters[i].length;j++){
					if(matrixForClusters[i][j]!=0.0){
						name.add(nodes1.get(j));
						number.add(j);
					}
				}
				if(name.size()!=0) nameClusters.add(name);
				if(number.size()!=0) numberClusters.add(number);
			}
		}
		
		return nameClusters;
	}
	static HashMap<String, Integer> getNodeRelation(){
		return nodes;
	}
}
