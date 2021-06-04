# PracticasDSS
Repositorio para las practicas de Diseño de Sistemas Software del curso 20/21.

# Instalacion y ejecucion
Para la instalacion y ejecucion del proyecto necesitamos: 
  * Maven
  * Eclipse (opcional)
  * JavaVM 11 (o superior) Instalado
  * Mysql Installer/Workbench

Descargaremos Maven desde la pagina oficial de apache : https://maven.apache.org/download.cgi#Installation.

Descomprimiremos y crearemos una variable del sistema MAVEN_HOME hacia el directorio de maven. Actualizamos a continuacion la variable path con esta variable del sistema. 

Nos colocaremos en nuestro /core y realizaremos mvn install

Nos colocaremos en nuestro /backend y realizaremos mvn install

Nos colocaremos en nuestro /accessing-data-mysql y realizaremos mvn install

Es absolutamente necesario tener el servicio de Mysql80 ejecutandose en nuestra máquina.

Por ultimo, en el directorio target de nuestro backend o de nuestra accessing-data-mysql, realizaremos el comando java -jar backend-0.0.1-SNAPSHOT.jar o accessing-data-mysql-0.0.1-SNAPSHOT.jar para ejecutar los diferentes proyectos empaquetados. Dependiendo de que servicio queramos lanzar realizaremos el lanzamiento de los dos servicios en paralelo cambiando el puesto de desplegado de uno de ellos.

Como en esta practica pararemos en el Hito 5, tendremos dos proyectos diferentes, que son el Backend de servicios de la cafeteria, y el backend de pagos telematicos.

