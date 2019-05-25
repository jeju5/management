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
  * Instance Type (optimized for)
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
      