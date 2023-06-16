package cn.edu.whut.sept.zuul;
import cn.edu.whut.sept.zuul.Service.Game;
import cn.edu.whut.sept.zuul.Entity.Room;
import org.junit.*;
import static org.junit.Assert.*;

public class GameTest extends Game {

//    private static Player player=new Player("wzy",300);
//    private static Room room=new Room("in a lecture theater");

    @BeforeClass
    public static void setUpBeforeClass() throws Exception{
        System.out.println("@BeforeClass");
    }
    @AfterClass
    public static void tearDownAfterClass() throws Exception{
        System.out.println("@AfterClass");
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("测试开始");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("测试结束");
    }




    @Test
    public void testNewGame() {
        Game testGame = new Game();
//        testGame.play();

        Room testRoom1 = new Room("testroom1");
        Room testRoom2 = new Room("testroom2");


        if(testGame.getCurrentRoom().getShortDescription()=="outside the main entrance of the university"){
            System.out.println(testGame.getCurrentRoom().getShortDescription());
            System.out.println("初始化与预期结果一致");
        }
        else
            System.out.println("初始化与预期结果不符");

        testGame.setCurrentRoom(testRoom1);
        if(testGame.getCurrentRoom().getShortDescription()=="testroom1"){
            System.out.println("setCurrentRoom()与预期结果一致");
            System.out.println("getCurrentRoom()与预期结果一致");
        }
        else if(testGame.getCurrentRoom().getShortDescription()=="outside the main entrance of the university")
             System.out.println("setCurrentRoom()出错");
        else System.out.println("getCurrentRoom()出错");





    }





    @Ignore
    public void testMultiply() {
        fail("Not yet implemented");
    }




}


