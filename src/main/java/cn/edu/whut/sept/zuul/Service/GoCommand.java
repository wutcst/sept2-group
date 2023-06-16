package cn.edu.whut.sept.zuul.Service;

import cn.edu.whut.sept.zuul.Entity.Room;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GoCommand extends Command
{
    public boolean execute(Game game)
    {
        if(!hasSecondWord()) {
            System.out.println("Go where?");
        }

        String direction = getSecondWord();
        Room currentRoom = game.getCurrentRoom();

        Room nextRoom = game.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else if(nextRoom.getShortDescription().equals("in the radom room")){
            int r = new Random().nextInt(5);
            game.adddirection("radom");
            switch(r){
                case 0:
                    nextRoom=nextRoom.getExit("north");//theater
                    game.setCurrentRoom(nextRoom);
                    break;
                case 1:
                    nextRoom=nextRoom.getExit("north").getExit("west");//outside
                    game.setCurrentRoom(nextRoom);
                    break;
                case 2:
                    nextRoom=nextRoom.getExit("north").getExit("west").getExit("south");//lab
                    game.setCurrentRoom(nextRoom);
                    break;
                case 3:
                    nextRoom=nextRoom.getExit("north").getExit("west").getExit("west");//pub
                    game.setCurrentRoom(nextRoom);
                    break;
                case 4:
                    nextRoom=nextRoom.getExit("north").getExit("west").getExit("south").getExit("east");//office
                    game.setCurrentRoom(nextRoom);
                    break;
            }
        }
        else {
            game.adddirection(direction);//添加方向入栈
            game.setCurrentRoom(nextRoom);
            System.out.println(nextRoom.getLongDescription());
        }

        return false;
    }
}
