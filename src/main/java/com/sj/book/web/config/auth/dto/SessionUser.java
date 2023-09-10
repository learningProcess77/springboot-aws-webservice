package com.sj.book.web.config.auth.dto;

import com.sj.book.web.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

/**
 * User 가 아닌, 별도의 SessionUser 클래스를 구현하는 이유
 *  - User 클래스에 직렬화를 구현하지 않기 위함임
 *  - User 클래스는 엔티티 이기 때문에, 다른 엔터티와의 관계 형성 (@ManyToMany, @OneToMany 등) 가능성이 열려있음
 *      다른 자식 엔터티를 갖고 있다면, 직렬화 대상에 자식들까지 포함되므로 성능 이슈, 부수 효과 발생 가능성이 높음
 *      따라서, 직렬화 긴ㅇ을 가진 세션 Dto 를 하나 추가로 만드는 것이 운영 및 유지보수에 유리
 * 
 */

@Getter
public class SessionUser implements Serializable {
    
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
