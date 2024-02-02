package net.javafullstack.Common;

import java.util.*;
import java.util.List;

public class DetailView extends FileReader {
    List post;
    String[] title = {"제목","내용","작성자","작성일"};

    public  void showPage(int myNumber, Map dataMap) {
        switch (myNumber) {
            case 1 :
                showPost(dataMap);
                break;
            case 2 :
                showAboutMe(dataMap);
                break;
            case 3 :
                showPost(dataMap);
                break;
            case 4 :
                showContect(dataMap);
                break;
            case 5 :
                showLogin(dataMap);
                break;
        }
    }
    public void showPost(Map dataMap) {
        System.out.println();
        int number = 0;
        for(int i = 0; i < dataMap.size(); i++) {
            post = (List)dataMap.get(i+1);
            System.out.printf("NO %2d / ",(i+1));
            for(int j = 0; j < post.size(); j++) {
                if(j != 1) {System.out.print(title[j] + " : " + post.get(j) + "  " );}
            }
            System.out.println();
        }
        while (number == 0) {
            number = getListNumber(dataMap);
        }
        showPostDetail(dataMap, number);
    }

    public void showPostDetail(Map dataMap, int number) {
        System.out.println();
        System.out.println();
        String myUrl = getPostsURL(number);
        String myBody = showURL(myUrl,0);
        Map bodyToCrop = makePostDetail(myBody);
        System.out.println("NO : " + number);
        post = (List)dataMap.get(number);
        for(int i = 0; i < post.size();i++) {
            if (i != 1) {
                System.out.println(title[i] + " : " + post.get(i));
            } else if (i == 1) {
                System.out.println(title[i] + ":");
                for (int j = 0; j < bodyToCrop.size(); j++) {
                    System.out.println(bodyToCrop.get(j+1));
                }
            }
        }
        number = doubleCheckForPost(number, dataMap);
        if (number == 0) {
            showPost(dataMap);
        }
    }
    public void showAboutMe(Map dataMap) {
        System.out.println();
        System.out.println("안녕하세요! 저는 장지현 입니다!");
        System.out.println("--------------------------");
        Object[] keys = (dataMap.keySet()).toArray();
        for(int i = 0; i < keys.length; i++) {
            System.out.print(keys[i] + " : ");
            System.out.print(dataMap.get(keys[i]));
            System.out.println();
        }
    }
    public void showContect(Map dataMap) {
        System.out.println();
        System.out.println("아래의 정보를 입력해주시면 얼른 연락드릴께요!");
        System.out.println("--------------------------");
        Object[] keys = (dataMap.keySet()).toArray();
        for(int i = 0; i < keys.length; i++) {
            System.out.print(keys[i] + "번 항목 : ");
            System.out.print(dataMap.get(keys[i]));
            System.out.println();
        }
    }
    public void showLogin(Map dataMap) {
        System.out.println();
        System.out.println("아래의 항목을 입력 후 로그인 해주세요!");
        System.out.println("--------------------------");
        Object[] keys = (dataMap.keySet()).toArray();
        for(int i = 0; i < keys.length; i++) {
            System.out.print(keys[i] + "번 항목 : ");
            System.out.print(dataMap.get(keys[i]));
            System.out.println();
        }
    }

    public int getListNumber(Map dataMap) {
        Scanner scanner = new Scanner(System.in);
        int rtnMenuNo = 0;
        while (rtnMenuNo == 0) {
            System.out.println();
            System.out.println("조회할 게시글 번호를 선택하세요. : ");
            String menuNo = scanner.next();
            try {
                if (!menuNo.isEmpty() && menuNo != null) {
                    rtnMenuNo = Integer.parseInt(menuNo);
                }
                if(!(rtnMenuNo > 0 && rtnMenuNo <= dataMap.size())) {
                    System.out.println();
                    System.out.println("게시글 번호 내에서 선택해주세요!");
                    showPost(dataMap);
                    rtnMenuNo = 0;
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요!");
                rtnMenuNo = 0;
            }
        }
        return rtnMenuNo;
    }

    public int doubleCheckForPost(int myNumber, Map dataMap) {
        Scanner scanner = new Scanner(System.in);
        int rtnMenuNo = 0;
        while (rtnMenuNo == 0) {
            System.out.println();
            System.out.println("다른글도 조회하시겠습니까? \n1.리스트로 이동\n2.이전글\n3.다음글\n4.처음으로");
            String menuNo = scanner.next();
            try {
                if(!menuNo.isEmpty() && menuNo != null) {
                    rtnMenuNo = Integer.parseInt(menuNo);
                } if(!(rtnMenuNo > 0 && rtnMenuNo < 5)) {
                    System.out.println("입력 범위에서만 입력해주세요!");
                    System.out.println();
                    rtnMenuNo = 0;
                }
            } catch(NumberFormatException e) {
                System.out.println("정해진 숫자만 입력해주세요!");
                rtnMenuNo = 0;
            }
            switch (rtnMenuNo) {
                case 1 :
                    myNumber = 0;
                    break;
                case 2 :
                    if ((myNumber-1) > 0 && (myNumber-1) <= dataMap.size()) {
                        showPostDetail(dataMap, (myNumber-1));
                    } else {
                        System.out.println("이전 글이 없습니다.");
                        rtnMenuNo = 0;
                    }
                    break;
                case 3 :
                    if ((myNumber+1) > 0 && (myNumber+1) <= dataMap.size()) {
                        showPostDetail(dataMap, (myNumber+1));
                    } else {
                        System.out.println("다음 글이 없습니다.");
                        rtnMenuNo = 0;
                    }
                    break;
                case 4:
                    break;
            }
        }
        return myNumber;
    }
}
