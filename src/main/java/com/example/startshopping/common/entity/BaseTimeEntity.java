package com.example.startshopping.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

//jpa에게 해당 entity는 auditing 기능을 사용한다는 것을 알리는 어노테이션이다
@EntityListeners(value = {AuditingEntityListener.class})//엔티티들이 변동이 생겼다 그러면 자동으로 감지
@MappedSuperclass //상속받는 클래스에게 매핑 정보만 제공하는 클래스
@Getter
@Setter
public abstract class BaseTimeEntity { //추상클래스로 만든다

    @CreatedDate
    @Column(updatable = false) // 수정할수 없다
    private LocalDateTime regDate; //생성시간

    @LastModifiedDate
    private LocalDateTime updateTime; //수정시간


}