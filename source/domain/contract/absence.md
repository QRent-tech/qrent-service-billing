# Absence, how it works: 
## Add a new Absence:
Absence can be added to any driver.
### Request:
-  Long driverId;
-  LocalDate dateStart;
-  LocalDate dateEnd;
-  Boolean withCar;
-  reason [VACATION, SICK_LEAVE];
-  String comment;

### Validation:  
- check if requested absence has time overlapping;
- check if requested absence's time interval overlaps with Balance calculated period;

## Update an existing Absence:
Existing Absence can be updated.
### Request:
-  Long id;
-  Long driverId;
-  LocalDate dateStart;
-  LocalDate dateEnd;
-  Boolean withCar;
-  reason [VACATION, SICK_LEAVE];
-  String comment;

### Validation:
- check if updated absence time interval has time overlapping with another absences;
- check if requested absence's time interval overlaps with Balance calculated period;

## Delete an existing Absence:
Existing Absence can be deleted.
### Request:
-  Long id;

### Validation:
- check if requested absence's time interval overlaps with Balance calculated period;

## Involvement into Rent Calculation:
Absences counted-in during Weekly Rent calculation. The System select a count of Absence days that occur during calculated week.
In case of calculated week has 1 absence day, no Rent amount adjustment required.
In case of calculated week has 2..5 absence days, new transaction with type "absence adjustment" will be created. The amount for this transaction is calculated by next formula: 

> **amount = (defaulRentAmount /  6) * absence-days-count;**
