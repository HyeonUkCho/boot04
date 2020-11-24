package com.example.boot04.boot04.persistence;

import com.example.boot04.boot04.domain.PDSBoard;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PDSRepository extends CrudRepository<PDSBoard, Long> {

    @Modifying
    @Query("UPDATE FROM PDSFile f set f.pdsfile = ?2 WHERE f.fno = ?1 ")
    public int updatePDSFile(Long fno, String newFilename);

    @Modifying
    @Query("DELETE FROM PDSFile f where f.fno = ?1 ")
    public int deletePDSFile(Long fno);

    @Query("SELECT p, count(f) FROM PDSBoard p LEFT OUTER JOIN p.files f WHERE p.pid > 0 GROUP BY p ORDER BY p.pid DESC ")
    public List<Object[]> getSummary();
}
