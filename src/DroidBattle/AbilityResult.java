package DroidBattle;

public class AbilityResult{
    private boolean isSuccessful;
    private String abilityLog;


    public AbilityResult(){
        isSuccessful = false;
        abilityLog = "";
    }
    public AbilityResult(boolean IsSuccessful, String AbilityLog){
        this.isSuccessful = IsSuccessful;
        this.abilityLog = AbilityLog;
    }
    public String getAbilityLog() {
        return abilityLog;
    }
    public boolean getSuccessfulStatus(){
        return isSuccessful;
    }
    public void setAbilityLog(String abilityLog) {
        this.abilityLog = abilityLog;
    }
    public void setSuccessful(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }
}
