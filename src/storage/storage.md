**STORAGE**

- DB can cache query results as well.

- Replication
  - Master - Slave
  - Master - Master
  
- Sharding
  - Distribute data bw different servers, they are interact together to give the final outcome
  - Sharding by (idea: maximise Intra shard)
    - Range of keys
    - CH
    - Geolocation based

- Quorum
  - When data replication, when do we send back response to the user.
    - Sync :After the data is copied to all the slaves
    - Async: 
    - Quorum : some x number of machines (n/2)+1

- NO SQL vs SQL
  - SQL - Structured - RDBMS 
    - Relation bw entities
    - A RDBMS will store closely related data into tables
    - Distribute/Divide the data into smaller and smaller tables
    - Doing the above steps helps to normalization and decouple even the slightly unrelated data to a separate table(normalization)
    - Normalization gives you the property of data not being duplicated 
    - ACID
  - SQL - cons
    - Difficult if not structured

# NO SQL
## KEY VALUE ##
- Redis


## Wide column
- Ad serving company, each company(publisher) would have different info to be stored

Website is a column family and each company can have its own set of attributes
![Screenshot 2024-08-19 at 12.05.39 AM.png](images%2FScreenshot%202024-08-19%20at%2012.05.39%E2%80%AFAM.png)

Company 2 has only 10 attributes
![Screenshot 2024-08-19 at 12.06.31 AM.png](images%2FScreenshot%202024-08-19%20at%2012.06.31%E2%80%AFAM.png)

![Screenshot 2024-08-19 at 12.07.11 AM.png](images%2FScreenshot%202024-08-19%20at%2012.07.11%E2%80%AFAM.png)

super column family - Publishers
column family - Website, Physical, Payment
key - 
Columns - attributes

*
Wide Family vs Document DB
- For the above kind of requirement if stored as document DB, The publisher ID 101 can be present in shard 1 and id 107 can be present in shard 2 and so on,
  If I want to get all the website info about all the publishers, that will result in an intra shard query.
- In wide column store, all the column family info of the all the ids will be stored and one box, one after the other
*

Graph DB












































































































