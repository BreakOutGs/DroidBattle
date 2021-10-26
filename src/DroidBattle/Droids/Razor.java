package DroidBattle.Droids;

import DroidBattle.AbilityResult;
import DroidBattle.Droid;
import DroidBattle.Abilities.CuttingRazors;

public class Razor extends Droid {

    public Razor(){
        super("Razor", 125, 1, 20, 20);
        super.abilites.add(new CuttingRazors());
    }

    @Override
    public Droid Clone() {
        return new Razor();
    }
    @Override
    public AbilityResult BasicAttack(Droid enemy) {
        int totaldamage  = 0;
        totaldamage += currentEnergy*damage;
        currentEnergy = 0;
        enemy.TakeDamage(totaldamage);
        return new AbilityResult();
        
    }
    
}
