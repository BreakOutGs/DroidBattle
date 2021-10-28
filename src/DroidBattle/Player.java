package DroidBattle;
import java.util.ArrayList;

import DroidBattle.Droids.Bubble;
import DroidBattle.Droids.Hive;
import DroidBattle.Droids.Razor;

enum PlayerType{
    User,
    Computer
}

public class Player {
    
    private ArrayList<Droid> droids;
    private PlayerType playerType;
    private String name;
    
    private int currency;
    public Player(){
        currency=0;
        droids = new ArrayList<Droid>();
    }
    public Player(String Name,int startMoney, ArrayList<Droid> Droids, PlayerType pType){
        currency = startMoney;
        if(Droids!=null)this.droids = Droids;
        else this.droids = new ArrayList<Droid>();
        playerType = pType;
        this.name = Name;
    }

    public int getCurrency() {
        return currency;
    }
    public void addCurrency(int CurrencyAmount){
        currency+=CurrencyAmount;
    }
    public void SpentCurrency(int CurrencyAmount){
        currency-=CurrencyAmount;
    }
    public void SetCurrencyAmount(int CurrencyAmount){
        currency = CurrencyAmount;
    }
    public void BuyDroid(Droid droid){
        if(droid.getCurrencycost()<=currency){
            droids.add(droid.Clone());
            currency-=droid.getCurrencycost();
        }
    }
    public ArrayList<Droid> get_Droids(){
        return droids;
    }
    public void set_Droids(ArrayList<Droid> Droids){
        this.droids = Droids;
    }
    public PlayerType getPlayerType() {
        return playerType;
    }
    public String getName() {
        return name;
    }
    public void Move(){
        ArrayList<Droid> deaddroids = new ArrayList<Droid>(); 
        for (Droid droid : droids) {
            if(!droid.isDead())
                droid.NextMove();
            else
                deaddroids.add(droid);
        }
        droids.removeAll(deaddroids);
    }
    public String generateSaveString(String separator){
        String saveString="";
        saveString+=name+separator;
        saveString+=String.valueOf(currency)+separator;
        saveString+=String.valueOf(droids.size())+separator;
            for (Droid droid : droids) {
                saveString+=droid.getType()+separator;
            }
        saveString+=playerType.toString();
        return saveString;
        
    }
    static public Player getPlayerFromString(String loadString){
        /*Потрібно зчитати значення в такому порядку:
            Назву та кількість валюти
            Кількість дронів та їх імена
            тип гравця
        */
        String separator = String.valueOf((char)202);
        int lastSeparatorIndex = 0;
        int  nextSeparatorIndex = loadString.indexOf(separator,lastSeparatorIndex);
        //зчитування імені гравця
        String l_name = loadString.substring(lastSeparatorIndex, nextSeparatorIndex);
        int l_currency = 0;
        int l_ndroids = 0;
        String l_droidtype="";
        ArrayList<Droid> l_droids = new ArrayList<Droid>();
        PlayerType l_ptype;

        lastSeparatorIndex = nextSeparatorIndex+1;
        nextSeparatorIndex = loadString.indexOf(separator,lastSeparatorIndex);
        //зчитування валюти і конвертація з строки в число
        l_currency = Integer.valueOf(loadString.substring(lastSeparatorIndex, nextSeparatorIndex));
        
        lastSeparatorIndex = nextSeparatorIndex+1;
        nextSeparatorIndex = loadString.indexOf(separator,lastSeparatorIndex);
        //зчитування кількості дронів у гравця
        l_ndroids = Integer.valueOf(loadString.substring(lastSeparatorIndex, nextSeparatorIndex));
        
        for(int i=0;i<l_ndroids;i++){
            lastSeparatorIndex = nextSeparatorIndex+1;
            nextSeparatorIndex = loadString.indexOf(separator,lastSeparatorIndex);
            //зчитування назва дрону
            l_droidtype = loadString.substring(lastSeparatorIndex, nextSeparatorIndex);
            switch(l_droidtype){
                case "Hive": l_droids.add(new Hive()); break;
                case "Razor": l_droids.add(new Razor()); break;
                case "Bubble": l_droids.add(new Bubble()); break;
                default: break;
            }
            
        }
        //Тут до кінця строки залишається лиш тип гравця
        lastSeparatorIndex = nextSeparatorIndex+1;
        var testString = loadString.substring(lastSeparatorIndex, loadString.length());
        l_ptype = PlayerType.valueOf(testString);
        return new Player(l_name, l_currency, l_droids, l_ptype);
    }
    public boolean IsLost(){
        for (Droid droid : droids) {
            if(!droid.isDead()) return false;
        }
        return true;
    }
}
