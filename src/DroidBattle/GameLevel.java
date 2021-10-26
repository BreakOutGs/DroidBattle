package DroidBattle;


import java.util.ArrayList;
import java.util.List;

import DroidBattle.Droids.Bubble;
import DroidBattle.Droids.Hive;
import DroidBattle.Droids.Razor;



public class GameLevel implements Cloneable {
   private ArrayList<Player> players;
   private ArrayList<Droid> droidsInWorkshop;
   private String name;
    /*
    TODO:
    Початковий вибір дроїда
    Хід гравця
    Хід комп'ютера
    */

    public GameLevel(){
        players = new ArrayList<Player>();
        ArrayList<Droid> startDroids = (ArrayList<Droid>) List.of((Droid)new Hive());

        System.out.println("Почато нову гру. Виберіть початкового робота:");
        for(int i=0;i<startDroids.size();i++){
            System.out.println("\t"+String.valueOf(i+1)+". "+startDroids.get(i).getName());
        }
    
        
    }
    public GameLevel(String Name, ArrayList<Player> Players, ArrayList<Droid> DroidInWorkshop){
        this.players = Players;
        this.droidsInWorkshop = DroidInWorkshop;
        this.name = Name;
    }
        public ArrayList<Droid> getDroidsInWorkshop() {
            return droidsInWorkshop;
        }
        public ArrayList<Player> getPlayers() {
            return players;
        }
    
    static public GameLevel loadFromString(String loadString){
        String separator = String.valueOf((char)201);
        int lastSeparatorIndex = 0;
        int nextSeparatorIndex = loadString.indexOf(separator,lastSeparatorIndex+1);
        String l_lvlname = loadString.substring(lastSeparatorIndex, nextSeparatorIndex);
        int l_nplayers;
        int l_ndroids;
        ArrayList<Player> l_players = new ArrayList<Player>();
        ArrayList<Droid> l_droids = new ArrayList<Droid>();
        String l_droidtype = "";

        lastSeparatorIndex = nextSeparatorIndex+1;
        nextSeparatorIndex = loadString.indexOf(separator,lastSeparatorIndex);
        l_nplayers = Integer.valueOf(loadString.substring(lastSeparatorIndex, nextSeparatorIndex));
            for(int i=0;i<l_nplayers;i++){
                lastSeparatorIndex = nextSeparatorIndex+1;
                nextSeparatorIndex = loadString.indexOf(separator,lastSeparatorIndex);
                l_players.add(Player.getPlayerFromString(loadString.substring(lastSeparatorIndex,nextSeparatorIndex)));
            }
            lastSeparatorIndex = nextSeparatorIndex+1;
            nextSeparatorIndex = loadString.indexOf(separator,lastSeparatorIndex);
        l_ndroids = Integer.valueOf(loadString.substring(lastSeparatorIndex, nextSeparatorIndex));
            for(int i = 0;i<l_ndroids;i++){
                lastSeparatorIndex = nextSeparatorIndex+1;
                nextSeparatorIndex = loadString.indexOf(separator,lastSeparatorIndex);
                     l_droidtype = loadString.substring(lastSeparatorIndex,nextSeparatorIndex);
                     switch(l_droidtype){
                        case "Hive": l_droids.add(new Hive()); break;
                        case "Razor": l_droids.add(new Razor()); break;
                        case "Bubble": l_droids.add(new Bubble()); break;
                        default: break;
                    }
                }
        return new GameLevel(l_lvlname, l_players, l_droids);
    }
    public String generateSaveString(String separator1, String separator2){
        //ASCII[201] - символ для розділення значень
        /* Потрібно записати:
            Гравців
            Роботів в майстерні
         */
        String saveString = "";
        saveString+= name + separator1;
        saveString+= Integer.toString(players.size()) + separator1;
        for (Player player : players) {
            saveString+= player.generateSaveString(separator2) + separator1;     
        }
        saveString+= Integer.toString(droidsInWorkshop.size()) + separator1;
        for (Droid droid : droidsInWorkshop) {
            saveString+= droid.getType()+separator1;
        }
        
        return saveString;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

}
