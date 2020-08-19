/**
 * 求排列组合中的Cnm
 *
 */
public class Cnm {

	public static void main(String[] args) {
		int N = 100;
		int[][] c = new int[N+5][N+5];
		for(int i = 0; i < N; i++) c[i][0] = 1;
	    for(int i = 1; i < N; i++) {
	        for(int j = 1; j <= i; j++) {
	            if(j == i) {
	            	c[i][j] = 1;
	            }
	            else {
	                c[i][j] = (c[i-1][j] + c[i-1][j-1]);
	            }
	        }
	    }
	    
	    System.out.println(c[5][3]);
	}

}
