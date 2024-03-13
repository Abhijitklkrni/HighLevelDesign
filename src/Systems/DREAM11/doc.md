
## DREAM11

1. MVP
   - Show all the contests
   - Join a contest
   - Create teams
   - Add money to the wallet
   - Show live points
   
2. Estimation of Scale
   - 1.4 Billion Population
   - 1 Billion Internet users
   - 200 Million Total users
   - 5M Active users
   - 200M * 1000B - User metadata = 200 GB of user data
   - Contests : id, prize money split, joined users(5M) per contest * 100 Contest * 100 = 50GB per day
   - IPL : 60days * 50 GB = 3000GB = 3TB, ~5TB/year
   - NO SQL for User  DB as well, to store the contests information
   - Team 200B /Team, 20 teams/user,5M user,100 Matches ~= 20MB * 10^4 = 2000GB = 2TB
   - Shard Teams DB on userId
   
3. CAP
   - Availability is important , Eventual consistency is ok - For Getting live points
   - Consistency is Important then availability during joining contest
   - Consistency high, Low Latency and vice versa

4. System Deep dive
   - API
     - Join Contest(requestId,contestId, userId, teamId)
     - Get Joined Contests(userId)
     - getUpdatedPoints(userId, matchId)
     - create Team (userId, matchId, List<playerId)
     - Update Points (MatchId, Map<playerId,Map<points,Reason>>)
   
   - Team DB sharded on userId
   - Have Cache for live points of famous matches -  5matches, 100M users,10 teams = 5MB * 10^5 ~= 500GB
   - 

Users
- name 
- userId
- email
- phone number

Contest 
- contest Id
- Size
- Money Split Map<Range, Amount>
- MatchId
- Info

Match 
- MatchId
- Game DateTime
- Home Team
- Opposition Team
- Ground (Venue)
- Umpires
- Players

User_Contest
- ContestId
- userId
- teamId
- JoinedDateTime

Team
- teamId
- UserId
- MatchId
- List<Players>
- Captain
- ViceCaptain
- Points

Player
- playerId
- dream11 rating
- Nationality
- Team

Player_Match
- playerId
- MatchId
- total points scored
- Map<Points,Reason>