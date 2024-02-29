## CAP - https://www.scaler.com/academy/mentee-dashboard/class/147579/session?joinSession=1
###### Consistency - On every read, the distributed system should return the value of the latest write or error
- Never return a stale value, give latest write or error
### ACID - NO violation of constrain - The sum of balance before is equal to the sum of balances after the transaction 
![Screenshot 2024-03-01 at 12.24.13 AM.png](resources%2FCAP%2FScreenshot%202024-03-01%20at%2012.24.13%E2%80%AFAM.png)

###### Availability - The system is always available to take your request/ answer your request even though the answer may be stale
- Should always be available to read or write into the system
- Stale value on read is ok but no error

###### Partition Tolerance - Network partition 
- when we have multiple machines, they talk on network
- Network partition : 2 machines are not able to talk to each other, not able to send packets, not able to receive ack, bcoz of rat bite, router fail, network is overloaded
![Screenshot 2024-03-01 at 12.43.08 AM.png](resources%2FCAP%2FScreenshot%202024-03-01%20at%2012.43.08%E2%80%AFAM.png)

## CAP Theorem
- by Google engineer - Eric
- A system tries to be consistent, tries to be available
##### but when ever your system faces network partitioning you have to prioritize either strong consistency or full availability

#### why does app servers talk to each?
1. When a server goes down , its replica has to transfer data to the other servers

#### Example - Event Reminder Service

##### Banking - C >>> A
##### Fb Comments - A >>> C
##### Video ingestion on Hotstar - C >>> A
##### Trading ? both are important . How to handle?
Minimise network partitioning , but cant be zero
- try to make less network call
- Strong Intranet
- invest more in infra
- But when there is a NP, C >>> A

##### SPANNER DB - GOOGLE
- Propriety hardware
- super redundant network cables ? many connected cable, if one cable is not ablt to tranfer data , use other route
![Screenshot 2024-03-01 at 1.17.56 AM.png](resources%2FCAP%2FScreenshot%202024-03-01%20at%201.17.56%E2%80%AFAM.png)
### Consistency Spectrum CAP - C stands for Strong Consistency
![Screenshot 2024-03-01 at 1.26.39 AM.png](resources%2FCAP%2FScreenshot%202024-03-01%20at%201.26.39%E2%80%AFAM.png)
### Availability Spectrum 
- Double 9 availability = 99% availability
- Triple 9 availability  = 99.9% available, downtime ~ 9hours/ year
- Four 9 availability = 99.99% available, downtime ~ 1hr / year

##### PACELC - extension of CAP 
- ![Screenshot 2024-03-01 at 1.35.28 AM.png](resources%2FCAP%2FScreenshot%202024-03-01%20at%201.35.28%E2%80%AFAM.png)
- E - Even otherwise
- L - Latency
- C - Consistency
- There is always a trade off between Latency and Consistency, if we want consistency(write at multiple places), Latency will increase
![Screenshot 2024-03-01 at 1.37.16 AM.png](resources%2FCAP%2FScreenshot%202024-03-01%20at%201.37.16%E2%80%AFAM.png)
#### Storage Layer
- Archival /cold storage
- Sharding (dividing data)
- Replication ( copying data)

### REPLICATION

##### MASTER SLAVE REPLICATION
![Screenshot 2024-03-01 at 1.45.14 AM.png](resources%2FCAP%2FScreenshot%202024-03-01%20at%201.45.14%E2%80%AFAM.png)
