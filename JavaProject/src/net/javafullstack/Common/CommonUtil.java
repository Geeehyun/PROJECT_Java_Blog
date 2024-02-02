package net.javafullstack.Common;

import java.util.*;

public class CommonUtil {
    public int displayMenu1() {
        String[] arrMenu1 = {"1 : Home", "2 : About Me", "3 : Posts", "4 : Contact", "5 : LogIn"};
        int menuNo = 0;
        do {
            System.out.println();
            for (String no : arrMenu1) {
                if(menuNo != 0 && no.split(" : ")[0].equals(String.valueOf(menuNo))) {
                    System.out.println(no + " <<");
                } else {
                    System.out.println(no);
                }
            }
            if(!(menuNo > 0 && menuNo < 5)) {
                System.out.println();
                menuNo = getInputMenu();
            }
            if((menuNo > 0 && menuNo < 6)) {
                System.out.println();
                for (String no : arrMenu1) {
                    if(menuNo != 0 && no.split(" : ")[0].equals(String.valueOf(menuNo))) {
                        System.out.println(no + " <<");
                    } else {
                        System.out.println(no);
                    }
                }
                System.out.println();
                menuNo = doubleCheck(menuNo);
            }
        } while (menuNo == 0);
        return menuNo;
    }


    //    public int displayMenu2() {
//        String[] arrMenu2 = {"31 : 이전 글", "32 : 다음 글", "33 : 전체 글", "99 : 이전 메뉴"};
//        int menuNo = 0;
//        do {
//            for (String no : arrMenu2) {
//                if(no.split(" : ")[0].equals(String.valueOf(menuNo))) {
//                    System.out.println(no + " <<");
//                } else {
//                    System.out.println(no);
//                }
//            }
//            if(menuNo != 99 || menuNo == 0) {
//                System.out.println();
//                menuNo = getInputMenu();
//            }
//            if(menuNo > 30 && menuNo < 34) {
//                for (String no : arrMenu2) {
//                    if(menuNo != 99 && no.split(" : ")[0].equals(String.valueOf(menuNo))) {
//                        System.out.println(no + " <<");
//                    } else {
//                        System.out.println(no);
//                    }
//                }
//                System.out.println();
//                menuNo = doubleCheck(menuNo);
//            }
//            if(menuNo == 99) {
//                for (String no : arrMenu2) {
//                    if(no.split(" : ")[0].equals(String.valueOf(menuNo))) {
//                        System.out.println(no + " <<");
//                    } else {
//                        System.out.println(no);
//                    }
//                }
//                menuNo = displayMenu1();
//                break;
//            }
//        } while (menuNo == 0);
//        return menuNo;
//    }
    public int getInputMenu() {
        Scanner scanner = new Scanner(System.in);
        int rtnMenuNo = 0;
        while (rtnMenuNo == 0) {
            System.out.println("메뉴를 선택하세요. : ");
            String menuNo = scanner.next();
            try {
                if (!menuNo.isEmpty() && menuNo != null) {
                    rtnMenuNo = Integer.parseInt(menuNo);
                }
                if(!(rtnMenuNo > 0 && rtnMenuNo < 6)) {
                    System.out.println();
                    System.out.println("메뉴 범위에서만 입력해주세요!");
                    rtnMenuNo = 0;
                }
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("숫자만 입력해주세요!");
                rtnMenuNo = 0;
            }
        }
        return rtnMenuNo;
    }

    public int getInputRestart() {
        Scanner scanner = new Scanner(System.in);
        int rtnMenuNo = 0;
        while (rtnMenuNo == 0) {
            System.out.println();
            System.out.println("처음부터 다시 시작하시겠습니까? 예(1) - 처음으로 / 아니오(2) - 종료 ");
            String menuNo = scanner.next();
            try {
                if (!menuNo.isEmpty() && menuNo != null) {
                    rtnMenuNo = Integer.parseInt(menuNo);
                }
                if (rtnMenuNo != 1 && rtnMenuNo != 2) {
                    System.out.println();
                    System.out.println("메뉴 범위에서만 입력해주세요!");
                    rtnMenuNo = 0;
                }
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("숫자만 입력해주세요!");
                rtnMenuNo = 0;
            }
        }
        return rtnMenuNo;
    }

    public int doubleCheck(int myNumber) {
        Scanner scanner = new Scanner(System.in);
        int rtnMenuNo = 0;
        while (rtnMenuNo == 0) {
            System.out.println("해당 메뉴를 조회하시겠습니까? 예(1) / 아니오(2)");
            String menuNo = scanner.next();
            try {
                if (!menuNo.isEmpty() && menuNo != null) {
                    rtnMenuNo = Integer.parseInt(menuNo);
                }
                if (rtnMenuNo != 1 && rtnMenuNo != 2) {
                    System.out.println("입력 범위에서만 입력해주세요!");
                    System.out.println();
                    rtnMenuNo = 0;
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요!");
                rtnMenuNo = 0;
            }
        }
        if(rtnMenuNo == 2) {
            myNumber = 0;
        }
        return myNumber;
    }



}