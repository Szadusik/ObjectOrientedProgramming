package Objects;
import java.util.Arrays;

public class Genotype {

    private final int[] genes;
    private final int size;
    private final int numOfGenes;

    public Genotype(int numOfGenes, int size) {
        this.genes = new int[size];
        this.size = size;
        this.numOfGenes = numOfGenes;
        fillRandom();
        makeProperGen();
    }
    public Genotype(Genotype g1, Genotype g2) {
        this(g1.getNumOfGenes(), g1.getSize());

        if (g1.getSize() != g2.getSize()) throw new IllegalArgumentException("Gens have different sizes");
        if (g1.getNumOfGenes() != g2.getNumOfGenes())
            throw new IllegalArgumentException("Gens have different range of values");

        //random places to div DNA
        int firstPlaceToDiv = (int) (Math.random() * (size - 1));
        int secondPlaceToDiv = firstPlaceToDiv;
        while (secondPlaceToDiv == firstPlaceToDiv) {
            secondPlaceToDiv = (int) (Math.random() * (size - 1));
        }
        if (firstPlaceToDiv > secondPlaceToDiv) {
            int tmp = firstPlaceToDiv;
            firstPlaceToDiv = secondPlaceToDiv;
            secondPlaceToDiv = tmp;
        }

        for (int i = 0; i <= firstPlaceToDiv; i++) {
            this.genes[i] = g1.getGenes()[i];
        }
        for (int i = firstPlaceToDiv + 1; i <= secondPlaceToDiv; i++) {
            this.genes[i] = g2.getGenes()[i];
        }
        for (int i = secondPlaceToDiv; i < size; i++) {
            this.genes[i] = g1.getGenes()[i];
        }

        makeProperGen();
    }

    public int getNumOfGenes() {
        return this.numOfGenes;
    }

    public int getSize() {
        return this.size;
    }

    public int[] getGenes() {
        return genes;
    }

    private void fillRandom() {
        for (int i = 0; i < this.size; i++) {
            this.genes[i] = (int) (Math.random() * (this.numOfGenes));
        }
        Arrays.sort(this.genes);
    }

    private void makeProperGen() {
        boolean flag = true;
        while (flag) {
            flag = false;

            boolean[] isInGens = new boolean[this.numOfGenes];

            for (int i = 0; i < this.numOfGenes; i++) {
                isInGens[i] = false;
            }
            for (int i = 0; i < size; i++) {
                isInGens[this.genes[i]] = true;
            }
            for (int i = 0; i < this.numOfGenes; i++) {
                if (!isInGens[i]) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                for (int i = 0; i < this.numOfGenes; i++) {
                    if (!isInGens[i]) {
                        genes[(int) (Math.random() * (this.size))] = i;
                    }
                }
            }
        }

        Arrays.sort(genes);
    }

    public int returnRandomGen() {
        int rand = (int) (Math.random() * (this.size));
        return this.genes[rand];
    }

    public int getDominantGene(){
        int i=0,dominant=0,amount=0,current=0,maxAmount=0;
        while(i<this.size){
            if(current!=this.genes[i]){
                if(amount>maxAmount){
                    maxAmount = amount;
                    dominant = this.genes[i-1];
                }
                amount = 1;
                current = this.genes[i];
            }
            else
                amount++;
            i++;
        }
        return dominant;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(int gene: this.genes){
            result.append(" ").append(gene);
        }
        return result.toString();
    }
}
