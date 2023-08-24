package main;

import dto.UserDTO;
import service.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        UserDTO login = new UserDTO();
        while (run){
            System.out.println("=================자바 회원 프로젝트=================");

            if(login.getUserId() == null){
                System.out.println("1.회원가입 | 2.로그인 | 3.회원목록 | 4.회원정보삭제 | 0.종료");
                System.out.print("메뉴선택 > ");
                int menu = scanner.nextInt();
                switch (menu){
                    case 1:
                        System.out.println("----------회원가입----------");
                        userService.save();
                        break;
                    case 2:
                        System.out.println("----------로그인----------");
                        login = userService.login();
                        break;
                    case 3:
                        System.out.println("----------회원목록----------");
                        userService.findAll();
                        break;
                    case 4:
                        System.out.println("----------회원정보삭제----------");
                        userService.delete();
                        break;
                    case 0:
                        run = false;
                        System.out.println("시스템 종료");
                        break;
                    default:
                        System.out.println("메뉴를 잘못선택했습니다.");
                        break;
                }
            }else {
                System.out.println("1.회원정보 | 2.회원정보수정 | 3.로그아웃 | 4.회원탈퇴 | 0.종료");
                System.out.print("메뉴선택 > ");
                int menu = scanner.nextInt();
                switch (menu){
                    case 1:
                        System.out.println("----------회원정보----------");
                        userService.findUserInfo();
                        break;
                    case 2:
                        System.out.println("----------회원정보수정----------");
                        userService.update(login);
                        break;
                    case 3:
                        System.out.println("----------로그아웃----------");
                        login = new UserDTO();
                        break;
                    case 4:
                        System.out.println("----------회원탈퇴----------");
                        userService.withdraw(login);
                        break;
                    case 0:
                        run = false;
                        System.out.println("시스템 종료");
                        break;
                    default:
                        System.out.println("메뉴를 잘못선택했습니다.");
                        break;
                }

            }



            System.out.println("1.회원가입 | 2.로그인 | 3.회원목록 | 4.회원정보수정 | 5.회원정보삭제");
        }
    }
}