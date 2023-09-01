package repository;

import DTO.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private List<UserDTO> userDTOList = new ArrayList<>();

    public void save(UserDTO userDTO){
        userDTOList.add(userDTO);
    }
    public List<UserDTO> findAll() {
        return userDTOList;
    }

    public UserDTO findById(Long userId){
        for (UserDTO userDTO : userDTOList){
            if (userId == userDTO.getUserId()){
                return userDTO;
            }
        }
        return null;
    }

    public UserDTO login(String userEmail, String userPassword){
        for (UserDTO userDTO : userDTOList){
            if(userEmail.equals(userDTO.getUserEmail())){
                if (userDTO.getUserPassword().equals(userPassword)) {
                    return userDTO;
                }
            }
        }
        return null;
    }

    public boolean findEmail(String userEmail){
        boolean find = false;
        for (UserDTO userDTO : userDTOList){
            if(userDTO.getUserEmail().equals(userEmail)){
                find = true;
                break;
            }
        }
        return find;
    }

    public void delete(long id){
        for (UserDTO userDTO : userDTOList){
            if(userDTO.getUserId() == id){
                userDTOList.remove(userDTO);
                break;
            }
        }
    }

    public void update(UserDTO userDTO){
        for (UserDTO userDTO1 : userDTOList){
            if (userDTO.getUserId() == userDTO1.getUserId()){
                userDTO1 = userDTO;
                // 의미없는 문장이다.
                break;
            }
        }
    }
}
