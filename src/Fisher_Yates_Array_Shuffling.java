

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author dfellig
 */
public class Fisher_Yates_Array_Shuffling {

    public static Integer[] fisherYatesShuffling(Integer[] arr, int n) {
        Integer[] a = new Integer[n];
        int[] ind = new int[n];
        for (int i = 0; i < n; i++) {
            ind[i] = 0;
        }
        int index;
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            do {
                index = rand.nextInt(n);
            } while (ind[index] != 0);

            ind[index] = 1;
            a[i] = arr[index];
        }

        return a;
    }

    public static ArrayList<Integer> getValuesAsArray(Integer[] arr, int length){
        int n = length;
        Integer[] a = arr;
        int[] res = new int[n];
        Integer[] resObj;
        for (int i = 0; i < n; i++) {

            a[i] = i + 1;
        }

        resObj = fisherYatesShuffling(a, n);

        ArrayList<Integer> toReturn = new ArrayList<>();

        for(int i = 0; i < a.length; i ++){
            toReturn.add(a[i]);
        }

        return toReturn;
    }



    public static void main(String agrs[]) {

        //call methods
        Chromosome a = new Chromosome();
        ArrayList<Integer> b = new ArrayList<Integer>();
        b.add(2);
        b.add(4);
        b.add(4);
        b.add(1);
        b.add(5);
        b.add(1);
        b.add(2);
        b.add(4);

        a.setChromosome(b);

        System.out.println(a.CalculateFitnessValue());

    }

}
