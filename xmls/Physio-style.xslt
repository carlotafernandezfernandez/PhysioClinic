<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
	
	<p><b>Owner:</b></p>
	<p> Name: <xsl:value-of select="owner/@name" /> </p>
	<p> Email:   <xsl:value-of select="owner/email" /> </p>
	<p> Phone: <xsl:value-of select="owner/phone" /></p>
	<p> CardNumber: <xsl:value-of select="owner/cardnumber" /></p>
	
	<table border="1">
	  <th>Pet's name</th>
      <th>Type Of Animal</th>
      <th>Coat</th>
      <th>Date of Birth</th>
      <th>Cured</th>
      
      <xsl:for-each select="owner/pets/pet">
      <xsl:sort select="@name" />
	       <tr>
	            <td><xsl:value-of select="@name" /></td>
	            <td><xsl:value-of select="typeofAnimal" /></td>
	            <td><xsl:value-of select="coat" /></td>
	            <td><xsl:value-of select="dob" /></td> 
	            <td><xsl:value-of select="cured" /></td>                  
	       </tr>        
      </xsl:for-each>  
		
	</table>		
	   
   </html>
   </xsl:template>
	
</xsl:stylesheet>