package com.xin.control;

import com.xin.model.*;
import com.xin.service.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by golden on 2016/9/7 0007.
 */
@Controller
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private IPetService service;

    @RequestMapping( "/main")
    public String main(){
        return "shop/main.ftl";
    }

    /*通过种类查找产品*/
    @RequestMapping( "/queryProducts/{kindid}")
    public String queryProduct(@PathVariable(value="kindid") String kindid, ModelMap modelMap){
        Map<String,String> map=new HashMap<String,String>();
        map.put("in_kindid",kindid.trim());
        List<Product> products=service.queryProducts(map);

        modelMap.put("products",products);

        return "shop/products.ftl";
    }

    /*通过产品查找宠物们的商品信息*/
    @RequestMapping( "/queryItems/{productid}")
    public String queryItems(@PathVariable(value="productid") String productid, ModelMap modelMap){
        Map<String,String> map=new HashMap<String,String>();
        map.put("in_productid",productid.trim());
        List <Item> items =service.queryItems(map);

        modelMap.put("items",items);

        return "shop/items.ftl";
    }

    /*通过产品查找单个宠物的信息*/
    @RequestMapping( "/queryItem/{itemid}")
    public String queryItem(@PathVariable(value="itemid") String itemid, ModelMap modelMap){
        Map<String,String> map=new HashMap<String,String>();
        map.put("in_itemid",itemid.trim());
        List <Item> items =service.queryItems(map);

        modelMap.put("item",items.get(0));

        return "shop/item.ftl";
    }

    //将商品添加到购物车
    @RequestMapping("/addCart/itemid/{itemid}/quantity/{quantity}" )
    public String addCart(@PathVariable(value="itemid") String itemid,
                          @PathVariable(value="quantity") String quantity,
                          ModelMap m){
        Map map=new HashMap();
        map.put("in_itemid",itemid.trim());
        map.put("in_quantity",quantity.trim());
        List <Cart> cartList =service.addCart(map);
        m.put("cartList",cartList);
        return "shop/cart.ftl";
    }

    @RequestMapping("/deleteCart/itemid/{itemid}/orderid/{orderid}")
    public String deleteCart(
            @PathVariable(value="itemid") String itemid,
            @PathVariable(value="orderid") String orderid,
            ModelMap m
    ) {
        CartKey cartKey = new CartKey();
        cartKey.setItemid(itemid);
        cartKey.setOrderid(orderid);
        List<Cart> cartList = service.deleteCart(cartKey);
        m.put("cartList",cartList);
        return "shop/cart.ftl";
    }

    @RequestMapping("/submitCart/orderid/{orderid}/totalPrice/{totalPrice}")
    public String submitCart(
            @PathVariable(value="orderid") String orderid,
            @PathVariable(value="totalPrice") BigDecimal toalPrice,
        ModelMap m
    ) {
        Orders order = new Orders();
        order.setOrderid(orderid);
        order.setTotalprice(toalPrice);
        order.setOrderdate(new Date());
        service.submitCart(order);
        return "shop/main.ftl";
    }

    @RequestMapping(value="/updateCart" )
    public String updateCart(
            @RequestParam(value="itemList") String[] itemList,
            @RequestParam(value="orderList") String[]  orderList,
            @RequestParam(value="quantityList") String[]  quantityList,
            ModelMap m){
        Map<String,String> map=new HashMap<String,String>();
        for( int i=0;i<itemList.length;i++){
            map.put("in_orderid",orderList[i]);
            map.put("in_itemid",itemList[i]);
            map.put("in_quantity",quantityList[i]);
            service.updateCart(map);
        }
        CartKey cartKey =new CartKey();
        cartKey.setOrderid(orderList[0]);
        List <Cart> cartList =service.queryCart(cartKey);
        m.put("cartList",cartList);
        return "shop/cart.ftl";

    }

}
