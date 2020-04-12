### Overview
This service was developed as part of the [Introduction to Azure Spring Cloud](https://medium.com/@smoothed9/introduction-to-azure-spring-cloud-part-1-introduction-azure-setup-30fc3ed84f2b) series. 

Order Service is a pass-through application. It demonstrates the usage of Service Registry provided by the Azure Spring Cloud. 

Order Service receives an order and invokes the [Inventory Service](https://github.com/smoothed9/azure-spring-cloud-inventory-service) using Netflix Eureka and OpenFeign, to update the inventory in an Azure Cosmos Database. 

More details about this service can be found in the documentation @ [Introduction to Azure Spring Cloud — Part 3: Order Service](https://medium.com/@smoothed9/introduction-to-azure-spring-cloud-part-3-order-service-363c25d1b072)