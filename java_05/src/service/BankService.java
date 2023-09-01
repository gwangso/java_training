package service;

import dto.AccountDTO;
import dto.ClientDTO;
import repostitory.BankRepository;

import java.util.List;
import java.util.Scanner;

public class BankService {
    Scanner scanner = new Scanner(System.in);

    BankRepository bankRepository = new BankRepository();

    public void clientSave() {
        // 고객이름 입력
        System.out.print("고객이름 > ");
        String clientName = scanner.next();

        // 계좌번호 입력
        System.out.print("계좌번호 > ");
        String accountNumber = scanner.next();

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

        // 초기입금액
        Long deposit;
        while (true){
            System.out.print("초기입금액 > ");
            String money = scanner.next();
            deposit = numbercheck(money);
            if(deposit > 0){
                break;
            }else {
                System.out.println("다시 입력해주세요");
            }
        }

        // clientDTO 생성
        ClientDTO clientDTO = new ClientDTO(clientName, accountNumber, clientPass);
        // AccountDTO 생성
        AccountDTO accountDTO = new AccountDTO(accountNumber, deposit, 0L);
        // clientDTO를 repositroy에 보낸 후 저장 성공 시 true, 실패시 false를 불러옴
        boolean result1 = bankRepository.saveClinet(clientDTO);
        boolean result2 = bankRepository.deposit(clientDTO, accountDTO);
        // 둘다 true일때만 계좌신설
        if (result1 && result2){
            System.out.println("계좌신설에 성공했습니다.");
//        // 계좌신설은 됐는데 거래가 안됨
//        } else if (result1 && !result2) {
//            System.out.println("거래오류");
//        // 거래는 되는데 계좌신설이 안됨(경우의 수 없지 않을까?
//        } else if (!result1 && result2) {
//            System.out.println("계좌생성오류");
        // 둘다 안됨
        } else {
            System.out.println("알수없는 이유로 계좌 신설에 실패했습니다.");
        }
    }

    // 계좌 체크
    public ClientDTO checkAccount(){
        System.out.print("계좌번호 > ");
        String accountNumber = scanner.next();
        return bankRepository.findbyAccount(accountNumber);
    }

    // 계좌번호로 잔액 조회
    public void balanceInquery() {
        ClientDTO clientDTO = checkAccount();
        if (clientDTO != null){
            System.out.println("이름 : " + clientDTO.getClientName());
            System.out.println("잔액 : " + clientDTO.getBalance());
        }else {
            System.out.println("없는 계좌번호입니다.");
        }
    }

    // 입금
    public void deposit() {
        //계좌 찾기
        ClientDTO clientDTO = checkAccount();
        if (clientDTO == null){
            System.out.println("없는 계좌번호입니다.");
        }else {
            // 입금액 입력
            System.out.print("입금액 > ");
            String money = scanner.next();
            // 입력받은 문자를 숫자로 반환(문자입력시 0 반환)
            Long deposit = numbercheck(money);
            // null일 경우 입금 취소
            if (deposit==null) {
                System.out.println("입금금액을 잘못입력했습니다.");
                System.out.println("입금을 취소합니다.");
            // 입금액이 0보다 작거나 같거나 문지입력시 입금 취소
            }else if (deposit<=0){
                System.out.println("입금을 취소합니다.");
            // 입금액이 null이 아니고(문자 안받음) 0보다 클경우 입금시도
            }else {
                System.out.println(deposit + "원을 입금합니다.");
                AccountDTO accountDTO = new AccountDTO(clientDTO.getAccountNumber(), deposit, 0L);
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

    // 출금
    public void withdraw() {
        // 출금할 계좌 찾기
        ClientDTO clientDTO = checkAccount();
        if (clientDTO == null){
            System.out.println("없는 계좌번호입니다.");
        }else {
            int cnt = 0;
            while (true){
                System.out.print("비밀번호를 입력하시오. > ");
                String password = scanner.next();
                // 비밀번호가 옳을 경우
                if(password.equals(clientDTO.getClientPass())){
                    // 출금액 설정
                    System.out.print("출금액 > ");
                    String money = scanner.next();
                    // 출금액 숫자인지 판별 (문자입력시 0 출력)
                    long withdraw = numbercheck(money);
                    // 출금액이 0 이상일 경우
                    if (withdraw>=0){
                        long balance = clientDTO.getBalance();
                        // 잔액이 출금액보다 크면 출금
                        if (balance-withdraw >=0){
                            // 출금은 한번 더 물어봄
                            System.out.print(withdraw + "원을 출금하시겠습니까?(Y/y) > ");
                            String ok = scanner.next();
                            if(ok.equals("Y") || ok.equals("y") || ok.equals("ㅛ")){
                                AccountDTO accountDTO = new AccountDTO(clientDTO.getAccountNumber(), 0L, withdraw);
                                boolean result = bankRepository.withdraw(clientDTO, accountDTO);
                                if (result) {
                                    System.out.println("출금이 완료되었습니다.");
                                    System.out.println(clientDTO.getClientName() + "님의 현재 잔액은 " + clientDTO.getBalance() + "원 입니다.");
                                }else {
                                    System.out.println("알 수 없는 이유로 입금이 실패했습니다.");
                                }
                                return;
                            }else {
                                System.out.println("출금을 취소합니다.");
                                break;
                            }
                            // 잔액이 출금액보다 작으면 잔액부족 출력
                        }else{
                            System.out.println("잔액이 부족합니다.");
                            break;
                        }
                        // 출금액이 0보다 작거나 같거나 문자입력시
                    }else {
                        System.out.println("출금을 취소합니다.");
                        break;
                    }
                // 비밀번호가 다를 경우
                }else {
                    cnt++;
                    System.out.println("비밀번호가 틀렸습니다.("+cnt+"/5)");
                    if (cnt==5){
                        System.out.println("비밀번호를 5회 틀리셨습니다.");
                        System.out.println("처음화면으로 돌아갑니다.");
                        return;
                    }
                }
            }
        }
    }

    // 계좌번호 거래내역 조회
    public void accountInquery() {
        // 계좌번호 찾기
        System.out.print("조회할 계좌번호 > ");
        String accountNumber = scanner.next();
        ClientDTO clientDTO = bankRepository.findbyAccount(accountNumber);

        if (clientDTO != null){
            // 계좌 정보 출력
            System.out.println("[ 고객명 : " + clientDTO.getClientName() +
                    "\t계좌번호 : " + clientDTO.getAccountNumber() +
                    " ]"
            );
            System.out.println("--------------------------------------------------");
            // 해당 계좌의 거래내역만 출력
            List<AccountDTO> list = bankRepository.findAccountByAccount(clientDTO.getAccountNumber());
            // 받아온 list에 정보가 없으면 거래내역 없다 출력
            if (list.isEmpty()){
                System.out.println("거래내역이 없습니다.");
            // 받아온 거래내역에 정보가 입금인지 출력인지 구분, 입금액/출금액 출력
            }else {
                // 1번 선택시 입금내역만 2번선택시 출금내역만
                System.out.println("1.입금내역 | 2.출금내역 | 그외.전체거래내역");
                System.out.print("메뉴선택 > ");
                String menu = scanner.next();
                switch (menu) {
                    case "1":
                        System.out.println("-------------------------입금-------------------------");
                        for (AccountDTO accountDTO : list){
                            if (accountDTO.getDeposit() != 0){
                                System.out.println(("입금\t"+accountDTO.getDeposit()+"원"));
                            }
                        }
                        break;
                    case "2":
                        System.out.println("-------------------------출금-------------------------");
                        for (AccountDTO accountDTO : list){
                            if (accountDTO.getWithdraw() != 0){
                                System.out.println("출금\t"+accountDTO.getWithdraw()+"원");
                            }
                        }
                        break;
                    default:
                        System.out.println("-------------------------전체거래-------------------------");
                        for (AccountDTO accountDTO : list){
                            if (accountDTO.getWithdraw() != 0){
                                System.out.println("출금\t"+accountDTO.getWithdraw()+"원");
                            }else if (accountDTO.getDeposit() != 0){
                                System.out.println(("입금\t"+accountDTO.getDeposit()+"원"));
                            }
                        }
                        break;
                }
            }
            System.out.println("--------------------------------------------------");
            System.out.println("잔액 : " + clientDTO.getBalance());
        }else {
            System.out.println("없는 계좌번호입니다.");
        }
    }

    // 고객 샘플데이터
    public void clinetSampleData() {
        for (int i = 1; i<=5; i++){
            ClientDTO clientDTO = new ClientDTO("인간"+i, "account"+i, "pass"+i);
            bankRepository.saveClinet(clientDTO);
            AccountDTO accountDTO = new AccountDTO(clientDTO.getAccountNumber(), i*1000L, 0L);
            bankRepository.deposit(clientDTO, accountDTO);
        }
    }

    // 모든 계좌목록 출력
    public void findAll() {
        for (ClientDTO clientDTO : bankRepository.findAll()){
            System.out.println(clientDTO);
        }
    }

    // 숫자확인 메서드
    public Long numbercheck(String num){
        long a = 0;
        try {
            a = Integer.parseInt(num);
            return a;
        }catch (Exception e){
            return a;
        }
    }
}