package test3;

/**
 * 给定n个矩阵{A1,A2 ,...,An}，其中Ai与Ai+1是可乘的，i=1,2,...,n-1。
 * 考察这n个矩阵的连乘积A1A2...An，给出其最优计算次序，使矩阵连乘运算数乘次数最少。
 */
public class Array {
    public static void matrixChain(int [] p, int [][] m, int [][] s) {
        int n=p.length-1;
        for (int i = 1; i <= n; i++) m[i][i] = 0; //初始化二维数组m
        for (int r = 2; r <= n; r++) {
            for (int i = 1; i <= n - r + 1; i++) {
                int j = i + r - 1;
                m[i][j] = m[i + 1][j] + p[i - 1] * p[i] * p[j]; //求出Ai到Aj的连乘
                s[i][j] = i;
                for (int k = i + 1; k < j; k++) {           //寻找是否有断点可优化Ai到Aj的连乘
                    int t = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (t < m[i][j]) {
                        m[i][j] = t;
                        s[i][j] = k;
                    }
                }
            }
        }
        System.out.println("数乘次数为"+m[1][n]);
    }

    public static void traceback (int i, int j ,int [][]s)
    { //输出计算A[i: j] 的最优计算次序
        if ( i == j) {
            System.out.print("A"+i);
            return;
        } else {
            System.out.print("(");
            traceback(i, s[i][j], s);       //前部分
            traceback(s[i][j] + 1, j, s);//后部分
            System.out.print(")");
        }
    }

    public static void main(String[] args){
        int[] p = new int[]{5,10,4,6,10,2};
//        p = new int[]{3,5,10,8,2,4};
        int[][] m = new int[p.length][p.length];
        int[][] s = new int[p.length][p.length];
        matrixChain(p,m,s);
        traceback(1,p.length-1,s);
    }
}
