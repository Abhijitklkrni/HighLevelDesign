Pre req: Quad tress
Timestamp based entries - Cassandra handles it well
###### Timestamp based entries - Cassandra handles it well

UBER

- Places of interest = drivers
- problem - drivers are not static
- Deleting and adding could be done as seen before, but will be difficult as there would be many cases in case of drivers
- Why not have different Quad tress for different regions as intercity would not be an option
- Why would a driver in Mumbai be concerned about user in Bengaluru
- 
![Screenshot 2024-03-10 at 3.35.36 PM.png](resources%2FUberCaseStudy%2FScreenshot%202024-03-10%20at%203.35.36%E2%80%AFPM.png)

- Consider Uber as smaller Uber system working as a Whole

![Screenshot 2024-03-10 at 3.37.06 PM.png](resources%2FUberCaseStudy%2FScreenshot%202024-03-10%20at%203.37.06%E2%80%AFPM.png)

- Each region would have its Quad tree and DB

![Screenshot 2024-03-10 at 3.39.20 PM.png](resources%2FUberCaseStudy%2FScreenshot%202024-03-10%20at%203.39.20%E2%80%AFPM.png)
- Even if by mistake DNS routed you to wrong gateway(NY), Uber will internally route you to right Gateway

## ZOOM INTO ONE CLUSTER
- Initialize the Quad trees not wrt drivers, but with previous findings and all
- Check for each Driver location and assign him to an node
- Keep Global cache with driver and his current location
- With the feed coming from each driver in each minute, check the global cache and see if the location has got updated. If yes, update the cell nodes in the Quad Trees without splitting or merging as done before, if not - Ignore
- Also the quad tree creation
![Screenshot 2024-03-10 at 4.01.32 PM.png](resources%2FUberCaseStudy%2FScreenshot%202024-03-10%20at%204.01.32%E2%80%AFPM.png)

![Screenshot 2024-03-10 at 4.01.49 PM.png](resources%2FUberCaseStudy%2FScreenshot%202024-03-10%20at%204.01.49%E2%80%AFPM.png)

![Screenshot 2024-03-10 at 4.02.45 PM.png](resources%2FUberCaseStudy%2FScreenshot%202024-03-10%20at%204.02.45%E2%80%AFPM.png)
- Uber see traffic at diff places at diff time
- Morning : home -> office,college and so on
- Evening : Office,College,School -> Home or Home-> Club, Restaurant
- So re initializing Quad trees is good enough
- Uber does this to avoid frequent restructuring of Quad trees due to movement of drivers
- 
https://www.scaler.com/academy/mentee-dashboard/class/147619/session?joinSession=1





