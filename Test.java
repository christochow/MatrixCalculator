import javax.swing.*;
import java.util.Scanner;
import java.io.*;
public class Test {
    private float[][] arr;
    private Test(float[][] arr){
        this.arr=arr;
    }

    /**
     * Divide rows by the first non-zero element of each row
     */
    private void Simplify() {
        for (int i = 0; i < arr.length; i++) {
            float temp = 1;
            int cur = 0;
            for (int a = 0; a < arr[i].length; a++) {
                if (cur == 0 && arr[i][a] != 0) {
                    temp = arr[i][a];
                    arr[i][a] = 1;
                    cur++;
                } else if (cur == 1) {
                    arr[i][a] = arr[i][a] / temp;
                }
            }
        }
    }

    /**
     * find the index of the leading one (if any) of an array
     * @param float a
     * @return int
     */
    private int find_one(float[] a){
        for (int b=0;b<a.length;b++){
            if (a[b]==1){
                return b;
            }
        }
        return -1;
    }

    /**
     * Row reducing the matrix
     * @param int size
     */
    private void Reduce(int size) {
        this.Simplify();
        if (size != arr.length) {
            int pivot = find_one(arr[size]);
            for (int o = 0; o < arr.length; o++) {
                if (o != size) {
                    if(pivot==-1){
                    continue;
                }
                    float m = arr[o][pivot];
                    this.Rows(size,o,m);
                }
            }
            Reduce(++size);
        }
    }


    /**
     * helper function to subtract row a from row b (in arr)
     * @param int a
     * @param int b
     * @param float ply
     */
    private void Rows(int a,int b,float ply){
        int l = arr[a].length;
        for (int u=0;u<l;u++){
                arr[b][u]=arr[b][u]-arr[a][u]*ply;
        }
    }

    public static void main(String[] args) {

        int row = Integer.parseInt(JOptionPane.showInputDialog("input number of rows of matrix"));
        int column = Integer.parseInt(JOptionPane.showInputDialog("input number of columns of matrix"));
        float[][] aa=new float[row][column];
        for(int i=0;i<row;i++){
            String input = (JOptionPane.showInputDialog("Enter numbers for row "+i+1+" in the format '1,2,3,...'"));
            String[] m = input.split(",");
            for(int b=0;b<column;b++){
                aa[i][b]=Integer.parseInt(m[b]);
            }
        }
        float[][] a = {{0,2,5},{3,6,12},{3,2,2}};
        System.out.println("original");
        for (int y=0;y<aa.length;y++){
            for (int b=0;b<aa[y].length;b++){
        System.out.print(aa[y][b]+" ");
            }
            System.out.println();
        }
        Test m = new Test(aa);
        m.Reduce(0);
        System.out.println("Row Echelon Form");
        for (int y=0;y<m.arr.length;y++){
            for (int b=0;b<m.arr[y].length;b++){
                System.out.print(m.arr[y][b]+" ");
            }
            System.out.println();
        }
    }
}
