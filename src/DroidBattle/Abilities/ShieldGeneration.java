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
        super.owner.generateShield(10);
        return null;
    }

    @Override
    public DroidAbility Clone() {
        return new ShieldGeneration();
    }
    
}
