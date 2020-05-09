package com.beyond.hello.spring.boot.response;

import com.beyond.hello.spring.boot.entity.TbUser;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserResponse extends PagingAndSortingRepository<TbUser, Long> {
}
