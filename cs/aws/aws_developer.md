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
  * EC2 = ECC = Elastic Cloud Computing = Computing Service in Cloud
  * Pricing
    * on demand: price by time -> good for short-term need
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
  * EBS
    * Volumes that can be attached to EC2
    * Types
      * SSD
        * GP2 (General): 3IOPS/GB, max 16,000 IOPS (IOPS = input/output operations per second)
        * IO1 (Intensive): proper for more than 16,000 IOPS
      * HDD
        * SC1
          * lowest cost among current
          * use case: file server
          * can't be boot volume
        * ST1
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
        * when your load balancer fails, it throws 504 error (gateway timeout)
        * x-forwarded-for header
          * public ip makes a request to domain name
          * DNS service transfers the request to a load balancer
          * load balancer transfer a request to Application Server
          * Application Server will get public ip as x-forwarded-for header
      * Route53 Lab
        * Route53 is AWS DNS service
          * it maps the request to EC2, S3 or load balancer
        * AWS -> EC2 -> Load Balancers
          * Create Load Balancer
            * you have 3 options (Application Load Balancer(HTTP/HTTPS), Network Load Balancer(TCP), Classic Load Balancer)
            * select Application Load balancer for our use (flexible and general)
            * configure name, listeners(protocol(HTTP/HTTPS) and port) and zones(region)
            * configure security group
            * configure routing (DNS Name --mapping--> App Server); you can route a request to Instance, IP, Lambda function
            * select EC2 Instance you created
            * configure health check
        * AWS -> Route53
          * Domains -> Register Domain (buy a domain and register it)
          * Hosted Zones
            * select the domain you registered
            * create record set
            * select Alias (this allows DNS server to map a request to AWS service you created) -> select the load balancer you created
        * TEST: make a request to the domain you registerd ---> DNS server ---> load balancer --> EC2

         -> DNS management get started now
          * Create hosted zone



