<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />


<xsl:template match="/">
   <html>
	
	<p><b>Physio:</b></p>
	<p> Name: <xsl:value-of select="physio/@name" /> </p>
	<p> Phone: <xsl:value-of select="physio/phone" /></p>
	<p> Speciality: <xsl:value-of select="physio/speciality" /></p>
	<p> Email: <xsl:value-of select="physio/email" /> </p>
	
	<table border="1">
	  <th>Client´s name</th>
      <th>Phone</th>
      <th>Card number</th>
      <th>Email</th>
      <th>Date of Birth</th>
      
      <xsl:for-each select="physio/client/client">
      <xsl:sort select="@name" />
	       <tr>
	            <td><xsl:value-of select="@name" /></td>
	            <td><xsl:value-of select="phone" /></td>
	            <td><xsl:value-of select="card_n" /></td>
	            <td><xsl:value-of select="email" /></td>
	            <td><xsl:value-of select="doB" /></td>                           
	       </tr>        
      </xsl:for-each>  
		
	</table>		
   </html>
   </xsl:template>
	
</xsl:stylesheet>