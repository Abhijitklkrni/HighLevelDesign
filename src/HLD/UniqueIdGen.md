![Screenshot 2024-03-10 at 4.41.11 PM.png](resources%2FUniqueIdGen%2FScreenshot%202024-03-10%20at%204.41.11%E2%80%AFPM.png)

- Incremental gives us more power to decide which id is greater than which id

### Option 1 and Option 2(multiple machines)
![Screenshot 2024-03-10 at 4.47.09 PM.png](resources%2FUniqueIdGen%2FScreenshot%202024-03-10%20at%204.47.09%E2%80%AFPM.png)
- Cons:
    - SPOF
    - Cant handle multiple requests, will be slow rather
    - ![Screenshot 2024-03-10 at 4.50.31 PM.png](resources%2FUniqueIdGen%2FScreenshot%202024-03-10%20at%204.50.31%E2%80%AFPM.png)
    - Duplicate Ids, if all machines keep co ordinating still SPOF and high latency
    - ![Screenshot 2024-03-10 at 4.51.54 PM.png](resources%2FUniqueIdGen%2FScreenshot%202024-03-10%20at%204.51.54%E2%80%AFPM.png)
    - Start with 0,1,2 and next request will be +3 of prev number,
    - ![Screenshot 2024-03-10 at 4.52.44 PM.png](resources%2FUniqueIdGen%2FScreenshot%202024-03-10%20at%204.52.44%E2%80%AFPM.png)
    - Network problem and one machine is skipped, and once the machine is back, it will give an smaller id 

### Option 3
- ![Screenshot 2024-03-10 at 4.58.56 PM.png](resources%2FUniqueIdGen%2FScreenshot%202024-03-10%20at%204.58.56%E2%80%AFPM.png)
- Unique, but not sequential

### Option 4 UUID - 128Bit
- Unique but random and not incremental

### Option 6 Timestamp + serverId
- ![Screenshot 2024-03-10 at 5.01.38 PM.png](resources%2FUniqueIdGen%2FScreenshot%202024-03-10%20at%205.01.38%E2%80%AFPM.png)
- 2 request at same milli second, will still be different in server id
- Cons: 2 request to the same server at the same milli second.


# TWITTER SNOWFLAKES ALGORITHM
- Snowflake - snow will falls have sizes shapes and sizes. i.e why the name
- 64Bit 
- ![Screenshot 2024-03-10 at 5.07.36 PM.png](resources%2FUniqueIdGen%2FScreenshot%202024-03-10%20at%205.07.36%E2%80%AFPM.png)

1. Sign Bit(1 bit)
   - Always set to zero
   - Can be used for soft delete (0 - active, 1 - Deleted)
   - Can be used as per use case

2. Timestamp Bits (41 Bits)
   - Sensitive to seconds
   - EPOCH time (how many seconds have passed by fixed date)
   - 1Jan 1970 - fixed date for EPOCH TIME
   - 41 bits = 2^41 seconds ~ 69000 years
   - If sensitive is in milli seconds = 2^41milli seconds ~ 69 years
   - 70 years is GREAT considering how fast systems change
   - Twitter decided  cut off date in 4th Nov, 2010

3. Datacenter Bits (5 bits) = 32 Data centres
   - ![Screenshot 2024-03-10 at 5.16.12 PM.png](resources%2FUniqueIdGen%2FScreenshot%202024-03-10%20at%205.16.12%E2%80%AFPM.png)

4. Machine Bits(5 bits) = 32 Machines/ Data center
    - If We dont have multiple datacenter than those 5 bits can be used for machines - 10bits = 1024 machines
   
5. Sequence Number ( 12bits)
    - Sequence number are reserved for generating auto incremented sequence numbers
    - 2^12 = 4096 numbers
    - Basically, 4096 requests can be handled by each machine at each millisecond






























































































