**REDIS**

**REDIS IS SINGLE THREADED FOR  USER OPERATION.**

*********
SINGLE THREADED vs MULTI THREADED
- SINGLE Threaded is preferred when the operations are CPU intensive, even if we use multiple threads, CPU capacity is the same. context switching will consume cpu cycles and operations get slower when compared to single thread executing
- If the operations are IO intensive, multithreading env will be performant, because when the thread is waiting for a IO operation, it does not consume CPU cycles. hence other threads can utilize the CPU during that time
- if Single, no hassle of resource sharing, hence no locking
*********

As Redis writes the data in memory and there are no IO operations , Redis decided with single thread

Key - Value(many structures)
    - Cant have relations between entries(keys), as its majorly key value
- cache
- Datastore
- Message Broker

- In Memory Datastore

Pros-Cons from RDBMS 
- Faster writes and reads (as stores in primary memory)


Cons
- Not Durable (RAM)
- Low storage capability

- Not for large files(BLOBs)


Architecture
- Redis Client
- Redis Server (uses this server's RAM)
- Both can sit on same or diff boxes
- uses REDIS protocol
![Screenshot 2024-08-20 at 12.07.10 AM.png](images%2FScreenshot%202024-08-20%20at%2012.07.10%E2%80%AFAM.png)


Redis client
- console
- API (JEDIS for Java)

Problem - Durability ?
- All computations/ Live accesses/ Live storages happen in server and it is in memory
- to make sure data is not lost
  - RDB : redis data backup
    - Specific intervals/ Regular snapshot to be stored in some hard disk
    - Creates .rdb files to store in disk
    - .rdb files is only for backup( does not serve requests)
    - ![Screenshot 2024-08-20 at 12.32.30 AM.png](images%2FScreenshot%202024-08-20%20at%2012.32.30%E2%80%AFAM.png)
    - Data can be lost in between intervals
    - to create a RDB, redis spuns a new thread and not on the main thread
    - Network intensive as rdb are heavy
    
  - AOF - Append only File
    - Disk has a file , which is appended only
      - Any operation on server is to be logged in the file
      - ![Screenshot 2024-08-20 at 12.34.49 AM.png](images%2FScreenshot%202024-08-20%20at%2012.34.49%E2%80%AFAM.png)
      - File has commands(will have data.. add key, update key and so on) to get back the original/latest state of the data
      - Heavier to maintain, Latency and network calls
      - ![Screenshot 2024-08-20 at 12.57.54 AM.png](images%2FScreenshot%202024-08-20%20at%2012.57.54%E2%80%AFAM.png)
      - why not 2nd method ?
        - finding key would be costly in the disk - seeking is costly
        - doing multiple modifications can corrupt the file
        - AOF - only last cmd can be corrupted
        - ![Screenshot 2024-08-20 at 1.03.59 AM.png](images%2FScreenshot%202024-08-20%20at%201.03.59%E2%80%AFAM.png)
        - chunks in AOF
        - cons of 1method
          - High boot time - has to replay all cmds
          - if running for larger time, AOF will get lengthy
          - Redis in the background rewrites the log file, keeps only latest of the key(same as SS table and stuff)
  - Why not RDB + AOF?
    - Good solution - better boot time


- RDB is perfect if we want to store backups , share backups
- RDB is perfect for disaster recovery, store in other regions encrypted
- ![Screenshot 2024-08-20 at 1.16.41 AM.png](images%2FScreenshot%202024-08-20%20at%201.16.41%E2%80%AFAM.png)
*********
- When ever we want to store the rdb in the disk, we basically have to spun a new thread. Which at the OS level would be forking the existing thread and copying context to the child thread. 
- If the rdb file is heavy, forking would take time and make REDIS SERVER unresponsive for that time
*********

AOF
- Rewrites to shorten the log file
- does not loose data, as it works on copy of the file

**REDIS REPLICATION**
- Master - slave config
- ![Screenshot 2024-08-20 at 1.23.26 AM.png](images%2FScreenshot%202024-08-20%20at%201.23.26%E2%80%AFAM.png)
- fault tolerant, slave can become Master
- faster reads

- Many depend on replication only rather than using RDB or AOF
- If the data is very critical, its better to combine replication with RDB or AOF
- * REDIS by default does async replication 
- ![Screenshot 2024-08-20 at 1.29.08 AM.png](images%2FScreenshot%202024-08-20%20at%201.29.08%E2%80%AFAM.png)

- We can tweak the config as well using *WAIT* command, it waits till x number of slaves to be copied
- ![Screenshot 2024-08-20 at 1.31.48 AM.png](images%2FScreenshot%202024-08-20%20at%201.31.48%E2%80%AFAM.png)

- We will have some lag and in real system, partition is for sure
- some ack cmd to say it has read stream till that time
- If multiple acks are missed , Master might want to stop the stream to save bandwidth
- when slave is back, partial resynchronisation request is triggered
- If slave is back after long long time and Master is not able to fulfill the partial resync request as it might not be holding that long of a delta. That time it goes will complete synchronization
- Master and slave will communicate on REDIS Protocol only

- Replication activity is a non blocking on the Master
- Non blocking on slave as well, reads on slave is also catered using prev version of data
- redis.conf - make reads on slaves blocking when sync is happening

NOOB Mistake
- Most of the systems are set up with auto restart on the machines and Redis persistence is OFF
- ![Screenshot 2024-08-20 at 1.48.58 AM.png](images%2FScreenshot%202024-08-20%20at%201.48.58%E2%80%AFAM.png)
- The system is good , up and running
- If the master goes down, there would be a network partition with the slaves
- As the auto restart is on, the Master is back , up and running. As the data was stored in memory, data is lost
- Master election didn't happen because the Master was not down for so long time.
- When the master is back, the slaves would send a partial resync and Master would not understand it and then next slaves would request for complete resync and empty Master will be synced with Slaves wiping out the data in slaves as well.

PERSISTENCE OR STOP AUTO RESTART

**REDIS value DATASTRUCTURES**
- KEY : STRING (512 MB only)
  - Binary safe string - sometimes when we read a file we see funny chinese like characters, it because of encoding and decoding in different formats(UTF8)
  - Any string is safe 
  - If keys are long , retrieval will be longer
  

- VALUES
  - String
  - List : LinkedList (mostly doubly)
  - Set
  - Sorted Set( value is associated with some score)
  - Hash (key value)
  - Stream -

REDIS - 2
-  Redis also gives us transactions
- *MULTI*  
  - SET
  - GET
  - ADD
- *EXEC* 

- The commands are not executed but are queued until *EXEC* is called.
- The commands will be executed sequentially
- Util these commands are executed no other command will be executed
- And individual and all together as well will be *ATOMIC*
- Other commands will be blocked from EXEC call and not from MULTI call
- *ISOLATION* is also given, as no other commands will be executed after the EXEC cmd
- NOT DURABLE and NOT CONSISTENCY - not well-defined for REDIS 

- How does transaction work with persistence? AOF ?
  - What if first few cmds got executed and written to AOF and 3rd cmd failed. How do you remove the AOF ? its read only file.
    - REDIS writes into AOF only after it has executed all the commands

- **REDIS IS SINGLE THREADED FOR  USER OPERATION.** For its operations it might create multiple threads internally.

**REDIS SHARDING**
- Using single machine RAM would limit storage capabilities. hence cluster of machine serving as Redis server
- Redis also uses something similar to Consistence Hashing called HASH SLOT to decide which key into what cluster.

![Screenshot 2024-08-20 at 9.35.43 PM.png](images%2FScreenshot%202024-08-20%20at%209.35.43%E2%80%AFPM.png)

***HASH SLOT***
- Redis has fixed number of hash slot - 2^14 = 16384
- Redis by default has 16384 slots
- The key of the entry will be hashed and % 16384 will be done
- The key will be present hash slots from 0 to 16383

- If we one machines these slots will be part of 1 machines
- If we have two machines, will slot will be split into 2machines and so on.

- There are queries which involve single key and multiple keys as well.
- Redis client is aware that what shard to call to get the key
- Intershard queries are possible when executing queries which involve multiple keys
- ***REDIS CLUSTER ONLY ALLOWS TO HAVE INTRASHARD QUERIES BY DEFAULT***

- ![Screenshot 2024-08-20 at 9.47.44 PM.png](images%2FScreenshot%202024-08-20%20at%209.47.44%E2%80%AFPM.png)

- If we have a use case where we need to perform operations on multiple keys, we should have all those keys on one single shard
![Screenshot 2024-08-20 at 10.16.18 PM.png](images%2FScreenshot%202024-08-20%20at%2010.16.18%E2%80%AFPM.png)
if we have key such as the above
All iiitd students in one single shard

****WE can use REDIS TAGS****
We can inform Redis which part of keys to considered for hashing
![Screenshot 2024-08-20 at 10.18.14 PM.png](images%2FScreenshot%202024-08-20%20at%2010.18.14%E2%80%AFPM.png)



![Screenshot 2024-08-20 at 10.22.22 PM.png](images%2FScreenshot%202024-08-20%20at%2010.22.22%E2%80%AFPM.png)

![Screenshot 2024-08-20 at 10.58.28 PM.png](images%2FScreenshot%202024-08-20%20at%2010.58.28%E2%80%AFPM.png)

High Consistency, lower Availability
![Screenshot 2024-08-20 at 11.04.03 PM.png](images%2FScreenshot%202024-08-20%20at%2011.04.03%E2%80%AFPM.png)

Async replication - lesser Consistency, higher Availability with Async replication. We might miss some writes on Master if it goes down before sending it to replicas
![Screenshot 2024-08-20 at 11.04.26 PM.png](images%2FScreenshot%202024-08-20%20at%2011.04.26%E2%80%AFPM.png)

SYNC Replication , both consistency and Availability?? Performance will be low 
CAP Theorem breached?? 
- if the slave S1 is down, Master M1 will not be able to serve the requests as it has to do sync update to its replica, hence the M1 is not available, **AVAILABILITY TAKES A HIT**
![Screenshot 2024-08-20 at 11.02.54 PM.png](images%2FScreenshot%202024-08-20%20at%2011.02.54%E2%80%AFPM.png)

*WAIT* command can be used 
















