package cn.edu.whut.sept.zuul.Controller;

import cn.edu.whut.sept.zuul.Service.BackCommand;
import cn.edu.whut.sept.zuul.Service.Command;
import cn.edu.whut.sept.zuul.Service.Game;
import cn.edu.whut.sept.zuul.Service.GoCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
public class MapController {

    @Autowired
    Game game;
    @Autowired
    GoCommand gocommand;
    @Autowired
    BackCommand backCommand;

    @GetMapping("/Game")
    public String StartGame(Model model){
        String RoomDescription=game.getCurrentRoom().getLongDescription();
        model.addAttribute("description",RoomDescription);
        if(game.getCurrentRoom().getExit("east")==null)  model.addAttribute("east",null);
        else model.addAttribute("east","east");
        if(game.getCurrentRoom().getExit("west")==null)  model.addAttribute("west",null);
        else model.addAttribute("west","west");
        if(game.getCurrentRoom().getExit("south")==null)  model.addAttribute("south",null);
        else model.addAttribute("south","south");
        if(game.getCurrentRoom().getExit("north")==null)  model.addAttribute("north",null);
        else model.addAttribute("north","north");
        ArrayList<Integer> RoomWeights = game.getCurrentRoom().getWeight();
        model.addAttribute("RoomWeights",RoomWeights);
        ArrayList<Integer> PlayerWeights = game.getPlayer().getPlayerWeights();
        int maxWeight=game.getPlayer().freeSpace();
        model.addAttribute("playerName",game.getPlayer().getName());
        model.addAttribute("PlayerWeights",PlayerWeights);
        model.addAttribute("maxWeight",maxWeight);
        int cookie=game.getCurrentRoom().getCookie();
        model.addAttribute("cookie",cookie);
        return "map";
    }

    /**
     *
     * @param direction 前进的方向
     * @return 返回游戏界面
     */
    @GetMapping("/go/{direction}")
    public String Godirection(@PathVariable String direction){
        //Command gocommand = new GoCommand();
        gocommand.setSecondWord(direction);
        gocommand.execute(game);
        return "redirect:/Game";
    }

    /**
     *
     * @param id
     * @param redirectAttributes
     * @return 玩家拿去某件物品
     */
    @GetMapping("/take/{id}")
    public String take(@PathVariable int id, RedirectAttributes redirectAttributes){
        boolean result=game.take(id);
        if(result) redirectAttributes.addFlashAttribute("take","success");
        else redirectAttributes.addFlashAttribute("take","error");
        return "redirect:/Game";
    }

    /**
     *
     * @param id
     * @return 玩家丢弃某件物品
     */
    @GetMapping("/drop/{id}")
    public String drop(@PathVariable int id){
        game.drop(id);
        return "redirect:/Game";
    }

    /**
     * 玩家返回前一个房间
     * @return
     */
    @GetMapping("/back")
    public String back(){
        //Command command=new BackCommand();
        backCommand.execute(game);
        return "redirect:/Game";
    }

    /**
     * 玩家吃掉房间的魔法饼干
     * @return
     */
    @GetMapping("/eatCookie")
    public String eatCookie(){
        game.eatCookie();
        return "redirect:/Game";
    }
}
