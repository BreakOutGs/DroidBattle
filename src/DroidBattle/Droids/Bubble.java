package DroidBattle.Droids;

import DroidBattle.AbilityResult;
import DroidBattle.ColorSystem;
import DroidBattle.Droid;
import DroidBattle.Abilities.ShieldGeneration;

public class Bubble extends Droid {
    
   
    
    public Bubble(){
        super("Bubble", 120, 9, 10, 40);
        maxEShieldCapacity = 15;
        currEShieldCapacity  = maxEShieldCapacity;
        super.abilities.add(new ShieldGeneration());
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
    public String PrintShortInfo() {
        if(currEShieldCapacity>0)
            return super.PrintShortInfo()+"\t Shield: %s %d %s".formatted(ColorSystem.ANSI_GREEN,this.currEShieldCapacity, ColorSystem.ANSI_RESET);
        else
            return super.PrintShortInfo();
    }

    @Override
    public Droid Clone() {
        return new Bubble();
    }
  
}
