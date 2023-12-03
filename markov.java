import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class markov {

    String data[][]=new String[1830][];
    String train[][] = new String[(int)(0.8*1830)][1];
    String test[][] = new String[(int)(0.2*1830)][1];
    int h=0;
    int t=0;
    double hh=0;
    double tt=0;
    double ht=0;
    double th=0;

    void input()
    {
        String line = "";
        String splitBy = ",";
        int k=0;
        try   
        {  
            //parsing a CSV file into BufferedReader class constructor  
            BufferedReader br = new BufferedReader(new FileReader("unbiased coins toss.csv"));
            while ((line = br.readLine()) != null)   //returns a Boolean value  
            {
                String[] toss = line.split(splitBy);    // use comma as separator  
                data[k]=toss;
                k++;
            }
            br.close();
        }
        catch (IOException e)   
        {  
            e.printStackTrace();  
        } 
    }

    void trainTestSplit()
    {
        int k=0;
        for(int i=0;i<(int)(0.8*data.length);i++)
        {
            train[k++][0] = data[i][0];
        }
        k=0;
        for(int i=(int)(0.8*data.length);i<data.length;i++)
        {
            test[k++][0] = data[i][0];
        }
        // for(int i=0;i<test.length;i++)
        // {
        //     System.out.println(test[i][0]);
        // }
    }

    void probability()
    {
        for(int i=0;i<(int)(0.8*data.length);i++)
        {
            if(train[i][0].equals("H"))
                h++;
            else
                t++;
        }
        for(int i=0;i<((int)(0.8*data.length)-1);i++)
        {
            if(train[i][0].equals("H") && train[i+1][0].equals("H"))
                hh++;
            else if(train[i][0].equals("T") && train[i+1][0].equals("T"))
                tt++;
            else if(train[i][0].equals("H") && train[i+1][0].equals("T"))
                ht++;
            else if(train[i][0].equals("T") && train[i+1][0].equals("H"))
                th++;
        }
        System.out.println("Probability of head occuring after head "+hh);
        System.out.println("Probability of tail occuring after tail "+tt);
        System.out.println("Probability of tail occuring after head "+ht);
        System.out.println("Probability of head occuring after tail "+th);
        hh=hh/h;
        tt=tt/t;
        ht=ht/t;
        th=th/h;
        // System.out.println("Probability of head occuring after head "+hh);
        // System.out.println("Probability of tail occuring after tail "+tt);
        // System.out.println("Probability of tail occuring after head "+ht);
        // System.out.println("Probability of head occuring after tail "+th);
    }

    void accuracy()
    {
        int numTosses = 366;  // Number of coin toss

        // Transition matrix: [Heads, Tails]
        double[][] transitionMatrix = {
            //H     T
            {0.2, 0.8},  // H
            {0.8, 0.2}   // T
        };

        // Initial state: Start with "Heads"
        int currentState = 0;  // 0 represents "Heads," 1 represents "Tails"

        // Simulate coin tosses
        int cp=0;
        Random random = new Random();
        for (int toss = 0; toss < numTosses; toss++) {
            // Perform the next toss based on the transition probabilities
            double randomValue = random.nextDouble();
            if (randomValue < transitionMatrix[currentState][0]) {
                if(test[toss][0].equals("H"))
                cp++;
                currentState = 0;
            } else {
                if(test[toss][0].equals("T"))
                cp++;
                currentState = 1;
            }
        }
        System.out.println("Accurarcy of model is "+((cp/244.0)*100)+" %");
    }

    public static void main(String[] args) {

        markov obj = new markov();
        obj.input();
        obj.trainTestSplit();
        obj.probability();
        obj.accuracy();
    }
}

