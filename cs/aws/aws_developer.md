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
* IAM Policy Simulator
  * Test the effect of Policy before committing it.


# SECTION3: EC2
* EC2 101
  * EC2 = ECC = Elastic Cloud Computing = Computing Service in Cloud
  * Pricing
    ```
    onDemand=ShortTime
    reserved=Regular
    Spot=FlexibleTime
    DedicatedHost=RegionSpecific
    ```
    * on demand: price by time -> good for short-term need (short-term also means specific short time)
    * reserved: capacity reservation -> good for regular-basis need
    * spot: capacity bidding -> good for flexible-time need (start time & end time), good for temporary additional need
      * if terminated partial hour usage -> if AWS EC2 terminates it, you won't be charged / if you terminate it, you will be charged for a complete hour
    * dedicated host: physical EC2 server -> good for region-specific regulations
  * Instance Type (optimized for)
    ```
    ATM'General' --- C'Compute' --- RX'Memory' --- PGF'Accelerated' --- HID'Storage'
    ```
  * EBS = elastic block store
    * Volumes that can be attached to EC2
    * aws instance is placed at availability zone. (any instance like db, ec2, ...)
      * AWS Region: geographic area
      * AWS Availability Zone: resource isolation within AWS Region
      * Availability Zones < Region (ex. US-east-1; default regions)
    * If you want to encrypt data, you have to configure encryption when creating EBS Volume
    * Types
      ```
      SSD: 'GP2' < 16000IOPS < 'IO1'
      HDD: 'SC1'(fileserver) < OPtimized < 'ST1'(datawarehousing)   (can't be boot volume)
      Magnetic: 'legacy'
      ```
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
            * symmetric key encryption (encrypt/decrypt with the same key)
              ```
              PLAIN-TEXT ---encrypt with key1---> CIPHER-TEXT ---decrypt with key1---> PLAIN-TEXT
              ```
            * asymmetrical key encryption (encrypt/decrypt with the differnt keys; decryption key is private key)
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
      LAYER7: Application --> layer7 load balancing: operates at Application layer
      LAYER6
      LAYER5
      LAYER4: Transport  --> layer4 load balancing: operates at Transport(Network) layer
      LAYER3
      LAYER2
      LAYER1
      ```
      * Types
        * Application Load Balancer: HTTP/HTTPS. works within the application. incoming app traffic control
        * Network Load Balancer: TCP/SSL. incoming network traffic controls
        * Classic Load Balancer: HTTP, HTTPS, TCP, SSL. doesn't look at the request. single port mapping
      * when your load balancer fails, it throws 504 error (gateway timeout)
      * x-forwarded-for header
        * Requester (Public IP) -> DNS -> Load Balancer -> Application Server
        * Application Server will get Requester (public ip) as "x-forwarded-for header"
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
      * when a developer leaves a group -> delete the key and create a new key. (don't reuse it)
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
  * OLTP (OL"T"P = Online Transaction Processing)
    * SQL Server, MySQL...
    * Receieves the request and handles query reqeust. ex) ATM)
  * OLAP (OL"A"P = Online Analytics Processing)
    * RedShift
    * Analyzes Data and provides the data. ex) Datawarehousing)
  * DynamoDB (NoSQL)
  * ElastiCache caches data in in-memory database, and it supports Memcached & Redis.
* AWS RDS(Relational Database) LAB
  * AWS UI -> RDS -> Launch DB
    * Select MySQL
    * Create DB (set username, password, hostname and dbname as acloudguru)
    * click RDS instance you created. copy public endpoint (acloudguru.cn4wzd2dzaem.us-east-2.rds.amazonaws.com)
  * AWS UI -> EC2 -> Launch EC2
    * keep everything as default
    * configure User Details
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
          * add type: MySQL, port: 3306, source: myWebDMZ (type this it will give back a source)
    * What will happen (TEST)
      * {ipaddressofEC2}/connect.php --HTTP-'myWebDMZ'--> [EC2] --'rds-lab-wizard'--> [RDS]
* BackUps
  * composed of 'Automatic Backup' and 'Manual DB Snapshot' (backup=entire copy of db, snapshot=readonly copy of db at certain point)
  * AWS Console -> RDS
    * Select RDS instance -> check actions 'Restore to point in time, Take Snapshots ...'
    * Create a snapshot.
    * ON the left, click Snapshots. You will see previously taken snapshots.
    * Select the snapshot you created. Click 'Copy Snapshot'
* RDS Encryption
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
* EC2 Auto Scaling Group
  * An Auto Scaling group contains a collection of Amazon EC2 instances that are treated as a logical grouping for the purposes of automatic scaling and management.
  * It ensures that proper number of EC2 Instances are used to handle the load of application

# SECTION4: S3
* S3 101
  * S3 = Simple Storage Service
  * Object based storage (not block storage)
    * allows you to upload files
    * block storage is a storage that holds data using raw volumes
    * unavailable for OS, DB, Block
  * unlimited total storage
  * each file can be 0-5TB
  * files are stores in bucket (similart to folder)
  * s3 bucket name should be named universially unique. (globally unique)
  * HTTP 200 Code if upload was successful
  * Data Consistency Model
    * Create -> Read after Write Consisteny -> New file is immediately accessible.
    * Update/Delete -> Eventual Consistency -> Delete or update is not immediatley applied. (takes time to propagate)
  * Object consists of (5core/4subresources)
    ```
    - 1.Key
    - 2.Value: 
    - 3.VersionId
    - 4.Metadata
    - 5.Subresources
      - 5-1.bucket policy
      - 5-2.ACL(access control list: privilege & access configuration)
      - 5-3.CORS(cross origin resource sharing)
      - 5-4.transfer accelaration
        
    Key: File name
    Value: File data (key/value model)
    VersionId: versioning information
    Metadata: user defined data about data
    Subresources: bucket specific configuration data
    ```
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
      * Very cheap, Verp slow. Works for very infrequently accessed data (proper for historic archive)
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
    * SSE (Server Side Encryption) via 'SSE-S3/SSE-KMS/SSE-C'
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
    * edge location (!= AWS Region) (!= AZ) (=nearby caching location)
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
     * AWS updated its bucket performance. and you don't need to randomize object key prefix to enhance s3 performance.
   * S3 101 Summary
     * s3 is object based
     * unlimited storage but each file should be 0byte - 5TB
     * single operation can handle 5GB (PUT update -> 5GB is the limit)
     * files are stored in bucket
     * universal naming
     * put(create): read after write consistency -> immediate
     * put(update) or delete: eventual consistency -> takes time
     * don't use bucket url -> use internal s3 website url (url starts as https://s3-~~~~~ since s3 is aws service in public sector)

# SECTION5: Serverless Computing
* AWS Lambda
  * Lambda is abstract layer of 'Data Center + Hardware + Assembly Code + High Level Languages + OS + Application Layer)
  * Language Support: Node.js Java Python C# Go
  * first 1million requests are free. 20cents per 1million requests after.
  * lambda scales out automatically (not scale up)
  * AWS X-ray debugs AWS Lambda
  * Lambda can work globally
  * Lambda Concurrent Execution
    * There is a concurret execution limit for Lambda
    * default is 1000 execution per region (you can increase this limit)
    * HTTP 429 TOO MANY REQUESTS error
    * Reserved Concurrency guarantees number of execution sets for critical Lambda function (this also have a limit)
  * Lambda Version
    * When you create a new Lambda function. Its version is $LATEST
    * You can create multiple versions and use it with alias to that version
  * Access to VPC (Virtual Private Cloud)
    * You can make Lambda to access third party VPC by providing private subnet id and security group id with vpc-config parameter (or in UI)
      ```
      aws lambda update-function-configuration \
      --function-name myFunction \
      --vpc-config SubnetIds=subnet=1122aa,SecuritGroupIds=sg-12321
      ```
      * private subnet id
      * security group id
      * then Lambda sets up ENIs
* API Gateway
  * API = application programming interface = set of features that utilize an application.
    * REST API uses JSON
    * SOAP API uses XML (you can configure SOAP API pass through)
  * AWS API Gateway is a fully managed API service
  * API caching: you can cache endpoint response for redundant requests for TTL.
  * same origin policy <--> CORS
  * CORS is a client sided thing
    ```
    CORS by example:

    [browser] -> [www.example.com] -> [www.example.org]
                  Origin1              Origin2
                                       Access-Control-Allow-Origin
                                       
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
  * you can track usage by api key
  * you can maintain multiple versions
  * API Gateway supports Swagger Import, which creates an API based on the documentation
  * you can update existing api endpoint with overwrite/merge.
  * AWS API Gateway capacity
    * 10,000 requests per second
    *  5,000 concurrent requests (across all APIs with in a single account)
    * if you reach the capacity you will get 429 Too Many Reqeusts response
* Build a serverless website
  * S3
    * create an S3
    * enable static website hosting of this bucket and set index.html
  * Route53
    * register a domain
  * Lambda
    * create a Lambda
    * select python
    * select author from scratch
    * role: myLambdaRole
    * policy: simple microservice template
    * paste following for function code
      ```
      function that returns a response with Ryan Kroonenburg body and 200 status.
      
      def lambda_handler(event, context):
          print("In lambda handler")

          resp = {
              "statusCode": 200,
              "headers": {
                  "Access-Control-Allow-Origin": "*",
              },
              "body": "Ryan Kroonenburg"
          }

          return resp
      ```
    * add trigger: select API Gateway
    * select create new API
    * click API Gateway you created
      * by default there is 'ANY' method, delete this method
      * create a 'GET' method
      * select integration type: Lambda
      * allow proxy integration
      * upon click Save, you get an ARN
    * select action -> deploy API
    * goto API Gateway
      * select 'GET' method trigger you created
      * copy 'invoke url' and load it on the browser.
      * on terminal try the following and verify mytext.txt
        ```
        curl "{invokeURL}" > mytext.txt        
        ```
    * goto S3
      * open and edit static index.html and add GET call to the new API Gateway endpoint you created
        ```
        xhttp.open({invokeURL}, true)
        ```
      * make S3 bucket public
    * Route53
      * open hosted zone -> open domain you registered
      * create Record Set
      * select 'Alias Target: S3 bucket you create'
      * verify that you are redirected to a page built with API Gateway & Lambda & S3 when hitting a domain url you created.
* Lambda Version Control
  * each version of lambda function will have a unique ARN
  * $LATEST is the latest version value of lambda function
  * ARN
    * qualified ARN: arn with version suffix
    * unqualified ARN: arn without version suffix
  * you can create an alias for each version of lambda function
  * LAB
    * goto Lambda
    * create a functinon 'someFunc'
    * select the function and click qualifiers.
    * select $LATEST as its version
    * click version: click publish new version and add 'v1' as its description
    * modify code and do same with 'v2' and 'v3'
    * select Actions -> create alias
    * select the version of lambda you want as its alias
    * you can split traffic to different versions by if you select 'additional version' and 'weight'
      * you can't target $Latest version and split traffic at the same time. this feature is supported only for non-latest versions.
  * Alexa Lab
    * create a S3 bucket
    * copy ARN of the bucket. (ARN = Amazon Resource Name)
    * make a bucket public by pasting the policy for this bucket. this policy allows public read/get
      ```
      {
          "Version": "2012-10-17",
          "Statement": [
              {
                  "Sid": "PublicReadGetObject",
                  "Effect": "Allow",
                  "Principal": "*",
                  "Action": "s3:GetObject",
                  "Resource": "{bucket resource arn}/*"   <--- copy the arn of bucket policy
              }
          ]
      }
      ``` 
    * goto Amazon Polly
      * type in some text into Text-to-speech section
      * click synthesize to S3
      * select S3 output bucket and enter your bucket name
      * click synthesize
      * goto AWS Polly -> S3 synthesis task. you will see synthesis task you created
      * goto S3 bucket and verify that mp3 file is there.
      * goto AWS Lambda
      * click create Function
      * select aws serverless application repository (this is like public repo for aws lambda)
      * select alexa-skills-kit-nodejs-factskill application
      * click deploy
      * in Lambda dashboard you will see function that is deployed. click it.
      * copy arn of this lambda function
      * goto developer.amazon.com and hit amazon alexa
      * click create alexa skill
      * click custom and click create skill
      * choose a template 'fact skill'
      * in dashboard paste lambda function arn into default region section
      * click save endpoint. this will host Alexa to this lambda endpoint.
* AWS Step Function
  * allows visualization of workflow & status
  * Amazon States Language is the language that builds this step function (it is JSON-based)
  * it logs state of each step
  * LAB
    * goto AWS UI -> Step Function
    * click get Started
    * click sample project (this visualizes sample Batch jobs that AWS provides as an example)
    * click job status poller
    * click create Resources
    * enter an execution name and click start execution
    * goto AWS UI -> Batch
    * click Jobs and see batch job is generated
    * goto AWS UI -> Step Function and see how this job is being executed.
    * goto AWS Lambda and see lambda functions that this job created
* X-Ray
  * X-Ray collects data on requests that application serves and allow users to view them.
  * X-Ray vs CloudTrail
    * X-ray is a troubleshooting tool, recording everything possible
    * CloudWatch is a API logger
  * Architecture
    * EC2 -> X-Ray SDK & X-Ray Daemon -> X-Ray Daemon -> X-Ray API -> X-Ray Console
  * X-Ray SDK provides
    * interceptors to trace HTTP requests
    * client handlers to instrument AWS SDK clients
    * HTTP client to call internal/external HTTP web services
  * LAB
    * AWS UI -> BeanStalk
      * ApplicationName: scoreKeep, Code: Java
      * use sampleApplication
      * after created
    * go to IAM -> Role
      * select AWS ElasticBeanStalk EC2 Role
      * attach policy
        * X-Ray Full Access, S3 Full Access, DynamoDB Full Access
    * BeanStalk
      * click 'Click to generate more data' to crete sample data traffic a few times
      * in X-Ray you will be able to see data trace
    * BeanStalk
      * goto its dashboard, under RunningVersion upload source code of scoreKeep as zip file (from Udemy) and click deploy.
      * this makes your BeanStalk to tic tac toe game app. play with it.
    * AWS UI -> X-Ray console
      * see that scoreKeep app data are traced
      * click service node you are interested in and you can trace details.
    * Xray integrates with ElasticLoadBalancing, S3, Lambda, EC2, ElasticBeanStalk
* Exam Tips
  * Lambda Scales out, not scales up (this means you have flexibility on number of concurrent lambda services running at the same time)
  * Lambda can work globally (ex. backup S3 bucket A to S3 bucket B)

# SECTION6. DYNAMO DB
* Intro
  * DYNAMO DB is AWS NO SQL DB.
  * supports document and key/value pairs
    * supported document: JSON, HTML and XML
  * Item: similar to row in table
    * each item is made of key/value pairs
  * Attribute: similar to column in table
  * DynamoDB stores/retrieves data based on a primary key
    * partition key: unique attribute -> each item has its unique partition key
    * composite key: combination of partition key and sort key -> each item has its unique "partition key with sort key".
  * DynamoDB access control
    * authentication and access control is managed by AWS IAM
    * ex) how to make certain user can view only its data
      * add userId in dynamodb:LeadingKeys where you maintain db with its userId being partitionKey (leadingKey=partitionKey)
      ```
      "Condition": {
        "ForAllValues:StringEquals": {
            "dynamodb:LeadingKeys": [
                "${www.amazon.com:user_id}"
            ],
            "dynamodb:Attributes": [
                "UserId",
                "GameTitle",
                "Wins",
                "Losses",
                "TopScore",
                "TopScoreDateTime"
            ]
      },
      ```
* Create DynamoDB Table lab
  * Create a Role
    * IAM -> Create Role
    * select AWS Service, next
    * select AmazonDynamoDBFullAccess policy
    * create a role 'DynamoDB'
  * Create an Instance and table
    * EC2
    * select Linux, t2micro, DynamoDB role
    * add script
      ```
      #!/bin/bash
      yum update -y
      yum install httpd php git -y                     
      service httpd start                   # apache service
      chkconfig httpd on
      cd /var/www/html
      echo "<?php phpinfo();?>" > test.php
      git clone https://github.com/acloudguru/dynamodb
      ```
    * select a security group that has http & ssh.
    * launch instance
    * copy public IPv4 address
    * ssh into this instance and verify test.php and dynamodb git repo you set up with shell script
      ```
      ssh -i keypair.pem ec2-user@35.176.32.146
      sudo su
      cd /var/www/html
      ```
    * install aws sdk for php (https://docs.aws.amazon.com/sdk-for-php/v3/developer-guide/getting-started_installation.html)
    * hit url page that runs createtable php scripts (http://35.176.32.146/dynamodb/cretetables.php)
  * Play with table
    * Console -> DynamoDB
    * see 4tables that php script created
    * select ProductCatalog table
    * there are Query/Scan options. Query filters data and Scan fetches all data
    * go to terminal that sshed into and run following command
      ```
      #Command to query Dynamodb from EC2 command line
      aws dynamodb get-item --table-name ProductCatalog --region eu-west-2  --key '{"Id":{"N":"205"}}'
      ```
  * Index
    * DynamoDB Index is a data structure that helps you perform fast queries. There are two types of DynamoDB Index,.
    * Local Secondary Index
      * index you can only create when you are creating a table. it can't be modified/added/removed.
      * has same partition key(unique key) as table, but different sort key.
    * Global Secondary Index
      * index you can create when you create table or add later on
      * different partition key and sort key as table
  * Query
    * fins item in a table based on Primary Key or distinct value you are searching for.
    * you can use ProjectionExpression to return only wanted attributes from the query.
    * results are by default sorted by sort key
    * ScanIndexForwardParameter = false --> reverses the order of query result.
    * By default it is Eventually Consistent. you can make it strongly consistent by explicitly setting it so.
      * Eventual consistency: all access to the data are weakly guaranteed to return the same data.
      * Strongly Eventual consistency: all access to the data are guaranteed to return the same data.
  * Scan
    * examines all data in the table. you can filter results by attributes.
  * Query or Scan?
    * Query is more efficient
    * Scan dumps all data and filter it from there, this requires unwanted additional steps (SCAN takes longer operation time)
  * Performance Improvement
    * set smaller page size. (fewer read operation)
    * isolate scan operations to specific tables
    * try parallel scan
      * by default scan uses sequential scan. it retrieves 1mb then increments additional 1mb sequentially.
    * avoid scan.
    * you can use ProjectionExpression to return only wanted attributes from the query.
  * Capacity Units
    * provisioned capacity
      * when you create table you can specify read/write capacity units (1-4-8)
      ```
      * 1 write capacity unit = 1KB write/sec
      * 1 read capacity unit  = 4KB strongly consistent read/sec
                              = 8KB eventually consistent read/sec
      ```
    * on-demand capacity
      * you don't need to specify requirements
      * it automatically scales up/down based on the activity of your application
    * provisioned or on-demand?
      * unpredictable, unknown -> on-demand. otherwise, provisioned.
  * DAX (DynamoDB Accelerator)
    * DAX in-memory write-through cache optimized for DynamoDB. (Elisticache is not optimized for DynamoDB)
    * clusted & in-memory cache for dynamodb
    * delivers upto 10X read performance
    * write thru caching service: data is written in cache & back-end datastore.
    * suitable for eventual read consistency. (not suitable for strongly eventual read consistency)
    * no benefit for write intensive app. (doesn't help write operation)
  * Elisticache
    * in-memory cache in the cloud
    * good if your app is read-heavy and not frequently changing
    * frequently-access data is stored in in-memory for low-latency access
    * Two options
      * Memcached
        * multi-threaded
        * no multi-AZ
      * Redis
        * open-source
        * supports more complex data.
        * supports multi-AZ
    * Two caching strategies
      * lazy-loading
        ```
        * when data is requested -> data is available in cache   -> return data from cache
                                    data is unavailable in cache -> return null -> get data from datastore and write it in the cache
                                   
        returning null doesn't mean return null to the client. it means return null from cache. (to be handled by app)
        ```
        * advantage
          * only requested data is filled in cache. you don't need to cache un-requested data
          * elasticache node failure is not critical. you can create a new one (it will have lot of cache-miss at first)
        * disadvantage
          * cache miss penalty is somewhat large: 'Initial Request + Query to DB + write to Cache'
          * stale data: data is only updated when there is a cache miss. this means data can be outdated
            * TTL adjustment can help avoid stale data problem 
      * write-through: 
        * add/update data into cache whenever data is written(add/update) to the datastore.
        * advantage
          * data never stale.
          * users are more tolerant of latency when updating the data.
        * disadvantage
          * every write involves write to datastore & cache
          * cache node failure is big -> data is missing until data is written in datastore.
  * DynamoDB Transaction
    * ACID transaction
      * atomic: each transaction is treated as a single unit
      * consistent: no data corruption
      * isolated: doesn't affect other data
      * durable: when transaction is committed, data stays as committed (even when datacenter powerloss, for example)
  * DynamoDB TTL
    * you can set up TTL for dynamoDB tables so that you don't keep irrelevant data.
    * TTL is expressed as epoch time(unix time stamp expression)
    * Console -> DynamoDB -> select table -> action: manage TTL
      * you can run preview TTL to see what happens afterward.
  * DynamoDB Streams
    * time ordered stream of item-level modification(insert/update/delete)
    * logs are stored encrypted and for 24hrs.
    * only accessible thru dedicated dynamodb api endpoint.
    * can be used as a datasource for Lambda function
    * capture a time-ordered sequence of all the activity which occurs on your DynamoDB table – e.g. insert, update, delete.
  * provisionedthroughputexceededexception
    * exception that dynamodb table throws when requests exceed your provision configuration.
    * if you use AWS SDK, it will automatically retry when exception is thrown.
  * exponential back off
    * AWS uses exponentialBackOff. This means when request fails AWS retries with progressively longer waits.
    * ExponentialBackOff is not limited to dynamoDB. It is a common feature of AWS SDK.
  * BatchGetItem vs GetItem
    * GetItem returns a single item
    * BatchGetItem returns a set of items

# SECTION7. KMS (Key Management Service)
* KMS is a AWS key management service
* Lab
  * create a group
    * IAM -> group
    * group administrator access policy
  * create two users (name: KMS encrypter, KMS manager)
    * enable programmatic access(ssh) & console access
    * add a user to the group you created
  * create key
    * IAM -> Encryption Keys
      * note that IAM is global service but their encryption key is regional. (you can't use encryption key in us-east for eu-london)
    * select a region and create Key
    * define key administative permission: KMS manager (who administer this, but not uses)
    * define key usage permission: KMS encrypter (who uses this: encrypt/decrpy data)
* CMK (customer master key)
  * you can set up: Alias, Descryption, Created Date, Key Material(KMS default/External, Tags, Administrative Permission, Usage Permission, Policy
  * can never export CMK out of KMS
  * API calls
    * aws kms encrypt     take your plain text -> create it into ecrypted file (decrypted -> encrypted)
    * aws kms decrypt     take your encrypted text -> create it into plain text (encrypted -> decrypted)
    * aws kms re-encrypt  take your encrypted data -> decrypt it -> create it into encrypted file (encrypted -> encrypted)
    * aws kms enable-key-rotation   take a key and rotate it every year
  * Envelope encryption
    ```
    Customer Master Key -> Envelope Key (data key) -> Data
    ```
    * CMK decrypts the data key
    * Envelop key decrypts the data

# SECTION8. Other Services
* SQS (Simple Queue Service)
  * message queue management system
  * message is a job message that tells a service to execute some tasks
  * SQS is a pull(=poll) based system (for example EC2 instance will have to pull message from SQS)
  * SQS allows you to decouple components of an app so they run independently.
  * Message maximum 256KB text in any format.
  * Queue Types
    * Standard
      * No order in message
    * FIFO
      * First In First Out
      * 0 --> [0000] --> 0
  * SQS Delay Queue
    * postpones the delivery of new messages (invisible to consumers during delay duration)
    * default is 0, maximum is 900 seconds
    * setting delay queue doens't affect existing msg in Standard Queue (only new msg).
    * setting delay queue does affect existing msg in FIFO Queue.
  * Managing large message
    * use S3 to store them
    * Amazon SQS Extended Client Library for Java -> make SQS talk to S3
    * use AWS Java SDK -> S3 API utilization (Can't use regular SQS API)
  * Visibility Timeout = amount of time that the message is invisible in the SQS queue after being read. (Default: 30sec, Max: 12Hours). Use ChangeMessageVisibility endpoint to do so.
  * Retention Period: Message can be kept unread for 1min to 14days.
  * There is a chance that message being read more than once. So use Visibility Timeout
  * Regular Short Poll returns immediately. (even if msg wanted is not queued)
  * Long Poll returns after a msg is queued. (if not, times out - 20secondsx)
* SNS (Simple Notification Service)
  * Push Based (no polling)
  * SNS can deliver SMS text message, Emails and HTTP endpoints.
  * SNS can trigger Lambda functions.
  * follows pub-sub (publish-subscribe) model where SNS publishes msg and users subscribe to topics. (SNS pushes msg and no further checking on them)
  * When using Amazon SNS, you (as the owner) create a topic and control access to it by defining policies that determine which publishers and subscribers can communicate with the topic.
  * Consumer must subscribe to a topic to receieve notification from SNS.

* SES (Simple Email Service)
  * email can trigger Lambda function, SNS notification.
  * email only
  * email can be stored to S3
  * can handle incoming / outgoing email (SNS only does outgoing notification)
  * ex) automated email, Purchase Confirmation, Shipping Updates...etc
  * SES only need email address and just sends it.
* Elastic Beanstalk
  * Deployment & Scaling Web service.
  * You can select EC2 of your choice
  * You can also deploy docker image as well. (not only EC2)
  * Supports Java, php, Python, Ruby, Go, Docker, .Net, Node.js / Tomcat, Passenger, Puma, IIS
  * You can create an app with source code zip file. (this is saved to s3 bucket)
  * Deployment policy (Updating EBS)
    * All at once
      * Deploys the new version to all instances simultaneously/
      * Update Fail -> To roll back, perform all at once.
    * Rolling
      * deploys the new version in batches. (say you have 10, you update a batch of instances (say 3) then move on)
      * Update Fail -> To roll back, perform rolling update.
    * Rolling with Additional Batch Policy
      * Create new batch of instances -> deploy -> Create mnew batch of instances -> deploy
      * Maintains full capacity during the deployment. (Performance Sensitive, Suitable for no-downtime app)
      * Update Fail -> To roll back, perform rolling update.
    * Immutable Deployment
      * Create whole new instances -> deploy -> terminate old versions
      * Maintains full capacity during the deployment.
      * Creats a fresh group of instances in their autoscaling group -> Health Check Pass -> Move to new group -> Terminate old group
      * Update Fail -> To roll back, delete new instance and autoscaling group.
  * Configuring EBS
    * You can have configuration file
      * in YAML/JSON format
      * with .config extension
      * inside .ebextensions folder (.ebextensions folder in the top-level directory)
  * EBS & RDS
    * you can create RDS database inside EBS (from EBS console). This is good for test env, but since RDS is coupled with EBS it has no flexibility in lifecycle.
    * Ideally in prod. env, decouple RDS from EBS.
      * To allow EC2 (in your EBS) to connect to outside DB (ex. RDS), you need to do two things
        1. add additional Security Group to auto scaling group.
        2. provide DB connection credential in EBS. (with config file)
* Kinesis
  * Kinesis is a streaming service using Kinesis shards.
    * Kinesis Shard = sequence of data
    * Shard capacity
      * READ: 5read/sec, 2MB/sec
      * WRITE: 1000write/sec, 1MB/sec
    * As data size increases, you increase the number of shards (re-sharding)
  * Streaming data = small chunks of data that is sent from a web service.
  * Kinesis Types
    1. Kinesis Streams
       * Two Types: Data Stream & Video Stream
       * Producers -> Kinesis Streams: stored as shards -> Consumers
       * Kinesis Streams consts of shards
       * Shards have TTL
    2. Kinesis Firehose
       * Producers -> Kinesis Firehose: automated storage and consumption process
    3. Kinesis Analytics
       * Producers -> Kinesis Analytics: has availibility for SQL queries.
  * Kinesis Client Library
    * A library running on consumer side at processor instance level.
    * It creates a record processor for a shard.
    * When shard number increases, KCL increases record processor number.
    * LoadBalances between multiple consumers (processors number if equally distributed to consumer instances)
    * You don't need multiple processors to process one shard. (because its only 1MB ~ 2MB).
    * One consumer instance can handle multiple shards using multiple processors.
    * Best Practice: AutoScaling Group
* AWS Storages
  * AWS Storage Gateway: The Storage Gateway service is primarily used for attaching infrastructure located in a Data center to the AWS Storage infrastructure. The AWS documentation states that; "You can think of a file gateway as a file system mount on S3." (=File system on S3) (=Hybrid Storage that enables on-premise app to use AWS Storage)
  * Amazon Elastic File System (EFS) is a mountable file storage service for EC2, but has no connection to S3 which is an object storage service. (=Object Storage on EC2)
  * Amazon Elastic Block Store (EBS) is a block level storage service for use with Amazon EC2 and again has no connection to S3. (=Block Storage on EC2)

# SECTION9. Developer Theroies
* CI/CD
  * CI=Continuous Integration: "Code Repo -> Build -> Test"
  * CD=Continuous Delivery: "Code Update -> Production Release"
  * AWS CI/CD Services
    ```
    ----------------------------------------------------------
    * Code Repo = AWS CodeCommit
    * Build     = AWS CodeBuild
    * Deploy    = AWS CodeDeploy
    ----------------------------------------------------------
    * Manage    = AWS CodePipeline (something like Jenkins)
    ----------------------------------------------------------
    ```
* AWS CodeCommit
  * Based on Git
  * Tracks and maintains commit history.
* AWS CodeBuild
  * compiles and tests your code. (CodeCommit -> CodeBuild -> build Docker Image)
  * AWS Elastic Container Service
    * AWS docker container management platform
    * You can create AWS ECS clusters. (cluster = a group of instances)
  * AWS Elastic Container Registry
    * AWS docker container registry platform
    * You can create a Repository to hold each docker image
    * ECR repo is different from CodeCommit repo. You can run following command in local {myCodeCommitRepo} with Dockerfile to push docker image to AWS ECR repo. You can find push commands in AWS console
    ```
    aws ecr get-login --no-include-email --region eu-central-1    # do ecr login
    docker build -t {myECR-RepoName} .                            # build tag-able docker image
    docker tag ........                                           # tag it
    docker push .......                                           # push it to ECR-repo
    ```
  * buildspec.yml
    * defines step to take on each build step
    * you can specify ECS/ECR commands in this file to automate everything
      * crete CodeBuild project with 'use a build spec file' & 'codeCommit repo you have with buildspec.yml & dockerfile'
      * you can either 'use a build spec file' or 'insert build commands (do automation part in UI console)'
        * 'insert build commands' lets you override 'buildspec.yml' specification
      * you can override settings in UI
        * 'Environment variables override' section
    * you can check build logs in CodeBuild console. full logs in CloudWatch
* AWS CodeDeploy
  * Compatiable with other management tools: AWS CodePipeline, Jenkins, Puppet, and ... etc.
  * CodeDeploy vs ElasticBeanStalk. (ElasticBeanStalk is more of bigger end-to-end delivery service where code deploy is deployment specific service)
  * Two Deployment Options
    * In-Place(Rolling) Deployment
      * Rolling Update
      * Each instance stops an app when doing an upgrade installation.
      * Only Ec2 and On-premise (Not Lambda)
      * To roll back, you have to re-deploy older version.
    * Blue/Green Deployment
      * Blue is active(current) version instances
      * Green is new version instances
      * No Performance Down Issue
    * Terms
      * Deployment Group: A set of EC2 instances or Lambda functions where you will deploy new software.
      * AppSpec File: A document that defines actions AWS CodeDeploy will execute
  * CodeDeploy agent is a program that runs code deploy tasks in terminal
  * You can store revision codes in S3.
  * AppSpecFile
    * Defines parameters used for CodeDeploy.
    * For Lambda Deployment: three sections required (version, resources and hooks). Can be written in YAML/JSON
      1. version: reserved for future use (currently 0.0 is allowed only)
      2. resources: properties(name, alias, currentVersion, TargetVersion) of Lambda to deploy
      3. hooks(Lambda): Lambda to run during deployment. You can specify point of time to execute each Lambda.
         ```
         new Lambda Created
         BeforeAllowTraffic
         AllowTraffic         (=trafficRerouted to new Lambda)
         AfterAllowTraffic
         ```
         * BeforeAllowTraffic: point of time before traffic is routed to newly deployed Lambda. (validate Lambda is deployed correctly)
         * AfterAllowTraffic: point of time after traffic is routed to newly deployed Lambda. (validate Lambda is functioning correctly)
    * For EC2/OnPremises Deployment: three sections required (version, resources and hooks). Can be written in YAML (not JSON)
      ```
      appsepc.yml should be in in the root directory of app for EC2/OnPremises Deployment.
      
      Typical Setup:
        appspec.yml
        /scripts
        /config
        /src
      ```
      1. version: reserved for future use (currently 0.0 is allowed only)
      2. os: OS you are using
      3. files: location of file to be copied from and to (source & destination)
      4. hooks: Lifecycle Hooks to run during deployment. You can specify point of time to execute each Script.
         ```
         * BeforeBlockTraffic: Run tasks on instances before they are deregistered from a load balancer
         * BlockTraffic: Deregister instances from a load balancer
         * AfterBlockTraffic: Run tasks on instances after they are deregistered from a load balancer
         
         * ApplicationStop: stop app to prepare for new version deployment 
         * DownloadBundle: copy app revision files to temp location
         * BeforeInstall: before Install
         * Install: Codedeploy agent copies the app revision files from temp location to correct location
         * AfterInstall: after Install
         * ApplicationStart: start app for new version deployment
         * ValidateService: after app is started
         
         * BeforeAllowTraffic: Run tasks on instances before they are egistered from a load balancer
         * AllowTraffic: register instances from a load balancer
         * AfterAllowTraffic: Run tasks on instances after they are registered from a load balancer
         ```
         ```
         example
         
         hooks: 
          BeforeInstall:
           - location: scripts/unzipResource.sh
           - location: scripts/unzipData.sh
          AfterInstall:
           - location: scripts/runTests.sh
             timeout: 180
          ApplicationStart:
           - location: scripts/runUnitTests.sh
             timeout: 1800  
          ValidateService:
           - location: scripts/runIntegrationTests.sh
             timeout: 1800
             runas: deployUser
         ```
* AWS CodePipeline
  * You can configure it to trigger a pipeline when code change is committed in repo
  * If one step in a pipeline fails, the whole flow stops there.
* AWS CloudFormation
  * CloudFormation lets you manage AWS infrastructure as code
  * Supports YAML/JSON
  * CloudFormation template describes endstate of infrastructure you want to provision.
  * You upload template to CloudFormation using S3
  * Resulting resource is called "CloudFormation Stack".
  * If deployment fails (part of deployment), it rolls back the entire stack. (default)
  * CloudFormation Template (sections)
    * Parameters: input custom values (ex: env type)
    * Conditions: value based on condition (ex: create some resources based on input)
    * Resources: the only mandatory section, defining AWS resources to create (ex: aws resource you want to deploy(create) with this cloudformation)
    * Mappings: custom mappings of key to different values (ex: use different key-values for different regions; AMI is an amazon VM image)
    * Transforms: reference code in S3 (ex: include template code snippet from outside into this template. use S3)
    * Outputs: declare output values that you can import into different stacks. (create cross-stack references)
  * AWS SAM (Serverless Application Model)
    * SAM is an extension for CloudFormation to deploy serverless apps
    * SAM CLI commands
      * sam package: "to S3" package up your deployment packages and upload it to S3
        ```
        sam package --template-file ./cloudFormationTemplate.yml --output-template-file samTemplate.yml --s3-bucket bucketname
        ```
      * sam deploy: "to World" deploy serverless app with package you created with sam package. (deploys with CloudFormation
        ```
        sam deploy --template-file ./samTemplate.yml --stack-name serverlessStack --capabilities CAPABILITY_IAM

        # capabilities: CAPABILITY_IAM grants permission for sam agent to create/deploy this app.
        ```
      * you still need CloudFormationTemplate (AWSTemplateFormat), and you put AWS::Serverless::Function as Resources Type;
        ```
        Resources:
         Type: AWS::CloudFormation::Stack
        ```
  * CloudFormation Nested Stacks
    * Nested Stacks allow re-use of CloudFormation code.
    * Allows you to reuse pieces of CloudFormation code in multiple templates, for common use cases like provisioning a load balancer or web server?
    * in CloudFormationTemplate, specify Type as follows
      ```
      Resources:
       Type: AWS::CloudFormation::Stack
      ```
# SECTION10. Advanced IAM
* Web Identity Federation
  * Concept of granting permission to users who are authorized/authenticated through web identity provider. (Google/Facebook/Amazon...etc). Successful authentication from web identity provider is followed by getting an authentication code which can be traded for other credentials.
* Amazon Cognito provides Web Identity Federation
  * = Identity broker between app and web identity provider.
    * Cognito brokers between app and Web Identity Provider maps authentication code to temporary credential for IAM role. (Web Identity Provider --> Authentication Code --> Cognito Broker --> Temporary Credential --> IAM Role
  * Provides sign-up/sign-in and guest access.
  * Synchronizes user data across devices
  * AWS Recommended Approach for mobile apps.
* Cognito User Pools
  * = User Directory
  * uses 'User Pool' = user directories to manage sign-up/sign-in functionality for apps to manage user sign-in/sign-up directly or via Web Id Providers.
* Cognito IdentityPool
  * = User Authorizer
  * Amazon Cognito identity pools provide temporary AWS credentials for users who are guests (unauthenticated) and for users who have been authenticated and received a token.
  ```
  WebIdentityProvider -> User Pool -> Identity Pool -> AWS Resources
  ```
  * AWS SNS is used to send silent notification to associated devices when user data is updated.
* IAM Policies
  * AWS Managed Policies
    * IAM Policy creaed/administered by AWS.
    * You can use(attach) this policy to multiple users/groups/roles across AWS accounts
    * You can't change permissions defined in AWS Managed Policies.
  * Customer Managed Policies
    * IAM Policy created/administered by you (your own AWS account)
    * You can use this policy to multiple users/groups/roles but only within your AWS account.
  * Inline Policies
    * IAM Policy embedded within the user/group/role. (strict 1:1 relationship between user and policy)
    * You can't user this policy to different users/groups/roles.
    * In general, AWS Managed Policies or Customer Managed Policies are recommended.
    * Userful when permissions in a policy is strictly limited to this single user.
* AssumeRoleWithWebIdentity
  * API provided by STS (Security Token Service) that returns temp credential to be used in AWS Resources
    * Returns AssumedRoleUser (ARN & Id) which uniquely tied to temporary credentials (not IAM role/user)
  * Cognito uses STS to retrieve temp credentials for WebIndentityProvider authenticated users.
    ```
    1. WebIdentityProvider --> AssumeRoleWithWebIdentity --> STS --> AWS Resources
    ```

# SECTION11. CloudWatch
* CloudWatch can monitor Compute, Storage, DB, Analytics and others...
* CloudWatch can be used on premise apps (not limited to AWS resources)
* CloudWatch Metrics
  * Host Level Metrics: CPU, Network, Disk and Health Status
    * You can't get storage of virtual disk it is a custom metric, Disk here in host level means disk IO.
  * Custom metric
    * RAM Utilization
    * Disk Utilization
  * Granularity (How small can the metric unit be?)
    * 1min (detailed: minimum)
    * 5min (standard)
  * Retrievable with API
  * By default logs are store indefinitely. (You can configure this)
  * You can retrieve logs of terminated AWS resources.
  * You can set up alert when CloudWatch monitors the conditions you are looking for
* CloudWatch vs CloudTrail vs AWS Config
  * CloudWatch watches performance
  * CloudTrail watches API requests
  * AWS Config records the state of AWS environment 

# Section12 Other Exam Topics & Tips
* To allow one AWS account to access and manage resources in another AWS account -> configure aws cross account access
* Where would you store confidential information (credentials, license codes) for AWS resources? -> AWS Systems Manager Parameter Store (as parameter values)
* AWS CLI Pagination
  * When you run cli command you can control the number of items displayed as a command output.
  * be default: page size = 1000
  * too large page size can result in time-out error -> use page-size or max-items
    ```
    aws s3api list-objects --bucket myBucket --page-size 120 (return all results in 120size pages)
    aws s3api list-objects --bucket myBucket --max-items 120 (return first 120 items and that's it)
    ```
* Default AWS SDK Region = US-EAST-1
* Your mission-critical web application needs to store its session state so that it can be accessed quickly and is highly available. Which service should you use? => session stae => object data
* You can select a specific Availability Zone in which to place your DynamoDB Table.
