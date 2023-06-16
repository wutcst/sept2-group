package cn.edu.whut.sept.zuul.Entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
@Data
public class Room
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList<Integer> weight = new ArrayList<>();
    private int cookie;

    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<>();
        int num = (int) (Math.random() * 5 + 1);
        for(int i=0;i<num;i++){
            weight.add((int)(Math.random()*100+1));
        }
        int flag=((int)(10 * Math.random())) % 2;
        if(flag==0) cookie=0;
        else cookie=(int)(Math.random()*100+1);
    }

    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        String weightdescription="";
        for(int i=0;i<weight.size();i++){
            if(i!=weight.size()-1) weightdescription+="物品i的重量为"+weight.get(i)+"\n";
            else weightdescription+="物品"+i+1+"的重量为"+weight.get(i)+"\n";
        }
        return "You are " + description + ".\n" + getExitString();
    }

    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    public void removeWeight(int n){
        weight.remove(n);
    }

    public void addWeight(int w){
        weight.add(w);
    }

    public void removeCookie(){
        cookie=0;
    }
}


