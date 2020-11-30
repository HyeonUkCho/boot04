package com.example.boot04.boot04.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter
@ToString(exclude = "board")
@Entity
@Table(name = "tbl_free_replies", indexes = {@Index(unique = false,columnList = "board_bno")} )
@EqualsAndHashCode(of="rno")
public class FreeBoardReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    private String reply;
    private String replyer;

    @CreationTimestamp
    private Timestamp regdate;
    @UpdateTimestamp
    private Timestamp updatedate;

    @ManyToOne
    private FreeBoard board;
}
