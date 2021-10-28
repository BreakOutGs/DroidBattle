package DroidBattle;

import java.util.ArrayList;

public abstract class DroidAbility {
    private String name;
    private int cooldown;
    private int energyCost;
    private int currentCooldown;
    protected Droid owner;
    protected Droid aimed;
    protected AbilityAim aimType;
    protected AbilityType type;
    protected ArrayList<Droid> enemies;

    public DroidAbility(String Name, int EnergyCost, int Cooldown, AbilityAim AimType, AbilityType AType){
        this.name = Name;
        this.energyCost = EnergyCost;
        this.cooldown = Cooldown;
        this.currentCooldown = cooldown;
        this.aimType = AimType;
        this.type = AType;
    }
    public void setAimed(Droid aimed) {
        this.aimed = aimed;
    }
    public void setOwner(Droid owner) {
        this.owner = owner;
    }
    public Droid getAimed() {
        return aimed;
    }
    public Droid getOwner() {
        return owner;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public AbilityResult Activate(){
        var abilityresult = new AbilityResult();
        
        return abilityresult;
    }
    
    public  AbilityResult Use(){
        AbilityResult result = new AbilityResult();
        if(owner.getCurrentEnergy()>=getEnergyCost()){
             owner.setCurrentEnergy(owner.getCurrentEnergy()-getEnergyCost());
             result.setSuccessful(true);
        }
        else result.setSuccessful(false);
        return result;
    }
    
    public abstract DroidAbility Clone();
    
    public AbilityAim getAimType() {
        return aimType;
    }
    
    public int getCurrentCooldown() {
        return currentCooldown;
    }
    
    public AbilityType getType() {
        return type;
    }
    
    public int getEnergyCost() {
        return energyCost;
    }
    
    public int getCooldown() {
        return cooldown;
    }
    public ArrayList<Droid> getEnemies() {
        return enemies;
    }
    public void setEnemies(ArrayList<Droid> enemies) {
        this.enemies = enemies;
    }
}

