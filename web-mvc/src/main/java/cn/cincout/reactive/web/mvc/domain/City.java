package cn.cincout.reactive.web.mvc.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zhaoyu on 19-6-21.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Data
public class City implements Serializable {
    private int id;
    private String name;
}
