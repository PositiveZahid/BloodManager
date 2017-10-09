# Blood Manager

A workshop on Advanced Java Programming organized by Center for Cognitive Skill Enhancement (CCSE), a sub-project of Higher Education Quality Enhancement Project (HEQEP) which is a project of University Grants Commission of Bangladesh, UGC, was held at IUB from 1st to 3rd October 2015.
In the workshop, participants learned about a new framework to code Android applications. The three day workshop started with the core concepts of Object Oriented Programming which is a prerequisite for the main concentration of the workshop, the powerful 3-Tier Programming Architecture. Using this 3-Tier Programming framework, participants of the workshop will now be able to create quality applications with comparatively very little effort. At the end of the workshop participants were put to test their Java programming skills by solving real-world problems using Android and the 3-Tier Programming framework.
The workshop was conducted by Md. Rakibul Alam, Shabnam Shahreen Sifat, Aunnoy K Mutasim and Md. Nazmul Hoq. Rakib, Sifat and Aunnoy are currently working as Junior Lecturers for the Department of Computer Science and Engineering, Independent University, Bangladesh and Nazmul is currently working as a Lecturer for the Department of Computer Science and Engineering, Daffodil International University. They were all nominated for a Training Program on Java and Maintenance organized by Bangladesh Hi-Tech Park Authority through the World Bank financed project “Support to Development of Kaliakoir Hi-Tech Park (SDKHTP)” under ICT Division. The training was conducted by INFOSYS LTD (India’s third largest IT Company). In that extensive and highly demanding training all of them did exceptionally well and stood among the top 10 participants for their outstanding performances.


# 3-tier architecture


We will use a 3-tier architecture to build our Android Application which is similar to standards often followed to build enterprise java applications.
These tiers are - 
•	Persistence
•	Business
•	Presentation

The possible communication flows between the layers are represented by:

Persistence<->Business<->Presentation


It means that the presentation layer never calls or performs persistence operations; it always does it through the business layer. This architecture is meant to fulfill the demands of a high availability android application.
Persistence
Only this tier talks to the database. Any persistence operation need to be done in the application is performed here. In our case, our persistence medium will be SQLite which is a file based DBMS and we are using SQLite Library for Android. This layer may be divided into multiple classes, where each class deals with a certain type of activities. 

Business
All logic which is tied to the android application functionality is located in this layer. This functionality could be initiating a money transfer for a customer who wants to pay for a product using her/his credit card. It could just as well be creating a new user, deleting a user or calculating the outcome of any task in an android based application.
This layer is divided into multiple classes and each of these classes has different tasks to do. Generally, this tier has following types of classes- 

•	Manager class
•	Transfer Object classes
•	Validation Class

Presentation
Our presentation layer is in charge of... presentation! It's responsible for the user interface and shows information to the user. In our case, we are going to use Android’s Layout XML and other resources to build the presentation layer.
The layer calls methods in managers of the business layer to perform operations requested by the user and to receive information to show in the application. 
