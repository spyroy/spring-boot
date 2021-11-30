package com.person.person.dao;

import com.person.person.model.Person;
import jdk.jfr.Registered;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao{

    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        if(person.getId() != null) {
            try {
                DB.add(new Person(person.getId(), person.getName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                DB.add(new Person(id, person.getName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id);
        if(personMaybe.isEmpty()){
            return 0;
        }
        DB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person update) {
        return selectPersonById(id).map(person -> {
            int indexOfPersonToUpdate = DB.indexOf(person);
            if(indexOfPersonToUpdate >= 0) {
                try {
                    DB.set(indexOfPersonToUpdate, new Person(id, update.getName()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 1;
            }
            return 0;
        }).orElse(0);
    }

}
