package javaapplication1;


public class Ship {
        

      public boolean isVertical = true;  
      public int type;   
      private int health;  


      public Ship(int type, boolean vertical) {
        this.isVertical = vertical;
        this.type = type;
        this.health = type;  
      }
     
      public boolean alive() {
        if(health > 0) {
          return true;
        }else {
          return false;
        }
      }

      /*
      shot دالة بتقلل من روح المركب لما الضربة بتيجي فيها اللي هي ال    
       */
      public void hit() {
        health--;
      }
}