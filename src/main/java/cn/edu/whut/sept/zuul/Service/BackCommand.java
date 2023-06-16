package cn.edu.whut.sept.zuul.Service;

import cn.edu.whut.sept.zuul.Entity.Room;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class BackCommand extends Command {
    @Override
    public boolean execute(Game game) {

        Room currentRoom = game.getCurrentRoom();
        LinkedList<String> directions = game.getDirections();
        if (directions.isEmpty()) {
            System.out.println("There is no door!");
        }
        else {
            String direction="";
            switch(directions.getFirst()){
                case "east":
                    direction="west";
                    break;
                case "west":
                    direction="east";
                    break;
                case "south":
                    direction="north";
                    break;
                case "north":
                    direction="south";
                    break;
                case "radom":
                    direction="radom";
            }
            Room nextRoom = game.getCurrentRoom().getExit(direction);
            game.removedirection();
            game.setCurrentRoom(nextRoom);
            System.out.println(nextRoom.getLongDescription());
        }
        return false;
    }
}
