## SQL vs NO SQL

#### SQL
- Still the best
- by design , single machine, not distributed
- Strong point - wrt CAP , rate of partitioning within the server is not possible
- That is why it finds its place where both CA are imp. Banking, Trading is some where part of their system
- why single machine? when developed we didnt have much data
- Tables - fixed schema. schema can be changed but is costly
- bcoz of Single machine - it assumes Normalization - no redundant data
- More Normalized - More joins
- ACID guarantee
- ATOMIC : 0% or 100%
- CONSISTENCY: DB state remains consistent before and after the transaction
###### Foreign key constraint remains even after the transaction( Student_id, course_id) there should be ids in the table if they are not present in student and course table, after transaction the constraint should still remain
- ISOLATION: Transaction should be isolated
- DURABLE: Committed data will not be lost

###### SQL guarantees ALL TRANSACTIONS ARE ACID

## PROS
![Screenshot 2024-03-03 at 12.54.07 AM.png](resources%2FSQl_NoSQL%2FScreenshot%202024-03-03%20at%2012.54.07%E2%80%AFAM.png)
![Screenshot 2024-03-03 at 12.56.58 AM.png](resources%2FSQl_NoSQL%2FScreenshot%202024-03-03%20at%2012.56.58%E2%80%AFAM.png)
## CONS
- Adding one extra column would change the size of each row , all data should now be rewritten on the disk, hence schema change operations are costly
- Difficult if no fixed schema
- Product might have different attributes
- ![Screenshot 2024-03-03 at 1.01.45 AM.png](resources%2FSQl_NoSQL%2FScreenshot%202024-03-03%20at%201.01.45%E2%80%AFAM.png)
![Screenshot 2024-03-03 at 1.02.53 AM.png](resources%2FSQl_NoSQL%2FScreenshot%202024-03-03%20at%201.02.53%E2%80%AFAM.png)

#### SQL cannot be used for large data ? hahaha
- ![Screenshot 2024-03-03 at 1.04.15 AM.png](resources%2FSQl_NoSQL%2FScreenshot%202024-03-03%20at%201.04.15%E2%80%AFAM.png)
- SQL doesnt come with inbuilt sharding support,add wrapper with CH logic,LB, reverse proxy (add layer with 3rd party)
- ![Screenshot 2024-03-03 at 1.06.44 AM.png](resources%2FSQl_NoSQL%2FScreenshot%202024-03-03%20at%201.06.44%E2%80%AFAM.png)
- ACID Guarantee only on single machine
- ![Screenshot 2024-03-03 at 1.08.53 AM.png](resources%2FSQl_NoSQL%2FScreenshot%202024-03-03%20at%201.08.53%E2%80%AFAM.png)
- ![Screenshot 2024-03-03 at 1.09.32 AM.png](resources%2FSQl_NoSQL%2FScreenshot%202024-03-03%20at%201.09.32%E2%80%AFAM.png)
- EX: 

###### WHENEVER WE HAVE A SHARDED SQL, understand that each shard is acting as independent SQL Machine
- ![Screenshot 2024-03-03 at 1.13.50 AM.png](resources%2FSQl_NoSQL%2FScreenshot%202024-03-03%20at%201.13.50%E2%80%AFAM.png)
- JOIN ACROSS SHARDS
- ![Screenshot 2024-03-03 at 1.14.40 AM.png](resources%2FSQl_NoSQL%2FScreenshot%202024-03-03%20at%201.14.40%E2%80%AFAM.png)

##### IF JOINS ARE GETTING SUPER COSTLY, WE WILL HAVE TO COMPROMISE NORMALIZATION

##### IN REALITY, CONCEPTS ARE SAME FOR SQL AND NO SQL, it only boils down to db design decisions
- Single machine - Normalisation can be done
- Multiple - Denorm
- Fixed schema - difficult to update, No fixed schema read will be costly
- Concepts remain the same

- ![Screenshot 2024-03-03 at 1.22.45 AM.png](resources%2FSQl_NoSQL%2FScreenshot%202024-03-03%20at%201.22.45%E2%80%AFAM.png)

#### Why people store json column in SQL?
- DB companies wants to sell their product

# NO SQL
- No single idea - if it's not SQL : NO SQL
- Semi or no structured data
- By design - Cluster : Horizontally scaled machines

#### KEY-VALUE PAIR NO SQL DB
- REDIS, MemCached
- just key value
- ![Screenshot 2024-03-03 at 1.31.15 AM.png](resources%2FSQl_NoSQL%2FScreenshot%202024-03-03%20at%201.31.15%E2%80%AFAM.png)

- Store Aadhaar number of Billion population
- key as Aadhaar number and value as all info as csv string
- CH and shard key will be Aadhaar Number

#### DOCUMENT NO SQL DB
- MONGO DB, COUCH DB, ELASTIC DB
- Sharding key is document id
- ![Screenshot 2024-03-03 at 1.37.59 AM.png](resources%2FSQl_NoSQL%2FScreenshot%202024-03-03%20at%201.37.59%E2%80%AFAM.png)
- ![Screenshot 2024-03-03 at 1.38.43 AM.png](resources%2FSQl_NoSQL%2FScreenshot%202024-03-03%20at%201.38.43%E2%80%AFAM.png)
- ![Screenshot 2024-03-03 at 1.39.25 AM.png](resources%2FSQl_NoSQL%2FScreenshot%202024-03-03%20at%201.39.25%E2%80%AFAM.png)
- Search docs with attributes inside the value
- search docs where price < 5k, some products might not even have price attribute

## HOW DOES IT WORK?
- ![Screenshot 2024-03-03 at 1.43.12 AM.png](resources%2FSQl_NoSQL%2FScreenshot%202024-03-03%20at%201.43.12%E2%80%AFAM.png)
- create Indexes, to know which all machines have the search attribute.
- MONGO gives this feature of adding index , not an extra layer from our side
- MONGO calls it WIRED TIGER

##### We create indexes or Mongo creates it own based on frequent query field?
- BOTH
![Screenshot 2024-03-03 at 2.01.56 AM.png](resources%2FSQl_NoSQL%2FScreenshot%202024-03-03%20at%202.01.56%E2%80%AFAM.png)
- Tags - help mongo store relevant data/ data queried together on the same machine

#### Wide column DB/ Columnar DB NO SQL DB
- Cassandra, HBase, and the baap BigTable
- Logging
- no sql db closest to SQL db
- ![Screenshot 2024-03-03 at 2.08.23 AM.png](resources%2FSQl_NoSQL%2FScreenshot%202024-03-03%20at%202.08.23%E2%80%AFAM.png)
![Screenshot 2024-03-03 at 2.09.58 AM.png](resources%2FSQl_NoSQL%2FScreenshot%202024-03-03%20at%202.09.58%E2%80%AFAM.png)
- 
