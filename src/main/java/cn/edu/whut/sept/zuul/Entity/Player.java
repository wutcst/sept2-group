package cn.edu.whut.sept.zuul.Entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Player {
    String name;//玩家姓名
    int maxWeight;//最大重量
    ArrayList<Integer> PlayerWeights = new ArrayList<>();//玩家携带物品

    public Player(String name,int maxWeight){
        this.name=name;
        this.maxWeight=maxWeight;
    }

    //玩家丢弃物品
    public void drop(Room room, int i){
        int w = PlayerWeights.remove(i);
        room.addWeight(w);
    }

    //玩家拿去物品
    public boolean take(Room room,int n){
        int sum=0;
        for(int i=0;i<PlayerWeights.size();i++){
            sum+=PlayerWeights.get(i);
        }
        if(sum+room.getWeight().get(n)>maxWeight) return false;
        else{
            PlayerWeights.add(room.getWeight().get(n));
            room.removeWeight(n);
            return true;
        }
    }

    //玩家吃魔法饼干
    public void eatMagicCookie(Room room){
        maxWeight+=room.getCookie();
        room.removeCookie();
        room.setCookie(0);
    }

    //背包剩余容量
    public int freeSpace(){
        int sum=0;
        for(int i=0;i<PlayerWeights.size();i++){
            sum+=PlayerWeights.get(i);
        }
        return maxWeight-sum;
    }
}
