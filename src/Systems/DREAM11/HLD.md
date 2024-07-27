### DREAM11

**MVP**
- User registration
- User authentication/authorisation
- User should see the live contests
- The contests should be grouped based on the match
- User should be able to create the teams for the match(4Teams)
- User should be able to join the contest
- User should be able to see the history of contests he has joined in the past for a match
- User should be able to see the points scored by the player for that particular match
- Deposit money to the winners after the game
- Payment


**Estimation of Scale**
- 500Millions users
- 500M * 1Kb = 500 GB, users - SQL DB
- 100Million, 5teams
- 1Team = 11player = 8B ~= 100B... 100M * 5 * 100B = 50GB Teams * 10 = 500GB /day, 1.5 TB /month, 18TB year , 180TB 10 year, shard userId
- Contest - 20 Contest * 10match = 200Contest = 10000 * 8B * 200 = 16MB
- 100M * 5 / 86400 = 5000 qps write
- 100M * 10 / 86400 = 10000 qps read 
- Read + Write heavy system

**CAP**
- 

** DESIGN DEEPDIVE**
- API createTeam(userId,matchId,Team player[])
- Join a contest ( userId,matchId, contestId,teamId)



DB - 
Contest 
1. users 
2. prize money
3. 1-10 - Range

10000 - 101
ContestDetails
- contestId 101_1
- size - 100
- 100Entries



101_1 10 50
101_2 10 10
101_3 10 80
101_4 10 100











