### MESSENGER
![Screenshot 2024-03-06 at 12.34.08 AM.png](resources%2Fmessenger%2FScreenshot%202024-03-06%20at%2012.34.08%E2%80%AFAM.png)

1. FEATURE
   - Send msg/ Receive Msg
   - 1 : 1 msg
   - Group Messaging
   - Chat History
   - msg should be part of Conversations
   - ![Screenshot 2024-03-06 at 12.42.04 AM.png](resources%2Fmessenger%2FScreenshot%202024-03-06%20at%2012.42.04%E2%80%AFAM.png)
   - ![Screenshot 2024-03-06 at 12.44.00 AM.png](resources%2Fmessenger%2FScreenshot%202024-03-06%20at%2012.44.00%E2%80%AFAM.png)

2.Estimation of Scale
   - Users 2Billions
   - DAU 400 Million
   - Avg. msg sent per day 400Million * 50 = 20B Msgs/Day
   - Write QPS = 20B/86400
   - Read QPS = 4 * write QPS
   - READ + WRITE HEAVY SYSTEM :)

MESSAGE
- content - 70 chars - 70 B
- senderId - 8B
- receiverId - 8B
- TimeStamp - 8B
- msg_id - 8B
- media_ref - 10B
- Status[] - 1Bit each(Delivered,Read,Edited) ~ 8B
- Total ~= 200B /msg
![Screenshot 2024-03-06 at 12.53.14 AM.png](resources%2Fmessenger%2FScreenshot%202024-03-06%20at%2012.53.14%E2%80%AFAM.png)

![Screenshot 2024-03-06 at 12.54.51 AM.png](resources%2Fmessenger%2FScreenshot%202024-03-06%20at%2012.54.51%E2%80%AFAM.png)

#### 10 years of data Petta Bytes of data
![Screenshot 2024-03-06 at 12.55.37 AM.png](resources%2Fmessenger%2FScreenshot%202024-03-06%20at%2012.55.37%E2%80%AFAM.png)

###### Sharding is a MUST


###### WHATSAPP is DIFFERENT
- WA doesn't store data, it only acts as broker
- WA stores on the client's device


3. DESIGN GOALS
   - Latency Reasonably low latency
   - Consistency is important. C >> A

Conversation
convId userId    type 
101    A1,B1     1-1
102    A1,B1,C1  grp


### IDEMPOTENT
- The requests sent to the server should be idempotent
- Why? In Phone Pe ,If I try to do a payment of 10Rs and the server has received the request but the ack failed to reach back the client. Client might think the request was lost hence retries. As previously the request was processed the server should ignore the 2nd request and not process it further and send back an ack to the client.
- REST POST calls are stateless and not Idempotent by nature
- That is the reason we should have client generated RequestId sent with each request
- ![Screenshot 2024-03-06 at 1.16.03 AM.png](resources%2Fmessenger%2FScreenshot%202024-03-06%20at%201.16.03%E2%80%AFAM.png)


DESIGN DEEP DIVE - convId(grpId,receiverId)
   - API DESIGN (userId can be changed to auth token)
     1. SendMessage(senderId, convId,content)
        - ![Screenshot 2024-03-06 at 1.19.44 AM.png](resources%2Fmessenger%2FScreenshot%202024-03-06%20at%201.19.44%E2%80%AFAM.png)
        - ![Screenshot 2024-03-06 at 1.22.03 AM.png](resources%2Fmessenger%2FScreenshot%202024-03-06%20at%201.22.03%E2%80%AFAM.png)
     2. GetAllConversations(userId,limit,offset) - paginated
        - ![Screenshot 2024-03-06 at 1.24.43 AM.png](resources%2Fmessenger%2FScreenshot%202024-03-06%20at%201.24.43%E2%80%AFAM.png)
     3. getMessages ( userId,ConvId,limit,offset)
        - ![Screenshot 2024-03-06 at 1.25.38 AM.png](resources%2Fmessenger%2FScreenshot%202024-03-06%20at%201.25.38%E2%80%AFAM.png)
        
   - DESIGN
     - Component diagram
     - User table, conversation table, Messages table
     - Federated User db (Sharded SQL with userId as shardKey)
     - Federated(separate) Conversation and Message db
     - ConvId as shard key : API 1 and 3 will be Intrashard and 2 will be all shard
    
----- NOT COMPLETE -----











































































