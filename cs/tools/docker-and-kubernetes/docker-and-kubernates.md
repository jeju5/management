## Docker and Kubernetes: The Complete Guide

# Install Docker on Mac
  * sign up for docker hub
    * jexxx009

# What is Docker?
  * Docker Client(CLI), Docker Server and Docker Hub
    ```
    docker run hello-world
    ```
    * docker run = docker create + docker start.
      * docker create command creates a container.
      * docker start starts a container.    
      * when you run the command above for the first time, docker client asks docker server for hello-world image. (note that       docker client and docker server is at local)
    * docker server looks at image cache where it realizes image cache doesn't have the image.
    * docker server downloads hello-world image from docker hub
  * Image
    * a file that describes a program configuration and startup commands. (file system snapshot + startup commands; FS = file     system)
  * Container
    * a namespaced resources that runs a process(s). (partition of resources)
    * Namespace (Linux Namespace) is a feature of Linux kernel to partition resources for processes.
    
# Docker commands
  ```
  docker run busy-box ls
  ```
  * busybox is a lite-weight linux program.
  
  ```
  docker ps
  ```
  * lists currently running containers.
  
  ```
  docker ps --all
  ```
  * lists history of containers.
    
  


