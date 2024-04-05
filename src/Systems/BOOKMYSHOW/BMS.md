**BOOK MY SHOW**

MVP
1. Get all Movies,shows, Events
2. Book tickets
3. Payment

Estimation of Scale
- 300Million users
- Every user 1Kb - 300M * 1KB = 300GB
- 100,000 Events/Movies in month = 33000 events per day
- 33000 * 300 seats = 9,900,000 = 9.9M requests per day = 10M /86400 = 14-15 qps
- Event 100Kb of information - 100KB * 100000 = 10GB per month ~ 120GB/year - 1200GB ~= 1.2TB

CAP
- while fetching seats to view empty seats - A >> C
- Booking - C >>A
- Getting events A >> C
- Read heavy system


System design
- API
  - Get Events in particular city ( Events DB shard by city ID)
  - BookEvent (request Id, userId, eventId, seats{})
  - Global cache for active cities
  - Get to the server, rank the events and return
- 
