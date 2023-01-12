import java.io.FileReader;  
import com.opencsv.CSVReader;  
public class readfile  
{    
public static void main(String[] args)  
{  
CSVReader reader = null;  
try  
{  
//αναλύοντας ένα αρχείο CSV στον constructor κλάσης readfile  
reader = new CSVReader(new FileReader("c:\\Baratheon.csv"));  
String [] nextLine;  
//μπορεί όμως να διαβάσει μόνο μία γραμμή ανα csv 
while ((nextLine = reader.readNext()) != null)  
{  
for(String token : nextLine)  
{  
System.out.print(token);  
}  
System.out.print("\n");  
}  
}  
catch (Exception e)   
{  
e.printStackTrace();  
}  
}  
}  
