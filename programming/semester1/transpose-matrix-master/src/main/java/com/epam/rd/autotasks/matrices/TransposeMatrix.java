package com.epam.rd.autotasks.matrices;
import java.util.Arrays;

public class TransposeMatrix {
    public static int[][] transpose(int[][] matrix) {
            int rows = matrix.length;
            int columns = matrix[0].length;
            int[][] matrix1 = new int[columns][rows];
            for (int i = 0;i < rows; i++){
                for (int j = 0; j < columns; j++){
                    matrix1[j][i]=matrix[i][j];
                }
            }
            return matrix1;
        }


    public static void main(String[] args) {

        System.out.println("Test your code here!\n");

        // Get a result of your code

        int[][] matrix = {
                {1, 2},
                {7, -13} };

        int[][] result = transpose(matrix);
        System.out.println(Arrays.deepToString(result).replace("],", "]\n"));
    }

}
