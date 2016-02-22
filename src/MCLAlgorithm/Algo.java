/**
 * 
 */
package MCLAlgorithm;

/**
 * @author Sagar Shinde
 *
 */
public class Algo {
	static double[][] applyMCL(int exp, double inf, double[][] transitionMatrix){
		double[][] oldMatrix=new double[transitionMatrix.length][transitionMatrix.length];
		double[][] newMatrix=new double[transitionMatrix.length][transitionMatrix.length];
		newMatrix=fillMatrix(newMatrix,transitionMatrix);
		int iterationsCount=0;
		while(!checkEquality(oldMatrix,newMatrix)){
			iterationsCount++;
			oldMatrix=fillMatrix(oldMatrix, newMatrix);
			newMatrix=expand(exp, newMatrix);
			newMatrix=inflate(inf, newMatrix);
			newMatrix=prune(newMatrix);
		}
		System.out.println("Total number of iterations: "+iterationsCount);
		for(int i=0;i<newMatrix.length;i++){
			for(int j=0;j<newMatrix[i].length;j++){
				if(newMatrix[i][j]!=0.0){
					newMatrix[i][j]=(int)(newMatrix[i][j]*1000000);
					newMatrix[i][j]=(double)(newMatrix[i][j]/1000000);
				}
			}
		}
		return newMatrix;
	}
	static boolean checkEquality(double[][] oldMatrix,double[][] newMatrix){
		for(int i=0;i<oldMatrix.length;i++){
			for(int j=0;j<oldMatrix[i].length;j++){
				if(Math.abs(oldMatrix[i][j]-newMatrix[i][j])>0.000001) return false;
			}
		}
		return true;
	}
	static double[][] fillMatrix(double[][] matrix,double[][] transitionMatrix){
		for(int i=0;i<transitionMatrix.length;i++){
			for(int j=0;j<transitionMatrix[0].length;j++){
				matrix[i][j]=transitionMatrix[i][j];
			}
		}
		return matrix;
	}
	static double[][] expand(int exp, double[][] matrix){
		double[][] mat=new double[matrix.length][matrix.length];
		mat=fillMatrix(mat, matrix);
		for(int i=1;i<exp;i++){
			mat=matrixMultiply(mat, matrix);
		}
		return mat;
	}
		
	static double[][] matrixMultiply(double[][] matrix1, double[][] matrix2){
		double[][] mat=new double[matrix1.length][matrix1.length];
		for(int i=0;i<matrix1.length;i++){
			for(int j=0;j<matrix2[i].length;j++){
				double[] first=matrix1[i];
				double[] second=getColumn(matrix2,j);
				mat[i][j]=multiply(first,second);
			}
		}
		return mat;
	}
	static double[] getColumn(double[][] matrix, int column){
		double[] col=new double[matrix.length];
		for(int i=0;i<matrix.length;i++){
			col[i]=matrix[i][column];
		}
		return col;
	}
	static double multiply(double[] first, double[] second){
		double d=0.0;
		for(int i=0;i<first.length;i++){
			d+=first[i]*second[i];
		}
		return d;
	}
	static double[][] inflate(double inf, double[][] matrix){
		matrix=inflateIndividualElements(inf, matrix);
		matrix=columnWiseNormalize(matrix);
		return matrix;
	}
	static double[][] inflateIndividualElements(double inf, double[][] matrix){
		double[][] mat=new double[matrix.length][matrix.length];
		for(int i=0;i<matrix.length;i++){
			for(int j=0;j<matrix[i].length;j++){
				mat[i][j]=Math.pow(matrix[i][j], inf);
			}
		}
		return mat;
	}
	static double[][] columnWiseNormalize(double[][] matrix){
		for(int i=0;i<matrix[0].length;i++){
			double sum=0.0;
			for(int j=0;j<matrix.length;j++){
				sum+=matrix[j][i];
			}
			for(int j=0;j<matrix.length;j++){
				matrix[j][i]=matrix[j][i]/sum;
			}
		}
		return matrix;
	}
	static double[][] prune(double[][] matrix){
		for(int i=0;i<matrix.length;i++){
			for(int j=0;j<matrix[i].length;j++){
				if(matrix[i][j]<=0.0000001) matrix[i][j]=0.0;
			}
		}
		return matrix;
	}
}
