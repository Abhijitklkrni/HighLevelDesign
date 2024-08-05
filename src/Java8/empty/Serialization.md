**SERIALIZATION**

- Object to byte stream
- Byte stream to Object

- implements Serializable is compulsory or else NotSerializableException
- serialVersionUID is used to ensure that the same class (that is loaded) is used to deserialize the object
- transient keyword is used to avoid serialization of a field (private transient String password)
- static fields are not serialized
- static transient does not make sense
- Externalizable interface is used to customize serialization
- FileInputStream and FileOutputStream are used to read and write objects
- FileInputStream is passed to ObjectInputStream and FileOutputStream is passed to ObjectOutputStream
- ObjectInputStream and ObjectOutputStream are used to read and write objects
- ObjectInputStream.readObject() and ObjectOutputStream.writeObject() are used to read and write objects

- ![Screenshot 2024-07-24 at 11.56.20 PM.png](images%2FScreenshot%202024-07-24%20at%2011.56.20%E2%80%AFPM.png)
![Screenshot 2024-07-24 at 11.58.17 PM.png](images%2FScreenshot%202024-07-24%20at%2011.58.17%E2%80%AFPM.png)

Custom Serialization:
![Screenshot 2024-07-25 at 12.03.53 AM.png](images%2FScreenshot%202024-07-25%20at%2012.03.53%E2%80%AFAM.png)

![Screenshot 2024-07-25 at 12.05.00 AM.png](images%2FScreenshot%202024-07-25%20at%2012.05.00%E2%80%AFAM.png)

![Screenshot 2024-07-25 at 12.05.53 AM.png](images%2FScreenshot%202024-07-25%20at%2012.05.53%E2%80%AFAM.png)

![Screenshot 2024-07-25 at 12.09.36 AM.png](images%2FScreenshot%202024-07-25%20at%2012.09.36%E2%80%AFAM.png)
 
- If parent is not serializable, and while deserializing child, jvm tries to create the object of parent, then parent no arg constructor will be called

- If parent is serializable, then parent constructor will not be called while deserializing child

![Screenshot 2024-07-25 at 12.12.37 AM.png](images%2FScreenshot%202024-07-25%20at%2012.12.37%E2%80%AFAM.png)
- bcoz parent is not serializable, so parent constructor is called and it try to retrieve the value of parent class, and i value will remain 10 and not 200.

- if parent is serializable, then parent constructor will not be called and i value will be 200


