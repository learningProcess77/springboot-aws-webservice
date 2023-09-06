package com.sj.book.web.domain.posts;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class PostsRepositoryTest {
    
    @Autowired
    PostsRepository postsRepository;

    /**
     * 단위 테스트가 끝날 때마다 수행되는 메소드 지정
     * 전체 테스트 수행 시, 테스트간 데이터 침범을 막기 위해 사용
     */
    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오가() {
        // given
        String title = "AWS 배우기";
        String content = "AWS 부분까지 어서 배우고 싶다!";

        postsRepository.save(
                Posts
                        .builder()
                        .title(title)
                        .content(content)
                        .author("surimju")
                        .build()
        );

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }


}