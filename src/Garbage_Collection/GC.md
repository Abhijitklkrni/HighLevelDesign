
- RAM is limited and JVM manages the memory automatically. 
- It is the process of looking at heap memory, identifying which objects are in use and which are not, and deleting the unused objects.
- An in use object, or a referenced object, means that some part of your program still maintains a pointer to that object.
- An unused object, or unreferenced object, is no longer referenced by any part of your program. So the memory used by an unreferenced object can be reclaimed.
- Each thread has its own stack, and the stack holds the reference to the objects.
- 
![Screenshot 2024-08-05 at 10.34.51 PM.png](images%2FScreenshot%202024-08-05%20at%2010.34.51%E2%80%AFPM.png)

![Screenshot 2024-08-05 at 10.35.17 PM.png](images%2FScreenshot%202024-08-05%20at%2010.35.17%E2%80%AFPM.png)


1. Strong Reference - all refs we create
2. Weak Reference - WeakReference<Person> weakPerson = new WeakReference<>(new Person());
   - WeakReference is used to create a reference that isn't strong enough to keep an object in memory.
   - When the garbage collector runs, it's allowed to clear out WeakReference objects and reclaim the memory that was being held by them.
   - WeakReference is useful for creating caches or other data structures that need to be garbage collected when they're not in use.
   - When tried to access the object, it will return null. after garbage collection.
   
3. Soft Reference - SoftReference<Person> softPerson = new SoftReference<>(new Person());
    - SoftReference is used to create a reference that is cleared out by the garbage collector only in response to memory demand.
    - SoftReference is useful for caches that should be kept in memory as long as there is enough memory available.
    - When tried to access the object, it will return the object. after garbage collection.

HEAP MEMORY
- Non Heap Memory - PermGen before Java 8, Metaspace after Java 8

- Young Generation
  - EDEN, S0, S1 (Survivor Spaces)
- Old Generation
![Screenshot 2024-08-05 at 11.18.34 PM.png](images%2FScreenshot%202024-08-05%20at%2011.18.34%E2%80%AFPM.png)

- Young Generation - Minor Garbage Collection (more frequent)
  - New objects are created in the Eden space.
  - After the garbage collection, the objects that are still in use are moved to the survivor spaces.
  - The objects that are not in use are deleted.
  - The objects that are moved to the survivor spaces have an age of 1.
  - After each garbage collection, the age of the objects is incremented by 1.
  - If an object survives multiple garbage collections, it is moved to the old generation.

- Old Generation - Major Garbage Collection (less frequent)
  - The objects that are moved to the old generation are garbage collected less frequently.
  - The garbage collection in the old generation is called a major garbage collection.
  - The major garbage collection is more time-consuming than the minor garbage collection.
  - The major garbage collection is triggered when the old generation is full.
  - The major garbage collection is also called a full garbage collection.

- Permanent Generation (PermGen) - Metaspace
   - PermGen is used to store the metadata of the classes.
   - examples of metadata are the class name, methods, variables, and the bytecode of the methods.
  
**Garbage Collection Algorithms**
Mark and Sweep
- Mark - Mark the objects that are in use.
- Sweep - Delete the objects that are not in use.

Mark and sweep with Compaction
- After the sweep phase, the objects that are in use are moved to one end of the heap.
- The sweep phase is followed by the compaction phase.

App threads stop during garbage collection
- Serial Garbage Collector
  - Single-threaded garbage collector( -XX:+UseSerialGC) - 1 thread for minor and one thread for major garbage collection 
- Parallel Garbage Collector
  - Multiple threads for minor and major garbage collection (-XX:+UseParallelGC)
- CMS Garbage Collector
  - Concurrent Mark Sweep Garbage Collector (-XX:+UseConcMarkSweepGC)
  - The CMS garbage collector is used to minimize the pause time.
  - The CMS garbage collector runs concurrently with the application threads.
- G1 Garbage Collector
- Z Garbage Collector



































