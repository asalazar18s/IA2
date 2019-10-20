

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
            toReturn.add(resObj[i]);
        }

        return toReturn;
    }



    public static void main(String agrs[]) {

/*        //call methods
        Chromosome a = new Chromosome();
        ArrayList<Integer> b = new ArrayList<Integer>();
        b.add(2);
        b.add(4);
        b.add(7);
        b.add(4);
        b.add(8);
        b.add(5);
        b.add(5);
        b.add(2);
        a.setChromosome(b);

        Chromosome c = new Chromosome();
        ArrayList<Integer> d = new ArrayList<Integer>();
        d.add(3);
        d.add(2);
        d.add(7);
        d.add(5);
        d.add(2);
        d.add(4);
        d.add(1);
        d.add(1);
        c.setChromosome(d);

        Chromosome e = new Chromosome();
        ArrayList<Integer> f = new ArrayList<Integer>();
        f.add(2);
        f.add(4);
        f.add(4);
        f.add(1);
        f.add(5);
        f.add(1);
        f.add(2);
        f.add(4);
        e.setChromosome(f);

        Chromosome g = new Chromosome();
        ArrayList<Integer> h = new ArrayList<Integer>();
        h.add(3);
        h.add(2);
        h.add(5);
        h.add(4);
        h.add(3);
        h.add(2);
        h.add(1);
        h.add(3);
        g.setChromosome(h);

        ArrayList<Chromosome> Olivia = new ArrayList<>();
        Olivia.add(a);
        Olivia.add(c);
        Olivia.add(e);
        Olivia.add(g);
        Main aaron = new Main(Olivia);

        Collections.sort(aaron.Population);

        for(Chromosome str: aaron.Population){
            System.out.println(str.getFitnessValue());
        }*/

        Main aaron = new Main();
        aaron.getInitPopulation();
        aaron.GenAlgorithm(aaron.Population);


    }

}
