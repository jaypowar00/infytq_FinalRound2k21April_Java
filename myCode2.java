/*

Question: input will be mxn inmatrix of integers, and output is outmatrix.
first line of input consist of number of rows (m).
following m lines will have each n number of items.

special sparse matrix is matrix having number of zero elements greater of equal to number of non-zero elements.

If inmatrix is not sparse matrix return -1.
Else convert inmatrix to non-sparse matrix and display its elements having space between each elements in row
Condition for converting:
    1.if the sum of elements in particular row (rowsum) is less than of equal to sum of elements in particular column (colsum)
      then add a non-zero positive integer,num to rowsum such that num+rowsum is divisible by 2.
    2.if the sum of elements in particular row (rowsum) is greater than sum of elements in particular column (colsum)
      then add a non-zero positive integer,num to rowsum such that num+rowsum is divisible by 3.

Example:

sample input:
4
0 0 0
6 0 3
0 4 1
0 0 0

sample output:
1 2 1
6 3 3
0 2 0
0 0 0


*/
import java.io.*;
import java.util.*;

public class myCode2
{
    public static void main (String[] args) throws java.lang.Exception
    {
        
        Scanner s = new Scanner(System.in);
        int m = s.nextInt();
        int n, mi=0, mj=0;
        int[][]  matrix;
        String[] matrixString = new String[m];
        for(int i=0; i<m; i++) {
            matrixString[i] = s.nextLine();
            while(matrixString[i].isEmpty())
                matrixString[i] = s.nextLine();
        }
        
        n = matrixString[0].split(" ").length;
        matrix = new int[m][n];

        for(int i=0; i<m; i++)
            for(int j=0; j<n; j++){
                int t = Integer.parseInt(matrixString[i].split(" ")[j]);
                matrix[i][j] = t;
            }
        
        if(isSparseMatrix(matrix)) {
            while(isSparseMatrix(matrix)) {
                if(matrix[mi][mj] == 0){
                    int rowsum = rowSum(matrix, mi);
                    int colsum = colSum(matrix, mj);
                    int tempN=0;
                    if(rowsum <= colsum){
                        for(int i=1; i<10; i++){
                            if((i+rowsum)%2 == 0) {
                                tempN = i;
                                break;
                            }
                        }
                    }else {
                        for(int i=1; i<10; i++){
                            if((i+rowsum)%3 == 0){
                                tempN = i;
                                break;
                            }
                        }
                    }
                    matrix[mi][mj] = tempN;
                    if(mi==m-1 && mj==n-1)
                        break;
                    else{
                        if(mj==n-1){
                            mj=0;
                            mi++;
                        }else{
                            mj++;
                        }
                    }
                }else{
                    if(mi == m-1)
                        break;
                    else{
                        if(mj == n-1){
                            mj=0;
                            mi++;
                        }else
                            mj++;
                    }
                }
            }
            
            for(int i=0; i<m; i++) {
                for(int j=0; j<n; j++) {
                    System.out.print(matrix[i][j]);
                    if(j!=n-1)
                        System.out.print(" ");
                }
                System.out.println();
            }
        }else {
            System.out.println("-1");
        }
    }
    
    public static boolean isSparseMatrix(int[][] matrix) {
        int zeroes = 0, nonZeroes = 0;
        for(int[] row: matrix)
            for(int item: row) {
                if(item == 0)
                    zeroes++;
                else
                    nonZeroes++;
            }
        return zeroes >= nonZeroes;
    }
    
    public static int rowSum(int[][] matrix, int row){
        int sum=0;
        for(int i=0; i<matrix.length; i++)
            for(int j=0; j<matrix[0].length; j++)
                if(i==row)
                    sum+=matrix[i][j];
        return sum;
    }
    
    public static int colSum(int[][] matrix, int col) {
        int sum=0;
        for(int i=0; i<matrix.length; i++)
            for(int j=0; j<matrix[0].length; j++)
                if(j==col)
                    sum+=matrix[i][j];
        return sum;
    }
}