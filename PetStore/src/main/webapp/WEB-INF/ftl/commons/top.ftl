

<html>
  <head>
  		<TITLE></TITLE>
		<META content="text/html; charset=utf-8" http-equiv=Content-Type>
		<#--声明一个代表项目名称的变量：便于访问路径的编写-->
	  <#assign project_name = request.contextPath />
  </head>
  
 <BODY bgColor=white >
   	<TABLE background="${project_name}/images/bkg-topbar.gif" border=0 cellSpacing=0
			cellPadding=5 width="100%">
			<TBODY>
				<TR>
					<TD>
						<A href="/SPetStoreTemp"><IMG border=0
								src="${project_name}/images/logo-topbar.gif">
						</A>
					</TD>
					<TD align=right >
						<#--购物车图标-->
						<A href=""><IMG border=0 name=img_cart
								src="${project_name}/images/cart.gif">
						</A>
						<IMG border=0 src="${project_name}/images/separator.gif">
						<IMG border=0 src="${project_name}/images/separator.gif">
						
					</TD>
					<TD align=left valign="bottom">
						<FORM action="">
							<INPUT name=keyword size=14>
							<INPUT border=0 src="${project_name}/images/search.gif" type=image>
						</FORM>
					</TD>
					
				</TR>
			</TBODY>
		</TABLE>
		<center>
			<a
				href="${project_name}/pet/queryProduct/FISHS"><img
					border="0" src="${project_name}/images/sm_fish.gif" />
			</a>
			<img border="0" src="${project_name}/images/separator.gif" />
			<a
				href="${project_name}/pet/queryProduct/DOGS"><img
					border="0" src="${project_name}/images/sm_dogs.gif" />
			</a>
			<img border="0" src="${project_name}/images/separator.gif" />
			<a
				href="${project_name}/pet/queryProduct/REPTILES"><img
					border="0" src="${project_name}/images/sm_reptiles.gif" />
			</a>
			<img border="0" src="${project_name}/images/separator.gif" />
			<a
				href="${project_name}/pet/queryProduct/CATS"><img
					border="0" src="${project_name}/images/sm_cats.gif" />
			</a>
			<img border="0" src="${project_name}/images/separator.gif" />
			<a
				href="${project_name}/pet/queryProduct/BIRDS"><img
					border="0" src="${project_name}/images/sm_birds.gif" />
			</a>
		</center>
		