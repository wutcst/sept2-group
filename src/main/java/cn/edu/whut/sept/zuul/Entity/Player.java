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

    public void drop(Room room, int i){
        int w = PlayerWeights.remove(i);
        room.addWeight(w);
    }

    /**
     * 玩家拿去房间中的物品
     * @param room 房间
     * @param n 物品序号
     * @return 返回拿去结果
     */
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

    /**
     * 玩家吃掉房间内的魔法饼干
     * @param room
     */
    public void eatMagicCookie(Room room){
        maxWeight+=room.getCookie();
        room.removeCookie();
        room.setCookie(0);
    }

    /**
     * 求出玩家背包剩余量
     * @return
     */
    public int freeSpace(){
        int sum=0;
        for(int i=0;i<PlayerWeights.size();i++){
            sum+=PlayerWeights.get(i);
        }
        return maxWeight-sum;
    }
}
