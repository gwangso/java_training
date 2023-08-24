package repository;

import dto.StudentDTO;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    // 학생정보를 저장할 리스트
    private List<StudentDTO> studentDTOList = new ArrayList<>();

    public boolean save(StudentDTO studentDTO) {
        // id 값을 하나씩 증가시켜 함께 저장
        // 현재 리스트에 저장된 학생수 가져오고 1 증가하여 저장
        int size = studentDTOList.size() + 1;
        studentDTO.setId((long) size);

        // 리스트에 신규학생 추가
        return studentDTOList.add(studentDTO);  // add 메서드는 성공하면 true 실패하면 false 반환
    }

    public List<StudentDTO> findAll() {
        return studentDTOList;
    }

    public boolean delete(StudentDTO studentDTO){
        return studentDTOList.remove(studentDTO);
    }
}