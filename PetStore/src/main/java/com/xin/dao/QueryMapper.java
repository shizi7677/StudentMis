package com.xin.dao;

import com.xin.model.Item;
import com.xin.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by golden on 2016/9/8 0008.
 */
@Repository
public interface QueryMapper {
    List<Product> queryProducts(Map map);
    List<Item> queryItems(Map map);
}
