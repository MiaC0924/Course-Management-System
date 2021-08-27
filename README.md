# Comp3004 Project - Team 4

### Team 4 member: ###
Name          | Prefer        | GitHub ID
:------------ | :-----------: | :----------
Wenwen Chen   | Mia           | MiaC0924
Qinming Guo   | Allen         | qinmingguo
Xiangfei Kong | Kevin         | DeusPio
Chao Wu       | Charles       | ChaoWu0405

---------
### How to run the "HelloWorld" demo: ###
1. clone repositories by HTTP URL <pre> https://github.com/MiaC0924/3004Project.git </pre>
2. Have your local mongoDB run
   - You could download mongoDB here: https://www.mongodb.com/try/download/community
   - Run the mongo database shell
   - Connect the project database  
      •	The mongoDB uri is mongodb://Localhost:27017/CMS
3. open "master" branch  
   `$ git checkout master`
4. run "CmsApplication.java", which could be found by  
   `/3004Project/src/main/java/com/TeamProject/CmsApplication.java`
2. open below link in your browser <pre>http://localhost:8080/index.html</pre>

--------
### Introduction of classes packaging: ###
1. Person  
   - *Person*  
   - Professor  
   - Student  
   - Admin   
   
2. Course - Abstract Factory Design Pattern  
   - *CourseFactory*  
      - CompFactory,  
      - MathFactory, etc.
   - *Lab*
   - *Tutorial*
   - *PreCondition*
   - *Credit*
   - Course
   - University
   - Department (ComputerScience, Mathematics, etc.)
   - Subject
   
3. Evaluator - Visitor Design Pattern
   - *Visitor*
      - majorVisitor
      - overallVisitor
   - *Visitable*
   
4. DataAnalyst - Strategy/Decorator/Composite Design Pattern
   - StrategyController
   - *Strategy*
      - DataStrategy
      - AverageStrategy
   - *CompositeStrategy*
   - *DecoratorStrategy*
      - OnlyMaleDecorator
      - OnlyFemaleDecorator
   - Data

5. Observer - Observer Design Pattern
   - *Observer*
   - *Subject*
