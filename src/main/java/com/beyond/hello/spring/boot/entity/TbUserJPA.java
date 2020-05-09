package com.beyond.hello.spring.boot.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/*
 * 这个接口似乎不用也可以，全部注释掉，也没发现有影响
 */
public interface TbUserJPA extends
        Serializable,
        JpaRepository<TbUser, Integer>,
        JpaSpecificationExecutor<TbUser> {
}
