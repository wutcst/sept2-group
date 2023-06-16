package cn.edu.whut.sept.zuul.Service;

import cn.edu.whut.sept.zuul.Entity.Room;
import org.springframework.stereotype.Service;

@Service
public class LookCommand extends Command {
    @Override
    public boolean execute(Game game) {
        Room room=game.getCurrentRoom();
        String LookWeight="";
        for(int i=0;i<room.getWeight().size();i++){
            LookWeight+="物品i的重量为"+room.getWeight().get(i)+"\n";
        }
        System.out.println(LookWeight);
        return false;
    }
}
