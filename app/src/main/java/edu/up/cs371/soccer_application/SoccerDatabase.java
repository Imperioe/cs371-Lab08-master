package edu.up.cs371.soccer_application;

import android.util.Log;

import edu.up.cs371.soccer_application.soccerPlayer.SoccerPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 * 
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    Hashtable<String, SoccerPlayer> players = new Hashtable<String, SoccerPlayer>();
    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
	public boolean addPlayer(String firstName, String lastName,
			int uniformNumber, String teamName) {
        if(players.get(firstName+"##"+lastName) == null){
            players.put(firstName+"##"+lastName, new SoccerPlayer(firstName,lastName,uniformNumber,teamName));
            return true;
        }
        return false;
	}

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        return (players.remove(firstName+"##"+lastName) != null);
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
	public SoccerPlayer getPlayer(String firstName, String lastName) {
        return players.get(firstName+"##"+lastName);
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        SoccerPlayer sp = players.get(firstName+"##"+lastName);
        if(sp != null){
            sp.bumpGoals();
            return true;
        }

        return false;
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        SoccerPlayer sp = players.get(firstName+"##"+lastName);
        if(sp != null){
            sp.bumpAssists();
            return true;
        }

        return false;
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        SoccerPlayer sp = players.get(firstName+"##"+lastName);
        if(sp != null){
            sp.bumpShots();
            return true;
        }

        return false;
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        SoccerPlayer sp = players.get(firstName+"##"+lastName);
        if(sp != null){
            sp.bumpSaves();
            return true;
        }

        return false;
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        SoccerPlayer sp = players.get(firstName+"##"+lastName);
        if(sp != null){
            sp.bumpFouls();
            return true;
        }

        return false;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        SoccerPlayer sp = players.get(firstName+"##"+lastName);
        if(sp != null){
            sp.bumpYellowCards();
            return true;
        }

        return false;
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        SoccerPlayer sp = players.get(firstName+"##"+lastName);
        if(sp != null){
            sp.bumpRedCards();
            return true;
        }

        return false;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
	public int numPlayers(String teamName) {
        if(teamName == null){
            return players.size();
        }


        int count = 0;
        Set<String> keys = players.keySet();
        Iterator<String> itr = keys.iterator();
        while (itr.hasNext()){
            String str = itr.next();
            if(players.get(str).getTeamName().equals(teamName)){
                count++;
            }
        }

        return count;
	}

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
	// get the nTH player
	@Override
    public SoccerPlayer playerNum(int idx, String teamName) {
        int count = 0;
        Set<String> keys = players.keySet();
        Iterator<String> itr = keys.iterator();
        while (itr.hasNext()){
            String str = itr.next();

            if(teamName == null){
                if(idx == count){
                    return players.get(str);
                }else{
                    count++;
                }
            }else if(players.get(str).getTeamName().equals(teamName)){
                if(idx == count){
                    return players.get(str);
                }else{
                    count++;
                }
            }
        }

        return null;
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
	// read data from file
    @Override
	public boolean readData(File file) {
        try {
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()){
                String firstName = scan.nextLine();
                String lastName = scan.nextLine();
                int uniform = scan.nextInt();
                int goals = scan.nextInt();
                int assists = scan.nextInt();
                int shots = scan.nextInt();
                int fouls = scan.nextInt();
                int saves = scan.nextInt();
                int yellow = scan.nextInt();
                int red = scan.nextInt();
                scan.nextLine();
                String team = scan.nextLine();
                scan.nextLine();
                String key = firstName+"##"+lastName;
                players.put(key, new SoccerPlayer(firstName,lastName,uniform,team));
                while(goals > 0){
                    players.get(key).bumpGoals();
                    goals--;
                }
                while(assists > 0){
                    players.get(key).bumpAssists();
                    assists--;
                }
                while(shots > 0){
                    players.get(key).bumpShots();
                    shots--;
                }
                while(fouls > 0){
                    players.get(key).bumpFouls();
                    fouls--;
                }
                while(saves > 0){
                    players.get(key).bumpSaves();
                    saves--;
                }
                while(yellow > 0){
                    players.get(key).bumpYellowCards();
                    yellow--;
                }
                while(red > 0){
                    players.get(key).bumpRedCards();
                    red--;
                }

            }
            return true;
        }catch(FileNotFoundException fnfe){
            return false;
        }
	}

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
	// write data to file
    @Override
	public boolean writeData(File file) {
        try {
            PrintWriter pw = new PrintWriter(file);
            Set<String> keys = players.keySet();
            Iterator<String> itr = keys.iterator();
            while (itr.hasNext()){
                String str = itr.next();
                pw.println(logString(players.get(str).getFirstName()));
                pw.println(logString(players.get(str).getLastName()));
                pw.println(logString(players.get(str).getUniform()+""));
                pw.println(logString(players.get(str).getGoals()+""));
                pw.println(logString(players.get(str).getAssists()+""));
                pw.println(logString(players.get(str).getShots()+""));
                pw.println(logString(players.get(str).getFouls()+""));
                pw.println(logString(players.get(str).getSaves()+""));
                pw.println(logString(players.get(str).getYellowCards()+""));
                pw.println(logString(players.get(str).getRedCards()+""));
                pw.println(logString(players.get(str).getTeamName()));
                pw.println(logString("##"));
            }
            pw.close();
            return true;
        }catch(FileNotFoundException fnfe){
            return false;
        }
	}

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see edu.up.cs371.soccer_application.SoccerDB#getTeams()
     */
	// return list of teams
    @Override
	public HashSet<String> getTeams() {
        return new HashSet<String>();
	}

}
