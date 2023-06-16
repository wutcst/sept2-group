package cn.edu.whut.sept.zuul;
import java.util.LinkedList;

import cn.edu.whut.sept.zuul.Service.BackCommand;
import cn.edu.whut.sept.zuul.Service.Command;
import cn.edu.whut.sept.zuul.Service.GoCommand;
import cn.edu.whut.sept.zuul.Service.Game;
import cn.edu.whut.sept.zuul.Entity.Room;
import org.junit.Test;
import static org.junit.Assert.*;

public class BackCommandTest {
    @Test
    public void testExecuteWithValidDirection() {
        Game game = new Game();
        LinkedList<String> directions = new LinkedList<String>();
        Command goCommand = new GoCommand();
        Command backCommand = new BackCommand();
        goCommand.setSecondWord("west");//前进方向为west
        goCommand.execute(game);//进入到pub
        boolean result = backCommand.execute(game);//返回到outside
        assertTrue(!result);
        assertEquals("outside the main entrance of the university", game.getCurrentRoom().getShortDescription());
    }

    @Test
    public void testExecuteWithInvalidDirection() {
        Game game = new Game();
        Room startingRoom = new Room("starting room");
        LinkedList<String> directions = new LinkedList<String>();
        game.setCurrentRoom(startingRoom);
        game.setDirections(directions);

        Command backCommand = new BackCommand();
        boolean result = backCommand.execute(game);
        assertFalse(result);
        assertEquals(startingRoom, game.getCurrentRoom());
    }

    @Test
    public void testExecuteWithRandomDirection() {
        Game game = new Game();
        Room theater = new Room("in a lecture theater");
        Command goCommand = new GoCommand();
        Command backCommand = new BackCommand();
        goCommand.setSecondWord("east");//前进方向为west
        goCommand.execute(game);//进入到pub
        goCommand.setSecondWord("south");//前进方向为south
        goCommand.execute(game);//进入到随即房间进行传送
        boolean result = backCommand.execute(game);//返回到theater
        assertFalse(result);
        assertEquals("in a lecture theater", game.getCurrentRoom().getShortDescription());
    }
}
