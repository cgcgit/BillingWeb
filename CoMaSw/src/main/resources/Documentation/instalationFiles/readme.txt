
-----------------------------------------
-- PASOS PARA LA INSTALACIÓN DE CoMaSw --
-----------------------------------------
1. Descomprimir el fichero comasw_files.zip Este fichero comprimido contiene lo siguiente:
   * CoMaSw.war - fichero war de la aplicación.
   * postgresql-42.4.1.jar - driver jdbc para postgres.
   * creacion_db.sql - script con los pasos necesarios para la creación de la base de datos.
   * db_comasw.sql - fichero de importación de la base de datos.
2. Configurar la base de datos
   La base de datos de la aplicación se llama db_comasw y tiene dos usuarios:
     * comasw_admin - usuario propietario de la base de datos. Todo nuevo objeto a crear en la base de datos de la aplicación deberá crearse con este usuario.
     * comasw_app - usuario de la aplicación CoMaSw (con el que establece conexión la aplicación a la base de datos). En caso de crear nuevos objetos en la base de datos relacionados con la aplicación deberán otorgársele los permisos correspondientes a este usuario sobre dichos objetos. 

   A continuación se describen los pasos a seguir para crear y configurar la base de datos.
     (a) Crear la base de datos db_comasw
            Desde la ruta en la que hemos descomprimido el fichero nos conectaremos a la base de datos de postgresql y ejecutamos lo siguiente:
               $ creacion_db.sql
     (b) Importar la base de datos db_comasw.
            Desde el terminal nos colocamos en la ruta en la que hemos descomprimido el fichero y ejecutamos lo siguiente:
               $ psql -U postgres db_comasw < db_comasw.sql


3. Configurar el servidor web Wildfly. A través del CLI de Wildfly ejecutar lo siguiente:
     (a) Desplegar el driver JDBC- Ejecutamos lo siguiente, siendo [ruta_driver] la ruta en la que hemos ubicado el driver contenido en el fichero comasw_files.zip:
           module add --name=org.postgresql --resources=[ruta_driver]/postgresql-42.4.1.jar --dependencies=javax.api,javax.transaction.api 
     (b) Añadimos el driver JDBC. Ejecutamos lo siguiente:
           /subsystem=datasources/jdbc-driver=postgres:add(driver-name="postgres", driver-module-name="org.postgresql", driver-class-name=org.postgresql.Driver)

4. Copiar el fichero comasw.war a la carpeta de aplicaciones de Wildfly

5. Desplegar la apliación en el servidor


--------------------------
--  EJECUCIÓN DE CoMaSw --
--------------------------
Para acceder a la aplicación escribir en la barra de direcciones de la aplicación la siguiente URL: http://[servidor]:8080, donde servidor es el nombre del servidor web.
