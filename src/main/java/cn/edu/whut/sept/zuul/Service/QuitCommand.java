package cn.edu.whut.sept.zuul.Service;

import org.springframework.stereotype.Service;

@Service
public class QuitCommand extends Command
{
    public boolean execute(Game game)
    {
        if(hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
