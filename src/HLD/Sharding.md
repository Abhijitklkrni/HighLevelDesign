
<b><h1>SHARDING

## WE CAN HAVE DIFFERENT SHARD KEY FOR DIFFERENT TABLES

### Single database machine
- Not practical for storing TBs of data
- Mutually exclusive and collectively exhaustive
- 1 data id in only one machine - Mutually exclusive
- No data point is lost /left out of the cluster - collectively exhaustive
- Sharding - scaling horizontally with Mutually exclusive and collectively exhaustive
- each server is called shard

# FACEBOOK
1. User Table (name,email,user_id)
2. User_Friends (user_id, friend_user_id) (bidirectional meaning 100,200 and 200,100 both will be present)
3. POSTS (post_id, text, user_id, created_TS)

Sharding key : USER_ID

### Sharding a SQL also called partitioning
- same schema in all the machines
- if user_id the shard key , on the basis of CH value, user data will be stored in that particular shard
- SHARDING KEY : KEY on which CH will decide which shard will do be stored
- user_od, request_id,

## DETOUR
- Any client request to a cluster of machines can be handled in 2 ways,
1. LoadBalancer/Gateway - client requests LB and LB decides the server
2. Have a client_code running on the calling client, and the client_app running will be responsible to route the request to the right server
- Ex. Redis has its client running on the client machine to talk to its cluster
- Not the best approach, approach 1 is good

## INTRA SHARD Query
- The query which will be fetching data from only 1 shard
- Super optimised
- To find all friends of a user
- Find all posts of a single user

## INTER SHARD Query
- The query which will be fetching data from more than 1 shard
-

#### News Feed:
1. If we want to show news feed to a user, we need to fetch the user friends(Intra) and next to fetch the posts made by his friends would need a inter(almost all)
2. The queries would be run in almost all db servers , then the response should be aggregated in the app server level and respond
3. High Latency and high load of DB

<b> WE NEED TO CHOOSE THE RIGHT SHARDING KEY SO THAT MAJORITY OF THE FREQUENT QUERIES CAN REDUCE AAS INTRA SHARD QUERIES AND FEW/RARE/ TIME NOT CRITICAL QUERIES CAN BE INTER SHARD

![sharding spectrum.png](resources%2Fsharding%20spectrum.png)

## FACEBOOK NEWS FEED : Case Study
- Post by friends/ pages you follow
- Profile page - my posts

#### Assume : FB uses SQL DB
- Assume all data is fit inside same machine

![Screenshot 2024-02-24 at 5.41.40 PM.png](resources%2FScreenshot%202024-02-24%20at%205.41.40%E2%80%AFPM.png)

### ALL DATA CANNOT FIT IN ONE MACHINE
- NO Practical Sharding key which would help us for all intra shard queries

![Screenshot 2024-02-24 at 5.48.24 PM.png](resources%2FScreenshot%202024-02-24%20at%205.48.24%E2%80%AFPM.png)

###### All the above queries and Profile page would give me Intra shard queries but the most important thing News Feed which the users requests many times anf by almost all the users is getting to INTER (ALL) SHARD and is very bad

#### So to improve news feed , can we think for Caching?
- Client side ? yes, for perceived low latency but will be an issue
- App server ? yes, with some king og LRU users, we can maintain whole news feed
- Not the only solution :  cos if any of their friend/following posts something new, news feed would have to be updated. As Virat is followed by Billions of people , billions of news feed would have to be updated bcpz of one Virat's posts
- the above Virat post is called FAN OUT update


## DENORMALIZATION + Computed Cache
- ok duplicating data to have optimal queries

### Calculation

![Screenshot 2024-02-24 at 6.09.37 PM.png](resources%2FScreenshot%202024-02-24%20at%206.09.37%E2%80%AFPM.png)

![Screenshot 2024-02-24 at 6.12.35 PM.png](resources%2FScreenshot%202024-02-24%20at%206.12.35%E2%80%AFPM.png)

![Screenshot 2024-02-24 at 6.13.53 PM.png](resources%2FScreenshot%202024-02-24%20at%206.13.53%E2%80%AFPM.png)

- So for feed purpose, I would need last 30 of data 
- => 30 * 20GB = 600 GB
- Commodity Hardware (upto 4 - 5 TB) - basic machines big companies use
- 
## Store copy of 600GB in separate machine calling it cache and now all news feed queries is <b> INTRA SHARD</b>

# Using a recent posts(SAME SCHEMA) cache we have converted the inter shard query of news feed generation and converted into INTRA Shard query

#### COLD STORAGE
![Screenshot 2024-02-24 at 6.31.05 PM.png](resources%2FScreenshot%202024-02-24%20at%206.31.05%E2%80%AFPM.png)


### If user wants beyond the 30days data from recent post Cache, we need to go to DB and ask for posts with Time stamp greater than last post available in cache

- If we have space limitation on the recent posts cache , we can have hierarchy such as last 15 days , then (16-30 days), and then (30-60 days) and so on if required depending on use case

### When using pagination and I am on 3rd page and new things get added, how will this be handled?
- LIMIT , OFFSET AND also Timestamp

