package model;


public class Hostile extends Alien {
   
    public Hostile() {
        super();
        name = "Man Ray"; 
        friendly = false;
        greeting = "My name is Man Ray... Is this perhaps your wallet.....with your ID?";

    }

        public void manRay(){
                System.out.println("This Alien seems Hostile! Do you want to Fight (1) or Flee? (2)");
        }

        public int attack() {
        double chance = Math.random();
            if(chance < 0.13) {
                System.out.println(name + " misses the attack!");
                return 0;
            }

        int damage = 3;
        if (chance >= 0.13 && chance < 0.25) {
            damage *= 2;
            System.out.println(name + " lands a critical hit! DOUBLE DAMAGE");
        }
        System.out.println(name + " attacks and deals " + damage + " damage.");
        return damage;
    }
}
