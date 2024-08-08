# SEARCH TYPEAHEAD

## TEMPLATE
1. List of features/ Min Viable Product (MVP)
2. Estimation of Scale
   - Is Sharding a necessity ? One Server can handle 4-6 TB of data
   - Read heavy/ write heavy/ Read or Write heavy
   - News Feed : Read 
   - Stream of data from Sensors - Write
   - Messenger - Read + Write

3. Design Goals / Tradeoffs
   - CAP (Consistency or Availability)
   - Horizontal / Vertical Scaling
   - App Servers - Stateful or Stateless
   - Consistency or Latency
   - Caching
   - SQL vs NO SQL

4. Design Deep Dive
   - APIs
   - Components - LB, app server, global cache, DB layer,File storage
   - Data flow


## DESIGN TYPEAHEAD
1. MVP
   ![Screenshot 2024-03-03 at 5.05.10 PM.png](resources%2FTypeaheadSystem%2FScreenshot%202024-03-03%20at%205.05.10%E2%80%AFPM.png)
   - No Spell correction,no search results based on location or geography

2. Estimation of Scale
   - Google scale 
   - Population ~7B
   - Users with internet - 4B
   - Daily Active Users(DAU) - 1B
   - 10 queries per person per day
   - Total Queries/Day = 10 Billion
   - ![Screenshot 2024-03-03 at 5.19.00 PM.png](resources%2FTypeaheadSystem%2FScreenshot%202024-03-03%20at%205.19.00%E2%80%AFPM.png)
   - Every search query request will send many typeahead request, request after each character is written after fixed chars decided
   - Queries to backend for 1 search - 5
   - No. of type ahead completion queries = 5 * 10B = 50B
   - ![Screenshot 2024-03-03 at 5.27.51 PM.png](resources%2FTypeaheadSystem%2FScreenshot%202024-03-03%20at%205.27.51%E2%80%AFPM.png)
   - ![Screenshot 2024-03-03 at 5.28.33 PM.png](resources%2FTypeaheadSystem%2FScreenshot%202024-03-03%20at%205.28.33%E2%80%AFPM.png)
   ##### Only if read/write overpowers the other by 100 multiple, we should mark it as that heavy system

###### Concurrency 
QPS = Queries per second
1. Write queries/day = 10B / 24 * 60 * 60 = 1.1 * 100,000
2. Read queries/day = 10B / 24 * 60 * 60 = 5.1 * 100,000
- Avg. 100k read queries = max 200k
- Avg. 500k write queries = max 1M
QPS helps me decide on # of App servers required

###### STORAGE





![Screenshot 2024-03-05 at 12.39.16 AM.png](resources%2FTypeaheadSystem%2FScreenshot%202024-03-05%20at%2012.39.16%E2%80%AFAM.png)


![Screenshot 2024-08-08 at 9.57.39 PM.png](resources%2FTypeaheadSystem%2FScreenshot%202024-08-08%20at%209.57.39%E2%80%AFPM.png)

If I get a search on Michelle, increment frequency in the left table and 
for each character substring  mic,mich,miche,michel,michell,michell,michelle - check the right table if their top 5 suggestions will have to be reshuffled as 
top 5 suggestions are based on frequency of the search in the left table

- right table has suggestions with their frequency

- As writes are very frequent the above will be difficult

- we somehow have to manage high writes
- Message Queue - Kafka
- **Threshold Approach**
  - If the frequency of the search is more than 10000, then only update the right table
  - after 10001,20001,30001 only update the right table
  - We are ok with eventual consistency

- HOW ABOUT SCHEDULED JOB? BAD IDEA :(
  - Every 5 mins, update the right table
  - But this will not be real time
  - If the search is very frequent, then the suggestions will not be updated in real time


- Client side cache (Personal top 5 suggestions)
- App server cache (Global top 5 suggestions) - For popular substrings of popular searches









