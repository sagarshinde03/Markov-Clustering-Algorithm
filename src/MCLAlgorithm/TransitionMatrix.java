/**
 * 
 */
package MCLAlgorithm;

/**
 * @author Sagar Shinde
 *
 */
public class TransitionMatrix {
	static double[][] generateTransitionMatrix(int[][] adjMatrix){
		double[][] matrix=new double[adjMatrix.length][adjMatrix.length];
		for(int j=0;j<matrix[0].length;j++){
			int count=0;	// counts the number of ones on each column
			for(int i=0;i<matrix.length;i++){
				if(adjMatrix[i][j]==1) count++;
			}
			for(int i=0;i<matrix.length;i++){
				if(adjMatrix[i][j]==1) matrix[i][j]=(double)1/count;
			}
		}
		return matrix;
	}
}
