import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;

public class Probability  
{  
public static void main(String[] args)   
{
String data[][]=new String[1220][];
String line = "";  
String splitBy = ",";
int k=0;
int h=0;
int t=0;
double hh=0;
double tt=0;
double ht=0;
double th=0;
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

String train[][] = new String[(int)(0.8*1220)][1];
String test[][] = new String[(int)(0.2*1220)][1];

for(int i=0;i<train.length;i++)
{
    train[i][0] = data[i][0];
}

k=0;
for(int i=976;i<data.length;i++)
{
    test[k++][0] = data[i][0];
}

for(int i=0;i<train.length;i++)
{
    System.out.println(train[i][0]);
}

for(int i=0;i<1220;i++)
{
if(data[i][0].equals("H"))
h++;
else
t++;
}
for(int i=0;i<1219;i++)
{
if(data[i][0].equals("H") && data[i+1][0].equals("H"))
hh++;
else if(data[i][0].equals("T") && data[i+1][0].equals("T"))
tt++;
else if(data[i][0].equals("H") && data[i+1][0].equals("T"))
ht++;
else if(data[i][0].equals("T") && data[i+1][0].equals("H"))
th++;
}
hh=hh/h;
tt=tt/t;
ht=ht/t;
th=th/h;
System.out.println(hh+" "+tt+" "+ht+" "+th);
br.close();
}
catch (IOException e)   
{  
e.printStackTrace();  
}  
}  
}