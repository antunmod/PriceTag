# PriceTag
An application for tracking prices in stores. The application is based on crowdsourcing. It was made as a bachelor's theses at Computer Science at the Faculty of Electrical Engineering and Computing of the University of Zagreb. In this project I was mentored by prof. dr. sc. Dejan Škvorc.

The app provides the following functionalities:
- adding a new product with name, image, producer name, description, price and store in which it was found
- updating existing product price
- looking through recently added products
- searching for products for the given category, subcategory, producer name, store name or product name
- rating the credibility of prices updated by other users

## Rating formula
The user credibility is an important factor in crowdsourcing applications so the users can know if the information which is presented to them is correct and with what level of certainty. The formula for user rating is: </br>
```0.5 + (sum_of_all_user_ratings/number_of_user_ratings)/2```, <br>
where `sum_of_all_user_ratings` is calculated as the sum of other users' ratings of this user (positive or negative) times the credibility of those users and the `number_of_user_ratings` is the total number of ratings for this user. This formula will result in solid rankings, but in the future the total points of the user rating another user have to be used in credibility calculation for more stable rankings.
