package com.beyond.hello.spring.boot.response;

import com.beyond.hello.spring.boot.entity.TbUser;
import org.springframework.data.repository.PagingAndSortingRepository;

/*
 * JPA分页用的接口接口，调用JPA提供的查询语句
 */
public interface UserJpaResponse extends PagingAndSortingRepository<TbUser, Long> {
}
