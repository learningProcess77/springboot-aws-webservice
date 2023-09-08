package com.sj.book.web;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void 메인페이지_로딩() {
        // when
        String body = this.restTemplate.getForObject("/", String.class);

        System.out.println("##### body : " + body);
        /* 결과
            <html>
            <head>
                <title>스프링 부트 웹서비스</title>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            </head>
            
            <body>
                <h1>스프링 부트로 시작하는 웹 서비스</h1>
            </body>
            </html>
         */

        // then
        assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");

    }

}