package main;
import boardDTO.BoardDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Test {
    public static void main(String[] args) {
        List<BoardDTO> list = new ArrayList<>();
        System.out.println(list.get(0));
        for (BoardDTO boardDTO : list){
            System.out.println(boardDTO.getId());
        }

    }
}
