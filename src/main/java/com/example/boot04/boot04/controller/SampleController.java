package com.example.boot04.boot04.controller;

import com.example.boot04.boot04.domain.Member;
import com.example.boot04.boot04.domain.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class SampleController {

    @GetMapping("/sample1")
    public void sample1(Model model) {
        model.addAttribute("greeting", "안녕하세요.");
    }

    @GetMapping("/sample2")
    public void sample2(Model model) {
        MemberVO memberVO = new MemberVO(123, "u00", "p00", "honggildong", new Timestamp(System.currentTimeMillis()));
        model.addAttribute("memberVO", memberVO);
    }

    @GetMapping("/sample3")
    public void sample3(Model model) {
        List<MemberVO> list = new ArrayList<>();

        for(int i=0; i<10; i++) {
            list.add(new MemberVO(123,"u0"+i, "p0"+i, "hong"+i, new Timestamp(System.currentTimeMillis())));
        }
        model.addAttribute("list", list);
    }

    @GetMapping("/sample4")
    public void sample4(Model model) {
        List<MemberVO> list = new ArrayList<>();

        for(int i=0; i<10; i++) {
            list.add(new MemberVO(
                    i,
                    "u000" + i%3,
                    "p0000" + i%3,
                    "honggildong" + i,
                    new Timestamp(System.currentTimeMillis())
            ));
        }

        model.addAttribute("list", list);
    }

    @GetMapping("/sample7")
    public void sample7(Model model) {
        model.addAttribute("now", new Date());
        model.addAttribute("price", 123456789);
        model.addAttribute("title", "This is a just sample");
        model.addAttribute("options", Arrays.asList("AAA","BBBB","CCCC","DDDD"));

    }

    @GetMapping("/sample8")
    public void sample8(Model model) {

    }

    @GetMapping("/sample/hello")
    public void hello() {

    }
}
