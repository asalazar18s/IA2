import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;

public class Genetic_Algorithm {
    ArrayList<Chromosome> Population = new ArrayList<>();
    Integer NumOf200kGens = 0;

    public Genetic_Algorithm(){}

    public Genetic_Algorithm(ArrayList<Chromosome> population) {
        Population = population;
    }


    public void getInitPopulation(){
        // Generates initial pòpulation of 30 candidates / individuals.
        Population.clear();
        for (int i = 0; i < 30; i ++){
            Integer[] newIndividual = new Integer[8];
            ArrayList<Integer> list = Fisher_Yates_Array_Shuffling.getValuesAsArray(newIndividual, 8);
            Chromosome new_Chromosome = new Chromosome(list);
            //System.out.println(new_Chromosome.getFitnessValue() + " : " + new_Chromosome.getChromosome());
            Population.add(new_Chromosome);
        }
        Collections.sort(Population);

    }

    public String GenAlgorithm(){
        ArrayList<Chromosome> population = Population;
        // TODO: check fit value of first element in list if its already sorted because if there is a 28 its over
        if(population.get(0).getFitnessValue() == 28){
            System.out.println("Perfect Configuration Gen: 0" + " Num of 200k generations: " + NumOf200kGens);
            return population.get(0).toString();
        }

        for(int j = 0; j < 200000; j ++) {
            ArrayList<Chromosome> newGen = new ArrayList<>();
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

                if (child.getFitnessValue() == 28) {
                    System.out.println("Perfect Configuration " + "Gen: " + j + " Num of 200k generations: " + NumOf200kGens);
                    return child.toString();
                }
            }
            // set newgen as next population to look into

            population = newGen;
            Population = newGen;

            Collections.sort(population);
            Collections.sort(Population);
            if(j % 10000 == 0){
                System.out.println("Generation: " + j + "; Num of 200k generations: " + NumOf200kGens);
                System.out.println("Best Specimen:");
                System.out.println(population.get(0).toString() + "\n");
            }

        }
        // Comment the next four lines if you want to just get the best configuration after 200k iterations
        System.out.println("\n\n\nNew Initial Population\n\n\n");
        NumOf200kGens += 1;
        getInitPopulation();
        return GenAlgorithm();

        // Uncomment if you want the best configuration after 200k iterations
        //System.out.println("Best Configuration");
        //return population.get(0).toString();
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
        // TODO: Validate if this is working properly
        int n = child.chromosome.size();
        // gen random value in range of n
        int valToChange = ThreadLocalRandom.current().nextInt(0, n);

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
