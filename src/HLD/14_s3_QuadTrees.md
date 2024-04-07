# Distributed File System

- FAT,NTFS,FAT32 - File system, not distributed
# HDFS - Hadoop Distributed File System
- Distributed

# AWS S3
- Distributed, managed by AWS

###### THESE ARE NOT CDN - CDN IS LIKE A CACHE

Ex: SCALER
- Lots of videos, classes, images
- Each video - 3GB
- ~10,000 videos
- 3GB * 10000 = 30 TB
- Will definitely not stored in DB
- will be pushed to HDFS or S3
- CDN for early access for users

2. 
- 5GB per file generated
- 100,000 files in total
- - ~= 100TB
![Screenshot 2024-03-09 at 7.22.01 PM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-09%20at%207.22.01%E2%80%AFPM.png)

#### Requirements for distributed file system - what HDFS and S# would have implemented
![Screenshot 2024-03-09 at 7.24.24 PM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-09%20at%207.24.24%E2%80%AFPM.png)
- Even Downloading should not be Atomic as upload

![Screenshot 2024-03-09 at 7.26.03 PM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-09%20at%207.26.03%E2%80%AFPM.png)

![Screenshot 2024-03-09 at 7.26.45 PM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-09%20at%207.26.45%E2%80%AFPM.png)

![Screenshot 2024-03-09 at 7.27.38 PM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-09%20at%207.27.38%E2%80%AFPM.png)

Torrent
- Parallel downloading if you store entire file as 1 unit

- Files are going to be chunkified
  -  ![Screenshot 2024-03-09 at 8.50.38 PM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-09%20at%208.50.38%E2%80%AFPM.png)
  -  
![Screenshot 2024-03-09 at 8.53.53 PM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-09%20at%208.53.53%E2%80%AFPM.png)
- Client reads with 10 threads, each thread reading in parallel. Each thread requests next chunk after finishing
- Client is not watching at the time of downloading, he/she will download and then watch
- In case of network, downloading can resume from the prev id only and not start from first.

### CHUNK SIZE
- ![Screenshot 2024-03-09 at 9.02.55 PM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-09%20at%209.02.55%E2%80%AFPM.png)
![Screenshot 2024-03-09 at 9.03.14 PM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-09%20at%209.03.14%E2%80%AFPM.png)

![Screenshot 2024-03-09 at 9.03.41 PM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-09%20at%209.03.41%E2%80%AFPM.png)

![Screenshot 2024-03-09 at 9.03.59 PM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-09%20at%209.03.59%E2%80%AFPM.png)

Chunk size is defaulted in HDFS
- ![Screenshot 2024-03-09 at 9.05.27 PM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-09%20at%209.05.27%E2%80%AFPM.png)

## HDFS - DEEP DIVE
![Screenshot 2024-03-09 at 9.08.25 PM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-09%20at%209.08.25%E2%80%AFPM.png)

# ACTIVE PASSIVE
######  for SPOF
![Screenshot 2024-03-09 at 9.09.25 PM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-09%20at%209.09.25%E2%80%AFPM.png)

###### NAME NODES CAN EVEN BE IN MASTER SLAVE
- In Master Slave all nodes are active, slaves help in reads
- Active passive, only the master is active  

## METADATA - Inside Name node 
![Screenshot 2024-03-09 at 9.14.15 PM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-09%20at%209.14.15%E2%80%AFPM.png)

###### METADATA SHOULD/CAN BE ALWAYS STORED IN SQL DBs

![Screenshot 2024-03-09 at 10.13.11 PM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-09%20at%2010.13.11%E2%80%AFPM.png)
- Machines in same rack have same network cable, switch.

- HDFS makes use of Rack Aware Algo- Data is stored in servers which are on different racks

## WRITE FLOW | UPLOAD FLOW
- ![Screenshot 2024-03-09 at 10.20.49 PM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-09%20at%2010.20.49%E2%80%AFPM.png)
- Flow
  - Client sends upload request to App server
  - App server request HDFS server with the help of HDFS Client running on the app server
  - App server requests chunk_size from HDFS server
  - With client sending continuous data , once the size of file reaches chunk size,
  - App server will send chunk_ready request to HDFS server asking for the location to store the chunk
  - Metadata entry will be updated at every time there is an update
  - More on the image
- Client will continuously stream of bytes to the app server
###### Network fails ? retry only 1 chunk og 128MB only

## READ FLOW | DOWNLOAD
![Screenshot 2024-03-09 at 10.29.05 PM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-09%20at%2010.29.05%E2%80%AFPM.png)
![Screenshot 2024-03-10 at 1.49.24 AM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-10%20at%201.49.24%E2%80%AFAM.png)


## QuadTrees


![Screenshot 2024-03-10 at 2.23.13 AM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-10%20at%202.23.13%E2%80%AFAM.png)

###### Uber is a difficult problem as the drivers location is changing dynamically

#### Problem
![Screenshot 2024-03-10 at 2.26.11 AM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-10%20at%202.26.11%E2%80%AFAM.png)

- Step1: Collect data of places of interest all over the world
- ![Screenshot 2024-03-10 at 2.30.08 AM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-10%20at%202.30.08%E2%80%AFAM.png)
- 100 M entries of min
- 

### Iteration1- SQL and NOSQL dont work
![Screenshot 2024-03-10 at 2.38.50 AM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-10%20at%202.38.50%E2%80%AFAM.png)

### Iteration2
![Screenshot 2024-03-10 at 2.39.43 AM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-10%20at%202.39.43%E2%80%AFAM.png)

Divide world into grid 
![Screenshot 2024-03-10 at 2.00.05 PM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-10%20at%202.00.05%E2%80%AFPM.png)

- Dividing into equal sized grid would lead to unequal density of population.

QUAD TREES
- Start with assuming the whole world is inside a single cell
![Screenshot 2024-03-10 at 2.02.10 PM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-10%20at%202.02.10%E2%80%AFPM.png)
- Keep asking yourself recursively If a grid has fixed no.(100) of places of interest, divide that cell again into 4 equal parts
![Screenshot 2024-03-10 at 2.04.08 PM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-10%20at%202.04.08%E2%80%AFPM.png)
- 100 is a good number to find 100 barber shops near me
- Ultimately all the places of interest will be stored in the leaf nodes of the tree like structure formed after dividing the cells
![Screenshot 2024-03-10 at 2.10.25 PM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-10%20at%202.10.25%E2%80%AFPM.png)

![Screenshot 2024-03-10 at 2.09.57 PM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-10%20at%202.09.57%E2%80%AFPM.png)
###### Now this below table will be a leaf node id as well saying the place stored is in which leaf id
![Screenshot 2024-03-10 at 2.08.10 PM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-10%20at%202.08.10%E2%80%AFPM.png)

- Updated tables
![Screenshot 2024-03-10 at 2.13.20 PM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-10%20at%202.13.20%E2%80%AFPM.png)

### Finding GridId(leaf id) for a point
- ![Screenshot 2024-03-10 at 2.18.11 PM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-10%20at%202.18.11%E2%80%AFPM.png)
- Knowing all these points of the parent cell, we now know which child node does our point lie in
- Keep going recursively to end up on a leaf node
- That's the leaf node
- If the point lies on a line, decide in the first where to go and do it everytime

Why only 4 parts?
- If only 2 parts, only 1 dimension will get reduced, so min is 4
- 4 is not a compulsion, some do it 8 and so on
- UBER maps it as a Hexagon, according to them hexagon maps the world well
- Rectangle is a base shape

BUILD GOOGLE MAPS
- ![Screenshot 2024-03-10 at 2.29.34 PM.png](resources%2FS3_QuadTrees%2FScreenshot%202024-03-10%20at%202.29.34%E2%80%AFPM.png)
![Screenshot 2024-03-10 at 2.30.27 PM.png](resources%2FUberCaseStudy%2FScreenshot%202024-03-10%20at%202.30.27%E2%80%AFPM.png)
![Screenshot 2024-03-10 at 2.31.22 PM.png](resources%2FUberCaseStudy%2FScreenshot%202024-03-10%20at%202.31.22%E2%80%AFPM.png)

![Screenshot 2024-03-10 at 2.35.08 PM.png](resources%2FUberCaseStudy%2FScreenshot%202024-03-10%20at%202.35.08%E2%80%AFPM.png)
![Screenshot 2024-03-10 at 2.35.41 PM.png](resources%2FUberCaseStudy%2FScreenshot%202024-03-10%20at%202.35.41%E2%80%AFPM.png)

![Screenshot 2024-03-10 at 2.39.56 PM.png](resources%2FUberCaseStudy%2FScreenshot%202024-03-10%20at%202.39.56%E2%80%AFPM.png)
Finding neighbours
- Use hashmap, go to parent and ask for its other children
- Use mathematical approach, from the point asked in the query , do some delta addition and subtraction from the original point to end on other (neighbours) and find the cell id using the prev technique and fetch places using that cellId or leaf ID
- ![Screenshot 2024-03-10 at 2.42.36 PM.png](resources%2FUberCaseStudy%2FScreenshot%202024-03-10%20at%202.42.36%E2%80%AFPM.png)
![Screenshot 2024-03-10 at 2.46.05 PM.png](resources%2FUberCaseStudy%2FScreenshot%202024-03-10%20at%202.46.05%E2%80%AFPM.png)

![Screenshot 2024-03-10 at 2.47.05 PM.png](resources%2FUberCaseStudy%2FScreenshot%202024-03-10%20at%202.47.05%E2%80%AFPM.png)
![Screenshot 2024-03-10 at 2.48.12 PM.png](resources%2FUberCaseStudy%2FScreenshot%202024-03-10%20at%202.48.12%E2%80%AFPM.png)

![Screenshot 2024-03-10 at 2.53.11 PM.png](resources%2FUberCaseStudy%2FScreenshot%202024-03-10%20at%202.53.11%E2%80%AFPM.png)

https://www.scaler.com/academy/mentee-dashboard/class/147619/session?joinSession=1
AMAZING CALCULATION FOR QUAD TREE - 1.50

![Screenshot 2024-03-10 at 3.03.36 PM.png](resources%2FUberCaseStudy%2FScreenshot%202024-03-10%20at%203.03.36%E2%80%AFPM.png)

- ALL these 100M places will be stored in leaf nodes
- Assuming if 20 places are stored in the each node on an avg..
- ![Screenshot 2024-03-10 at 3.04.46 PM.png](resources%2FUberCaseStudy%2FScreenshot%202024-03-10%20at%203.04.46%E2%80%AFPM.png)
- 5M leaf nodes
- Penultimates would have 5M/4, 5M/16, 5M/64 and so on...
- This is a infinite GP problem 
![Screenshot 2024-03-10 at 3.06.16 PM.png](resources%2FUberCaseStudy%2FScreenshot%202024-03-10%20at%203.06.16%E2%80%AFPM.png)


![Screenshot 2024-03-10 at 3.07.18 PM.png](resources%2FUberCaseStudy%2FScreenshot%202024-03-10%20at%203.07.18%E2%80%AFPM.png)
- =6.5M nodes in total


## Storage Space
- ![Screenshot 2024-03-10 at 3.08.50 PM.png](resources%2FUberCaseStudy%2FScreenshot%202024-03-10%20at%203.08.50%E2%80%AFPM.png)
- Quad tree nodes :
  - 4(x,y) nodes = 8*8bytes = 64 Bytes
  - CEll Id: 8Bytes
  - Parent Id, 4 child ids : 5 ids = 8 * 5 = 40Bytes
  - =112Bytes ~= 200B
  - 6.5M nodes * 200B/per node = 1.3 GB (hyaan?)

- Place Storage
- ![Screenshot 2024-03-10 at 3.17.43 PM.png](resources%2FUberCaseStudy%2FScreenshot%202024-03-10%20at%203.17.43%E2%80%AFPM.png)

![Screenshot 2024-03-10 at 3.18.39 PM.png](resources%2FUberCaseStudy%2FScreenshot%202024-03-10%20at%203.18.39%E2%80%AFPM.png)

![Screenshot 2024-03-10 at 3.19.13 PM.png](resources%2FUberCaseStudy%2FScreenshot%202024-03-10%20at%203.19.13%E2%80%AFPM.png)




## QUAD TREES
- Save all places of interest in a table
  - place id
  - name
  - type
  - lat
  - long
  - desc
  - **LEAF ID**

Save Quad tree info
- leafId
- left top lat,long
- left bottom lat,long
- right top lat,long
- right bottom lat,long

LEAF ID??  All the places of interest will be stored in the Quad tree
- Quad tree is formed by dividing the whole world recursively into 4 parts until the number of places of interest is less than 100
- This forms as a tree like structure, root being the whole world and children as 4 parts after division
- Ultimately the places of interest will be stored in the leaf nodes, that's the lead id

Given a point (lat, long) , how to find the leaf id?
- Starting from the root node, do (x1+x2)/2,(y1+y2)/2 for the current node mid-point
- After getting the mid-point now we can decide which quadrant to move 
- Ex: if x of the point is less than mid points x, then its left side. on checking y the same way you go up or down
- If the node has more children go deeper until you find the leaf node
 
#### After finding the leaf node id, we can find all the places of interest from that node , rank and respond back

What if we needed more places of interest?
- Check the surrounding leaf nodes as well. How? go to the current leaf left top/bottom and subtract some small number to land on left neighbour and do the same for rest 


#### What if we want to add new place?
- Get the leaf id of the new place using its lat long
- Check if after adding the places are more than 100 in the leaf
- No?  Add the place in the places table with the leaf id.. no more changes required in the quad tree.
- YES? Divide the quad tree into 4 children and for 101 places decide the lead node ids and update the places table

#### What if we want to Delete a place?
- Delete the place from the table
- Check the parent of the place ka leaf node, if after deleting sum of places in its child nodes is less than 100, merge them and update the places leaf cell id

#### Place moves from one place to another
- Do delete and add

#### Storage requirements above


### UBER

- The grids won't be divided based on no. of drivers in it as we discussed previously
- The grid size will be decided based on the historical data, analysis and time of the day 
- Grid resizing may happen every 4hours
- For example, morning people tend to go the office, school and other places from their home. so if the drivers are concentrated near societies - its ok and similarly during evening people go from office to home
- Initialize the QUAD TREE with the current location of the drivers
- Driver location keeps changing every second - DON'T UPDATE THE QUAD TREES
- Maintain a DB like CASSANDRA - good for frequent writes and timestamp based entries to keep track of all locations received from Driver client
- Maintain a cache like REDIS to maintain only the Current location
- Everytime we get a new location of the driver , enter in the cassandra db --> Check with the current location in the REDIS with some threshold, only if significant moment is there then update the QUAD TREE
- Update the QUAD tree means only change the driver's leaf id
- WHEN EVER A DRIVER REQUEST COMES CHECK THE DRIVERS IN THE CURRENT LEAF ID AND ITS SURROUNDINGS






































