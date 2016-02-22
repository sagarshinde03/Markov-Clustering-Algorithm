/**
 * 
 */

/**
 * @author Sagar Shinde
 *
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import MCLAlgorithm.Clusters;

public class Solution {
	public static void main(String[] args) {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String fileName="";
		int exp=0;
		double inf=0.0;
		try {
			System.out.println("Please enter the value of expansion factor.");
			exp=Integer.parseInt(br.readLine());
			System.out.println("Please enter the value of inflation factor.");
			inf=Double.parseDouble(br.readLine());
			System.out.println("Please enter the name of the file you want to process.");
			fileName=br.readLine();
			if(fileName.contains(".")){
				int index=fileName.indexOf(".");
				fileName=fileName.substring(0,index);
			}
		} catch (IOException e) {e.printStackTrace();}
		Clusters c=new Clusters();
		c.makeClusters(exp, inf, fileName);
	}
}