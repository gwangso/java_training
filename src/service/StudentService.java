package service;

import dto.StudentDTO;
import repository.StudentRepository;

import java.util.List;
import java.util.Scanner;

public class StudentService {
    Scanner scanner = new Scanner(System.in);
    StudentRepository studentRepository = new StudentRepository();
    // 학생등록 메서드
    // 매개변수 없음.
    // 리턴 없음.
    // 메서드 이름: save()
    // 실행내용
    // 스캐너 이름(studentName), 학과(studentMajor), 전화번호(studentMobile)를 입력받고
    // 입력값을 DTO객체에 담아서 StutdentRepository의 save() 메서드로 전달
    public void save(){
        System.out.println("학생등록");
        System.out.print("이름> ");
        String studentName = scanner.next();
        System.out.print("학과> ");
        String studentMajor = scanner.next();
        System.out.print("전화번호> ");
        String studentMobile = scanner.next();
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentName(studentName);
        studentDTO.setStudentMajor(studentMajor);
        studentDTO.setStudentMobile(studentMobile);

        boolean result = studentRepository.save(studentDTO);
        if(result){
            System.out.println("등록성공");
        } else {
            System.out.println("등록실패");
        }
    }

    /*  findAll() 메서드
        Main에서 3번 선택시 호출되는 메서드
        매개변수 리턴 없음
        실행내용
          - Repository의 findAll 메서드를 호출하여 리스트를 리턴받음.
          - 리턴받은 리스트에 담긴 데이터를 for문을 이용하여 출력함.
    * */
    public void findAll(){
        for(StudentDTO studentDTO : studentRepository.findAll()){
            System.out.println(studentDTO.toString());
        }
    }

    public void delete(){
        System.out.println("학생삭제");
        System.out.println("삭제할 이름> ");
        String deleteName = scanner.nextLine();
        boolean findStudent = false;
        for (StudentDTO studentDTO : studentRepository.findAll()){
            if (studentDTO.getStudentName().equals(deleteName)) {
                findStudent = true;
                System.out.println("정말로 삭제하시겠습니까(Y,y) ?>");
                String check = scanner.next();
                if (check.equals("ㅛ") || check.equals("y") || check.equals("Y")) {
                    studentRepository.delete(studentDTO);
                } else {
                    System.out.println("삭제를 취소합니다.");
                }
            }
        }
        if(!findStudent){
            System.out.println("학생을 찾을 수 없습니다.");
        }
    }

}