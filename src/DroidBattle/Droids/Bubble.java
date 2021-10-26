package DroidBattle.Droids;

import DroidBattle.AbilityResult;
import DroidBattle.Droid;
import DroidBattle.Abilities.ShieldGeneration;

public class Bubble extends Droid {
    
   
    
    public Bubble(){
        super("Bubble", 75, 9, 10, 40);
        maxEShieldCapacity = 15;
        currEShieldCapacity  = maxEShieldCapacity;
        super.abilites.add(new ShieldGeneration());
    }

    @Override
    public AbilityResult TakeDamage(int Damage) {
        String takeDamageLog = "";
        if(Damage<currEShieldCapacity){
            currEShieldCapacity-=Damage;
        }
        else{
            takeDamageLog+= super.TakeDamage(Damage-currEShieldCapacity).getAbilityLog();
            currEShieldCapacity=0;
        }
        return new AbilityResult(true,takeDamageLog);
    }

    @Override
    public Droid Clone() {
        return new Bubble();
    }
  
}
