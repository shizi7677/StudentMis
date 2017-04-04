<#include "commons/top.ftl" encoding="utf-8" parse="true" >
<form id="login" name="login" method="post" action="${project_name}/user/login">

    <table align="center" border="0">
        <tr>
            <td>
                <font color="red">
                <#if error ? exists>
                  ${error}
                 </#if>
                </font>
                <br/>
            </td>
        </tr>
        <tr>
            <td>
                请输入用户名和密码：<br/>
            </td>
        </tr>
        <tr>
            <td>
                用户名：
                <input type="text" id="username" name="username"/>
            </td>
        </tr>
        <tr>
            <td>
                密&nbsp;码：
                <input type="password" id="password" name="password"/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="image" border="0" src="../images/button_submit.gif"/>
            </td>
            <td>
                <a href="${project_name}/user/rmain">
                    <img border="0" src="../images/button_register_now.gif"/>
                </a>
            </td>
        </tr>
    </table>
</form>
<#include "commons/bottom.ftl" encoding="utf-8" parse="true">