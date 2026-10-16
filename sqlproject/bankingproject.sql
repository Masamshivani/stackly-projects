use sqlassignment;
CREATE TABLE customers ( customer_id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), city VARCHAR(50) );
CREATE TABLE accounts ( account_id INT PRIMARY KEY AUTO_INCREMENT, customer_id INT, account_type VARCHAR(20), balance DECIMAL(10,2), FOREIGN KEY (customer_id) REFERENCES customers(customer_id) );

insert into customers(name,city) values('shivani','hyderabad'),
('vaishnavi','mumbai'),('karni','chennai'),('harsha','bangalore'),('jansi','chennai'),('varsha','vizag'),
('sindhuja','chennai'),('girija','coimbatore'),('manisha','mumbai'),('varsha','hyderabad'),('karuna','madhurai'),
('jithesh','selam'),('grishma','coimbatore'),('tejashwini','selam'),('meenakshi','madhurai'),('apoorva','chennai'),
('chandhana','selam'),('srija','bangalore'),('premala','coimbatore'),('jithesh','hyderabad'),('jhansi','vizag'),
('preethi','mysore'),('pravalika','mumbai'),('deekshu','mumbai'),('pranavi','mysore'),('loki','bangalore'),
('vishnu','madhurai');

insert into accounts(customer_id,account_type,balance) values(1,'savings',40000),
(1,'current',25000),(2,'savings',10000),(3,'current',16000),(4,'current',30000),(4,'savings',35000),
(5,'savings',50000),(6,'savings',20000),(6,'current',25000),(7,'savings',7000),(8,'current',60000),
(9,'savings',40000),(10,'current',57000),(11,'savings',45000),(12,'current',50000),(13,'savings',70000),
(14,'current',40000),(15,'savings',46000),(16,'savings',65000),(17,'current',30000),(17,'savings',40000),
(18,'current',37000),(18,'savings',40000),(19,'savings',60000),(20,'current',20000),(21,'current',15000),
(22,'current',18000),(23,'savings',25000),(24,'current',22000),(25,'savings',30000),(25,'current',33000),
(26,'current',40000),(27,'savings',50000);

#basic+WHERE+operators


#1. Retrieve all accounts with balance greater than 20,000.
select *from accounts where balance>20000;

#2. Find customers who live in Chennai.
select *from customers where city='chennai';

#3. Display accounts with balance between 20,000 and 50,000.
select *from accounts where balance between 20000 and 50000;

#4. Find customers whose names start with 'J'.
select *from customers where name like 'j%';

#5. Retrieve accounts of type 'Savings' or 'Current'.
select *from accounts where account_type in('savings','current');

#6. Display accounts that are not 'Savings'.
select *from accounts where not account_type='savings';

#7. Find customers whose names contain the letter 'a'.
select *from customers where name like '%a%';

#8. Retrieve accounts with balance less than or equal to 30,000.
select *from accounts where balance<=30000;

#9. Find customers who are not from Madurai.
select *from customers where city='madhurai';

#10. Display accounts where balance is not between 10,000 and 40,000.
select *from accounts where balance between 10000 and 40000;

#11. Retrieve customers whose names end with 'i'.
select *from customers where name like '%i';

#12. Find accounts with balance equal to 50,000.
select *from accounts where balance=50000;

#13. Display customers whose city is either Chennai or Salem.
select *from customers where city in('chennai','selam');

#14. Find accounts with balance greater than 10,000 and less than 40,000.
select *from accounts where balance between 10000 and 40000;

#15. Retrieve accounts where account type is not in ('Current').
select * from accounts where not account_type='current';

#order by

#16. Display all accounts sorted by balance in descending order.
select *from accounts order by balance desc;

#17. List customers sorted alphabetically by name.
select name from customers order by name;

#18. Display accounts sorted by account type and then by balance (descending).
select *from accounts order by account_type asc,balance desc;

#aggregate functions

#19. Find the total balance of all accounts.
select sum(balance) as total_balance from accounts;
 
#20. Calculate the average balance of accounts.
select round(avg(balance),2) as average_balance from accounts;

#21. Find the maximum account balance.
select max(balance) as maximum_balance from accounts;

#22. Find the minimum account balance.
select min(balance) as minimum_balance from accounts;

#23. Count the total number of customers.
select count(customer_id) as customers_count from customers;

#group by+having

#24. Find total balance grouped by account type.
select account_type,sum(balance) as balance_sum 
from accounts group by account_type;

#25. Find average balance for each account type.
select account_type,round(avg(balance),2) as average_balance
from accounts group by account_type;

#26. Display account types having average balance greater than 20,000.
select account_type,round(avg(balance),2) as average_balance
from accounts group by account_type
having round(avg(balance),2)>20000;

#27. Count number of accounts for each customer.
select customer_id,count(customer_id) as count
from accounts
group by customer_id;

#28. Display customers having more than one account.
select customer_id,count(customer_id) as count
from accounts
group by customer_id
having count(customer_id)>1
order by customer_id;

#JOINS

#29. Retrieve customer names along with their account balances.
select c.customer_id,c.name,sum(a.balance) as total_balance
from customers c
join accounts a
on c.customer_id=a.customer_id
group by c.customer_id,c.name;
 
#30. Display all customers and their accounts (including customers without accounts).
select c.customer_id,c.name,a.account_type,a.balance,c.city
from customers c join accounts a
on c.customer_id=a.customer_id;

#31. Display all accounts and corresponding customer details.
select c.name,a.account_type
from customers c join accounts a
on c.customer_id=a.customer_id;

#32. Retrieve customer names and account types where balance is greater than 20,000.
select c.name,a.account_type
from customers c
join accounts a
on c.customer_id=a.customer_id
where a.balance>20000;

#33. List customers with their total balance using JOIN.
select c.customer_id,c.name,a.balance
from customers c
join accounts a
on c.customer_id=a.customer_id;

#34. Display customer names and balances sorted by balance.
select c.customer_id,c.name,a.balance
from customers c
join accounts a
on c.customer_id=a.customer_id
order by a.balance desc;

#35. Count number of accounts for each city using JOIN.
select c.city,count(a.account_id) as count
from customers c
join accounts a
on c.customer_id=a.customer_id
group by c.city;

#SUBQUERIES

#36. Find accounts with balance greater than average balance.
select *from accounts where balance>(select avg(balance) from accounts);

#37. Retrieve customers who have accounts.
SELECT DISTINCT c.name
FROM customers c
INNER JOIN accounts a
ON c.customer_id = a.customer_id;


#38. Find customers who do not have any accounts.
SELECT c.name
FROM customers c
LEFT JOIN accounts a
ON c.customer_id = a.customer_id
where a.account_id is null;

#39. Display account(s) with the maximum balance.
select *from accounts
where balance=(select max(balance) from accounts);

#40. Find customers whose total balance is greater than 40,000.
select c.customer_id,c.name,sum(a.balance) as total_balance
from customers c
join accounts a
on c.customer_id=a.customer_id
group by c.customer_id,c.name
having sum(a.balance)>40000;






CREATE TABLE trains (train_id INT PRIMARY KEY AUTO_INCREMENT,
train_name VARCHAR(50), 
source VARCHAR(50), 
destination VARCHAR(50));

CREATE TABLE bookings ( booking_id INT PRIMARY KEY AUTO_INCREMENT, 
train_id INT, passenger_name VARCHAR(50), 
fare DECIMAL(10,2), status VARCHAR(20), 
FOREIGN KEY (train_id) REFERENCES trains(train_id) );

INSERT INTO trains (train_name, source, destination) VALUES ('Express1','Chennai','Madurai'), 
('Express2','Coimbatore','Salem'), 
('Express3','Madurai','Chennai'),
('Express4','Bangalore','Mysore'),
('Express5','Hyderabad','Nizamabad');

INSERT INTO bookings (train_id, passenger_name, fare, status) VALUES (1,'rupa',500,'Confirmed'), 
(1,'shivani',500,'Waiting'), 
(2,'Priyanka',300,'Confirmed'), 
(3,'rajesh',450,'Cancelled'), 
(2,'mahesh',300,'Confirmed'),
(4,'Krishna',650,'Confirmed'),
(4,'Vignesh',600,'Confirmed'),
(5,'Taruni',550,'Waiting'),
(5,'anvi',550,'Confirmed'),
(5,'Vishnu',550,'Cancelled'),
(3,'navya',450,'Confirmed');

#Basic+where+Operators

#retrieving data all records from trains table
select *from trains;

#retrieving data all records from trains table
select *from bookings;

#Basic + WHERE + Operators

#41. Retrieve all bookings with fare greater than 400.
select *from bookings
where fare>400;

#42. Find bookings where status is not 'Confirmed'.
select *from bookings 
where not status='Confirmed';

#43. Display trains starting from Chennai.
select *from trains
where source='Chennai';

#44. Retrieve bookings with fare between 300 and 500.
select *from bookings
where fare between 300 and 500;

#45. Find passengers whose names start with 'A'.
select passenger_name
from bookings where passenger_name like 'A%';

#Joins+group by+order by

#46. Retrieve train names along with passenger names.
select t.train_name,t.source,t.destination,
b.passenger_name,b.fare
from trains t
join bookings b 
on t.train_id=b.train_id;

#47. Count number of bookings for each train.
select t.train_id,t.train_name,count(b.booking_id) as booking_count
from trains t
join bookings b
on t.train_id=b.train_id
group by t.train_id,t.train_name;

#48. Display train names and total fare collected for each train.
select t.train_id,t.train_name,sum(b.fare) as total_fare
from trains t
join bookings b
on t.train_id=b.train_id
group by t.train_id,t.train_name;

#subqueries

#49. Find bookings with fare equal to the highest fare.
select *from bookings
where fare=(select max(fare) from bookings);

#50. Retrieve trains that have more than one booking.
select t.train_id,t.train_name,count(b.booking_id)
from trains t
join bookings b
on t.train_id=b.train_id
group by t.train_id,t.train_name
having count(b.booking_id)>1;






#creating students table
CREATE TABLE Students (student_id INT PRIMARY KEY, 
student_name VARCHAR(50), 
department VARCHAR(30), marks INT );

#inserting records into students table
INSERT INTO Students (student_id, student_name, department, marks)
VALUES
(1, 'shivani', 'CSE', 85),
(2, 'Rajesh', 'ECE', 74),
(3, 'Anjali', 'CSE', 81),
(4, 'Nikila', 'ECE', 68),
(5, 'Priyanka', 'ECE', 89),
(6, 'Karna', 'IT', 75),
(7, 'Snehitha', 'CSE', 98),
(8, 'Arnavi', 'EEE', 64),
(9, 'Divija', 'IT', 89),
(10, 'Vikram', 'ECE', 79),
(11, 'Mahesh', 'CSE', 78),
(12, 'Ravi', 'EEE', 86),
(13, 'Purna', 'IT', 91),
(14, 'Sanjay', 'ECE', 69),
(15, 'Keerthana', 'CSE', 80);

#31. Find the average marks scored by students in each department.
SELECT department, AVG(marks) AS average_marks
FROM Students
GROUP BY department;

#32. Find departments whose average marks are above 75.
SELECT department, AVG(marks) AS average_marks
FROM Students
GROUP BY department
HAVING AVG(marks) > 75;

#33. Find the highest mark scored in each department.
SELECT department, MAX(marks) AS highest_marks
FROM Students
GROUP BY department;

#34. Find the total number of students in each department.
SELECT department, COUNT(*) AS total_students
FROM Students
GROUP BY department;

#35. Find departments having more than 5 students.
SELECT department, COUNT(*) AS total_students
FROM Students
GROUP BY department
HAVING COUNT(*) > 5;

#36. Display departments sorted by average marks in descending order.
SELECT department, AVG(marks) AS average_marks
FROM Students GROUP BY department
ORDER BY average_marks DESC;

#37. Find the top 3 departments based on average marks.
SELECT department, AVG(marks) AS average_marks
FROM Students GROUP BY department
ORDER BY average_marks DESC LIMIT 3;

#38. Find departments whose average marks are between 70 and 90.
SELECT department, AVG(marks) AS average_marks
FROM Students GROUP BY department HAVING AVG(marks) BETWEEN 70 AND 90;

#39. Find the total marks scored by students in each department.
SELECT department, SUM(marks) AS total_marks FROM Students
GROUP BY department;

#40. Display departments sorted by the total number of students.
SELECT department, COUNT(*) AS total_students
FROM Students GROUP BY department ORDER BY total_students DESC;

#41. Find the lowest mark scored in each department.
SELECT department, MIN(marks) AS lowest_marks
FROM Students GROUP BY department;

#42. Find departments where the highest mark is greater than 90.
SELECT department, MAX(marks) AS highest_marks
FROM Students
GROUP BY department
HAVING MAX(marks) > 90;

#43. Find the number of students scoring above 80 in each department.
SELECT department, COUNT(*) AS students_above_80
FROM Students
WHERE marks > 80
GROUP BY department;

#44. Find departments where more than 3 students scored above 75.
SELECT department, COUNT(*) AS students_above_75
FROM Students WHERE marks > 75
GROUP BY department HAVING COUNT(*) > 3;

#45. Display departments ordered by highest mark in descending order.
SELECT department, MAX(marks) AS highest_marks FROM Students
GROUP BY department ORDER BY highest_marks DESC;

