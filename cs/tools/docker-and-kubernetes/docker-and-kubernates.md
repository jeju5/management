## Docker and Kubernetes: The Complete Guide

# Install Docker on Mac
  * sign up for docker hub
    * jexxx009

# What is Docker?
  * Docker Client(CLI), Docker Server and Docker Hub
    ```
    docker run hello-world
    ```
    * when running the command above, docker client asks docker server for hello-world image. (note that docker client and 
    docker server is at local)
    * docker server looks at image cache where it realizes image cache doesn't have the image.
    * docker server downloads hello-world image from docker hub
  * Image
    * a file that describes a program configuration and startup commands. (snapshot + startup commands)
  * Container
    * a namespaced resources that runs a process(s). (partition of resources)
    * Namespace (Linux Namespace) is a feature of Linux kernel to partition resources for processes.
    
# Docker and busybox
  ```
  docker run busy-box ls
  ```
  * busybox is a VM
    
  


