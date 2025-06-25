# arqui-soft-II
TP II Arquitectura de Software II


## Requerimientos recomendados:

    JDK 17.0.15-amzn
    Maven 3.8.8

Revisar que estén bien seteadas las variables JAVA_HOME y MAVEN_HOME en computadora. Para corroborarlo puede probar por consola
```echo $JAVA_HOME``` y también  ```echo $MAVEN_HOME``` deben apuntar a cada carpeta correspondiente.
Se recomiendo instalar sdkman lo que le permitirá manejar más fáciles las versiones.

## Instalación
Una vez descargado el proyecto, al abrirlo con cualquier IDE automáticamente detecterá el POM y buscará instalar todas las dependencias. En caso de que haya algún problema o asegurarse que se instale correctamente, se puede ejecutar el comando
```mvn clean install```

## Setup inicial
Para poder levantar el proyecto, previamente se debe tener una cuenta en Atlas Database, creando un entorno gratuito el cual generará un cluster de trabajo. Para ello puede revisar la documentación [acá](https://www.mongodb.com/products/platform/atlas-database).
Luego deberá obtener la url, usuario y contraseña para poder configurar dinámicamente el archivo application.properties bajo la ruta ```src/main/resources/```

### Variables

Se debe ejecutar el servicio con los siguientes valores

- mongo.user: Usuario de la DB de mongo
- mongo.pass: Pass de la DB de mongo
- mongo.host: Host de la DB de mongo
- mongo.db: Nombre de la DB de mongo
- ar.edu.unq.weather.loader.component.import.cron: Cronjob para parametrizar cada cuanto tiempo se desea ejecutar, por ej "0 */3 * ? * *"
- ar.edu.unq.weather.loader.component.weather.map.api.key: Apikey de weather app
- LOG_PATH: Carpeta para los logs, debe ser la ruta incluyendo el `/` al final por ejemplo `/Obervability-System/logs/`
