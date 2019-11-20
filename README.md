# Taxman

Tax calculator for sales receipts.

[![Build Status](https://travis-ci.org/vinz486/taxman.svg?branch=master)](https://travis-ci.org/vinz486/taxman)

#### Run all unit tests:
```
./gradlew clean test
```

#### Build application and generate start scripts:
```
./gradlew installDist
```

#### Finally, execute application:
```
build/install/taxman/bin/taxman
```
or, simply run:
```
./gradlew run
```

You should get an output like this:
```
╒══════════════════════════════════════════════╕
│                  Receipt ID                  │
│     c4938c4e-1cd2-4778-ab8d-792c5119381b     │
╞══════╤══════════════════════════════╤════════╡
│  1 x │                         Book │  12.49 │
│  1 x │                     Music CD │  16.49 │
│  1 x │                Chocolate Bar │   0.85 │
╞══════╧══════════════════════════════╧════════╡
│                             Sales Taxes 1.50 │
╞══════════════════════════════════════════════╡
│                                  Total 29.83 │
╘══════════════════════════════════════════════╛

╒══════════════════════════════════════════════╕
│                  Receipt ID                  │
│     7e5ba5e1-62ea-47f8-9144-df60c87529a6     │
╞══════╤══════════════════════════════╤════════╡
│  1 x │ (imported) Box of Chocolates │  10.50 │
│  1 x │ (imported) Bottle of Perfume │  54.65 │
╞══════╧══════════════════════════════╧════════╡
│                             Sales Taxes 7.65 │
╞══════════════════════════════════════════════╡
│                                  Total 65.15 │
╘══════════════════════════════════════════════╛

╒══════════════════════════════════════════════╕
│                  Receipt ID                  │
│     13b1b8dd-3e9a-4108-8bfc-08040f1024cd     │
╞══════╤══════════════════════════════╤════════╡
│  1 x │ (imported) Bottle of Perfume │  32.19 │
│  1 x │            Bottle of Perfume │  20.89 │
│  1 x │     Packet of Headache Pills │   9.75 │
│  1 x │ (imported) Box of Chocolates │  11.85 │
╞══════╧══════════════════════════════╧════════╡
│                             Sales Taxes 6.70 │
╞══════════════════════════════════════════════╡
│                                  Total 74.68 │
╘══════════════════════════════════════════════╛
```

#### Components and Libraries

 * CSV input parsing is got form [OpenCSV] (http://opencsv.sourceforge.net/)
 * Getter/Setter Builder autogeneration is form [Project Lombok] (https://projectlombok.org/)
 * Dependency Injection is provided by [Dagger2] (https://dagger.dev/)
 * ASCII Table rendering is produced by (ASCII Table) (https://github.com/vdmeer/asciitable)
 
#### Improvements
There are a lot of discussions about the rigth way to represent money and prices in the Java world. BigDecimal can be a solution but you must deal with some Exeptions thown when loosing precision (eg. 10 / 3 divisions).

There is a java library called [JavaMoney] (https://javamoney.github.io/) that worth some exploration for sure.

