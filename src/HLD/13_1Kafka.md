### ASYNC TASKS

#### Till now
![Screenshot 2024-03-07 at 12.15.45 AM.png](resources%2FKafka%2FScreenshot%202024-03-07%20at%2012.15.45%E2%80%AFAM.png)
- Not always possible to wait until all writes are completed

1. Non Blocking Tasks
2. Even is registered, will take place in future

### Mailing system
- USE CASE 1
  ![Screenshot 2024-03-07 at 12.18.04 AM.png](resources%2FKafka%2FScreenshot%202024-03-07%20at%2012.18.04%E2%80%AFAM.png)
###### More waiting time

- USE CASE 2
  ![Screenshot 2024-03-07 at 12.24.54 AM.png](resources%2FKafka%2FScreenshot%202024-03-07%20at%2012.24.54%E2%80%AFAM.png)
###### Rate of consumption is not equal to rate of production

- USE CASE 3
##### Send mail Digest - Do Aggregation
![Screenshot 2024-03-07 at 12.26.53 AM.png](resources%2FKafka%2FScreenshot%202024-03-07%20at%2012.26.53%E2%80%AFAM.png)

## IMPLEMENTATION OF ASYNC TASKS
![Screenshot 2024-03-07 at 12.28.58 AM.png](resources%2FKafka%2FScreenshot%202024-03-07%20at%2012.28.58%E2%80%AFAM.png)


# KAFKA
![Screenshot 2024-03-07 at 12.33.58 AM.png](resources%2FKafka%2FScreenshot%202024-03-07%20at%2012.33.58%E2%80%AFAM.png)

![Screenshot 2024-03-07 at 12.38.08 AM.png](resources%2FKafka%2FScreenshot%202024-03-07%20at%2012.38.08%E2%80%AFAM.png)

![Screenshot 2024-03-07 at 12.38.34 AM.png](resources%2FKafka%2FScreenshot%202024-03-07%20at%2012.38.34%E2%80%AFAM.png)


![Screenshot 2024-03-07 at 12.41.50 AM.png](resources%2FKafka%2FScreenshot%202024-03-07%20at%2012.41.50%E2%80%AFAM.png)

## Offset and all config are managed by ZOOKEEPER

#### PROBLEMS
![Screenshot 2024-03-07 at 1.05.27 AM.png](resources%2FKafka%2FScreenshot%202024-03-07%20at%201.05.27%E2%80%AFAM.png)
- ![Screenshot 2024-03-07 at 12.41.50 AM.png](resources%2FKafka%2FScreenshot%202024-03-07%20at%2012.41.50%E2%80%AFAM.png)

1. Max space of a Topic = 1 machine space
2. Parallel consumption from one service is not possible as FIFO needs to be followed

![Screenshot 2024-03-07 at 1.48.33 AM.png](resources%2FKafka%2FScreenshot%202024-03-07%20at%201.48.33%E2%80%AFAM.png)

![Screenshot 2024-03-07 at 1.49.13 AM.png](resources%2FKafka%2FScreenshot%202024-03-07%20at%201.49.13%E2%80%AFAM.png)

![Screenshot 2024-03-07 at 1.51.07 AM.png](resources%2FKafka%2FScreenshot%202024-03-07%20at%201.51.07%E2%80%AFAM.png)

Ex:
![Screenshot 2024-03-07 at 1.53.04 AM.png](resources%2FKafka%2FScreenshot%202024-03-07%20at%201.53.04%E2%80%AFAM.png)

![Screenshot 2024-03-07 at 1.54.16 AM.png](resources%2FKafka%2FScreenshot%202024-03-07%20at%201.54.16%E2%80%AFAM.png)

![Screenshot 2024-03-07 at 1.57.20 AM.png](resources%2FKafka%2FScreenshot%202024-03-07%20at%201.57.20%E2%80%AFAM.png)

[Zookeeper_Kafka.pdf](resources%2FZookeeper%2FZookeeper_Kafka.pdf)















