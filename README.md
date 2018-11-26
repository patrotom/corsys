# corSys #

## Setup ##

### MySQL database ###

#### Install ####

1. [Download and install MySQL database](https://dev.mysql.com/downloads/installer/)
    * Search for the proper installer based on your operating system
2. Run MySQL
3. Create user `corsys`, database `corsys_dev` and grant all privileges on database `corsys_dev` to user `corsys`
4. To do this, open MySQL shell under root privileges (*nix)

``` bash
mysql> CREATE USER 'corsys'@'localhost' IDENTIFIED BY 'password';
mysql> CREATE DATABASE corsys_dev;
mysql> GRANT ALL PRIVILEGES ON corsys_dev.* TO 'corsys'@'localhost';
```

`Note: Set password exactly to string 'password'`

#### Insert data ####

1. Run [create script](/mysql/create_script.sql) on `corsys_dev` database
    * This script will fill in our database with empty tables required by our application

``` bash
mysql -u corsys -p -h localhost corsys_dev < create_script.sql
```

2. Run [insert script](/mysql/insert_script.sql) on `corsys_dev` database
    * This script will fill in our genereated tables with testing data

``` bash
mysql -u corsys -p -h localhost corsys_dev < insert_script.sql
```

### Java ###

1. Download and install [Java SE Runtime Environment 8](https://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)


## Run ##

1. Run corsys.jar
