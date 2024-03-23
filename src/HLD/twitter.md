## ****Twitter****

**MVP**
1. I should be able to send tweets
2. Get feed of tweets I follow
3. Explore tweets
4. Re tweet
5. Follow someone

**Estimation of Scale**
- 500Million of total users = 50 Cr
- 300 Million of daily users (DAV)
- 100 Million active users who post
- 300 Million read posts
- Each users tweets 5 on an average
- Each tweet
    - tweetId (8 Bytes)
    - userId (8 Bytes)
    - mediaUrl
    - caption
    - ~200Bytes
    - 30M * 20,000Bytes = 600GB of data daily
    - 100M * 5 * 200Bytes = 100GB 
    - 1Month = 100 * 30 = 3TB
    - 1Year =  3.2TB , 10 Years = 30TB Data
- Sharding is a must ( sharding key can be userId)

**CAP**
- Availability >> Consistency
- Latency >> Consistency ( Eventual Consistency)  

**Design Deepdive**
1. API
   - Post Tweet(requestId, userId, mediaUrl, caption)
   - Get Feed(userId)

