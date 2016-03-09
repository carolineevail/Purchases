package com.theironyard;

/**
 * Created by Caroline on 3/9/16.
 */
public class reqs {
}


//    In your controller, create a @PostConstruct method called init to parse the CSV files
//      For each CSV file, you should loop over each line, parse each column into a Customer or Purchase object, and add
//          it to a repository
//      Make it so this only happens when the repositories are empty

//    Edit home.html and the / route to make it display the purchases
//      It should display the results in an HTML table so the columns line up (see screenshot below)
//      You should be able to see the name and email from the customer table, and the category, credit card, cvv,
//          and date fields from the purchase table

//    Filter the table by category via a query parameter

//    In PurchaseRepository, add a method that finds Purchase objects by category

//    Modify your / route to take a category parameter
//      If the parameter isn't null, call the method you made instead of findAll

//    Displays links at the top that allow you to filter by each category