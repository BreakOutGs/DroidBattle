package DroidBattle.Abilities;

import DroidBattle.AbilityAim;
import DroidBattle.AbilityResult;
import DroidBattle.AbilityType;
import DroidBattle.DroidAbility;

public class ShieldGeneration extends DroidAbility {

    public ShieldGeneration(){
        super("ShieldGeneration", 5, 3, AbilityAim.Self, AbilityType.Heal);
    }
    @Override
    public AbilityResult Use() {
        int generatedShieldCapacity = 10;
        AbilityResult result = super.Use();
        if(result.getSuccessfulStatus()){
            super.owner.generateShield(generatedShieldCapacity);
         }
        return null;
    }

    @Override
    public DroidAbility Clone() {
        return new ShieldGeneration();
    }
    
}
