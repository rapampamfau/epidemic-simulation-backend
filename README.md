To test the application, after running you can use SWAGGER documentation (http://localhost:8080/swagger-ui.html) or Postman.
Example body value JSON should look like this: 
  {
    "simulationName": "TestSim",
    "populationQuantity": 10000,
    "initialNumberOfInfected": 10,
    "virusReproductionRate": 2.0,
    "mortalityRate": 2.0,
    "daysFromInfectionToRecovery": 2,
    "daysFromInfectionToDeath": 1,
    "simulationDuration": 10
  }
