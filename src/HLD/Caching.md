Caching

<h2>Latency</h2>
- Its subjective
- Sum of all latency

<b>User Latency</b>
- RTT - Round trip time :The time it takes to reach the server/gateway plus time it takes to return from server to client
![Screenshot 2024-02-22 at 10.39.17 PM.png](..%2F..%2F..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2F7q%2Fkh8lx6c56sjchhvswcts09dm0000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_ECD2t8%2FScreenshot%202024-02-22%20at%2010.39.17%E2%80%AFPM.png)

- Network call from app server to DB

- DB Access time

- Computation time

<h3>Caching help reduce Latency</h3">

For DNS, data is cached in
- Browser
- OS
- Router
- Neighbourhood

<h2>Browser cache</h2>
- cache at browser level, so user feels low latency

<h2>Local/ In memory/ App server cache</h2>
- If the requests are stateful and we are sure if the request is going to hit the same server, why dont we maintain the data at app server level and save network call to DB
- App server caching
- Given we know that in stateful load balancing a particular request is always going to land on th same machine and we can make use of this and cache the relevant data on the right machines
- Cached data can be stored in RAM/ Hard disk of the server as storing at hard disk level also stores network call time 

Separation of concern mislead?
- Cache is not the source of truth, so saving temporarily doesn't harm

<h2>Global Cache</h2>
- Parallel cluster of machines whos only job is to save cache
- Redis, Memcached 
- Virat kohli - If Instagram realises that the famous people posts can be viewed/requested by many people , why don't they store in the cache and save call to db
- used if many servers save the same cache

If we want to maintain Session stickyness
- meaning requests from one session go to the same server, we should have session id as the CH key

<b>Distributed</b> - cluster of machines
In distributed system - CH plays a major role to send requests to the correct node/machine

<h2>Caching-2</h2>
CDN - Content Delivery Network

- Netflix has Data centre in US
- Users are spread across
- If all users request the same DC, servers may get choked
- Big static(even live match is static 10.30 - 10.35 will never change) media content like videos , photos , etc need to be transferred again and again for every user
- Network will be choked at source end(Netflix, Hotstar)
- Duplicate data - if 2 users request same episode, we need to send the same content twice even if they are in the same room
- Latency of data transfer is prop to distance

Solution
CDN
- Big companies who have done the hard work in building infrastructure all over the world
- AWS, Akamai
- invest Billion of dollars and put physical hardware all over the world
![Screenshot 2024-02-23 at 12.23.52 AM.png](..%2F..%2F..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2F7q%2Fkh8lx6c56sjchhvswcts09dm0000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_FPZaXc%2FScreenshot%202024-02-23%20at%2012.23.52%E2%80%AFAM.png)
- Main centre starting at continent level going down to country, state , city level with copies

- Companies(Netflix, Hotstar) get into contract with Akamai and send its content to akamai's central server and Akamai transfers the content till its leaf nodes where ever it thinks users may come from , depending on the plan , more money less Latency guarantee
- Companies also maintain the data at their end as ultimate source of truth

BLOB Storage ( S3, File storage)
- Images and videos are not stored in the normal sql db as we might not need to go to a particular time and change its value in case of videos and we might not go to a particular pixel and chnage its value
- SQL stores numbers, text and things which we might change frequently


- when user does normal requests , all these are handled by Netflix servers but the time
 user decides to watch a particular videos and click on it, Netflix processes the request and now
instead of getting the videos from its own server , it gives the client the url of the videos stored in the nearest CNC server
  https://scontent.fblr2-2.fna.fbcdn.net/v/t39.30808-6/404233870_369037542135994_1012928038352047342_n.jpg?_nc_cat=107&ccb=1-7&_nc_sid=dd5e9f&_nc_ohc=yG54rjupcNAAX-X2Hjn&_nc_ht=scontent.fblr2-2.fna&oh=00_AfB2Rftri30DfHAFoxONtykDX0Q-LZPQXFK12IZVVhB27w&oe=65DD46AB
-blr CDN for me

- If the data requested is not available in the CDN node, it requests its parent node to get the data and so on
- How does it form the CDN url? 
CDN after storing will send out the resource id and depending on location it knows the prefix of the url and it will append the resource id
![Screenshot 2024-02-23 at 12.48.57 AM.png](..%2F..%2F..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2F7q%2Fkh8lx6c56sjchhvswcts09dm0000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_jrhLIO%2FScreenshot%202024-02-23%20at%2012.48.57%E2%80%AFAM.png)
- Data is also transferred in the same way 

- How does Netflix or Hotstar know which CDN node is nearest to me?
If location is shared well and good or else through
- <h3>ANYCAST IP Routing</h3>
- READ MORE

<b>CACHE  IS JUST A COPY</b>
- <b> 1. CACHE is not ultimate store of data , hence not source of truth</b>
- <b> 2. CACHE is limited in size.. Cache size <<<<<< BD size, so cant cache everything </b>
<h3>So Cache can become inconsistent</h3>

<h2>Cache Invalidation Strategy</h2>

- Inconsistent cache may not be such imp in some cases, like on a post, no of views, no of viewers
- IRCTC seats available - Very imp

- <h3>TTL - Time To Live </h3>
1. Our cache can become out of sync with the source of truth, with TTL we can kick out the old cache entries

<b>Cache eviction Strategies</b>
- <h5>FIFO</h5>
- <h5>LRU</h5>
- <h5>LIFO</h5>
- <h5>MRU</h5>


<h3>Strategy used to write in the Cache </h3>
1. Write through cache
- Write in the cache first and then to DB
- ![Screenshot 2024-02-23 at 1.09.48 AM.png](..%2F..%2F..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2F7q%2Fkh8lx6c56sjchhvswcts09dm0000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_EA65gq%2FScreenshot%202024-02-23%20at%201.09.48%E2%80%AFAM.png)
- Con 
-  Write Latency increases
- Cache is not of same size as DB  , will have to keep kicking out the old data - loads of cache eviction as we are trying to write everything
Pros
1. Data consistency
2. For Read heavy system and less writes ( Twitter tweet, In shorts). 
3. Read will speed up

2. WRITE BACK CACHE
- Client doesnt wait for the cache to DB copy
![Screenshot 2024-02-23 at 1.10.17 AM.png](..%2F..%2F..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2F7q%2Fkh8lx6c56sjchhvswcts09dm0000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_t6gMKr%2FScreenshot%202024-02-23%20at%201.10.17%E2%80%AFAM.png)

Pro
- very fast write
- DB can be written in  batches

Cons
- Data might be lost forever
if a thing is missed it should not be that critical.
Ex : Emoji on the live stream, where timing is important.. its ok even if it is not recorded

3. WRITE AROUND CACHE ( )
- Most commonly used
- Write to DB and request is returned
- Based on requirement - cache will poll or DB pushes to cache in async
- pro : Data is always persisted
- con : Inconsistent
![Screenshot 2024-02-23 at 1.14.57 AM.png](..%2F..%2F..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2F7q%2Fkh8lx6c56sjchhvswcts09dm0000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_gRxLnY%2FScreenshot%202024-02-23%20at%201.14.57%E2%80%AFAM.png)


REAL LIFE EXAMPLES
- RANK LIST of a contest
- System diagram
1. Given the question are fixed content and every student will request the same content, why not cache it globally or even as app server level if Qs are less and stateless
2. After submitting , for ever person every Q status must be stored in DB
3. To Rank we will need to check every person's every Q to rank them and this will keep changing every minutes
4. Read rank query will be slow as app server will collect and need to generate the result and is not scalable

Alternate Solution
1. Reading Q's is still app server cache as stateless
2. Read rank list Query : 
- Decouple calculating rank per request. 
- Have a job which runs every 5mins or decided time and calculates the rank and updates the GLOBAL CACHE.
- Any read rank list Q can be handled by any server and be read from Global Cache.

CODE EVALUATION
- When we start coding on a Q in the editor the code keeps on saving continuously in Client cache.
- Every 2mins / 5mins - we do a saveCode to the server to save in DB
- EvaluateCode() : Rely on test cases file - stored in file storage as they are big files
- Getting test file on every evaluate call will be costly

- Global cache is also costly
- not even client cache - hacking
- CDN - NOOOOOO
- App server is the only place and it should be made stateful and storage only those problem id test cases file
- How to handle different languages evaluation
1. Create a short-lived container(with all libs - docker image) specific to the env , evaluate ,save to db, return response and remove the container
![Screenshot 2024-02-23 at 1.50.06 AM.png](..%2F..%2F..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2F7q%2Fkh8lx6c56sjchhvswcts09dm0000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_XvzeOf%2FScreenshot%202024-02-23%20at%201.50.06%E2%80%AFAM.png)

<h1>Evaluate button -> Gateway -> Load Balancer of that particular service (does CH) -> particular node