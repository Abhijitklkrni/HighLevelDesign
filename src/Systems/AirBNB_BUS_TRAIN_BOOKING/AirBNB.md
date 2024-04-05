BUS BOOKING/TRAIN BOOKING/ HOTEL BOOKING
MVP
1. List all the hotels
2. Book Hotels
3. View availability of room type in a hotel
4. Booking history
5. Payment

Estimation of scale
1. 1Million Hotels, each Hotel 100rooms
   - hotelId, roomId, city , location, description, type,... 1Kb = 100M * 1Kb = 100GB (Sharded by city)
2. 300 Million users
3. 50Millions active users
4. Reservation data - 100M rooms * 365 * 1KB(reservation data) = 100 * 365 GB = 36.5 TB/year = 365 TB of data for 10 years
   - 180 Days of booking allowed ~ 20TB of data, shard by hotelId --> For reservation
   - 365 days - 36.5TB data in a separate DB for providing booking history
5. 100M writes in a day = 100M / 86400 ~= 1000 qps
6. 300Million reads = 300M/100000 = 3000qps

CAP
- 


Design
- API
  - Get All hotels(city) 
  - Fetch all hotels info and send back the response, once the user selects the hotel, then check for availability
    1. For each hotel do availability check using the hotelId in the reservation DB with dates and type as filter - Paginated 
  - Reserve hotel(hotelId, roomId, details)
    1. Write in to futureReservation DB and in async into recent history DB 