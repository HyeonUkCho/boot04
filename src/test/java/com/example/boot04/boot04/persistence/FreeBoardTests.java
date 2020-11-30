package com.example.boot04.boot04.persistence;

import com.example.boot04.boot04.domain.FreeBoard;
import com.example.boot04.boot04.domain.FreeBoardReply;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class FreeBoardTests {

    @Autowired
    FreeBoardRepository freeBoardRepository;

    @Autowired
    FreeBoardReplyRepository freeBoardReplyRepository;

    @Test
    public void insertDummy() {
        IntStream.range(1,200).forEach(i-> {
            FreeBoard freeBoard = new FreeBoard();
            freeBoard.setTitle("Free Board ... " + i);
            freeBoard.setContent("Free Content ... " + i);
            freeBoard.setWriter("user" + i%10);

            freeBoardRepository.save(freeBoard);
        });
    }

    @Transactional
    @Test
    public void insertReply2Way() {
        Optional<FreeBoard> result = freeBoardRepository.findById(199L);
        result.ifPresent(board -> {
            List<FreeBoardReply> replies = board.getReplies();
            FreeBoardReply freeBoardReply = new FreeBoardReply();
            freeBoardReply.setReply("REPLY.........");
            freeBoardReply.setReplyer("replyer00");
            freeBoardReply.setBoard(board);

            replies.add(freeBoardReply);

            board.setReplies(replies);

            freeBoardRepository.save(board);
        });
    }

    @Test
    public void inserReply1Way() {
        FreeBoard freeBoard = new FreeBoard();
        freeBoard.setBno(199L);

        FreeBoardReply freeBoardReply = new FreeBoardReply();
        freeBoardReply.setReply("REPLY........");
        freeBoardReply.setReplyer("replyer00");
        freeBoardReply.setBoard(freeBoard);

        freeBoardReplyRepository.save(freeBoardReply);
    }

    @Test
    public void testList1() {
        Pageable page = PageRequest.of(0,10, Sort.Direction.DESC, "bno");
        freeBoardRepository.findByBnoGreaterThan(0L, page).forEach(freeBoard -> {
            log.info(freeBoard.getBno() + ": " + freeBoard.getTitle());
        });
    }

//    @Test
//    public void testList2() {
//        Pageable page = PageRequest.of(0,10, Sort.Direction.DESC, "bno");
//        freeBoardRepository.findByBnoGreaterThan(0L, page).forEach(freeBoard -> {
//            log.info(freeBoard.getBno() + ": " + freeBoard.getTitle() + ":" + freeBoard.getReplies().size());
//        });
//    }

    @Test
    public void testList3() {
        Pageable page = PageRequest.of(0,10, Sort.Direction.DESC, "bno");
        freeBoardRepository.getPage(page).forEach(arr -> {
            log.info(Arrays.toString(arr));
        });
    }
}
