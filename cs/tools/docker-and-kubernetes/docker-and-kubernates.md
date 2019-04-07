## Docker and Kubernetes: The Complete Guide

# Install Docker on Mac
  * sign up for docker hub
    * jexxx009

# What is Docker?
  * Docker Client(CLI), Docker Server and Docker Hub
    ```
    docker run hello-world
    ```
    * at local, docker client asks docker server for hello-world image.
    * docker server looks at image cache where it realizes image cache doesn't have the image.
    * docker server downloads hello-world image from docker hub
  * Image
    * a file that describes a configuration of a program and startup commands. (snapshot + command)
  * Container
    * a namespaced group of resources that runs a process(s).
    * Namespace (Linux Namespace) is a feature of Linux kernel to partition resources for processes.


