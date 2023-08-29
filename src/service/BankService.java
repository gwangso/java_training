package service;

import dto.AccountDTO;
import dto.ClientDTO;
import repostitory.BankRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class BankService {
    Scanner scanner = new Scanner(System.in);

    BankRepository bankRepository = new BankRepository();

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    public void clientSave() {
        // 고객이름 입력 (공백시 계좌생성 중단)
        System.out.print("고객이름 > ");
        String clientName = scanner.next();
        if (clientName==""){
            System.out.println("계좌생성을 취소합니다.");
            return;
        }
        // 계좌번호 입력 (공백시 계좌생성 중단)
        System.out.print("계좌번호 > ");
        String accountNumber = scanner.next();
        if (accountNumber==""){
            System.out.println("계좌생성을 취소합니다.");
            return;
        }
        // 계좌중복체크 (중복시 계좌생성 중단)
        boolean check = bankRepository.accountCheck(accountNumber);
        if(!check){
            System.out.println("사용할 수 없는 계좌입니다.");
            System.out.println("계좌생성을 취소합니다.");
            return;
        }
        // 계좌비밀번호 입력 (공백시 계좌생성 중단)
        System.out.print("계좌비밀번호 > ");
        String clientPass = scanner.next();
        if (clientPass==""){
            System.out.println("계좌생성을 취소합니다.");
            return;
        }
        // 계좌 비밀번호 확인(5회 틀릴시 계좌생성 중단)
        int cnt = 0;
        while (true) {
            System.out.print("비밀번호 확인 > ");
            String verifyClientPass = scanner.next();
            cnt++;
            if(clientPass.equals(verifyClientPass)) {
                break;
            }else {
                System.out.println("비밀번호가 일치하지 않습니다.(" + cnt + "/5)");
                if(cnt==5){
                    System.out.println("계좌생성을 취소합니다.");
                    return;
                }
            }
        }
        // 현재시간
        LocalDateTime now = LocalDateTime.now();
        Long deposit;
        while (true){
            System.out.print("초기입금액 > ");
            String money = scanner.next();
            deposit = numbercheck(money);
            if(deposit != null){
                break;
            }
        }

        // clientDTO 생성
        ClientDTO clientDTO = new ClientDTO(clientName, accountNumber, clientPass, now.format(dtf));
        AccountDTO accountDTO = new AccountDTO(accountNumber, deposit, 0L, now.format(dtf));
        // clientDTO를 repositroy에 보낸 후 저장 성공 시 true, 실패시 false를 불러옴
        boolean result1 = bankRepository.saveClinet(clientDTO);
        boolean result2 = bankRepository.deposit(clientDTO, accountDTO);
        if (result1 && result2){
            System.out.println("계좌신설에 성공했습니다.");
//        } else if (result1 && !result2) {
//            System.out.println();
//        } else if (!result1 && result2) {
//            System.out.println();
        } else {
            System.out.println("알수없는 이유로 계좌 신설에 실패했습니다.");
        }
    }

    public void balanceInquery() {
        System.out.print("조회할 계좌번호 > ");
        String accountNumber = scanner.next();
        ClientDTO clientDTO = bankRepository.findbyAccount(accountNumber);
        if (clientDTO != null){
            System.out.println("이름 : " + clientDTO.getClientName());
            System.out.println("잔액 : " + clientDTO.getBalance());
        }else {
            System.out.println("없는 계좌번호입니다.");
        }
    }

    public void deposit() {
        System.out.print("입금 계좌번호 > ");
        String accountNumber = scanner.next();
        ClientDTO clientDTO = bankRepository.findbyAccount(accountNumber);
        if (clientDTO == null){
            System.out.println("없는 계좌번호입니다.");
        }else {
            System.out.print("입금액 > ");
            String money = scanner.next();
            if (money == ""){
                System.out.println("입금을 취소합니다.");
                return;
            }
            long deposit = numbercheck(money);
            if (deposit<=0){
                System.out.println("입금을 취소합니다.");
            }else {
                System.out.println(deposit + "원을 입금합니다.");
                LocalDateTime now = LocalDateTime.now();
                AccountDTO accountDTO = new AccountDTO(accountNumber, deposit, 0L, now.format(dtf));
                boolean result = bankRepository.deposit(clientDTO, accountDTO);
                if (result) {
                    System.out.println("입금이 완료되었습니다.");
                    System.out.println(clientDTO.getClientName() + "님의 현재 잔액은 " + clientDTO.getBalance() + "원 입니다.");
                }else {
                    System.out.println("알 수 없는 이유로 입금이 실패했습니다.");
                }
            }
        }
    }

    public void withdraw() {
        System.out.print("출금 계좌번호 > ");
        String accountNumber = scanner.next();
        ClientDTO clientDTO = bankRepository.findbyAccount(accountNumber);
        if (clientDTO == null){
            System.out.println("없는 계좌번호입니다.");
        }else {
            System.out.print("출금액 > ");
            String money = scanner.next();
            if (money == ""){
                System.out.println("출금을 취소합니다.");
                return;
            }
            long withdraw = numbercheck(money);
            if (withdraw<=0){
                System.out.println("출금을 취소합니다.");
            }else {
                long balance = clientDTO.getBalance();
                if (balance-withdraw >=0){
                    System.out.print(withdraw + "원을 출금하시겠습니까?(Y/y) > ");
                    String ok = scanner.next();
                    if(ok.equals("Y") || ok.equals("y") || ok.equals("ㅛ")){
                        LocalDateTime now = LocalDateTime.now();
                        AccountDTO accountDTO = new AccountDTO(accountNumber, 0L, withdraw, now.format(dtf));
                        boolean result = bankRepository.withdraw(clientDTO, accountDTO);
                        if (result) {
                            System.out.println("출금이 완료되었습니다.");
                            System.out.println(clientDTO.getClientName() + "님의 현재 잔액은 " + clientDTO.getBalance() + "원 입니다.");
                        }else {
                            System.out.println("알 수 없는 이유로 입금이 실패했습니다.");
                        }
                    }else {
                        System.out.println("출금을 취소합니다.");
                    }
                }else{
                    System.out.println("잔액이 부족합니다.");
                }
            }
        }
    }

    public void accountInquery() {
        System.out.print("조회할 계좌번호 > ");
        String accountNumber = scanner.next();
        ClientDTO clientDTO = bankRepository.findbyAccount(accountNumber);
        if (clientDTO != null){
            System.out.println(clientDTO);
            System.out.println("----------------------------------------");
            List<AccountDTO> list = bankRepository.findAccountByAccount(clientDTO.getAccountNumber());
            if (list.size()==0){
                System.out.println("거래내역이 없습니다.");
            }else {
                for (AccountDTO accountDTO : list){
                    System.out.println(accountDTO);
                }
            }
        }else {
            System.out.println("없는 계좌번호입니다.");
        }
    }

    public void clinetSampleData() {
        LocalDateTime now = LocalDateTime.now();
        for (int i = 1; i<=5; i++){
            ClientDTO clientDTO = new ClientDTO("인간"+i, "account"+i, "pass"+i, now.format(dtf),i*1000L);
            bankRepository.saveClinet(clientDTO);
        }
    }

    public void findAll() {
        for (ClientDTO clientDTO : bankRepository.findAll()){
            System.out.println(clientDTO);
        }
    }

    public Long numbercheck(String num){
        long a = 0;
        try {
            a = Integer.parseInt(num);
            return a;
        }catch (Exception e){
            return null;
        }
    }
}