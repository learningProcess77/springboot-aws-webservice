package com.sj.book.web.domain.posts;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 *  1. MappedSuperclass
 *      - Jpa Entity 클래스들이 해당 클래스를 상속할 경우 필드들도 칼럼으로 인식하도록 함
 *      - Entity 에서 하위 클래스들이 상위 클래스의 모든 필드를 자신의 '컬럼'으로 인식하게 함
 *  
 *  2. EntityListeners(AuditingEntityListener.class) 
 *      - 해당 클래스에 Auditing 기능을 포함시킴
 * 
 */


@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    
    @CreatedDate
    private LocalDateTime createdDate;
    
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
