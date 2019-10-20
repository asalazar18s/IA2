import java.util.ArrayList;

public class Chromosome implements Comparable{
    ArrayList<Integer> chromosome;
    Integer FitnessValue;



    public Chromosome(){}


    public Chromosome(ArrayList<Integer> chromosome) {
        this.chromosome = chromosome;
        // get fitnessfunction value and set it...
        int holder = CalculateFitnessValue();
        this.FitnessValue = holder;
    }

    public ArrayList<Integer> getChromosome() {
        return chromosome;
    }

    public void setChromosome(ArrayList<Integer> chromosome) {
        this.chromosome = chromosome;
        int holder = CalculateFitnessValue();
        this.FitnessValue = holder;
    }

    public Integer getFitnessValue() {
        return FitnessValue;
    }

    public void setFitnessValue(Integer fitnessValue) {
        FitnessValue = fitnessValue;
    }


    public int CalculateFitnessValue(){
        // https://jeroenpelgrims.com/solving-8-queens-problem-on-an-8x8-board-with-a-genetic-algorithm/
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
        // Diagonal Clashes
        int mod = 0;
        int diagonalClashes = 0;
        // loop through Columns
        for(int i = 0; i < chromosome.size(); i ++){
            // loop through each diagonal
            for(int j = 0; j < chromosome.size(); j ++){
                mod = i - j;
                // Check that we dont repeat previous clashes or that same queen clashes with itself
                if (mod < 0){
                    if((chromosome.get(j) + mod) == chromosome.get(i) || (chromosome.get(j) - mod) == chromosome.get(i)){
                        diagonalClashes += 1;
                    }
                }
            }
        }
        int totalFitness = 28 - (horizontalClashes + diagonalClashes);
        return totalFitness;
    }

    @Override
    public int compareTo(Object o) {
        int compareFitness=((Chromosome)o).getFitnessValue();
        return compareFitness-this.FitnessValue;

    }

}
