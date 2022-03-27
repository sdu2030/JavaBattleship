import java.util.*;

class Turn{
   static int totalWins = 0;
   static int totalLosses = 0;
   static int totalAttempts = 0;
   static int totalMisses = 0;
   static int totalHits = 0;
   
   static int attempts = 0;
   static int misses = 0;
   static int hits = 0;
   
   public Turn (Board A){   
      String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      attempts++;
      totalAttempts++;
      
      Scanner in = new Scanner(System.in);
      
      System.out.print("(Enter 'Q' to quit) Guess: ");
      String guess = in.nextLine().toUpperCase().replace(" ", "");
      
      if(guess.equals("Q")){
         System.exit(0);
      }
      
      int y = alphabet.indexOf(guess.substring(0, 1));
      int x = Integer.valueOf(guess.substring(1))-1;
         
      if ( !A.getHiddenValue(x,y).equals(A.getWater()) ){
         A.setHiddenValue(x,y, A.getHit());
         hits++;
         totalHits++;
         System.out.println("Hit!");
      }
      else {
         A.setHiddenValue(x,y, A.getMiss());
         misses++;
         totalMisses++;
         System.out.println("Miss!");
      }
   }
   
   
   
   public int getAttempts(){
      return attempts;
   }
   public int getMisses(){
      return misses;
   }
   
   public int getHits(){
      return hits;
   }
   ////
   public int getTotalAttempts(){
      return totalAttempts;
   }
   public int getTotalMisses(){
      return totalMisses;
   }
   
   public int getTotalHits(){
      return totalHits;
   }
   public void clearGameData(){
      attempts = 0;
      misses = 0;
      hits = 0;
   }
   public void clearAllData(){
      totalAttempts = 0;
      totalMisses = 0;
      totalHits = 0;
      
      attempts = 0;
      misses = 0;
      hits = 0;
   }
   
   public void setTotalWins(int x){
      totalWins += x;
   }
   public void setTotalLosses(int x){
      totalLosses += x;
   }
   
   public int getTotalLosses(){
      return totalLosses;
   }
   public int getTotalWins(){
      return totalWins;
   }

}