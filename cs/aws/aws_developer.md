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
    * make sure that the security group you use for this EC2 instance allows http as inbound because you want to hit this instance via url
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
      * AWS -> EC2 -> Security Group
      * select rds-lab-wizard (Don't confuse with Security Group that EC2 uses. This is Security Group that RDS uses.)
        * this is used by RDS instance by default. (go check RDS -> instance -> Security)
        * click -> inbound -> edit
          * add type: MySQL, port: 3306, source: myWebDMZ (type this it will give back a source
    * What will happen (TEST)
      * {ipaddressofEC2}/connect.php --HTTP-'myWebDMZ'--> [EC2] --'rds-lab-wizard'--> [RDS]
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
* Multi-AZ (AZ = Availability Zone)
  * scenario: RDS instance you created in AWS is in US-east-1A. You keep the identical DB in US-east-1B
  * Multi-AZ is for distarous recovery. (AZ Failure)
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
  * IN-MEMORY CACHING: Memcached --Complexity--> Redis

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
    * Create -> Read after Write Consisteny -> New file is immediately accessible.
    * Update/Delete -> Eventual Consistency -> Delete or overwriting is not immediatley applied. (takes time to propagate)
  * Object consists of (5core/4subresources)
    * Key: File name
    * Value: File data (key/value model)
    * VersionId: versioning information
    * Metadata: user defined data about data
    * Subresources: bucket specific configuration data
      * (4): bucket policy, access control list, CORS(cross origin resource sharing), transfer accelaraion
  * Tiers
    * S3 Standard
      * nine9 durability : 99.999999999% (unlikely to loss data)   # but data will be there anyway.
      * two9 availability: 99.99%        (likely to be accessible) # connection can be messed up
    * S3 Standard IA (Infrequently Accessed)
      * nine9 & one9
      * lower fee but charged retrieval fee for all S3 IA object.
    * S3 One Zone IA
      * nine9 & 99.5
      * same as S3 -IA, but is stored in a single availibility zone.
    * Reduced Redundancy Storage
      * 99.99% availibility and durability to provide one-year storage.
      * good for data that can be easiliy regenerated if lost.
    * Glacier
      * Very cheap, Verp slow. Works for very infrequently accessed data (historic archive)
    * S3 Intelligent Tiering
      * same availability and durability as Standard S3.
      * good for unknown access patterns.
      * It has two tiers (frequent/infrequent) and it automatically moves your data to cost-effective tier.
      * no retrieval fee but has very small monthly maintenance fee.
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
* S3 Policies Lab
  * AWS Console -> S3 -> Create Bucket
    * Version: you can choose to keep versions all objects.
    * Log: you can log all request to bucket.
    * Tag: you can set up taggings to track usage.
    * Object level logging: via CloudTrail
    * Encryption: AES256(256bit) / AWSKMS(key management system) (these are server-side encryption)
    * Metrics: via CloudWatch (AWS metrics sercice)
    * Public Access: you can configure public accessibility.
      * by default: it is completly "private" and S3 Log Delivery group has no write access
  * Click created bucket
    * create folders
    * try uploading a file
    * click file and try access with url: ACCESS DENIED because it is "private" by default
    * goto bucket -> permissions -> enable public access
    * goto a folder -> upload a file with public access -> try url access -> it works!
    * bucket policy is in json format
      * bucket -> permissions -> policy
      * policy generator
        * principal is the entity you are applying policy to (entity = user ARN; AWS Console -> IAM -> Users -> get User ARN)
        * amazon resource name is the bucket arn
        * click generate -> copy json -> paste into policy editor
* S3 Encryption
  * S3 Encryption "in Transit"
    * SSL/TLS
    * encrypts data in network
  * S3 Encryption "at Rest"
    * SSE (Server Side Encryption)
      * SSE-S3
        * S3 Managed Key
        * x-amz-server-side-encryption: AES256
      * SSE-KMS
        * AWS Managed Key (=Key Management Service)
        * x-amz-server-side-encryption: KMS
      * SSE-C (Customer Managed Key)
    * CSE (Client Side Encryption)
      * You encryption data on your own (application level)
  * When object is uploaded to S3, PUT method is initiated.
  * If you want to enforce encryption, deny all PUT without x-amz-server-side-encryption expectation.
* S3 Encryption Lab (Server Side Encryption)
  * you can enable SSE in console during creating a s3 bucket but lets enable SSE after creating a bucket.
  * create a bucket and click that bucket
  * click permissions tab -> bucket policy
  * click policy generator
    * type of policy: select S3 bucket policy
    * principal: * (all)
    * effect: you can either allow or deny an access using this policy
    * action: Put object
    * ARN: arn is arn with s3 name ex) arn:aws:s3:::encryptionlab1289841239 when bucket name is encryptionlab1289841239
    * click add condition -> Condition: StringNotEquals & Key: amz-serverside-encryption & Value: aws:kms
    * generate it
  * copy policy and add to S3 bucket policy section
  * then you will see an error: Action does not apply to any resource(s) in statement
  * just add /* after ARN that you added (often aws doesn't allow one resource as a target, so put /*)
  * try uploading a file to this S3 (in set properties, select none as encryption) -> fail
  * try uploading a file to this S3 (this time, select AWS-KMS: aws/s3) -> success
* S3 CORS (Cross Origin Resource Sharing)
  * create a public bucket (bucketA)
  * click bucket -> static website hosting
    * select use this bucket to host a website
    * aws provides endpoint url in the top
    * create index.html & error.html for index & error pages
    * save
  * click bucket -> overview -> upload
    * upload index.html, error.html, loadpage.html (download from udemy)
    * grant public access and upload
  * try hitting the static hosting url from browser and see the content
  * now create a new bucket (bucketB)-> static website hosting
    * upload loadpage.html with public access
  * go back to bucketA
    * modify index.html and replace <script> in a way that it loads loadpage.html as its url
      ```
      <script>
      $("#{div-id}").load(http://bucketA.s3-website.ca-central-1.amazonaws.com/loadpage.html)
      </script>
      ```
    * if you try bucket static page index.html you will see 403error in chrome console (blocked by CORS policy)
  * go back to bucketB
    * go to permissions
    * go to CORS configuration
    * add url of bucketA to AllowedOrigin
  * by default bucket has no access to different bucket -> you can configure with CORS policy
* CDN (content delivery network)
  * web service that speeds up web content delivery based on geographic location of a user.
  * edge location handles web requests from nearby locations instead of directly hitting origin
    * edge location (!= AWS Region) (!= AZ)
      * geographically dispersed data center that caches web contents
      * you can also write contents you want inside edge location
    * origin
      * origin of all files that CDN will distribute
  * AWS Cloudfront is Amazaon Web Service CDN. It is optimized to work with other AWS services.
    * RTMP: media streaming CDN
    * Web Distribution: web site CDN (HTTP/HTTPS)
  * S3 CloudFrond Lab
    * Create a bucket
      * crete a bucket in a region that is very far from your location
      * upload images. (1.jpg, 2.jpg, 3.jpg)
    * UI -> CloudFront -> Create Distribution
      * you have two options. WEB vs RTMP
      * select Web -> Get Started
        * origin: select s3 bucket you created (origin is the actual data center where data remains)
        * restrict bucket access: yes (all request to this bucket will go thru cloudfront)
        * origin access identity: create a new identity (create a new user for cloudfront to access s3)
        * grant access: Yes, Update Bucket Policy (by default new user will have no access. let aws handle this)
        * configure -> Protocol(Http/Https), Requests
        * restrict viewer access
          * grant access to a user based on "signed url" or "signed cookies"
          * lets say a user paid to view the content, then assign a url to a certain user (for example udemy)
          * for this lab select no.
    * CloudFront -> click the distribution you created
      * Restriction tab -> you can configure regional restriction (ex. block access from China)
    * go to bucket you created, which you created cloudfront for.
      * delete public read access for an image 1.jpg
      * you can't access this file vai URL
    * now copy url of your cloudfront and replace it with s3 url, except file name part.
      ex) https://labcf139.s3.eu-west-3.amazonaws.com/1.jpg -> 328r9328.cloudfront.net/1.jpg
    * now your cloudfront will pull 1.jpg from s3 bucket. this works because access from cloudfront is not public. note that you created a origin access identitiy for this s3 bucket. the first time you pull this image is relatively slow. next time you try from a ingocnito browser tab. this request is quick.
  * S3 optimization
    * S3 optimization is based on workload you are running
      * GET intensive workload: you use s3 for excessive GET requests.
        * optimization: use cloudfront to speed it up.
      * Mixed request type workload: you use s3 for various request types.
        * s3 uses key name to determine the partition it will use to store this data.
        * optimization: use random key prefix for each object. so that it is randomly distributed.
        * if files in the same partition is frequently requested. this will cause IO issue.
        * TTL (time to live): how long will you cache data in edge location
   * S3 performance update
     * 3500 put/sec
     * 5500 get/sec
     * AWS updated its bucket performance. you don't need to randomize object key prefix to enhance s3 performance.
   * S3 101 Summary
     * s3 is object based
     * unlimited storage but each file should be 0byte - 5TB
     * single operation can handle 5GB (PUT update -> 5GB is the limit)
     * files are stored in bucket
     * universal naming
     * put(create): read after write consistency -> immediate
     * put(update) or delete: eventual consistency -> takes time
     * don't use bucket url -> use s3 website url (url starts as https://s3-~~~~~)


# SECTION5: Serverless Computing
* AWS Lambda
  * Lambda is abstract layer of 'Data Center + Hardware + Assembly Code + High Level Languages + OS + Application Layer)
  * Language Support: Node.js Java Python C# Go
  * first 1million requests are free. 20cents per 1million requests after.
  * lambda scales out automatically (not scale up)
  * AWS X-ray debugs AWS Lambda
  * Lambda can work globally
* API Gateway
  * API = application programming interface = set of features that utilize an application.
  * REST API uses JSON
  * SOAP API uses XML
  * AWS API Gateway is a fully managed API service
  * API caching: you can cache endpoint response for redundant requests for TTL.
  * same origin policy <--> CORS
  * CORS is a client sided thing
    ```
    CORS by example:

    A web browser loads a page from www.example.com.
    The page includes a script that makes a request to www.example.org.
    The origin of the request is www.example.com.
    The browser either makes the request or sends an OPTIONS request first (the preflight request).
    When the server at www.example.org receives a request from an origin other than www.example.org
    it responds with a response header Access-Control-Allow-Origin which tells the browser the origins
    allowed to make requests.
    It may also respond with other headers like Access-Control-Allow-Methods and Access-Control-Allow-Headers that can 
    restrict the types of allowed requests.
    When the browser is told what origins are allowed it will block future requests from disallowed origins.
    ```
  * you can log results to CloudWatch
  
