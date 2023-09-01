package repository;

import DTO.BoardDTO;
import DTO.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class BoardRepository {

    private List<BoardDTO> boardDTOList = new ArrayList<>();

    public List<BoardDTO> findAll(){
        return boardDTOList;
    }

    public void save(BoardDTO boardDTO){
        boardDTOList.add(boardDTO);
    }

    public BoardDTO findById(Long id,UserDTO userDTO){
        for (BoardDTO boardDTO : boardDTOList){
            if(boardDTO.getId().equals(id) && userDTO.getUserEmail().equals(boardDTO.getBoardUserEmail())){
                return boardDTO;
            }
        }
        return null;
    }

    public void boardHits(long id){
        for (BoardDTO boardDTO : boardDTOList){
            if(boardDTO.getId().equals(id)){
                boardDTO.setBoardHits(boardDTO.getBoardHits()+1);
            }
        }
    }

    public void delete(BoardDTO boardDTO){
        for (BoardDTO boardDTO1 : boardDTOList){
            if (boardDTO == boardDTO1){
                boardDTOList.remove(boardDTO1);
                break;
            }
        }
    }

    public void allDelete(UserDTO userDTO){
        for (BoardDTO boardDTO : boardDTOList){
            String boardEmail = boardDTO.getBoardUserEmail();
            String userEmail = userDTO.getUserEmail();
            System.out.println(boardEmail);
            System.out.println(userEmail);
            if(boardEmail.equals(userEmail)){
                boardDTOList.remove(boardDTO);
            }
        }
    }

    public List<BoardDTO> findByTitle(String boardTitle){
        List<BoardDTO> list = new ArrayList<>();
        for (BoardDTO boardDTO1 : boardDTOList){
            if (boardTitle.equals(boardDTO1.getBoardTitle())){
                list.add(boardDTO1);
            }
        }
        return list;
    }

    public List<BoardDTO> findMyWrite(UserDTO userDTO){
        List<BoardDTO> list = new ArrayList<>();
        for (BoardDTO boardDTO : boardDTOList){
            if (userDTO.getUserEmail().equals(boardDTO.getBoardUserEmail())){
                list.add(boardDTO);
            }
        }
        return list;
    }
}
