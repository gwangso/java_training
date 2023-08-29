package main;

import service.BankService;

import java.util.Scanner;

public class BankMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankService bankService = new BankService();
        boolean run = true;
        while(run){
            System.out.println("====================은행====================");
            System.out.println("1.신규계좌 등록 | 2.잔액조회 | 3.입금 | 4.출금 | 5.입출금 내역 조회 | 0.종료");
            System.out.print("메뉴 > ");
            String menu = scanner.nextLine();
            System.out.println();
            switch (menu){
                case "1":
                    System.out.println("--------------------신규계좌 등록--------------------");
                    bankService.clientSave();
                    break;
                case "2":
                    System.out.println("--------------------잔액조회--------------------");
                    bankService.balanceInquery();
                    break;
                case "3":
                    System.out.println("--------------------입금--------------------");
                    bankService.deposit();
                    break;
                case "4":
                    System.out.println("--------------------출금--------------------");
                    bankService.withdraw();
                    break;
                case "5":
                    System.out.println("--------------------입출금내역--------------------");
                    bankService.accountInquery();
                    break;
                case "6":
                    System.out.println("--------------------전체계좌조회--------------------");
                    bankService.findAll();
                    break;
                case "7":
                    System.out.println("--------------------SampleData--------------------");
                    bankService.clinetSampleData();
                    break;
                case "0":
                    run = false;
                    break;
                default:
                    System.out.println("다시 선택해 주세요");
                    break;
            }
            System.out.println();
        }
        System.out.println("프로그램을 종료합니다.");
    }
}