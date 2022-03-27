import java.util.*;

public class Interface{

   
   public Interface(){
   
   }

   public void pressStart(){
      Scanner in = new Scanner(System.in);
      System.out.println("Press any key to begin...");
      in.nextLine();
   }
   
   public void mainMenu(){
      int bSizeX = 10;
      int boats = 5;
   
   
      Turn y = null;
      Board x = null;
   
      int i = 1;
      Scanner in = new Scanner(System.in);
      
      while(i > 0){
      
         line();
         System.out.println("1. Play Game\n2. View Statistics\n3. Game Settings\n4. Cheat Codes");
         line();
         int selection = in.nextInt();
      
         
         if (selection == 1){
         
         
            x = new Board(bSizeX, bSizeX);
            
               
            //default value = 5
            x.populateBoard(boats);
               
            //System.out.println(x.hiddenToString());
            
            while (!x.getVictory()){
               line();
               System.out.println(x);
               y = new Turn(x);
               if (y.getAttempts()>50){
                  line();
                  System.out.println("You shot more than 50 times, You Lose!");
                  line();
                  System.exit(0);
               }
               System.out.println(x.update());
            }
            y.clearGameData();
            y.setTotalWins(1);
         }
         
         
         else if (selection == 2){
            line();
            try{
               System.out.printf("Total wins: %d | Total losses: %d\nTotal misses: %d Total hits: %d Total shots: %d\n", y.getTotalWins(), y.getTotalLosses(), y.getTotalMisses(), y.getTotalHits(), y.getTotalAttempts());
            }
            catch(Exception e){
               System.out.println("Total wins: 0 | Total losses: 0\nTotal misses: 0 Total hits: 0 Total shots: 0");
            }
            line();
         }
         else if (selection == 3){
            line();
            System.out.println("1. Change board dimensions\n2. Change amount of boats");
            line();
            
            int selection1 = in.nextInt();
            
            if (selection1 == 1){
               
               System.out.println("How tall and wide? (max 26)");
               bSizeX = in.nextInt(); 
            
            }
            else if (selection1 == 2){
               System.out.println("How many boats?");
               boats = in.nextInt();
            }
         
            
            System.out.println();
            
            line();
            
            System.out.println("Changes updated!");
            
            line();
         
            
         }
         else if (selection == 4){
            x.setCheat();
            line();
         }
         
         
         if (playAgain()){
            i++;
         }
         
         i--;
      }         
   }
   
   public void line() {  
      System.out.println("===================================");
   }
   
   public boolean playAgain(){
      Scanner in = new Scanner(System.in);
      System.out.println("Press any key to continue, type 'Q' to quit.");
      return !(in.nextLine().toUpperCase().equals("Q"));
   }
}

