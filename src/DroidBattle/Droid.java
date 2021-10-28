package DroidBattle;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;


public abstract class Droid {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_DAMAGECOLOR = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_DNAMECOLOR = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static int Droids_Count=0;

    private String type;
    protected String name;
    protected int maxhp;
    protected int currenthp;
    protected int maxEnergy;
    protected int currentEnergy; 
    protected int damage;
    protected int currencycost;

    protected int maxEShieldCapacity;
    protected int currEShieldCapacity;

    private boolean isDead;
    
    protected ArrayList<DroidAbility> abilities;

    public Droid(String Type, int MaxHealth, int Energy, int Damage, int CurrencyCost) {
        Droids_Count++;
        this.type = Type;
        this.maxhp = MaxHealth;
        this.currenthp = maxhp;
        this.damage = Damage;
        this.isDead = false;
        this.name = "Droid"+String.valueOf(Droids_Count);
        this.currencycost = CurrencyCost;
        this.maxEnergy = Energy;
        this.currentEnergy = maxEnergy;
        this.abilities = new ArrayList<DroidAbility>();
    }

    public String PrintInfo() {
        String print_str = "";
        print_str += "\n Droid " + ANSI_DNAMECOLOR + this.type + ANSI_WHITE + " stat:";
        print_str += "\n Max Health: " + ANSI_GREEN + this.maxhp + ANSI_WHITE;
        print_str += "\n Current Health: " + ANSI_GREEN + this.currenthp + ANSI_WHITE;
        print_str += "\n Droid damage: " + ANSI_DAMAGECOLOR + this.damage + ANSI_WHITE;
        return print_str;
    }
    public String PrintShortInfo(){
        return name+" - "+type+" HP:"+ColorSystem.ANSI_RED+String.valueOf(currenthp)+"/"+String.valueOf(maxhp)+ColorSystem.ANSI_RESET+
        " \t Energy: "+ColorSystem.ANSI_BLUE+String.valueOf(currentEnergy)+"/"+String.valueOf(maxEnergy)+ColorSystem.ANSI_RESET;
    }

    public AbilityResult TakeDamage(int Damage) {
        AbilityResult toReturn = new AbilityResult(true, "");
        String functionLog = "";
        currenthp -= Damage;
        if (currenthp <= 0)
            isDead = true;
        functionLog += "Droid " + ANSI_DNAMECOLOR + this.type + ANSI_WHITE + "takes" + ANSI_DAMAGECOLOR
                + String.valueOf(Damage) + ANSI_WHITE + " damage";
        toReturn.setAbilityLog(functionLog);
        return toReturn;
    }
    public AbilityResult BasicAttack(Droid enemy){
        AbilityResult result = new AbilityResult();
        currentEnergy--;
        enemy.TakeDamage(damage);
        return result;
    }
    public AbilityResult ModifiedAttack(Droid enemy, double modificator){
        AbilityResult result = new AbilityResult();
        enemy.TakeDamage((int)(damage*modificator));
        return result;
    }
    public int getRepairCost(){
        int repPrice = 0;
        float pricePerHp = 0.05f; 
        repPrice = Math.round((maxhp-currenthp)*pricePerHp);
        return repPrice;
    }
    public void RepairFullHp(){
        currenthp = maxhp;
    }
    public String getName() {
        return name;
    } 
    public int getCurrencycost() {
        return currencycost;
    }
    public void setCurrencycost(int currencycost) {
        this.currencycost = currencycost;
    }
    public abstract Droid Clone();

    public void getHealed(int Hp){
        if( (currenthp+Hp)>maxhp){
            currenthp=maxhp;
        }
        else{
            currenthp+=Hp;
        }
    }
    public String getType() {
        return type;
    }
    public int getCurrentEnergy() {
        return currentEnergy;
    }
    public void setCurrentEnergy(int Energy){
        if(Energy<0) return;
        if(Energy>maxEnergy){
            currentEnergy = maxEnergy;
        }
        else currentEnergy = Energy;
    }
    public int getCurrenthp() {
        return currenthp;
    }
    public ArrayList<DroidAbility> getAbilites() {
        return abilities;
    }
    public void setAbilites(ArrayList<DroidAbility> abilites) {
        this.abilities = abilites;
    }
    public void NextMove(){
        currentEnergy = maxEnergy;
    }
    public void generateShield(int ShieldCapacity){
        currEShieldCapacity += ShieldCapacity;
    }
    public boolean isDead(){
        if(currenthp<=0) return true;
        else return false;
    }

}
