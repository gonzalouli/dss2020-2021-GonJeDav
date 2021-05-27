# PracticasDSS
Repositorio para las practicas de DSS en Java.

# Instalacion y ejecucion
Para la instalacion y ejecucion del proyecto necesitamos: 
  * Maven
  * Eclipse (opcional)
  * JavaVM 8 < Instalado

Descararemos Maven desde la pagina oficial de apache : https://maven.apache.org/download.cgi#Installation.

Descomprimiremos y crearemos una variable del sistema MAVEN_HOME hacia el directorio de maven. Actualizamos a continuacion la variable path con esta variable del sistema. 

Los colocaremos en nuestro /core y realizaremos mvn install

Los colocaremos en nuestro /backend y realizaremos mvn install

Los colocaremos en nuestro /accessing-data-mysql y realizaremos mvn install


Por ultimo, en el directorio target de nuestro backend o de nuestra accessing-data-mysql, realizaremos el comando java -jar backend-0.0.1-SNAPSHOT.jar para ejecutar nuestro proyecto empaquetado. Dependiendo de que servicio querramos lanzar, o, realizaremos el lanzamiento de los dos servicios en paralelo.



