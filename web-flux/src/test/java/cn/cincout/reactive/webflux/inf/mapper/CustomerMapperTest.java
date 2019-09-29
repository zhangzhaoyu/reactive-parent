package cn.cincout.reactive.webflux.inf.mapper;

import cn.cincout.reactive.webflux.domain.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Created by zhaoyu on 19-9-26.
 *
 * @author zhaoyu
 * @sine 1.8
 */

@SpringBootTest
@ExtendWith({SpringExtension.class})
class CustomerMapperTest {
    @Autowired
    private CustomerMapper customerMapper;

    @Test
    void countByExample() {
    }

    @Test
    void deleteByExample() {
    }

    @Test
    void deleteByPrimaryKey() {
    }

    @Test
    void insert() {
        Customer customer = Customer.builder()
                .name("xiaoming")
                .phonenumber("18511929999")
                .build();

        int result = customerMapper.insert(customer);
        Assertions.assertEquals(1, result);
    }

    @Test
    void insertSelective() {
    }

    @Test
    void insertBatch() {
    }

    @Test
    void selectByExample() {
    }

    @Test
    void selectByPrimaryKey() {
    }

    @Test
    void updateByExampleSelective() {
    }

    @Test
    void updateByExample() {
    }

    @Test
    void updateByPrimaryKeySelective() {
    }

    @Test
    void updateByPrimaryKey() {
    }
}