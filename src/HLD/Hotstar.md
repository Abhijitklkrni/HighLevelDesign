

HOTSTAR

1. Ingestion - saving the video/file at Hotstar's end.
2. Discovery - Search in the saved/ingested videos
3.  STREAMING - Once the user plays the video

STATIC CONTENT - CDN (Bread and Butter)

Video - 
1. Codec - Encoding + Decoding (mp4,avi...)
2. Resolution - 480p,720p,1080p,4k and so on.. No. of pixels

###### Video is nothing but images changing quickly one after the other.
- fps - frames per second
- 20fps,30fps,60fps,120fps...
- More nos of fps smoother the video

![Screenshot 2024-03-16 at 12.38.52 PM.png](resources%2Fhotstar%2FScreenshot%202024-03-16%20at%2012.38.52%E2%80%AFPM.png)
Image - Is a 2D matrix of pixels
- [...]
- [...]
- [...]

# Each . represents a pixel and each pixel can be identified by
- if Black and white (0-255)
- If color - A pixel has three values[R,G,B] RED,GREEN,BLUE
- More no of pixels better the resolution
- 720p has 720 * 1280 
- 1080p has 1920 * 1080 - check web

##### Higher the RESOLUTION, Higher will be pixels and so the high storage requirements

MOVIE -> 2Hours,30fps , 720p, 255 is the value to be stored so 8 bits are required so 1Byte
2 * 60 * 60 * 30 * 720 * 1280 * 3 *  1Byte
~ 597GB :(
- This is a RAW video
- Apply compression - codec on top on it ()
- 500GB --> 2GB
- How this MAGIC ? Mostly the pictures one after the other are the same, hence during compression only 1 photo is saved.

##### Ingestion would be RAW Video only, streaming would be codec video
- Hotstar would need RAW video as it needs to tweak the files according to its use case
- Also compression are lossy
-  ![Screenshot 2024-03-16 at 12.38.52 PM.png](resources%2Fhotstar%2FScreenshot%202024-03-16%20at%2012.38.52%E2%80%AFPM.png)

1. Save RAW in BLOB 
2. ![Screenshot 2024-03-16 at 12.38.52 PM.png](resources%2Fhotstar%2FScreenshot%202024-03-16%20at%2012.38.52%E2%80%AFPM.png)

INGESTION PIPELINE FOLLOWS
- ![Screenshot 2024-03-16 at 4.22.19 PM.png](resources%2Fhotstar%2FScreenshot%202024-03-16%20at%204.22.19%E2%80%AFPM.png)
- ![Screenshot 2024-03-16 at 4.44.01 PM.png](resources%2Fhotstar%2FScreenshot%202024-03-16%20at%204.44.01%E2%80%AFPM.png)
1. Get the highest quality RAW file from the Movie producer save in S3(s3 by default creates chunks and saves, each chunk of 128MB by default)
   - ![Screenshot 2024-03-16 at 4.52.40 PM.png](resources%2Fhotstar%2FScreenshot%202024-03-16%20at%204.52.40%E2%80%AFPM.png)
   - ![Screenshot 2024-03-16 at 4.53.13 PM.png](resources%2Fhotstar%2FScreenshot%202024-03-16%20at%204.53.13%E2%80%AFPM.png)
   
2. Run content moderation service before converting into each resolution
   - ![Screenshot 2024-03-16 at 4.54.47 PM.png](resources%2Fhotstar%2FScreenshot%202024-03-16%20at%204.54.47%E2%80%AFPM.png)
3. Create all resolutions of raw file using 2. ( 144p raw file,720p raw file,1080p raw file and so on...) and save in S3 again
    - Produce the 2. file in Queue like kafka and have multiple consumer groups, where each consumer group will convert it into one resolution
    - ![Screenshot 2024-03-16 at 4.59.13 PM.png](resources%2Fhotstar%2FScreenshot%202024-03-16%20at%204.59.13%E2%80%AFPM.png)
    - The created video will be stored in S3 and its metadata will also be added  
4. Create codec of each resolution and save back in S3 again
    - ![Screenshot 2024-03-16 at 5.31.30 PM.png](resources%2Fhotstar%2FScreenshot%202024-03-16%20at%205.31.30%E2%80%AFPM.png)
    - Might have multiple consumer groups , one for .mp4 and one for avi
    - As for Hotstar if its client player has some issue with mp4 codec, it will send out avi format to the clients(rarely happens)
5. Logical Chunking :
   - All videos created till now were also in chunked and stored in S3(internally).
   - But the chunks created by S3 might not be ideal for data transfer to client
   - ![Screenshot 2024-03-16 at 5.35.32 PM.png](resources%2Fhotstar%2FScreenshot%202024-03-16%20at%205.35.32%E2%80%AFPM.png)
   - Why chunking twice(s3 internally and step 5)?
     - The chunk size I decide for optimized transfer to client might not match the chunk created by S3
##### Live video is actually not live, as it needs to go through all these ingestion system, Live will be less delayed as it would cut few steps
##### That is why in live, few contents might get through the content moderation and then later, Youtube catches it deletes it

![Screenshot 2024-03-16 at 6.27.36 PM.png](resources%2Fhotstar%2FScreenshot%202024-03-16%20at%206.27.36%E2%80%AFPM.png)

![Screenshot 2024-03-16 at 6.29.08 PM.png](resources%2Fhotstar%2FScreenshot%202024-03-16%20at%206.29.08%E2%80%AFPM.png)

##### ALL OTT performance is all dependent on CDN

# DISCOVERABILITY
- ![Screenshot 2024-03-16 at 6.32.04 PM.png](resources%2Fhotstar%2FScreenshot%202024-03-16%20at%206.32.04%E2%80%AFPM.png)
![Screenshot 2024-03-16 at 6.33.29 PM.png](resources%2Fhotstar%2FScreenshot%202024-03-16%20at%206.33.29%E2%80%AFPM.png)
![Screenshot 2024-03-16 at 6.34.50 PM.png](resources%2Fhotstar%2FScreenshot%202024-03-16%20at%206.34.50%E2%80%AFPM.png)


# STREAMING
![Screenshot 2024-03-16 at 6.36.43 PM.png](resources%2Fhotstar%2FScreenshot%202024-03-16%20at%206.36.43%E2%80%AFPM.png)

#### Adaptive bitrate streaming
![Screenshot 2024-03-16 at 6.40.36 PM.png](resources%2Fhotstar%2FScreenshot%202024-03-16%20at%206.40.36%E2%80%AFPM.png)


![Screenshot 2024-03-16 at 6.42.43 PM.png](resources%2Fhotstar%2FScreenshot%202024-03-16%20at%206.42.43%E2%80%AFPM.png)

After selecting one particular video, but before playing it. Load all metadata right after you get into that video page with few chunks loaded even before clicking play button
![Screenshot 2024-03-16 at 6.45.31 PM.png](resources%2Fhotstar%2FScreenshot%202024-03-16%20at%206.45.31%E2%80%AFPM.png)

![Screenshot 2024-03-16 at 6.47.23 PM.png](resources%2Fhotstar%2FScreenshot%202024-03-16%20at%206.47.23%E2%80%AFPM.png)

Companies do some data analysis saying this video is usually starts from 5th chunks.
- So why not load 5,6, 7th chunk as well pre emptively

![Screenshot 2024-03-16 at 6.53.03 PM.png](resources%2Fhotstar%2FScreenshot%202024-03-16%20at%206.53.03%E2%80%AFPM.png)
Cache as much as client machine can.- LRU or some kind of cache

##### Q: How much to future load?
- My future loading will depend on device fingerprinting done before

If the cache size is low, the content might gets kicked out and if you seek back to the watched content, you might have to reload
![Screenshot 2024-03-16 at 6.57.26 PM.png](resources%2Fhotstar%2FScreenshot%202024-03-16%20at%206.57.26%E2%80%AFPM.png)


Things | concepts which enable free streaming
#### Chunking
   - watching without downloading
   - watch without having full movie size free space

#### Pre-emptive loading

#### Browser Caching / Client side caching
   - only coz of this pre emptive loading

#### CDN

#### Server side cache
   - popular movies - cache meta data

#### Adaptive Bitrate Streaming
   - Depending on the bandwidth of the user, pre emptive loading of future chunks can be decided which resolution can be downloaded
   - How does Hotstar know my bandwidth? On the requests its sending, time taken to play each chunk and so on
   
Q: If I move to the end of the chunk which is not yet loaded, will it load the whole chunk or last few seconds only ?
![Screenshot 2024-03-16 at 7.08.04 PM.png](resources%2Fhotstar%2FScreenshot%202024-03-16%20at%207.08.04%E2%80%AFPM.png)
- Whole chunk will have to be downloaded

Q:

![Screenshot 2024-03-16 at 7.12.08 PM.png](resources%2Fhotstar%2FScreenshot%202024-03-16%20at%207.12.08%E2%80%AFPM.png)


















