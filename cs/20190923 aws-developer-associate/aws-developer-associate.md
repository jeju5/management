# AWS Certified Developer - Associate 2019
* UDEMY
* Ryan Kroonenburg & Faye Ellis



# Intro
* Exam Structure
  * https://d1.awsstatic.com/training-and-certification/docs-dev-associate/AWS_Certified_Developer_Associate_Updated_June_2018_Exam_Guide_v1.3.pdf
  * 130 minutes & 65 questions
  * $150



# IAM
* IAM
  * Identity Access Management
  * Allows you to manage users & level of access to AWS console
* Terms
  * User: end user
  * Group: group of Users
  * Role: Role with in the service (set of permissions)
  * Policy: Permission defining JSON (document)
* CUSTOMIZE SIGN-IN URL
  * Services -> Security, Identity, & Compliance -> IAM
    * click Customize (you can customize url for sign-in link)
    * you will have to delete existing setup if you customize url
* ENABLE MFA (Multi Factor Authorization)
    * Security Status -> Manage MFA -> Virtual MFA device -> continue
    * Install MFA app into my phone
    * Scan QR CODE with your phone -> try Authenticator login
* IAM User
    * add/delete a AWS user
* IAM Groups
    * group of users
* IAM Policy
    * defines permission
    * written in JSON
    * delegate policy(es) to a group or directly assign to a user
* IAM Role
    * set of permissions assigned to an entity(ex. EC2)
* IAM Policy Simulator
  * Test the effect of Policy before committing it.
* IAM with SIGV4
  * SIGV4 = "HTTP request authentication" ★
  * Signature Version 4 is the process of adding authentication to AWS HTTP request
    
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
* explicit permission/deny overrides general ones.
  * ex. policy1 allows user1 & policy2 explicitly denies user1 ==> deny user1
  
* IAM Role & Permissions
  * IAM Role based approach is the best practice when you need permissions on AWS Resources.
  * When working with onPremise Resources, you can also store IAM credentials in '~/.aws/credentials' of onPremise instance to gain access to the IAM Role you want to use.

* To allow one AWS account to access and manage resources in another AWS account
  * configure aws cross account access

* root user access key
  * it is recommended to delete root-user-access-key. (if you don't have it, don't create it if you really need it)
  * it is recommended to allow root credential for one admin user with Multifactor Authentication (MFA) ★

* Instance Profile ★
  * "Profile passing container"
  * Instance profile is a container for an IAM role that you can use to pass role information to an EC2 instance when the instance starts.
  * credentials are rotated automatically

* Instance Role ★
  * "EC2 role"
  * IAM role that can be only used in EC2

* Service-Linked IAM Role
  * IAM role that is linked to AWS Service (this has all permission for one service to call another service)

* IAM access key & aws configure ★
  * username & password is for AWS console use
  * for programmatic access use access keys
  * EC2/onPremise: store access keys in ~/.aws.credentials file.

# EC2
* EC2 101
  * EC2 = ECC = Elastic Cloud Computing = Computing Service in Cloud
  * Pricing
    ```
    reserved  : Regular        : capacity reservation -> good for regular-basis need (=scheduled)
    onDemand  : Short          : price by time -> good for short-term need (short-term also means specific short time)
    Spot      : FlexibleTime   : capacity bidding -> good for flexible-time need (start time & end time), good for temporary additional need
                                 if terminated partial hour usage -> if AWS EC2 terminates it, you won't be charged / if you terminate it, you will be charged for a complete hour
    Dedicated : RegionSpecific : physical EC2 server -> good for region-specific regulations
  * Instance Type (optimized for)
    ```
    ATM'General' --- C'Compute' --- RX'Memory' --- PGF'Accelerated(Graphic)' --- HID'Storage'
    ```
* EC2 & EBS = elastic block store
  * Volumes that can be attached to EC2
  * aws instance is placed in an AZ (AZ-locked). (any instance like db, ec2, ...) ★
    * AWS Region: geographic area
    * AWS AZ Availability Zone: resource isolation within AWS Region
    * Availability Zones < Region (ex. US-east-1; default regions)
  * If you want to encrypt data, you have to configure encryption when creating EBS Volume
  
  * you have to mount filesystem to use as file storage
    * it is block volume when created
  
  * EBS Types
    ```
    SSD: 'GP2' < 16000IOPS < 'IO1'
    HDD: 'SC1'(fileserver) = "C"old HHD < 'ST1'(datawarehousing) = op"T"imized  (can't be boot volume)
    Magnetic: 'legacy'
    ```
    * SSD
      * GP2 < 16000 < IO1
      * General Purpose SSD = GP2 (General): 3IOPS/GB, max 16,000 IOPS (IOPS = input/output operations per second)
      * Provisioned IOPS SSD = IO1 (Intensive): proper for more than 16,000 IOPS
      * can be a boot volume ★
    * HDD
      * SC1 < Optimized < ST1 (op"T"imized)
      * Cold HHD = SC1
        * lowest cost among current
        * use case: file server
        * can't be boot volume ★
      * Throughput Optimized HDD = ST1
        * use case: big data, data warehousing
        * can't be boot volume (boot volume: disk that relates to OS booting)
    * Magnetic
      * legacy service
      * lowest cost among all

* EBS has snapshot feature ★

* EBS & encryption ★
  * supports in-transit & at-rest encryption (KMS)
  * EBS encryption should be done when creating EBS
  * snapshot of encrypted EBS is always encrypted.
  * volumes & snapshot from encrypted EBS is encrypted.

* EBS detachement "(STOP)->(UNMOUNT)->DETACH" (S)(U)D
  * STOP -> Detach (OS volume)
  * Unmount -> Detach (otherwise)
    * stop if EBS is root(boot) volume; stop os
    * unmount if instance is running; unmount running
       
* EC2 Security Group
  * define allowed inbound/outbound
  * types: ssh, rdp, http...
    * ssh: secure shell
    * rdp: remote desktop protocol
    * http: hyper text transfer protocol (protocol for http request/response)
  
* EC2 & Key
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
  
* EC2 & SSH
  * you can connect via one of two options
    * standalone ssh: ssh via terminal
    * mindterm ssh: ssh via Chrome browser (requires JAVA)
  * ssh with standalone
    ```
    chmod 400 labKeyPair3.pem
    ssh -i "labKeyPair3.pem" ec2-user@ec2-18-221-93-64.us-east-2.compute.amazonaws.com
    ```

* EC2 AutoScaling Group
  * service that maintains stable performance by scaling up/down number of instances on demand (if no scaling policies specified)
  * The desired capacity, maximum capacity should be handled manually.
  * if not manually adjusted, Auto Scaling group maintains this number of running instances even if an instance becomes unhealthy.
  * autoscaling group can't start/stop/restart app. It only manages EC2 instance.
  * if unhealthy -> ASG terminates unhealthy instnace and launches a new one. (not restart app though) ★

* RoleBased EC2
  * Create a S3 bucket
  * Create a user that has no access
  * SSH into this EC2 instance with this user and try 'aws s3 ls'. It will fail.
  * IAM -> Roles -> Create Role -> select entity type: AWS Service and service: EC2
  * select AmazonS3FullAccess Policy and create a role
  * EC2 -> select ec2 instance you created
  * right click the instance, click Attach/Replace IAM Role
  * Try 'aws s3 ls'. It will list s3 buckets. You will also have availability to s3 features further on.
  * you can use EC2 Role based!
    * what does this mean?
    * whenever you use this instance, you will have all permission that this Role has.
    * if you try EC2 with a user that has no access, you will still have some permission to this RoleBasedEC2
  * EXAM TIP
    * Roles allow you to not use Keys (Access Keys, Secret Keys)
    * Rolse are preferred (security perspective) because it is easily detached/attached to instances without having to stop or terminate instances.

* RoleBased "EC2-task"
  * Like you create a Role and assign to an instance, you can create a role and assign to a EC2 task.

* EC2 link-local address
  * iP address(169.254.169.254) only valid inside from ec2
  * http://169.254.169.254/latest/meta-data/ is where you get meta-data
    * EC2 metadata is some dynamic values about this instance like instance id, hostname ...etc (policy is not here)
  * Retrieve Instance User Data
    * To retrieve user data from within a running instance, use the following URI:
    * http://169.254.169.254/latest/user-data

* EC2 Placement Group "SCP"
  * how will you place instances?

  * spread: within Hardware (reduce correlated failures)
  * cluster: within AZ
  * partition: within Logical Group

  * EC2 Placement Group in Cloudformation
  ```
  "PlacementGroup" : {
     "Type" : "AWS::EC2::PlacementGroup",
     "Properties" : {
              "Strategy" : "cluster"
     }
  }
  ```
  
* EC2 Task Placement Strategy
  * how will you assign tasks to instances?
  * field
    * instanceId or custom (like availibility zone, cpu, memory ... etc)

  * type ("SBR TYPE")
    * spread: place tasks spread-evenly
    * binpack: place tasks based on cpu & memory (this minimizes the number of instances in use)
    * random: place task randomly
      * but still honrs explicit/implicit constraints you specified
      * and makes sure that instances assigned have enough resources
  
  ```
  "placementStrategy" : [
    {
      "field": "attribute:ecs.availability-zone",
      "type": "spread"
    }
  ]
  ```
  
* EC2 Task Group
  * set of related EC2 tasks
  
* EC2 Task Placement Constraint
  * Constraint(rule) that is used during task placement.
  
* EC2 Cluster Query Language ★
  * container instance grouping expression
  * Expressions that enable you to group container instances. (by attributes like Availability Zone, instance type, or custom metadata)
  
* EC2 Storage
  * Instance Storage
    * ephemeral = volatile = data lost when With EC2 stopped.

  * Amazon Elastic Block Store (EBS)
    * Block Storage for EC2 (ec2 only)
    * EBS encryption -> Do when creating one
    * persistent = durable = data not lost when EC2 stopped
    * it is locked with in a AZ; can't be shared to different AZ (this is general rule of thumb in AWS; AZ-lock)
    * new volumes are raw block storage, and needs file system to be used.

  * Amazon Elastic File System (EFS)
    * Object storage for EC2 (and other aws services; not public)
    * faster than s3
    * designed to be shared across multiple instances (shared across AZ)

* EC2 User Data ★
  * you can define automated startup tasks
  * must be base64 encoded
  

* EC2 Logs
  * EC2 logs stay inside EC2.
  * If you want to investigate who deleted EC2, EC2 logs wouldn't help. 

* EC2 & Elastic IP
  * IPv4 address designed for dynamic cloud computing.
  * With an Elastic IP address, you can mask the failure of an instance by rapidly remapping the address to another instance in your account.
  * multiple instances pointed by elatic IP (automatically maps to healthy one)
  * Elastic IP doesn't scales. this is major difference between load balancer

# ALB
* AWS Load Balancer (ALB = AWS Load Balancer)
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
      * Application Load Balancer
        * layer7
        * dynamic port mapping, allowing multi-tasking
          * when there are multiple containers you need dynamic port mapping => application load balancer ★
        * supports HTTP/HTTPS/Websocket ★
        * works within the application. incoming app traffic control 

      * Network Load Balancer
        * supports TCP/SSL ★
        * incoming network traffic controls (layer4)

      * Classic Load Balancer
        * single port mapping
        * hard-coded port mapping.
        * No flexibility for multi-task.
        * HTTP, HTTPS, TCP, SSL. doesn't look at the request.
        * Health Check
          * You can make load balanceer ping health check for their availability.
          * InService (means healthy), OutofService (means unhealthy)
          * If Health Check enabled, The load balancer performs health checks on all registered instances, whether the instance is in a healthy state or an unhealthy state.
        * When creating Classic Load Balancer facing Internet, always resolve DNS name and use it. Never use IP address because it may change.
        * path based routing is based on hostname & url-path. (URL = hostname:port/path)
* ports
  * default HTTP port: 80
  * default HTTPS port: 443

* Loadbalncer & X-Forwarded-Foor
  * Network Loadbalancer eliminates the need of using X-Forwarded-For
  * other load balancers need x-forwaded-for if they want origin ip

* The load balancer routes requests only to the healthy instances.
  * When the load balancer determines that an instance is unhealthy, it stops routing requests to that instance
  * health check and routing is done automatically (so do nothing)

* when your load balancer fails, it throws 504 error (Gateway timeout)
* x-forwarded-for header
  * Requester (Public IP) -> DNS -> Load Balancer -> Application Server
  * Application Server will get Requester address (public origin ip) as "x-forwarded-for header"
* Session Stickiness (=session affinity)
  * This ensures that all requests from the user during the session are sent to the same instance.
  * By default, a Classic Load Balancer routes each request independently to the registered instance with the smallest load. You can enable session stickiness to make your service bind a request and an instance.
  

# Route53
* AWS Route53
  * Route53 is AWS DNS service. It maps the request to EC2, S3 or load balancer
  * to do domain mapping -> register cname in Route53
  * Routing Policy
    ```
    Simple routing policy – (single resource)       <-- Use for a single resource that performs a given function for your domain.
    Failover routing policy – (healthy/unhelathy)
    Geolocation routing policy – (absolute location)
    Geoproximity routing policy – (nearest)
    Latency routing policy – (best performance)
    Weighted routing policy – (proportional)
    Multivalue answer routing policy – (return multiple domains)
    ```
  * Services -> Route 53 -> DNS management 'Get Started Now'
    * Registered Domain -> Register Domain
      * buy a domain or register an existing one you own
  * Hosted Zones
    * Hosted Zone = container that holds routing configuration data ★
    * configure routing (DNS Name --mapping--> App Server)
    * you can route a request to Instance, IP, Lambda function
    * configure health check

  * TEST
    * make a request to the domain you registerd --> DNS server --> load balancer --> EC2


# CLI
* AWS CLI(Command Line Interface)
  * create a user
    * 'Developer1' with 'Developer' group with programmatic access
    * get an access key id & secret access id
  
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
  * Exam tips
    * give least privilege to a user.
    * create groups and assign to user(s) properly.
    * don't use a single access key. assign keys to different roles/users/groups.
    * when a developer leaves a group -> delete the key and create a new key. (don't reuse it)

  * AWS CLI Pagination
    * When you run cli command you can control the number of items displayed as a command output.
    * be default: page size = 1000
    * too large page size can result in time-out error -> use page-size or max-items
    * max-itmes can be used with  --starting-token to specifty where to start
      ```
      aws s3api list-objects --bucket myBucket --page-size 120 (return all results in 120size pages)
      aws s3api list-objects --bucket myBucket --max-items 120 (return first 120 items and that's it)
      ```

  * CLI & IAM Configuration
    * You changed IAM role but doesn't work? -> check IAM configuration in CLI (it might be using old IAM role configured in AWS CLI setting)

  * in AWS CLI, passing value as a flag is better than aws configure (when possible)
    ```
    ex)  --region us-east-2
    ```

  * dry-run lets you test if you have permission to execute something
    ```
    --dry-run 
    ```
    
* AWS sign in URL "ID/ALIAS.signin.aws ..." ★
  ```
  https://AWS_ID.signin.aws.amazon.com/console/
  https://AWS_ID_ALIAS.signin.aws.amazon.com/console/    # if you create Alias for AWS ID
  ```

  
# AWS & DB
* AWS RDS
  * AWS Relational DB Services
  * OLTP (OL"T"P = Online Transaction Processing)
  * Data Query & Data CRUD Operation
  * SQL Server, MySQL...
  * BackUps
    * composed of 'Automatic Backup' and 'Manual DB Snapshot' (backup=entire copy of db, snapshot=readonly copy of db at certain point)
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

* RDS enhanced monitoring ★
  * you can enable enhanced monitoring to monitor CPU & memory usage. (CPU% & MEM%)
    
* AWS RDS Troubleshoot & Log ★
  * RDS Error Log (every error)
  * RDS Slow query log (query that takes long time)
  * RDS has audit log, general logs as well

* RDS doesn't scale automatically

* RDS Backup periodic
  * way1. use cron job
    * create cron event in cloudwatch
    * trigger lambda that does snapshot
  * way2. enable auto backup
    * retention period: 0-35 days

* AWS RDS & TDE
  * AWS RDS supports TDE(transparent data encryption) for MS SQL Server.
  * This feature automatically
    * encrypts data before it is written.
    * decrypts data after it is read.

* AWS Redshift
  * OLAP (OL"A"P = Online Analytics Processing)
  * Data Analytics & Data Warehousing
  * supports SQL clients

* AWS DynamoDB
  * NoSQL


    



# S3
* S3 101
  * S3 = Simple Storage Service
  * Object based storage (not block storage)
    * allows you to upload files
    * block storage is a storage that holds data using raw volumes
    * unavailable for OS, DB, Block
  * unlimited total storage
  * each file can be 0-5TB

  * files are stored in bucket
    * Data Consistency Model
      ```
      PUT-Create          -> strongly consistent (read-after-write) = immediate
      PUT-Update & Delete -> eventual consistent = takes time
      ```
  * files are stored in bucket (similart to a folder)
  * s3 bucket name should be named universially unique. (globally unique)
  * HTTP 200 Code if upload was successful

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
* S3 URL
  * hosted–style
    * for static hosting
    * "starts with bucket" -> "bucket s3 region amazonaws/com"
    * http://bucketName.s3-aws-region.amazonaws.com
    * http://bucketName.s3-website.region.amazonaws.com (memorize this)
  * path-style URL
    * "ends with bucket"
    * http://s3-aws-region.amazonaws.com/bucketName
    
* Tiers
  * S3 Standard
    * nine9 durability : 99.999999999% (unlikely to loss data)   # but data will be there anyway. 
    * two9 availability: 99.99%        (likely to be accessible) # connection can be messed up
    * "9929"
  * S3 Standard IA (Infrequently Accessed)
    * nine9 & one9
    * lower fee but charged retrieval fee for all S3 IA object.
  * S3 One Zone IA
    * nine9 & 99.5
    * same as S3 -IA, but is stored in a single AZ
  * S3 Intelligent Tiering
    * same availability and durability as Standard S3.
    * good for unknown access patterns.
    * It has two tiers (frequent/infrequent) and it automatically moves your data to cost-effective tier.
    * no retrieval fee but has very small monthly maintenance fee.
  * Reduced Redundancy Storage
    * 99.99% availibility and durability to provide one-year storage.
    * good for data that can be easiliy regenerated if lost.
  * Glacier
    * Very cheap, Verp slow. Works for very infrequently accessed data (proper for historic archive)
    * Retrieval Options
      * Expedited retrieval: 1-5mins (mins)
      * Standard retrieval: 3-5hours (sub5)
      * Bulk retrieval: 5-12hours, large amt, cheapest

    
* Charges
  * Storage (per GB)
  * Requests (Get, Put, Copy)
  * Storage Management (Analytics, Tagging)
  * Data Management (Data transfer out of S3; if data is transferred into S3, its free)
  * Transfer Acceleration (Use of Cloudfront CDN)

* S3 Multipart upload ★
  * S3 single operation can upload upto 5GB.
  * consider multi-part if exceeding 5GB
  * using S3 multipart upload, you can upload upto 5TB. (5TB limit)
  * multi-part upload improves upload time.
  * AWS CLI automatically attempts to multipart upload if file is too large.
  * you need kms:encrypt/decrypt/reencrypt/GenerateDataKey/DescribeKey permissions for multipart upload


 

* S3 Transfer Acceleration
  * accelerates file transfer using Cloudfront (CDN)
  * S3 bucket name should be DNS compliant without any periods(.)

* S3 Security
  * Bucket is private by default (only owner has access)
  * You can set up
    * bucket policy
      * applied at bucket level
    * access control list  (ACL)
      * applied at bucket and indiviaul objects
      * ACL is attached to a bucket like bucket policty.
      * AWS recommends the use of bucket policy
      * S3 bucket policy is applied to the bucket and all its contents. if you want to manage individual object access configure ACL ★
    * you configure buckets to log every access and request
    
* S3 Encryption
  * S3 Encryption "in Transit"
    * SSL/TLS or Client Side Encryption(You encryption data on your own (application level))
  
  * S3 Encryption "at Rest"
    * Client Side
      * done by client

    * Server SIde
      * "SSE (Server Side Encryption) via SSE-C/S3/KMS"
      * SSE-C
        * encryption key: you manage (Client Managed Keys)
        * encryption/decryption: s3 manage
        * HTTPS is mandatory (HTTP rejected)
        * client must provide encryption key info as following headers
          ```
          * x-amz-server-side-encryption-customer-algorithm: AES256 (this is how you will encrypt)
          * x-amz-server-side-encryption-customer-key: encoded encryption key
          * x-amz-server-side-encryption-customer-key-MD5: encoded MD5 digest of encryption key
          ```
        * SSE-C also uses AES256 but the header is different
          ```
          SSE-S3  =>  "s3:x-amz-server-side-encryption": "AES256"
          SSE-C   =>  "x-amz-server-size-encryption-customer-algorithm" : "AES256"
          ```
        * HMAC
          * HMAC is hash authenticated code
          * hash-based message authentication code
          * When you use SSE-C, then the client key you provided is stored in salted HMAC
          * However, this HMAC can't be used for decryption/encryption. If you lose the key you lose the data.
      
      * SSE-S3
        * encryption key: s3 manage 
        * encryption/decryption: s3 manage (S3 Managed Keys)
        * supports HTTP/HTTPS
        ```
        * "x-amz-server-side-encryption": "AES256" ★
        ```

      * SSE-KMS
        * encryption key: KMS manage
        * encryption/decryption: s3 manage
        * option best for envelope key, access control, audit trail, rotation and create/manage encryption keys. ★
        * KMS doesn't use AES256 algorithm.
        * supports HTTP/HTTPS
        ```
        * "x-amz-server-side-encryption": "aws:kms" ★
        * "x-amz-server-side-encryption-aws-kms-key-id": you can specify specific encryption key.
          if you use "x-amz-server-side-encryption": "aws:kms" but not "x-amz-server-side-encryption-aws-kms-key-id", then AWS uses default KMS key
        ```

      * Notethat 
        * SSE-C only supports HTTPS
        * SSE-S3 & SSE-KMS supports HTTP/HTTPS
      
* Enforcing Encryption
  * When object is uploaded to S3, PUT method is initiated.
  * If you want to enforce encryption, deny all PUT without "x-amz-server-side-encryption" expectation.

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
  * CORS configuration example
    * CORS configuration configures preflight OPTION request.
    ```
    <?xml version="1.0" encoding="UTF-8"?>
    <CORSConfiguration xmlns="http://s3.amazonaws.com/doc/2006-03-01/">
     <CORSRule>
      <AllowedOrigin>https://tutorialsdojo.com</AllowedOrigin>  # AllowedOrigin: allowed CORS origin
      <AllowedMethod>GET</AllowedMethod>                        # AllowedMethod: allowed CORS request method types
      <AllowedMethod>PUT</AllowedMethod>
      <AllowedMethod>POST</AllowedMethod>
      <AllowedMethod>DELETE</AllowedMethod>
      <AllowedHeader>*</AllowedHeader>                          # AllowedHeader: Headers allowed in preflight request
      <ExposeHeader>ETag</ExposeHeader>                         # ExposeHeader: Headers that customers are able to access ★
      <ExposeHeader>x-amz-meta-custom-header</ExposeHeader>
      <MaxAgeSeconds>3600</MaxAgeSeconds>                       # MaxAgeSeconds: Preflight Caching length ★
     </CORSRule>
    </CORSConfiguration>
    ```
  * IN exam check if they are in the same/different domain before choosing CORS ★

* S3 CRR (Cross-Region Replication) ★
  * feature of auto/async copying of objects across buckets in different AWS regions.
  * you neeed to enable versioning in bucket ★ (CRR needs versioning enabled)
  * you need to specify source & destination in different region
  * DynamoDB doesn't have CRR
    * If you want to sync DynamoDB tables in different regions. You can use DynamoDB stream. When new change made -> stream it -> update other table.

* S3 optimization
    * S3 optimization is based on workload you are running
      * GET intensive workload: you use s3 for excessive GET requests.
        * optimization: use cloudfront(CDN) to speed it up.
      * Mixed request type workload: you use s3 for various request types.
        * s3 uses key name to determine the partition it will use to store this data.
        * optimization: use random key prefix for each object. so that it is randomly distributed.
        * if files in the same partition is frequently requested. this will cause IO issue.
        * TTL (time to live): how long will you cache data in edge location
   * S3 performance update
     * 3500 put/sec
     * 5500 get/sec
     * AWS updated its bucket performance. and you don't need to randomize object key prefix to enhance s3 performance.  

  * S3 bucket is private by default.
    * to allow access you can either allow users manually in bucket policy or giving away pre-signed URL
    * A presigned URL gives you access to the object identified in the URL (presigned URL is targeting an object)
    * advantage on presigned URL is that users outside of AWS can gain access to this object.

* S3 Versioning
  * Any unversioned file before versioning will have null version
  * overwrite increases version
  * delete is recoverable operation
    * when you delete an object, Amazon S3 inserts a delete marker, which becomes the current object version and you can restore the previous version
  * versioning applies to all object within the bucket. (can't specify folder/object to version)

* Other AWS Storages Services
  * AWS Storage Gateway
    * "Hybrid File gateway as a file system mount on S3." (Hybrid: onPremise Service + AWS Storage)
    * The Storage Gateway service is primarily used for attaching infrastructure located in a Data center to the AWS Storage infrastructure.
    
* S3 Select
  * retrieve data subset by simple SQL expression
  * By using S3 Select to retrieve only the data needed by your application
  * ierformance increase (upto x4)

* S3 Inventory
  * storage management tool
  * audit report, encryption report, replication report.
  
* S3 Analytics
  * Analyze storage access patterns
  * help you decide right storage class

* S3 PutObject vs PostObject
  * Put is more suitable for most cases. (PostObject is used in browser upload scenario)


# AWS CloudFront (content delivery network)
* CloudFront is Amazaon Web Service CDN(Content Delivery Network).
  * It is optimized to work with other AWS services.
  * Supports Web(HTTP/HTTPS) & Media (RTMP)
  * Doesn't support UDP. AWS Global Accelerator supports UDP
* CDN basics  (in general)
  * web service that speeds up web content delivery based on geographic location of a user.
  * edge location handles web requests from nearby locations instead of directly hitting origin
  * edge location (!= AWS Region) (!= AZ) (=nearby caching location)
    * geographically dispersed data center that caches web contents
    * you can also write contents you want inside edge location
  * origin
    * origin of all files that CDN will distribute
* you can configure regional restrictions on CDN (block japan  & russia for example)

* Cloudfront & SSL 
  * You can use HTTPS for inbound/outbound
    * [Viewer] <--HTTPS(A)--> [Edge Location : CloudFront] <--HTTPS(B)--> [Origin]

    * to enable HTTPS(A): set HTTPS in "Viewer Protocol Policy" ★
    * to enable HTTPS(B): set HTTPS in "Origin Protocol Policy"
  * 504 Error (Gateway time out)
    * set up primary origin and second origin for Cloudfront to automatically switch.
    * use Labmda@Edge
    

# Serverless Computing
* AWS Lambda
  * Lambda is abstract layer of 'Data Center + Hardware + Assembly Code + High Level Languages + OS + Application Layer)
  * first 1million requests are free. 20cents per 1million requests after.
  * lambda scales out automatically (not scale up)
  * AWS X-ray debugs AWS Lambda
  * Lambda can work globally
  
  * Lambda Concurrent Execution
    * push based (ex: API Gateway Integration, S3 Events integration)
      ```
      concurrent executions = (requests per time) x (time per request )
      ex) 50 request/sec * 100 sec/request = 5000 concurrent executions
      ```

    * pull based (ex: Kinesis Stream Integration)
      ```
      concurrent execution = number of shards
      ```

    * LIMIT ★
      * There is a concurret execution limit for Lambda
      * default is 1000 execution per region (and is maximum without a request)
      * you can increase this limit by making a request to AWS)

    * HTTP 429 TOO MANY REQUESTS error -> concurrency issue
    * Reserved Concurrency
      * reserved number of execution set for critical lambda functions (this also have a limit)
      
  * Lambda Version
    * When you create a new Lambda function. Its version is $LATEST
    * You can create multiple versions and use it with alias to that version
  * You can configure RAM Memory assigned to a Lambda for performance requirement.
  * environemntal variable size total shouldn't exceed 4kb
  
* Lambda Language Supports
  * Node.js Java Python C# Go
  * Doesn't support C++
  * what if you want to use language that is not supported by Lambda? -> use "AWS Lambda Custom Runtime"
    * create a layer that uses "AWS Lambda Custom Runtime"

* There is no SecurityGroup config in Lambda
  * simply not needed to config inbound/outbound ★

* Lambda Runtime
  * Runtime is a program that runs a Lambda function's handler method when the function is invoked.
  * you can include runtime codes in an excutable file named 'bootstrap' in deployment package of lambda function.
  
* Access to VPC (Virtual Private Cloud)
  * terms
    * VPC
      * Virtual Private Cloud
    * ENI
      * Elastic Network Interfaces (an interface you can attach to an instance in VPC)
      * ENI is connection component of VPC
    * EIP
      * Elastic IP address
    * NAT
      * Network Address Translation
      * VPC-to-outside outbound traffic handler
      * NAT is outbound connection component of VPC (from VPC to outside)
      * instance in public subnet in VPC that enables an outbound call from private subnet in VPC to outside and prevents inbound call from outside into private subnet
    * subnet
      * IP group
      * All devices whose IP addresses have the same prefix. (partion of same ip network)
  * AWS Lambda uses the VPC information you provide to set up ENIs that allow your Lambda function to access VPC resources.
  * You can do so by providing private subnet id and security group id with vpc-config parameter (or in UI)
  * Each ENI is assigned a private IP address from the IP address range within the subnets you specify but is not assigned any public IP addresses.
    ```
    aws lambda update-function-configuration \
    --function-name myFunction \
    --vpc-config SubnetIds=subnet=1122aa,SecuritGroupIds=sg-12321
    ```
    * private subnet id
    * security group id
    * then Lambda sets up ENIs automatically

  * Creating and Attaching IAM role approach doesn't work on VPC environment. IAM role is only for AWS resources.
  
  * If Resource in VPC needs outbound calls
    1. NAT
      * Set up NAT (add NAT Gateway)
      * and use security group that works.
    2. VPC endpoint
      * create VPC endpoint in outer resource to handle calls from VPC to this resource
    
    * using AWS ROLE/ACCESSKEYs in instance in VPC doesn't work because of connectivity issue. NAT is required in this case


* AWS VPC Flow Logs
  * VPC Flow Logs is VPC IP traffic logs (flow in & flow out)

* Upload Code
  * 4ptions
    * paste code in Lambda IDE
    * upload zip in Lambda Console
    * refer to zip file object in s3 with Cloudformation (AWS::Lambda::Function)
    * write inline python/nodejs code with Cloudformation (AWS::Lambda::Function) if there is no dependencies

  * paste code in LambdaIDE
  * upload zip in Lambda Console
  * use Cloudformation & S3
    ```
    {
      "Type" : "AWS::Lambda::Function",
      "Properties" : {
          "Code" : {
            "S3Bucket" : String,
            "S3Key" : String,
            "S3ObjectVersion" : String,
            "ZipFile" : String
          },
          "DeadLetterConfig" : DeadLetterConfig,
          "Description" : String,
          "Environment" : Environment,
          "FunctionName" : String,
          "Handler" : String,
          "KmsKeyArn" : String,
          "Layers" : [ String, ... ],
          "MemorySize" : Integer,
          "ReservedConcurrentExecutions" : Integer,
          "Role" : String,
          "Runtime" : String,
          "Tags" : [ Tag, ... ],
          "Timeout" : Integer,
          "TracingConfig" : TracingConfig,
          "VpcConfig" : VpcConfig
        }
    }
    ```

* Cloudformation & CLI
  * cloudformation package: upload template on cloud
    ```
    aws cloudformation package --template-file /path_to_template/template.json --s3-bucket bucket-name --output-template-file packaged-template.json
    ```
  * cloudformation deploy: build template on cloud
    ```
    aws cloudformation deploy --template-file /path_to_template/template.json --stack-name my-new-stack --parameter-overrides Key1=Value1 Key2=Value2 --tags Key1=Value1 Key2=Value2
    ```

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

* Exam Tips
  * Lambda Scales out, not scales up (this means you have flexibility on number of concurrent lambda services running at the same time)
  * Lambda can work globally (ex. backup S3 bucket A to S3 bucket B)
  
* Lambda & Fails
  * synchronous invocation fails -> you will get an error
  * async invocation fails -> retries two more times -> if retry fails, log msg to DLQ (if configured)
  * you can't set up sync/async invocation per lambda on event based trigger. It depends on 

* Lambda@Edge
  * Amazon CloudFront feature that lets you run code (geolocationally) closer to users of your application
  * improves performance and latency.
  * With Lambda@Edge, you don't have to provision or manage infrastructure in multiple locations around the world.
  
* Lambda Good Practice
  * avoid initialization on every invocation. (Lambda Initialization)
    * use global/static variables(singletons).
      * ex) init S3 client out side of Lambda handler and reuse that connection on next invocation
    * move those outside of labmda.
  * make use of seperate handler(helper methods)
    
  
* /tmp directory
  * Best way to store temporary files created by Lambda function is to put it under /tmp (512MB capacity)
  * If Lambda function has to download something everytime executed, just store it in the /tmp of the execution context.

* AWS Lambda Deployment Package ★
  * A deployment package is a ZIP archive that contains your function code and dependencies
  * You put function source code and dependencies in one zip file and upload it for use.
  * If the deployment package is larger than 50 MB, you must use Amazon S3.

* Lambda Layer
  * Lambda layer is a library code that can be used for your Lambda function.
  * each lambda function can have upto five layers and total unzipped can't exceed 250MB.

* Lambda & InvocationType
  * Invocation Type is something that triggers Lambda function and there are 3types
    * RequestResponse(default): sync invocation. rseponse is response data.
    * Event: async invocation. make use of DLQ(if configured). response only has response code. (if you want to do EVENT integration, your app and lambda should be async compliant) ★
    * DryRun: no actual run, just validate user/role permission on this invocation.

* Lambda & API Gateway
  ```
  request -> Lambda --response should be certain format--> API GATEWAY
  ```
  * Labmda should pass the response with required data in JSON format to API Gateway. If this format is wrong or not in JSON, API Gateway will throw 502 BAD GATEWAY error

* API Gateway
  * API = application programming interface = set of features that utilize an application.
    * REST API uses JSON
    * SOAP API uses XML (you can configure SOAP API pass through)
  * AWS API Gateway is a fully managed API service
  * API caching: you can cache endpoint response for redundant requests for TTL.
  * same origin policy <--> CORS
  * CORS
    ```
    CORS by example:

    [browser] <- [www.example.com] <- [www.example.org]
                  Origin1              Origin2
                                       Access-Control-Allow-Origin
                                       
    A web browser loads a page from www.example.com
    www.example.com makes a request to www.example.org (The origin of this request is www.example.com)
    When the server at www.example.org receives a request from an origin other than www.example.org

    * browser is responsible for not making a request to a destination where you are whitelisted
    * server is responsible for managing allowed CORS domains
    ```
  * you can log results to CloudWatch
  * you can track usage by api key
  * you can maintain multiple versions
  * API Gateway supports Swagger Import & openAPI definition which creates an API based on the documentation
  * you can update existing api endpoint with overwrite/merge.
  * AWS API Gateway capacity
    * 10,000 requests per second
    *  5,000 concurrent requests (across all APIs with in a single account)
    * if you reach the capacity you will get 429 Too Many Reqeusts response
  
  * Stage Variable
    * think of API stage as environment. You can set up different stage variable to be used in different env.

  * Stage Variable vs Environmental Variable ★
    * Stage variable is a concept in API Gateway
    * Environmental Variable is a concept in Lambda, RDS, Elastic Beanstalk...etc

  * API Gateway & Use Usage Plans with API Keys ★
    * Usage Plan
      * manages who is accessible to API stages and methods. (making APIs like product offerings)
    * API keys
      * key for authorization.
      * keys that grant access to the API (should be unique, can be associated with more than 1 usage plan, but only one usage plan in a single stage)

* Lambda Authorizers
  * API Gateway feature of acess control from Labmda to API
  * Types
    * TOKEN authorizer = token-based = receives the caller's identity in a token (JWT, OAUTH)
    * REQUEST authorizer = request parameter-based = receives the caller's identity in a combination of parameters (headers, query string parameters, stageVariables, and $context variables)

* Lambda Environmental variable
  * lambda env variables stays with in a single lambda
  * it can't be shared
  * it can be encrypted with KMS, but if you want to share env variables use SSM parameter store.

* Lambda & Concurrency
  * account concurrenct execution limit: concurrecy capacity with in an account
  * reserve concurrency: concurrency capacity can be reserved for a lambda function
  * unreserved concurrency: whatever is left over from reserved concurrency is unreserved concurrency. (minimum 100 required; and this is combined minimum, that is not minimum per lambda)
  * note that reserve concurrency can't exceed to the point that any unreserved concurrency shouldn't go below 100 (100 combined across all left over lambdas)

* Lambda & Recursive Call
  * doesn't cause abrupt termination. It will cause massive increase in volume and cost

* AWS Api Gateway has Mapping Template
  * API Gateway lets you use mapping templates to map the payload from a method request to the corresponding integration request and from an integration response to the corresponding method response.  

  * API Gateway & Lambda Integration
    ```
    CLIENT -> METHOD REQUEST  -> INTEGRATION REQUEST  -> LAMBDA
           <- METHOD RESPONSE <- INTEGRATION RESPONSE <-
    ```

* Api Gateway Caching
  * you can enable caching with TTL to mitigate loads.

* API Gateway & Invalidating Cache
  * "Cache-Control: max-age=0"  ... client sends a request with this Header to fetch fresh data
  * Require authorization ... if you want API Gateway to allow cache invalidation only for authentication users, tick 'Require Authorization' in UI console setting ★
  

* API Gateway Integration Types ★
  * API Gateway Lambda Integration
    * AWS: with custom mapping            (non-proxy is custom mapping; data mapping, request mapping, response mapping)
    * AWS_PROXY: without custom mapping   (proxy is non-custom mapping)
  * API Gateway EC2 Integration
    * HTTP: with custom mapping
    * HTTP_PROXY: without custom mapping
  * MOCK: return response without hitting backend.

* API Gateway & HTTPS
  * API Gateway only uses HTTPS connection (no HTTP, FTP, Websocket)

* API Gateway & authorization integrates with ★
  * IAM with sigv4: use with other resource & IAM role based
  * Lambda Authorizer: use with lambda (token/request)
  * Cognito User Pool: user with 3rd web

* AWS Step Function (SF)
  * workflow logging/visualization (light, no-infrastructure)
  * uses JSON-based Amazon States Language
  * provisioned by cloud-formation
    * cloudformation provisions elastic beanstalk, stepfunction
    
* Amazon Simple Work Flow (SWF) ★
  * workflow logging/visualization (heavy, full-stack, infrastructure)
  * task-oriented API.
  * ensures that task is assigned only once. (unlike SQS)
  * concepts
    * marker: record execution history
    * signal: inject information into execution
    * timer: calculate time elapses for notification
    * tags: categorize execution

* X-Ray
  * X-Ray collects data on requests that application serves and allow users to view them.
  * X-Ray vs CloudTrail
    * X-ray is a troubleshooting tool, recording everything possible
    * CloudTrail is a API logger
  * You have to enable 'active tracing' to accept data coming into X-Ray ★
  
  * Architecture
    * [EC2 -> X-Ray SDK & X-Ray Daemon] -> X-Ray API -> X-Ray Console
    * X-Ray SDK works with X-Ray Daemon (actually they are running in EC2)
    
  * Works with the following services (not S3)
    ```
    API Gateway
    App Mesh
    CloudTrail
    AWS Config
    Amazon EC2
    Elastic Beanstalk
    Elastic Load Balancing
    Lambda
    Amazon SNS
    Amazon SQS
    Working with Go
    ```

* AWS X-Ray Segment Documents
  * JSON that describes what your app does to handle request
    * configuration of segment handled by X-Ray
    * you can add custom attributes in this document

  * X-Ray Subsegment
    * Additional data about time & downstream calls
    * subsegment fields
      * namespace: aws(aws sdk calls)
      * remote (other downstream calls)

  * Segment/Subsegment can have annotations and metadata inside ★
    * X-Ray Annotations
      * trace & filter
      * indexed key-value pairs
    * X-Ray Metadata
      * non-trace & non-filter
      * store
      * unindexed key-value pairs

  * Sending segment documents to X-Ray
    * send directly to X-Ray-API by PutTraceSegments API
    * send to X-Ray daemon that buffer & upload in batches to X-Ray-API
    
* X-Ray & Fargate
  * Fargate is  serverless ComputeEngine for ECS.
  * when you are using EC2, you run X-Ray agent on EC2. However, when you use it with Fargate, you run it on a sidecar container.

* X-Ray on multiple accounts
  * create an IAM role that has access on multiple accounts.
  * configure x-ray daemon to use this IAM role

* X-Ray Daemon
  * Application that collects segment data and pass it on to X-Ray API

* X-Ray SDK
  * SDK takes care of HTTP requests (inbound & outbound)
  * interceptors: trace HTTP requests
  * client handlers: instrument AWS SDK clients
  * HTTP client: call internal/external HTTP web services

* Configure X-RAY SDK for Node.js & variables	★
  * address, tracename, debugmode
  * AWS_XRAY_DAEMON_ADDRESS
    * IP address of X-Ray daemon
    * host and port of the X-Ray daemon listener. (128.0.0.1:2000 by default; both tracing and sampling)
    * Use this variable if you have configured the daemon to listen on a different port.
  * AWS_XRAY_TRACING_NAME
    * segment name
    * SDK need Tracename, Lambda need TraceID
  * AWS_XRAY_DEBUG_MODE
    * debug boolean (SDK debug mode, Lambda doesn't debug you know)
    
* Configure X-RAY SDK for Lambda & variables ★
  * address, traceid, contextmissing
  * AWS_XRAY_DAEMON_ADDRESS 
    * IP address of X-Ray daemon
  * _X_AMZN_TRACE_ID
    * ontains tracing header which includes traceId, segmentId, ... etc
  * AWS_XRAY_CONTEXT_MISSING
    * When tracing header is missing, this variable is set to determine behavior.
    

* X-Ray APIs
  * GetTraceSummaries: Get IDs and annotations for traces available for a specified time frame 
  * BatchGetTraces: Get a list of traces specified by ID
  * GetGroup: Get group resource details
  * Steps to filter out trace you want
    * GetTraceSummaries -> get IDs --> BatchGetTraces
  
* X-Ray sampling size
  * sampling per second = reservoir size + ( (incoming requests per second - reservoir size) * fixed rate)
    ```
    reservor size: 50
    fixed rate: 10%
    request per second : 100
    
    50 + (100-50) * 0.10
    = 50 + 5
    = 55 req/sec
    ```
    
* X-Ray set up ★
  * on EC2
    * manually install it
  * on EB
    * put xray-daemon.config under .ebextensions
  * on ECS
    * create a docker image that runs X-Ray daemon -> deploy to ECS cluster
    * X-Ray daemon listens on port 2000 (so allow UDP on port 2000)
  


# DYNAMO DB
* Intro
  * DYNAMO DB is AWS NO SQL DB.
  * supports document and key/value pairs
    * supported document: JSON, HTML and XML
  * Item: similar to row in table
    * each item is made of key/value pairs
  * Attribute: similar to column in table

  * DynamoDB stores/retrieves data based on a primary key
    * partition key: unique attribute -> each item has its unique partition key (note that partition key also is an attribute)
      * primary key = partition key(simple primary) + sort key(composite primary)
    * composite key: combination of partition key and sort key -> each item has its unique "partition key with sort key". (2attribute)

  * DynamoDB access control
    * authentication and access control is managed by AWS IAM
    * how to make certain user can view only its data
      * set userId as dynaoDB partitionKey
      * add userId in dynamodb:LeadingKeys (leadingKey=partitionKey)
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
* CLI
  ```
  # Command to query Dynamodb from EC2 command line
  aws dynamodb get-item --table-name ProductCatalog --region eu-west-2  --key '{"Id":{"N":"205"}}'
  ```
* Index
  * data structure for fast queries.
  
  * Local Secondary Index (LSI)
    * index you can only create when you are creating a table. it can't be modified/added/removed.
    * same partition key(unique key) as table
    * different sort key as table.
    * LSI uses same RCU & WCU as the table.
    * LSI supports both eventual consistency & strong consistency ★

  * Global Secondary Index (GSI)
    * index you can create when you create table or add later on
    * query on Global Secondary Index only supports eventual consistency (no strong-consistency) ★
    * different partition key then table
    * different sort key then table

    * even when table's provision is enough, table can suffer throttling when GSI's resource is not enough. ★
      * make sure that [GSI's WCU/RCU] >= [TABLE's WCU/RCU]

* Query
  * you can query a table, gsi, lsi. GSI & LSI querying makes query fast.
  
  * results are by default sorted by sort key
  
  * ProjectionExpression
    * return only wanted attributes
  * ScanIndexForwardParameter
    * set to false --> reverses the order of query result.
  
  * Eventually Consistent.  (default)
    * you can make it strongly consistent by explicitly setting it so.
    * Eventual consistency: all access to the data are weakly guaranteed to return the same data.
    * Strongly Eventual consistency: all access to the data are guaranteed to return the same data.
      * Read-After-Write Consistency = Strongly Eventual Consistency

* Scan
  * examines all data in the table.
  * you can filter results by attributes.
 
* Query or Scan?
  * Query is more efficient
  * Scan takes all data and filter it from there, this requires unwanted additional steps (SCAN takes longer operation time)
  
* Performance Improvement
  * set smaller page size. (fewer read operation; this approach improves for query&scan)
  * isolate scan operations to specific tables
  * try parallel scan
    * by default scan uses sequential scan. it retrieves 1mb then increments additional 1mb sequentially.
  * avoid scan.
  * you can use ProjectionExpression to return only wanted attributes from the query.
  * DAX improves read performance, but in general, caching increases costs.
  
* DynamoDB Capacity Units
  * DynamoDB Capacity unit provision is per table. Its not shared across tables
  * regular capacity: 1-4-8
    ```
    * 1 write capacity unit = 1KB write/sec
    * 1 read capacity unit  = 4KB strongly consistent read/sec
                            = 8KB eventually consistent read/sec
    ```
    ```
    # calculation rule
      "AVG-UNIT-SIZE ÷ 1-4-8 x OPERATION PER SECOND" ★
      - get [avg-unit-size] (in multiples of 1-4)
      - get [unit-per-item] (avg-unit-size ÷ 1-4-8)
      - do  [unit-per-item] x [operation-per-second]

    ```
  * transactional capacity: simply multiply x2 of regular capacity needed.
    ```
    ex) if you want ~~~ trasactional capacity: calculate ~~~ regular capacity required and multiply 2.
    ```
  * on-demand capacity
    * you don't need to specify requirements
    * it automatically scales up/down based on the activity of your application
  * provisioned or on-demand?
    * unpredictable, unknown -> on-demand. otherwise, provisioned.
    
* DAX (DynamoDB Accelerator)
  * DAX in-memory cache optimized for DynamoDB. (Elisticache is not optimized for DynamoDB)
  * clusted & in-memory cache for dynamodb
  * delivers upto 10X read performance
  * write thru caching service: data is written in cache & back-end datastore.
  * DAX Usage
    * suitable for eventual consistency read.
    * not suitable for strongly eventual consistency read.
    * not suitable for write intensive.
  
* DynamoDB Transaction
    * ACID transaction
      * atomic: each transaction is treated as a single unit
      * consistent: no data corruption
      * isolated: doesn't affect other data
      * durable: when transaction is committed, data stays as committed (even when datacenter powerloss, for example
      
* DynamoDB TTL
  * you can set up TTL for dynamoDB tables so that you don't keep irrelevant data.
  * TTL is expressed as epoch time(unix time stamp expression)
  * Console -> DynamoDB -> select table -> action: manage TTL
    * you can run preview TTL to see what happens afterward.
    
* DynamoDB Streams
  * "time ordered stream of item-level modification" (insert/update/delete)
  * logs are stored encrypted and for 24hrs.
  * if you don't handle the stream data within 24hrs the data is gone
  * only accessible thru dedicated dynamodb api endpoint.
  * can be used as a trigger for Lambda function
    * Lambda trigger source: DynamoDB Stream, Cloudwatch event (DynamoDB Stream is suitable when new data is inserted/updated)
  * Kinesis Adapter is more suitable for handling these streams (than Lambda)
  * capture a time-ordered sequence of all the activity which occurs on your DynamoDB table – e.g. insert, update, delete.

* DynamoDB Stream & Lambda Integration ★
  * requires (Trigger & Role setup in Lambda)
  * In Lambda: Create a trigger in Lambda
  * To Lambda: Assign AWSLambdaDynamoDBExecutionRole to Lambda (execution role)
  * all things are done in Lambda side.
  * Example scenario
    * DynamoDB content is changed -> Event sent to Lambda -> Lambda does something.

* DynamoDB Streams & StreamSpecification configuration
  * StreamEnabled: boolean
  * StreamViewType
    * KEYS_ONLY: keys of the modified item
    * NWE_IMAGE: item after modified
    * OLD_IMAGE: item before modified
    * NEW_AND_OLD_IMAGES: item before and after modified
    
* DynamoDB atomic counters
  * a counter that increments without having any interference to other write requests. (simple)

* AWS Exponential back off
  * AWS SDK has exponentialBackOff. This means when request fails AWS retries with progressively longer waits.
  * ExponentialBackOff is not limited to dynamoDB. It is AWS SDK feature.
  * it is simply a retry logic. AWS will automatically retry if something errors out, exponentially increasing the wait time.
  * you will have to configure it to use it.
* BatchGetItem vs GetItem
  * GetItem returns a single item
  * BatchGetItem returns a set of items
* Creating DynamoDB Table
  * Region is selectable, AZ is not-selectable
* Provision
  * WCU = write capacity unit.
    * The WCU you provisioned is per table. Not shared across tables.
  * Hot partition
    * When data access is imbalanced, a 'hot' partition can receive such a higher volume of read and write traffic compared to other partitions.
    
* Exceptions
  * ProvisionedThroughputExceededException
    * exception that dynamodb table throws when requests exceed your provision configuration.
    * if you use AWS SDK, it will automatically retry when exception is thrown.
  * RequestLimitExceeded
    * request exceeds account capacity
  * ThrottlingException
    * request rate exceeds capacity

* If you want to delete entire records within the table, just 'DeleteTable' and recreate it.

* Locking
  * Optimistic Locking
    * "Be optimistic" that client/db is the same -> lock item with version number only
    * client side item for update/delete is the same as dynamo db side. (protects overwrites)
    * you have to configure verion number
      * each item gets a version number as an attribute
      * when version mismatches that means someone already has updated it, causing update failure on mismatch
    * transactional operation doesn't support optimistic locking
  * Pessimistic Locking
    * "Be pessimistic" that client/db is the same -> lock the entire item until you finish it
    * causes performance issue
    * suitable for transactional db
  * Overly Optimistic Locking
  
* DynamoDB Batch Operations
  * Using dyanmoDB BatchGetItem, BatchWriteItem can save resources on redundant operations.
    * BatchGetItem = 5*GetItem
    * BatchWriteItem = 2*PutItem + 4*DeleteItem
  
* DynamoDB & ReturnConsumedCapacity ★
  * ReturnConsumedCapacity (NONE < TOTAL < INDEXES)
    * ReturnConsumedCapacity.NONE
      * nothing (default)
    * ReturnConsumedCapacity.TOTAL
      * total-WCU-consumed
    * ReturnConsumedCapacity.INDEXES
      * total-WCU-consumed & subtotal-WCU-consumed in table & secondary index

* DynamoDB & ReturnValues ★
  * NONE: (default)
  * ALL_OLD: return all attributes of an item before updated
  * ALL_NEW: return all attributes of an item after updated
  * UPDATED_NEW: return only updated attributes of an item after updated

* DynamoDB & throttling solution ★
  * SOLUTION1: implement error retries & exponential back off
  * SOLUTION2: spread workload (ex) technique: write sharding = distribute write operation across) => resolves write calls ★
  * SOLUTION3: use DAX
  * SOLUTION4: increase RCU & WCU


# AWS Elasticache
* Elaisticache
  * in-memory cache in the cloud
  * good if your app is read-heavy and not frequently changing
  * frequently-access data is stored in in-memory for low-latency access
  * Elasticache can sit infront of RDS for in-memory caching
  
  * Two options
    * Memcached
      * si"M'ple = "M"emcached
      * simple data, multi-thread, no multi-AZm not-highly-available
    * Redis
      * Red"i"s = Compl"i"cated
      * complicated data, single-thread, multi-AZ, highly available
      * Redis is better than Memcached in all ways except 'multi-threading'
      * proper for session state (session state needs high availability)

  * Two caching strategies
    * lazy-loading
      * "cache when requested - some stale & ttl"
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
      * "cache when written - no stale"
      * add/update data into cache whenever data is written(add/update) to the datastore.
      * you can still enable TTL to free unnecessary caching with write-through strategy★
      * advantage
        * data never stale.
        * users are more tolerant of latency of write operations (when updating the data and this caching is done only on update/create)
      * disadvantage
        * every write involves write to datastore & cache
        * cache node failure is big -> data is missing until data is written in datastore.
  

# KMS (Key Management Service)
* KMS is a AWS key management service
* note that IAM is global service but their encryption key is regional. (you can't use encryption key in us-east for eu-london)
* CMK (customer master key)
  * you can set up: Alias, Descryption, Created Date, Key Material(KMS default/External, Tags, Administrative Permission, Usage Permission, Policy
  * can never export CMK out of KMS
  * KMS encrypts/decrpyts data and stores CMK
  * KMS creates CMKs and never export them un-encrypted
  * API calls
    * aws kms encrypt     take your plain text -> create it into ecrypted file (decrypted -> encrypted)
    * aws kms decrypt     take your encrypted text -> create it into plain text (encrypted -> decrypted)
    * aws kms re-encrypt  take your encrypted data -> decrypt it -> create it into encrypted file (encrypted -> encrypted)
    * aws kms enable-key-rotation   take a key and rotate it every year
  * Envelope encryption
    ```
    "Data is encrypted by DataKey and DataKey is encrypted by CMK"
                                          <Envelope>
    Data --Plain DataKey-------1-------> Encrypted Data
               |
               |
               2
               |
               --Customer Master Key(plain text)--> Encrypted DataKey
    
    1. createKey: create CMK
    2. generateDataKey: create DataKey (returned cipher-text(encrypted with CMK) & plain-text)
    3. encrypt: DataKey encrypts Data
    4. delete plaintext-datakey & store ciphertext-dataKey
    5. Encrypted Data and Encrypted DataKey is enveloped
    ```
  * Envelope decryption
    ```
    1. CMK decrypts Encrypted DataKey
    2. Plain DataKey decrypts Encrypted Data
    ```
  
  * KMS limitation
    * 4KB (you can upload upto 4KB for KMS encryption.)
    * if it exceeds 4kB
      * use EncryptionSDK, encrypt in code and store encrypted file (you may use envelope encrypt)
    

* Encryption in local
  1. use GenerateDataKey -> get a data key. (returns encrypted(CiphertextBlob) & plaintext)
  2. Use plaintext-data-key -> encrypt data locally -> erase the plaintext-data-key from memory. (always erase plaintext-data-key when not in use)
  3. Store the-encrypted-data-key with locally encrypted data.

* Decryption in local
  1. use Decrypt to decrypt the encrypted data key -> get a data key. (returns plaintext)
  2. Use the plaintext-data-key -> decrypt data locally -> then erase the plaintext data key from memory.
    
* KMS endpoints
  * Encrypting Key
    * GenerateDataKey: return unencrpyted dataKey & CMK encrypted dataKey
    * GenerateDataKeyWithoutPlaintext: return CMK encrypted dataKey
  * Encrypting general text
    * Encrypt
  * when using these endpoints above you provide KeyId specifying encrypting key.    
  
  * Decrypt: decrypt keys or general text.
  * GenerateRandom: generate random byte string

* KMS doesn't use asymmetric encryption algorithm

* CMK is customer master key. KMS stores CMK; KMS receives data, encrypts and sends it back. (client master key != customer master key)


# Notification Services
* AWS SQS (Simple Queue Service)
  * message queue management system
  * message is a job message that tells a service to execute some tasks
  * SQS is a pull(=poll) based system (for example EC2 instance will have to pull message from SQS)
  * SQS allows you to decouple components of an app so they run independently.

  * SQS retrieval
    * you can retrieve 1~10 msg at one time
  
  * SQS maximum size
    * Message maximum 256KB text in any format.

  * Queue Types
    * Standard
      * No order in message
      * Standard Queue doesn't support ordering nor deduplication
    * FIFO
      * First In First Out
      * 0 --> [0000] --> 0

      * SQS FIFO & MessageGroupId ★
        * tag that specifies that a message belongs to a specific message group. (you can use it for ordering)

      * SQS FIFO & MessageDeduplicationId ★
        * token used for deduplication of sent messages.
        * when msg with MessageDeduplicationId is delivered -> msg with the same id won't be delivered for 5min deduplication interval.
        * to do deduplication do one of the following:
          * Enable content-based deduplication. 
          * Explicitly provide the message deduplication ID (or view the sequence number) for the message.

  * SQS Encryption & KMS
    * you can envrypt SQS msgs with KMS

  * SQS Delay Queue
    * "delay by hiding"
      * delay after queued
    * 0(default) - 900(max) seconds
    * configured by 'DelaySeconds' parameter ★
    * setting delay queue doens't affect existing msg in Standard Queue (only new msg).
    * setting delay queue affects existing msg in FIFO Queue. (this is commonsense becase fifo is all ordered. it starts delaying from first in msg)

  * SQS Long Poll
    * "longs for message"
      * deliver when available
    * not retention period stuff.
    * Regular Short Poll returns immediately. (even if msg wanted is not queued)
    * when your app takes longer time to generate msg and customer has to wait ->  use Long Poll

  * Visibility Timeout ★
    * "timeout for protection"
    * default: 30sec
    * configure: 0sec - 12hours
    * during the timeout the msg is protected from retrieval or deletion, it prevents other consumer to process the same msg.
    * Consumer must delete this msg after consuming it if wanted ★
    * When you want to prevent msg being read more than once. -> minimize Visibility Timeout

    * SQS ChangeMessageVisibility API
      * when message processing time is unpredictable (on client side), consumer can extend visibility timeout using ChangeMessageVisibility API action on its own.

  * SQS Retention Period
    * "keep read&unread msg"
    * min: 1min
    * default: 4day
    * max: 14day

  * Managing large message
    * use "Amazon SQS Extended Client Library" (Java) -> make SQS talk to S3 ★
    * make SQS talk to S3
    

* You can encrypt message using KMS  
* When there is a need for debugging SQS msg -> use SQS DLQ(dead letter queue) to isolate debugging msg.

* SQS API
  * PurgeQueue: delete(purge) a message specified
  * DeleteQueue: delete a queue specified


* SQS msg limit is 1KB - 256KB(max)

* SQS Dead Letter Queue (DLQ)
  * this is where undelivered msg eventually goes. You can debug or analyze issues here.
  * to implement DLQ in Lambda: specify SQS arn in Lambda's DeadLetterConfig
  
* Fanout pattern (SQS+SNS)
  ```
  SNS ---notifications to sqs---> SQS - msg consumer
                             ---> SQS - msg consumer
  ```
  * eliminates periodic check (SNS sends)
  * poll for update (SNS sends)
  * later processing (SQS retention)

* AWS SNS (Simple Notification Service)
  * Push Based (no polling)
  * SNS can deliver SMS text message, Emails and HTTP endpoints.
  
  * SNS target ★
    * subscribers, lambda, SQS
  
  * follows pub-sub (publish-subscribe) model
    * SNS publishes msg and users subscribe to topics.
    * SNS pushes msg and no further checking on them

  * When using Amazon SNS, you (as the owner) create a topic and control access to it by defining policies that determine which publishers and subscribers can communicate with the topic.
  * Consumer must subscribe to a topic to receieve notification from SNS.

* AWS SES (Simple Email Service)
  * email can trigger Lambda function, SNS notification.
  * email only
  * email can be stored to S3
  * can handle incoming / outgoing email (SNS only does outgoing notification)
  * ex) automated email, Purchase Confirmation, Shipping Updates...etc
  * SES only need email address and just sends it.



# AWS EB: Elastic Beanstalk
  * Deployment & Scaling Web service.
  * You can select EC2 of your choice
  * You can also deploy docker image as well. (not only EC2)
  * Supports Java, php, Python, Ruby, Go, Docker, .Net, Node.js / Tomcat, Passenger, Puma, IIS
  * You can create an app with source code zip file. (this is saved to s3 bucket)
  
  * Deployment command ★
    ```
    EB only supports zip or war file format for deployment. (no tar)
    
    eb deploy
    ```
    
  * Deployment policy
    * All at once
      * Deploys the new version to all instances simultaneously/
      * Update Fail -> To roll back, perform all at once.
    * Rolling
      * deploys the new version in batches. (say you have 10, you update a batch of instances (say 3) then move on)
      * it doesn't increase instance numbers
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
    
    * Immutable vs Blue/Green ★
      * Immutable: creates new instance set in the same env and delete old ones
      * BlueGreen: creates new instance set in new env and switch DNS (DNS change is the main key)

    * EB performs in-place deployment by default but you can also do blue/green
      
  * Configuring EBS
    * You can have configuration file
      * in YAML/JSON format
      * with .config extension
      * inside .ebextensions folder
      * .ebextensions folder in the top-level directory; (path: .ebextensions/{NAME}.config)
        * you can set up https here.
        * you can set up x-ray daemon here.
        
  * EB & RDS
    * you can create RDS database inside EB (from EB console).
    * This is good for test env, but since RDS is coupled with EBS it has no flexibility in lifecycle.
    * Ideally in prod. env, decouple RDS from EBS.
      * To allow EC2 (in your EBS) to connect to outside DB (ex. RDS), you need to do two things
        1. add additional Security Group to auto scaling group.
        2. provide DB connection credential in EBS. (with config file)

* Elastic Beanstalk prod/test env
  * always create a seperate/independent env for each purpose
  * don't share prod resources with test

* Elastic Beanstalk & env.yaml
  * env.yaml stores environment values

* Elastic Beanstalk & RDS Decoupling
  1. create RDS DB & add security group rule for new DB (green)
  2. remove security group rule for present DB (blue)
  4. use blue/green deployment

* Elastic Beanstalk & cloudformation
  * Elastic Beanstalk uses cloudformation to launch resources (always) ★
  * if you have issue with Elastic Beanstalk, you can take a look at cloudformation resource.

* Elastic Beanstalk Use a Lifecycle Policy ★
  * Every time you upload new app version, EB increments its version and stores previous versions.
  * You can avoid reaching version storage limits by 'Lifecycle Policy'
  * It tells EB to delete version(s) when total count of versions exceeds setting.
  * Deletes up to 100 versions each time the lifecycle policy is applied. Elastic Beanstalk deletes old versions after creating the new version, and does not count the new version towards the maximum number of versions defined in the policy.
  
* Docker Platform
  * need to run multiple docker containers on each instance --> docker multi-container platform (doesn't have proxy)
  * need to run a single docker containers on each instance --> docker single-container platform (has nginx proxy)
  * need to customize image in several ways --> custom platform

* Elastic Beanstalk & Worker Environment
  * Worker Environment is an environment that is decoupled from front-end application functionality.
  * It is useful when what you are trying to do takes a long time to complete or may compromise performance of app.
  * usage
    * set up cron.yaml to run cron tasks.
    * The Worker Environment SQS Daemon to do SQS tasks
    * use it with Dead Letter Queues

# AWS Kinesis
* Kinesis
  * Kinesis is a streaming service using Kinesis shards.
    * Kinesis Shard = sequence of data
    * Shard capacity
      * READ: 5read/sec, 2MB/sec
      * WRITE: 1000write/sec, 1MB/sec
    * As data size increases, you increase the number of shards (re-sharding)
  * Streaming data = small chunks of data that is sent from a web service.
  * Kinesis Streams consists of shards
  * Shards have TTL

  * Whenever there is a need for real-time data service, Kinesis is the answer (highly scalabe)

  * Kinesis Types
    1. Kinesis Data Streams
       * General Data streaming
    2. Kinesis Video Streams
       * General Video streaming
    3. Kinesis Firehose
       * Data streams into AWS storage service and analyze it.
       * sink: how data packaged for AWS storage service. (can be s3, redshift, elasticsearch; note that these can be analyzed)
    4. Kinesis Analytics
       * Data streams with Java/SQL
       
  * Kinesis Client Library (KCL)
    * "shard handler on consumer side"
    * A library running on consumer side at processor(ex. EC2) instance level.
    * It creates a record processor for a shard.
    * When shard number increases, KCL increases record processor number.
      * maximum number of processors (= KCL instance)
        * is the maximum number of shard possible
    * LoadBalances between multiple consumers (processors number if equally distributed to consumer instances)
    * You don't need multiple processors to process one shard. (because its only 1MB ~ 2MB).
    * One consumer instance can handle multiple shards using multiple processors.
    * Best Practice: AutoScaling Group
  
  * Kinesis Server Side Encryption
    * Kinesis automatically encrypts data before it's at rest by using an AWS KMS customer master key (CMK) you specify. (data at rest simply means data is written for storage)
  
  * Kinesis DataStream
    * Partition key is unique. It is used to group data within a stream.
      * when partition keys are not distributed well enough, data distribution across shards will be skewed causing ProvisionedThroughputExceededException. ★
      * so if you have provision issue => check partitino key and shard
      * Sequence Number is used with partition key (works like a sort key)
    
  * Kinesis Resharding
    * Kinesis resharding a sharding strategy for efficiency and cost.
    * Shard: partition of data (Shard is in stream.)
    * Stream: its capacity is measured by number of shard inside. (stream capacity = how many shards?)
    * cold shard: shard that receieves less data. It needs merge. merge decreases stream capacity (=cost)
    * hot shard: shard that receieves more data. It needs split. split increases stream capacity (=cost)  
    
   * Kinesis Retention Period
     * Kinesis data stream stores records from 24 hours by default, up to 168 hours.
    
    

# SECTION9. Developer Theroies
* CI/CD
  * CI=Continuous Integration: "Code Repo -> Build -> Test"
  * CD=Continuous Delivery: "Code Update -> Production Release"
  * AWS CI/CD Services
    ```
    ----------------------------------------------------------
    * Code Repo = AWS CodeCommit
    * Build     = AWS CodeBuild  (build spec)
    * Deploy    = AWS CodeDeploy (appspec)
    ----------------------------------------------------------
    * Manage    = AWS CodePipeline (something like Jenkins)
    ----------------------------------------------------------
    ```
* AWS CodeCommit
  * Based on Git
  * Tracks and maintains commit history.
  
  * You can either connect using HTTPS or SSH
    * HTTPS: using Git or IAM credentials (IAM recommended)
    * SSH: Generate SSH Keys
      * store private key in local
      * upload public key in code commit server to associate with IAM user
    * CODE COMMIT doesn't support http ★
  
  * CodeCommit event can be detected by Cloudwatch -> creating Cloudwatch event integration possible

  * Codecommit & encryptin
    * automatically encrypted.
    * encrypted in-transit & at-rest automatically by default.
  
* AWS CodeBuild
  * compiles and tests your code. (CodeCommit -> CodeBuild -> build Docker Image)

  * BUILDSPEC (buildspec.yaml)
    * defines step to take on each build step
    * you can specify ECS/ECR commands in this file to automate everything
      * crete CodeBuild project with 'use a build spec file' & 'codeCommit repo you have with buildspec.yml & dockerfile'
      * you can either 'use a build spec file' or 'insert build commands (do automation part in UI console)'
        * 'insert build commands' lets you override 'buildspec.yml' specification
      * you can override settings in UI
        * 'Environment variables override' section
    * you can check build logs in CodeBuild console.
    * you can enable caching as well (cache: )

  * Codebuild & encryption
    * just specify KMS to use (otherwise default AWS CMK will be used.)

  * DEBUG ★
    * you can send full logs to CloudWatch
    * you can quickly run code build in local for quick checking

  * AWS Elastic Container Service
    * AWS docker container management platform
    * You can create AWS ECS clusters. (cluster = a group of instances)
    * "ECS_ENABLE_TASK_IAM_ROLE=true" enables IAM roles for tasks in container
    
    * CLI commands
      ```
      aws ecs create-service --cluster MyCluster ... etc
      ```
    * ECS service scheduler

    * ECS task definition ★
      * docker container configuration is done thru task definition
        * port
        * cpu
        * logs

    * container agent: connect containers cluster
    
    * ECS & ecs.config
      * when ECS launches instances (EC2), it sets environment configs in /etc/ecs/ecs.config
    
  * AWS Elastic Container Registry
    * AWS docker container registry platform
    * You can create a Repository to hold each docker image
    * ECR repo is different from CodeCommit repo. You can run following command in local {myCodeCommitRepo} with Dockerfile to push docker image to AWS ECR repo. You can find push commands in AWS console
    ```
    # how to push
    aws ecr get-login --no-include-email --region eu-central-1    # do ecr login
    docker build -t {myECR-RepoName} .                            # build tag-able docker image
    docker tag ........                                           # tag it
    docker push .......                                           # push it to ECR-repo
    
    # how to pull                                                       (ECR-login -> docker pull) 
    aws ecr get-login --no-include-email --region eu-central-1          # do ecr login; ecr login does docker signin
    docker pull 1234567890.dkr.ecr.eu-west-1.amazonaws.com/demo:latest  # pull from ECR-repo
    ```
    * When ECR push/pull to a repo it calls ecr:GetAuthorizationToken to get authorized. (configure IAM)
  
* AWS Codebuild & Caching
  * cachinig is available in S3
  * in buildspec, set up cache: section

* AWS CodeDeploy
  * Compatiable with other management tools: AWS CodePipeline, Jenkins, Puppet, and ... etc.
  * CodeDeploy vs ElasticBeanStalk. (ElasticBeanStalk is more of bigger end-to-end delivery service where code deploy is deployment specific service)

* Deployment Group
  * "group of instances targeted for deployment"
  * A set of EC2 instances or Lambda functions where you will deploy new software.

* CodeDeploy agent
  * program that runs code deploy tasks for EC2 instances.
  * it is required for EC2 deployment and it actually is installed in EC2 instance.
  * it runs on HTTPS (443) (information: in general AWS resources port80 is for HTTP, port443 is for HTTPS)

  * you can't install codedeploy agent on ECS cluster (deploy a dedicated instance for codedeploy agent) ★
  * code deploy can deploy to Lambda, EC2, Onpremise! ★

* You can store revision codes in S3.

* CodeDeploy Deployment Types (INPLACE / BLUEGREEN)
  
  * In-Place Deployment
    * Applicable to: EC2, OnPremise (no Lambda) ★
    * works like Rolling Update (Rolling is a Deployment Type in ElasticBeanstalk)
    * Each instance stops an app when doing an upgrade installation.

  * Blue/Green Deployment
    * Applicable to: EC2, OnPremise(this means on premise compute platform. Instance must be EC2), ECS, Lambda ★
    * Enviroment swapping (blue env -> green env), which is a difference between immutable (no env swap)    
    * Blue is active(current) version instances.
    * Green is new version instances
    * No Performance Down.

  * Note that in-place & blue/green uses deployment configs as below allatonce,halfattime,oneatatime / canary,linear,allattime

* CodeDeploy Deployment configuratrion for EC2/OnPremise
  * AllAtOnce: Deploys up to all instances at a time (as many as possible).
  * HalfAtATime: Deploys to up to half of the instances at a time.
  * OneAtATime: Deploys the application revision to only one instance at a time.

* CodeDeploy Deployment configuratrion for Lambda
  * CodeDeploy provides three options for Lambda deployment
    * Canary: Traffic is shifted in two increments. (gradual)
      * ex) Canary10Percent10Minutes -> +10% at the beginning ---+90% gradually--> done at 10min
    * Linear: Traffic is shifted in equal increments.
      * ex) Linear10PercentEvery10Minutes -> +10% at 10min -> +20% at 20min -> ...
    * All-at-once: All traffic is shifted from the original Lambda function to the updated Lambda function version at once.
    
* AppSpecFile (appspec.yaml)
  * Defines parameters used for CodeDeploy.
  1. For Lambda Deployment
    * Lambda appspec can be written in YAML or JSON
    * three sections required (version, resources and hooks).
      1. version: reserved for future use (currently 0.0 is allowed only)
      2. resources: properties(name, alias, currentVersion, TargetVersion) of Lambda to deploy
      3. hooks (=Lambda Operations)
          * Lambda to run during deployment. You can specify point of time to execute each Lambda.
            ```
            new Lambda Created
            BeforeAllowTraffic
            AllowTraffic         (=trafficRerouted to new Lambda)
            AfterAllowTraffic
            ```
            * BeforeAllowTraffic: point of time before traffic is routed to newly deployed Lambda. (validate Lambda is deployed correctly)
            * AfterAllowTraffic: point of time after traffic is routed to newly deployed Lambda. (validate Lambda is functioning correctly)
  2. For EC2/OnPremises Deployment
    * EC2/OnPremise appspec can be only written in YAML (not JSON)
      ```
      appsepc.yml should be in in the root directory of app for EC2/OnPremises Deployment.

      Typical Setup:
        appspec.yml
        /scripts
        /config
        /src
      ```
    * three sections required (version, resources and hooks).
      1. version: reserved for future use (currently 0.0 is allowed only)
      2. os: OS you are using
      3. files: location of file to be copied from and to (source & destination)
      4. hooks: Lifecycle Hooks to run during deployment. You can specify point of time to execute each Script.
        ```
        7: blocktraffic.applicationstop.downloadbundle.install.applicationstart.validateservice.allowtraffic

        * BeforeBlockTraffic: Run tasks on instances before they are deregistered from a load balancer
        * BlockTraffic: Deregister instances from a load balancer
        * AfterBlockTraffic: Run tasks on instances after they are deregistered from a load balancer

        * ApplicationStop: stop app to prepare for new version deployment 
        * DownloadBundle: copy app revision files to temp location
        * BeforeInstall: before Install
        * Install: Codedeploy agent copies the app revision files from temp location to correct location
        * AfterInstall: after Install
        * ApplicationStart: start app for new version deployment
        * ValidateService: after app is started (last deployment lifecycle event where you can do something)

        --------------------------deployment end--------------------------

        * BeforeAllowTraffic: Run tasks on instances before they are egistered from a load balancer
        * AllowTraffic: register instances from a load balancer (last!last! lifecycle that is reserved for code deploy)
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
  * overall build/deploy management tool
  * You can configure it to trigger a pipeline when code change is committed in repo
  * If one step in a pipeline fails, the whole flow stops there.
  * speed up pipeline -> do parallel tasks (there is no caching in pipeline tasks)

* AWS CloudFormation
  * CloudFormation lets you manage AWS infrastructure as code
  * Supports YAML/JSON
  * CloudFormation template describes endstate of infrastructure you want to provision.
  * You upload template to CloudFormation using S3
  * Resulting resource is called "CloudFormation Stack".
  * If deployment fails (part of deployment), it rolls back the entire stack. (default)
  * When you want to delete an exporting stack, you have to delete the stack that imports the stack.

  * AWS Cloudformation templates can be shared across region and accounts ★

  * CloudFormation Template (sections)
    ```
    {
      "AWSTemplateFormatVersion" : "version date",

      "Description" : "JSON string",

      "Metadata" : {
        template metadata
      },

      "Parameters" : {
        set of parameters
      },

      "Mappings" : {
        set of mappings
      },

      "Conditions" : {
        set of conditions
      },

      "Transform" : {
        set of transforms
      },

      "Resources" : {
        set of resources
      },

      "Outputs" : {
        set of outputs
      }
    }
    ```
    * Metadata
      * information about this template
    * Parameters
      * input custom values (ex: env type)
      * it can't be dependent to each other, they are all independent ★
      * You can't define conditions in parameter section.
      * Conditions make use of what's coming from parameter.
    
    * Conditions
      * value based on condition (ex: create some resources based on input)
      * Conditions section is a collection of condition function
      * Condition function returns True/False, determining rest of the part in the section to be continued.
    * Resources
      * the only mandatory section, defining AWS resources to create (ex: aws resource you want to deploy(create) with this cloudformation)
      * the order it defined doesn't matter, they are created in a parallel manner ★
    * Mappings
      * custom mappings of key to different values (ex: use different key-values for different regions; AMI is an amazon VM image)
    * Transforms
      * AWS SAM version
      * external reference to S3 (when used with AWS::Include)
    * Outputs
      * export for cross-stack reference
      * For each AWS account, Export value must be unique within a region. (not across the global)
        * note that output doesn't have to exported
        * Export under output is a name you want to export for import in others
      * whatever is Export name can be retrieved with Fn::ImportValue in different stack.
    * CloudFormation templates are uploaded to S3 (by default it creates one in a region, and reuse it for that purpose)

* Cloud Formation & StackSets ★
  * Feature of applying create, update, or delete stacks across multiple accounts and regions with a single operation

  * AWS SAM (Serverless Application Model)
    * AWS SAM CLI lets you locally build, test, and debug serverless applications
    * SAM template is an extension of CloudFormation template
      * When there are need for serverless approach and you were given two options SAM vs CFN: SAM is closer to answer (even though CFN can deploy serverless apps)
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
      * you still need CloudFormationTemplate (AWSTemplateFormat). Look at Transform and Resources.Type
        ```
        AWSTemplateFormatVersion: '2010-09-09'
        Transform: 'AWS::Serverless-2016-10-31'
        Resources:
          MyFunction:
            Type: 'AWS::Serverless::Function'
            Properties:
              Handler: index.handler
              Runtime: nodejs6.10
              CodeUri: 's3://my-bucket/function.zip'
        ```
    * Serverless App types
      ```
      AWS::Serverless::Api          -> api gateway resource
      AWS::Serverless::Application  -> application
      AWS::Serverless::Function     -> labmda
      AWS::Serverless::LayerVersion -> lambda layer version (library or runtime code)
      AWS::Serverless::SimpleTable  -> dynamoDB
  
  * Creating a Lambda Function (Example)
    * How to create
      * Option 1) upload all the code as a zip to S3 and refer the object in AWS::Lambda::Function block.
        ```
        "AMIIDLookup": {
          "Type": "AWS::Lambda::Function",
          "Properties": {
              "Handler": "index.handler",
              "Role": {
                  "Fn::GetAtt": [
                      "LambdaExecutionRole",
                      "Arn"
                  ]
              },
              "Code": {
                  "S3Bucket": "lambda-functions",
                  "S3Key": "amilookup.zip"
              },
              "Runtime": "nodejs8.10",
              "Timeout": 25,
              "TracingConfig": {
                  "Mode": "Active"
              }
          }
        }
        ```
      * Option 2) wite the code inline for Node.js and Python as long as there are no dependencies for your code. if you choose to use 'ZipFile' option. Put the code as inline and cloudformation will zip it and put it in deployment package.
        ```
        mastertestingLambdaDataDigestor:
          Properties:
            Code:
              ZipFile: >
                def handler(event, context):
                  pass
            FunctionName: mastertesting_Kinesis2DynamoDB_Datapipeline
            Handler: handler.kinesis_to_dynamodb
            Role: SOMEROLE
            Runtime: python3.6
          Type: AWS::Lambda::Function
        ```
  
  * CloudFormation Nested Stacks
    * Nested Stacks allow re-use of CloudFormation code.
    * Allows you to reuse pieces of CloudFormation code in multiple templates, for common use cases like provisioning a load balancer or web server?
    * in CloudFormationTemplate, specify Type as follows
      ```
      Resources:
       Type: AWS::CloudFormation::Stack
      ```
  * CloudFormation & Protection ★
    * Stack Termination Protection
      * = accidental termination
      * protects accidental termination
    * Rollback Trigger
      * = CFN delete
      * quickly roll back by deleting the whole 'new' stack

* CFN & CLI commands
  ```
  cfn-init: install, create and start
  cfn-signal: synchronize other resources in the stack
  cfn-get-metadata: retrieve metadata
  cfn-hup: check for updates (helper-update)
  ```


# AWS Cognito
* Web Identity Federation
  * Concept of granting permission to users who are authorized/authenticated through web identity provider.
    * (Google/Facebook/Amazon...etc).
    * term: on-premise directory is 3rd-web domain. this can be also authenicated with aws cognito
  * Successful authentication from web identity provider is followed by getting an authentication code which can be traded for other credentials.
* Amazon Cognito provides Web Identity Federation
  * = Identity broker between app and web identity provider.
    * Cognito brokers between app and Web Identity Provider maps authentication code to temporary credential for IAM role. (Web Identity Provider --> Authentication Code --> Cognito Broker --> Temporary Credential --> IAM Role
  * Provides sign-up/sign-in and guest access.
  * Synchronizes user data across devices
  * AWS Recommended Approach for mobile apps.



* Cognito User Pools
  * User Authenicator
  * sign-up, sign-in, 3rd-web sign-in (not necessarily 3rd-web sign in required)
  * token provision (for users that are authenticated) (ex: JWT)

* UserPool gives token to authenticated users -> this token is sent to Identity Pool for temp credentials -> Access AWS Resource

* Cognito IdentityPool
  * User AWS-TEMP-Enablemend
  * provide temporary AWS credentials for users who are guests (unauthenticated) for those who have token.

  ```
  WebIdentityProvider -> User Pool -> Identity Pool -> AWS Resources
                         sign up      temp creden.
                         sign in
                         token
  ```
  * AWS SNS is used to send silent notification to associated devices when user data is updated.

* AssumeRoleWithWebIdentity
  * API provided by STS (Security Token Service) that returns temp credential to be used in AWS Resources
    * Returns AssumedRoleUser (ARN & Id) which uniquely tied to temporary credentials (not IAM role/user)
  * Cognito uses STS to retrieve temp credentials for WebIndentityProvider authenticated users.
    ```
    1. WebIdentityProvxider --> AssumeRoleWithWebIdentity --> STS --> AWS Resources
    ```

* AWS Cognito Sync vs AWS AppSync ★
  * AWS AppSync is stronger.
  * AWS AppSync: allows multiple users syncchronization in real time on shared data
  * AWS Cognito Sync: doesn't have multiple user sync feature

* AWS STS (security token service)
  * STS allows you to request temporary AWS credentials
  * API Gateway doesn't support STS
  * STS provides several auth related apis
  
* AWS STS APIs ★
  * GetFederationToken
    * federated users (not necessarily webfederated)
  * GetSessionToken
    * MFA enabled users get token

  * AssumeRole
    * need access to other aws resources
  * AssumerRoleWithSAML
    * SAML(SSO, CSO, Auth0) authenticated
  * AssumeRoleWithWebIdentity
    * 3RD-web-authenticated, cognito
    * Returns a set of temporary credentials for authenticated users (via 3rd party web)

  * DecodeAuthorizationMessage
    * decode auth msg
  



# CloudWatch
* CloudWatch can monitor Compute, Storage, DB, Analytics and others...
* CloudWatch can be used on premise apps (not limited to AWS resources)

* CloudWatch Metrics ★
  * Host Level Metrics
    * CPU, Network, Disk and Health Status
    * You can't get storage of virtual disk it is a custom metric, Disk here in host level means disk IO.
  * Custom metric
    * RAM Utilization
    * Disk Utilization
    * number of requests per min
  * Retrievable with API

  * CloudWatch & Retention ★
    * By default logs are kept indefinitely.
    * You can configure this by setting expiration policy

  * You can retrieve logs of terminated AWS resources.
  * You can set up alert when CloudWatch monitors the conditions you are looking for
  
* CloudWatch vs CloudTrail vs AWS Config vs AWS X-Ray
  * CloudWatch is performance monitor tool (health, operational status)
  * CloudTrail is API logger/debugger tool (API logs)
  * AWS Config is aws resource configuration monitor tool
  * AWS X-Ray is trouble shooting tool (not monitoring/tracking)
  
* In CloudWatch, you can create a cron event to regularly trigger something like backing up a DB.

* Cloudwatch monitor & granularity
  * 1 - 60 sec (high resolution)
  * 1min (standard detailed; detailed monitoring)
    * detailed monitoring is about granularity (1min), it doensn't extend metrics you are watching.
    * detailed monitoring also requires additional cost
  * 5min (standard default)

* CloudWatch alarm
  * 10sec/30sec (high resolution)
  * 1min (regular)



* Cloudwatch Alarm
  * state
    * OK: metric/expression is within defined threshold
    * ALARM: metric/expression is outside of defined threshold
    * INSUFFICIENT_DATA: alarm just started and data is not enough.
  * setting
    * Period: interval to evaluate metric/expression
    * Evaluation Period: recent periods to determine ALARM state.
    * Datapoints to Alarm: number of data points in evaluation period to determine ALARM state
    * ex: when you set '4 Datapoints to Alarm & 3 Evaluation Period', alarm will set when there are breach more or equal to 4 during 3 recent period.

* Cloudwatch Event
  * Events(generated by AWS resources), Rules(how events are defined), Targets(who will process event)

  * Alarm vs Event
    * alarm: indication of metric/expression outside of defined threshold (to acknowledge)
    * event: indication of change is AWS environment/resource (to process)
    
* CloudWatch Log Export
  * You can export cloudwatch log data to S3 bucket. (doesn't need lambda in this integration)
  
* CloudWatch Metrics ★
  * Count: count of API calls.
  * CacheHitCount (count served in caching)
  * CacheMissCount (count served in backend)
  * Latency: responsiveness of overall API calls.
  * IntegrationLatency: responsiveness of the backend. (Integration == Backend)


# Other Topics & Tips
* AWS System Manager (SSM)
  * Parameter Store
  * State Manager

* AWS SSM Parameter Store
  * Where would you store confidential information (credentials, license codes) for AWS resources? Parameter Store
  * applies to runtime env provision
  * can also integrated for retrieving latest resource info (latset AMI Id) ★
  * SecureString
    * you can create SecureString parameters
    * plainText as parameterName, KMS encryptedValue as parameterValue
    * SSM uses AWS KMS to encrypt and decrypt the parameter values of Secure String parameters.

* AWS SSM State Manager
  * Automates AWS EC2 state keeping process

* AWS Secrets Manager
  * AWS Secrets Managers stores your confidential information.
  * It is similar to SSM but has more ability such as 'secrets rotation' & 'password generation'.

* AWS Service Catalog
  * creates and manages IT catalogs

* Default AWS SDK Region = US-EAST-1

* Your mission-critical web application needs to store its session state so that it can be accessed quickly and is highly available. Which service should you use?
  * session stae is relational
  * DynamoDB & Elasticache (DAX is a caching service)

* Security
  * AWS Shield: managed Distributed Denial of Service (DDoS) protection service (layer3, layer4)
  * AWS WAF(=Web Application Firewall): Firewall that helps protect your web applications from common web exploits (ex. SQL injection, IP attack, CrossSiteScripting)
  * Amazon Macie: Security service that uses machine learning to automatically discover, classify, and protect sensitive data in AWS.
    
* AWS intrinsic functions
  ```
  Fn::Ref       return value of parameter or resource such as ID
  Fn::GetAtt    return attribute value of a resource
                ex) !GetAtt myELB.SourceSecurityGroup.OwnerAlias
  Fn::Sub       substitues string value
  Fn::And
  Fn::Equals
  Fn::If
  Fn::Not
  Fn::Or
  Fn::Join      joins a string by delimiter
  ```

* EB vs ECS (ElasticBeanStalk vs ElasticContainerService + CodeDeploy) ★
  * Elastic Beanstalk (multi-container) is an abstraction layer on top of ECS (Elastic Container Service) with some bootstrapped features and some limitations
  * EB is easier, ECS gives more-control

* CloudFormation & Find In Map
```
{ "Fn::FindInMap" : [ "MapName", "TopLevelKey", "SecondLevelKey"] }
!FindInMap [ MapName, TopLevelKey, SecondLevelKey ]

"RegionMap" : {
  "us-east-1" : { 
    "HVM64" : "ami-0ff8a91507f77f867", "HVMG2" : "ami-0a584ac55a7631c0c" 
  },
}

"Fn::FindInMap" : [ 
  "RegionMap", 
  { 
    "Ref" : "AWS::Region" 
  }, 
  "HVM64" 
]   returns ami-0ff8a91507f77f867
```

* When using RDS & ElastiCache for an ElasticBeanStalk application
  * Elasticache can be defined in .ebextensions (because if given externally then what happens if cache server disappears?)
  * RDS can be referneced thru environmental variables (because if defined in .ebextensions, lifecycle is coupled to app lifecycle)
    
* Instead of KMS encryption, you can encrypt in application layer using EncryptionSDK

* Cognito
  * Authorization in Cognito IdentityPool --JWT--> Handle/Validate JWT Token in Cognito UserPool
  
* CloudFormation & Codepipeline uses its artifact store as S3 (artifact is a file that is created during build process)

* Canary Release
  * When releasing a new API, route slowly to a new version (you can configure percentage of this)
  
* Error Cause
  * Authorization -> IAM Roles and Permission
  * Timeout -> Security Group Rule (EC2 Security Group Rule != IAM Group)
  
* Security Group acts as a firewall. It is a service for EC2 & RDS.

* In General, anything comes out of encrypted AWS DB is encrypted.
  * ex) encrypted RDS -> encrypted snapshot
  
* AWS Glue
  * data extract & load for analytics

* AWS ACM
  * AWS Certificate Manger
  * import/manage/provide SSL/TLS certificates
    * AWS Certificate Manager (if it is in a supported region) ★
    * AWS IAM Certificate Store (if it is not in a supported region) ★

* AWS CodeStar
  * Quick CICD management tool like codepipeline (light version of code pipeline)

* AWS QuickSight
  * ML based data analysis & visualization tool. (quickly sight data)
  
* AWS Athena
  * AWS data query service
  
* AWS Fargate
  * serverless compute service.

* When there is data abuse
  * blocking IP address is not a solution. Abuser can easily change IP address.

* AWS Inspector ★
  * EC2 troubleshooter tool

* AWS Billing
  * Consolidated Billing: proper for multiple account billing ★
  * AWS Cost and Usage: proper for estimation, usage types and operation (single account)
  * Detailed Billing: will be deprecated

* AWS CloudHSM(hardware security model) ★
  * key management service with symmetric&assymetric encryption (KMS doesn't have assymetric)
  * stores cryptographic keys
  * uses symmetric/asymmetric encryption algorithm (KMS doesn't do asymmetric)

* Error codes
  * 403 Forbidden (authentication error)
    * EXAM TIP
      * AUTHENTICATION FAIL -> check IAM
      * TIMEOUT -> check Security group
  * 429 Too Many Request
    * throttling error
    * concurrency
  * 502 Bad Gateway
    * general API Gateway error: for example Lambda passes non-JSON result to API Gateway
    * type map error
  * 504 Gateway Timeout
    * INTEGRATION_TIMEOUT ★
      * executed more than 29secs (50milsecond - 29sec configurable)
      * ex: if lambda takes 27~35secs to complete
    * INTEGRATION_FAILURE
      * integration failed somewhere for some reason
        * ex: lambda throws exception and integration fails all the time

* AWS Route Table
  * table that contains subnet configuration,
  * one subnet can only be associated with one route table. (one subnet can read one route table)
  * one route table can be associated with multiple subnets. (one route table can be pointed from multiple subnets)

* Limits
  * KMS limit 4kb
  * Lambda environmental variable: 4kb
  * Lambda deployment package: 250MB
  * Lambda layer: 250MB
  * Lambda /tmp: 512MB
  * SQS 1msg: 256kb

* Automatic Scale
  * Lambda, DynamoDB, SQS, Codecommit, EC2 Spot Fleet scales automatically.
  * RDS doesn't scale automatically.

* commands ECS, ECR, CFN, SAM
  ```
  aws ecs create-cluster --cluster ... etc             (create cluster)
  aws ecs create-service --cluster MyCluster ... etc   (create service task)
  ```
  ```
  aws ecr get-login --no-include-email --region eu-central-1
  docker build -t {myECR-RepoName} .
  docker tag ........
  docker push .......
    
  aws ecr get-login --no-include-email --region eu-central-1
  docker pull 1234567890.dkr.ecr.eu-west-1.amazonaws.com/demo:latest
  ```
  ```
  aws cloudformation package --template-file /path_to_template/template.json --s3-bucket bucket-name --output-template-file packaged-template.json
  aws cloudformation deploy --template-file /path_to_template/template.json --stack-name my-new-stack --parameter-overrides Key1=Value1 Key2=Value2 --tags Key1=Value1 Key2=Value2
  ```  
  ```
  eb deploy
  ```
  ```
  sam package --template-file ./cloudFormationTemplate.yml --output-template-file samTemplate.yml --s3-bucket bucketname
  sam deploy --template-file ./samTemplate.yml --stack-name serverlessStack --capabilities CAPABILITY_IAM
  ```
  * package is uploading
  * deploy is deploying


* AWS Data-pipeline
  * tool that helps moving data between different accounts or resources

* Review
  * T1E1: 3, 12, 33, 37, 43, 47, 49, 51, 64
  * T1E2: 5, 6, 11, 21, 32, 36, 42 (region?), 43, 46, 49, 50, 52, 53, 62
  * T1E3: 2, 3, 4, 7, 8, 12, 17, 21, 24, 25, 29, 35, 36, 41, 43, 44, 56, 58, 59, 64
  * T1E4: 10, 25, 28, 30, 34, 40, 45, 46, 50, 51, 52, 57, 58
  * T1E5: 7, 8 
  * T2E1: 3, 5, 6, 8, 13, 15, 16, 20, 24, 25, 28, 29, 44, 45, 48, 49, 54, 55, 56, 60, 64
  * T2E2: 12, 16, 20, 21, 33, 38, 45, 50, 57, 58, 60, 61
  * T2E3: 6, 10, 14, 17, 18, 29, 35, 46, 47
  * T2E4: 