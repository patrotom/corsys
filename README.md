# corSys #

## Setup ##

### MySQL database ###

#### Install ####

1. [Download and install MySQL database](https://dev.mysql.com/downloads/installer/)
    * Search for the proper installer based on your operating system
2. Run MySQL
    * Run under default port `3306`
3. Create user `corsys`, database `corsys_dev` and grant all privileges on database `corsys_dev` to user `corsys`
4. To do this, open MySQL shell under root privileges (*nix)

``` bash
mysql> CREATE USER 'corsys'@'localhost' IDENTIFIED BY 'password';
mysql> CREATE DATABASE corsys_dev;
mysql> GRANT ALL PRIVILEGES ON corsys_dev.* TO 'corsys'@'localhost';
```

`Note: Set password exactly to string 'password'`

#### Insert data ####

1. Run [create script](mysql/create_script.sql) on `corsys_dev` database
    * This script will fill in our database with empty tables required by our application

``` bash
mysql -u corsys -p -h localhost corsys_dev < create_script.sql
```

2. Run [insert script](mysql/insert_script.sql) on `corsys_dev` database
    * This script will fill in our generated tables with testing data

``` bash
mysql -u corsys -p -h localhost corsys_dev < insert_script.sql
```

### Java ###

1. Download and install [Java SE Runtime Environment 8](https://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)

## Run ##

1. Run `corsys.jar`

``` bash
java -jar corsys.jar
```

1. Open [localhost:8080](http://localhost:8080) in web browser
2. Log in with credentials

### Test credentials ###

You can log in to the application under these test credentials:

`Note: you will be logged in as a receptionist`

* Username: olga.kubova
* Password: password

You can use this test patient account for creating reservation:

* Username: lubomir.vavra

`Note: you can test creating reservation, listing all reservations and adding a new patient`

`Note: for the purpose of testing of creating a new reservation, please pick only from dates in the range 26.11.2018-29.11.2018 (available test data in DB)`
