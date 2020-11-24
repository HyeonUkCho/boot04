package com.example.boot04.boot04.persistence;

import com.example.boot04.boot04.domain.PDSBoard;
import com.example.boot04.boot04.domain.PDSFile;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class PDSBoardTests {

    @Autowired
    PDSRepository pdsRepository;

    @Test
    public void testInsertPDS() {
        PDSBoard pdsBoard = new PDSBoard();
        pdsBoard.setPname("Document");

        PDSFile file1 = new PDSFile();
        file1.setPdsfile("file1.doc");

        PDSFile file2 = new PDSFile();
        file2.setPdsfile("file2.doc");

        pdsBoard.setFiles(Arrays.asList(file1, file2));
        log.info("try to save pds");

        pdsRepository.save(pdsBoard);
    }

    @Transactional
    @Test
    public void testUpdateFileName1() {
        Long fno = 1L;
        String newName = "updatedFile1.doc";

        int count = pdsRepository.updatePDSFile(fno, newName);
        log.info("update count: "+ count);
    }

    @Transactional
    @Test
    public void testUpdateFileName2() {

        String newName = "updatedFile2.doc";
        Optional<PDSBoard> result = pdsRepository.findById(2L);

        result.ifPresent(pds ->{
            log.info("data exists, try update");

            PDSFile target = new PDSFile();
            target.setFno(2L);
            target.setPdsfile(newName);

            int idx = pds.getFiles().indexOf(target);

            if(idx > -1) {
                List<PDSFile> list = pds.getFiles();
                list.remove(idx);
                list.add(target);
                pds.setFiles(list);
            }

            pdsRepository.save(pds);
        });
    }

    @Transactional
    @Test
    public void deletePDSFile() {
        Long fno = 2L;
        int count = pdsRepository.deletePDSFile(fno);
        log.info("DELETE PDSFILE: "+ count);
    }

    @Test
    public void insertDummies() {
        List<PDSBoard> list = new ArrayList<>();

        IntStream.range(1,100).forEach(i ->{
            PDSBoard pdsBoard = new PDSBoard();
            pdsBoard.setPname("자료 "+i);

            PDSFile pdsFile1 = new PDSFile();
            pdsFile1.setPdsfile("file1.doc");

            PDSFile pdsFile2 = new PDSFile();
            pdsFile2.setPdsfile("file2.doc");

            pdsBoard.setFiles(Arrays.asList(pdsFile1, pdsFile2));

            list.add(pdsBoard);
        });

        pdsRepository.saveAll(list);
    }

    @Test
    public void viewSummary() {
        pdsRepository.getSummary().forEach(arr -> log.info(Arrays.toString(arr)));
    }

}
