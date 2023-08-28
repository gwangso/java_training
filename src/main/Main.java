package main;

import boardService.BoardService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        BoardService boardService = new BoardService();
        boolean run = true;
        while (run){
            System.out.println("= = = = = = = 게시판 = = = = = = =");
            System.out.println("1.글작성 | 2.글목록 | 3.글수정 | 4.글삭제 | 5.검색 | 0.나가기");
            System.out.print("메뉴선택 > ");
            String menu = scanner.next();
            switch (menu){
                case "1":
                    boardService.save();
                    break;
                case "2":
                    boardService.findAll();
                    break;
                case "3":
                    boardService.update();
                    break;
                case "4":
                    boardService.delete();
                    break;
                case "5":
                    boardService.search();
                    break;
                case "0":
                    System.out.println("종료합니다");
                    run = false;
                    break;
                default:
                    System.out.println("다시 선택해주세요.");
                    break;
            }
        }
    }
}