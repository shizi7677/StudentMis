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

<table align="center" bgcolor="#008800" cellspacing="2" cellpadding="3"
       border="0" width="60%">
    <tr bgcolor="#FFFF88">
        <td bgcolor="#FFFFFF">
            宠物编号
        </td>
        <td bgcolor="#FFFFFF">
            宠物单价
        </td>
        <td bgcolor="#FFFFFF">
            宠物供应商
        </td>
        <td bgcolor="#FFFFFF">
            宠物状态
        </td>
        <td bgcolor="#FFFFFF">
            说明
        </td>
        <td bgcolor="#FFFFFF">
            加入购物车
        </td>
    </tr>

<#list items as item>
    <tr bgcolor="#FFFF88">
        <td>
            <a href="${project_name}/pet/queryItem/${item.itemid}">     ${item.itemid} </a>
        </td>
        <td>
        ${item.unitcost}
        </td>
        <td>
        ${item.supplier}
        </td>
        <td>
        ${item.status}
        </td>
        <td>
        ${item.description}
        </td>
        <td>
            <a href="${project_name}/pet/addCart/itemid/${item.itemid}/quantity/1"><img border="0"
                                                                                        src="${project_name}/images/button_add_to_cart.gif"/>
            </a>
        </td>
    </tr>
</#list>
</table>
<#include "commons/bottom.ftl" encoding="utf-8" parse="true" >
