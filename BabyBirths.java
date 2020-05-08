import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

/**
 * Write a description of class BabyBirths here.
 * 
 * @author (Benjamin Mayiba) 
 * @version (1/5/2020)
 */
public class BabyBirths {
    
    public void totalBirths(FileResource fr){
        
        int totalBirths = 0;
        int contM = 0;
        int contF = 0;
        int totalNames = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if(rec.get(1).equals("F")){
             contF++;
            }else{
            contM++;
            }
        
        }
        totalNames = contM + contF;
        System.out.println("total births: " + totalBirths);
        System.out.println("Number of girls names: " + contF);
         System.out.println("Number of boys names: " + contM);
          System.out.println("Total names in the file: " + totalNames);
    
    
    }
    
    public void testTotalBirths(){
       FileResource fr = new FileResource("yob1905.csv");
       totalBirths(fr);   
    } 
    
    /**
     * Write the method named getRank that has three parameters: an integer named year, a string named name, 
     * and a string named gender (F for female and M for male). 
     * This method returns the rank of the name in the file for the given gender, 
     * where rank 1 is the name with the largest number of births. 
     * If the name is not in the file, then -1 is returned. 
     */
    public int getRank(int year,String name,String gender){
        String ruta = "yob" + year + ".csv";  // Pensar en cambiar la ruta para el cuestionario
        FileResource fr = new FileResource(ruta);
        int contG = 0;
        int rank = -1;
        
        
        
        for(CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            
            if(rec.get(1).equals(gender)){
                contG++;
            }
            if(rec.get(0).equals(name) && rec.get(1).equals(gender)){
                rank = contG;
            }       
            
        }
        
        
        
      return rank;  
    
    }
    
    public void testGetRank(){ 
        
        System.out.println(getRank(1880,"Mich","M")); 
    
    }
    /**
     *   Write the method named getName that has three parameters: an integer named year, 
     *   an integer named rank, and a string named gender (F for female and M for male). 
     *   This method returns the name of the person in the file at this rank, for the given gender, 
     *   where rank 1 is the name with the largest number of births. If the rank does not exist in the file, 
     *   then “NO NAME” is returned.
     */
    public String getName(int year,int rank,String gender){
        String ruta = "yob" + year + ".csv";  // Pensar en cambiar la ruta para el cuestionario

         FileResource fr = new FileResource(ruta);
         int contG = 0;
         int result = 0;
         
        for(CSVRecord rec : fr.getCSVParser(false)){
             
             //int result = getRank(year,rec.get(0),gender);
             if(rec.get(1).equals(gender)){
                result++;
                if(result == rank){
                String name = rec.get(0) ;
                return name;
                }
                }
             
             
                }
            
           return "NO NAME";
        }
    
         public void testGetName(){
    
        System.out.println(getName(1982,450,"M"));
    }
    
    /**
     * What would your name be if you were born in a different year? 
     * Write the void method named whatIsNameInYear that has four parameters: 
     * a string name, an integer named year representing the year that name was born, an integer named newYear 
     * and a string named gender (F for female and M for male). 
     * This method determines what name would have been named if they were born in a different year, 
     * based on the same popularity. That is, you should determine the rank of name in the year they were born, 
     * and then print the name born in newYear that is at the same rank and same gender.
     */
    public void  whatIsNameInYear(String name,int year,int newYear,String gender){
        // Rank in the year when you was born
        int rank = getRank(year,name,gender);
        
        // Name in a different year with the same rank
        String newName = getName(newYear,rank,gender); 
        System.out.println(name + " born in " + year + " would be " + newName + " if he was born in " + newYear );
        
    
    }
    
    public void testWhatIsNameInYear(){
        whatIsNameInYear("Owen",1974,2014,"M");
    
    }
    
    /**
     * Write the method yearOfHighestRank that has two parameters: 
     * a string name, and a string named gender (F for female and M for male). 
     * This method selects a range of files to process and returns an integer, 
     * the year with the highest rank for the name and gender. 
     * If the name and gender are not in any of the selected files, it should return -1.
     */
    public int yearOfHighestRank(String name,String gender){ 
        DirectoryResource dr = new DirectoryResource();
        StorageResource store = new StorageResource();
        for(File f : dr.selectedFiles()){
            //FileResource fr = new FileResource(f);
           String nom = f.getName();
        //String s = fr.asString();
        store.add(nom);
        
        }
                int cont = 4000000;
                int mare = -1;
        for (String gola : store.data()) {
        //System.out.println(gola);
        FileResource fr = new FileResource(gola);
          String gol = gola.substring(3,7);
          
                int anyo = Integer.parseInt(gol);
                int result = 0;
                
                for(CSVRecord rec : fr.getCSVParser(false)){
             
                      
                      if(rec.get(0).equals(name)&&rec.get(1).equals(gender)){
                          result = getRank(anyo,rec.get(0),gender);
                      }
                      
             
                }
                //System.out.println(anyo);
                //System.out.println(result);
                if(result != 0 && result != -1){
                  if(cont>result){
                    cont = result;
                    mare = anyo;
                    
                }
                }
                
                //return mare;
      }
              //System.out.println(cont);
                //System.out.println(mare);
                
       return mare;         
    }

    public void testYearOfHighestRank(){
        
        System.out.println(yearOfHighestRank("Mich","M"));
    
    }
    
    public double getAverageRank(String name,String gender){
           DirectoryResource dr = new DirectoryResource();
           StorageResource store = new StorageResource();
        for(File f : dr.selectedFiles()){            
           String nom = f.getName();        
           store.add(nom);
        
        }
                int cont = 0;
                double mare = 0.0;
                double control = -1.0;
      for (String gola : store.data()) {
        //System.out.println(gola);
        FileResource fr = new FileResource(gola);
          String gol = gola.substring(3,7);
                int anyo = Integer.parseInt(gol);
                int result = 0;
                
                for(CSVRecord rec : fr.getCSVParser(false)){
             
                      
                      if(rec.get(0).equals(name)&&rec.get(1).equals(gender)){
                          result = getRank(anyo,rec.get(0),gender);
                      }
             
                }
                                
                    if(result != 0){
                    cont++;
                    mare += result;                    
                    }                                                                                    
      }
              
                mare = mare/cont;
                if(mare != 0){
                  control = mare;
                }
                
       return control;  
    
    }
    
   public void testGetAverageRank(){
       
       System.out.println(getAverageRank("Susan","F")); 
       
       
    }
    
    /**
     * Write the method getTotalBirthsRankedHigher that has three parameters:
     * an integer named year, a string named name, and a string named gender (F for female and M for male). 
     * This method returns an integer, the total number of births of those names with the same gender and same year 
     * who are ranked higher than name. 
     * For example, if getTotalBirthsRankedHigher accesses the "yob2012short.csv" file 
     * with name set to “Ethan”, gender set to “M”, and year set to 2012, then this method should return 15, 
     * since Jacob has 8 births and Mason has 7 births, and those are the only two ranked higher than Ethan.
     * 
     */
    public int getTotalBirthsRankedHigher(int year,String name,String gender){
        String ruta = "yob" + year + ".csv";  // Pensar en cambiar la ruta para el cuestionario
       FileResource fr = new FileResource(ruta);
		
		int sum = 0;
		for(CSVRecord record : fr.getCSVParser(false)){
			
			if(record.get(1).equals(gender)){
				
				if(record.get(0).equals(name)) return sum;
				
				sum += Integer.parseInt(record.get(2));
								
			}//end if record euqals gender condition;
			
		}//end for CSV record record;
		
		return sum;
    }
    
    public void testGetTotalBirthsRankedHigher(){
    
    System.out.println("Emily: " + getTotalBirthsRankedHigher(1990,"Emily","F")); 
    System.out.println( "Drew: " + getTotalBirthsRankedHigher(1990,"Drew","M"));
    }
          
}
