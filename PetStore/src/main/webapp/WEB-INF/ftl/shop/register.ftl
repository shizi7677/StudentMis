<#--为了用SpringMVC做校验，View层必须引入Springjsp标签控件；利用宏来校验注册信息-->
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]/>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"] />

<#include "commons/top.ftl" encoding="utf-8" parse="true">

<#--commandName属性必须要有，代表校验的对象-->
<@form.form method="post" action="${project_name}/user/register" commandName="user">
<TABLE cellpadding=10 cellspacing=0 align=center border=1 width="400"
       bgcolor=#dddddd>
<TR>
<TD>
    <FONT color=darkgreen>
        <H3>
            请输入注册信息：
        </H3>
    </FONT>
    <TABLE border=0 bgcolor=#FFFF88 width="100%">

        <TR bgcolor=#FFFF88>
            <TD>
                用户名&nbsp;:
                <@form.input path="username" id="username" />
                <font color="red">
                    <@form.errors path="username"/>
                </font>
            </TD>
        </TR>
        <TR bgcolor=#FFFF88>
            <TD>
                密码&nbsp;&nbsp;：
                <@form.input path="password" id="password" />
                <font color="red">
                    <@form.errors path="password"/>
                </font>

            </TD>
        </TR>
        <TR bgcolor=#FFFF88>
            <TD>
                确认密码：
                <@form.input path="pass_again" id="pass_again" />
                <font color="red">
                    <@form.errors path="pass_again"/>
                </font>
            </TD>
        </TR>
    </TABLE>
    <FONT color=darkgreen>
        <H3>
            个人信息：
        </H3>
    </FONT>
    <TABLE bgcolor="#FFFF88" border=0
           bgcolor=#FFFF88 width="100%">
        <TR bgcolor=#FFFF88>
            <TD>
                真实姓名：
                <@form.input path="realname" id="realname"/>
                <font color="red">
                    <@form.errors path="realname"/>
                </font>
            </TD>
        </TR>
        <TR bgcolor=#FFFF88>
            <TD>
                地址：
                <@form.input path="address" id="address" />
                <font color="red">
                    <@form.errors path="address"/>
                </font>
            </TD>
        </TR>
        <TR bgcolor=#FFFF88>
            <TD>
                邮箱：
                <@form.input path="email" id="email" />
                <font color="red">
                    <@form.errors path="email"/>
                </font>
            </TD>
        </TR>

    </TABLE>

    <FONT color=darkgreen>
        <H3>
            个人设置：
        </H3>
    </FONT>
    <TABLE bgcolor="#FFFF88" border=0 cellpadding=3 cellspacing=1 width="100%">
        <TR bgcolor=#FFFF88>
            <TD>
                选择喜好：
            <#--由于下面的信息并不需要做校验，因此没有必要用Spring标签控件-->
            <#-- 从后台的数据库中查询出宠物的种类-->
                <select id="kindid" name="hobby.kindid">    <#--  为hobby对象传递kindid字段；-->
                    <#list kind_list as kind>
                        <option value="${kind.kindid}">
                        ${kind.kindname}
                        </option>
                    </#list>
                </select>
            </TD>
        </TR>
    </TABLE>

    <BR>
    <CENTER>
        <input border=0 type="image" src="../images/button_submit.gif"/>
    </CENTER>
</@form.form>
<#include "commons/bottom.ftl" encoding="utf-8" parse="true" >
