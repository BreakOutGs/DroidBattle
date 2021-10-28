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
        AbilityResult result = super.Use();
        if(result.getSuccessfulStatus()){
            for(Droid enemy : super.enemies){
           owner.ModifiedAttack(enemy, 1.5);
        }

        }
        
       
        return result;
    }

    @Override
    public DroidAbility Clone() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
