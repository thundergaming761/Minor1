import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class markov {

    String data[][]=new String[1220][];
    String train[][] = new String[(int)(0.8*1220)][1];
    String test[][] = new String[(int)(0.2*1220)][1];
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
        int in[] = new int[(int)(0.8*1220)];
        for(int i=0;i<(int)(0.8*1220);i++)
        {
            int rand = (int)(Math.random()*1220);
            in[i] = rand;
            train[k++][0] = data[rand][0];
        }
        k=0;
        for(int i=0;i<1220;i++)
        {
            int flag=0;
            for(int j=0;j<in.length;j++)
            {
                if(i==in[j])
                {
                    flag = 1;
                    break;
                }
            }
            if(k==244)
            break;
            if(flag==0)
            {
                // System.out.println(i);
                test[k++][0] = data[i][0];
            }
        }
        // for(int i=0;i<in.length;i++)
        // {
        //     System.out.println(in[i]);
        // }
    }

    void probability()
    {
        for(int i=0;i<(int)(0.8*1220);i++)
        {
            if(train[i][0].equals("H"))
                h++;
            else
                t++;
        }
        for(int i=0;i<((int)(0.8*1220)-1);i++)
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
        hh=hh/h;
        tt=tt/t;
        ht=ht/t;
        th=th/h;
    }

    void accuracy()
    {
        int numTosses = 244;  // Number of coin toss

        // Transition matrix: [Heads, Tails]
        double[][] transitionMatrix = {
            //H     T
            {hh, ht},  // H
            {th, tt}   // T
        };

        // Initial state: Start with "Heads"
        int currentState = 0;  // 0 represents "Heads," 1 represents "Tails"

        // Simulate coin tosses
        Random random = new Random();
        for (int toss = 1; toss <= numTosses; toss++) {
            // Perform the next toss based on the transition probabilities
            double randomValue = random.nextDouble();
            if (randomValue < transitionMatrix[currentState][0]) {
                // Transition to "Heads"
                System.out.println("Toss " + toss + ": Heads");
                currentState = 0;
            } else {
                // Transition to "Tails"
                System.out.println("Toss " + toss + ": Tails");
                currentState = 1;
            }
        }
    }

    public static void main(String[] args) {

        markov obj = new markov();
        obj.input();
        obj.trainTestSplit();
        obj.probability();
        obj.accuracy();
        
    }
}

