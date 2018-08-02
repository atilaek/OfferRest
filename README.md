# Worldpay Job Application Demo 
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

- offers are read through a json file and has three default offers:

		1- Atilas Outdated offer
		
		2- Connors Valid offer
		
		3- Malcolms Valid offer


- offers first read from offers_data.json file 

- offers gets checked when they are retrieved by getByID methods.
if they are outdated (as time goes) then they get cancelled 
(setCancelled => true)




NOTES TO DISCUSS IN THE INTERVIEW:
1- if value returns null from the webservice ,
it throws a controlled exception to boot

(OfferExistsException & OfferNotFoundException)

I  have also embedded a "notFound()" ResponseEntity 

2- Im using both ResponseEntityFormer and
 (OfferExistsException || OfferNotFoundException) to send error message 
 if offer does not exist (Even though its a bit redundant)

