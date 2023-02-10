## How to run application 
#### 1) Download zip file and unzip
#### 2) In IntelliJ select open project
#### 3) Select build.gradle file within branchTht project
#### 4) In the right hand corner you should see the application name BranchThtApplication, click play button. If this is not loaded for you, find BranchThtApplication.java. Command click and select Run 'BranchThtApplication'

## Main endpoint of application 
#### With application running head to Postman(use GET) or any browser and put in this api: http://localhost:8080/username/octocat
#### If done in browser, copy entire payload and paste into https://jsonformatter.org/json-pretty-print, hit make pretty button to allow payload to be easily read

## Thought Process Application
#### 1) After reading requirements of application I started to figure out how to split this up.
#### 2) I would need to get data from the given apis. Once I had the needed data, I would need to set up a service to orchestrate retrieval of data, formatting of data to requirements, handle exceptions and return correct data wanted.  
#### 3) I would need multiple layers to handle specific parts of the application: controller, service layer and dao. 
#### 4) I created these classes first by stubbing out and testing that I could do a simple end-to-end test. After successfully setting up my classes I started to setting up logic to get data, transform data, check if we got data and return proper data. 
#### 5) Also set up testing to individually test methods in service, mocking response of doa. 
#### 6) Tested end-to-end, code clean up/walk through 

## Bugs
#### Currently, one bug is present within the date format. Either my pattern is off or using SimpleDateFormat. Current bug effects hour section of time. returning 11 when should be 18 but did not want to spend too much time on this. I plan to try a bit longer after submission.  