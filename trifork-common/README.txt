
Readme for Trifork-Common projektet
===================================


1. Form�l
---------
Form�let med denne readme fil er at forklare kort hvad det er man kan 
opn� ved at benytte projektet samt hvordan man kommer i gang med at bruge 
det.


2. Introduktion
---------------
Trifork-common projektet indeholder nogle Ant build-scripts med nogle 
standard targets som mange projekter kan drage nytte af. Det g�r dels 
at man kommer hurtigt i gang, og dels giver det en vis standardisering
og f�lles forst�else projekterne imellem.

Projektet har fx targets der underst�tter brug af Ivy til at hente projektets
dependencies. Der er konfigureret et s�t standard Ivy repositories hvorfra
dependencies hentes ud fra tre felter: organisation, name, revision. De
benyttede repositories er sat op i /trifork-common/etc/ivy/ivy-maven2-ivyconf.xml.
S�fremt en dependency ikke findes i nogen af de konfigurerede Ivy repositories
kan man l�gge det ind under i trifork-common projektet med f�lgende navngivning:

  /trifork-common/lib/shared-lib/<org>/<name>/<rev>/<name>-<rev>.jar

S� vil det v�re tilg�ngeligt for alle brugere af trifork-common, p� samme m�de
som de ting der g�res tilg�ngelige via Ivy repositories.


3. Ops�tning af et nyt projekt - Fase 1: Dependencies
-----------------------------------------------------
Det antages at der laves et nyt projekt i en separat folder, og p� niveau med
denne folder checkes trifork-common projektet ud.

Der skal laves to filer, dels et Ant build script (build.xml) og del en
konfiguration til dependency v�rkt�jet Ivy (ivy.xml). Herunder er vist
eksempler p� disse to filer.

________________________________________________________________________________
Minimal build.xml:

<?xml version="1.0"?>
<!-- the project element's name attribute will be used in the name of the module's jar file -->
<project name="vaccinationsregister" default="compile" basedir=".">
	<property file="build.properties" />
	<property name="trifork-common" value="${basedir}/../trifork-common" />
	<import file="${trifork-common}/etc/build-common.xml" />
</project>

________________________________________________________________________________
Eksempel ivy.xml:

<ivy-module version="1.0">
	<!-- ivy beskrivelse: http://www.jaya.free.fr/ivy/doc/ivyfile/ -->
	<info module="myproject" organisation="trifork" status="integration" />
	<configurations>
		<conf name="deploy" />
		<conf name="build" extends="deploy" />
		<conf name="test" extends="deploy" />
	</configurations>
	<dependencies>

		<dependency org="trifork" name="trifork-common" rev="latest.integration" conf="*->@" />

		<!-- jdbc driver for MySQL -->
		<dependency org="mysql" name="mysql-connector-java" rev="5.0.5" />

		<!--  Spring dependencies -->
		<dependency org="org.springframework" name="spring-jdbc" rev="2.5.6">
			<exclude org="jotm" />
			<exclude org="commons-attributes" />
		</dependency>

		<!-- Exclude -->
		<exclude org="commons-attributes" />
		<exclude org="xerces" module="xmlParserAPIs" />
		<exclude org="com.ibm.icu" module="icu4j" />

	</dependencies>
</ivy-module>
________________________________________________________________________________


A. Referencer
-------------

Ivy hovedside
http://ant.apache.org/ivy/

Ivy quick start tutorial
http://ant.apache.org/ivy/history/latest-milestone/tutorial/start.html

