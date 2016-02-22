/**
 * 
 */
package MCLAlgorithm;

/**
 * @author Sagar Shinde
 *
 */
import java.io.*;
import java.util.*;

public class Clusters {
	public void makeClusters(int exp, double inf, String fileName){
		Scanner x=null;
		try {
			x=new Scanner(new File("Data_For_HW3\\"+fileName+".txt"));
		} catch (FileNotFoundException e) {e.printStackTrace();}
		
		ArrayList<String> nodeRelation=new ArrayList<String>(); 
		while(x.hasNext()){
			String line=x.nextLine();
			line=line.trim();
			nodeRelation.add(line);
		}
		
		ArrayList<ArrayList<String>> clusters=new ArrayList<ArrayList<String>>();
		Clustering c=new Clustering();
		int[][] adjacencyMatrix=c.getAdjacencyMatrix(nodeRelation);
		clusters=c.applyMCLAlgorithm(exp, inf, adjacencyMatrix);
		
		//modularity calculation starts
		HashMap<String, Integer> nodes=Clustering.getNodeRelation();
		int modularity=ModularityCalculation.calculateModularity(nodes, adjacencyMatrix, clusters);
		//modularity calculation ends
		int clusterCount=0;
		for(ArrayList<String> a:clusters){
			clusterCount++;
			System.out.print("Cluster#"+clusterCount+": ");
			for(String b:a){
				System.out.print(b+" ");
			}
			System.out.println();
		}
		System.out.println("Total number of clusters:"+clusters.size());
		System.out.println("Modularity: "+modularity);
		
		int count=0;
		TreeMap<Integer, Integer> verticesClusters=new TreeMap<Integer, Integer>();	//stores the info of which vertex belongs to which cluster
		for(ArrayList<String> a:clusters){
			count++;
			for(String b:a){
				verticesClusters.put(Integer.parseInt(b), count);
			}
		}
		// generate .clu file
		//the file will be created in the project folder
		PrintWriter writer=null;
		try {
			writer = new PrintWriter(fileName+"_exp"+exp+"_inf"+inf+".clu", "UTF-8");
		}catch (FileNotFoundException e) {e.printStackTrace();}
		catch (UnsupportedEncodingException e) {e.printStackTrace();}
		writer.println("*Partition PartitionName");
		writer.println("*Vertices "+verticesClusters.size());
		for(int a:verticesClusters.keySet()){
			writer.println(verticesClusters.get(a));
		}
		writer.close();
	}
}
