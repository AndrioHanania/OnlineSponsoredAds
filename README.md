Online Sponsored Ads

As an ad tech company we want to develop a module that
enables sellers to create campaigns for
promoting their products.

Ways to run the application:

1. using docker
Need to run 'docker-compose up' inside the dir where the
Dockerfile is located.
In this way you don't need to install PostgreSQL and create
DB with my info.
2. using the Spring Boot configuration main
In this case you will need to install PostgreSQL and create
DB and changed the info in the application.yml file.
And also connect the data source url into intellij.


API:
(Sorry I wanted to use Swagger, but somehow it didn't work :) )

end-points for products:

1. GET http://localhost:8080/api/products/getAll

2. POST http://localhost:8080/api/products/createProduct
   Content-Type: application/json
{
"serialNumber": 1,
"title": "product1",
"category": "category1",
"price": 34.60
}

3. PUT http://localhost:8080/api/products/updateCategory?productId=1&newCategory=category2

4. PUT http://localhost:8080/api/products/updatePrice?productId=1&newPrice=30.55

5. DELETE http://localhost:8080/api/products/deleteProduct?productId=1

end-points for campaigns:

1. GET http://localhost:8080/api/campaigns/getAll

2. POST http://localhost:8080/api/campaigns/createCampaign
   Content-Type: application/json
{
"name": "campaign1",
"date": "07\\12\\2023",
"bid": 191.78,
"productIds": [3,6,24]
}

3. PUT http://localhost:8080/api/campaigns/updateBid?campaignId=1&newBid=130.88

4. PUT http://localhost:8080/api/campaigns/updateProducts
   Content-Type: application/json
{
"campaignId": 1,
"productIds": [3,5,24]
}

5. DELETE http://localhost:8080/api/campaigns/deleteCampaign?campaignId=1

6. DELETE http://localhost:8080/api/campaigns/deleteAllDeactivate


end-points for serve ads:

1. GET http://localhost:8080/api/ads/serveAd?serveAd=category1

2. DELETE http://localhost:8080/api/ads/dropDB





