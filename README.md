  # Sobre el proyecto:
  
Este proyecto es un Trabajo Práctico Integrador
de la Matería Back End 1 de la carrera
Certified Tech Developer de Digital House
en colaboración con Globant & Mercado Libre.

La consigna se basa en la creación de una API RESTful con Java,
utilizando Spring Boot, Maven, H2 como base de datos, JPA, JUnit5 para los test unitarios y
Log4j para el logging de errores. En este proyecto también se usa Bootstrap para los estilos de la vista HTML
y JavaScript con AJAX para las llamadas a la API RESTful desde la vista.

El proyecto se basa en una clínica odontológica
donde hay odontólogos, pacientes, domicilios y turnos.

Las relaciones entre las clases Java
y tablas H2 serían las siguientes:

- Un paciente tiene un domicilio.
- Un odontologo puede ter uno o más turnos.
- Un paciente puede tener uno o más turnos.
- Un turno tiene un paciente y un odontólogo.

A su vez cada clase tiene sus atributos individuales obviamente.

---

### Integrantes del proyecto:

- Francesco Maurano (francescomaurano10@gmail.com)
- Juan Cruz Marzetti Falcone (naujmarz@gmail.com)


---
## Colaboración:

Si queres colaborar con el proyecto podés hacer un fork
indicando en tus cambios en el código una 
explicación de qué solución(o mejora) esos cambios le brindan al código.

# IaC

Las configuraciones de IaC como `hosts.ini`, `playbook.yaml`, `Dockerfile`, `docker-compose.yaml`, `.gitlab-ci.yml`, son para la creación de un contenedor Docker y la creación de una imagen que se desplegará en una instancia EC2. Este repositorio se ejecuta en GitLab. Para el correcto funcionamiento, primero debemos ejecutar la siguiente infraestructura con Terraform: [iac-for-docker-in-ec2](https://github.com/juancruzmarzetti/iac-for-docker-in-ec2) (también automatizada con variables y pipeline desde GitLab).

## Configuración de Variables de Terraform

Para el correcto funcionamiento de este proyecto, debemos configurar primero las variables de Terraform en `variables.tf`:

- `name`: Nuestro nombre o el del proyecto.
- `environment`: Tipo de ambiente al que apunta nuestra infraestructura (ej: dev, testing, prod, etc.).
- `vpcid`: ID de una VPC ya creada en nuestra cuenta de AWS.
- `subnetid`: ID de una subnet asociada con nuestra VPC declarada en la variable `vpcid`.
- `keyname`: Nombre de un par de claves creado en AWS (también debemos descargarlo en formato `.pem` y agregarlo en nuestro directorio raíz del proyecto, creando un archivo `.gitignore` y especificando en él el nombre exacto del archivo, ej: juankeys.pem. Esto hará que Terraform al ejecutarse lea este par de claves pero que al mismo tiempo no se suba al repositorio remoto, lo cual sería una falla seria de seguridad).

## Configuración de Backend

En `main.tf`, debemos modificar los valores de las direcciones de backend en `backend "http"{...}`, según el ID de nuestro proyecto de GitLab (Repositorio de GitLab > Settings > General > Project ID). Una vez lo tengamos, en los valores de `address`, `lock_address` y `unlock_address`, reemplazamos por nuestro ID en esos valores de la siguiente manera donde dice "ProjectID": `https://gitlab.com/api/v4/projects/ProjectID/terraform/...`.

## Configuración de Variables de Entorno en GitLab

Luego, configuramos nuestras variables de entorno en GitLab (Settings > CI/CD > Variables):

- `TF_API_TOKEN`: Token de acceso personal; ir a User Settings > Access Tokens > Generar un token con permisos "api" y "read_repository" (copiar el token, ya que solo se muestra una vez). Ese token será el valor de esta variable.
- `JUANKEYS (KEYS)`: (Esta variable tiene el nombre de mis llaves en el Pipeline, pero por ejemplo, donde dice `- echo "$JUANKEYS" > juankeys.pem`, se referencia en el pipeline a crear el archivo `juankeys.pem`, que contenga lo que contiene la variable de entorno `JUANKEYS`; entonces, donde dice `juankeys.pem`, iría el nombre del archivo de tus llaves, y donde dice `JUANKEYS`, iría el nombre de la variable de entorno que elijas. Es importante, al declarar la variable de entorno, además de declararla como protected, asignar absolutamente todo el contenido de las keys.pem, hasta lo que parece ser un comentario, y no borrar los saltos de línea. PD: también en los pipeline donde dice `- rm juankeys.pem` y `- chmod 600 juankeys.pem` cambiar por el nombre del archivo .pem de tus llaves.
- `AWS_ACCESS_KEY_ID` y `AWS_SECRET_ACCESS_KEY`: Nuestras credenciales IAM de AWS CLI (estas variables no es necesario que estén en el grupo donde llegarían a los dos repositorios, basta con definirlas para el entorno del repositorio de la infraestructura de Terraform).

## Despliegue de la Infraestructura

Una vez configurado todo esto, nuestra infraestructura debería funcionar levantando una instancia EC2 t2.micro. Cuando se levante, vamos a ir a la Consola de EC2 y hacer clic en el ID de esta instancia; ahí veremos, en el detalle de la instancia EC2, que hay una dirección IPv4 Pública con esta sintaxis: `ec2-54-91-21-6.compute-1.amazonaws.com`. La copiamos y la pegamos en nuestro archivo `hosts.ini` (repositorio backend), en la parte donde dice `ec2-54-91-21-6.compute-1.amazonaws.com`; esa dirección IPv4 pública la cambiamos por la nuestra. Al lado donde dice `juankeys.pem` también lo cambiamos por el nombre de nuestro archivo .pem.

## Configuración en Docker Hub

Después, en Docker Hub, creamos un repositorio vacío y en el archivo `docker-compose.yaml` (repositorio backend), el valor de `image` lo reemplazamos con nuestro nombre de usuario de Docker Hub (ej: juancruzmarzetti), seguido de una barra diagonal y el nombre de nuestro repositorio vacío recién creado (ej: proyinte) (ej completo: `image: juancruzmarzetti/proyinte`).

Luego, en nuestro archivo Dockerfile, en la línea que dice: `COPY --from=build /home/app/target/odontologo-0.0.1-SNAPSHOT.jar /myproject.jar` debemos reemplazar donde dice `odontologo-0.0.1-SNAPSHOT.jar` con el archivo .jar que genera nuestro proyecto Maven al hacer el build: esto lo podemos saber buscando en el archivo pom.xml, donde dice `<groupId>com.me</groupId> <artifactId>odontologo</artifactId> <version>0.0.1-SNAPSHOT</version> <name>odontologo</name> <description>Demo project for Spring Boot</description>`, el archivo .jar resultante sería algo como `artifactID-version.jar`.

## Configuración de Variables de Entorno Adicionales

Después, faltaría la configuración de las siguientes variables de entorno:

- `JUANKEYS/KEYS`: Si ya se configuraron anteriormente en el entorno del grupo donde están los dos repositorios (el de backend y el de infraestructura), ya no hay que configurar esta variable.
- `CI_REGISTRY`: Con el valor `docker.io` (sin comillas).
- `CI_REGISTRY_USER`: Nuestro nombre de usuario de Docker Hub.
- `CI_REGISTRY_PASSWORD`: Nuestra contraseña de Docker Hub (esta variable debe estar protected y masked).

## Personalización del Pipeline

Por último, tenemos que personalizar el pipeline, archivo `.gitlab-ci.yml`, donde debemos:

- Reemplazar donde se nombra a `juancruzmarzetti/proyinte` por el valor que pusimos en `docker-compose.yaml` como valor de `image`.
- Reemplazar donde se nombra a `juankeys.pem` por el nombre del archivo de nuestras llaves .pem.
- Reemplazar donde se nombra a `JUANKEYS` ($JUANKEYS) por el nombre que le pusimos a nuestra variable de entorno CI/CD que guarda el valor de nuestras llaves .pem.



---
 ### In english:
# About the project:

This project is an Integrative Practical Work
of the Back End Matter 1 of the
Digital House Certified Tech Developer carreer
in collaboration with Globant & Mercado Libre.

The slogan is based on the creation of a RESTful API with Java,
using Spring Boot, Maven, H2 as a database, JPA, JUnit5 for unit tests and
Log4j for error logging. This project also uses Bootstrap for the HTML view styles.
and JavaScript with AJAX for RESTful API calls from the view.

The project is based on a dental clinic
where there are dentists, patients, homes and shifts.

Relationships between Java classes
and H2 tables would be the following:

- A patient has an address.
- A dentist can have one or more shifts.
- A patient can have one or more shifts.
- A shift has a patient and a dentist.

In turn, each class obviously has its individual attributes.

---

### Project members:

- Francesco Maurano (francescomaurano10@gmail.com)
- Juan Cruz Marzetti Falcone (naujmarz@gmail.com)

---

## Collaboration:

If you want to collaborate with the project you can make a fork
indicating in your code changes a
explanation of what solution (or improvement) those changes provide to the code.

# IaC

The IaC configurations such as `hosts.ini`, `playbook.yaml`, `Dockerfile`, `docker-compose.yaml`, `.gitlab-ci.yml`, are for creating a Docker container and an image that will be deployed on an EC2 instance. This repository runs on GitLab. For it to work correctly, we must first execute the following infrastructure with Terraform: [iac-for-docker-in-ec2](https://github.com/juancruzmarzetti/iac-for-docker-in-ec2) (also automated with variables and pipeline from GitLab).

## Terraform Variables Configuration

For the project to function correctly, we need to configure the Terraform variables in `variables.tf` first:

- `name`: Our name or the project's name.
- `environment`: Type of environment our infrastructure points to (e.g., dev, testing, prod, etc.).
- `vpcid`: ID of an existing VPC in our AWS account.
- `subnetid`: ID of a subnet associated with our VPC declared in the `vpcid` variable.
- `keyname`: Name of a key pair created in AWS (also download it in `.pem` format and add it to our project's root directory, creating a `.gitignore` file and specifying the exact name of the file, e.g., juankeys.pem. This will make Terraform read this key pair during execution but at the same time, not upload it to the remote repository, which would be a serious security breach).

## Backend Configuration

In `main.tf`, we need to modify the backend addresses in `backend "http"{...}`, according to our GitLab project ID (GitLab Repository > Settings > General > Project ID). Once we have it, in the `address`, `lock_address`, and `unlock_address` values, we replace it with our ID in these values as follows where it says "ProjectID": `https://gitlab.com/api/v4/projects/ProjectID/terraform/...`.

## GitLab Environment Variables Configuration

Then, we set up our environment variables in GitLab (Settings > CI/CD > Variables):

- `TF_API_TOKEN`: Personal access token; go to User Settings > Access Tokens > Generate a token with "api" and "read_repository" permissions (copy the token as it is only shown once). This token will be the value of this variable.
- `JUANKEYS (KEYS)`: (This variable has the name of my keys in the Pipeline, but for example, where it says `- echo "$JUANKEYS" > juankeys.pem`,
