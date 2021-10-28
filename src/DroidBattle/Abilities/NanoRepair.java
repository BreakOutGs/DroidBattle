package DroidBattle.Abilities;

import DroidBattle.AbilityAim;
import DroidBattle.AbilityResult;
import DroidBattle.AbilityType;
import DroidBattle.DroidAbility;

public class NanoRepair extends DroidAbility {
    public NanoRepair(){
        super("NanoRepair",5,2,AbilityAim.Allie,AbilityType.Heal);
    }
    @Override
    public AbilityResult Use(){
        int HealingPower=10;
        AbilityResult result = super.Use();
        if(result.getSuccessfulStatus()){
           aimed.getHealed(HealingPower);
        }
        return result;
    }
    @Override
    public DroidAbility Clone() {
        DroidAbility abilityclone = new NanoRepair();
        return abilityclone;
    }
}
