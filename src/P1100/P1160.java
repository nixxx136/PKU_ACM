/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package P1100;

import java.util.*;

/**
 * Problem Link: http://poj.org/problem?id=1160
 * Sample Input:
 * 10 5
 * 1 2 3 6 7 9 11 22 44 50
 * Sample Output:
 * 9
 * @author nixxx136
 */
public class P1160 { // change class name to Main when submit
    
    private static int[][] cost; // the cost of build just one post office between i and j villages
    private static int[][] opt; // optimal cost of first i post offices for first j villages
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int V = in.nextInt(); // number of villages
        int P = in.nextInt(); // number of post offices
        int[] X = new int[V];
        for (int i=0; i<V; i++) {
            X[i] = in.nextInt();
        }
        cost = new int[V][V];
        for (int i=0; i<V; i++) {
            for (int j=i; j<V; j++) {
                int mid = (i+j) / 2;
                for (int k=i; k<=j; k++) {
                    cost[i][j]+=Math.abs(X[mid]-X[k]);
                }
            }
        }
        
        for (int i=0; i<V; i++) {
            for (int j=0; j<V; j++) {
                System.out.print(cost[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
        
        opt = new int[P][V];
        for (int i=0; i<V; i++) opt[0][i] = cost[0][i];
        
        for (int i=1; i<P; i++) {
            for (int j=0; j<V; j++){
                opt[i][j] = 3000000;
            }
        }
        
        for (int i=1; i<P; i++) {
            for (int j=i; j<V; j++) {
                for (int k=0; k+j<V; k++) {
                    if (opt[i][j+k]>opt[i-1][j-1]+cost[j][j+k]) {
                        opt[i][j+k]=opt[i-1][j-1]+cost[j][j+k];
                    }
                }
            }
        }
        for (int i=0; i<P; i++) {
            for (int j=0; j<V; j++){
                System.out.print(opt[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println(opt[P-1][V-1]);
    }
}
