<#include "commons/top.ftl" encoding="utf-8" parse="true" >

<table border="0" width="100%" cellspacing="0" cellpadding="0">
    <tr>
        <td valign="top" width="20%" align="left">
            <table align="left" bgcolor="#008800" border="0" cellspacing="2"
                   cellpadding="2">
                <tr>
                    <td bgcolor="#FFFF88">
                        <a href="${project_name}/pet/main"><b><font color="BLACK" size="2">&lt;&lt; 主菜单</font>
                        </b></a>
                    </td>
                </tr>
            </table>
        </td>
        <td valign="top" align="center">
            <h2 align="center">
                购物清单：
            </h2>

            <#--在提交时，将用户更改的信息提交到后台-->
            <form name="form1" method="post"
                  action="${project_name}/pet/updateCart">

                <table align="center" bgcolor="#008800" border="0" cellspacing="2"
                       cellpadding="5">
                    <tr bgcolor="#cccccc">
                        <td>
                            产品编号
                        </td>
                        <td>
                            宠物编号
                        </td>
                        <td>
                            是否有库存
                        </td>
                        <td>
                            宠物描述
                        </td>
                        <td>
                            购买数量
                        </td>
                        <td>
                            宠物单价
                        </td>
                        <td>
                            合计
                        </td>
                        <td>
                            操作
                        </td>
                    </tr>

                <#assign  totalPrice=0/>
                <#assign  orderid=""/>
                <#list  cartList as cart>
                    <tr bgcolor="#FFFF88">
                        <td>
                            <b>
                                <a href="">${cart.item.productid}</a>
                            </b>
                        </td>
                        <td>
                        ${cart.itemid}
                            <#--设置隐藏的变量，为了修改购物车信息时使用-->
                            <input type="hidden" name="itemList" value="${cart.itemid}">
                            <input type="hidden" name="orderList" value="${cart.orderid}">
                            <#assign  orderid="${cart.orderid}"/>
                        </td>
                        <td>
                        ${cart.item.status}
                        </td>
                        <td align="center">
                        ${cart.item.description}
                        </td>
                        <td>
                            <#--这样前端页面中会循环输入名为quantityList的值； 同名不同值；这样后台就可以采用数组的形式来取得各个值-->
                            <input type="number" name="quantityList" value="${cart.quantity}"/>
                        </td>
                        <td align="right">
                        ${cart.item.unitcost}
                        </td>
                        <td align="right">
                        ${cart.item.unitcost * cart.quantity}  <#--计算合计-->
							<#assign  totalPrice= totalPrice+cart.item.unitcost * cart.quantity/> <#--计算总计-->
                        </td>
                        <td align="right">
                            <a href="${project_name}/pet/deleteCart/itemid/${cart.itemid}/orderid/${cart.orderid}"">
                            <img border="0" src="${project_name}/images/button_remove.gif"/>
                            </a>
                        </td>
                    </tr>
                </#list>
                    <tr bgcolor="#FFFF88">
                        <td colspan="7" align="right">
                            <b>
                                总计：${totalPrice}
                            </b>
                        </td>
                        <td>
                            <#--确定提交修改信息按钮-->
                            <input type="image" src="${project_name}/images/button_update_cart.gif">
                        </td>
                    </tr>
                </table>
            </form>

            <center>
                 <#--确定提交订单的按钮-->
                <a href="${project_name}/pet/submitCart/orderid/${orderid}/totalPrice/${totalPrice}">
                    <img border="0" src="${project_name}/images/button_checkout.gif"/>
                </a>
            </center>
        </td>
    </tr>
</table>
<br>

<#include "commons/banner.ftl" encoding="utf-8" parse="true" >
<#include "commons/bottom.ftl" encoding="utf-8" parse="true" >
