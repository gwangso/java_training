package service;

import dto.UserDTO;
import repository.UserRepository;

import java.util.List;
import java.util.Scanner;

public class UserService {
    Scanner scanner = new Scanner(System.in);

    UserRepository userRepository = new UserRepository();

    private static UserDTO loginUserDTO = null;

    public UserDTO getLoginUserDTO() {
        return loginUserDTO;
    }

    public void setLoginUserDTO(UserDTO loginUserDTO) {
        UserService.loginUserDTO = loginUserDTO;
    }

    public void save(){
        boolean run = true;
        while (run){
            System.out.print("이메일 > ");
            String userEmail = scanner.nextLine();
            if(userEmail == "") {
                System.out.println("회원가입을 종료합니다.");
                break;
            }
            if(userRepository.findEmail(userEmail)){
                System.out.println("이미 존재하는 이메일입니다.");
                continue;
            }

            System.out.print("비밀번호 > ");
            String userPassword = scanner.nextLine();
            if(userPassword == "") {
                System.out.println("회원가입을 종료합니다.");
                break;
            }
            System.out.print("이름 > ");
            String userName = scanner.nextLine();
            if(userName == "") {
                System.out.println("회원가입을 종료합니다.");
                break;
            }
            System.out.print("전화번호 > ");
            String userMobile = scanner.nextLine();
            if(userMobile == "") {
                System.out.println("회원가입을 종료합니다.");
                break;
            }
            UserDTO userDTO = new UserDTO(userEmail,userPassword,userName,userMobile);
            userRepository.save(userDTO);
            System.out.println("회원가입에 성공했습니다.");
            run = false;
        }
    }

    public void findAll(){
        for(UserDTO userDTO : userRepository.findAll()){
            System.out.println(userDTO.toString());
        }
    }

    public UserDTO login(){
        System.out.print("Eamil > ");
        String userEmail = scanner.next();
        System.out.print("Password > ");
        String userPassword = scanner.next();
        UserDTO userDTO = userRepository.login(userEmail, userPassword);
        if(userDTO != null){
            System.out.println("로그인 성공");
            loginUserDTO = userDTO;
            return userDTO;
        }else {
            System.out.println("로그인 실패");
            return null;
        }
    }

    public void delete(){
        System.out.print("회원id > ");
        long id = scanner.nextLong();
        UserDTO userDTO =  userRepository.findById(id);
        if (userDTO != null){
            userRepository.delete(id);
            System.out.println("회원 삭제를 완료했습니다.");
        }else {
            System.out.println("없는 회원입니다.");
        }
    }

    public void findUserInfo(UserDTO userDTO){
        System.out.println(userRepository.findById(userDTO.getUserId()).toString());

    }

    public void update(){
        System.out.print("Eamil > ");
        String userEmail = scanner.next();
        System.out.print("Password > ");
        String userPassword = scanner.next();
        UserDTO userDTO = userRepository.login(userEmail, userPassword);
        if(userDTO != null){
            System.out.print("전화번호 수정 > ");
            String userMobile = scanner.next();
            System.out.print("해당 전화번호("+userMobile+")로 수정하시겠습니까?(Y/y) > ");
            String check = scanner.next();
            if(check.equals("y") || check.equals("Y") || check.equals("ㅛ")){
                userDTO.setUserMobile(userMobile);
                userRepository.update(userDTO);
                System.out.println("정보수정이 완료되었습니다.");
            }else {
                System.out.println("정보수정을 취소합니다.");
            }
        }else {
            System.out.println("아이디/비밀번호가 틀렸습니다.");
        }
    }
    public void update(UserDTO userDTO){
        System.out.print("Eamil > ");
        String userEmail = scanner.next();
        System.out.print("Password > ");
        String userPassword = scanner.next();
        if(userDTO.getUserPassword().equals(userPassword) && userDTO.getUserEmail().equals(userEmail)){
            System.out.print("전화번호 수정 > ");
            String userMobile = scanner.next();
            System.out.print("해당 전화번호("+userMobile+")로 수정하시겠습니까?(Y/y) > ");
            String check = scanner.next();
            if(check.equals("y") || check.equals("Y") || check.equals("ㅛ")){
                userDTO.setUserMobile(userMobile);
                // 이미 setUserMobile에서 값을 변경해줬다
                // 객체는 주소를 이용해 값을 출력한다.
                // 해당 주소의 값이 변경되면 기존에 그 주소를 바라보고 있던 다른 객체도 변경된 값을 출력하게 된다.
//                userRepository.update(userDTO);
                System.out.println("정보수정이 완료되었습니다.");
            }else {
                System.out.println("정보수정을 취소합니다.");
            }
        }else {
            System.out.println("아이디/비밀번호가 틀렸습니다.");
        }
    }

    public void withdraw(UserDTO userDTO){
        System.out.print("정말로 탈퇴하시겠습니까?(Y/y) > ");
        String check = scanner.next();
        if(check.equals("y") || check.equals("Y") || check.equals("ㅛ")){
            userRepository.delete(userDTO.getUserId());
            System.out.println("수고하셨습니다.");
        }else {
            System.out.println("탈퇴를 취소했습니다. 정말 옳은 선택이에요");
        }
    }
}
