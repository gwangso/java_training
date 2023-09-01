package main;

import DTO.UserDTO;
import service.UserService;
import service.BoardService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        BoardService boardService = new BoardService();
        userService.sampleData();
        boardService.sampleData();
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run){
            System.out.println("=================계시판 프로젝트=================");
            if(userService.getLoginUserDTO() == null){
                System.out.println("1.회원가입 | 2.로그인 | 3.회원목록 | 4.글목록 | 5.회원정보삭제 | 6.검색 | 0.종료");
                System.out.print("메뉴선택 > ");
                int menu = scanner.nextInt();
                switch (menu){
                    case 1:
                        System.out.println("----------회원가입----------");
                        userService.save();
                        break;
                    case 2:
                        System.out.println("----------로그인----------");
                        userService.setLoginUserDTO(userService.login());
                        break;
                    case 3:
                        System.out.println("----------회원목록----------");
                        userService.findAll();
                        break;
                    case 4:
                        System.out.println("----------게시글목록----------");
                        boardService.findAll();
                        break;
                    case 5:
                        System.out.println("----------회원정보삭제----------");
                        userService.delete();
                        break;
                    case 6:
                        boardService.search();
                    case 0:
                        run = false;
                        System.out.println("시스템 종료");
                        break;
                    default:
                        System.out.println("메뉴를 잘못선택했습니다.");
                        break;
                }
            }else {
                System.out.println("1.회원정보 | 2.회원정보수정 | 3.글작성 | 4.나의 글목록 | 5.회원탈퇴 | 0.로그아웃");
                System.out.print("메뉴선택 > ");
                int menu = scanner.nextInt();
                switch (menu){
                    case 1:
                        System.out.println("----------회원정보----------");
                        userService.findUserInfo(userService.getLoginUserDTO());
                        break;
                    case 2:
                        System.out.println("----------회원정보수정----------");
                        userService.update(userService.getLoginUserDTO());
                        break;
                    case 3:
                        System.out.println("-----------글작성-------------");
                        boardService.save();
                        break;
                    case 4:
                        boardService.myWrite();
                        break;
                    case 5:
                        System.out.println("----------회원탈퇴----------");
                        userService.withdraw();
                        userService.logout();
                        break;
                    case 0:
                        System.out.println("----------로그아웃----------");
                        userService.logout();
                        break;
                    default:
                        System.out.println("메뉴를 잘못선택했습니다.");
                        break;
                }
            }
        }
    }
}