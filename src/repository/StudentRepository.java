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

    public StudentDTO findById(long id){
        for (StudentDTO studentDTO : studentDTOList) {
            if (id == studentDTO.getId()) {
                return studentDTO;
            }
        }
        return null;
    }

    public StudentDTO delete(Long id){
        for (int i = 0; i<studentDTOList.size(); i++){
            if (id == studentDTOList.get(i).getId()){
                return studentDTOList.remove(i);
            }
        }
//        for (StudentDTO studentDTO : studentDTOList) {
//            if (id == studentDTO.getId()) {
//            }
//        }
        return null;
    }

    public void update(StudentDTO studentDTO){
        for (int i = 0; i<studentDTOList.size(); i++){
            if (studentDTO.getId() == studentDTOList.get(i).getId()){
                 studentDTOList.set(i, studentDTO);
            }
        }

//        for (StudentDTO studentDTO1 : studentDTOList){
//            if (studentDTO1.getId() == studentDTO.getId()){
//                studentDTO1 = studentDTO;
//            }
//        }

//        for (StudentDTO studentDTO1 : studentDTOList){
//            if (studentDTO1.getId() == studentDTO.getId()){
//                  studentDTO1.setStudentMobile(studentDTO.getStudentMobile());
//
//            }
//        }
    }
}