### Manage No SQL Shards

![Screenshot 2024-03-03 at 12.03.47 PM.png](resources%2FManage_NOSQL_Shards%2FScreenshot%202024-03-03%20at%2012.03.47%E2%80%AFPM.png)

### OLD CH
-![Screenshot 2024-03-03 at 12.09.20 PM.png](resources%2FManage_NOSQL_Shards%2FScreenshot%202024-03-03%20at%2012.09.20%E2%80%AFPM.png)

### Adding replication for CH
- Before : 1 Server = 1 Shard
- Now server marking won't be there on the CH virtual ring, we will have shard marking
- ![Screenshot 2024-03-03 at 12.12.18 PM.png](resources%2FManage_NOSQL_Shards%2FScreenshot%202024-03-03%20at%2012.12.18%E2%80%AFPM.png)
- When we use Master- Slave replication inside each shard( collection of server in this case), our shards cannot crash

## Orchestrator
![Screenshot 2024-03-03 at 12.21.45 PM.png](resources%2FManage_NOSQL_Shards%2FScreenshot%202024-03-03%20at%2012.21.45%E2%80%AFPM.png)
![Screenshot 2024-03-03 at 12.24.54 PM.png](resources%2FManage_NOSQL_Shards%2FScreenshot%202024-03-03%20at%2012.24.54%E2%80%AFPM.png)

- Add new m machines
![Screenshot 2024-03-03 at 12.26.41 PM.png](resources%2FManage_NOSQL_Shards%2FScreenshot%202024-03-03%20at%2012.26.41%E2%80%AFPM.png)
###### What to do with new machines?
- Ensure sufficient(R) replacement machines, if multiple servers crash
- Add new shard to decrease load on existing shards
##### No of Shards = (m-R)/X(rep factor)
- Increase replication factor
- ![Screenshot 2024-03-03 at 12.29.10 PM.png](resources%2FManage_NOSQL_Shards%2FScreenshot%202024-03-03%20at%2012.29.10%E2%80%AFPM.png)
- ![Screenshot 2024-03-03 at 12.34.35 PM.png](resources%2FManage_NOSQL_Shards%2FScreenshot%202024-03-03%20at%2012.34.35%E2%80%AFPM.png)

###### Put idle machines to work
![Screenshot 2024-03-03 at 12.38.11 PM.png](resources%2FManage_NOSQL_Shards%2FScreenshot%202024-03-03%20at%2012.38.11%E2%80%AFPM.png)
- Now as the X is more than min, even if one server crash X is maintained.

## SHARD CREATION
![Screenshot 2024-03-03 at 12.54.45 PM.png](resources%2FManage_NOSQL_Shards%2FScreenshot%202024-03-03%20at%2012.54.45%E2%80%AFPM.png)

- When a new shard is created by Orchestrator, data fromm old machines has to be copied from old to new machines.
- This happens in 2 phases - Hot and Cold

### STAGING PHASE (Takes long time) ~ 10GB 
- ![Screenshot 2024-03-03 at 1.01.24 PM.png](resources%2FManage_NOSQL_Shards%2FScreenshot%202024-03-03%20at%201.01.24%E2%80%AFPM.png)
- Orchestrator starts copying data to the new shard, but LB and config manager doesnt know about the new shard, hence they will still be routing the new requests to the old shards
- The time of copying new data might be added/removed to the old shards, hence creating a delta 

### REAL PHASE ~ 10 MB( takes only few seconds as delta would be less)
![Screenshot 2024-03-03 at 1.10.11 PM.png](resources%2FManage_NOSQL_Shards%2FScreenshot%202024-03-03%20at%201.10.11%E2%80%AFPM.png)

![Screenshot 2024-03-03 at 1.12.12 PM.png](resources%2FManage_NOSQL_Shards%2FScreenshot%202024-03-03%20at%201.12.12%E2%80%AFPM.png)

###### Doing this in 2 phases reduces the downtime even in case strongly consistent 
- Consistency is an issue only during real phase
- So if we had announced to LB during STAGING phase, depending on consistency needs, data would have to made unavailable for long time(time taken to copy 10GB instead of 10MB in 2 phase)

REPLICATION 
1. Master Slave
2. MULTI MASTER
![Screenshot 2024-03-03 at 1.19.50 PM.png](resources%2FManage_NOSQL_Shards%2FScreenshot%202024-03-03%20at%201.19.50%E2%80%AFPM.png)

## MULTI MASTER (Cassandra masters Multi master)
![Screenshot 2024-03-03 at 1.30.03 PM.png](resources%2FManage_NOSQL_Shards%2FScreenshot%202024-03-03%20at%201.30.03%E2%80%AFPM.png)

![Screenshot 2024-03-03 at 1.37.42 PM.png](resources%2FManage_NOSQL_Shards%2FScreenshot%202024-03-03%20at%201.37.42%E2%80%AFPM.png)

![Screenshot 2024-03-03 at 1.39.43 PM.png](resources%2FManage_NOSQL_Shards%2FScreenshot%202024-03-03%20at%201.39.43%E2%80%AFPM.png)

###### Why read from (X+1)/2 or (N+1)/2 ?

![Screenshot 2024-03-03 at 1.42.43 PM.png](resources%2FManage_NOSQL_Shards%2FScreenshot%202024-03-03%20at%201.42.43%E2%80%AFPM.png)

### In Cassandra
![Screenshot 2024-03-03 at 1.43.44 PM.png](resources%2FManage_NOSQL_Shards%2FScreenshot%202024-03-03%20at%201.43.44%E2%80%AFPM.png)
- 
###### TUNABLE CONSISTENCY
![Screenshot 2024-03-03 at 1.46.34 PM.png](resources%2FManage_NOSQL_Shards%2FScreenshot%202024-03-03%20at%201.46.34%E2%80%AFPM.png)

2 phase commit:
https://www.youtube.com/watch?v=-_rdWB9hN1c
https://www.youtube.com/watch?v=eltn4x788UM

Class Summary: https://docs.google.com/document/d/1PfCmn-rhCJEyWyWf45VtIUFRHyoqiwFyp5o2TCcrZnE/edit

Live lecture notes: https://notability.com/n/02gARvLBh1muntEAvw_8NX

##### What happens if Master dies during write in Master Slave?
1. Before writing it to Master - No response is replied, hence request times out..No Data Loss
2. After writing to Master - Master dies. Data Loss
3. Write happened on Slaves as well before responding. No data loss
![Screenshot 2024-03-03 at 4.02.28 PM.png](resources%2FManage_NOSQL_Shards%2FScreenshot%202024-03-03%20at%204.02.28%E2%80%AFPM.png)

##### Scaling
![Screenshot 2024-03-03 at 4.07.40 PM.png](resources%2FManage_NOSQL_Shards%2FScreenshot%202024-03-03%20at%204.07.40%E2%80%AFPM.png)

##### SHARDING
- Vertical Partitioning - Normalization
- Horizontal across servers - Sharding

###### WHY?
1. Data is too large
2. Load is too large (IRCTC write during Tatkal)
- Improves write throughput and allows storing large amount of data

##### REPLICATION
- Copy data
###### WHY?
1. Data is CRITICAL - prevent loss of data
2. Load is too large (read - Master slave, read,write - Multi Master)
- Prevents data loss and Improves read throughput

##### CACHING
- Only improves read throughput


















