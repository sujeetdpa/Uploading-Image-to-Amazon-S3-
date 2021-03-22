# Uploading-Image-to-Amazon-S3-
A web application to upload and download the profile image of users to AmzonS3 Storage.

# How to Run the project
1.Open the project in suitable IDE like Eclipse or Intellij Idea and it will download all the jars required including amazon sdk.<br>
2.Change the Amazon S3 bucket name in src.main.java.buckets.Buckets package to your required  bucket name.<br>
3.Change the AmazonS3 credentials in src.main.java.config.AmazonConfig class to your amazon credential (the provided credential will not work).<br>
4.Now run the spring boot project.<br>
5.Go to the directory src/main/frontend and run the react app by (npm start command).<br>
6.In web browser open http://localhost:3000/  <br>

# Technology Used
1.Frontend - React<br>
2.Backend - Java,Spring Boot<br>
3.Cloud Service - Amazon S3.<br>

# Project related images
1.Before uploading image<br>
![Capture](https://user-images.githubusercontent.com/60792923/112029903-b6cd6480-8b5f-11eb-864a-f1f967c4901c.PNG)
2. After uploading image Amazon S3 Bucket content.<br>
![Capture3](https://user-images.githubusercontent.com/60792923/112030045-d9f81400-8b5f-11eb-9404-6a2a9bbf144e.PNG)
3. After downloading image<br>
4. ![Capture1](https://user-images.githubusercontent.com/60792923/112030145-f431f200-8b5f-11eb-8ba4-64f6bb222e51.PNG)
