<#include "commons/top.ftl" encoding="utf-8" parse="true" >

<table align="left" bgcolor="#008800" border="0" cellspacing="2"
       cellpadding="2">
    <tr>
        <td bgcolor="#FFFF88">
            <a href="${project_name}/pet/main"><b><font color="BLACK" size="2">&lt;&lt; 主菜单</font>
            </b></a>
        </td>
    </tr>
</table>

<p>
<center>
    <h2>
        具体宠物信息
    </h2>
</center>
<table align="center" bgcolor="#008800" border="0" cellspacing="2"
       cellpadding="3">
    <tr bgcolor="#CCCCCC">
        <td>
            <img src="${project_name}/images/${item.product.picture}">
        </td>
    </tr>

    <tr bgcolor="#FFFF88">
        <td width="100%" bgcolor="#cccccc">
            宠物编号：${item.itemid}
        </td>
    </tr>

    <tr bgcolor="#FFFF88">
        <td width="100%" bgcolor="#cccccc">
            产品编号：${item.productid}
        </td>
    </tr>
    <tr bgcolor="#FFFF88">
        <td>
            单价：${item.unitcost}
        </td>
    </tr>
    <tr bgcolor="#FFFF88">
        <td>
            <font size="3"><i>${item.description}</i>
            </font>
        </td>
    </tr>

    <tr bgcolor="#FFFF88">
        <td>
            <a href="${project_name}/pet/addCart/itemid/${item.itemid}/quantity/1">
                <img border="0" src="${project_name}/images/button_add_to_cart.gif"/>
            </a>
        </td>
    </tr>
</table>

<#include "commons/bottom.ftl" encoding="utf-8" parse="true" >


