# Offer REST Server With Spring for REST
1-This is a Spring Boot Rest API

2- Run WorldpayOffersServer class
(RunDebugConf.png)

3- in Browser:

@Get
http://localhost:8080/Worldpay/offers

@Get
http://localhost:8080/Worldpay/offer/{id}
PS:
	
	- http://localhost:8080/Worldpay/offer/1 will return null 
	since it is expired

	- http://localhost:8080/Worldpay/offer/2 will return the object

	- http://localhost:8080/Worldpay/offer/3 will return the object

@Delete
http://localhost:8080/Worldpay/offer/{id}

@Put
http://localhost:8080/Worldpay/offer/{id}

@Post
http://localhost:8080/Worldpay/offer/{id}
- for testing SOAPUI.png & Rest Test IDEA.png can be a referance

- offers are structured as
        
        1- Data Class - holds, sorts and lists Offers - has List of Offers as data
        
        2- Offer Class - has id, name, Price Class, description , expiration date, and cancelled as data
        
        3- Price Class - has amount and currency as data

- offers are read through a json file and has three default offers:

		1- Atilas Outdated offer
		
		2- Connors Valid offer
		
		3- Malcolms Valid offer


- offers first read from offers_data.json file 

- offers gets checked when they are retrieved by getByID methods.
if they are outdated (as time goes) then they get cancelled 
(setCancelled => true)




NOTES:
1- if value returns null from the webservice ,
it throws a controlled exception to boot

(OfferExistsException & OfferNotFoundException)

I  have also embedded a "notFound()" ResponseEntity 

2- Im using both ResponseEntityFormer and
 (OfferExistsException || OfferNotFoundException) to send error message 
 if offer does not exist (Even though its a bit redundant)

