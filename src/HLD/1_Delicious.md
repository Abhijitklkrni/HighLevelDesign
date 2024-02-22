Delicious
- Allow user to login and store bookmarks
- add bookmarks
- view bookmarks
- remove bookmarks

start
- buy a static ip address
- DNS registration

DNS
- handles requests at internet scale - HUGE

Problems
- millions and millions of requests - Scale
- Latency 

solution
- Distributed systems
- DNS is a hierarchy
- TLD(Top Level Domain) server (each server for .com, .in, .us. and so on)
- Each TLD has more servers and those servers have more (neighbourhood servers)
- Caching at each level of network calls (browsers, routers)

Domains
- Go Daddy, directI - aggregators
- top level domains names are owned by a non-governmental organisation called
    ICANN (central authority). ICANN maintains all the top level domains
- Go Daddy and all aggregators buy from in bulk from ICANN and sell

start
- buy a static ip address
- DNS registration (domain name and IP mapping)

ICANN maintains TLD servers
Neighbourhood DNS servers are managed by ISP(to provide fast internet)

DNS Spoofing 
- wrong DNS servers can be very dangerous
- fake DNS can give fake clone bank websites page and extract credentials

Delicious became popular
Bottlenecks
- CPU
- Hard disk
- RAM
- Network card (NIC)
- SPOF

Solution
- Horizontal scaling
- Vertical scaling