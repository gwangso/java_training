package boardService;

import java.util.List;
import java.util.Scanner;

import boardDTO.BoardDTO;
import boardRepository.BoardRepository;

public class BoardService {
    Scanner scanner = new Scanner(System.in);

    BoardRepository boardRepository = new BoardRepository();

    public void findAll(){
        while (true){
            System.out.println();
            System.out.println("-----------------글목록-----------------");
            for (BoardDTO boardDTO : boardRepository.findAll()) {
                boardDTO.print_land();
            }
            System.out.print("조회할 게시글 번호 > ");
            String input = scanner.nextLine();
            if(input == ""){
                System.out.println("조회를 그만두겠습니다.");
                break;
            }
            Long id = numberTest(input);
            if(id!=null){
                BoardDTO boardDTO = boardRepository.findById(id);
                if (boardDTO != null){
                    boardRepository.boardHits(id);
                    System.out.println();
                    System.out.println("-----------------------------------");
                    System.out.println("\t[ " + boardDTO.getBoardTitle() + " ]");
                    System.out.println(" > " + boardDTO.getBoardContents());
                    System.out.println(" - " + boardDTO.getBoardWriter() + " -");
                    System.out.println("-----------------------------------");
                }else {
                    System.out.println("없는 글입니다.");
                }
            }else {
                System.out.println("잘못입력하셨습니다.");
            }
        }
    }

    public void save(){
        System.out.println();
        System.out.println("-----------------글작성-----------------");
        while (true){
            System.out.print("글제목 > ");
            String boardTitle = scanner.nextLine();
            if(boardTitle == ""){
                System.out.println("글작성을 취소합니다.");
                break;
            }
            System.out.print("작성자 > ");
            String boardWriter = scanner.nextLine();
            if(boardWriter == ""){
                boardWriter = "Anonymous";
            }
            System.out.print("비밀번호 > ");
            String boardPass = scanner.nextLine();
            if(boardPass == ""){
                System.out.println("글작성을 취소합니다.");
                break;
            }
            System.out.println("--------------------글내용--------------------");
            String boardContent = scanner.nextLine();
            System.out.print("글을 저장하시겠습니까?(Y/y) > ");
            String save = scanner.nextLine();
            if(save.equals("y") || save.equals("Y") || save.equals("ㅛ")){
                BoardDTO boardDTO = new BoardDTO(boardTitle, boardWriter, boardContent, boardPass);
                boardRepository.save(boardDTO);
                break;
            }else {
                System.out.println("글작성을 취소합니다.");
                break;
            }

        }
    }


    public void update(){
        System.out.println();
        System.out.println("-----------------글수정-----------------");
        while (true) {
            for (BoardDTO boardDTO : boardRepository.findAll()){
                boardDTO.print_land();
            }
            System.out.print("조회할 게시글 번호 > ");
            String input = scanner.nextLine();
            if(input == ""){
                System.out.println("조회를 그만두겠습니다.");
                break;
            }
            Long id = numberTest(input);
            if(id!=null){
                BoardDTO boardDTO = boardRepository.findById(id);
                if (boardDTO != null){
                    System.out.print("수정할 제목 > ");
                    String newTitle = scanner.nextLine();
                    System.out.println("내용 > ");
                    String newContents = scanner.nextLine();
                    if (newTitle == "" && newContents == "") {
                        System.out.println("글수정을 취소합니다.");
                        break;
                    }
                    System.out.print("수정하시겠습니까?(Y/y) > ");
                    String check = scanner.nextLine();
                    if(check.equals("y") || check.equals("Y") || check.equals("ㅛ")){
                        if(newTitle != "") boardDTO.setBoardTitle(newTitle);
                        if(newContents != "") boardDTO.setBoardContents(newContents);
                        System.out.println("수정을 완료했습니다.");
                        break;
                    }else {
                        System.out.println("글 수정을 취소합니다.");
                        break;
                    }
                }else {
                    System.out.println("없는 글입니다.");
                }
            }else {
                System.out.println("잘못입력하셨습니다.");
            }
        }
    }

    public void delete(){
        System.out.println("-----------------글삭제-----------------");
        boolean run = true;
        while (run){
            for (BoardDTO boardDTO : boardRepository.findAll()){
                boardDTO.print_land();
            }
            System.out.print("삭제할 게시글 번호 > ");
            String input = scanner.nextLine();
            if(input == ""){
                System.out.println("삭제를 그만두겠습니다.");
                break;
            }
            Long id = numberTest(input);
            if(id!=null){
                BoardDTO boardDTO = boardRepository.findById(id);
                if (boardDTO != null){
                    int cnt = 0;
                    while (cnt<5){
                        System.out.println("비밀번호를 입력하세요");
                        String password = scanner.nextLine();
                        if (password.equals(boardDTO.getBoardPass())){
                            System.out.print("정말로 삭제하시겠습니까?(Y/y) > ");
                            String check = scanner.nextLine();
                            if(check.equals("y") || check.equals("Y") || check.equals("ㅛ")){
                                boardRepository.delete(boardDTO);
                                System.out.println("삭제가 완료되었습니다.");
                                run = false;
                                break;
                            }else {
                                System.out.println("삭제를 취소합니다.");
                                run = false;
                                break;
                            }
                        }else {
                            System.out.println("비밀번호를 틀렸습니다.("+ ++cnt +"/5)");
                            if (cnt==5) System.out.println("초기화면으로 돌아갑니다.");
                            run =false;
                        }
                    }
                }else {
                    System.out.println("없는 글입니다.");
                }
            }else {
                System.out.println("잘못입력하셨습니다.");
            }
        }
    }

    public void search(){
        System.out.println();
        System.out.println("-----------------검색-----------------");
        System.out.print("제목 > ");
        String boardTitle = scanner.nextLine();
        List<BoardDTO> list = boardRepository.findByTitle(boardTitle);
        if (list.size() != 0){
            for (BoardDTO boardDTO : list){
                boardRepository.boardHits(boardDTO.getId());
                System.out.println("----------------------------------");
                boardDTO.print_land();
                System.out.println("작성글 : " + boardDTO.getBoardContents());
                System.out.println("----------------------------------");
            }
        }else {
            System.out.println("없는 글입니다.");
        }
    }

    public void sampleData(){
        for (int i = 1; i <=10 ; i++){
            BoardDTO boardDTO = new BoardDTO("title"+i, "writer"+i , "contents"+i, ""+i);
            boardRepository.save(boardDTO);
        }
        for (int i = 1; i <=5 ; i++){
            BoardDTO boardDTO = new BoardDTO("title"+i, "writer"+i , "contents"+i, ""+i);
            boardRepository.save(boardDTO);
        }
        for (int i = 1; i <=2 ; i++){
            BoardDTO boardDTO = new BoardDTO("title"+i, "writer"+i , "contents"+i, ""+i);
            boardRepository.save(boardDTO);
        }
    }

    //문자열을 숫자로 변환하되 숫자가 아닌 문자 입력시 null 리턴
    public static Long numberTest(String input) {
        try {
            Long id = Long.parseLong(input);
            return id;
        }catch (Exception e) {
            return null;
        }
    }
}
