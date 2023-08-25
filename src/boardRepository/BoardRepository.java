package boardRepository;

import boardDTO.BoardDTO;

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

    public BoardDTO findById(Long id){
        for (BoardDTO boardDTO : boardDTOList){
            if(boardDTO.getId().equals(id)){
                return boardDTO;
            }
        }
        return null;
    }

    public void delete(BoardDTO boardDTO){
        for (BoardDTO boardDTO1 : boardDTOList){
            if (boardDTO == boardDTO1){
                boardDTOList.remove(boardDTO1);
                break;
            }
        }
    }

    public BoardDTO findByTitle(String boardTitle){
        for (BoardDTO boardDTO1 : boardDTOList){
            if (boardTitle.equals(boardDTO1.getBoardTitle())){
                return boardDTO1;
            }
        }
        return null;
    }
}
