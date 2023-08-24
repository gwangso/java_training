package service;

import dto.UserDTO;
import repository.UserRepository;

import java.util.List;
import java.util.Scanner;

public class UserService {
    Scanner scanner = new Scanner(System.in);

    UserRepository userRepository = new UserRepository();

    public void save(){
        System.out.print("이메일 > ");
        String userEmail = scanner.next();
        System.out.print("비밀번호 > ");
        String userPassword = scanner.next();
        System.out.print("이름 > ");
        String userName = scanner.next();
        System.out.print("전화번호 > ");
        String userMobile = scanner.next();

        UserDTO userDTO = new UserDTO(userEmail,userPassword,userName,userMobile);
        userRepository.save(userDTO);
        System.out.println("회원가입에 성공했습니다.");
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
        UserDTO userDTO = userRepository.findByEmail(userEmail, userPassword);
        if(userDTO != null){
            System.out.println("로그인 성공");
            return userDTO;
        }else {
            System.out.println("로그인 실패");
            return new UserDTO();
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
        UserDTO userDTO = userRepository.findByEmail(userEmail, userPassword);
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
                userRepository.update(userDTO);
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
            System.out.println("ㅅㄱ");
        }else {
            System.out.println("탈퇴를 취소했습니다. 정말 옳은 선택이에요");
        }
    }
}
