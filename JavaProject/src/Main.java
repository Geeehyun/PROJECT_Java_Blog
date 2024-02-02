import net.javafullstack.Common.MenuController;
import net.javafullstack.Common.FileReader;
import net.javafullstack.Common.DetailView;

import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        play();
    }
    public static void play() {
        MenuController menuController = new MenuController();
        FileReader fileReader = new FileReader();
        DetailView detailView = new DetailView();

        int myNumber = 0;
        int restartYN = 0;

        String myURL = "";
        String myBody = "";
        Map dataMap = new HashMap<>();

        myNumber = menuController.displayMenu();
        myURL = fileReader.getURL(myNumber);
        myBody = fileReader.showURL(myURL, myNumber);
        dataMap = fileReader.retunMyData(myNumber,myBody);
        detailView.showPage(myNumber,dataMap);
        restartYN = menuController.getInputRestart();
        if(restartYN == 1) {play();}
    }


}