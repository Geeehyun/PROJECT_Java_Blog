package net.javafullstack.Common;

import java.io.Reader;
import java.util.*;

public class MakeData {
    Map dataMap = new HashMap<>();
    String key;
    int keyInt;
    String value;
    List valueList;
    List texts;
    Map textMap;
    String[] tags = {"<p>","</p>","<h3>","</h3>","<h4>","</h4>","<br>","<b>","</b>","</li>","<div class=\"inputarea\">","<div class=\"inputarea login\">","</div>","</label>","<article class=\"article\">","</article>","</a>"};

    void setDefault() {
        dataMap.clear();
        key = null;
        value = null;
    }
    String[] toTrim(String[] arr) {
        for(int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].trim();
        }
        return arr;
    }
    List toForm(String body) {
        List bodyToList = new ArrayList<>();
        for(int i = 0; i < tags.length; i++) {
            body = body.replaceAll(tags[i],"");
        }
        for(String i : toTrim(body.split("\n"))) {
            bodyToList.add(i);
        }
        for(int i = 0; i < bodyToList.size(); i++) {
            if(bodyToList.get(i).equals("")) {
                bodyToList.remove(i);
                i--;
            } else {
                for(int j = 1; j <= 10; j++) {
                    if(bodyToList.get(i).equals("<a href=\"view" + (j) + ".html\">")) {
                        bodyToList.remove(i);
                        i--;
                        break;
                    }
                }
            }
        }
        return bodyToList;
    }
    Map toFormToPost(String body) {
        List bodyToList = new ArrayList<>();
        List insideElement = new ArrayList<>();
        Map<Integer,List> finaMap = new HashMap();
        for(String i : toTrim(body.split("</article>"))) {
            bodyToList.add(i);
        }
        for(int i = 0; i < bodyToList.size(); i++) {
            String text = (String) bodyToList.get(i);
            for(int j = 0; j < tags.length; j++) {
                text = text.replaceAll(tags[j],"");
                bodyToList.set(i,text);
            }
            for(int j = 1; j <= 10; j++) {
                if(text.contains("<a href=\"view" + (j) + ".html\">")) {
                    text = text.replaceAll("<a href=\"view" + (j) + ".html\">","");
                    bodyToList.set(i,text);
                }
            }
        }
        for(int i = 0; i < bodyToList.size(); i++) {
            insideElement.clear();
            String[] textArr = toTrim(((String) bodyToList.get(i)).split("\n"));
            for(int j = 0; j < textArr.length; j++) {
                insideElement.add(textArr[j]);
            }
            for(int j = 0; j < insideElement.size(); j++) {
                if (insideElement.get(j).equals("") || insideElement.get(j).equals(" ") ) {
                    insideElement.remove(j);
                    j--;
                }
            }
            finaMap.put((i+1),new ArrayList(insideElement));
        }
        return finaMap;
    }
    public Map makeHome(String body) {
        setDefault();
        body = body.substring(body.indexOf("<article class=\"article\">"),body.lastIndexOf("</article>"));
        body.replaceAll("<article class=\"article\">","");
        textMap = toFormToPost(body);
        for(int i = 0; i < textMap.size(); i++) {
            keyInt = (i+1);
            List text = new ArrayList<>((List)textMap.get(i+1));
            for(int j = 0; j < text.size(); j++) {
                String element = (String) text.get(j);
                if(j == 2) {
                    text.set(j,element.substring(element.indexOf(">")+1,element.lastIndexOf(" ")));
                    text.add(element.substring(element.lastIndexOf(" ")+1));
                    j+=2;
                } else {
                    text.set(j,element.substring(element.indexOf(">")+1));
                }
            }
            valueList = text;
            dataMap.put(keyInt,valueList);
        }
        return dataMap;
    }
    //    public Map makeHome(String body) {
//        setDefault();
//        String temp = body.substring(body.indexOf("<article class=\"article\">"),body.lastIndexOf("</article>"));
//        temp = temp.replaceAll("<article class=\"article\">","");
//        String[] tempArr = toTrim(temp.split("</article>"));
//        String[][] finalList = new String[tempArr.length][];
//        for(int i = 0; i < tempArr.length; i++) {
//            tempArr[i] = tempArr[i].replaceAll("<a href=\"view"+ (i+1) +".html\">", "");
//            tempArr[i] = tempArr[i].replaceAll("</a>","");
//            String[] tempArr3 = tempArr[i].split("\n");
//            tempArr3 = toTrim(tempArr3);
//            List<String> tempToList;
//            tempToList = Arrays.asList(tempArr3);
//            List<String> myList = new ArrayList<>();
//            for(int j = 0; j < tempToList.size(); j++) {
//                String tempE = tempToList.get(j);
//                if(!tempE.isEmpty()) {
//                    myList.add(tempE);
//                }
//            }
//            finalList[i] = myList.toArray(new String[myList.size()]);
//        }
//        for(int i = 0; i < finalList.length; i++) {
//            keyInt = i+1;
//            List<String> textList = new ArrayList<>();
//           for(int j = 0; j < finalList[i].length; j++){
//               String text = finalList[i][j];
//               text = text.substring(text.indexOf(">") + 1, text.lastIndexOf("<"));
//               if(j != (finalList[i].length-1)) {
//                   textList.add(text);
//               } else {
//                   String[] lastE = text.split("  ");
//                   textList.add(lastE[0]);
//                   textList.add(lastE[1]);
//               }
//           }
//           valueList = textList;
//           dataMap.put(keyInt, valueList);
//        }
//        return dataMap;
//    }
    public Map makeAboutMe(String body) {
        setDefault();
        body = body.substring(body.indexOf("<ul class=\"info\">")+17,body.indexOf("</ul>"));
        texts = toForm(body);
        for(int i = 0; i < texts.size(); i++) {
            String text = (String) texts.get(i);
            key = (text.substring(text.lastIndexOf(">")+1, text.lastIndexOf(" :"))).trim();
            value = (text.substring(text.lastIndexOf(":")+1)).trim();
            dataMap.put(key,value);
        }
        return dataMap;
    }

//    public Map makePosts(String body) {
//        setDefault();
//        String temp = body.substring(body.indexOf("<article class=\"article\">"),body.lastIndexOf("</article>"));
//        temp = temp.replaceAll("<article class=\"article\">","");
//        String[] tempArr = toTrim(temp.split("</article>"));
//        String[][] finalList = new String[tempArr.length][];
//        for(int i = 0; i < tempArr.length; i++) {
//            tempArr[i] = tempArr[i].replaceAll("<a href=\"view"+ (i+1) +".html\">", "");
//            tempArr[i] = tempArr[i].replaceAll("</a>","");
//            tempArr[i] = tempArr[i].trim();
//            String[] tempArr3 = tempArr[i].split("\n");
//            tempArr3 = toTrim(tempArr3);
//            List<String> tempToList;
//            tempToList = Arrays.asList(tempArr3);
//            List<String> myList = new ArrayList<>();
//            for(int j = 0; j < tempToList.size(); j++) {
//                String tempE = tempToList.get(j);
//                if(!tempE.isEmpty()) {
//                    myList.add(tempE);
//                }
//            }
//            finalList[i] = myList.toArray(new String[myList.size()]);
//        }
//        for(int i = 0; i < finalList.length; i++) {
//            keyInt = i+1;
//            List<String> textList = new ArrayList<>();
//            for(int j = 0; j < finalList[i].length; j++){
//                String text = finalList[i][j];
//                text = text.substring(text.indexOf(">") + 1, text.lastIndexOf("<"));
//                if(j != (finalList[i].length-1)) {
//                    textList.add(text);
//                } else {
//                    String[] lastE = text.split("  ");
//                    textList.add(lastE[0]);
//                    textList.add(lastE[1]);
//                }
//            }
//            valueList = textList;
//            dataMap.put(keyInt, valueList);
//        }
//        return dataMap;
//    }

    public Map makeContact(String body) {
        setDefault();
        body = body.substring(body.indexOf("<div class=\"inputarea\">"),body.lastIndexOf("</div>"));
        texts = toForm(body);
        for(int i = 0; i < texts.size(); i++) {
            String text = (String) texts.get(i);
            keyInt = i+1;
            value = text.substring(text.lastIndexOf(">")+1);
            dataMap.put(keyInt, value);
        }
        return dataMap;
    }
    public Map makeLogin(String body) {
        setDefault();
        body = body.substring(body.indexOf("<div class=\"inputarea login\">")+29,body.lastIndexOf("</div>"));
        texts = toForm(body);
        for(int i = 0; i < texts.size(); i++) {
            String text = (String) texts.get(i);
            keyInt = i+1;
            value = text.substring(text.lastIndexOf(">")+1);
            dataMap.put(keyInt, value);
        }
        return dataMap;
    }

    public Map makePostDetail(String body) {
        setDefault();
        body = body.substring(body.indexOf("<article class=\"contents\">")+26,body.lastIndexOf("</article>"));
        texts = toForm(body);
        int count = 1;
        for (int i = 0; i < texts.size(); i++) {
            keyInt = count;
            value = (String) texts.get(i);
            dataMap.put(keyInt,value);
            count++;
        }
        return dataMap;
    }
}

