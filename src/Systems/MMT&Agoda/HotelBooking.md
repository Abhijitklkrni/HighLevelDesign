## TWITTER

**MVP** 
1. Create a tweet
2. Like a Tweet
3. ReTweet
4. Follow users/Pages
5. Feed

Estimation of scale
- Total users 1Billion
- 300Million DAU
- 100M writes, 300M Reads
- User DB - 1B * 1KB = 1TB data
- 1 tweet
  - tweetId - 8 Bytes 
  - userId - 8 Bytes
  - tweet - 250 Bytes
  - media url - 10Bytes
  - Original tweetId
  - ~= 300Bytes
###### - 100M * 5 * 300B = 150GB = 4.5TB /Month =  50TB/year = 500TB for 10years 
- 50M * 5tweets/day * 300B = 250 * 300 MB = 75 GB/day = 2.1 TB/month = 25TB /year = 250 TB for 10years
- Following (follower, followee)
- 100M/86400 ~- 1000 write qps, 3000 read qps
- Read + Write heavy system

CAP
- Posting a tweet - A >> C

Design
- API
  1. Create a tweet(tweetId,userId,txt, originalTweetId, Media)
    - LB -> App servers -> DB(Cassandra like) sharded by userId ---Async---> written into FEED DB(30days)
    - FEED DB can also be huge (2.2TB) (user Id shard)
  2. GetFeed() 
    - Get from the Feed DB directly
  3. Like a Tweet(tweetId)














How is media transferred from frontend to backend?
- Every media has its binary file, it is compressed(BASE64,JPEG,JPG,MP4 and so on) and is sent to backend 
Prepare the Media: The media file is prepared on the client side, typically by encoding it into a suitable format (e.g., JPEG, PNG, MP4) and possibly compressing it to reduce its size.

Initiate Upload: The client makes a POST request to the media/upload endpoint, providing the media data along with some metadata, such as the media type and possibly a media category (e.g., image, video).

Chunked Upload: Twitter supports chunked media uploads, where the media file is divided into smaller chunks. Each chunk is uploaded sequentially using a series of API requests. This allows large media files to be uploaded in parts, reducing the likelihood of failures and enabling faster uploads.

Complete Upload: Once all the chunks have been successfully uploaded, the client makes a final request to the media/upload endpoint with a command to finalize the upload. Twitter then processes the uploaded media and returns a media ID that can be used to attach the media to a tweet or other content.

Attach Media to Tweet: The media ID obtained from the upload process can be included when creating a tweet. This associates the uploaded media with the tweet, allowing it to be displayed alongside the tweet's text.

Overall, Twitter's media upload process is designed to be robust and efficient, allowing users to easily share images, videos, and GIFs through the platform.








