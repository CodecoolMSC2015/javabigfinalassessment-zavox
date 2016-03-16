package data;

import human.Person;

import java.util.List;
import java.util.Set;

public abstract class DataReader  {
    public Set<String> searchCriteria;
    public SearchType searchType;

    public void setSearchCriteria(Set<String> searchCriteria){
        this.searchCriteria = searchCriteria;
    }
    public void setSearchType(SearchType searchType){
        this.searchType = searchType;
    }

    public abstract Set<Person> getPersons();
}
