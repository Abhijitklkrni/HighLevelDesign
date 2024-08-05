**SERIALIZATION**
***Java uses reflection to serialize and deserialize the object***
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

**EXTERNALIZATION**
- Serialize part of the object
- Externalizable interface is used to customize serialization
![Screenshot 2024-07-25 at 12.17.34 AM.png](images%2FScreenshot%202024-07-25%20at%2012.17.34%E2%80%AFAM.png)


![Screenshot 2024-07-25 at 12.18.11 AM.png](images%2FScreenshot%202024-07-25%20at%2012.18.11%E2%80%AFAM.png)

![Screenshot 2024-07-25 at 12.19.31 AM.png](images%2FScreenshot%202024-07-25%20at%2012.19.31%E2%80%AFAM.png)

![Screenshot 2024-07-25 at 12.49.35 AM.png](images%2FScreenshot%202024-07-25%20at%2012.49.35%E2%80%AFAM.png)

![Screenshot 2024-07-25 at 12.50.42 AM.png](images%2FScreenshot%202024-07-25%20at%2012.50.42%E2%80%AFAM.png)

![Screenshot 2024-07-25 at 12.51.24 AM.png](images%2FScreenshot%202024-07-25%20at%2012.51.24%E2%80%AFAM.png)

**SERIALVERSIONUID**
![Screenshot 2024-07-25 at 12.53.22 AM.png](images%2FScreenshot%202024-07-25%20at%2012.53.22%E2%80%AFAM.png)

![Screenshot 2024-07-25 at 12.55.06 AM.png](images%2FScreenshot%202024-07-25%20at%2012.55.06%E2%80%AFAM.png)

- SerialVersionUID is used to ensure that the same class (that is loaded) is used to deserialize the object
- If the serialVersionUID is not present, then the jvm will generate a serialVersionUID based on the class definition
- Serial version UID is used to maintain versioning of the class
- Java uses reflection to serialize and deserialize the object






