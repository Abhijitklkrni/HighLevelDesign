## RATE LIMITER
- Controlled no of requests from a service
- Limit no. of requests
- Google - Are you a human?, after rate limit exceeded
- HTTP STATUS - 429, too many requests

##### These algos will run in the Gateway
Algorithms used
1. Token Bucket Algorithm]
   - ![Screenshot 2024-03-10 at 7.49.25 PM.png](resources%2FRateLimiter%2FScreenshot%202024-03-10%20at%207.49.25%E2%80%AFPM.png)
   - Refill rate - tokens get added at fixed rate to the bucket and once the token is available, only then user request will be served
   - refill rate - token/ 0.1 second = 10 tokens / sec
   - which means , user can request max 10 times per second
   - If there are no requests coming from a user then the token can max accumulate to the capacity level configure.
   - So, even if the bucket is filled to capacity, at a time (burst activity) user can send only capacity level of requests
   - Con: No concept of waiting, if there are no tokens left, requests will get dropped 
   - ![Screenshot 2024-03-10 at 11.59.01 PM.png](resources%2FRateLimiter%2FScreenshot%202024-03-10%20at%2011.59.01%E2%80%AFPM.png)

2. Leaky Bucket
   - Request from the client will be added to the bucket till the max capacity is reached
   - AppServer will be reading from the bucket at a fixed rate 
   - Concept of waiting, requests will be queued upto a capacity and will then be answered
   - Ex; Mail service
   - ![Screenshot 2024-03-11 at 12.09.50 AM.png](resources%2FRateLimiter%2FScreenshot%202024-03-11%20at%2012.09.50%E2%80%AFAM.png)
   - ![Screenshot 2024-03-11 at 12.10.44 AM.png](resources%2FRateLimiter%2FScreenshot%202024-03-11%20at%2012.10.44%E2%80%AFAM.png)
   ![Screenshot 2024-03-11 at 12.11.16 AM.png](resources%2FRateLimiter%2FScreenshot%202024-03-11%20at%2012.11.16%E2%80%AFAM.png)
   ![Screenshot 2024-03-11 at 12.00.05 AM.png](resources%2FRateLimiter%2FScreenshot%202024-03-11%20at%2012.00.05%E2%80%AFAM.png)

3. Fixed Window Counter
   - You use it or lose it. No carry over
   - No burst traffic, traffic could be max upto no. of requests in window
   - ![Screenshot 2024-03-11 at 12.46.31 AM.png](resources%2FRateLimiter%2FScreenshot%202024-03-11%20at%2012.46.31%E2%80%AFAM.png)
   - Never used
   - ![Screenshot 2024-03-11 at 12.45.43 AM.png](resources%2FRateLimiter%2FScreenshot%202024-03-11%20at%2012.45.43%E2%80%AFAM.png)
   - Intention gets defeated

4. SLIDING WINDOW COUNTER - better version of 3
   - ![Screenshot 2024-03-11 at 12.50.19 AM.png](resources%2FRateLimiter%2FScreenshot%202024-03-11%20at%2012.50.19%E2%80%AFAM.png)









































































   