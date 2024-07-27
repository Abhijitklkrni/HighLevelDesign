
![Screenshot 2024-04-11 at 1.10.52 AM.png](images%2FScreenshot%202024-04-11%20at%201.10.52%E2%80%AFAM.png)

**@RequestMapping - inform spring about the handler mappings**

![Screenshot 2024-04-11 at 1.16.14 AM.png](images%2FScreenshot%202024-04-11%20at%201.16.14%E2%80%AFAM.png)

![Screenshot 2024-04-11 at 1.18.02 AM.png](images%2FScreenshot%202024-04-11%20at%201.18.02%E2%80%AFAM.png)


- SOLID/Design patterns - says how the code should be formatted
- MVC is the same for API
- REST is how the APIs should be named
  - Best practices to create API's
  
![Screenshot 2024-04-11 at 1.20.46 AM.png](images%2FScreenshot%202024-04-11%20at%201.20.46%E2%80%AFAM.png)

**REST**
- Each API must be working on some entity
- ![Screenshot 2024-04-11 at 1.22.26 AM.png](images%2FScreenshot%202024-04-11%20at%201.22.26%E2%80%AFAM.png)


- Every API should be strctured around the resourse they are working upon
/products/create -Wrong
/products/get - Wrong

- The type of action that API is doing should not be a part of endpoint
- For action - Http methods

**PATCH**
![Screenshot 2024-04-11 at 1.26.51 AM.png](images%2FScreenshot%202024-04-11%20at%201.26.51%E2%80%AFAM.png)

**PUT**
  - Replace a entity - Override

- Rest APIs should be stateless


###### **FTP IS STATEFULL**


![Screenshot 2024-04-11 at 1.34.08 AM.png](images%2FScreenshot%202024-04-11%20at%201.34.08%E2%80%AFAM.png)

GET /mentors/10
![Screenshot 2024-04-11 at 1.34.55 AM.png](images%2FScreenshot%202024-04-11%20at%201.34.55%E2%80%AFAM.png)

- We get back userId only
- If we need user info , we have to make one more API call
**These are called CHATTY API's**
- ![Screenshot 2024-04-11 at 1.36.18 AM.png](images%2FScreenshot%202024-04-11%20at%201.36.18%E2%80%AFAM.png)

- NO restriction on return type
  - JSON (most preferred)
  - XML
  - STRING
  - PROTOBUFF (RPC calls)

JACKSON lib does it
Serialization - JSON -> Object
De Serialization - Object -> JSON








































