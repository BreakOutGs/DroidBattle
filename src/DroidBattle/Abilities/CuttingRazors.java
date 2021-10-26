package DroidBattle.Abilities;

import DroidBattle.AbilityAim;
import DroidBattle.AbilityResult;
import DroidBattle.AbilityType;
import DroidBattle.Droid;
import DroidBattle.DroidAbility;

public class CuttingRazors extends DroidAbility{

    private int damage;

    public CuttingRazors(){
        super("CuttingRazors", 1, 5, AbilityAim.Enemies, AbilityType.Attack);
        damage = 20;
    }

    @Override
    public AbilityResult Use() {
        
        for(Droid enemie : super.enemies){
            enemie.TakeDamage(damage);
        }
        return null;
    }

    @Override
    public DroidAbility Clone() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
