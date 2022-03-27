import java.util.*;

public class Board{

   public static void setCheat(){
      cheat = !cheat;
      if (cheat){
         System.out.println("Cheats have been enabled!");
      }
      else{
         System.out.println("Cheats have been disabled!");
      }
   }

   static boolean cheat = false;
   
   private String water = "~";
   private String hit = "X";
   private String miss = "_";
   
   
   private int[] boatLength =  { 2 ,  3 ,  3 ,  4 ,  5 };
   
   private ArrayList<String> boatName = new ArrayList<String>(Arrays.asList("Patrol Boat","Submarine","Destroyer","Battleship","Aircraft Carrier"));
   private ArrayList<String> boats = new ArrayList<String>(Arrays.asList("P", "S", "D", "B", "A"));
   private ArrayList<Boolean> boatStatus = new ArrayList<Boolean>(Arrays.asList(true, true, true, true, true)); 
   

   
   private String[][] displayBoard;
   private String[][] hiddenBoard;
   private boolean victory = false;
   
   public Board(){
      this(10, 10);
   }

   public Board(int h, int w){
   
      displayBoard = new String[h][w];
      hiddenBoard = new String[h][w];
      
      for (int r = 0; r < displayBoard[0].length; r++){
         for (int c = 0; c < displayBoard.length; c++){
            displayBoard[r][c] = water;
            hiddenBoard[r][c] = water;
         }
      }
      
   }
   
   
   public void populateBoard(int x){
      //RNG for rotation, parameter for amount of boats
      
      for(int i = 0; i < x; i++){
         int boat = i % 6;
         boolean check = true;
         
         if (i > 5){
            boatName.add(boatName.get(boat));
            boats.add(boats.get(boat));
            boatStatus.add(boatStatus.get(boat));
         }
         
         if (Math.random() > 0.5){
            int startX = (int)(Math.random() * (hiddenBoard[0].length-1));
            int startY = (int)(Math.random() * (hiddenBoard.length-1-boatLength[boat]));
            
            for (int r = 0; r < boatLength[boat]; r++){
               if (!(hiddenBoard[startX][startY + r].equals(water))){
                  i--;
                  check = false;
                  break;
               }
            }
                        
            if (check){
               for (int r = 0; r < boatLength[boat]; r++){
                  hiddenBoard[startX][startY + r] = boats.get(boat);
               }
            }
            
         }
         else{
         
            int startX = (int)(Math.random() * (hiddenBoard[0].length-1-boatLength[boat]));
            int startY = (int)(Math.random() * (hiddenBoard.length-1));
            
            for (int c = 0; c < boatLength[boat]; c++){
               if (!(hiddenBoard[startX + c][startY].equals(water))){
                  i--;
                  check = false;
                  break;
               }
            }
            
            if (check){
               for (int c = 0; c < boatLength[boat]; c++){
                  hiddenBoard[startX + c][startY] = boats.get(boat);
               }
            }
         }
         
         
      }
      
   }


   public String toString(){
      String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
   
      
      String output = " ";
      
      for (int i = 0; i < displayBoard[0].length; i++){
         output += " ";
         if (i < 9){
            output += " "+(i+1);
         }
         else{
            output += (i+1);
         }
      }
      
      output += "\n";
      
      for (int r = 0; r < displayBoard[0].length; r++){
      
         output += alphabet[r];
         
         for (int c = 0; c < displayBoard.length; c++){
            output += "  "+displayBoard[r][c];
         }
         
         output += "\n";
      }
      
      if (cheat){
         output += "\n" + hiddenToString();
      }
      return output;
   }
   
   
   public String hiddenToString(){
      String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
   
      
      String output = " ";
      
      for (int i = 0; i < displayBoard[0].length; i++){
         output += " ";
         if (i < 9){
            output += " "+(i+1);
         }
         else{
            output += (i+1);
         }
      }
      
      output += "\n";
      
      for (int r = 0; r < hiddenBoard[0].length; r++){
      
         output += alphabet[r];
         
         for (int c = 0; c < hiddenBoard.length; c++){
            output += "  "+hiddenBoard[r][c];
         }
         
         output += "\n";
      }
      
      return output;
   }
   
   
   public String update(){
   
      
      //copying hidden board over to display board
      for (int r = 0; r < hiddenBoard.length; r++){
         for (int c = 0; c < hiddenBoard[r].length; c++){
            if (hiddenBoard[r][c].equals(hit) || hiddenBoard[r][c].equals(miss)){
               displayBoard[r][c] = hiddenBoard[r][c];
            }  
         }
      }
      
      for (int b = 0; b < boats.size(); b++){
      
         //sunk variable checks if the boat is already sunk or not
         boolean sunk = boatStatus.get(b);
         boatStatus.set(b, false);
         
         for (int r = 0; r < hiddenBoard.length; r++){
            for(int c = 0; c < hiddenBoard[r].length; c++){
            
               if (hiddenBoard[r][c].equals(boats.get(b))){
                  boatStatus.set(b, true);                  
               }
               
            }
         }
         if (!boatStatus.get(b) && sunk){
            System.out.println("The " + boatName.get(b) + " is sunk!");
         }
      }
      
      victory = true;
      
      for (boolean x : boatStatus){
         if(x){
            victory = false;
         }
      }
      
      if (victory) {
         return "You win!";
      }
      
      else{
         return "Next turn!";
      
      }
   }
   
   //getters and setters
   public String getHiddenValue(int y, int x){
      return hiddenBoard[x][y];
   }
   
   public void setHiddenValue(int y, int x, String z){
      hiddenBoard[x][y] = z;
   }
   
   public String getWater(){
      return water;
   }
   
   public String getHit(){
      return hit;
   }
   
   public String getMiss(){
      return miss;
   }
   
   public boolean getVictory(){
      return victory;
   }
   
}
