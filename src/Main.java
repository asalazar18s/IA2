import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;

public class Main {
    ArrayList<Chromosome> Population = new ArrayList<>();

    public Main (){}

    public Main(ArrayList<Chromosome> population) {
        Population = population;
    }


    public void getInitPopulation(){
        // Generates initial pòpulation of 30 candidates / individuals.
        for (int i = 0; i < 30; i ++){
            Integer[] newIndividual = new Integer[8];
            ArrayList<Integer> list = Fisher_Yates_Array_Shuffling.getValuesAsArray(newIndividual, 8);
            Chromosome new_Chromosome = new Chromosome(list);
            System.out.println(new_Chromosome.getFitnessValue() + " : " + new_Chromosome.getChromosome());
            Population.add(new_Chromosome);
        }
        System.out.println("\n");
        Collections.sort(Population);
        for (int i = 0; i < Population.size(); i ++){
            Chromosome aaron = Population.get(i);
            System.out.println(aaron.toString());
        }
    }

    public String GenAlgorithm(ArrayList<Chromosome> population){
        population = Population;

        for(int j = 0; j < 200000; j ++) {
            ArrayList<Chromosome>newGen = new ArrayList<>();
            for (int i = 0; i < population.size(); i++) {
                // get random value the size of list of population to get both parents
                Chromosome parent1 = rouletteWheelSelection();
                Chromosome parent2 = rouletteWheelSelection();

                //Reproduction
                Chromosome child = Reproduce(parent1, parent2);

                // Check if mutation is needed
                double MutationProb = Math.random();
                if (MutationProb <= 0.1) {
                    child = Mutate(child);
                }
                newGen.add(child);

                if (child.getFitnessValue() == 28){
                    return child.toString();
                }
            }
            population.addAll(newGen);
            Collections.sort(population);

        }
        int max = 0;
        int indexOfMax = 0;
        for (int counter = 0; counter < population.size(); counter++)
        {
            if (population.get(counter).getFitnessValue() > max)
            {
                max = population.get(counter).getFitnessValue();
                indexOfMax = counter;
            }
        }

        return population.get(indexOfMax).toString();
    }


    public Chromosome Reproduce(Chromosome x, Chromosome y){
        Chromosome child = new Chromosome();
        int n = x.chromosome.size();
        // gen random value in range of n
        int randomNum = ThreadLocalRandom.current().nextInt(0, n + 1);

        // get first part of the child to be appended to final child
        ArrayList<Integer> childPart1 = new ArrayList<Integer>(x.chromosome.subList(0, randomNum));

        // get second part of the child to be appended to final child
        ArrayList<Integer> childPart2 = new ArrayList<Integer>(y.chromosome.subList(randomNum, n));
        ArrayList<Integer> ChildPart2toappend = new ArrayList<Integer>(childPart2);


        for(int i = 0; i < childPart1.size(); i ++){
            child.chromosome.add(childPart1.get(i));
        }
        for(int i = 0; i < childPart2.size(); i ++){
            child.chromosome.add((Integer) childPart2.get(i));
        }

        return child;
    }

    public Chromosome Mutate(Chromosome child){
        // La probabilidad de mutación de un gen en la cromosoma debe ser de 0.01
        // La posición del gen en el cromosoma que va a mutar debe ser seleccionado
        // aleatoriamente.
        int n = child.chromosome.size();
        // gen random value in range of n
        int valToChange = ThreadLocalRandom.current().nextInt(0, n + 1);

        int newVal = ThreadLocalRandom.current().nextInt(1, 8);

        child.chromosome.set(valToChange, newVal);

        return child;
    }


    public Chromosome rouletteWheelSelection(){
        Random m_rand = new Random();
        int sum = 0;
        int sum_to_check = 0;

        for (int i = 0 ; i < Population.size(); i++) {
            sum += Population.get(i).getFitnessValue();
        }

        int rValue = ((new Random()).ints(1, 0, sum)).findFirst().getAsInt();

        for (int i = 0 ; i < Population.size(); i++) {
            sum_to_check += Population.get(i).getFitnessValue();
            if (rValue <= sum_to_check) {
                return Population.get(i);
            }
        }
        return null;

    }
}
