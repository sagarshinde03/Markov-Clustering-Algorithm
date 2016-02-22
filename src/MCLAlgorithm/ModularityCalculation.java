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

public class ModularityCalculation {
	static int calculateModularity(HashMap<String, Integer> nodes, int[][] adjacencyMatrix, ArrayList<ArrayList<String>> clusters){
		int intraSum=0;
		for(ArrayList<String> cluster:clusters){
			for(int i=0;i<cluster.size();i++){
				for(int j=i+1;j<cluster.size();j++){
					String first=cluster.get(i);
					String second=cluster.get(j);
					intraSum+=adjacencyMatrix[nodes.get(first)][nodes.get(second)];
				}
			}
		}
		int interSum=0;
		for(int i=0;i<clusters.size();i++){
			for(int j=i+1;j<clusters.size();j++){
				ArrayList<String> c1=clusters.get(i);
				ArrayList<String> c2=clusters.get(j);
				interSum+=calculateInterSum(c1, c2, nodes, adjacencyMatrix);
			}
		}
		return intraSum-interSum;
	}
	static int calculateInterSum(ArrayList<String> c1, ArrayList<String> c2, HashMap<String, Integer> nodes, int[][] adjacencyMatrix){
		int interSum=0;
		for(int i=0;i<c1.size();i++){
			for(int j=0;j<c2.size();j++){
				String first=c1.get(i);
				String second=c2.get(j);
				interSum+=adjacencyMatrix[nodes.get(first)][nodes.get(second)];
			}
		}
		return interSum;
	}
}
