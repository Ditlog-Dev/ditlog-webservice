<h1 align="center">
  <br>
  Ditlog Web Service
  <br>
  <br>
</h1>

<p align="center">
  <a href="https://travis-ci.org/Ditlog-Dev/ditlog-webservice"><img src="https://api.travis-ci.org/Ditlog-Dev/ditlog-webservice.svg?branch=master" alt="travis"></a>
  <a href="http://checkstyle.sourceforge.net/google_style.html"><img src="https://img.shields.io/badge/code--style-google-blue.svg" alt="Checkstyle - Google Java Style Guide"></a>
</p>

<p align="center">
This repository contains the backend/REST API server of the DitLog Project.
</p>

---

## Installation
1. Open IntelliJ IDEA Ultimate
2. Import this repo
3. Configure database connection. Change `application.properties.sample` to `application.properties` and configure credential
4. Download `odbc7.jar` from oracle, and put it on folder `libs/`

## Running using Docker in development

Prerequisites: [Docker Engine](https://docs.docker.com/engine/installation/) and [Docker Compose](https://docs.docker.com/compose/install/) __must be installed__;

Internet access for pulling gradle dependencies and Docker images.

**Note: Docker commands might need root privileges/sudo.**

1. Navigate to the project directory.
2. Run `./init`. Docker will download gradle dependecies and build image docker based on Dockerfile.
3. Run `./start` to start the application and its supporting services (Nginx, Oracle). Docker will download images if necessary.
4. Voila you're done running docker!

### Note :

- Run `./shell` to get into shell of Java Application

- Run `docker ps` to see running containers. Take note of the name of the container running the app service.

- To stop the containers, do `CTRL + C` to get out of continous build and run './stop'.

- To remove the containers, run `docker-compose down`.

- If the Dockerfile is modified, you will need to rebuild the Docker images by removing the containers and running `./init` again.

- Using pagination
```
/indicators?page=W&limit=X&dir=Y&sort=Z
    
W = page number (positive number >= 0, default = 0)
X = item limit per page (positive number >= 1, default = 5)
Y = direction (asc or desc, default=asc)
Z = sorting key (check JSON structure. in indicator, default = id) 
```

---

## Keynote

### Features
- This project support live reloading if you modify the apps. To add the live reloading you must install [RemoteLiveReload](https://chrome.google.com/webstore/detail/remotelivereload/jlppknnillhjgiengoigajegdpieppei?hl=en-GB) Extension on Google Chrome. If you do not want to install, then you must refresh your browser manually

- In DB testing using H2

### To Do

---

## Contributors