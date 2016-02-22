/**
 * 
 */
package MCLAlgorithm;

/**
 * @author Sagar Shinde
 *
 */
import java.util.ArrayList;
import java.util.HashMap;

public class AdjacencyMatrix {
	static int[][] generateAdjacencyMatrix(ArrayList<String> nodeRelation, HashMap<String, Integer> nodes){
		int[][] adjMatrix=new int[nodes.size()][nodes.size()];
		for(String n: nodeRelation){
			int index=n.indexOf(" ");
			String[] words;
			if(index==-1){
				words = n.split("\\t+");
			}else{
				words = n.split("\\s+");
			}
			int first=nodes.get(words[0]);
			int second=nodes.get(words[1]);
			adjMatrix[first][second]=1;
			adjMatrix[second][first]=1;
		}
		for(int i=0;i<adjMatrix.length;i++){
			adjMatrix[i][i]=1;
		}
		return adjMatrix;
	}
}
