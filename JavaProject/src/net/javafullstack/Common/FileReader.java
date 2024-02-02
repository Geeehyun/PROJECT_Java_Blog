package net.javafullstack.Common;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileReader extends DataBuilder {
    public String getURL(int myNumber) {
        Map<Integer, String> myList = new HashMap<Integer,String>();
        myList.put(1, "home.html");
        myList.put(2, "aboutMe.html");
        myList.put(3, "posts.html");
        myList.put(4, "contact.html");
        myList.put(5, "login.html");
        return "C:\\Users\\wkdwl\\OneDrive\\java4\\html_prj\\" + myList.get(myNumber);
    }
    public String getPostsURL(int myNumber) {
        return "C:\\Users\\wkdwl\\OneDrive\\java4\\html_prj\\" + "view" + myNumber + ".html";
    }

    public String showURL(String url, int myNumber) {
        File myHtml = new File(url);
        String body;
        try(
                Reader rd = new java.io.FileReader(myHtml);
                BufferedReader br = new BufferedReader(rd)
        ) {
            String data;
            StringBuilder bs = new StringBuilder();
            while ((data = br.readLine()) != null) {
                bs.append(data + "\n");
            }
            body = String.valueOf(bs);
            body = body.substring(body.indexOf("<section id=\"container\" class=\"container\">")+43, body.lastIndexOf("</section>"));
            body = body.substring(0, body.lastIndexOf("</section>")+10);
            if(myNumber == 2) {body = body.substring(0, body.lastIndexOf("</section>")+10);}
            if(myNumber == 3) {body = body.substring(body.indexOf("<section class=\"wrap\">"));}
            if(myNumber == 4 || myNumber == 5) {body = body.substring(body.indexOf("<fieldset class=\"field\">"),body.lastIndexOf("</fieldset>")+11);}
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return body;
    }

    public Map retunMyData(int myNumber, String body) {
        Map<String, String> dataMap = new HashMap<>();
        switch (myNumber) {
            case 1 :
                dataMap = super.makeHome(body);
                break;
            case 2 :
                dataMap = super.makeAboutMe(body);
                break;
            case 3 :
                dataMap = super.makeHome(body);
                break;
            case 4 :
                dataMap = super.makeContact(body);
                break;
            case 5 :
                dataMap = super.makeLogin(body);
                break;
        }
        return dataMap;
    }
}
