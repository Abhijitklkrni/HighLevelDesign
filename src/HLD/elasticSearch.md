### ELASTIC SEARCH
![Screenshot 2024-03-07 at 10.42.41 PM.png](resources%2FelasticSearch%2FScreenshot%202024-03-07%20at%2010.42.41%E2%80%AFPM.png)

![Screenshot 2024-03-07 at 10.44.15 PM.png](resources%2FelasticSearch%2FScreenshot%202024-03-07%20at%2010.44.15%E2%80%AFPM.png)

![Screenshot 2024-03-07 at 10.44.59 PM.png](resources%2FelasticSearch%2FScreenshot%202024-03-07%20at%2010.44.59%E2%80%AFPM.png)

- SQL will be very un optimal

![Screenshot 2024-03-07 at 10.48.59 PM.png](resources%2FelasticSearch%2FScreenshot%202024-03-07%20at%2010.48.59%E2%80%AFPM.png)
- Mongo supports full text search - internally uses Elastic search
- Elastic search is also a Document DB with added features


## FIRST PRINCIPLES
- Inverse Index
- Original content is stored as is it, copy is used to do the processing,
![Screenshot 2024-03-07 at 10.51.22 PM.png](resources%2FelasticSearch%2FScreenshot%202024-03-07%20at%2010.51.22%E2%80%AFPM.png)

![Screenshot 2024-03-07 at 10.55.27 PM.png](resources%2FelasticSearch%2FScreenshot%202024-03-07%20at%2010.55.27%E2%80%AFPM.png)
NLP MODEL - Natural Language processing
- teach computer human language

### How to create Inverted index ?
#### APACHE LUCENE
1. Remove stop words
2. Stemming - keeping only the root word
![Screenshot 2024-03-07 at 11.01.30 PM.png](resources%2FelasticSearch%2FScreenshot%202024-03-07%20at%2011.01.30%E2%80%AFPM.png)

3. Tokenization - List of tokens at the end
- ![Screenshot 2024-03-07 at 11.04.28 PM.png](resources%2FelasticSearch%2FScreenshot%202024-03-07%20at%2011.04.28%E2%80%AFPM.png)
- Depending on processing power and space and money
![Screenshot 2024-03-07 at 11.06.09 PM.png](resources%2FelasticSearch%2FScreenshot%202024-03-07%20at%2011.06.09%E2%80%AFPM.png)

Bi - skip- grams : Skip 1 in between and have 2 words
Tri- skip - grams : Skip 1 in between and have 3 words

![Screenshot 2024-03-07 at 11.10.25 PM.png](resources%2FelasticSearch%2FScreenshot%202024-03-07%20at%2011.10.25%E2%80%AFPM.png)

![Screenshot 2024-03-07 at 11.11.52 PM.png](resources%2FelasticSearch%2FScreenshot%202024-03-07%20at%2011.11.52%E2%80%AFPM.png)

- All posts will have its list of tokens given by Apache Lucene
- Example - all linked in posts will have its list of token format stored separately

- ![Screenshot 2024-03-07 at 11.13.33 PM.png](resources%2FelasticSearch%2FScreenshot%202024-03-07%20at%2011.13.33%E2%80%AFPM.png)

### Some black box
- All tokens will be given its importance
- If a word is occurring in almost all the posts then its importance will be very low, as it would not help in identifying the unique posts
- That is why we remove the stop words as well
- Tf IDf - read more
![Screenshot 2024-03-07 at 11.17.29 PM.png](resources%2FelasticSearch%2FScreenshot%202024-03-07%20at%2011.17.29%E2%80%AFPM.png)

![Screenshot 2024-03-07 at 11.19.10 PM.png](resources%2FelasticSearch%2FScreenshot%202024-03-07%20at%2011.19.10%E2%80%AFPM.png)

![Screenshot 2024-03-07 at 11.20.04 PM.png](resources%2FelasticSearch%2FScreenshot%202024-03-07%20at%2011.20.04%E2%80%AFPM.png)


###### 20/12/2023

APACHE LUCENE - Gets us Inverted indexes
![Screenshot 2024-03-07 at 11.32.57 PM.png](resources%2FelasticSearch%2FScreenshot%202024-03-07%20at%2011.32.57%E2%80%AFPM.png)

![Screenshot 2024-03-07 at 11.33.45 PM.png](resources%2FelasticSearch%2FScreenshot%202024-03-07%20at%2011.33.45%E2%80%AFPM.png)
- Terms with the highest score helps identify that doc
- Splunk is also same
LLM - Large Language Model (ChatGPT)

Inverted index - union of all list of tokens
and each token as key and value as [] of docs which has the keys

- Score will be given by lucene, 0 - nil. 1 is high
![Screenshot 2024-03-07 at 11.40.02 PM.png](resources%2FelasticSearch%2FScreenshot%202024-03-07%20at%2011.40.02%E2%80%AFPM.png)

EX:
![Screenshot 2024-03-07 at 11.42.31 PM.png](resources%2FelasticSearch%2FScreenshot%202024-03-07%20at%2011.42.31%E2%80%AFPM.png)

Rank the docs and return 
![Screenshot 2024-03-07 at 11.45.10 PM.png](resources%2FelasticSearch%2FScreenshot%202024-03-07%20at%2011.45.10%E2%80%AFPM.png)

LUCENE
![Screenshot 2024-03-07 at 11.45.49 PM.png](resources%2FelasticSearch%2FScreenshot%202024-03-07%20at%2011.45.49%E2%80%AFPM.png)


ELASTIC SEARCH
- Scalable LUCENE

 ![Screenshot 2024-03-07 at 11.47.42 PM.png](resources%2FelasticSearch%2FScreenshot%202024-03-07%20at%2011.47.42%E2%80%AFPM.png)

![Screenshot 2024-03-07 at 11.48.56 PM.png](resources%2FelasticSearch%2FScreenshot%202024-03-07%20at%2011.48.56%E2%80%AFPM.png)

Docs - 10B
Lists - 10B
Inverted index - 1

![Screenshot 2024-03-07 at 11.58.11 PM.png](resources%2FelasticSearch%2FScreenshot%202024-03-07%20at%2011.58.11%E2%80%AFPM.png)

![Screenshot 2024-03-08 at 12.01.47 AM.png](resources%2FelasticSearch%2FScreenshot%202024-03-08%20at%2012.01.47%E2%80%AFAM.png)


DETOUR - green:Logical, black:Physical
![Screenshot 2024-03-08 at 12.05.56 AM.png](resources%2FelasticSearch%2FScreenshot%202024-03-08%20at%2012.05.56%E2%80%AFAM.png)

![Screenshot 2024-03-08 at 12.07.36 AM.png](resources%2FelasticSearch%2FScreenshot%202024-03-08%20at%2012.07.36%E2%80%AFAM.png)

![Screenshot 2024-03-08 at 12.08.07 AM.png](resources%2FelasticSearch%2FScreenshot%202024-03-08%20at%2012.08.07%E2%80%AFAM.png)


![Screenshot 2024-03-08 at 12.10.04 AM.png](resources%2FelasticSearch%2FScreenshot%202024-03-08%20at%2012.10.04%E2%80%AFAM.png)

![Screenshot 2024-03-08 at 12.11.22 AM.png](resources%2FelasticSearch%2FScreenshot%202024-03-08%20at%2012.11.22%E2%80%AFAM.png)

![Screenshot 2024-03-08 at 12.13.06 AM.png](resources%2FelasticSearch%2FScreenshot%202024-03-08%20at%2012.13.06%E2%80%AFAM.png)

Elastic search using the above logic
- ![Screenshot 2024-03-08 at 12.16.23 AM.png](resources%2FelasticSearch%2FScreenshot%202024-03-08%20at%2012.16.23%E2%80%AFAM.png)
- Each shard = inverted index
- 1 physical machine = many logical shards
- If we want to scale buy more physical machines
- We need not recompute inverted indexes, just move the logical shard with its inverted index to the new physical machine
- ![Screenshot 2024-03-08 at 12.21.45 AM.png](resources%2FelasticSearch%2FScreenshot%202024-03-08%20at%2012.21.45%E2%80%AFAM.png)
![Screenshot 2024-03-08 at 12.22.07 AM.png](resources%2FelasticSearch%2FScreenshot%202024-03-08%20at%2012.22.07%E2%80%AFAM.png)
![Screenshot 2024-03-08 at 12.22.42 AM.png](resources%2FelasticSearch%2FScreenshot%202024-03-08%20at%2012.22.42%E2%80%AFAM.png)

https://www.scaler.com/academy/mentee-dashboard/class/147609/session?joinSession=1
- 2.30 for read query flow
- 2.38














