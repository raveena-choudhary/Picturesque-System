### Picturesque system



### Dependencies
- Node.js v16.13.2 https://nodejs.org/en/
- Steps to install apache maven in windows: https://mkyong.com/maven/how-to-install-maven-in-windows/
- Steps to install maven in eclipse : https://www.toolsqa.com/maven/how-to-install-maven-eclipse-ide/
- All depedencies used in backend(java-maven project) is placed in pom.xml file: https://github.com/mikasa511/APP-Project/blob/master/backend/pom.xml
- To install dependencies locally: Go to project/backend and run maven command- mvn clean and then mvn install in eclipse or cmd. 


### API Used:
pexels photos - `https://api.pexels.com/v1/curated?page=1&per_page=50`

JSON Response : 
```json
{
  "page": 1,
  "per_page": 50,
  "photos": [
    {
      "id": 14367476,
      "width": 3955,
      "height": 5932,
      "url": "https://www.pexels.com/photo/sea-landscape-man-beach-14367476/",
      "photographer": "Feyza Yıldırım",
      "photographer_url": "https://www.pexels.com/@feyzayildirimphoto",
      "photographer_id": 50964441,
      "avg_color": "#C5CACD",
      "src": {
        "original": "https://images.pexels.com/photos/14367476/pexels-photo-14367476.jpeg",
        "large2x": "https://images.pexels.com/photos/14367476/pexels-photo-14367476.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
        "large": "https://images.pexels.com/photos/14367476/pexels-photo-14367476.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
        "medium": "https://images.pexels.com/photos/14367476/pexels-photo-14367476.jpeg?auto=compress&cs=tinysrgb&h=350",
        "small": "https://images.pexels.com/photos/14367476/pexels-photo-14367476.jpeg?auto=compress&cs=tinysrgb&h=130",
        "portrait": "https://images.pexels.com/photos/14367476/pexels-photo-14367476.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800",
        "landscape": "https://images.pexels.com/photos/14367476/pexels-photo-14367476.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=627&w=1200",
        "tiny": "https://images.pexels.com/photos/14367476/pexels-photo-14367476.jpeg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=200&w=280"
      },
      "liked": false,
      "alt": ""
    },
    {
      "id": 14331960,
      "width": 2505,
      "height": 3336,
      "url": "https://www.pexels.com/photo/woman-in-black-and-white-floral-long-sleeve-shirt-14331960/",
      "photographer": "Lany-Jade Mondou",
      "photographer_url": "https://www.pexels.com/@lany",
      "photographer_id": 135943481,
      "avg_color": "#29201B",
      "src": {
        "original": "https://images.pexels.com/photos/14331960/pexels-photo-14331960.jpeg",
        "large2x": "https://images.pexels.com/photos/14331960/pexels-photo-14331960.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
        "large": "https://images.pexels.com/photos/14331960/pexels-photo-14331960.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
        "medium": "https://images.pexels.com/photos/14331960/pexels-photo-14331960.jpeg?auto=compress&cs=tinysrgb&h=350",
        "small": "https://images.pexels.com/photos/14331960/pexels-photo-14331960.jpeg?auto=compress&cs=tinysrgb&h=130",
        "portrait": "https://images.pexels.com/photos/14331960/pexels-photo-14331960.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800",
        "landscape": "https://images.pexels.com/photos/14331960/pexels-photo-14331960.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=627&w=1200",
        "tiny": "https://images.pexels.com/photos/14331960/pexels-photo-14331960.jpeg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=200&w=280"
      },
      "liked": false,
      "alt": ""
    },
    ...
    ],
}
```

> Follow below steps to run and test the system

---
### Backend:

1. To run backend APIs, go to the ```PARENT_DIRECTORY\APP-Project\backend``` and please up the tomcat server using the command in command line : 
```
mvn tomcat7:run
```

2. To check server is successfully started, make a request in browser: http://localhost:8080/photo-api/get-photographers-with-photos. User will be able to see some JSON response ( no need to bother about response). Please find the below screenshot for reference:
![image](https://user-images.githubusercontent.com/52369694/201550615-fab31c92-b3d9-4a44-9080-9899a1bedcb9.png)

---
### Frontend:
1. Run frontend on server using the command in command line :
```
npm install
npm start
```
2. Open any browser and open ```http://localhost:3000/```. You will be able to see below screen.
![image](https://user-images.githubusercontent.com/116931930/201552113-47e78f49-4164-4e2c-9ee2-aa78a78eb994.png)

---
### Testing:
Please Note: Tomcat server should up and running to run the tests green. Running tests will only do data insertion and deletion but will not up the server.
Server should be run by running command in cmd as mentioned in above steps.

1. Open command line in backend folder and run commands: 
```
mvn clean
```
--> this must should print BUILD SUCCESS message. Then run 
```
mvn install
```
--> this will run all the tests.

2. Tests are written using `Rest Assured` and `TestNG` tools in Java
3. To run test in IDE, you can use eclipse and install TestNG from eclipse marketplace. Once successfully installed, select the tests folder(src/test/java) on right click you should be able to see option to run tests as "TestNG tests".

In command line:
![image](https://user-images.githubusercontent.com/52369694/201550845-ff327544-dd37-479f-8e98-8c4e826e50a0.png)

In eclipse:
![image](https://user-images.githubusercontent.com/52369694/201550933-3a77cbed-ab85-4ad7-a7a4-31b8364d6e42.png)
=======
# Project-APP
Picturesque System

>>>>>>> f3c35c4a73fe5ce663815573c02ee5f3f99cda72
