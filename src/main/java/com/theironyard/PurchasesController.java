package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Caroline on 3/9/16.
 */
@Controller
public class PurchasesController {
    @Autowired
    CustomerRepository customers;

    @Autowired
    PurchaseRepository purchases;

    @PostConstruct
    public void init() throws FileNotFoundException {
        if (customers.count() == 0 && purchases.count() == 0) {
            File cFile = new File("customers.csv");
            Scanner cScanner = new Scanner(cFile);
            cScanner.nextLine();

            while (cScanner.hasNext()) {
                String line = cScanner.nextLine();
                String[] columns = line.split(",");
                Customer customer = new Customer(columns[0], columns[1]);
                customers.save(customer);
            }

            File pFile = new File("purchases.csv");
            Scanner pScanner = new Scanner(pFile);
            pScanner.nextLine();

            while (pScanner.hasNext()) {
                String line = pScanner.nextLine();
                String[] columns = line.split(",");
                Customer customer = customers.findOne(Integer.valueOf(columns[0]));
                Purchase purchase = new Purchase(columns[1], columns[2], columns[3], columns[4], customer);
                purchases.save(purchase);
            }
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String category, Integer page) {
        page = (page == null) ? 0 : page;
        PageRequest pr = new PageRequest(page, 10);
        Page<Purchase> p;
        if (category != null) {
            p = purchases.findByCategory(pr, category);
        }
        else {
            p = purchases.findAll(pr);
        }
        model.addAttribute("purchases", p);
        model.addAttribute("nextPage", page+1);
        model.addAttribute("showNext", p.hasNext());
        model.addAttribute("category", category);
        return "home";
    }
}


