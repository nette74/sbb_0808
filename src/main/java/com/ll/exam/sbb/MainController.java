package com.ll.exam.sbb;

import org.apache.catalina.session.StandardSession;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    static int increasenum=0;
    @RequestMapping("/sbb")
    @ResponseBody
    public String index(){

        //서버에서 실행
        System.out.println("index가 실행되고, 콘솔출력합니다.");

        //미래에 브라우저가 받아서 실행.
        return "index 가 변경되었습니다. 22342";
    }


    @GetMapping("/page1")
    @ResponseBody
    public String showGet() {

        return """
                <form method="POST" action="page2">
                    <input type="number" name="age">
                    <input type="submit">
                </form>
                """;
    }
    @PostMapping("/page2")
    @ResponseBody
    public String showPage2Post(@RequestParam(defaultValue = "0") int age){

        return "입력된 나이:%d".formatted(age);
    }


    @GetMapping("/plus")
    @ResponseBody
    public String showPlus(){

        return """
                <form method="POST" action="plus">
                    <input type="number" name="num1"/>
                    <input type="number" name="num2"/>
                    <input type="submit"/>
                </form>
                """;
    }

    @PostMapping("/plus")
    @ResponseBody
    public String postPlus(int num1,int num2){

        int result = num1+num2;
        return """
                %d
                """.formatted(result);
    }

    @GetMapping("/minus")
    @ResponseBody
    public String showMinus(){

        return """
                <form method="POST" action="minus">
                    <input type="number" name="num1"/>
                    <input type="number" name="num2"/>
                    <input type="submit"/>
                </form>
                """;
    }
    @PostMapping("/minus")
    @ResponseBody
    public String postMinus(int num1,int num2){

        int result = num1-num2;
        return """
                %d
                """.formatted(result);
    }





    @GetMapping("/increase")
    @ResponseBody
    public String showIncrease(){
        increasenum++;
        return """
                %d
                """.formatted(increasenum);
    }


    @GetMapping("/mbti")
    @ResponseBody
    public String showMbti(@RequestParam(defaultValue = "") String nameStr){

        if(nameStr.isEmpty())
        return """
                <form method="GET" action="mbti">
                    <input type="text" name="nameStr"/>
                    <input type="submit"/>
                </form>
                """;
        else
            return "ESFP";
    }

    @GetMapping("/saveSessionAge/{num}")
    @ResponseBody
    public String saveSession(@PathVariable int num, HttpServletRequest req){
        HttpSession session = req.getSession();
        session.setAttribute("number",num);
        return "세션 등록 완료";
    }
    @GetMapping("/showSessionAge")
    @ResponseBody
    public String showSession(HttpServletRequest req){
        HttpSession session = req.getSession();
        return session.getAttribute("number")+"";
    }


}
