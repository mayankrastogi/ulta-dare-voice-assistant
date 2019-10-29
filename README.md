# Ulta Dare

## Voice Experience for Ulta Beauty -  [Dare Mighty Things Hackathon](https://daremightythings.co/hackathon.html), Chicago
  
**Ulta Dare** is a Google Assistant conversational experience application for [Ulta Beauty](https://www.ulta.com/). This Android app was built in response to the [challenge](https://www.ulta.com/innovation/dmt2019/) sponsored by [Ulta Beauty](https://www.ulta.com/) and [Google Cloud Platform](https://cloud.google.com/) during the [Dare Mighty Things Hackathon](https://daremightythings.co/hackathon.html), held from Oct 26-27th in Chicago.

The experience was designed entirely using [Dialogflow](https://dialogflow.com/) with the help of **web-hooks** hosted on [Google Cloud Functions](https://cloud.google.com/functions/). The sample data provided by [Ulta Beauty](https://ulta.com/) was imported into a **PostgreSQL** database managed by [Google Cloud SQL](https://cloud.google.com/sql/). The web-hooks, written in Python, connected to this database using **SQLAlchemy**. This repository contains the source code for a companion app to the Google Assistant app, which displays the search results for products. 
  
### Team  
  
- Anirudh Nigania  
- [Mayank K Rastogi](https://github.com/mayankrastogi)  
  
### Technologies used  
  
- [Dialogflow](https://dialogflow.com/), for Natural Language Understanding (NLU) and designing the conversational integration with Google Assistant  
- [Google Cloud SQL](https://cloud.google.com/sql/), for hosting the provided sample data in a PostgreSQL database  
- [Google Cloud Functions](https://cloud.google.com/functions/), for hosting the Web Hook for Dialogflow and the API for fetching product catalog  
- [Cloudinary](https://cloudinary.com/), for on-the-fly manipulation of the product images  
- [Android 9 Pie](https://developer.android.com/about/versions/pie), for building a companion app to show search results collated by the assistant  
  
## Features  
  
- Launch the Google Assistant experience  
  
  > "Ok Google, talk to Ulta Dare."  
  
- Check availability of a certain product in a nearby store  
  
  > "Is eyeliner available near me?"

- Check availability of a product of a certain brand  
  
  > "Is MAC eyeliner available?"  

- Make an appointment  
  
  > "I need a haircut."  

- Search for products: Responds with a deep-link to the companion app which displays the results  
  
  > "Show me shampoos."  

- Search for products under a certain price: Responds with a deep-link to the companion app which displays the results  
  
  > "Show me all the perfumes under 40 dollars."  

- Exit  
  
  > "Goodbye."
