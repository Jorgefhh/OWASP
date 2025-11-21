#IMAGEN MODELO
FROM eclipse-temurin:21.0.9_10-jdk-ubi9-minimal


#PUERTO DE LA APP (INFORMATIVO)
EXPOSE 8080

#DEFINIR DIRECTORIO RAIZ
WORKDIR /root


#COPIAR Y PEGAR ARCHIVOS DENTRO DEL CONTENEDOR
COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root

#DESCARGO DEPENDENCIAS
RUN ./mvnw dependency:go-offline

#COPIO EL CÓDIGO FUENTE DENTRO DEL CONTENEDOR
COPY ./src /root/src

#CONSTRUIR APP
RUN ./mvnw clean install -DskipTests

#LEVANTAR APP CUANDO EL CONTENEDOR INICIE
ENTRYPOINT ["java","-jar", "/root/target/owasp-0.0.1.jar"]

