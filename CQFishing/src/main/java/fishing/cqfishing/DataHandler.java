package fishing.cqfishing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataHandler {
    
    public String fisherFileName;
    public ArrayList<Fisher> fisherList;    
    private Map<String, Collection<Fisher>> nameSearch;
    private Map<String, Collection<Fisher>> phoneSearch;
    
    //Default constructor
    public DataHandler(){
        
    }

    //Constructor for data handler
    public DataHandler(String fisherFileName) {
        this.fisherFileName = fisherFileName;
        
        this.fisherList = new ArrayList<>();
        
        this.nameSearch = new HashMap<>();
        
        readFishersFile();
    }
    
    //Method that reads fishers from file
    public void readFishersFile(){
        String line = "";
        File file = new File(fisherFileName);
        
        if(!file.exists())
        {
            return;
        }
        
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(fisherFileName));
            
            while((line = reader.readLine()) != null){
                String[] f = line.split(",");
                
                
                if(f.length == 5) {
                    int id = Integer.parseInt(f[0].trim());
                    String name = f[1].trim();
                    String phone = f[2].trim();
                    LocalDate date = LocalDate.parse(f[3].trim());
                    double amount = Double.parseDouble(f[4].trim());
                    
                    Fisher fisher = new Fisher(id, name, phone, date, amount);
                    fisherList.add(fisher);
                    
                    //Initiate name search
                    if(nameSearch == null){
                        nameSearch = new HashMap<>();
                    }
                    //Populate name search
                    if(!nameSearch.containsKey(name)){
                        nameSearch.put(name, new ArrayList<>());
                    }
                    
                    if (phoneSearch == null) {
                    phoneSearch = new HashMap<>();
                    }
                    
                    if(!phoneSearch.containsKey(phone)){
                        phoneSearch.put(phone, new ArrayList<>());
                    }
                    
                    //Initiate phone number search
                    nameSearch.get(name).add(fisher);
                    
                    //Populate phone number search
                    phoneSearch.get(phone).add(fisher);
                }
            }
            reader.close();
        } catch(IOException e){
            App.customAlert(e.getMessage());
        }
    }
    
    //Get all fishers from list
    public Fisher[] getAllFisher(){
        Fisher [] fisher = new Fisher[fisherList.size()];
        fisher = fisherList.toArray(fisher);
        return fisher;
    }
    
    //Method to sort name by ascending (A-Z)
    public void sortByNameAscending() {
        Collections.sort(fisherList, new Comparator<Fisher>() {
            @Override
            public int compare(Fisher fisher1, Fisher fisher2) {
                return fisher1.getName().compareToIgnoreCase(fisher2.getName());
            }
        });
    }
    
    //Method to sort name by descending (Z-A)
    public void sortByNameDescending() {
        Collections.sort(fisherList, new Comparator<Fisher>() {
            @Override
            public int compare(Fisher fisher1, Fisher fisher2) {
                return fisher2.getName().compareToIgnoreCase(fisher1.getName());
            }
        });
    }
    
    //Method to sort dates from earliest to latest
    public void sortByDateAscending() {
        Collections.sort(fisherList, new Comparator<Fisher>() {
            @Override
            public int compare(Fisher fisher1, Fisher fisher2) {
                return fisher1.getDate().compareTo(fisher2.getDate());
            }
        });
    }
    
    //Method to sort dates from latest to earliest
    public void sortByDateDescending() {
        Collections.sort(fisherList, new Comparator<Fisher>() {
            @Override
            public int compare(Fisher fisher1, Fisher fisher2) {
                return fisher2.getDate().compareTo(fisher1.getDate());
            }
        });
    }
    
    public void removeDuplicates(Collection<String> values){
        Set<String> set = new HashSet<>(values);
        
        for(String value: set){
            System.out.printf("%s ", value);
        }
        System.out.println();
    }
    
    //Method that initiate hashmap || Search by name method
    public Collection <Fisher> searchByName(String name){
        if(nameSearch.containsKey(name)){
            return nameSearch.get(name);
        }
        else{
            return Collections.emptyList();
        }
    }
    
    //Method that initiate hashmap || Search by phone method
    public Collection <Fisher> searchByPhone(String phone){
        if(phoneSearch.containsKey(phone)){
            return phoneSearch.get(phone);
        }
        else{
            return Collections.emptyList();
        }
    }
    
    
}
