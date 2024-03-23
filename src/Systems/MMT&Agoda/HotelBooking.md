## HOTEL BOOKING

### **MVP**
1. User should be able to search for hotels
2. User should be able to book a hotel
3. User should be able to cancel a booking
4. User should be able to see the booking history 
5. User should be able to see the hotel details

### **Estimation of Scale**
1. 200Million of total users = 20 Cr
2. 50 Million of daily users (DAV)
3. 10 Million active users who book
4. 1kb of user data => 200M * 1KB = 200GB (No sharding)
5. 10M Booking in a day => 200Bytes * 10M = 2GB, 1Month = 60GB, 1Year = 720GB,10Years = 7TB
6. Sharding must be done to reduce latency
7. qps = 50M/86400 = 600qps

### **CAP**
1. Consistency > Availability
2. We are ok with low to medium latency

### **System Deep dive**
1. API
   - Search Hotel(location, checkIn, checkOut)
   - Book Hotel(userId, hotelId, checkIn, checkOut)
   - Cancel Booking(bookingId)
   - Get Booking History(userId)
   - Get Hotel Details(hotelId)


###### Booking
- BookingId
- UserId
- HotelId
- CheckIn
- CheckOut
- Amount
- Status































































































