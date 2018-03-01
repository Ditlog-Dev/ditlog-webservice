# Ditlog Web Service

This repository contains the backend/REST API server of the DitLog Project.

---

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

## Keynote

### Features
- This project support live reloading if you modify the apps. To add the live reloading you must install [RemoteLiveReload](https://chrome.google.com/webstore/detail/remotelivereload/jlppknnillhjgiengoigajegdpieppei?hl=en-GB) Extension on Google Chrome. If you do not want to install, then you must refresh your browser manually

### To Do
- Add Oracle DB
- Add testing unit (JUnit)
- Add Continous Integration (Jenkins or CI)