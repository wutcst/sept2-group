/**
 * 该类是“World-of-Zuul”应用程序的主类。
 * 《World of Zuul》是一款简单的文本冒险游戏。用户可以在一些房间组成的迷宫中探险。
 * 你们可以通过扩展该游戏的功能使它更有趣!.
 *
 * 如果想开始执行这个游戏，用户需要创建Game类的一个实例并调用“play”方法。
 *
 * Game类的实例将创建并初始化所有其他类:它创建所有房间，并将它们连接成迷宫；它创建解析器
 * 接收用户输入，并将用户输入转换成命令后开始运行游戏。
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 1.0
 */
package cn.edu.whut.sept.zuul.Service;

import cn.edu.whut.sept.zuul.Entity.Parser;
import cn.edu.whut.sept.zuul.Entity.Player;
import cn.edu.whut.sept.zuul.Entity.Room;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
@Data
public class Game
{
    private LinkedList<String> directions =new LinkedList<>();//保存方向,实现back
    private Parser parser;
    private Room currentRoom;
    private Player player;

    public Game()
    {
        createRooms();
        parser = new Parser();
        player=new Player("player",300);
    }

    private void createRooms()
    {
        Room outside, theater, pub, lab, office, radom;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        radom = new Room("in the radom room");

        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);
        theater.setExit("south",radom);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        radom.setExit("north",theater);

        outside.setExit("radom",theater);
        lab.setExit("radom",theater);
        pub.setExit("radom",theater);
        theater.setExit("radom",theater);
        office.setExit("radom",theater);

        currentRoom = outside;  // start game outside
    }

    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            if(command == null) {
                System.out.println("I don't understand...");
            } else {
                finished = command.execute(this);
            }
        }

        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    public LinkedList<String> getDirections() {
        return directions;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room){
        this.currentRoom = room;
    }

    public void adddirection(String direction){
        directions.addFirst(direction);
    }

    public void removedirection(){
        directions.removeFirst();
    }

    public boolean take(int n){
        if(player.take(currentRoom,n)) return true;
        else return false;
    }

    public void drop(int n){
        player.drop(currentRoom,n);
    }

    public void eatCookie(){
        player.eatMagicCookie(currentRoom);
    }
}
