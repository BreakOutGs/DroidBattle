package DroidBattle;


import DroidBattle.Droids.Bubble;
import DroidBattle.Droids.Hive;
import DroidBattle.Droids.Razor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


enum ConsoleGameStates {
    MainMenu, Worhshop, Battle
}

public class ConsoleGame {

    // const block
    private final String GameName = "DroidX";
    private final int ReleaseNumber = 0;
    private final int BetaNumber = 0;
    private final int AlphaNumber = 1;
    static String dataFilePath = "D:/Levels.dat";
    static Scanner keyboard = new Scanner(System.in);
    // end const block

    private GameLevel lvl;
    private ArrayList<GameLevel> levels;
    private Player currentPlayer;
    private int currentPlayerId = 0;
    ConsoleGameStates currentGameState;
    /*
     * TODO: 1.
     */

    ConsoleGame() {
        levels = new ArrayList<GameLevel>();
        LoadLevels();
        ShowMainMenu();
    }

    private String cutString(String strToCut, int symbolsNumber){
        String str = "";
        if(strToCut.length()>symbolsNumber){
            str = strToCut.substring(0, symbolsNumber-2)+"..";
        }
        else {
            str = strToCut;
            for(int i = 0; i<symbolsNumber-strToCut.length();i++){
                str+=" ";
            }
        }
        return str;
    }
    
    private void ClearConsole() {
        System.out.println("\033[H\033[2J");
    }

    private String getGameVersionS() {
        return String.valueOf(ReleaseNumber) + "." + String.valueOf(BetaNumber) + "." + String.valueOf(AlphaNumber);
    }

    private void ShowMainMenu() {
        String s = "";
        int n = 1;
        int inputcode = 0;

        ClearConsole();
        s += "\t\t\t" + GameName + " - " + getGameVersionS() + "\n\n";
        s += "Main Menu";
        s += "\n " + String.valueOf(n) + ". New game";
        n++;
        if (lvl == null) {
            s += "\n " + ColorSystem.ANSI_BRIGHTBLACK + String.valueOf(n) + ". Continue" + ColorSystem.ANSI_RESET;
            n++;
        } else {
            s += "\n " + String.valueOf(n) + ". Continue";
            n++;
        }
        s += "\n " + String.valueOf(n) + ". Exit";
        n++;
        System.out.println(s);
        inputcode = keyboard.nextInt();
        switch (inputcode) {
        case 1:
            ShowNewGameMenu();
            break;
        case 2:
            if (lvl == null) {
                ShowMainMenu();
            } else {
                ;
            }
        case 3:
            return;
        default:
            ShowMainMenu();
        }

    }

    private void ShowNewGameMenu() {
        String s = "";
        int n = 0;
        int inputcode = 0;

        ClearConsole();
        s += "\t\t\t" + GameName + " - " + getGameVersionS() + "\n\n";
        s += "New game menu";
        s += "\n " + String.valueOf(n) + ". Back \n";
        n++;

        for (GameLevel gameLevel : levels) {
            s += "\n " + String.valueOf(n) + ". "+gameLevel.getName();n++;
        }
        n++;
        System.out.println(s);
        inputcode = keyboard.nextInt();
        switch (inputcode) {
        case 0:
            ShowMainMenu();
            break;
        default:
            if(inputcode>=0&&inputcode<=levels.size()){
                try{
                    StartLvl((GameLevel)levels.get(inputcode-1).clone());
                }catch(CloneNotSupportedException e){
                    System.out.println("An error occurred.");
                    e.getStackTrace();
                }
            }
            ShowNewGameMenu();
            break;
        }
    }

    private void StartLvl1() {
        ArrayList<Player> local_Players = new ArrayList<Player>(List.of(
                new Player("Player1", 30, null, PlayerType.User),
                new Player("Computer1", 0, new ArrayList<Droid>(List.of((Droid) new Hive())), PlayerType.Computer)));
        ArrayList<Droid> droidPool = new ArrayList<Droid>(List.of(new Hive(), new Razor(), new Bubble()));
        GameLevel local_lLevel = new GameLevel("Lvl1", local_Players, droidPool);
        this.lvl = local_lLevel;
        currentPlayer = lvl.getPlayers().get(currentPlayerId);
        if (currentPlayer.getPlayerType() == PlayerType.User) {
            ShowWorkshop();
            currentGameState = ConsoleGameStates.Worhshop;
        }

    }
    
    private void StartLvl(GameLevel level){
        this.lvl = level;
        currentPlayerId=0;
        currentPlayer = lvl.getPlayers().get(currentPlayerId);
        if (currentPlayer.getPlayerType() == PlayerType.User) {
            ShowWorkshop();
            currentGameState = ConsoleGameStates.Worhshop;
        }
    }

    public void ShowWorkshop() {
        String s = "";
        int n = 0;
        int inputcode = 0;
        ClearConsole();
        s += "\t\t\t" + GameName + " - " + getGameVersionS() + "\n\n";
        s += "Droid workshop menu:";
        s += "\n " + String.valueOf(n) + ". Main menu \n";
        n++;
        s += "\n " + String.valueOf(n) + ". Buy droids";
        n++;
        s += "\n " + String.valueOf(n) + ". Repair robots";
        n++;
        s += "\n " + String.valueOf(n) + ". Go to battle";
        System.out.println(s);
        inputcode = keyboard.nextInt();
        switch (inputcode) {
        case 0:
            ShowMainMenu();
            break;
        case 1:
            ShowBuyDroidsMenu();
            break;
        case 2:
            ShowRepairDroidMenu();
            break;
        case 3:
            ShowBattleMenu();
            break;
        default:
            ShowWorkshop();
            break;
        }

    }

    public void ShowBuyDroidsMenu() {
        String s = "";
        int n = 0;
        int inputcode = 0;
        ClearConsole();
        s += "\t\t\t" + GameName + " - " + getGameVersionS() + "\n\n";
        s += "\n Your gold: " + String.valueOf(currentPlayer.getCurrency());
        s += " \n Buy droid:";
        s += "\n " + String.valueOf(n) + ". Back\n";
        for (Droid ldroid : lvl.getDroidsInWorkshop()) {
            if (currentPlayer.getCurrency() < ldroid.getCurrencycost())
                s += ColorSystem.ANSI_BRIGHTBLACK;
            s += "\n " + String.valueOf(n + 1) + ". " + ldroid.getType();
            n++;
            s += ColorSystem.ANSI_RESET;

        }
        System.out.println(s);
        inputcode = keyboard.nextInt();
        if (inputcode == 0) {
            ShowWorkshop();
        } else if (inputcode - 1 < lvl.getDroidsInWorkshop().size()) {
            ShowDroidBuyConfirmations(lvl.getDroidsInWorkshop().get(inputcode - 1));
            return;
        } else {
            ShowBuyDroidsMenu();
            return;
        }

    }

    private void ShowDroidBuyConfirmations(Droid droid) {
        String s = "";
        int n = 1;
        int inputcode = 0;
        ClearConsole();
        s += "\t\t\t" + GameName + " - " + getGameVersionS() + "\n\n";
        s += "\n Your gold: " + String.valueOf(currentPlayer.getCurrency());
        s += "\n\n Do you want buy this droid:";
        s += droid.PrintInfo();
        s += "\n\n Price: " + String.valueOf(droid.getCurrencycost());
        s += "\n  " + String.valueOf(n) + ". Yes";
        n++;
        s += "\n  " + String.valueOf(n) + ". No";

        System.out.println(s);
        inputcode = keyboard.nextInt();
        switch (inputcode) {
        case 1:
            currentPlayer.BuyDroid(droid);
            ShowBuyDroidsMenu();
            break;
        case 2:
            ShowBuyDroidsMenu();
            break;
        default:
            ShowDroidBuyConfirmations(droid);
            break;
        }
    }

    private void ShowRepairDroidMenu() {
        String s = "";
        int n = 0;
        int inputcode = 0;
        ClearConsole();
        s += "\t\t\t" + GameName + " - " + getGameVersionS() + "\n\n";
        s += " \n Repair Menu";
        s += "\n " + String.valueOf(n) + ". Back \n";
        n++;
        s += "Select droid:";
        for (Droid ldroid : currentPlayer.get_Droids()) {
            s += "\n " + String.valueOf(n) + ". " + ldroid.PrintShortInfo();
            n++;
        }
        System.out.println(s);
        inputcode = keyboard.nextInt();
        switch (inputcode) {
        case 0:
            ShowWorkshop();
            break;
        default:
            if (inputcode >= 0 && inputcode < currentPlayer.get_Droids().size()) {
                ShowRepairConfirmation(currentPlayer.get_Droids().get(inputcode));
            } else
                ShowRepairDroidMenu();
            break;
        }

    }

    private void ShowRepairConfirmation(Droid droid) {
        String s = "";
        int n = 0;
        int inputcode = 0;
        int repairPrice = 0;
        ClearConsole();
        s += "\t\t\t" + GameName + " - " + getGameVersionS() + "\n\n";
        s += " \n You want to repair next droid:";
        s += droid.PrintInfo();
        s += "Do you want to confirm?:";
        s += "\n" + String.valueOf(n) + ". Yes";
        n++;
        s += "\n" + String.valueOf(n) + ". No";
        n++;
        System.out.println(s);
        inputcode = keyboard.nextInt();
        switch (inputcode) {
        case 0:
            repairPrice = droid.getRepairCost();
            if (currentPlayer.getCurrency() >= repairPrice)
                currentPlayer.SetCurrencyAmount(currentPlayer.getCurrency() - repairPrice);
        case 1:
            ShowBattleMenu();
            break;
        default:
            ShowRepairConfirmation(droid);
        }
    }

    public void ShowBattleMenu() {
        String s = "";
        int n = 0;
        int inputcode = 0;
        ClearConsole();
        s += "\t\t\t" + GameName + " - " + getGameVersionS() + "\n\n";
        s += "\n Your team:";
        for (Droid ldroid : currentPlayer.get_Droids()) {
            s += "\n" + ldroid.PrintShortInfo();
        }
        s += "\n\n Enemy teams:";
        for (Player lplayer : lvl.getPlayers()) {
            if (lplayer.getName() == currentPlayer.getName())
                continue;
            else {
                s += "\n Team of player " + lplayer.getName() + ": ";
                for (Droid ldDroid : lplayer.get_Droids()) {
                    s += "\n" + ldDroid.PrintShortInfo();
                }
            }

        }
        s += "\n\n " + String.valueOf(n) + ". Main menu";
        n++;
        s += "\n " + String.valueOf(n) + ". Droid managment";
        n++;
        s += "\n " + String.valueOf(n) + ". End move";
        n++;
        System.out.println(s);

        inputcode = keyboard.nextInt();
        switch (inputcode) {
        case 0:
            ShowMainMenu();
            break;
        case 1:
            ShowSelectDroidMenu();
            break;
        case 2:
            NextMove();
        default:
            ShowBattleMenu();
            break;

        }

    }

    public void ShowSelectDroidMenu() {
        String s = "";
        int n = 0;
        int inputcode = 0;
        String tempColor = ColorSystem.ANSI_RESET;
        ClearConsole();
        s += "\t\t\t" + GameName + " - " + getGameVersionS() + "\n\n";
        s += "\n " + String.valueOf(n) + ". Back \n";
        s += "\n Select Droid:";
        n++;
        for (Droid ldroid : currentPlayer.get_Droids()) {
            if(ldroid.getCurrentEnergy()<=0) tempColor = ColorSystem.ANSI_BRIGHTBLACK;
            else tempColor = ColorSystem.ANSI_RESET;
            s += "\n %s %d. %s %s ".formatted(tempColor,n,ldroid.getName(),ldroid.getType());
            n++;
        }
        s += "\n";
        System.out.println(s);
        inputcode = keyboard.nextInt();
        switch (inputcode) {
        case 0:
            ShowBattleMenu();
            break;
        default:
            if ((inputcode - 1) < currentPlayer.get_Droids().size()) {
                ShowDroidManagment(currentPlayer.get_Droids().get(inputcode - 1));
                break;
            }
            ShowSelectDroidMenu();
            break;
        }
    }
    
    public Player ShowSelectPlayer(ArrayList<Player> players, String menuTitle){
        Player toReturn = null;
        String s = "";
        int n = 0;
        int inputcode = 0;
        ClearConsole();
        s += "\t\t\t" + GameName + " - " + getGameVersionS() + "\n\n";
        s += "\n " + String.valueOf(n) + ". Back \n";
        s += "\n %s".formatted(menuTitle);
        n++;
        for (Player lPlayer : players) {
            if(lPlayer.getName()==currentPlayer.getName()) {n++;continue;}
            s += "\n %d %s %s ".formatted(n,". ", lPlayer.getName());
            n++;
        }
        s += "\n";
        System.out.println(s);
        inputcode = keyboard.nextInt();
        if(inputcode<0 || inputcode>players.size() || currentPlayerId+1==inputcode) 
            return ShowSelectPlayer(players, menuTitle);
        if(inputcode == 0) 
            return toReturn; 
        else 
            return players.get(inputcode-1);

    }
   
    public Droid ShowSelectDroid(ArrayList<Droid> droids, String menuTitle){
        Droid toReturn = null;
        String s = "";
        int n = 0;
        int inputcode = 0;
        ClearConsole();
        s += "\t\t\t" + GameName + " - " + getGameVersionS() + "\n\n";
        s += "\n " + String.valueOf(n) + ". Back \n";
        s += "\n %s".formatted(menuTitle);
        n++;
        for (Droid lDroid : droids) {
            s += "\n %d %s %s ".formatted(n,". ", lDroid.PrintShortInfo());
            n++;
        }
        s += "\n";
        System.out.println(s);
        inputcode = keyboard.nextInt();
        if(inputcode<0 || inputcode>droids.size()) return ShowSelectDroid(droids, menuTitle);
        if(inputcode == 0) return toReturn; else return droids.get(inputcode-1);

    }
    
   
    public DroidAbility ShowSelectAbility(ArrayList<DroidAbility> abilities, String menuTitle){
        DroidAbility toReturn = null;
        String s = "";
        int n = 0;
        int inputcode = 0;
        ClearConsole();
        s += "\t\t\t" + GameName + " - " + getGameVersionS() + "\n\n";
        s += "\n " + String.valueOf(n) + ". Back \n";
        s += "\n %s".formatted(menuTitle);
        n++;
        for (DroidAbility lPlayer : abilities) {
            s += "\n %d %s %s ".formatted(n,". ", lPlayer.getName());
            n++;
        }
        s += "\n";
        System.out.println(s);
        inputcode = keyboard.nextInt();
        if(inputcode<0 || inputcode>abilities.size()) ShowSelectAbility(abilities, menuTitle);
        if(inputcode == 0) return toReturn; else return abilities.get(inputcode-1);

    }
    
    public void ShowDroidManagment(Droid droid) {
        String s = "";
        int n = 0;
        int inputcode = 0;
        DroidAbility tempAbility;
        ClearConsole();
        s += "\t\t\t" + GameName + " - " + getGameVersionS() + "\n\n";
        s += " \n Droid Managment:";
        s += "\n " + String.valueOf(n) + ". Back \n";
        n++;
        s += "\n " + String.valueOf(n) + ". Attack";
        n++;
        s += "\n " + String.valueOf(n) + ". Select and use ability";
        n++;
        System.out.println(s);
        inputcode = keyboard.nextInt();
        switch (inputcode) {
        case 0:
            ShowBattleMenu();
            break;
        case 1:
            ShowSelectEnemy(droid);
            break;
        case 2:
            tempAbility = ShowSelectAbility(droid.getAbilites(), "Select ability:");
            tempAbility.setOwner(droid);
                switch(tempAbility.aimType){
                    case Enemies:
                      tempAbility.setEnemies(ShowSelectPlayer(lvl.getPlayers(), "Select enemy team:").get_Droids());
                    break;
                    case Enemy:
                        tempAbility.setAimed(ShowSelectDroid(
                            ShowSelectPlayer(lvl.getPlayers(), "Select player to choose his droid:").get_Droids(), 
                            "Select droid to use ability %s on".formatted(tempAbility.getName())
                            ));
                    break;
                    case Self:
                       
                        break;
                    case Allie:
                        tempAbility.setAimed(ShowSelectDroid(currentPlayer.get_Droids(), "Select droid to use %s on".formatted(tempAbility.getName())));
                        break;
                    case Team:
                        tempAbility.setAimed(ShowSelectDroid(currentPlayer.get_Droids(), "Select droid to use %s on".formatted(tempAbility.getName())));
                    break;
                    default: break;
                }
                tempAbility.Use();
                ShowBattleMenu();
                break;
        default:
            ShowDroidManagment(droid);
            break;
        }

    }

    public void ShowSelectEnemy(Droid droid) {
        String s = "";
        int n = 0;
        int inputcode = 0;
        ArrayList<Droid> ldroids = new ArrayList<Droid>();
        for (Player lPlayer : lvl.getPlayers()) {
            if (lPlayer.getName() != currentPlayer.getName())
                ldroids.addAll(lPlayer.get_Droids());
        }
        ClearConsole();
        s += "\t\t\t" + GameName + " - " + getGameVersionS() + "\n\n";
        s += " \n Select aim to Attack:";
        s += "\n " + String.valueOf(n) + ". Back \n";
        n++;

        for (Droid lDroid : ldroids) {
            s += "\n " + String.valueOf(n) + ". " + lDroid.getName() + "-" + lDroid.getType() + "\t Owner: "
                    + getDroidOwner(lDroid).getName();
            n++;
        }
        System.out.println(s);
        inputcode = keyboard.nextInt();
        switch (inputcode) {
        case 0:
            ShowDroidManagment(droid);
            break;
        default:
            if (inputcode - 1 < ldroids.size()) {
                droid.BasicAttack(ldroids.get(inputcode - 1));
                ShowBattleMenu();
            }
            ShowSelectEnemy(droid);

        }

    }

    private Player getDroidOwner(Droid droid) {
        for (Player lPlayer : lvl.getPlayers()) {
            for (Droid lDroid : lPlayer.get_Droids()) {
                if (lDroid.getName() == droid.getName())
                    return lPlayer;
            }
        }
        return null;
    }

    private void NextMove() {

        int winnerId = GetWinnerId();

        if (currentPlayerId + 1 < lvl.getPlayers().size())
            currentPlayerId++;
        else
            currentPlayerId = 0;
        currentPlayer = lvl.getPlayers().get(currentPlayerId);
        currentPlayer.Move();
        switch (currentPlayer.getPlayerType()) {
        case User:
        if(winnerId>=0) ShowGameOverMenu(currentPlayer);
        else{
             ShowBattleMenu();
            break;
        }
           
        case Computer:
            StartComputerMove();
            break;
        default:
            ShowBattleMenu();
            break;

        }

    }
    private int GetWinnerId(){
        int id = 0;
        int alivecounter = 0;
        for (Player lplayer : lvl.getPlayers()) {
            if(!lplayer.IsLost()){
                alivecounter++;    
                id = lvl.getPlayers().indexOf(lplayer);
            }  
        }
        switch(alivecounter){
            case 0: return -1;
            case 1: return id;
            default: return -1;
        }
    }
    
    private void ShowGameOverMenu(Player player){
        Player toReturn = null;
        String s = "";
        int n = 0;
        int inputcode = -1;
        String menuTitle;
        if(player.IsLost()){
            menuTitle = "Game over: you LOST. Press 0 to show main menu.";
        }
        else menuTitle = "Game over: you WON. Press 0 to show main menu.";
        s += "\t\t\t" + GameName + " - " + getGameVersionS() + "\n\n";
        s += "\n %s".formatted(menuTitle);n++;
        while(inputcode!=0){
            ClearConsole();
            System.out.println(s);
            inputcode = keyboard.nextInt();
        }
        ShowMainMenu();
        lvl = null;
        
    }
    private void StartComputerMove() {

        for (Droid computerdroid : currentPlayer.get_Droids()) {
            while (computerdroid.getCurrentEnergy() > 0) {
                computerdroid.BasicAttack(ComputerSelectEnemy());
            }
        }
        NextMove();
    }

    private Droid ComputerSelectEnemy() {
        Droid withMinHp = null;
        for (Player lplayer : lvl.getPlayers())
            if (lplayer.getName() != currentPlayer.getName())
                for (Droid ldroid : lplayer.get_Droids()) {
                    if (withMinHp == null)
                        withMinHp = ldroid;
                    else if (withMinHp.getCurrenthp() > ldroid.getCurrenthp()) {
                        withMinHp = ldroid;
                    }
                }
        return withMinHp;
    }

    public void LoadLevels() {
        String strToParse="";
        try{
            File file = new File("D:/Levels.dat");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
               strToParse += myReader.nextLine();
            }
            myReader.close();
        }catch(IOException e){
        System.out.println("An error occurred.");
        e.printStackTrace();
         }
        parseLoadString(strToParse);
}
   
    private void parseLoadString(String loadString){
         /*Потрібно зчитати значення в такому порядку:
            Кількість рівнів
            Зчитати строки для рівнів і передати їх для аналогічного методу рівнів
        */
        String separator = String.valueOf((char)200);
        
        String separator3 = String.valueOf((char)202);
        int lastSeparatorIndex = 0;
        int nextSeparatorIndex = loadString.indexOf(separator,lastSeparatorIndex);
        int l_nlvls = Integer.valueOf(loadString.substring(lastSeparatorIndex, nextSeparatorIndex));

        for(int i=0;i<l_nlvls;i++){
            lastSeparatorIndex = nextSeparatorIndex+1;
            nextSeparatorIndex = loadString.indexOf(separator,lastSeparatorIndex);
            levels.add(GameLevel.loadFromString(loadString.substring(lastSeparatorIndex, nextSeparatorIndex)));
        }

            
    }

    public void SaveLevels() {
        ArrayList<GameLevel> gamelevels = new ArrayList<GameLevel>(List.of(
                new GameLevel("Lvl 1 - Hives",
                        new ArrayList<Player>(
                                List.of(new Player("Player 1", 30, new ArrayList<Droid>(), PlayerType.User),
                                        new Player("Computer 1", 0, new ArrayList<Droid>(List.of(new Hive())),
                                                PlayerType.Computer))),
                        new ArrayList<Droid>(List.of((Droid) new Hive()))),
                new GameLevel("Lvl 2 - Razors", new ArrayList<Player>(List.of(
                        new Player("Player 1", 30, new ArrayList<Droid>(), PlayerType.User),
                        new Player("Computer 1", 0, new ArrayList<Droid>(List.of(new Razor(), new Hive(), new Hive())),
                                PlayerType.Computer))),
                        new ArrayList<Droid>(List.of((Droid) new Hive(), (Droid) new Razor()))),
                new GameLevel("Lvl 3 - Bubbles", new ArrayList<Player>(List.of(
                        new Player("Player 1", 100, new ArrayList<Droid>(), PlayerType.User),
                        new Player("Computer 1", 0,
                                new ArrayList<Droid>(List.of(new Bubble(), new Hive(), new Razor(), new Razor())),
                                PlayerType.Computer))),
                        new ArrayList<Droid>(List.of((Droid) new Hive(), (Droid) new Razor(), (Droid) new Bubble())))));
        String stringToWrite = "";
        String separator1 = Character.toString((char) 200);
        String separator2 = Character.toString((char) 201);
        String separator3 = Character.toString((char) 202);
        stringToWrite += gamelevels.size() + separator1;
        for (GameLevel gameLevel : gamelevels) {
            stringToWrite += gameLevel.generateSaveString(separator2,separator3) + separator1;
        }
        try {
            String filepath = "D:/Levels.dat";
            FileWriter filewriter = new FileWriter(dataFilePath);
            filewriter.write(stringToWrite);

            filewriter.close();

        } catch (IOException e) {
            System.out.println("An Error Occured");
            e.printStackTrace();
        }
    }
}
