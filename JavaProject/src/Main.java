import net.javafullstack.Common.CommonUtil;
import net.javafullstack.Common.GetData;
import net.javafullstack.Common.ShowDetail;

import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        play();
    }
    public static void play() {
        CommonUtil commonUtil = new CommonUtil();
        GetData getData = new GetData();
        ShowDetail showDetail = new ShowDetail();

        int myNumber = 0;
        int restartYN = 0;

        String myURL = "";
        String myBody = "";
        Map dataMap = new HashMap<>();

        myNumber = commonUtil.displayMenu1();
        myURL = getData.getURL(myNumber);
        myBody = getData.showURL(myURL, myNumber);
        dataMap = getData.retunMyData(myNumber,myBody);
        showDetail.showPage(myNumber,dataMap);
        restartYN = commonUtil.getInputRestart();
        if(restartYN == 1) {play();}
    }


}