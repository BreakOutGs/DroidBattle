package DroidBattle.Droids;

import DroidBattle.AbilityResult;
import DroidBattle.Droid;
import DroidBattle.Player;
import DroidBattle.Abilities.NanoRepair;

public class Hive extends Droid {
    public Hive(){
        super( "Hive", 100, 10, 2, 15);
        super.abilites.add(new NanoRepair());
    }

    @Override
    public Droid Clone() {
        return new Hive();
    }
    @Override
    public AbilityResult BasicAttack(Droid enemy) {
        int basicAttackEnergyCost = 2;
        int i=0;
        AbilityResult result = new AbilityResult();
        for( i=0;currentEnergy>0;i++){
            super.BasicAttack(enemy);
            currentEnergy--;
        }
        result.setAbilityLog("");
        return result;
    }
    

    
}
