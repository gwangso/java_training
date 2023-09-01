package boardDTO;

public class BoardDTO {
    private Long id;
    private String boardTitle;
    private String boardWriter;
    private String boardContents;
    private String boardPass;
    private long boardHits = 0;
    private static Long num = 1L;

    public BoardDTO() {
    }

    public BoardDTO(String boardTitle, String boardWriter, String boardContents, String boardPass) {
        this.id = num++;
        this.boardTitle = boardTitle;
        this.boardWriter = boardWriter;
        this.boardContents = boardContents;
        this.boardPass = boardPass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getBoardWriter() {
        return boardWriter;
    }

    public void setBoardWriter(String boardWriter) {
        this.boardWriter = boardWriter;
    }

    public String getBoardContents() {
        return boardContents;
    }

    public void setBoardContents(String boardContents) {
        this.boardContents = boardContents;
    }

    public String getBoardPass() {
        return boardPass;
    }

    public void setBoardPass(String boardPass) {
        this.boardPass = boardPass;
    }

    public Long getBoardHits() {
        return boardHits;
    }

    public void setBoardHits(Long boardHits) {
        this.boardHits = boardHits;
    }

    @Override
    public String toString() {
        return "BoardDTO{" +
                "id=" + id +
                ", boardTitle='" + boardTitle + '\'' +
                ", boardWriter='" + boardWriter + '\'' +
                ", boardPass='" + boardPass + '\'' +
                ", boardHits='" + boardHits + '\'' +
                '}';
    }
    
    public void print_land(){
        System.out.println(
                "[ 글 번호 : " + id +
                ", 글 제목 : " + boardTitle + " | " +
                ", 작성자 : " + boardWriter + " | " +
                ", 조회수 : " + boardHits + " ]"
        );
    }
    
}