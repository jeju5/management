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
  * Allows you to manage users & level of access to AWS console
* Terms
  * User: end user
  * Group: group of Users
  * Role: Role with in the service (set of permissions)
  * Policy: Permission defining JSON (document)
* IAM LAB
  * Services -> Security, Identity, & Compliance -> IAM
    * click Customize (you can customize url for sign-in link)
    * you will have to delete existing setup if you customize url
  * MFA (Multi Factor Authorization)
    * Security Status -> Manage MFA -> Virtual MFA device -> continue
    * Install MFA app into my phone
    * Scan QR CODE with your phone -> try Authenticator login
  * User
    * add/delete a AWS user
  * Groups
    * group of users
  * Policy
    * defines permission
    * written in JSON
    * delegate policy(es) to a group or directly assign to a user
  * Role
    * set of permissions assigned to an entity(ex. EC2)

# SECTION3: EC2
* EC2 101
  * EC2 = ECC = Elastic Cloud Computing = Computing Service in Cloud
  * Pricing
    * on demand: price by time -> good for short-term need (short-term also means specific short time)
    * reserved: capacity reservation -> good for regular-basis need
    * spot: capacity bidding -> good for flexible-time need (start time & end time), good for temporary additional need
      * if terminated partial hour usage -> if AWS EC2 terminates it, you won't be charged / if you terminate it, you will be charged for a complete hour
    * dedicated host: physical EC2 server -> good for region-specific regulations
  * Instance Type (optimized for)
    ```
    "MONEY-HONDA-GOLF-SPECIALFORCE"
   
    ATM           C|RX       PGF       HID
    Money         Honda      Golf   SpecialForce
    General  Compute|Memory  GPU    Storage
    
    * GPU = accelerated
    ```
    * General: A T M
    * Compute: C
    * Memory: R X(extreme)
    * Accelerated: P G F
    * Storage: H I D
  * EBS = elastic block store
    * Volumes that can be attached to EC2
    * aws instance is placed at availability zone. (any instance like db, ec2, ...)
      * AWS Region: geographic area
      * AWS Availability Zone: resource isolation within AWS Region
      * Availability Zones < Region (ex. US-east-1; default regions)
    * If you want to encrypt data, you have to configure encryption when creating EBS Volume
    * Types
      * SSD
        * GP2 < 16000 < IO1
        * General Purpose SSD = GP2 (General): 3IOPS/GB, max 16,000 IOPS (IOPS = input/output operations per second)
        * Provisioned IOPS SSD = IO1 (Intensive): proper for more than 16,000 IOPS
      * HDD
        * SC1 < Optimized < ST1 (op"T"imized)
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
  * Services -> EC2 -> Launch Instance
    * you will see VMs, select what you want
    * then select instance type: "ATM C|RX PGF HID"
    * then configure instance
      * purchasing options, number of instances, network, shutdown behavior(STOP, Terminate) ... etc
      * terminate means remove
    * add storage (EBS)
      * SSD HDD MAGNETIC
    * add tag
      * key/value pair to be used.
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
        * upon launching an instance you will be prompted to "Select an existing key pair or create a new key pair"
        * you will need public key and private key
          * public/private key
            * symmetric key encryption
              ```
              PLAIN-TEXT ---encrypt with key1---> CIPHER-TEXT ---decrypt with key1---> PLAIN-TEXT
              ```
            * asymmetrical key encryption
              ```
              PLAIN-TEXT ---encrypt with key1---> CIPHER-TEXT ---decrypt with key2---> PLAIN-TEXT

              * key1(public key): can be shared to the public
              * key2(private key): should be kept as a secret
              ```
        * create them and download them (or select an existing one)
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
      ```
      <OSI MODEL>
      LAYER7: Application
      LAYER6
      LAYER5
      LAYER4: Transport
      LAYER3
      LAYER2
      LAYER1
      ```
      * layer4 load balancing: operates at Transport layer
      * layer7 load balancing: operates at Application layer
      * Types
        * Application Load Balancer: HTTP, HTTPS. works within the application. incoming app traffic control
        * Network Load Balancer: TCP works at layer-4, incoming network traffic controls
        * Classic Load Balancer: HTTP, HTTPS, TCP, SSL. doesn't look at the request. single port mapping
      * when your load balancer fails, it throws 504 error (gateway timeout)
      * x-forwarded-for header
        * Public IP makes a request -> DNS -> Load Balancer -> Application Server
        * Application Server will get public ip as "x-forwarded-for header"
  * Route53 Lab
    * Route53 is AWS DNS service. It maps the request to EC2, S3 or load balancer
    * Services -> Route 53 -> DNS management 'Get Started Now'
    * Registered Domain -> Register Domain
      * buy a domain or register an existing one you own
    * Hosted Zones -> Create Hosted Zone
      * select the domain you registered
      * create record set
      * select Alias(the load balancer you created); allows DNS to map AWS service you created
      * select Application Load balancer for our use (flexible and general)
      * configure name, listeners(protocol(HTTP/HTTPS) and port) and zones(region)
      * configure security group
      * configure routing (DNS Name --mapping--> App Server); you can route a request to Instance, IP, Lambda function
      * select EC2 Instance you created
      * configure health check
    * TEST
      * make a request to the domain you registerd --> DNS server --> load balancer --> EC2
  * CLI(Command Line Interface) Demo
    * create a user
      * 'Developer1' with 'Developer' group with programmatic access
      * get an access key id & secret access id
    * create a s3 bucket (the bucket name has to be unique)
      ```
      aws s3 mb s3://mjcloud123asdf
      ```
      * this will create a s3 bucket
    * configure access
      * ssh into EC2 instance and run
        ```
        aws configure
        ```
      * you will be prompted for more information
      * enter access key id & secret access id. (from 'create a user' step)
      * enter nothing for region and output format -> sets as a default
    * create txt file and copy into s3
      ```
      aws s3 ls                                  # you will see s3 bucket list
      echo "hello" > hello.txt                   # create a txt file
      aws s3 cp hello.txt s3://mjcloud123asdf    # copy a txt file to s3 bucket
      aws s3 ls s3://mjcloud123asdf              # verify a txt file in s3 bucket
      ```
    * try deleting access key
      * AWS UI -> Users -> Access Keys -> Delete access key
    * try 'aws s3 ls' with exising user & keys, it will fail
    * you have to configure again. repeat 'aws configure' and enter additional data.
    * manage S3 in console
      * goto AWS console -> S3 -> click mjcloud123
      * click 'hello.txt'
      * click 'make public'
      * open text file
      * now you can view txt file in AWS UI
    * you can refer to aws commands at https://docs.aws.amazon.com/cli/latest/reference/
    * Exam tips
      * give least privilege to a user.
      * create groups and assign to user(s) properly.
      * don't use a single access key. assign keys to different roles/users/groups.
      * when a developer leaves a group -> delete it and create a new one. (don't reuse it)
  * RoleBased EC2
    * Create a S3 bucket
    * Create a user that has no access
    * SSH into this EC2 instance with this user and try 'aws s3 ls'. It will fail.
    * IAM -> Roles -> Create Role -> select entity type: AWS Service and service: EC2
    * select AmazonS3FullAccess Policy and create a role
    * EC2 -> select ec2 instance you created
    * right click the instance, click Attach/Replace IAM Role
    * you will still have to delete existing config & credentials (which stores keys, regions ...etc)
      ```
      cd ~/.aws
      rm credentials
      rm config
      ```
    * Try 'aws s3 ls'. It will list s3 buckets. You will also have availability to s3 features further on.
    * you can use EC2 Role based!
      * what does this mean?
      * whenever you use this instance, you will have all permission that this Role has.
      * if you try EC2 with a user that has no access, you will still have some permission to this RoleBasedEC2
    * EXAM TIP
      * Roles alloow you to not use Keys (Access Keys, Secret Keys)
      * Rolse are preferred (security perspective) because it is easily detached/attached to instances without having to 
        stop or terminate instances.
    
* RDS 101: AWS DB Services
  * SQL Server, MySQL...(OL"T"P = Online Transaction Processing = Receieves the request and handles query reqeust. ex) ATM)
  * RedShift (OL"A"P = Online Analytics Processing = Analyzes Data and provides the data. ex) Datawarehousing)
  * DynamoDB (NoSQL)
  * ElastiCache caches data in in-memory database, and it supports Memcached & Redis.
* RDS LAB
  * AWS UI -> RDS -> Launch DB
    * Select MySQL
    * Create DB (set username, password, hostname and dbname as acloudguru)
    * click RDS instance you created. copy public endpoint (acloudguru.cn4wzd2dzaem.us-east-2.rds.amazonaws.com)
  * AWS UI -> EC2 -> Launch EC2
    * keep everything as default
    * Configure User Details
      * Place shell scripts for advanced details section (as Text). This script will be executed during the launch.
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

     * ssh into your EC2 instance and modify connect.php to your rds endpoint
       ```
       ssh ec2-user@18.191.182.21 -i T2.pem      # ssh into an instance
       sudo su
       cd /var/www/html                          # go to html directory
       nano connect.php                          # replace $hostname with the public endpoint of your RDS  
                                                 # acloudguru.cn4wzd2dzaem.us-east-2.rds.amazonaws.com
                                                 # enter crtl+x to get out of nano editor
       ```
    * configure security group
      * make sure that the security group you use for this EC2 instance allows http as inbound because you want to hit this instance via url
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
    * unavailable for OS, DB, Block
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
  * Tiers
    * S3: 99.99% availability (likely to be accessible) & 99.999999999% durability (unlikely to loss data)
    * S3 Intelligent Tiering
      * good for unknown access patterns.
      * It has two tiers (frequent/infrequent) and it automatically moves your data to cost-effective tier.
      * same availability and durability as regular S3.
      * no retrieval fee but has very small monthly maintenance fee.
    * S3 IA (Infrequently Accessed): lower fee but charged retrieval fee.
    * S3 One Zone IA: same as S3 -IA but is stored in a single availibility zone. 99.999999999% durability & 99.5% availibility.
    * Reduced Redundancy Storage: 99.99% availibility and durability to provide one-year storage.
      * good for data that can be easiliy regenerated if lost.
    * Glacier: Very cheap, Verp slow. Works for very infrequently accessed data (historic archive)
  * Charges
    * Storage (per GB)
    * Requests (Get, Put, Copy)
    * Storage Management (Analytics, Tagging)
    * Data Management (Data transfer out of S3; if data is transferred into S3, its free)
    * Transfer Acceleration (Use of Cloudfront)
* S3 Security
  * Bucket is private by default (only owner has access)
  * You can set up
    * bucket policy: applied at bucket level
    * access control list: applied at object level
    * you configure buckets to log every access and request
* S3 Lab
  * AWS Console -> S3 -> Create Bucket
    * Version: you can choose to keep versions all objects.
    * Log: you can log all request to bucket.
    * Tag: you can set up taggings to track usage.
    * Object level logging: via CloudTrail
    * Encryption: AES256(256bit) / AWSKMS(key management system) (these are server-side encryption)
    * Metrics: via CloudWatch
    * Public Access: you can configure public accessibility.
      * by default: it is completly private and S3 Log Delivery group has no write access
  * Click created bucket
    * create folders
    * try uploading a file
    * click file and try access with url: ACCESS DENIED because it is private by default
    * goto bucket -> permissions -> enable public access
    * goto a folder -> upload a file with public access -> try url access -> it works!
    * bucket policy is in json format
      * bucket -> permissions -> policy
      * policy generator
        * principal is the entity you are applying policy to (entity = user ARN; AWS Console -> IAM -> Users -> get User ARN)
        * amazon resource name is the bucket arn
        * click generate -> copy json -> paste into policy editor
