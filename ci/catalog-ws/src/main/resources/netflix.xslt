<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" omit-xml-declaration="yes" cdata-section-elements="synopsis" encoding="UTF-8"/> 

<xsl:variable name="doublequot">"</xsl:variable>
<xsl:variable name="empty"><xsl:text></xsl:text></xsl:variable>

<xsl:template match="/">
<xsl:text>[</xsl:text>
<xsl:for-each select="catalog_titles/catalog_title">
<xsl:variable name="id">
	<xsl:if test="substring-after(id,'movies/')"><xsl:value-of select="substring-after(id,'movies/')"/></xsl:if>
	<xsl:if test="substring-after(id,'series/')"><xsl:value-of select="substring-after(id,'series/')"/></xsl:if>
</xsl:variable>
<xsl:text>{</xsl:text>
<xsl:text>"id":"</xsl:text><xsl:value-of select="$id"/><xsl:text>",</xsl:text>
<xsl:text>"name":"</xsl:text><xsl:value-of select="title/@regular"/><xsl:text>",</xsl:text>
<xsl:text>"description":"</xsl:text><xsl:value-of select="normalize-space(replace(link/synopsis, $doublequot, $empty))"/><xsl:text>",</xsl:text>
<xsl:text>"year":"</xsl:text><xsl:value-of select="release_year"/><xsl:text>",</xsl:text>
<xsl:text>"duration":"</xsl:text><xsl:value-of select="runtime"/><xsl:text>",</xsl:text>
<xsl:text>"cast": [ </xsl:text>
	<xsl:for-each select="./link[@title='cast']/people/link">
		<xsl:text>"</xsl:text><xsl:value-of select="@title" /><xsl:text>"</xsl:text><xsl:if test="position() != last()">,</xsl:if>
	</xsl:for-each>
<xsl:text>],</xsl:text>
<xsl:text>"directors": [ </xsl:text>
	<xsl:for-each select="./link[@title='directors']/people/link">
		<xsl:text>"</xsl:text><xsl:value-of select="@title" /><xsl:text>"</xsl:text><xsl:if test="position() != last()">,</xsl:if>
	</xsl:for-each>
<xsl:text>]</xsl:text>
<xsl:text>}</xsl:text>
<xsl:if test="position() != last()">,</xsl:if>
</xsl:for-each>
<xsl:text>]</xsl:text>	
</xsl:template>

</xsl:stylesheet>