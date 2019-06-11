# AWS Certified Developer - Associate 2019
* UDEMY
* Ryan Kroonenburg & Faye Ellis

# Intro
* Exam Structure
  * https://d1.awsstatic.com/training-and-certification/docs-dev-associate/AWS_Certified_Developer_Associate_Updated_June_2018_Exam_Guide_v1.3.pdf
* 130 minutes & 65 questions
* $150

# SECTION2: IAM
* IAM
  * Identity Access Management
  * allows you to manage users and level of access to AWS console
* Terms
  * User: end user
  * Group: group of Users
  * Role: Role with in the service
  * Policy: Document that defines permissions
* IAM LAB
  * click Services on the top -> Security, Identity, & Compliance -> IAM
    *  you can customize url for sign-in link
  * MFA
    * Security Status -> Manage MFA -> Virtual MFA device -> continue
    * Install MFA app into my phone
    * Scan QR CODE with your phone -> try Authenticator login
  * User
    * a AWS user
  * Groups
    * collection of users
  * Policy
    * defines permission
    * written in JSON
    * delegate policy(es) to a group or directly assign to a user
  * Roles
    * set of permissions assigned to an entity(ex. EC2)

# SECTION3: EC2
* EC2 101
  * EC2 = ECC = Elastic Cloud Computing = Computing Service in Cloud
  * Pricing
    * on demand: price by time -> good for short-term need (short-term also means specific short time)
    * reserved: capacity reservation -> good for regular-basis need
    * spot: bidding for capacity -> good for flexible-time need (start time & end time), good for temporary additional need
      * if terminated partial hour usage -> if AWS EC2 terminates it, you won't be charged / if you terminate it, you will be charged for a complete hour
    * dedicated host: physical EC2 server -> good for region-specific regulations
  * Instance Type (optimized for) "ATM CRX PGF HID"
    * General: A T M
    * Compute: C
    * Memory: R X(extreme)
    * Accelerated: P G F
    * Storage: H I D
  * EBS = elastic block store
    * Volumes that can be attached to EC2
    * aws instance is placed at availability zone. (any instance like db, ec2, ...)
      * Availability Zones < Region (ex. US-east-1; default regions)
    * If you want to encrypt data, you have to configure encryption when creating EBS Volume
    * Types
      * SSD
        * General Purpose SSD = GP2 (General): 3IOPS/GB, max 16,000 IOPS (IOPS = input/output operations per second)
        * Provisioned IOPS SSD = IO1 (Intensive): proper for more than 16,000 IOPS
      * HDD
        * Cold HHD = SC1
          * lowest cost among current
          * use case: file server
          * can't be boot volume
        * Throughput Optimized HDD = ST1
          * use case: big data, data warehousing
          * can't be boot volume (boot volume: disk that relates to OS booting)
      * Magnetic
        * legacy service
        * lowest cost among all
* EC2 LAB
  * AWS console -> Services -> EC2 -> Launch Instance
    * you will see VMs, select what you want
    * then select instance type: "ATM CRX PGF HID"
    * then configure instance
      * purchasing options, number of instances, network, shutdown behavior(STOP, Terminate) ... etc
      * terminate means remove
    * add storage
      * gp2, io1, magnetic ... 
    * add tag
    * configure security group
      * types: ssh, rdp, http...
        * ssh: secure shell
        * rdp: remote desktop protocol
        * http: hyper text transfer protocol (protocol for http request/response)
      * protocol: tcp
      * source
        * 0.0.0.0/0, ::/0
          * 0.0.0.0/0 is for ipv4
          * ::/0 is for ipv6
      * launch
        * you will need public key and private key
          * public/private key
            * symmetric key encryption
              ```
              PLAIN-TEXT ---encrypt with key1---> CIPHER-TEXT ---decrypt with key1---> PLAIN-TEXT
              ```
            * asymmetrical key encryption
              ```
              PLAIN-TEXT ---encrypt with key1---> CIPHER-TEXT ---decrypt with key1---> PLAIN-TEXT

              key1(public key): can be shared to the public
              key2(private key): should be kept as a secret
              ```
        * create them and download them
        * launch
  * go to instances
    * select an instance -> click connect
    * you can connect via one of two options (standalone ssh / mindterm ssh)
       * mindterm ssh is accessible through web browser such as Chrome. It requires JAVA
     * ssh with standalone
       ```
       chmod 400 labKeyPair3.pem
       ssh -i "labKeyPair3.pem" ec2-user@ec2-18-221-93-64.us-east-2.compute.amazonaws.com
       ```
     * work with your instance
       ```
       sudo su                # change working directory to a root user
       yum update -y          # upate packages with yum; -y means say yes to all questions entailed
       yum install httpd -y   # install Apache httpd server
       service httpd start    # start Apache httpd server
       service httpd status   # get Apache httpd server status

       cd /var/www/html       # go to Apache httpd directory
       nano index.html        # create an html file
       ...                    # with a welcome message
       head -10 index.html    # view first 10 lines
       ```
     * access IP address of your VM instance from a local
       * IPv4 Public IP address can be found from AWS Instances pane
  * Load Balancer
    * load balancer 101
      * layer 4 load balancing: operates at transport layer
      * layer 7 load balancing: operates at content layer
      * Types
        * Application Load Balancer: HTTP, HTTPS. works within the application. incoming app traffic control
        * Network Load Balancer: TCP works at layer-4, incoming network traffic controls
        * Classic Load Balancer: HTTP, HTTPS, TCP, SSL. doesn't look at the request. single port mapping
      * when your load balancer fails, it throws 504 error (gateway timeout)
      * x-forwarded-for header
        * public ip makes a request to domain name
        * DNS service transfers the request to a load balancer
        * load balancer transfer a request to Application Server
        * Application Server will get public ip as x-forwarded-for header
  * Route53 Lab
    * Route53 is AWS DNS service
      * it maps the request to EC2, S3 or load balancer
    * Build Load Balancers -> EC2
      * Create Load Balancer
        * select Application Load balancer for our use (flexible and general)
        * configure name, listeners(protocol(HTTP/HTTPS) and port) and zones(region)
        * configure security group
        * configure routing (DNS Name --mapping--> App Server); you can route a request to Instance, IP, Lambda function
        * select EC2 Instance you created
        * configure health check
    * Build Route53 DNS -> Load Balancer
      * Domains -> Register Domain (buy a domain and register it)
      * Hosted Zones
        * select the domain you registered
        * create record set
        * select Alias(the load balancer you created); allows DNS to map AWS service you created
    * TEST
      * make a request to the domain you registerd --> DNS server --> load balancer --> EC2
  * CLI Lab
    * create a user
      * 'Developer1' with 'Developer' group with programmatic access
      * get an access key id & secret access id
    * configure access
      * ssh into EC2 instance
      ```
      aws configure
      ```
      * enter access key id & secret access id.
      * enter nothing for region and output format -> sets as a default
    * create s3 bucket (the bucket name has to be unique)
      ```
      aws s3 mb s3://mjcloud123asdf
      ```
    * create txt file and copy into s3
      ```
      aws s3 ls                                  # you will see your s3 bucket
      echo "hello" > hello.txt                   # create a txt file
      aws s3 cp hello.txt s3://mjcloud123asdf    # copy a txt file to s3 bucket
      aws s3 ls s3://mjcloud123asdf              # verify a txt file in s3 bucket
      ```
    * try deleting access key
      * AWS UI -> Users -> Access Keys -> Delete access key
    * try 'aws s3 ls' with exising user & keys, it will fail
    * you have to configure again. repeat 'aws configure'
    * manage S3 in console
      * goto AWS console -> click 'mjcloud123
      * click 'hello.txt'
      * click 'make public'
      * open text file
      * now you can view txt file in AWS UI
    * you can refer to aws commands at https://docs.aws.amazon.com/cli/latest/reference/
    * Exam tips
      * give least privilege to a user.
      * create groups and assign to user(s) properly.
      * don't use a single access key. when a developer leaves a group -> delete it and create a new one. (don't reuse it)
  * EC2 with S3 Role
    * IAM -> Roles -> Create Role -> select entity type: AWS Service and service: EC2
    * select AmazonS3FullAccess Policy and create a role
    * EC2 -> select ec2 instance you created
    * right click the instance
    * click Attach/Replace IAM Role
    * you can use EC2 Role based
* RDS 101: AWS DB Services
  * SQL Server, Oracle, MySQL ... (OLTP = Online Transaction Processing = Receieves the request and handles query reqeust. ex) ATM)
  * RedShift (OLAP = Online Analytics Processing = Analyzes Data and provides the data. ex) Datawarehouse)
  * DynamoDB (NoSQL)
  * ElastiCache caches data in in-memory database, and it supports Memcached & Redis.
* RDS LAB
  * AWS UI -> RDS -> Launch DB
    * Select MySQL
    * Create DB (set username, password, hostname and dbname as acloudguru)
    * click RDS instance you created. copy public endpoint (acloudguru.cn4wzd2dzaem.us-east-2.rds.amazonaws.com)
  * AWS UI -> EC2 -> Launch EC2
    * keep everything as default
    * 3. Configure User Details
      * Place shell scripts for advanced details. This script will be executed during the launch.
      * make sure that the security group you use for this EC2 instance allows http as inbound.
        ```
        #!/bin/bash
        yum install httpd php php-mysql -y  
        yum update -y  
        chkconfig httpd on  
        service httpd start  
        echo "<?php phpinfo();?>" > /var/www/html/index.php              # create index.php with phpinfo();
        cd /var/www/html                                        
        wget https://s3.amazonaws.com/acloudguru-production/connect.php  # get connect.php from the link
        ```
     * ssh into your EC2 instance
       ```
       ssh ec2-user@18.191.182.21 -i T2.pem                    # ssh into an instance
       sudo su
       cd /var/www/html                                        # go to html directory
       nano connect.php
       replace $hostname with the public endpoint of your RDS  # acloudguru.cn4wzd2dzaem.us-east-2.rds.amazonaws.com
                                                               # enter crtl+x to get out of nano editor
       ```
    * configure security group
      * AWS -> EC2 -> Securit Group
      * select rds-lab-wizard
        * this is used by RDS instance by default. (go check RDS -> instance -> Security)
        * click -> inbound -> edit
          * add type: MySQL, port: 3306, source: myWebDMZ (type this it will give back a source)
  * BackUps
    * Automatic Backup
    * Manual DB Snapshot
    * AWS Console -> RDS
      * Select RDS instance -> check actions 'Restore to point in time, Take Snapshots ...'
      * Create a snapshot.
      * ON the left, click Snapshots. You will see previously taken snapshots.
      * Select the snapshot you created. Click 'Copy Snapshot'
  * Encryption
    * Done via AWS Key management (KMS)
    * If encrpyted, anything related to it (data, read replica, backup and snapshots) is encrypted.
  * Multi-AZ
    * scenario: RDS instance you created in AWS is in US-east-1A. You keep the identical DB in US-east-1B
    * Multi-AZ is for distarous recovery.
    * If you want performance enhancement -> go for 'Read Replicas'
  * Read Replicas
    * scenario: RDS instance you created in AWS has replications in the same US-east-1A. (or in different regions is possible).
    * When you get a heavy-load request to this DB, AWS will scale out and handle the load by spreading the request to DB replicas. 
    * Read Replicas is for Scaling (Performance).
    * You can have read replicas with Multi-AZ enabled.
      * AWS Console -> select DB
      * Instance Actions -> Create read replica
      * configure settings (you can enable multi-AZ here)
    * Read replicas can be promoted to a primary DB if you want. (This breaks replications)
  * ElastiCache
    * web cloud service that caches data in in-memory db to enhance performance. (low-latency access)
    * supports Redis and Memcached. Both are in-memory DB
    * ElastiCache for Memcached: pure caching, simple, scalability
    * ElastiCache for Redis: advanced data type (list, set, hash...), sorting & ranking, data persistency
      * data persistency: how likely is the data will remain safe? higher data persitency means safer the data is.

# SECTION4: S3
* S3 101
  * S3 = Simple Storage Service
  * Object based storage (not block storage)
    * allows you to upload files
    * block storage is a storage that holds data using raw volumes
  * unlimited
  * each file can be 0-5TB
  * files are stores in bucket (similart to folder)
  * s3 bucket name should be named universially unique. (globally unique)
  * HTTP 200 Code if upload was successful
  * Data Consistency Model
    * 1. Read and Write Consisteny: New file is immediately accessible
    * 2. Eventual Consistency: Delete or overwriting is not immediatley applied. (takes time to propagate)
  * Object consists of
    * Key: File name
    * Value: File data (key/value model)
    * VersionId: versioning information
    * Metadata: user defined data about data
    * Subresources: bucket specific configuration data
      * bucket policy, access control list, CORS(cross origin resource sharing), transfer accelaraion
