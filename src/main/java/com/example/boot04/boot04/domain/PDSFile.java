package com.example.boot04.boot04.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.convert.DataSizeUnit;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name="tbl_pdsfile")
@EqualsAndHashCode(of="fno")
public class PDSFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;
    private String pdsfile;
}
