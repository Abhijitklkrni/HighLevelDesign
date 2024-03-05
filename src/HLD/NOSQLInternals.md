## NO SQL Internals
## LSM Trees - Log Structured Merge Trees

###### B+ Trees  - SQL
###### LSM Trees - NOSQL

### REDIS - KEY VALUE STORE
1. WAL (Write Ahead Log) - AOF Append only file
   - Append only at the end 

2. Snapshots of Data at regular Intervals
   - Store in HDD

#### Iteration 1
- ![Screenshot 2024-03-05 at 1.05.53 AM.png](resources%2FNOSQLInternals%2FScreenshot%202024-03-05%20at%201.05.53%E2%80%AFAM.png)
- Update - O(N)search + O(N) push ahead


#### Iteration 2
- Write what ever we get we write it at end of file
- ![Screenshot 2024-03-05 at 9.38.51 PM.png](resources%2FNOSQLInternals%2FScreenshot%202024-03-05%20at%209.38.51%E2%80%AFPM.png)
- Write and Update : O(1)
- Read O(N) : latest key value reading backwards - even bigger than O(N) + avg no. of duplicates
- Delete : Insert delete record at End, when read backwards it finds it as deleted
- ![Screenshot 2024-03-05 at 9.43.35 PM.png](resources%2FNOSQLInternals%2FScreenshot%202024-03-05%20at%209.43.35%E2%80%AFPM.png)

#### Iteration 3
- ![Screenshot 2024-03-05 at 9.46.32 PM.png](resources%2FNOSQLInternals%2FScreenshot%202024-03-05%20at%209.46.32%E2%80%AFPM.png)
- If RAM can read/write only 100MB at once why not write in different files each of 100MB only
- If writes are going only on latest file, why not keep a copy of the latest file on RAM only to increase write speed and also update latest file into secondary memory 
- Still lower latency than getting file from sec to prim memory and then writing
- Primary Memory(RAM) stores as Map
- File stored in RAM is called MemTable
![Screenshot 2024-03-05 at 9.54.47 PM.png](resources%2FNOSQLInternals%2FScreenshot%202024-03-05%20at%209.54.47%E2%80%AFPM.png)
- Secondary memory is Write ahead log. Secondary memory is just a sequence of memory bits

#### Iteration 4
- T(4) - 200:Neelam Singh
- 200 was Kumarravel in RAM before updating it to Neelam Singh
- As RAM is key value we directly change the value of 200 , but secondary will be WAL
![Screenshot 2024-03-06 at 12.28.59 AM.png](resources%2FNOSQLInternals%2FScreenshot%202024-03-06%20at%2012.28.59%E2%80%AFAM.png)
- Any changes done on 3rd file doesn't affect 1 and 2
- If in case of power cut, RAM data is lost. RAM file can be recreated using Latest file
- Once RAM file has reached 100MB, Secondary memory file would be greater than 100MB because of the duplicates as its WAL.
- Sec memory file can be safely deleted once the file from RAM is dumped to Sec memory
- Once dumped there will be no duplicate entries within the file but there can be duplicates from other files
![Screenshot 2024-03-05 at 10.07.11 PM.png](resources%2FNOSQLInternals%2FScreenshot%202024-03-05%20at%2010.07.11%E2%80%AFPM.png)

![Screenshot 2024-03-05 at 10.10.36 PM.png](resources%2FNOSQLInternals%2FScreenshot%202024-03-05%20at%2010.10.36%E2%80%AFPM.png)
##### File on RAM : MemTable
##### Files on HDD : SSTable (Sorted String Table)

###### ![Screenshot 2024-03-05 at 10.12.38 PM.png](resources%2FNOSQLInternals%2FScreenshot%202024-03-05%20at%2010.12.38%E2%80%AFPM.png)

## Read
- Read from MemTable, if not table go backwards on SSTables
- That is why for certain keys , read are fast and for some read are bit more slow
- ![Screenshot 2024-03-05 at 11.09.07 PM.png](resources%2FNOSQLInternals%2FScreenshot%202024-03-05%20at%2011.09.07%E2%80%AFPM.png)

###### COMPACTION
- Get Consecutive SSTables from SSD to RAM, do a merge comparing with MemTable Ex. If some key is present in MemTable you can ignore from both SSTables. else take the latest from both the SSTable
- After doing this
  - No Duplicates between those 2 SSTables
  - Space used is less as no dupes
  - 2 SSTables can be merged into 1 SSTable if size is less than 100Mb after removing dupes
  - ![Screenshot 2024-03-05 at 11.25.54 PM.png](resources%2FNOSQLInternals%2FScreenshot%202024-03-05%20at%2011.25.54%E2%80%AFPM.png)

#### Iteration 5
![Screenshot 2024-03-05 at 11.29.04 PM.png](resources%2FNOSQLInternals%2FScreenshot%202024-03-05%20at%2011.29.04%E2%80%AFPM.png)

![Screenshot 2024-03-05 at 11.29.25 PM.png](resources%2FNOSQLInternals%2FScreenshot%202024-03-05%20at%2011.29.25%E2%80%AFPM.png)


##### Iteration 6
##### WHY CANT WE USE BINARY SEARCH TO FIND WITHIN SSTABLE AS THEY ARE SORTED ?
- ![Screenshot 2024-03-05 at 11.34.08 PM.png](resources%2FNOSQLInternals%2FScreenshot%202024-03-05%20at%2011.34.08%E2%80%AFPM.png)
- To read the SSTable I will have to bring it to RAM , deserialize it convert into Map and read it
- To deserialize and put it in Map, it takes O(N)
- If we want to apply Binary Search on serialized SSTable, we Cant Because the elements are not of fixed size. Even If I find the middle index, I cant guarantee it is pointing on middle element
- ![Screenshot 2024-03-05 at 11.38.01 PM.png](resources%2FNOSQLInternals%2FScreenshot%202024-03-05%20at%2011.38.01%E2%80%AFPM.png)


##### AMAZING ENGINEERING
- Each SSTable will partitions of fixed size say 64KB (New data architecture has 16MB limit)
- If 100MB SSTable ~ 1600 blocks
- One Condition is we cannot write 1 key value is different blocks
- One block can have multiple key value pairs
- Meaning after writing 1 key Value in block 1, if there is enough space left to write the 2nd key value you write it in the same block, else next block
- Each key value size cannot exceed the Block size

- With this while writing the MemTable to SSD, we maintain a array for each SSTable in RAM, saying which is the starting key of the block
- ![Screenshot 2024-03-06 at 12.16.02 AM.png](resources%2FNOSQLInternals%2FScreenshot%202024-03-06%20at%2012.16.02%E2%80%AFAM.png)
- Now Binary Search could be easily applied on the array and now instead of getting the whole SSTable of 100MB, we now only get 64KB(Block size) of data into RAM and read the data. This way reading speed increased by 1600 in this example
![Screenshot 2024-03-06 at 12.22.37 AM.png](resources%2FNOSQLInternals%2FScreenshot%202024-03-06%20at%2012.22.37%E2%80%AFAM.png)
![Screenshot 2024-03-06 at 12.22.58 AM.png](resources%2FNOSQLInternals%2FScreenshot%202024-03-06%20at%2012.22.58%E2%80%AFAM.png)

![Screenshot 2024-03-06 at 12.24.00 AM.png](resources%2FNOSQLInternals%2FScreenshot%202024-03-06%20at%2012.24.00%E2%80%AFAM.png)

![Screenshot 2024-03-06 at 12.25.00 AM.png](resources%2FNOSQLInternals%2FScreenshot%202024-03-06%20at%2012.25.00%E2%80%AFAM.png)























