import java.util.ArrayList;

public class Chromosome implements Comparable{
    ArrayList<Integer> chromosome;
    Integer FitnessValue;



    public Chromosome(){}


    public Chromosome(ArrayList<Integer> chromosome) {
        this.chromosome = chromosome;
        // get fitnessfunction value and set it...
        this.FitnessValue = CalculateFitnessValue();
    }

    public ArrayList<Integer> getChromosome() {
        return chromosome;
    }

    public void setChromosome(ArrayList<Integer> chromosome) {
        this.chromosome = chromosome;
    }

    public Integer getFitnessValue() {
        return FitnessValue;
    }

    public void setFitnessValue(Integer fitnessValue) {
        FitnessValue = fitnessValue;
    }


    public int CalculateFitnessValue(){
        // Horizontal clashes
        int horizontalClashes = 0;
        for (int i = 0; i < chromosome.size(); i ++){
            int valToCheck = chromosome.get(i);
            int totalOfVal = 0;
            for (int j = 0; j < chromosome.size(); j ++){
                if(chromosome.get(j) == valToCheck){
                    totalOfVal += 1;
                }
            }
            horizontalClashes = horizontalClashes + (totalOfVal -1);
        }

        horizontalClashes = horizontalClashes/2;


        return horizontalClashes;
    }

    @Override
    public int compareTo(Object o) {
        int compareFitness=((Chromosome)o).getFitnessValue();
        return compareFitness-this.FitnessValue;

    }

}
