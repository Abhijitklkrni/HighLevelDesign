
Vertical Scaling
- Bigger better machine

Problems
- Costly
- Estimation is difficult
- SPOF
- 

Horizontal Scaling
- Buy more servers
Pros
- Cheaper
- Flexibility
- no SPOF

cons
- machines need to talk - read data, share data
- network calls
- maintenance


-cant really predict resource required as product might be stable(user usage)

what should delicious use?
- Horizontal scaling as we are not sure of the future

Gateway (Reverse proxy)
- First point of contact to outside world
- route to correct machine
- block bad actors (not from blocked ip, bots)
- does not do any computation

SPOF ? Gateways follows Active and passive gatewaing 
- If a active gateway goes down ,passive ones takes over
- Its easy to replace as gateways doesn't do computation

- Sharded gatewaying/ replicated gatewaying / Gateway as a service

If the cluster are in diff regions then , each region/cluster would have different gateway

Gateway != Load Balancer
- In today's world gateway and load balancer are managed in a single server
- Gateway machine can (usually does) also perform the role of load balancer a
and hence people also call this machine a load balancer

Load Balancer
when we have multiple incoming request, and you want a machine to distribute those requests amongst different machine of the cluster

Hardware load balancing and software load balancer
- Hardware itself does load balancing
- Run a software to do load balancing

Load Balancing
- Stateless: If all machines are equally well-equipped to do the computation - Round Robin
- Stateful: chatGPT

Hashing for stateful
- id % number of machines

problems
- no. of machines will not stay the same
- transfer partial state of data to new server added

CONSISTENT HASHING
- Imagine a circle with marking from 0 to 10^18, 10^18 is just for reference, just a large number
- Will keep a hashing(Hs) function if given a server Id , it will place the server at the imaginary circle at some number. function will balance at placing the server so that majority of the circle portion is covered
- One more Hash function (Hr) , request id as example, function will be placed on the imaginary circle and request will flow to the server placed first when we move clockwise.

What happens if we add a new server?
- With the current setup when a new server is added, only the server placed next to it will have its load reduced.
- Adding a new server is not benefiting

- Same would happen when we remove an existing server, all the load that server was taking will be shifted to next server.

- The servers are stored in the sorted array, index being output of Hs

- When ever we receive a request, value is calculated using Hr snd using the sorted array 
we find the server to which the request should be sent using log(N) time. N - no. of servers


Solution:
- Instead of having one marking per server and multiple marks per servers
- ![Screenshot 2024-02-22 at 9.38.32 PM.png](..%2F..%2F..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2F7q%2Fkh8lx6c56sjchhvswcts09dm0000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_2MBp5A%2FScreenshot%202024-02-22%20at%209.38.32%E2%80%AFPM.png)

