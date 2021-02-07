# Repositorio para el calculo del retiro del 10% con pipeline de tests en Jenkins

## Herramientas ulilizadas para test:

- Postman
- Jmeter
- Junit
- Selenium

![alt text](/resources/pipeline.png)


## Pipeline:

 El pipeline es de tipo multibranch con ejecución del branch develop conectado a github.

### Steps


- Test Junit: Ejecuta los test de la aplicación y genera el jar
- Test Postman: Levanta la app y ejecuta los test mediante newman. El postman collection para newman se invoca directamente desde el repositorio de postman.
- Test Selenium: Ejecuta los test generados por Selenium Web Driver incluidos en la clase [SeleniumTST](/src/test/java/com/devops/dxc/devops/DevopsApplicationTests.java)

## Jenkins server:

Se levanto un servidor con Jenkins en digital ocean con la imagen [docker](https://hub.docker.com/r/jenkins/jenkins). Se debieron realizar las siguientes configuraciones para ejecutar el pipeline:

- Instalación de node js
- Instalación de npm
- Instalación de newman
- Instalación de chromedriver
- Instalación de chrome 88



